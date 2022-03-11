package com.example.proiectrestaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CosAdapter extends RecyclerView.Adapter<CosAdapter.MyViewHolder> {
    List<Meniu> meniuri;
    Action<String> onMenuSelected;
    OnMenuListener onMenuListener ;
    public CosAdapter(List<Meniu> meniuri,Action<String> onMenuSelected) {
        this.meniuri = meniuri;
        this.onMenuSelected = onMenuSelected;
    }
    public CosAdapter(List<Meniu> meniuri, OnMenuListener onMenuListener) {
        this.meniuri = meniuri;
        this.onMenuListener = onMenuListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cos_layout_item,parent,false);
        return new MyViewHolder(view, onMenuListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meniu meniu = meniuri.get(position);
        holder.itemView.setTag(R.id.cos_item, meniu.nume);
        holder.nameTxtView.setText(meniu.nume);
        Comanda comanda = Comanda.getInstance();
        holder.quantTxtView.setText(comanda.getMenuCount(position)+"");
        holder.pretTxtView.setText(meniu.pret*comanda.getMenuCount(position)+" lei");
        if(Comanda.getInstance().getMenuCount(position)==0) {
            holder.itemView.setVisibility(View.GONE);
        }
        else{
            holder.itemView.setVisibility(View.VISIBLE);
        }
        //holder.btn.setOnClickListener(BtnClick(position));

    }
//    public View.OnClickListener BtnClick(int position){
//
//    }

    @Override
    public int getItemCount() {
        return meniuri.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{//mai eficient decand findViewById


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxtView = itemView.findViewById(R.id.nume_meniu);
            pretTxtView = itemView.findViewById(R.id.pret);

        }
        public MyViewHolder(@NonNull View itemView, OnMenuListener onMenuListener) {
            super(itemView);
            nameTxtView = itemView.findViewById(R.id.nume_meniu);
            pretTxtView = itemView.findViewById(R.id.pret);
            this.onMenuListener = onMenuListener;
            itemView.setOnClickListener(this);
            quantTxtView = itemView.findViewById(R.id.quantity);
            //btn = itemView.findViewById(R.id.btnmeniu);
        }
        TextView nameTxtView;
        TextView pretTxtView;
        TextView quantTxtView;
        OnMenuListener onMenuListener;

        //Button btn ;

        @Override
        public void onClick(View view) {
                onMenuListener.onMenuClick(getAbsoluteAdapterPosition());
        }
    }

}