package com.example.proiectrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;

public class ComandaActivity extends AppCompatActivity {
    public final static String CHITANTA_KEY = "chitanta";
    private EditText email;
    private Button btnConfirma;
    private  String chitanta;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comanda_activity);
        chitanta = getIntent().getStringExtra(CHITANTA_KEY);
        Log.d("Comanda",chitanta);
        email = findViewById(R.id.mailtxt);
        btnConfirma =findViewById(R.id.btnConfirma);
        btnConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senEmail();
                Empty();
                Intent intent = new Intent(ComandaActivity.this, MeniuriActivity.class);
                startActivity(intent);
                ComandaActivity.this.finish();
            }
        });

    }
    private void Empty(){
        Comanda comanda = Comanda.getInstance();
        HashMap<Integer, Integer> map = comanda.getMenumap();
        map.replaceAll((k,v) ->v=0);
        CosActivity.pretTotal=0;
    }
    private void senEmail() {
        String mEmail = email.getText().toString();
        String mSubject = "TheForestMan";
        String mMessage = chitanta;


        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, mSubject, mMessage);

        javaMailAPI.execute();
    }
}
