package com.AndroidCourse.Utils.Net;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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

    public static String request(Map<String, String> param, URL url) {
        OkHttpClient client = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String,String> entry:param.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }

        RequestBody requestBody=builder.build();
        Request req = new Request.Builder().url(url).post(requestBody).build();

        final String[] back = new String[1];
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                back[0] = response.body().string();
            }
        });
        return back[0];
    }
}
