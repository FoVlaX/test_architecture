package com.example.pagerlistapp.models;

import java.io.Serializable;



public class WorksResponse implements Serializable {
    private DataWorks data;

    public DataWorks getData() {
        return data;
    }

    public void setData(DataWorks data) {
        this.data = data;
    }
}
