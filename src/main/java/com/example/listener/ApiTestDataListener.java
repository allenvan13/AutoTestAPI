package com.example.listener;
import java.util.ArrayList;
import java.util.List;

import com.example.DAO.ApiTestRepository;
import com.example.Entity.ApiTestData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;

@Slf4j
public class ApiTestDataListener extends AnalysisEventListener<ApiTestData> {

    /**
     * 每隔100条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    List<ApiTestData> list = new ArrayList<>();

    @Resource
    private ApiTestRepository apiTestRepository;

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param apiTestData
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ApiTestData apiTestData, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(apiTestData));
        list.add(apiTestData);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
//        apiTestRepository.save(list);
        log.info("存储数据库成功！");
    }
}
