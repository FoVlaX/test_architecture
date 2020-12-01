package com.example.pagerlistapp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Type implements Serializable  {
    private float type_id;
    private float media_id;
    private float parent_id;
    private String name;

    public float getType_id() {
        return type_id;
    }

    public float getMedia_id() {
        return media_id;
    }

    public float getParent_id() {
        return parent_id;
    }

    public String getName() {
        return name;
    }

    public String getName_object() {
        return name_object;
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

    private String name_object;
    private String description;
    private String uri;
    private String _extended;
}
