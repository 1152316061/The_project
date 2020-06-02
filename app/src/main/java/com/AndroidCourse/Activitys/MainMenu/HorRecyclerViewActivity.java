package com.AndroidCourse.Activitys.MainMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.AndroidCourse.R;

import java.util.ArrayList;
import java.util.List;

public class HorRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRvHor;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_recycler_view);

        list=new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add("一二是"+i);
        }
        mRvHor=findViewById(R.id.rv_hor);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(HorRecyclerViewActivity.this);
        mRvHor.setLayoutManager(linearLayoutManager);
        mRvHor.setAdapter(new HorAdapter(HorRecyclerViewActivity.this,list));
    }
}
