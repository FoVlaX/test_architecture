package com.example.pagerlistapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pagerlistapp.R;


public class LoadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int itemCount = 1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_bar, parent, false);
        return new LoadHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position)
    {
        return R.layout.load_bar;
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public class LoadHolder extends RecyclerView.ViewHolder{

        public LoadHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
