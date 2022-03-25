package com.example.proiectrestaurant.utils;

import java.util.HashMap;

public class Comanda {
    private HashMap<Integer, Integer> menumap;
    private static Comanda _instance;
    private Comanda(){
        menumap = new HashMap<Integer,Integer>();
        for(int i = 0; i< Helper.getMeniuri().size(); ++i){
            menumap.put(i,0);
        }
    }
    public static Comanda getInstance(){
        if(_instance == null){
            _instance = new Comanda();
        }
        return _instance;
    }

    public HashMap<Integer, Integer> getMenumap() {
        return menumap;
    }

    public void setMenumap(HashMap<Integer, Integer> menumap) {
        this.menumap = menumap;
    }

    public void setMenuCount(int position, int value){
        menumap.put(position, value);
    }
    public int getMenuCount(int position){
        return menumap.get(position);
    }
}
