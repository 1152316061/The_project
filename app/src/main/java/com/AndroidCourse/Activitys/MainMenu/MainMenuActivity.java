package com.AndroidCourse.Activitys.MainMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.AndroidCourse.R;
import com.AndroidCourse.Services.LocationService;
import com.AndroidCourse.Services.RemindService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private ViewPager2 vp;
    private BottomNavigationView bnv;
    private final String[] permissions = {
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.SEND_SMS,
    };
    private List<String> mPermissionList = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_CODE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getPermission();
        startService();

        vp = findViewById(R.id.vp);
        List<Fragment> list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());

        vp.setAdapter(new Adapter(this,list));
        bnv = findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_1:{
                    vp.setCurrentItem(0);
                    break;
                }
                case R.id.menu_2:{
                    vp.setCurrentItem(1);
                    break;
                }
                case R.id.menu_3:{
                    vp.setCurrentItem(3);
                    break;
                }
            }
            return false;
        });
        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bnv.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    private class Adapter extends FragmentStateAdapter {
        private List<Fragment> fragmentList;
        public Adapter(@NonNull FragmentActivity fragmentActivity, List<Fragment>list) {
            super(fragmentActivity);
            this.fragmentList=list;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }

    public void check2(){
        vp.setCurrentItem(1);
        bnv.getMenu().getItem(1).setChecked(true);
    }
    public void getPermission(){
        mPermissionList.clear();                                    //清空已经允许的没有通过的权限
        for (int i = 0; i < permissions.length; i++) {          //逐个判断是否还有未通过的权限
            if (ContextCompat.checkSelfPermission(MainMenuActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }

        if (mPermissionList.size() > 0) {                           //有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, MY_PERMISSIONS_REQUEST_CODE);
        } else {
            Log.e("getPermissions() >>>", "已经授权");     //权限已经都通过了
        }
    }
    private void startService(){
        Intent intent1 = new Intent(this, LocationService.class);
        Intent intent2 = new Intent(this, RemindService.class);
        startService(intent1);
        startService(intent2);
    }
}
