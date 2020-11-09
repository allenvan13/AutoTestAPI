package com.example.util;

import java.text.SimpleDateFormat;

/**
 * @author AllenVan
 * @version 1.0
 * @date 2020/11/9
 */
public class MyUtil {

    /**
     * 20位末尾的数字id
     */
    public static volatile int Guid=100;

    public static Long getGuid() {

        MyUtil.Guid+=1;

        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
        //获取时间戳
        String time=dateFormat.format(now);
        String info=now+"";
        //获取三位随机数
        //int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        int ran=0;
        if(MyUtil.Guid>999){
            MyUtil.Guid=100;
        }
        ran=MyUtil.Guid;

        return Long.valueOf(time+info.substring(2, info.length())+ran);
    }

}
