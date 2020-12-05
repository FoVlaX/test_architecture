package com.example.pagerlistapp.models;


import java.io.Serializable;



public class Technique implements Serializable {
    private String technique_id;
    private String media_id;

    public String getTechnique_id() {
        return technique_id;
    }

    public String getMedia_id() {
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

    private String name;
    private String description;
    private String uri;
    private String _extended;
}
