package com.example.proiectrestaurant.utils;

public interface Action<T> {
    void perform(T args);
}
