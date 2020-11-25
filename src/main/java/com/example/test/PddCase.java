package com.example.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.Entity.HttpClientResult;
import com.example.Entity.OrderInfo;
import com.example.util.BasicUtils;
import com.example.util.HttpTestUtil;
import com.example.util.MyHttpUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AllenVan
 * @version 1.0
 * @date 2020/11/19
 */
@Test
public class PddCase {

//    @Resource
//    private static OrderInfo orderInfo;
//
//    @Resource
//    private static HttpTestUtil httpTestUtil;
//
//    @Resource
//    private static BasicUtils basicUtils;

    private static String URL = "http://192.168.1.190:8002/oms/getOrder";

//    @BeforeMethod
//    public void testBefore(){
//        orderInfo.setOrderId("201106-529208773260471");
//        orderInfo.setEncryptData(basicUtils.priEnc(orderInfo));
//    }
//
//    @Test
//    public void testPdd01() throws Exception{
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("encryptData",orderInfo.getEncryptData());
//        params.put("orderId",orderInfo.getOrderId());
//        HttpClientResult result = httpTestUtil.doPost(URL,params);
//    }

    public static void main(String[] args) throws Exception{
        OrderInfo orderInfo = new OrderInfo();
        HttpTestUtil httpTestUtil = new HttpTestUtil();
        MyHttpUtils myHttpUtils = new MyHttpUtils();
        BasicUtils basicUtils = new BasicUtils();

//        orderInfo.setOrderId("201106-529208773260471");
        orderInfo.setOrderId("2222222");
        orderInfo.setEncryptData(null);

        OrderInfo orderInfo1 = new OrderInfo();

        orderInfo1.setEncryptData(basicUtils.priEnc(orderInfo));

//        System.out.println(basicUtils.priEnc(orderInfo));

        HashMap<String,String> params = new HashMap<String,String>();
        params.put("encryptData",orderInfo1.getEncryptData());
        params.put("orderId",orderInfo1.getOrderId());

        String body = JSON.toJSONString(params);

        HashMap<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json");

//        HttpClientResult result = httpTestUtil.doPost(URL,header,params);

        CloseableHttpResponse result = myHttpUtils.post(URL,body,header);
        JSONObject re = myHttpUtils.getResponseJson(result);
        System.out.println(re);
//        System.out.println(basicUtils.priDec(re.get("encryptData"),Map.class));
        System.out.println("==============================================");
        JSONObject re2= re.getJSONObject("data");
        System.out.println(re2);
//        System.out.println(basicUtils.priDec(JSON.toJSONString(result),Map.class));
        System.out.println("==============================================");
        String encryptData1= re2.get("encryptData").toString();
        System.out.println(encryptData1);
        System.out.println("==============================================");
        Map answer = basicUtils.priDec(encryptData1,Map.class);
        System.out.println("解密后："+answer);

    }
}
