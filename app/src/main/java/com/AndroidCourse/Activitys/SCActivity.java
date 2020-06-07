package com.AndroidCourse.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.AndroidCourse.Activitys.MainMenu.HorAdapter;
import com.AndroidCourse.POJO.Goods;
import com.AndroidCourse.R;
import com.AndroidCourse.Utils.DB.OLDBA;
import com.AndroidCourse.Utils.Net.HttpRequest;
import com.AndroidCourse.Utils.Net.RequestCallAble;
import com.alibaba.fastjson.JSON;
import com.xq.fasterdialog.dialog.NormalDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SCActivity extends AppCompatActivity {


    RecyclerView mRvHor;
    List<Goods> list;
    HorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_c);

        list = OLDBA.getOL(this);
        mRvHor=findViewById(R.id.rv_hor);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRvHor.setLayoutManager(linearLayoutManager);
        adapter = new HorAdapter(this, list,1);
        adapter.setOnItemButtonClickListener((view, position) -> {
            System.out.println(position+"$$$232323$$");
            delOl(list.get(position),position);

        });
        mRvHor.setAdapter(adapter);

        Button button = findViewById(R.id.jiesuan);

        button.setOnClickListener(v->{
            double sum = 0;
            for (int i = 0;i<list.size();i++){
                sum+=list.get(i).getPrice();
            }
            NormalDialog dialog = new NormalDialog(this);
            dialog.setContent("总价: "+sum).setNeutralText("确定").show();
        });

    }
    private List<Goods> getAllGoods(){
        List<Goods> list = null;
        try {
            String objs = new RequestCallAble(new HashMap<String, String>(), HttpRequest._Goods.getURL(),"").commit();
            System.out.println("getDONE");
            list = JSON.parseArray(objs,Goods.class);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("out");
        return list;
    }
    private void delOl(Goods g,int position){
        NormalDialog dialog = new NormalDialog(this);
        dialog.setContent("是否删除?").setPositiveText("是").setNegativeText("否").setPositiveListener(new BaseDialog.OnDialogClickListener() {
            @Override
            public void onClick(BaseDialog dialog) {
                System.out.println("in del");
                OLDBA.delOL(g,SCActivity.this);
                adapter.remove(position);
                list=adapter.context;
                adapter.notifyDataSetChanged();
            }
        }).show();

    }
}
