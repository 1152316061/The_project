package com.AndroidCourse.Activitys.MainMenu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.AndroidCourse.R;
import com.AndroidCourse.Utils.DB.MedicineDBA;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
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
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_3, container, false);

        SharedPreferences sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String UID = sp.getString("UID","0000");
        sp = getActivity().getSharedPreferences("phone", Context.MODE_PRIVATE);
        String phone = sp.getString("phone","110");
        tv1 = root.findViewById(R.id.textView8);
        tv2 = root.findViewById(R.id.textView7);
        tv3 = root.findViewById(R.id.textView6);
        tv1.setText(UID);
        tv2.setText("上海市黄浦区人民大道200号");
        tv3.setText(phone);
        button = root.findViewById(R.id.btout);
        button.setOnClickListener(v->{
            SharedPreferences sp2 = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp2.edit();
            edit.clear();
            edit.commit();
            MedicineDBA.delAllMedicine(getActivity());
            getActivity().finish();
        });
        return root;
    }
}
