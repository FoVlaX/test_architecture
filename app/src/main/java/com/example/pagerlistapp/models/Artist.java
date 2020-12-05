package com.example.pagerlistapp.models;


import java.io.Serializable;

public class Artist implements Serializable {
    private float artist_id;
    private float user_id;
    private float media_id;
    private float bgpic_id;
    private String sex;
    private String owner_type;
    private float date_add;
    private float author_id;
    private String dt_birth;
    private float place_birth_region_id;
    private String dt_death;
    private float place_death_region_id;
    private float work_region_id;
    private String is_order;
    private String status;
    private String is_top;
    private String is_pro;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String uri;
    private String bgpic_default;
    private String resources = null;
    CountersArtist counters;
    FlagsArtist flags;
    private String _extended;

    public float getArtist_id() {
        return artist_id;
    }

    public float getUser_id() {
        return user_id;
    }

    public float getMedia_id() {
        return media_id;
    }

    public float getBgpic_id() {
        return bgpic_id;
    }

    public String getSex() {
        return sex;
    }

    public String getOwner_type() {
        return owner_type;
    }

    public float getDate_add() {
        return date_add;
    }

    public float getAuthor_id() {
        return author_id;
    }

    public String getDt_birth() {
        return dt_birth;
    }

    public float getPlace_birth_region_id() {
        return place_birth_region_id;
    }

    public String getDt_death() {
        return dt_death;
    }

    public float getPlace_death_region_id() {
        return place_death_region_id;
    }

    public float getWork_region_id() {
        return work_region_id;
    }

    public String getIs_order() {
        return is_order;
    }

    public String getStatus() {
        return status;
    }

    public String getIs_top() {
        return is_top;
    }

    public String getIs_pro() {
        return is_pro;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUri() {
        return uri;
    }

    public String getBgpic_default() {
        return bgpic_default;
    }

    public String getResources() {
        return resources;
    }

    public CountersArtist getCounters() {
        return counters;
    }

    public FlagsArtist getFlags() {
        return flags;
    }

    public String get_extended() {
        return _extended;
    }
}
