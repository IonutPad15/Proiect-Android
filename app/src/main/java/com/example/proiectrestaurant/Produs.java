package com.example.proiectrestaurant;

import android.os.Parcel;
import android.os.Parcelable;

public class Produs implements Parcelable {
    String _nume;
    int _gramaj;
    int _cantitate;
    String unitate_masura;

    public Produs(String _nume, int _gramaj, int _cantitate, String unitate_masura) {
        this._nume = _nume;
        this._gramaj = _gramaj;
        this._cantitate = _cantitate;
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
