package com.example.proiectrestaurant;

public interface Action<T> {
    void perform(T args);
}
