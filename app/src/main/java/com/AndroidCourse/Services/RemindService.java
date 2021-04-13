package com.AndroidCourse.Services;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.AndroidCourse.POJO.Medicine;
import com.AndroidCourse.R;
import com.AndroidCourse.Utils.DB.MedicineDBA;
import com.AndroidCourse.Utils.Net.HttpRequest;
import com.AndroidCourse.Utils.Net.RequestCallAble;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class RemindService extends Service {
    private List<Medicine> list;
    private InnerReceiver receiver;
    private List<Timer> timers;
    private NotificationManager manager;
    private static int id = 0;
    private Map param;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("start>>>>>>>RemindService");
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        System.out.println("Service in");
        timers = new ArrayList<>();
        requestFromService();
        list = new ArrayList<>();
        SharedPreferences sp = sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
        param = new HashMap();
        param.put("UID",sp.getString("UID",""));
        receiver = new InnerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("updateMedList");
        registerReceiver(receiver,filter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void refreshList(){
        new Thread(() -> {
            stopAll();
            //list = MedicineDBA.getMedicine(RemindService.this);
            if(list.size()>0)
                setTimer();
            System.out.println(list.size());
        }).start();


    }
    private void requestFromService(){
        new Thread(() -> {
            while (true){
                try {
                    String response = new RequestCallAble(param, HttpRequest.GetMed.getURL()).commit();
                    List<Medicine> listT = JSON.parseArray(response,Medicine.class);
                    System.out.println("ListSize====>"+listT.size());
                    synchronized (this.list){
                        for(int i = 0;i<this.list.size();i++){
                            if(listT.contains(this.list.get(i))){
                                listT.remove(this.list.get(i));
                            }else{
                                MedicineDBA.delMedicine(this.list.get(i),this);
                                this.list.remove(i);
                            }
                        }
                        for(Medicine m : listT){
                            this.list.add(m);
                            MedicineDBA.addMedicine(m,this);
                        }
                    }
                    if(listT.size()>0){
                        refreshList();
                    }
                    Thread.sleep(600000);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }






    private void setTimer(){
        final long DAY = 24 * 60 * 60 * 1000;
        synchronized (list){
            Iterator<Medicine> it = list.iterator();
            while (it.hasNext()){
                Medicine m = it.next();
                System.out.println(m);
                String[] t = m.getTime().split(":");
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                Calendar startDate = Calendar.getInstance();
//                    startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE),
//                            Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]));
                startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE),
                        startDate.get(Calendar.HOUR), startDate.get(Calendar.MINUTE), startDate.get(Calendar.SECOND));
                System.out.println(startDate.getTime());
                Timer timer = new Timer();
                Task task = new Task(m);
                timer.schedule(task,startDate.getTime(),DAY);
                timers.add(timer);
            }
        }
    }
    private void stopAll(){
        Iterator<Timer> iterator = timers.iterator();
        while (iterator.hasNext()){
            iterator.next().cancel();
            iterator.remove();
        }

    }
    private class InnerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("broadcast>>>>>>>done");
            refreshList();
        }
    }
    private class Task extends TimerTask {
        private Medicine m0;
        public Task(Medicine m0){
            this.m0 = m0;
        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
            NotificationChannel channel = new NotificationChannel("static", "Primary Channel", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(RemindService.this,"static")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("用药时间")
                    .setContentText("药名: "+m0.getmName()+" 剂量:"+m0.getDosage());
            manager.notify(id,builder.build());
            id++;
            id%=100;
        }
    }
}