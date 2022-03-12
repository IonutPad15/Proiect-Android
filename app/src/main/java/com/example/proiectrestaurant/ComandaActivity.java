package com.example.proiectrestaurant;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ComandaActivity extends AppCompatActivity {
    public final static String CHITANTA_KEY = "chitanta";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comanda_activity);
        String chitanta = getIntent().getStringExtra(CHITANTA_KEY);
        Log.d("Comanda",chitanta);
    }
}
