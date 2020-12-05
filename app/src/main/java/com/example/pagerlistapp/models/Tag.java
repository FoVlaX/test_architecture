package com.example.pagerlistapp.models;

import java.io.Serializable;



public class Tag implements Serializable {
    private float tag_id;
    private String name;
    private String uri;

    public float getTag_id() {
        return tag_id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public String get_extended() {
        return _extended;
    }

    private String _extended;
}
