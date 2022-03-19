package com.example.proiectrestaurant;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

public class ComandaActivity extends AppCompatActivity {
    public final static String CHITANTA_KEY = "chitanta";
    private EditText email;
    private Button btnConfirma;
    private  String chitanta;

    private TextView txtPret;
    private String time;
    private double distance;
    Button btnLocation;
    TextView txt1, txt2, txt3, txt4, txt5;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comanda_activity);

        email = findViewById(R.id.mailtxt);
        txtPret = findViewById(R.id.total);
        txtPret.setText(String.valueOf(CosActivity.pretTotal)+" lei");
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

        btnLocation = findViewById(R.id.bt_location);
        txt1 = findViewById(R.id.text_view1);
        txt2 = findViewById(R.id.text_view2);
        txt3 = findViewById(R.id.text_view3);
        txt4 = findViewById(R.id.text_view4);
        txt5 = findViewById(R.id.text_view5);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(ComandaActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }
                else
                {
                    ActivityCompat.requestPermissions(ComandaActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }

        });

    }
    private void Empty(){
        Comanda comanda = Comanda.getInstance();
        HashMap<Integer, Integer> map = comanda.getMenumap();
        map.replaceAll((k,v) ->v=0);
        CosActivity.pretTotal=0;
        btnConfirma.setEnabled(false);
    }
    private void senEmail() {
        String mEmail = email.getText().toString();
        String mSubject = "TheForestMan";
        String mMessage = chitanta;


        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, mSubject, mMessage);

        javaMailAPI.execute();
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(ComandaActivity.this, Locale.getDefault());
                        List<Address> adresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        txt1.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Latitude :</b></font>"
                                        + adresses.get(0).getLatitude()
                        ));
                        txt2.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Longitude :</b></font>"
                                        + adresses.get(0).getLongitude()
                        ));
                        txt3.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Country Name :</b></font>"
                                        + adresses.get(0).getCountryName()
                        ));
                        txt4.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Locality :</b></font>"
                                        + adresses.get(0).getLocality()
                        ));
                        txt5.setText(Html.fromHtml(
                                "<font color ='#6200EE'><b>Adress :</b></font>"
                                        + adresses.get(0).getAddressLine(0)
                        ));
                        btnConfirma.setEnabled(true);
                        distance = calculateDistance(adresses.get(0).getLatitude(), adresses.get(0).getLongitude());
                        Log.d("Distanta",distance+"");
                        time= calculateTime(distance);
                        Log.d("Timp",time);
                        chitanta = Chitanta.generareChitanta(distance, time);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    private String calculateTime(double distance)
    {
        double speed = 45;//45 km/h
        speed = speed/60;//km/min pentru calculul aproximativ al timpului
        int minutes =(int) (distance/speed);
        Log.d("Distanta din timp:",(int)distance+"");
        Log.d("Distanta din timp:",distance+"");
        Log.d("Distanta/viteza din timp:",distance/speed+"");
        Log.d("Minutele din timp:",minutes+"");
        int hours =0;
        String rez;
        if(minutes>60)
        {
            hours = minutes/60;
            minutes = minutes%60;
            rez= hours+" hours, "+minutes+" minutes";
        }
        else{
            rez= minutes+" minutes";
        }
        return rez;


    }
    private double calculateDistance( Double latitude, Double longitude){
        double resLat = Double.parseDouble(getResources().getString(R.string.latitude));
        Log.d("Aia",String.valueOf(resLat));
        double resLong = Double.parseDouble(getResources().getString(R.string.longitude));
        Log.d("Aia",String.valueOf(resLong));
        double theta = longitude - resLong;
        double dist = Math.sin(deg2rad(latitude)) * Math.sin(deg2rad(resLat)) + Math.cos(deg2rad(latitude)) * Math.cos(deg2rad(resLat)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return (dist);
    }
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public String getTime() {
        return time;
    }

    public double getDistance() {
        return distance;
    }
}
