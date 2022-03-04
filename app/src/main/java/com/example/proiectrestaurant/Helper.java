package com.example.proiectrestaurant;

import java.util.Arrays;
import java.util.List;

public class Helper {
    public static List<Meniu> getMeniuri(){
        Meniu[] meniuri = new Meniu[4];
        meniuri[0]=new Meniu("Primul",22.5,1);
        meniuri[1] = new Meniu("Al doilea",20.5,1);
        meniuri[2] = new Meniu("Al treilea",20.5,1);
        meniuri[3] = new Meniu("Al 4-lea",20.5,1);

        return Arrays.asList(meniuri);
    }
}
