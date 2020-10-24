package com.AndroidCourse.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.AndroidCourse.POJO.HealthInformation;
import com.AndroidCourse.R;
import com.AndroidCourse.Utils.Net.HttpRequest;
import com.AndroidCourse.Utils.Net.RequestCallAble;
import com.alibaba.fastjson.JSON;
import com.xq.fasterdialog.dialog.NormalDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class HealthInformationActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_information_acyivity);

        et1 = findViewById(R.id.het1);
        et2 = findViewById(R.id.het2);
        et3 = findViewById(R.id.het3);
        rg = findViewById(R.id.radioGroup);

        Button bt = findViewById(R.id.hb);
        bt.setOnClickListener(v->{
            SharedPreferences sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
            String UID = sp.getString("UID","0000");
            String SP = et1.getText().toString().trim();
            String DP = et2.getText().toString().trim();
            String HR = et3.getText().toString().trim();
            String other = "";
            if(rg.getCheckedRadioButtonId()==R.id.radioButton2)
                other = "不适";
            if(UID.isEmpty() || SP.isEmpty() || DP.isEmpty() || HR.isEmpty()){
                NormalDialog dialog= new NormalDialog(this);
                dialog.setContent("存在空白项!").setNeutralText("确定").show();
            }else{
                commitHI(new HealthInformation(UID, Integer.parseInt(HR),Integer.parseInt(SP),Integer.parseInt(DP),new Timestamp(System.currentTimeMillis()),other));
            }
        });
    }
    private void commitHI(HealthInformation HI){
        Map<String,String> m = new HashMap<String, String>();
        m.put("hi", JSON.toJSONString(HI));
        RequestCallAble request = new RequestCallAble(m, HttpRequest.HI.getURL());
        try {
            String result = request.commit();
            System.out.println(result+"^^^^^^");
            if(result.equals("SUCCESS")){
                NormalDialog dialog = new NormalDialog(this);
                dialog.setContent("提交成功").setNeutralText("确定").setNeutralListener(new BaseDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(BaseDialog dialog) {
                        finish();
                    }
                }).show();
            }else{
                new NormalDialog(this).setTitle("错误").setContent("提交失败请重试").setNeutralText("确定").show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
