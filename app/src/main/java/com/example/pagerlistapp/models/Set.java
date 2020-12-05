package com.example.pagerlistapp.models;

import java.io.Serializable;



public class Set  implements Serializable {
    private float set_id;
    private float user_id;
    private String uri_owner;
    private String type;
    private float date;

    public float getSet_id() {
        return set_id;
    }

    public float getUser_id() {
        return user_id;
    }

    public String getUri_owner() {
        return uri_owner;
    }

    public String getType() {
        return type;
    }

    public float getDate() {
        return date;
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

    public float getCover_work_id() {
        return cover_work_id;
    }

    public CountersSet getCounters() {
        return counters;
    }

    public Flags getFlags() {
        return flags;
    }

    public String get_extended() {
        return _extended;
    }

    private String name;
    private String description;
    private String uri;
    private float cover_work_id;
    CountersSet counters;
    Flags flags;
    private String _extended;
}
