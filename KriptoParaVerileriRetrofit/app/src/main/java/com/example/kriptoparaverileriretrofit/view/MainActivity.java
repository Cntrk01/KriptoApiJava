package com.example.kriptoparaverileriretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kriptoparaverileriretrofit.R;
import com.example.kriptoparaverileriretrofit.adapter.RecyclerViewAdapter;
import com.example.kriptoparaverileriretrofit.model.KriptoModel;
import com.example.kriptoparaverileriretrofit.service.KriptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<KriptoModel> kriptoModels;
    Retrofit retrofi;
    private String BASE_URL="https://raw.githubusercontent.com/atilsamancioglu/";
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //api : https://github.com/atilsamancioglu/K21-JSONDataSet/blob/master/crypto.json
        recyclerView=findViewById(R.id.recyclerView);

        Gson gson=  new GsonBuilder().setLenient().create();
        retrofi=new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();
    }
    private void loadData(){
        KriptoAPI kriptoAPI=retrofi.create(KriptoAPI.class);
        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(kriptoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::data));
        /*
        * Call<List<KriptoModel>> call=kriptoAPI.getData();

        call.enqueue(new Callback<List<KriptoModel>>() {
            @Override
            public void onResponse(Call<List<KriptoModel>> call, Response<List<KriptoModel>> response) {
                if(response.isSuccessful()){
                    List<KriptoModel> responseList=response.body();
                    kriptoModels=new ArrayList<>(responseList); //responseList bir Liste.Ondan dolayı arrayliste çevirdim eşitledim
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter=new RecyclerViewAdapter(kriptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<KriptoModel>> call, Throwable t) {
                    t.printStackTrace();
            }
        });*/
    }
    public void data(List<KriptoModel> responseList){
        kriptoModels=new ArrayList<>(responseList); //responseList bir Liste.Ondan dolayı arrayliste çevirdim eşitledim
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter=new RecyclerViewAdapter(kriptoModels);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}