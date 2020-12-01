package com.example.pagerlistapp.models;


import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataWorks implements Serializable {
    private float count;
    List < Work > works;
    List < Media > media;
    List < Set > sets;
    List < Artist > artists;
    List < Type > types;

    public float getCount() {
        return count;
    }

    public List<Work> getWorks() {
        return works;
    }

    public List<Media> getMedia() {
        return media;
    }

    public List<Set> getSets() {
        return sets;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public List<Type> getTypes() {
        return types;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<Technique> getTechniques() {
        return techniques;
    }

    public List<Tag> getTags() {
        return tags;
    }

    List < Style > styles;
    List < Genre > genres;
    List < Material > materials;
    List <Technique> techniques;
    List < Tag > tags;
}
