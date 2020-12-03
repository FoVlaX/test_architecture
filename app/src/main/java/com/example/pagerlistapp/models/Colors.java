package com.example.pagerlistapp.models;

import java.io.Serializable;
import java.util.List;

import androidx.room.Ignore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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