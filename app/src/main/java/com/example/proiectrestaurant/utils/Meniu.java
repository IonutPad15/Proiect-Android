package com.example.proiectrestaurant.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

//Parcelable is a way to package objects
public class Meniu implements Parcelable {
    private String nume;
    private double pret;
    private int img;
    private ArrayList<Produs> produse;
    //Image img;
    public Meniu(String nume, double pret, int img,ArrayList<Produs> produse) {
        this.nume = nume;
        this.pret = pret;
        this.img = img;
        this.produse = produse;
    }

    protected Meniu(Parcel in) {
        nume = in.readString();
        pret = in.readDouble();
        img = in.readInt();
        produse = in.createTypedArrayList(Produs.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeDouble(pret);
        dest.writeInt(img);
        dest.writeTypedList(produse);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Meniu> CREATOR = new Creator<Meniu>() {
        @Override
        public Meniu createFromParcel(Parcel in) {
            return new Meniu(in);
        }

        @Override
        public Meniu[] newArray(int size) {
            return new Meniu[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }


    public int getImg() {
        return img;
    }

    public ArrayList<Produs> getProduse() {
        return produse;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setProduse(ArrayList<Produs> produse) {
        this.produse = produse;
    }

    @Override
    public String toString() {
        return "Meniu{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                '}';
    }
}
