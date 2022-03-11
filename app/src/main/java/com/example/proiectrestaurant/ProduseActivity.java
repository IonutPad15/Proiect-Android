package com.example.proiectrestaurant;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    public final static String MENU_ID = "meniuid";
    Meniu meniu;
    Dialog popup;
    int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popup= new Dialog(this);
        if(getIntent().hasExtra(MENU_KEY)) {
            meniu = getIntent().getParcelableExtra(MENU_KEY);
            position = getIntent().getIntExtra(MENU_ID,0);
            Log.d("Meniu",position+"");
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
    public void ShowPopUp(View view){
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
                if(cantitate[0]>0)
                    cantitate[0]--;
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
}
