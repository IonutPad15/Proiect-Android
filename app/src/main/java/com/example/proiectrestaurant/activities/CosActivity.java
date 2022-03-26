package com.example.proiectrestaurant.activities;

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

import com.example.proiectrestaurant.adapters.CosAdapter;
import com.example.proiectrestaurant.utils.Helper;
import com.example.proiectrestaurant.utils.Meniu;
import com.example.proiectrestaurant.utils.OnMenuListener;
import com.example.proiectrestaurant.R;

import java.util.List;

public class CosActivity extends AppCompatActivity implements OnMenuListener {
    public static double pretTotal=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cos_activity);
        View cosview= findViewById(R.id.cos_activity);
        cosview.getBackground().setAlpha(120);
        Button btnComanda = findViewById(R.id.btnComanda);
        RecyclerView recyclerView = findViewById(R.id.meniuriRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //false ca sa fie modul normal, de sus in jos. Daca era true, era de jos in sus
        //Adapter- modalitatea prin care ii spunem lui RecyclerView ce sa afiseze pe ecan
        recyclerView.setAdapter(new CosAdapter((Helper.getMeniuri()),this));
    }

    public void onMenuClick(int position) {
        Intent intent = new Intent(this, ProduseActivity.class);
        List<Meniu> meniuri = Helper.getMeniuri();
        Meniu meniu = meniuri.get(position);
        intent.putExtra(ProduseActivity.PRODUSE_KEY,meniu.getProduse());
        intent.putExtra(ProduseActivity.MENU_KEY,meniu);
        intent.putExtra(ProduseActivity.MENU_ID,position);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.cos_activity);
        RecyclerView recyclerView = findViewById(R.id.meniuriRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //false ca sa fie modul normal, de sus in jos. Daca era true, era de jos in sus
        //Adapter- modalitatea prin care ii spunem lui RecyclerView ce sa afiseze pe ecan
        recyclerView.setAdapter(new CosAdapter((Helper.getMeniuri()),this));

        if(recyclerView.getAdapter().getItemViewType(0)==View.GONE){
            TextView cos_gol = findViewById(R.id.cos_gol);
            Log.d("Cos gold","cos god");
            cos_gol.setVisibility(View.VISIBLE);
        }
    }
}
