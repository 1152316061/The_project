package com.AndroidCourse.Activitys.ShowMedActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AndroidCourse.POJO.Medicine;
import com.AndroidCourse.R;

import java.util.List;

public class MedAdaptor extends RecyclerView.Adapter<MedAdaptor.MedViewHolder> {

    private List<Medicine> list;
    private Context context;

    public MedAdaptor(List<Medicine> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //绑定布局文件
        return new MedViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.componet_med_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedViewHolder holder, int position) {
        Medicine medicine = list.get(position);
        holder.textView.setText(medicine.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshList(List<Medicine> list){
        this.list = list;
    }


    //以下为内部类
    class MedViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MedViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.medINF);
        }
    }
}

