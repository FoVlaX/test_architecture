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
public class Status implements Serializable {
    private List<Integer> delivery_region_ids;

    public List<Integer> getDelivery_region_ids() {
        return delivery_region_ids;
    }

    public List<String> getDelivery_regions() {
        return delivery_regions;
    }

    private List<String> delivery_regions;
}

