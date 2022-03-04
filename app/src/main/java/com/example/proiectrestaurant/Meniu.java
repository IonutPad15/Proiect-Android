package com.example.proiectrestaurant;

import android.media.Image;

public class Meniu {
    String nume;
    double pret;
    int idproduse;
    //Image img;
    public Meniu(String nume, double pret, int idproduse) {
        this.nume = nume;
        this.pret = pret;
        this.idproduse = idproduse;
    }

    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }

    public int getIdproduse() {
        return idproduse;
    }
}
