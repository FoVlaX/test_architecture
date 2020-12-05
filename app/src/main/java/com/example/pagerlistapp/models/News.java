package com.example.pagerlistapp.models;

import java.io.Serializable;


public class News implements Serializable {
    private String newsArtist;
    private String newsPost;

    public String getNewsArtist() {
        return newsArtist;
    }

    public String getNewsPost() {
        return newsPost;
    }

    public String getNewsImage() {
        return newsImage;
    }

    private String newsImage;
}
