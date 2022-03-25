package com.example.proiectrestaurant.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectrestaurant.utils.Action;
import com.example.proiectrestaurant.utils.Comanda;
import com.example.proiectrestaurant.utils.Meniu;
import com.example.proiectrestaurant.utils.OnMenuListener;
import com.example.proiectrestaurant.R;
import com.example.proiectrestaurant.activities.ComandaActivity;
import com.example.proiectrestaurant.activities.CosActivity;

import java.util.List;


public class CosAdapter extends RecyclerView.Adapter<CosAdapter.MyViewHolder> {
    List<Meniu> meniuri;
    Action<String> onMenuSelected;
    OnMenuListener onMenuListener ;
    Context context;
    View rootView;
    TextView txtTotalView;
    Button btnComanda;
    //TextView txtTotalView =CosActivity.txtTotalView;
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
        context = parent.getContext();
        rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        txtTotalView = rootView.findViewById(R.id.total);
        btnComanda = rootView.findViewById(R.id.btnComanda);
        return new MyViewHolder(view, onMenuListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meniu meniu = meniuri.get(position);
        holder.itemView.setTag(R.id.cos_item, meniu.getNume());
        holder.nameTxtView.setText(meniu.getNume());
        Comanda comanda = Comanda.getInstance();
        holder.quantTxtView.setText(comanda.getMenuCount(position)+"");
        holder.pretTxtView.setText(meniu.getPret()*comanda.getMenuCount(position)+" lei");
        if(Comanda.getInstance().getMenuCount(position)==0) {
            holder.itemView.setVisibility(View.GONE);
        }
        else{
            holder.itemView.setVisibility(View.VISIBLE);
        }
        holder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnComanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CosActivity.pretTotal!=0) {
                    Intent intent = new Intent(context, ComandaActivity.class);
                    context.startActivity(intent);
                }
                else Toast.makeText(context, "Nu aveti nimic in cos", Toast.LENGTH_SHORT).show();
            }
        });
        double pret = meniu.getPret();
        final int[] cantitate = {comanda.getMenuCount(position)};
        final double[] total = {0};
        txtTotalView.setText(CosActivity.pretTotal+"lei");
        holder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantitate[0] = Integer.parseInt(holder.quantTxtView.getText().toString());
                cantitate[0]++;
                CosActivity.pretTotal+=pret;
                Log.d("CosAdapter",CosActivity.pretTotal+"");
                txtTotalView.setText(CosActivity.pretTotal+"lei");
                comanda.setMenuCount(position,cantitate[0]);
                total[0] = pret*cantitate[0];
                holder.quantTxtView.setText(comanda.getMenuCount(position)+"");
                holder.pretTxtView.setText(total[0]+" lei");


            }
        });
        holder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantitate[0] = Integer.parseInt(holder.quantTxtView.getText().toString());
                if(cantitate[0]>0) {
                    cantitate[0]--;
                    CosActivity.pretTotal-=pret;
                    Log.d("CosAdapter",CosActivity.pretTotal+"");
                    txtTotalView.setText(CosActivity.pretTotal+"lei");
                    //cosActivity.update();
                }

                if(cantitate[0]==0)holder.itemView.setVisibility(View.GONE);

                comanda.setMenuCount(position,cantitate[0]);
                holder.quantTxtView.setText(comanda.getMenuCount(position)+"");
                total[0] = pret*cantitate[0];
                holder.pretTxtView.setText(total[0]+" lei");
            }
        });

    }


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
            btnplus = itemView.findViewById(R.id.plus);
            btnminus=itemView.findViewById(R.id.minus);
        }
        TextView nameTxtView;
        TextView quantTxtView;
        OnMenuListener onMenuListener;

        TextView pretTxtView;

        Button btnplus, btnminus ;

        @Override
        public void onClick(View view) {
                onMenuListener.onMenuClick(getAbsoluteAdapterPosition());
        }
    }

}