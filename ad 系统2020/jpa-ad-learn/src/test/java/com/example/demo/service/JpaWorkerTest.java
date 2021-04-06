package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.WorkerDao;
import com.example.demo.dao.WorkerDaoDefinition;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaWorkerTest {
    @Autowired
    private WorkerDao dao;
    @Autowired
    private WorkerDaoDefinition workerDaoDefinition;

    @Test
    public void testOpWorkerWithName() {
        log.info("{}", JSON.toJSONString(dao.findByName("Tom")));
        log.info("{}", JSON.toJSONString(workerDaoDefinition.findByName("Tom")));
    }
}
