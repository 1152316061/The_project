package com.AndroidCourse.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.AndroidCourse.POJO.Medicine;
import com.AndroidCourse.R;
import com.AndroidCourse.Utils.DB.DBhelper;
import com.AndroidCourse.Utils.DB.MedicineDBA;
import com.xq.fasterdialog.dialog.NormalDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

public class AddMedicineActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    EditText et3;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        et1 = findViewById(R.id.met1);
        et2 = findViewById(R.id.met2);
        et3 = findViewById(R.id.met3);
        bt = findViewById(R.id.mb);
        bt.setOnClickListener(v->{
            String name = et1.getText().toString().trim();
            String dosage = et2.getText().toString().trim();
            String time = et3.getText().toString().trim();
            if(name.isEmpty() || dosage.isEmpty() || time.isEmpty()){
                NormalDialog dialog= new NormalDialog(this);
                dialog.setContent("存在空白项!").setNeutralText("确定").show();
            }else{
                MedicineDBA.addMedicine(new Medicine(name,dosage,time),this);
                Intent intent = new Intent("updateMedList");
                sendBroadcast(intent);
                NormalDialog dialog= new NormalDialog(this);
                dialog.setContent("添加成功!").setNeutralText("确定").setNeutralListener(new BaseDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(BaseDialog dialog) {
                        finish();
                    }
                }).show();
            }
        });


    }
}
