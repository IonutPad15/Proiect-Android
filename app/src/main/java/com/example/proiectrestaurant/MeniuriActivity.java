package com.example.proiectrestaurant;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MeniuriActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.meniuri_activity);
        //RecyclerView recyclerView = findViewById(R.id.meniuriRecycleView);
        setContentView(R.layout.recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //LayoutManager: modalitatea in care sunt afisate pe ecran
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //false ca sa fie modul normal, de sus in jos. Daca era true, era de jos in sus
        //Adapter- modalitatea prin care ii spunem lui RecyclerView ce sa afiseze pe ecan
        recyclerView.setAdapter(new MeniuriAdapter(Helper.getMeniuri(), new Action<String>(){

            @Override
            public void perform(String args) {
                Toast.makeText(MeniuriActivity.this,args,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
