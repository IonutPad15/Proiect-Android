package com.example.proiectrestaurant.utils;

import com.example.proiectrestaurant.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static List<Meniu> getMeniuri(){
        Meniu[] meniuri = new Meniu[4];
        ArrayList<Produs> prod = getProduse();
        ArrayList<Produs> produse = new ArrayList<Produs>();
        produse.add(prod.get(0));
        produse.add(prod.get(3));
        produse.add(prod.get(4));
        meniuri[0]=new Meniu("Aripioare picante",14.5, R.drawable.aripioare_picante, produse);
        ArrayList<Produs> produse1 = new ArrayList<Produs>();
        produse1.add(prod.get(0));
        produse1.add(prod.get(1));
        produse1.add(prod.get(2));
        meniuri[1] = new Meniu("Meniu Maxi",16.5,R.drawable.meniu_maxi, produse1);
        ArrayList<Produs> produse2 = new ArrayList<Produs>();
        produse2.add(prod.get(0));
        produse2.add(prod.get(1));
        produse2.add(prod.get(5));
        meniuri[2] = new Meniu("Meniu McChicken",14.5,R.drawable.mc_chicken, produse2);
        ArrayList<Produs> produse3 = new ArrayList<Produs>();
        produse3.add(prod.get(6));
        produse3.add(prod.get(7));
        produse3.add(prod.get(8));
        produse3.add(prod.get(9));
        meniuri[3] = new Meniu("Omleta campionilor",13.5,R.drawable.omleta_campionilor, produse3);

        return Arrays.asList(meniuri);
    }
    public static ArrayList<Produs> getProduse(){
        ArrayList<Produs> produse = new ArrayList<Produs>(10);
        Produs p = new Produs("Cartofi prajiti",500,1,"g");
        produse.add(p);
        p = new Produs("Coca Cola",500,1,"ml");
        produse.add(p);
        p = new Produs("Burger Maxi King",350,1,"g");
        produse.add(p);
        p = new Produs("Aripioare picante",400,6,"g");
        produse.add(p);
        p = new Produs("Ketchup picant",50,1,"ml");
        produse.add(p);
        p = new Produs("Burgen Mc Chicken",350,1,"g");
        produse.add(p);
        p = new Produs("Oua",50,4,"g");
        produse.add(p);
        p = new Produs("Sunca",250,1,"g");
        produse.add(p);
        p = new Produs("Rosii",50,2,"g");
        produse.add(p);
        p = new Produs("Ardei picanti",30,4,"g");
        produse.add(p);
        return produse;

    }
    public static BufferedReader getReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static PrintWriter getWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }
}
