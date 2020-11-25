package com.example.Entity;

import lombok.Data;

/**
 * @author AllenVan
 * @version 1.0
 * @date 2020/11/19
 */
@Data
public class OrderInfo {

    private String encryptData;
    private String orderId;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "encryptData='" + encryptData + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
