package com.example.pagerlistapp.models;

import java.io.Serializable;
import java.util.List;

import androidx.room.Ignore;


public class Colors implements Serializable {
    public String getMiddle() {
        return middle;
    }
    public List<String> getPallete() {
        return pallete;
    }
    private String middle;
    @Ignore
    List<String> pallete;
    public void setMiddle(String middle) {
        this.middle = middle;
    }
    public void setPallete(List<String> pallete) {
        this.pallete = pallete;
    }
}