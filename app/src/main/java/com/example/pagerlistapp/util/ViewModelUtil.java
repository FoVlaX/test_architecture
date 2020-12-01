package com.example.pagerlistapp.util;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;

import java.util.HashMap;

public class ViewModelUtil {

    private static ViewModelUtil viewModelUtil;

    private HashMap<String, ComponentActivity> activities;

    private ViewModelUtil(){
        activities = new HashMap<>();
    }

    public void registerActivity(@NonNull String key, @NonNull ComponentActivity activity){
        activities.put(key, activity);
    }

    public ComponentActivity getActivity(String key){
        ComponentActivity activity;
        try{
            activity = activities.get(key);
        }catch (Exception ex){
            activity = new ComponentActivity();
        }
        return activity;
    }


    public static ViewModelUtil getViewModelUtil() {
        if (viewModelUtil == null){
            viewModelUtil = new ViewModelUtil();
        }
        return viewModelUtil;
    }
}
