package com.AndroidCourse.Activitys.MainMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.AndroidCourse.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    private RecyclerView mRvHor;
    private ImageView mIv1;
    private TextView mTv1;
    private ImageView mIv2;
    private TextView mTv2;
    private ImageView mIv3;
    private TextView mTv3;
    private ImageView mIv4;
    private TextView mTv4;
    private ImageView mIv5;
    private TextView mTv5;
    private ImageView mIv6;
    private TextView mTv6;
    private ImageView mIv7;
    private TextView mTv7;
    private ImageView mIv8;
    private TextView mTv8;

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


        mIv1 = view.findViewById(R.id.iv_1);
        mTv1 = view.findViewById(R.id.tv_1);
        mIv1 = view.findViewById(R.id.iv_2);
        mTv1 = view.findViewById(R.id.tv_2);
        mIv1 = view.findViewById(R.id.iv_3);
        mTv1 = view.findViewById(R.id.tv_3);
        mIv1 = view.findViewById(R.id.iv_4);
        mTv1 = view.findViewById(R.id.tv_4);
        mIv1 = view.findViewById(R.id.iv_5);
        mTv1 = view.findViewById(R.id.tv_5);
        mIv1 = view.findViewById(R.id.iv_6);
        mTv1 = view.findViewById(R.id.tv_6);
        mIv1 = view.findViewById(R.id.iv_7);
        mTv1 = view.findViewById(R.id.tv_7);
        mIv1 = view.findViewById(R.id.iv_8);
        mTv1 = view.findViewById(R.id.tv_8);
//        mIv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mTv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mIv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mTv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mIv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mTv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mIv4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mTv4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mIv5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mTv5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mIv6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mTv6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mIv7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mTv7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mIv8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });
//        mTv8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//                startActivity(intent);
//            }
//        });

//        mRvHor = view.findViewById(R.id.rv_hor);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRvHor.setLayoutManager(linearLayoutManager);
//        mRvHor.setAdapter(new HorAdapter(getActivity(),new ArrayList<>()));


        return view;
    }


}
