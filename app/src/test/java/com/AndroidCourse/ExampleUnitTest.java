package com.AndroidCourse;

import com.AndroidCourse.Utils.Net.HttpRequest;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void HTTPtest() {
        Map<String,String> m = new HashMap<>();
        m.put("UID","0000");
        m.put("pwd","1234");
        String ans = HttpRequest.request(m,HttpRequest.USER_PWD.getURL(),HttpRequest.USER_PWD.LOGIN);
        System.out.println(ans);
    }
}