package com.example.pagerlistapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pagerlistapp.R;


public class LoadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int itemCount = 1;
    private ProgressBar progressBar;
    private TextView textView;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_bar, parent, false);
        return new LoadHolder(view);
    }


    public void visibleLoadBar(){
        if (progressBar!=null && textView!=null) {
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
    }

    public void hideLoadBar(Boolean empty){
        if (progressBar!=null && textView!=null){
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);

            if (empty){
                textView.setText("Ничего не найдено");
            }else{
                textView.setText("Данные загруженны");
            }

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadHolder){
            progressBar = ((LoadHolder) holder).progressBar;
            textView = ((LoadHolder) holder).textView;
        }
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

        private ProgressBar progressBar;
        private TextView textView;

        public LoadHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
            textView = itemView.findViewById(R.id.text_loaded);

        }
    }

}
