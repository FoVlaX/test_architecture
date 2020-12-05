package com.example.pagerlistapp.models;

import java.io.Serializable;




public class Material implements Serializable {
    private float material_id;
    private float media_id;

    public float getMaterial_id() {
        return material_id;
    }

    public float getMedia_id() {
        return media_id;
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

    private String name;
    private String uri;
    private String _extended;
}
