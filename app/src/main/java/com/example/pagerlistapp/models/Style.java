package com.example.pagerlistapp.models;

import java.io.Serializable;


public class Style implements Serializable {
    private float style_id;
    private float parent_id;
    private float media_id;

    public float getStyle_id() {
        return style_id;
    }

    public float getParent_id() {
        return parent_id;
    }

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

    private String name;
    private String description;
    private String uri;
    private String _extended;
}
