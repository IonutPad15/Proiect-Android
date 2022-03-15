package com.example.proiectrestaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

public class Chitanta {
    public static String generareChitanta(){
            StringJoiner rez = new StringJoiner("");
            rez.add("         The Forest Man         \n   Str 1.Decembrie 1918, Nr.15  \n");
            rez.add("    Ludus / Oras Ludus/ Mures  \n             VANZARE\n====================================\n");
            rez.add("DATA             ");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            rez.add(dtf.format(now) + "\n");
            List<Meniu> meniuri = Helper.getMeniuri();
            Comanda comanda = Comanda.getInstance();
            double total = CosActivity.pretTotal;
            for (int i = 0; i < meniuri.size(); ++i) {
                if (comanda.getMenuCount(i) != 0) {
                    rez.add(comanda.getMenuCount(i) + "x" + meniuri.get(i).getNume());
                    rez.add("          Pret: " + comanda.getMenuCount(i) * meniuri.get(i).getPret() + " lei\n");
                }
            }
            rez.add("Total de plata:            " + total + " lei\n");
            return rez.toString();
    }
}
