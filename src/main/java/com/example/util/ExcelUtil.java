package com.example.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.example.Entity.ApiTestData;
import com.example.listener.ApiTestDataListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class ExcelUtil {

    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link ApiTestData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ApiTestDataListener}
     * <p>3. 直接读即可
     */
    public void simpleRead(String filePath) {
        // 有个很重要的点 ApiTestDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filePath, ApiTestData.class, new ApiTestDataListener()).sheet().doRead();
    }

    /**
     * 读取到内存方式读取同步返回，指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
     * @param filePath
     * @param object
     * @return List<Object>
     */
    public List<Object> synchronousRead(String filePath,Object object) {
        List<Object> list = EasyExcel.read(filePath).head(object.getClass()).sheet().doReadSync();
//        try {
//            for (Object o : list) {
//                log.info("读取到数据:{}", JSON.toJSONString(o));
//                System.out.println(o);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return list;
    }

    /**
     * 读取到内存方式读取同步返回
     * @param filePath
     */
    public void synchronousRead(String filePath) {
        List<Map<Integer, String>> listMap = EasyExcel.read(filePath).sheet().doReadSync();
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            log.info("读取到数据:{}", JSON.toJSONString(data));
        }
    }

}
