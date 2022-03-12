package com.example.proiectrestaurant;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MeniuriAdapter extends RecyclerView.Adapter<MeniuriAdapter.MyViewHolder> {
    List<Meniu> meniuri;
    Dialog popup;
    Action<String> onMenuSelected;
    OnMenuListener onMenuListener ;
    public MeniuriAdapter(List<Meniu> meniuri,Action<String> onMenuSelected) {
        this.meniuri = meniuri;

        this.onMenuSelected = onMenuSelected;
    }
    public MeniuriAdapter(List<Meniu> meniuri, OnMenuListener onMenuListener, Dialog popup) {
        this.meniuri = meniuri;
        this.onMenuListener = onMenuListener;
        this.popup=popup;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meniu meniu = meniuri.get(position);
        holder.itemView.setTag(R.id.meniu_item, meniu.nume);
        holder.nameTxtView.setText(meniu.nume);
        holder.pretTxtView.setText(meniu.pret+" lei");
        holder.imgView.setImageResource(meniu.img);
        //holder.btn.setOnClickListener(BtnClick(position));

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comanda comanda = Comanda.getInstance();
                TextView txtCloseView,numeMeniuView, pretTxtView, cantitateTxtView;
                popup.setContentView(R.layout.adauga_cos_popup);
                txtCloseView = popup.findViewById(R.id.close);
                numeMeniuView = popup.findViewById(R.id.nume_meniu);
                numeMeniuView.setText(meniu.nume);
                pretTxtView = popup.findViewById(R.id.pret);
                cantitateTxtView = popup.findViewById(R.id.quantity);
                final int[] cantitate = {comanda.getMenuCount(position)};
                if(cantitate[0]==0){
                    cantitate[0]++;
                    CosActivity.pretTotal+=meniu.pret;
                }
                pretTxtView.setText(meniu.pret*cantitate[0]+" lei");
                comanda.setMenuCount(position,cantitate[0]);
                cantitateTxtView.setText(comanda.getMenuCount(position)+"");
                Button btnPlus, btnMinus;
                btnPlus=popup.findViewById(R.id.plus);
                double pret = meniu.pret;
                final double[] total = {0};
                btnPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cantitate[0] = Integer.parseInt(cantitateTxtView.getText().toString());
                        cantitate[0]++;
                        CosActivity.pretTotal+=pret;
                        comanda.setMenuCount(position,cantitate[0]);
                        total[0] = pret*cantitate[0];
                        cantitateTxtView.setText(comanda.getMenuCount(position)+"");
                        pretTxtView.setText(total[0]+" lei");

                    }
                });
                btnMinus = popup.findViewById(R.id.minus);
                btnMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cantitate[0] = Integer.parseInt(cantitateTxtView.getText().toString());
                        if(cantitate[0]>0) {
                            cantitate[0]--;
                            CosActivity.pretTotal-=pret;
                        }
                        comanda.setMenuCount(position,cantitate[0]);
                        cantitateTxtView.setText(comanda.getMenuCount(position)+"");
                        total[0] = pret*cantitate[0];
                        pretTxtView.setText(total[0]+" lei");
                    }
                });
                txtCloseView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });
                popup.show();
            }
        });


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

            btn = itemView.findViewById(R.id.btnmeniu);
        }
        TextView nameTxtView;
        TextView pretTxtView;
        ImageView imgView;
        OnMenuListener onMenuListener;

        Button btn ;

        @Override
        public void onClick(View view) {
            if(view.getId()==btn.getId()){

            }
            else
            onMenuListener.onMenuClick(getAbsoluteAdapterPosition());
        }
    }

}
