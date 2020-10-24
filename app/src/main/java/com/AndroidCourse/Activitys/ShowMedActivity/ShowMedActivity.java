package com.AndroidCourse.Activitys.ShowMedActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.AndroidCourse.POJO.Medicine;
import com.AndroidCourse.R;
import com.AndroidCourse.Utils.DB.MedicineDBA;

import java.util.ArrayList;
import java.util.List;

public class ShowMedActivity extends AppCompatActivity {

    private List<Medicine> list;
    private SwipeRefreshLayout sw;
    private MedAdaptor medAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_med);

        sw = findViewById(R.id.sw2);
        RecyclerView rv  = findViewById(R.id.rv_med);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        medAdaptor = new MedAdaptor(list,this);
        rv.setAdapter(medAdaptor);

        sw.setOnRefreshListener(()->{
            list = MedicineDBA.getMedicine(this);
            medAdaptor.refreshList(list);
            medAdaptor.notifyDataSetChanged();
            sw.setRefreshing(false);
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sw.post(() -> {
            sw.setRefreshing(true);
            list = MedicineDBA.getMedicine(this);
            medAdaptor.refreshList(list);
            medAdaptor.notifyDataSetChanged();
            sw.setRefreshing(false);
        });
    }
}