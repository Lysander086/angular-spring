package com.example.counter.util;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbUtil {

    /**
     * 如何在静态调用的工具类中注入Spring管理对象 => ans: 通过 @PostConstruct
     */
    private static DbUtil dbUtil = null;

    private DbUtil() {}


    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @PostConstruct
    private void init() {
        dbUtil = new DbUtil();
        dbUtil.setSqlSessionTemplate(this.sqlSessionTemplate);
    }

    public static long getId() {
        Long res = dbUtil.getSqlSessionTemplate().selectOne(("testMapper.queryBalance"));
        if (null == res) {
            return -1;
        } else return res;
    }

}
