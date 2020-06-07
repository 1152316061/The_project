package com.AndroidCourse.Activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;


import com.AndroidCourse.Activitys.MainMenu.MainMenuActivity;
import com.AndroidCourse.POJO.Goods;
import com.AndroidCourse.POJO.User;
import com.AndroidCourse.R;
import com.AndroidCourse.Utils.DB.OLDBA;
import com.AndroidCourse.Utils.Net.HttpRequest;
import com.AndroidCourse.Utils.Net.RequestCallAble;
import com.alibaba.fastjson.JSON;
import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.NormalDialog;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


public class LoginActivity extends AppCompatActivity {
    private final String spTag = "Login";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent0 = new Intent(this,HelloActivity.class);
        startActivity(intent0);

        setContentView(R.layout.activity_login);
        EditText UID;
        EditText PWD;
        Button LOG;
        SharedPreferences sp = getSharedPreferences(spTag, Context.MODE_PRIVATE);
        String t = sp.getString("UID","");
        if(!t.isEmpty()){
            Intent intent = new Intent("MainMenu");
            startActivity(intent);
            finish();
        }
        UID = findViewById(R.id.UID);
        PWD = findViewById(R.id.PWD);

        LOG = findViewById(R.id.LOG);
        LOG.setOnClickListener(v->{
            login(UID.getText().toString().trim(),PWD.getText().toString().trim());
        });
    }

    private void login(@NotNull String UID,@NotNull String PWD){
        User user = new User(UID,PWD);
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.setLoadingText("登陆中").setCancelable(true);
        loadingDialog.show();
        Map<String,String> m = new HashMap<>();
        m.put("User", JSON.toJSONString(user));
        RequestCallAble request = new RequestCallAble(m,HttpRequest.USER_PWD.getURL(),HttpRequest.USER_PWD.LOGIN);
        String ans = null;
        try {
            ans = request.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadingDialog.cancel();
        if(ans.equals("SUCCESS")){
            SharedPreferences sp = getSharedPreferences(spTag, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("UID",user.getUID());
            editor.putString("pwd",user.getPwd());
            editor.apply();
            Intent intent = new Intent("MainMenu");
            startActivity(intent);
            finish();
        }else{
            NormalDialog normalDialog = new NormalDialog(this);
            normalDialog.setTitle("登录失败").setContent("账号或密码错误").setNeutralText("确定").show();
        }
    }

}
