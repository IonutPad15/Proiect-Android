package com.example.proiectrestaurant;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProduseActivity extends AppCompatActivity {
    public final static String MENU_KEY = "meniu";
    public final static String PRODUSE_KEY = "produse";
    Meniu meniu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().hasExtra(MENU_KEY)) {
            meniu = getIntent().getParcelableExtra(MENU_KEY);
            meniu.produse = getIntent().getParcelableArrayListExtra(PRODUSE_KEY);
            Toast.makeText(ProduseActivity.this, meniu.nume + " " + meniu.pret + " " , Toast.LENGTH_SHORT).show();
            Log.d("ProduseActivity",meniu.produse.get(1)._nume+" ");
            setContentView(R.layout.produse_activity);
            ImageView imgView = findViewById(R.id.meniu_image);
            imgView.setImageResource(meniu.img);
            TextView numeMeniuView =findViewById(R.id.nume_meniu);
            numeMeniuView.setText(meniu.nume);
            TextView pretTxtView = findViewById(R.id.pret);
            pretTxtView.setText(meniu.pret+" lei");
            RecyclerView recyclerView = findViewById(R.id.produseRecycleView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(new ProduseAdapter(meniu.produse));
        }
    }
}
