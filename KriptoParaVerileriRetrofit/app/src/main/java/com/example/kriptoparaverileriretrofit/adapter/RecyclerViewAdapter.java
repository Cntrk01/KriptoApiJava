package com.example.kriptoparaverileriretrofit.adapter;

import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kriptoparaverileriretrofit.R;
import com.example.kriptoparaverileriretrofit.model.KriptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<KriptoModel> kriptoModels;
    private String[]colors={"#a3ff00","#ff00aa","#b4a7d6"};

    public RecyclerViewAdapter(ArrayList<KriptoModel> kriptoModels) {
        this.kriptoModels = kriptoModels;
        notifyDataSetChanged();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textname;
        TextView textprice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }
        public void bind(KriptoModel kriptoModel,String[]color,Integer position){
            itemView.setBackgroundColor(Color.parseColor(color[position%3]));
            textname=itemView.findViewById(R.id.text_name);
            textprice=itemView.findViewById(R.id.text_price);
            textname.setText(kriptoModel.currency);
            textprice.setText(kriptoModel.price);
        }
    }
    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
            holder.bind(kriptoModels.get(position),colors,position); //bind fonksiyonunu burda cagirdim ve parametrelerini verdim.
    }

    @Override
    public int getItemCount() {
        return kriptoModels.size();
    }


}
