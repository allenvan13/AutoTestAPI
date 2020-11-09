package com.example.Entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author AllenVan
 * @version 1.0
 * @date 2020/10/22
 */

@Data
@Table(name = "api_test_base")
public class ApiTestData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_bigint")
    @ExcelIgnore
    private Long id;

    //excel读取
    @Id
    @Column(name = "caseID_varchar")
    @ExcelProperty(value = "用例编号", index = 0)
    private String caseId;

    @Column(name = "requestMethod_varchar")
    @ExcelProperty(value = "请求方式", index = 1)
    private String request_method;

    @Column(name = "url_varchar")
    @ExcelProperty(value = "接口地址", index = 2)
    private String url;

    @Column(name = "requestHeader_varchar")
    @ExcelProperty(value = "请求头", index = 3)
    private String request_header;

    @Column(name = "requestBody_varchar")
    @ExcelProperty(value = "请求参数", index = 4)
    private String request_body;

    @Column(name = "checkType_tinyint")
    @ExcelProperty(value = "测试校验类型", index = 5)
    private String checkType;

    @Column(name = "expectResult_varchar")
    @ExcelProperty(value = "期望结果", index = 6)
    private String expect_result;

    //程序生成数据
    @Column(name = "actualResult_varchar")
    @ExcelProperty(value = "实际结果", index = 7)
    private String actual_result;

    @Column(name = "isExist_bit")
    @ExcelProperty(value = "数据库查询是否存在", index = 8)
    private boolean are_exist;

    @Column(name = "isSuccess_bit")
    @ExcelProperty(value = "是否通过", index = 9)
    private boolean are_success;

    @Column(name = "runTime")
    @ExcelIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date run_time;

}
