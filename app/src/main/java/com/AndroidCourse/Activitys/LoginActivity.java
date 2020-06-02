package com.AndroidCourse.Activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;


import com.AndroidCourse.Activitys.MainMenu.HorRecyclerViewActivity;
import com.AndroidCourse.Activitys.MainMenu.MainMenuActivity;
import com.AndroidCourse.POJO.Goods;
import com.AndroidCourse.POJO.Medicine;
import com.AndroidCourse.POJO.User;
import com.AndroidCourse.R;
import com.AndroidCourse.Services.LocationService;
import com.AndroidCourse.Services.RemindService;
import com.AndroidCourse.Utils.DB.MedicineDBA;
import com.AndroidCourse.Utils.Net.HttpRequest;
import com.AndroidCourse.Utils.Net.RequestCallAble;
import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;
import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.NormalDialog;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class LoginActivity extends AppCompatActivity {
    private final String spTag = "Login";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//================================================================

        Button button = findViewById(R.id.button);
        button.setOnClickListener((v) -> {
            Intent intent=new Intent(LoginActivity.this, HorRecyclerViewActivity.class);
            startActivity(intent);
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



    List<Goods> getAllGoods(){
        List<Goods> list = null;
        try {
            String objs = new RequestCallAble(new HashMap<String, String>(),HttpRequest._Goods.getURL(),"").commit();
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
}
