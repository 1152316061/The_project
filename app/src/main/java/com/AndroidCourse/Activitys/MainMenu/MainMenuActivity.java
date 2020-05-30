package com.AndroidCourse.Activitys.MainMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.AndroidCourse.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ViewPager2 vp = findViewById(R.id.vp);
        List<Fragment> list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());

        vp.setAdapter(new Adapter(this,list));
        BottomNavigationView bnv = findViewById(R.id.bnv);
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
}
