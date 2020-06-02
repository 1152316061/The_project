package com.AndroidCourse.Activitys.MainMenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.AndroidCourse.POJO.Goods;
import com.AndroidCourse.R;
import com.AndroidCourse.Utils.DB.OLDBA;
import com.AndroidCourse.Utils.Net.HttpRequest;
import com.AndroidCourse.Utils.Net.RequestCallAble;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
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
    RecyclerView mRvHor;
    List<Goods> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_2, container, false);


        list = getAllGoods();
        mRvHor=root.findViewById(R.id.rv_hor);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        mRvHor.setLayoutManager(linearLayoutManager);
        HorAdapter adapter= new HorAdapter(getActivity(), list);
        adapter.setOnItemButtonClickListener(new HorAdapter.OnItemButtonClickListener() {
            @Override
            public void onClick(View view, int position) {
                System.out.println(position+"$$$$$$$$$");
                addToOL(list.get(position));
            }
        });
        mRvHor.setAdapter(adapter);
        return root;
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
    private void addToOL(Goods g){
        OLDBA.addToOL(g,getActivity());
    }
}
