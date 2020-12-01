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
public class WorksResponse implements Serializable {
    private DataWorks data;

    public DataWorks getData() {
        return data;
    }

    public void setData(DataWorks data) {
        this.data = data;
    }
}
