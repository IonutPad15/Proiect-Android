package com.example.proiectrestaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeniuriAdapter extends RecyclerView.Adapter<MeniuriAdapter.MyViewHolder> {
    List<Meniu> meniuri;
    Action<String> onMenuSelected;
    public MeniuriAdapter(List<Meniu> meniuri,Action<String> onMenuSelected) {
        this.meniuri = meniuri;
        this.onMenuSelected = onMenuSelected;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meniuri_item,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onContinentSeleceted.Perform(String) v.getTag(R.id.item));
                onMenuSelected.perform((String) v.getTag(R.id.meniu_item));
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meniu meniu = meniuri.get(position);
        holder.itemView.setTag(R.id.meniu_item, meniu);
        holder.nameTxtView.setText(meniu.nume);
        holder.pretTxtView.setText(meniu.pret+" lei");
    }

    @Override
    public int getItemCount() {
        return meniuri.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{//mai eficient decand findViewById


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxtView = itemView.findViewById(R.id.nume);
            pretTxtView = itemView.findViewById(R.id.pret);

        }
        TextView nameTxtView;
        TextView pretTxtView;

    }
}
