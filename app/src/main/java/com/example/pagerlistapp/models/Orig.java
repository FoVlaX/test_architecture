package com.example.pagerlistapp.models;

import java.io.Serializable;


public class Orig implements Serializable {
    private float x;
    private float y;
    private String hash_file_name;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getHash_file_name() {
        return hash_file_name;
    }
}
