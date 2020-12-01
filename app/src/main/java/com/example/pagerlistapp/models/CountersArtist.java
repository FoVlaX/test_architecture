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
public class CountersArtist implements Serializable {
    private float works_like;
    private float users_collections;
    private float collections;
    private float users_selections;
    private float selections;
    private float artist_sets;

    public float getWorks_like() {
        return works_like;
    }

    public float getUsers_collections() {
        return users_collections;
    }

    public float getCollections() {
        return collections;
    }

    public float getUsers_selections() {
        return users_selections;
    }

    public float getSelections() {
        return selections;
    }

    public float getArtist_sets() {
        return artist_sets;
    }

    public float getHistories() {
        return histories;
    }

    public float getReal_expositions() {
        return real_expositions;
    }

    public float getLikes() {
        return likes;
    }

    public float getComments() {
        return comments;
    }

    private float histories;
    private float real_expositions;
    private float likes;
    private float comments;
}
