package com.example.test;

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
        System.out.println(list.get(0));
//        System.out.println("==========================================");
//        System.out.println();
//        for (int i = 0; i < jsonArray.size(); i++) {
//            System.out.println(jsonArray.get(i));
//        }



//        for (Object o:list){
//            System.out.println(o.toString());
//            System.out.println();
//        }

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
