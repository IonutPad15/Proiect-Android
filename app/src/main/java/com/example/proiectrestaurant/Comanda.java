package com.example.proiectrestaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comanda {
    private HashMap<Integer, Integer> menumap;
    private static Comanda _instance;
    private Comanda(){
        menumap = new HashMap<Integer,Integer>();
        for(int i=0; i<Helper.getMeniuri().size();++i){
            menumap.put(i,0);
        }
    }
    public static Comanda getInstance(){
        if(_instance == null){
            _instance = new Comanda();
        }
        return _instance;
    }
    public void setMenuCount(int position, int value){
        menumap.put(position, value);
    }
    public int getMenuCount(int position){
        return menumap.get(position);
    }
}
