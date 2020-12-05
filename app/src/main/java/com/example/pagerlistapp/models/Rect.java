package com.example.pagerlistapp.models;

import java.io.Serializable;


public class Rect implements Serializable {
    private int x;
    private int y;
    private int width;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int height;
}
