package com.AndroidCourse.Utils.Net;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RequestCallAble implements Callable {
    private Map<String, String> param;
    private URL url;
    private String AIM;
    private static ExecutorService executor = Executors.newCachedThreadPool();


    public RequestCallAble(Map<String, String> param, URL url, String AIM) {
        this.param = param;
        this.url = url;
        this.AIM = AIM;
    }

    @Override
    public String call() throws Exception {
        HttpRequest httpRequest = new HttpRequest();
        String ans = httpRequest.request(param,url,AIM);
        return ans;
    }

    public String commit() throws ExecutionException, InterruptedException {

        Future future = executor.submit(this);
        return (String) future.get();
    }
}
