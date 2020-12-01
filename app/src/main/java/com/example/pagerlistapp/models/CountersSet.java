package com.example.pagerlistapp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CountersSet implements Serializable  {
    private float works;

    public float getWorks() {
        return works;
    }

    public float getLikes() {
        return likes;
    }

    public float getComments() {
        return comments;
    }

    private float likes;
    private float comments;
}
