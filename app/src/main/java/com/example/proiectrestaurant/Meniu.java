package com.example.proiectrestaurant;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

//Parcelable is a way to package objects
public class Meniu implements Parcelable {
    String nume;
    double pret;
    int idproduse;
    int img;
    ArrayList<Produs> produse;
    //Image img;
    public Meniu(String nume, double pret, int idproduse, int img,ArrayList<Produs> produse) {
        this.nume = nume;
        this.pret = pret;
        this.idproduse = idproduse;
        this.img = img;
        this.produse = produse;
    }

    protected Meniu(Parcel in) {
        nume = in.readString();
        pret = in.readDouble();
        idproduse = in.readInt();
        img = in.readInt();
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

    public int getIdproduse() {
        return idproduse;
    }

    public int getImg() {
        return img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nume);
        parcel.writeDouble(pret);
        parcel.writeInt(idproduse);
        parcel.writeInt(img);
    }

    @Override
    public String toString() {
        return "Meniu{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                '}';
    }
}
