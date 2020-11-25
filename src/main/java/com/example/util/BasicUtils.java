package com.example.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

/**
 * 基本工具类
 *
 * @Author wxy
 * @Date 2020/9/25 10:27
 * @Version 1.0
 */
@Slf4j
@Component
public class BasicUtils {
//    @Resource
//    private RSAProp rsaProp;

    String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdWMkUQws5ACrKUsN8QPjTl1f50JJf9VZgSrXoptkTTTncSkhSDMN9Ra4uDf8IwUf82k34MBlHZ+iLHjFpMlYG7xURjWgFG+dt5YpwIroGXiVGOQyiCpUpP6h8BW0Yz2KL22AKqzDcLq29hRnxYQuJAWpvCK6SqPk3psPa/Ack2QIDAQAB";
    String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJ1YyRRDCzkAKspSw3xA+NOXV/nQkl/1VmBKteim2RNNOdxKSFIMw31Fri4N/wjBR/zaTfgwGUdn6IseMWkyVgbvFRGNaAUb523linAiugZeJUY5DKIKlSk/qHwFbRjPYovbYAqrMNwurb2FGfFhC4kBam8IrpKo+Temw9r8ByTZAgMBAAECgYEAkRGVupehljkgOo+Z6kpg33xuwUVaXxxsObVICCskgpY13XOjP/rClhITaCTBWUoSW/7N2iBGfj4/+VuqBKpDAwIsZxMTMWPyMonuw0jI6/OmeHRaSIZsrx0slyApl7g/2gCkKWBpyKTuGl1qxFG61hG2pmJl/cxML3G56w68BLECQQD0zAwQ0oY4gsZ4vCd7SqG/yKx9mbZfDqrKzT9srsIfO9BBeCqkmanmlYytJncOsVMZI+G3pg9yl6WdX3WsrM9tAkEApIw0Cd8d064+wApnLYdlRvEtE3lFpetQk6i+2pJi9403NBPwDitoZkHgGcgpc6t+j9RT2kdJ0LghGvB/wwNLnQJBAK6U8ynwKl8VDRy/+1xXfoG7SSEORd0HJdEajdghzjneO4jlfWwEVwImt7wpCZHF7yCn0WtbF/s+oZNZqB+s4wUCQQCW38MbRgv4Ou1ocmF2mo3fRWYaU/jrkF76dSBCEzVRXJdAVgOnSl/YWxCbMy/m0lkikgIsw27IRPx90gSjj9L1AkAhgV/7RVjospFAyHOuQSAIkYGypixgVEH2nVkeg4iLqg5CLzi8XwR3nfe1ybNt4dLREHeBl1ABiQAtuJzP8Be/";


    /**
     * 公匙RSA加密
     */
    public <T> String pubEnc(T t) {
        //JSON序列化
        String json = JSONObject.toJSONString(t, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        //数据加密
        RSA rsa = new RSA(privateKey, publicKey);
        byte[] value = StrUtil.bytes(json, CharsetUtil.CHARSET_UTF_8);
        byte[] bytes = rsa.encrypt(value, KeyType.PublicKey);
        //BASE64编码
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 公匙RSA解密
     */
    public <T> T pubDec(String string, Class<T> cla) {
        //BASE64解码
        byte[] bytes = Base64.getDecoder().decode(string);
        //数据解密
        RSA rsa = new RSA(privateKey, publicKey);
        byte[] decrypt = rsa.decrypt(bytes, KeyType.PublicKey);
        String value = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
        //JSON序列化
        return JSONObject.parseObject(value, cla);
    }

    /**
     * 私匙RSA加密
     */
    public <T> String priEnc(T t) {
        //JSON序列化
        String json = JSONObject.toJSONString(t, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        //数据加密
        RSA rsa = new RSA(privateKey, publicKey);
        byte[] value = StrUtil.bytes(json, CharsetUtil.CHARSET_UTF_8);
        byte[] bytes = rsa.encrypt(value, KeyType.PrivateKey);
        //BASE64编码
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 私匙RSA解密
     */
    public <T> T priDec(String string, Class<T> cla) {
        //BASE64解码
        byte[] bytes = Base64.getDecoder().decode(string);
        //数据解密
        RSA rsa = new RSA(privateKey, publicKey);
        byte[] decrypt = rsa.decrypt(bytes, KeyType.PrivateKey);
        String value = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
        //JSON序列化
        return JSONObject.parseObject(value, cla);
    }

    public static void main(String[] args) {

        orderInfo od = new orderInfo();
        od.setOrderId("201106-529208773260471");
        BasicUtils basicUtils = new BasicUtils();
        String keyP = basicUtils.priEnc(od);
        System.out.println(keyP);

        String key2 = "LN0HNE4c2sqFvitEUFOmF9UG5xr+qt7jxXK8MzrjWGrRLiwlE/fkfeOQooPc2V+tERp0dMoaKJj1ra8iapXU6wy/LVpZeBvxc2RyZxnSL3Mp1wEbKQUI+V32U0Rnqc1g8WN0gRj5BcNwtjmUdLGCSqbKtqrkH2Ful4DB13B6dEgPvgA84C/bZapEItwUvS7oVNQTC4PADbFDuyh7cAWjRwrENA7XfNjzJikIgF8Z4gijs/8HrNy4wEKidc04FzqoZgWlITEpvxb7JANr0FgKkwto9lp0cGGcH5APxHHJynR+YUgEooy6qiiit1xOG3ecDYEhUQ5e/pq1K6CdlqfPvQGOuqjHbsk/XuWpMGgGN7wy78wP+xTCrad9xDKDBWwOKOfp8tTuOH4Wx3IyQfUW+9RCi/3oFVdeFjWOsPa2gqBIH91FlyRzm7ne5xQvy+xDHGDIS0H/2zHr2lQX3YzzVwC8qV9LmAwnZSwJhE8lIBIqMgezu4jQatoah7sjnte0VP40wGNuroVqvkwhNQ6hrW+PG3SMmJn7BuRNHrCPTg/ID/iuic6T8ilrWI6aZfHBf0NOOHQVhdknWdx6RsYF7hecjbF7s7VrJj68bv1/ncTL6Dz4pKJpQVURD0t0vf4T/Sj8RfMqkdj67TLGnccLzhxAJxSf4OAqA4ynWN7gRVU2lN3HH87x6mL0mHZg2ZDIIyPsrIvFrVJjM7kMJNaUZA/XRgQ74UM1CfOz2nyHCVDYlvda/AKhTwBavvwfJZiHMAcYa1B/UX5cwq6j9HJBaan1GLi6b3jnbdOGUFnd2oGHDFgXl9tP3yiWWwj2ZG92gKfORCZBE10Sth8zwgthP5pi/Q2aL1N59y7J51P/eub+i3TYuyPrOJezCL4ZKc26v7CYnA3r6609iCtJSnlDRLa5301y3Op+hT+j4jm7F24b0Qi7i8qfQxpL6M+DB22klG6QgOwB/9ImPMikt79UoTkr1TazIT3Y0GoN2UPNPNgjcpbK7YZ2QxyfIaMVPsS3lik8i/jN+SfLTZMS2xaknUN4NUVCKfKnw8eZX2AH9bgmkbfQHNgtxpN7A8x7pYMUMwpbnLE/RtO3QMbTPepb/E9yIVqLgWJnwCIrx1LcPwEMh81SjCjWUdxGwKQEYrCfnY4XBEo86KYdDP2oQkhIl/kc4MpuAAu6JmCtnSwXtbt6hbcHhhX9GJ2zUXJOX8ZADRLS/cSWuPj1GcjO0/pgv0LMfOB0+KungpVHQqGhH9vzxy/MhLeNRtv/rb5E3YiBdWikk6B824BcnOYoF+/Jpi0qOV7qhrdyNez2U+rNmklpclTvEi2KbWnXsrKlEsjbR6mN1NHgDozNF6Ew37Nk6w==";



        System.out.println("=========================================");
       Map map = basicUtils.priDec(key2,Map.class);

        System.out.println();

        //举例：查询订单详情接口
        //1.a方 先将数据赋值到对象
//        OmsOrderReq data1 = new OmsOrderReq();
//        data1.setOrderId("201106-529208773260471");
//        //2.a方 对整个对象经行加密
//        String value = basicUtils.priEnc(data1);
//        //3.a方 新建一个对象，只赋值encryptData字段的值
//        OmsOrderReq req1 = new OmsOrderReq();
//        req1.setOrderId(null);
//        req1.setEncryptData(value);
//        //4.a方 网络传输 b方 api接口接受到数据
//        //5.b方 取出对象中加密数据，解密数据
//        OmsOrderReq req2 = new OmsOrderReq();
//        Map map = basicUtils.pubDec(req2.getEncryptData(), Map.class);
        //6.b方 封装返回体 重复步骤1~4


    }

    static class orderInfo{
        String encryptData;
        String orderId;

        public String getEncryptData() {
            return encryptData;
        }

        public void setEncryptData(String encryptData) {
            this.encryptData = encryptData;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }

}
