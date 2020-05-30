package com.AndroidCourse.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_information_acyivity);
            commitHI(new HealthInformation("0000",200,200,20,new Timestamp(System.currentTimeMillis()),""));
    }
    private void commitHI(HealthInformation HI){
        Map<String,String> m = new HashMap<String, String>();
        m.put("HealthInformation", JSON.toJSONString(HI));
        RequestCallAble request = new RequestCallAble(m, HttpRequest.HI.getURL(),"");
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
