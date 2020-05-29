package com.AndroidCourse.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.AndroidCourse.R;
import com.AndroidCourse.Utils.Net.HttpRequest;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = findViewById(R.id.button);
        button.setOnClickListener((v)->{
            new Thread(()->{
                Map<String,String> m = new HashMap<>();
                m.put("UID","0000");
                m.put("pwd","1234");
                String ans = HttpRequest.request(m,HttpRequest.USER_PWD.getURL(),HttpRequest.USER_PWD.LOGIN);
                System.out.println(ans);
            }).start();
        });
    }
}
