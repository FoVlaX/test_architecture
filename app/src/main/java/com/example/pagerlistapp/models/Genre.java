package com.example.pagerlistapp.models;

import java.io.Serializable;




public class Genre implements Serializable {
    private float genre_id;
    private float media_id;
    private String name;
    private String description;
    private String uri;
    private String _extended;

    public float getMedia_id() {
        return media_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUri() {
        return uri;
    }

    public String get_extended() {
        return _extended;
    }

    public float getGenre_id(){
        return this.genre_id;
    }
}
