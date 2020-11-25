package com.example.test;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.Entity.HttpClientResult;
import com.example.util.DateUtil;
import com.example.util.HttpTestUtil;
import com.example.util.MyUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHttptool {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestHttptool.class);

    private static String URL_POST = "http://localhost:8090/login.do";

    public static void main(String[] args) {

        System.out.println("==========================================");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpTestUtil httpTestUtil = new HttpTestUtil();
        HttpPost post = new HttpPost(URL_POST);
        CloseableHttpResponse response = null;
        try {
//            post.setHeader("Cookie", "");
            post.setHeader("Connection", "keep-alive");
            post.setHeader("Accept", "application/x-www-form-urlencoded");
            post.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            post.setHeader("Accept-Encoding", "gzip, deflate, br");
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

            Map<String,String> params = new HashMap<>();
            params.put("code","admin");
            params.put("password","123456");
            HttpClientResult rs = httpTestUtil.doPost(URL_POST,params);
            System.out.println("==========================================");
            System.out.println(rs);
            System.out.println("==========================================");
            System.out.println(rs.getContent());
            System.out.println("==========================================");
            System.out.println(rs.getCode());
            System.out.println("==========================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("==========================================");

    }
    

    public static void test0003(){



    }

    public void test0001(){
        File file = new File("D:\\Data\\intellijData\\demo\\src\\main\\resources\\response.txt");
        HttpResponse re = HttpRequest.get("http://192.168.1.191:8088/demo/person").execute();
        System.out.println(re.getStatus());
        Assert.assertEquals(re.getStatus(),200);
    }

    public void test0002(){

        String url = null;
        String method = null;
        File file = new File("D:\\Data\\intellijData\\demo\\src\\main\\resources\\static\\data.xlsx");
        ExcelReader reader = ExcelUtil.getReader(file);
        List<Map<String, Object>> maplists = reader.readAll();
//        System.out.println(JSON.toJSONString(maplists,true));
        for (Map<String, Object> map : maplists) {
//            System.out.println(JSON.toJSONString(map,true));
            method = map.get("请求方式").toString();
            url = map.get("接口地址").toString();
            boolean a = method.equals("GET");
            System.out.println(a);

            if (method.equals("GET")){
                HttpResponse re = HttpRequest.get(url).execute();
                System.out.println(re.getStatus());
            }else if(method.equals("POST")){
                HttpResponse re = HttpRequest.post(url).execute();
                System.out.println(re.getStatus());
            }else {
                System.out.println("aaaaaa");
            }

        }
    }


}
