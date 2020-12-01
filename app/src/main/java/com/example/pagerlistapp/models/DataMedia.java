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
public class DataMedia implements Serializable {
    private String version;
    private String version_big;
    private String version_orig;
    Sizes sizes;

    public String getVersion() {
        return version;
    }

    public String getVersion_big() {
        return version_big;
    }

    public String getVersion_orig() {
        return version_orig;
    }

    public Sizes getSizes() {
        return sizes;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRatio() {
        return ratio;
    }

    public String getExt() {
        return ext;
    }

    public boolean isIs_animated() {
        return is_animated;
    }

    private float x;
    private float y;
    private float ratio;
    private String ext;
    private boolean is_animated;
}
