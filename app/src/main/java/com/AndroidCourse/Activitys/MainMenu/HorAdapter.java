package com.AndroidCourse.Activitys.MainMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AndroidCourse.R;

import java.util.List;

public class HorAdapter extends RecyclerView.Adapter<HorAdapter.LinearViewHolder> {

    private Context mContext;
    private List<String> context;

    public HorAdapter(Context context,List<String> list) {
        this.mContext = context;
        this.context=list;
    }

    @NonNull
    @Override
    public HorAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorAdapter.LinearViewHolder holder, int position) {

        holder.textView.setText(context.get(position));
    }

    @Override
    public int getItemCount() {
        return context.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_1);
            textView = itemView.findViewById(R.id.tv_1);
        }
    }
}
