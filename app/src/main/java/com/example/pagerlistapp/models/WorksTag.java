package com.example.pagerlistapp.models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorksTag implements Serializable {
    private int tag_id;
    private int date;
    private Rect data;
    private List<Rect> dataList;
}
