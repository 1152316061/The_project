package com.AndroidCourse.Utils.Net;



import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequest {
    public String request(Map<String, String> param, URL url, String AIM) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String,String> entry:param.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        if(!AIM.isEmpty())
            builder.add("AIM",AIM);
        RequestBody requestBody=builder.build();
        Request req = new Request.Builder().url(url).post(requestBody).build();

        final String[] back = new String[1];
        try {
            Response response = client.newCall(req).execute();
            back[0] = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return back[0];
    }


    public static final class USER_PWD{
        public static final String LOGIN = "1";
        public static final String CHANGE_PWD = "2";
        public static URL getURL(){
            URL url = null;
            try {
                url = new URL("http://10.0.2.2:8080/Test_war_exploded/UserPwd");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return url;
        }
    }

    public static final class HI{
        public static URL getURL(){
            URL url = null;
            try {
                url=new URL("http://10.0.2.2:8080/Test_war_exploded/HI");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return url;
        }
    }

    public static final class Loc{
        public static URL getURL(){
            URL url = null;
            try {
                url=new URL("http://10.0.2.2:8080/Test_war_exploded/Loc");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return url;
        }
    }

    public static final class _Goods{
        public static URL getURL(){
            URL url = null;
            try {
                url=new URL("http://10.0.2.2:8080/Test_war_exploded/Goods");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return url;
        }
    }
}
