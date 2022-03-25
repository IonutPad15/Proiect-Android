package com.example.proiectrestaurant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectrestaurant.utils.Produs;
import com.example.proiectrestaurant.R;

import java.util.ArrayList;


public class ProduseAdapter extends RecyclerView.Adapter<ProduseAdapter.MyViewHolder> {
    ArrayList<Produs> produse;
    public ProduseAdapter(ArrayList<Produs> produse) {
        this.produse = produse;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produse_item,parent,false);
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuSelected.perform((String) v.getTag(R.id.meniu_item));

            }
        });*/
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produs produs = produse.get(position);
        holder.itemView.setTag(R.id.produs_item, produs.get_nume());
        holder.nameTxtView.setText(produs.get_nume());
        holder.gramajTxtView.setText(produs.get_gramaj()+"");
        holder.umTxtView.setText(produs.getUnitate_masura());
        holder.cantitateTxtView.setText(produs.get_cantitate()+"x");
    }

    @Override
    public int getItemCount() {
        return produse.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {//mai eficient decand findViewById


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxtView = itemView.findViewById(R.id.nume);
            gramajTxtView = itemView.findViewById(R.id.gramaj);
            cantitateTxtView = itemView.findViewById(R.id.cantitate);
            umTxtView = itemView.findViewById(R.id.um);
            //imgView = itemView.findViewById(R.id.meniu_image);

        }

        TextView nameTxtView;
        TextView gramajTxtView;
        TextView cantitateTxtView;
        TextView umTxtView;
        //ImageView imgView;

    }

}
