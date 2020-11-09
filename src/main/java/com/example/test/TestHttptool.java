package com.example.test;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.util.DateUtil;
import com.example.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestHttptool {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestHttptool.class);

    public static void main(String[] args) {

        System.out.println(DateUtil.getCurrontTime());
        System.out.println("==========================================");
        System.out.println(DateUtil.getCurrentDate());
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
