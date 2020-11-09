package com.example.DAO;

import com.example.Entity.ApiTestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AllenVan
 * @version 1.0
 * @date 2020/11/4
 */
@Component
public interface ApiTestRepository extends JpaRepository<ApiTestData,Long> {

    public void save(List<ApiTestData> list);

}
