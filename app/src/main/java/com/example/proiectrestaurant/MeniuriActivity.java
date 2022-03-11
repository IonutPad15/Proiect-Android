package com.example.proiectrestaurant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeniuriActivity extends AppCompatActivity implements OnMenuListener {
    Dialog popup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popup=new Dialog(this);
        setContentView(R.layout.meniuri_activity);
        RecyclerView recyclerView = findViewById(R.id.meniuriRecycleView);
        //setContentView(R.layout.recycler_view);
        //RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //LayoutManager: modalitatea in care sunt afisate pe ecran
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //false ca sa fie modul normal, de sus in jos. Daca era true, era de jos in sus
        //Adapter- modalitatea prin care ii spunem lui RecyclerView ce sa afiseze pe ecan
        //recyclerView.setAdapter(new MeniuriAdapter((Helper.getMeniuri()),this));
        recyclerView.setAdapter(new MeniuriAdapter((Helper.getMeniuri()),this,popup));
        /*recyclerView.setAdapter(new MeniuriAdapter(Helper.getMeniuri(), new Action<String>(){

            @Override
            public void perform(String args) {
                Toast.makeText(MeniuriActivity.this,args,Toast.LENGTH_SHORT).show();
            }
        }));*/
        findViewById(R.id.shop_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeniuriActivity.this, CosActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onMenuClick(int position) {
        Intent intent = new Intent(this, ProduseActivity.class);
        List<Meniu> meniuri = Helper.getMeniuri();
        Meniu meniu = meniuri.get(position);
        intent.putExtra(ProduseActivity.PRODUSE_KEY,meniu.produse);
        intent.putExtra(ProduseActivity.MENU_KEY,meniu);
        intent.putExtra(ProduseActivity.MENU_ID,position);
        startActivity(intent);

        //Button btn = findViewById()
    }


//    public void ShowPopUp1(View view) {
//        List<Meniu> meniuri = Helper.getMeniuri();
//        Meniu meniu = meniuri.get(position);
//        TextView txtCloseView, numeMeniuView, pretTxtView, cantitateTxtView;
//        popup.setContentView(R.layout.adauga_cos_popup);
//        txtCloseView = popup.findViewById(R.id.close);
//        numeMeniuView = popup.findViewById(R.id.nume_meniu);
//        numeMeniuView.setText(meniu.nume);
//        pretTxtView = popup.findViewById(R.id.pret);
//        pretTxtView.setText(meniu.pret + " lei");
//        cantitateTxtView = popup.findViewById(R.id.quantity);
//        cantitateTxtView.setText("10");
//        Button btnPlus, btnMinus;
//        btnPlus = popup.findViewById(R.id.plus);
//        btnMinus = popup.findViewById(R.id.minus);
//        txtCloseView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popup.dismiss();
//            }
//        });
//        popup.show();
//    }
}
