package com.example.pagerlistapp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlagsArtist implements Serializable {
    private String is_liked;
    private String is_can_like;

    public String getIs_liked() {
        return is_liked;
    }

    public String getIs_can_like() {
        return is_can_like;
    }

    public String getIs_can_comment() {
        return is_can_comment;
    }

    public String getIs_can_edit() {
        return is_can_edit;
    }

    private String is_can_comment;
    private String is_can_edit;
}
