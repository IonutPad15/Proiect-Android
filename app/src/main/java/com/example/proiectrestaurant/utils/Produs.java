package com.example.proiectrestaurant.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class Produs implements Parcelable {
    private String _nume;
    private int _gramaj;
    private int _cantitate;
    private String unitate_masura;

    public Produs(String _nume, int _gramaj, int _cantitate, String unitate_masura) {
        this._nume = _nume;
        this._gramaj = _gramaj;
        this._cantitate = _cantitate;
        this.unitate_masura = unitate_masura;
    }

    public String get_nume() {
        return _nume;
    }

    public void set_nume(String _nume) {
        this._nume = _nume;
    }

    public int get_gramaj() {
        return _gramaj;
    }

    public void set_gramaj(int _gramaj) {
        this._gramaj = _gramaj;
    }

    public int get_cantitate() {
        return _cantitate;
    }

    public void set_cantitate(int _cantitate) {
        this._cantitate = _cantitate;
    }

    public String getUnitate_masura() {
        return unitate_masura;
    }

    public void setUnitate_masura(String unitate_masura) {
        this.unitate_masura = unitate_masura;
    }

    protected Produs(Parcel in) {
        _nume = in.readString();
        _gramaj = in.readInt();
        _cantitate = in.readInt();
        unitate_masura = in.readString();
    }

    public static final Creator<Produs> CREATOR = new Creator<Produs>() {
        @Override
        public Produs createFromParcel(Parcel in) {
            return new Produs(in);
        }

        @Override
        public Produs[] newArray(int size) {
            return new Produs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_nume);
        parcel.writeInt(_gramaj);
        parcel.writeInt(_cantitate);
        parcel.writeString(unitate_masura);
    }
}
