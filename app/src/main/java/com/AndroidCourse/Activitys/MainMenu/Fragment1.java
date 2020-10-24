package com.AndroidCourse.Activitys.MainMenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AndroidCourse.R;

import java.util.ArrayList;

import static android.content.Context.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    private ImageView Iv1;
    private ImageView Iv2;
    private ImageView Iv3;
    private ImageView Iv4;
    private ImageView Iv5;
    private ImageView Iv6;
    private ImageView Iv7;
    private ImageView Iv8;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        getViews(view);

        Iv1.setOnClickListener(v->{
            Intent intent= new Intent("HIA");
            startActivity(intent);
        });
        Iv2.setOnClickListener(v->{
//            Intent intent= new Intent("addMed");
            Intent intent= new Intent("ShowMed");
            startActivity(intent);
        });
        Iv3.setOnClickListener(v->{
            //发短信
            SharedPreferences sp = getActivity().getSharedPreferences("phone", Context.MODE_PRIVATE);
            String phone = sp.getString("phone","110");
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,"求救", null,null);
            Toast.makeText(getActivity(),"已发送",Toast.LENGTH_SHORT).show();
        });
        Iv4.setOnClickListener(v->{
            Intent intent= new Intent("web");
            intent.putExtra("WEB","http://www.piyao.org.cn/");
            startActivity(intent);
        });
        Iv5.setOnClickListener(v->{
            Intent intent= new Intent("web");
            intent.putExtra("WEB","https://www.sohu.com/a/373257575_260616");
            startActivity(intent);
        });
        Iv6.setOnClickListener(v->{
            Intent intent= new Intent("web");
            intent.putExtra("WEB","http://www.baidu.com");
            startActivity(intent);
        });
        Iv7.setOnClickListener(v->{
            MainMenuActivity ma = (MainMenuActivity) getActivity();
            ma.check2();
        });
        Iv8.setOnClickListener(v->{
            Intent intent= new Intent("web");
            intent.putExtra("WEB","https://www.sohu.com/a/312375730_120151741");
            startActivity(intent);
        });


        return view;
    }
    private void getViews(View v){
        Iv1 = v.findViewById(R.id.iv_1);
        Iv2 = v.findViewById(R.id.iv_2);
        Iv3 = v.findViewById(R.id.iv_3);
        Iv4 = v.findViewById(R.id.iv_4);
        Iv5 = v.findViewById(R.id.iv_5);
        Iv6 = v.findViewById(R.id.iv_6);
        Iv7 = v.findViewById(R.id.iv_7);
        Iv8 = v.findViewById(R.id.iv_8);
    }


}
