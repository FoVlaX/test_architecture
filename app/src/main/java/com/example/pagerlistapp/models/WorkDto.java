package com.example.pagerlistapp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkDto implements Serializable {
    private String work_id;
    private String name;
    private String description;
    private Integer count_likes;
}
