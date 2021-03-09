package com.example.counter.util;

import com.example.counter.bean.res.Account;
import com.google.common.collect.ImmutableMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbUtil {

  /** 如何在静态调用的工具类中注入Spring管理对象 => ans: 通过 @PostConstruct */
  private static DbUtil dbUtil = null;

  @Autowired private SqlSessionTemplate sqlSessionTemplate;

  private DbUtil() {}

  public static long getId() {
    Long res = dbUtil.getSqlSessionTemplate().selectOne(("testMapper.queryBalance"));
    if (null == res) {
      return -1;
    } else return res;
  }

  public static Account queryAccount(long uid, String password) {
    return dbUtil
        .getSqlSessionTemplate()
        .selectOne("userMapper.queryAccount", ImmutableMap.of("Uid", uid, "Password", password));
  }

  public static void updateLoginTime(long uid, String nowDate, String nowTime) {
    dbUtil
        .getSqlSessionTemplate()
        .update(
            "userMapper.updateAccountLoginTime",
            ImmutableMap.of(
                "Uid", uid,
                "ModifyDate", nowDate,
                "ModifyTime", nowTime));
  }

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
}
