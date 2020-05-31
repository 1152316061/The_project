package com.AndroidCourse.Activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import android.widget.Button;


import com.AndroidCourse.Activitys.MainMenu.MainMenuActivity;
import com.AndroidCourse.POJO.Medicine;
import com.AndroidCourse.POJO.User;
import com.AndroidCourse.R;
import com.AndroidCourse.Services.RemindService;
import com.AndroidCourse.Utils.DB.MedicineDBA;
import com.AndroidCourse.Utils.Net.HttpRequest;
import com.AndroidCourse.Utils.Net.RequestCallAble;
import com.alibaba.fastjson.JSON;
import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.NormalDialog;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    private final String spTag = "Login";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Medicine m = new Medicine("阿司匹林","1","10:00:00");
        MedicineDBA.addMedicine(m,this);
        Intent intent = new Intent(this, RemindService.class);
        startService(intent);
        Button button = findViewById(R.id.button);
        button.setOnClickListener((v)->{
            NotificationChannel channel = new NotificationChannel("static", "Primary Channel", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"static")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("标题")
                    .setContentText("内容");
            manager.notify(1,builder.build());
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
            Intent intent = new Intent("MainMenu").putExtra("UID", user.getUID());
            startActivity(intent);
            finish();
        }else{
            NormalDialog normalDialog = new NormalDialog(this);
            normalDialog.setTitle("登录失败").setContent("账号或密码错误").setNeutralText("确定").show();
        }
    }
}
