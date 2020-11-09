package com.example.Controller;

import com.example.DAO.ApiTestRepository;
import com.example.Entity.ApiTestData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author AllenVan
 * @version 1.0
 * @date 2020/11/4
 */
@RestController
public class ApiTestController {

    @Resource
    private ApiTestRepository apiTestRepository;

    @PostMapping("/save")
    public String save(ApiTestData apiTestData) {
        apiTestRepository.save(apiTestData);
        return "保存成功!";
    }


}
