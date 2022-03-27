package com.example.proiectrestaurant.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectrestaurant.utils.Helper;
import com.example.proiectrestaurant.utils.Meniu;
import com.example.proiectrestaurant.adapters.MeniuriAdapter;
import com.example.proiectrestaurant.utils.OnMenuListener;
import com.example.proiectrestaurant.R;

import java.util.List;

public class MeniuriActivity extends AppCompatActivity implements OnMenuListener {
    private Dialog popup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popup = new Dialog(this);
        setContentView(R.layout.meniuri_activity);
        RecyclerView recyclerView = findViewById(R.id.meniuriRecycleView);
        View meniuriview= findViewById(R.id.meniuri_activity);
        meniuriview.getBackground().setAlpha(120);
        //LayoutManager: modalitatea in care sunt afisate pe ecran
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //false ca sa fie modul normal, de sus in jos. Daca era true, era de jos in sus
        //Adapter- modalitatea prin care ii spunem lui RecyclerView ce sa afiseze pe ecan
        recyclerView.setAdapter(new MeniuriAdapter((Helper.getMeniuri()), this, popup));
        findViewById(R.id.back_icon).setVisibility(View.INVISIBLE);
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
        intent.putExtra(ProduseActivity.PRODUSE_KEY, meniu.getProduse());
        intent.putExtra(ProduseActivity.MENU_KEY, meniu);
        intent.putExtra(ProduseActivity.MENU_ID, position);
        startActivity(intent);


    }

}
