package com.example.proiectrestaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MeniuriAdapter extends RecyclerView.Adapter<MeniuriAdapter.MyViewHolder> {
    List<Meniu> meniuri;
    Action<String> onMenuSelected;
    OnMenuListener onMenuListener ;
    public MeniuriAdapter(List<Meniu> meniuri,Action<String> onMenuSelected) {
        this.meniuri = meniuri;
        this.onMenuSelected = onMenuSelected;
    }
    public MeniuriAdapter(List<Meniu> meniuri, OnMenuListener onMenuListener) {
        this.meniuri = meniuri;
        this.onMenuListener = onMenuListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meniuri_item,parent,false);
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuSelected.perform((String) v.getTag(R.id.meniu_item));

            }
        });
        return new MyViewHolder(view);*/
        return new MyViewHolder(view, onMenuListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meniu meniu = meniuri.get(position);
        holder.itemView.setTag(R.id.meniu_item, meniu.nume);
        holder.nameTxtView.setText(meniu.nume);
        holder.pretTxtView.setText(meniu.pret+" lei");
        holder.imgView.setImageResource(meniu.img);
    }

    @Override
    public int getItemCount() {
        return meniuri.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{//mai eficient decand findViewById


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxtView = itemView.findViewById(R.id.nume);
            pretTxtView = itemView.findViewById(R.id.pret);
            imgView = itemView.findViewById(R.id.meniu_image);

        }
        public MyViewHolder(@NonNull View itemView, OnMenuListener onMenuListener) {
            super(itemView);
            nameTxtView = itemView.findViewById(R.id.nume);
            pretTxtView = itemView.findViewById(R.id.pret);
            imgView = itemView.findViewById(R.id.meniu_image);
            this.onMenuListener = onMenuListener;
            itemView.setOnClickListener(this);
        }
        TextView nameTxtView;
        TextView pretTxtView;
        ImageView imgView;
        OnMenuListener onMenuListener;

        @Override
        public void onClick(View view) {
            onMenuListener.onMenuClick(getAbsoluteAdapterPosition());
        }
    }

}
