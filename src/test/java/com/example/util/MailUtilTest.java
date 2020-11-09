package com.example.util;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailUtilTest {

    @Autowired
    private MailUtil mailUtil;

//    @Test
//    public void test0001(){
//        mailUtil.sendSimpleMail("测试1","测试内容1");
//    }

//    @Test
//    public void test0002(){
//        File file = FileUtil.file(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\1.jpg");
//        mailUtil.sendAttachmentMail("测试2","测试内容2",file);
//    }

//    @Test
//    public void test0003(){
//        mailUtil.sendHtmlMail("测试3","测试内容3");
//    }

//    @Test
//    public void test0004(){
//        String imgId="2";
//        String content="<html><body>这是有图片的邮件：<img src=\'cid:"+imgId + "\'></body></html>";
//        String imgPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\1.jpg";
//        mailUtil.sendInlineResourceMail("测试4",content,imgPath,"imgId");
//    }
}