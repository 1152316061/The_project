package com.AndroidCourse.Activitys.MainMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AndroidCourse.POJO.Goods;
import com.AndroidCourse.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HorAdapter extends RecyclerView.Adapter<HorAdapter.LinearViewHolder> {

    private Context mContext;
    public List<Goods> context;
    private int Tag = 0;
    private OnItemButtonClickListener onItemButtonClickListener;

    public HorAdapter(Context context,List<Goods> list) {
        this.mContext = context;
        this.context=list;
    }
    public HorAdapter(Context context,List<Goods> list,int tag) {
        this.mContext = context;
        this.context=list;
        this.Tag = tag;
    }

    @NonNull
    @Override
    public HorAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorAdapter.LinearViewHolder holder, int position) {
        Goods g = context.get(position);
        Picasso picasso = Picasso.get();
        picasso.load(g.getImgUrl()).into(holder.imageView);
        holder.textView.setText(g.getName()+"\t"+g.getPrice()+"元");
        holder.button.setOnClickListener((v)->{
            onItemButtonClickListener.onClick(v,position);
        });
        if(Tag == 1) holder.button.setText("移除");

    }
    public void setOnItemButtonClickListener(OnItemButtonClickListener onItemButtonClickListener){
        this.onItemButtonClickListener = onItemButtonClickListener;
    }
    public void remove(int pos){
        context.remove(pos);
    }
    public void refreshList(List<Goods> list){
        this.context = list;
    }

    @Override
    public int getItemCount() {
        return context.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private Button button;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_1);
            textView = itemView.findViewById(R.id.tv_1);
            button = itemView.findViewById(R.id.b1);
        }
    }

    //监听器内部接口
    public interface OnItemButtonClickListener{
        void onClick(View view,int position);
    }
}
