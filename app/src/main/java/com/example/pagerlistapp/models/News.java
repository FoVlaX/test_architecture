package com.example.pagerlistapp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
