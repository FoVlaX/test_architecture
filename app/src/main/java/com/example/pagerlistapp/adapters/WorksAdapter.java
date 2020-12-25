package com.example.pagerlistapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pagerlistapp.InfoActivity;
import com.example.pagerlistapp.R;
import com.example.pagerlistapp.models.Media;
import com.example.pagerlistapp.models.Work;
import com.example.pagerlistapp.util.ViewUtilKt;


public class WorksAdapter extends PagedListAdapter<Work, RecyclerView.ViewHolder> {

    private int lastPosition = -1;


    public WorksAdapter() {
        super(DIFF_CALLBACK);
    }

    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_work_item,parent,false);
        return new WorkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // только в этом методе необходимо заполнять холдеры нигде больше!!!
        if (holder instanceof WorkHolder) {
            Work item = getItem(position);
            if (item !=null) {
                ((WorkHolder) holder).name.setText(item.getName());
                String likes = String.valueOf(item.getWork_id());
                ((WorkHolder) holder).count_likes.setText(likes);
                               // Media media = получаешь здесь этот объект что привязан к работе
                // Int size = получаешь размер в ПИКСЕЛЯХ, в нашем случае вертикальный размер изображения
                // String placeholder = здесь плейсхолдер, т.е. цвет из Media

                ((WorkHolder) holder).itemView.setOnClickListener((v)->{
                    Intent intent = new Intent(context, InfoActivity.class);
                    context.startActivity(intent);
                });

                try {
                    Media media = item.getMedia();
                    ViewUtilKt.showMedia(((WorkHolder) holder).image_view,
                            media,
                            Media.MediaRatio.s,
                            Media.MediaSide.y,
                            ((WorkHolder) holder).image_size,
                            "#" + item.getColors().getMiddle());
                }catch (Exception ex){

                }
            }
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        return R.layout.view_work_item;
    }

    public static class WorkHolder extends RecyclerView.ViewHolder {
        View item;
        AppCompatImageView image_view;
        TextView name;
        TextView count_likes;
        Integer image_size;
        public WorkHolder(View view) {
            super(view);
            image_size = (int) view.getResources().getDimension(R.dimen.medium_image_size);
            item = view.findViewById(R.id.item);
            image_view = view.findViewById(R.id.work_image);
            name = view.findViewById(R.id.work_name);
            count_likes = view.findViewById(R.id.work_count_likes);
        }
    }

    private static DiffUtil.ItemCallback<Work> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Work>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Work oldWork, Work newWork) {
                    return oldWork.getWork_id()
                            .compareTo(newWork.getWork_id())==0;
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Work oldWork,
                                                  Work newWork) {
                    return oldWork.getWork_id()
                            .compareTo(newWork.getWork_id())==0;
                }
            };

}
