package com.example.proiectrestaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static List<Meniu> getMeniuri(){
        Meniu[] meniuri = new Meniu[4];
        ArrayList<Produs> prod = getProduse();
        prod.remove(3);
        meniuri[0]=new Meniu("Primul",22.5,1,R.drawable.aripioare_picante, prod);
        prod = getProduse();
        prod.remove(prod.size()-1);
        prod.remove(prod.size()-1);
        meniuri[1] = new Meniu("Al doilea",20.5,1,R.drawable.meniu_maxi, prod);
        prod = getProduse();
        prod.remove(1);
        prod.remove(1);
        prod.remove(1);
        meniuri[2] = new Meniu("Al treilea",20.5,1,R.drawable.mc_chicken, prod);
        prod = getProduse();
        prod.remove(2);
        prod.remove(2);
        meniuri[3] = new Meniu("Al 4-lea",20.5,1,R.drawable.omleta_campionilor, prod);

        return Arrays.asList(meniuri);
    }
    public static ArrayList<Produs> getProduse(){
        ArrayList<Produs> produse = new ArrayList<Produs>(5);
        Produs p = new Produs("Cartofi prajiti",500,2,"g");
        produse.add(p);
        p = new Produs("Coca Cola",500,1,"ml");
        produse.add(p);
        p = new Produs("Aripioare picante",400,6,"g");
        produse.add(p);
        p = new Produs("Burger",500,1,"g");
        produse.add(p);
        p = new Produs("Oua",50,4,"g");
        produse.add(p);
        return produse;

    }
}
