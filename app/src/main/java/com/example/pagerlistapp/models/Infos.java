package com.example.pagerlistapp.models;

import java.io.Serializable;




public class Infos implements Serializable {
    private String owner_name;
    private String collection_name;
    private String exposition_id;
    private String exposition_name;
    private String exposition_region_name;

    public String getOwner_name() {
        return owner_name;
    }

    public String getCollection_name() {
        return collection_name;
    }

    public String getExposition_id() {
        return exposition_id;
    }

    public String getExposition_name() {
        return exposition_name;
    }

    public String getExposition_region_name() {
        return exposition_region_name;
    }

    public String getExposition_date_start() {
        return exposition_date_start;
    }

    public String getExposition_date_end() {
        return exposition_date_end;
    }

    public int getCount_expositions() {
        return count_expositions;
    }

    public int getAset_id() {
        return aset_id;
    }

    public String getAset_name() {
        return aset_name;
    }

    private String exposition_date_start;
    private String exposition_date_end;
    private int count_expositions;
    private int aset_id;
    private String aset_name;


    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public void setCollection_name(String collection_name) {
        this.collection_name = collection_name;
    }

    public void setExposition_id(String exposition_id) {
        this.exposition_id = exposition_id;
    }

    public void setExposition_name(String exposition_name) {
        this.exposition_name = exposition_name;
    }

    public void setExposition_region_name(String exposition_region_name) {
        this.exposition_region_name = exposition_region_name;
    }

    public void setExposition_date_start(String exposition_date_start) {
        this.exposition_date_start = exposition_date_start;
    }

    public void setExposition_date_end(String exposition_date_end) {
        this.exposition_date_end = exposition_date_end;
    }

    public void setCount_expositions(int count_expositions) {
        this.count_expositions = count_expositions;
    }

    public void setAset_id(int aset_id) {
        this.aset_id = aset_id;
    }

    public void setAset_name(String aset_name) {
        this.aset_name = aset_name;
    }
}