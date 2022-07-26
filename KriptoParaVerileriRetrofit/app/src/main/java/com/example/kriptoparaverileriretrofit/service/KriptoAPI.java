package com.example.kriptoparaverileriretrofit.service;

import com.example.kriptoparaverileriretrofit.model.KriptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface KriptoAPI {
    @GET("K21-JSONDataSet/master/crypto.json")
    Observable<List<KriptoModel>> getData();

}
