package com.example.test;

import com.alibaba.fastjson.JSON;
import com.example.Entity.ApiTestData;
import com.example.util.ExcelUtil;
import org.testng.annotations.*;

import java.util.List;

@Test
public class TestCase {

    @Test
    public void test01(){
        System.out.println("test01-----------------------！");
        String excelName = "data.xlsx";
        ExcelUtil eu = new ExcelUtil();
        String path = "D:\\Data\\intellijData\\AutoTestApi\\src\\main\\resources\\static\\data.xlsx";
        List<Object> list = eu.synchronousRead(path,new ApiTestData());
        for (Object o:list){
            System.out.println(JSON.toJSONString(o));
        }

        System.out.println("------------------------------------------------------");

        System.out.println("test01-----------------------！");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("BeforeTest=========================!");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("AfterTest=========================!");
    }

}
