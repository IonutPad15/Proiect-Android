package com.example.proiectrestaurant.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectrestaurant.utils.Action;
import com.example.proiectrestaurant.utils.Comanda;
import com.example.proiectrestaurant.utils.Meniu;
import com.example.proiectrestaurant.utils.OnMenuListener;
import com.example.proiectrestaurant.R;
import com.example.proiectrestaurant.activities.CosActivity;

import java.util.List;


public class MeniuriAdapter extends RecyclerView.Adapter<MeniuriAdapter.MyViewHolder> {
    List<Meniu> meniuri;
    Dialog popup;
    Action<String> onMenuSelected;
    Context context;
    View rootView;

    OnMenuListener onMenuListener ;
    public MeniuriAdapter(List<Meniu> meniuri, OnMenuListener onMenuListener, Dialog popup) {
        this.meniuri = meniuri;
        this.onMenuListener = onMenuListener;
        this.popup=popup;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meniuri_item,parent,false);
        context = parent.getContext();
        rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        return new MyViewHolder(view, onMenuListener);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meniu meniu = meniuri.get(position);
        holder.itemView.setTag(R.id.meniu_item, meniu.getNume());
        holder.nameTxtView.setText(meniu.getNume());
        holder.pretTxtView.setText(meniu.getPret()+" lei");
        holder.imgView.setImageResource(meniu.getImg());
        //holder.btn.setOnClickListener(BtnClick(position));

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comanda comanda = Comanda.getInstance();
                TextView txtCloseView,numeMeniuView, pretTxtView, cantitateTxtView;
                popup.setContentView(R.layout.adauga_cos_popup);
                View popupview= popup.findViewById(R.id.popup);


                Drawable res = rootView.getResources().getDrawable(meniu.getImg());
                popupview.setBackground(res);
                popupview.getBackground().setAlpha(120);
                txtCloseView = popup.findViewById(R.id.close);
                numeMeniuView = popup.findViewById(R.id.nume_meniu);
                numeMeniuView.setText(meniu.getNume());
                pretTxtView = popup.findViewById(R.id.pret);
                cantitateTxtView = popup.findViewById(R.id.quantity);
                final int[] cantitate = {comanda.getMenuCount(position)};
                if(cantitate[0]==0){
                    cantitate[0]++;
                    CosActivity.pretTotal+=meniu.getPret();
                }
                pretTxtView.setText(meniu.getPret()*cantitate[0]+" lei");
                comanda.setMenuCount(position,cantitate[0]);
                cantitateTxtView.setText(comanda.getMenuCount(position)+"");
                Button btnPlus, btnMinus;
                btnPlus=popup.findViewById(R.id.plus);
                double pret = meniu.getPret();
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

        TextView btn ;

        @Override
        public void onClick(View view) {
            if(view.getId()==btn.getId()){

            }
            else
            onMenuListener.onMenuClick(getAbsoluteAdapterPosition());
        }
    }

}
