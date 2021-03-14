package com.example.counter.util;

import com.example.counter.bean.res.Account;
import com.example.counter.bean.res.OrderInfo;
import com.example.counter.bean.res.PosiInfo;
import com.example.counter.bean.res.TradeInfo;
import com.example.counter.cache.CacheType;
import com.example.counter.cache.RedisStringCache;
import com.example.counter.thirdParty.order.OrderCmd;
import com.example.counter.thirdParty.order.OrderStatus;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: counter
 * @DESCRIPTION:
 * @USER: 涂玄武
 * @DATE: 2020/11/20 11:32
 */
@Component
public class DbUtil {
    /**
     * 如何在静态调用的工具类中注入Spring管理对象
     */
    private static DbUtil dbUtil = null;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private DbUtil() {
    }

    /**
     * 可直接DbUtil.getId()调用
     */
    public static long getId() {
        Long res = dbUtil.getSqlSessionTemplate().selectOne(
                "testMapper.queryBalance"
        );
        if (res == null) {
            return -1;
        } else {
            return res;
        }
    }

    /**
     * 根据账号和密码查询用户
     *
     * @param uid
     * @param password
     * @return
     */
    public static Account queryAccount(long uid, String password) {
        //Guava写法：ImmutableMap.of方法入参最多只能有5对,如果添加的数据超过5对,需要改用builder方法
        return dbUtil.getSqlSessionTemplate().selectOne(
                "userMapper.queryAccount",
                ImmutableMap.of("Uid", uid, "Password", password)
        );
    }

    /**
     * 更新最近登录时间
     *
     * @param uid
     * @param nowDate
     * @param nowTime
     */
    public static void updateLoginTime(long uid, String nowDate, String nowTime) {
        dbUtil.getSqlSessionTemplate().update(
                "userMapper.updateAccountLoginTime",
                ImmutableMap.of(
                        "Uid", uid,
                        "ModifyDate", nowDate,
                        "ModifyTime", nowTime
                )
        );
    }

    /**
     * 更新用户密码
     *
     * @param uid
     * @param oldPwd
     * @param newPwd
     * @return
     */
    public static int updatePwd(long uid, String oldPwd, String newPwd) {
        return dbUtil.getSqlSessionTemplate().update(
                "userMapper.updatePwd",
                ImmutableMap.of(
                        "Uid", uid,
                        "NewPwd", newPwd,
                        "OldPwd", oldPwd
                )
        );
    }
    ////////////////////////////////资金类///////////////////////////////////
    public static long getBalance(long uid) {
        Long res = dbUtil.getSqlSessionTemplate().selectOne(
                "orderMapper.queryBalance",
                ImmutableMap.of("Uid", uid)
        );
        if (res == null) {
            return -1;
        } else {
            return res;
        }
    }

    /**
     * 查询持仓数据列表
     *
     * @param uid
     * @return
     */
    public static List<PosiInfo> getPosiList(long uid) {
        //查缓存
        String suid = Long.toBinaryString(uid);
        String posiS = RedisStringCache.get(suid, CacheType.POSI);
        if (StringUtils.isEmpty(posiS)) {
            //未查到 查库 删除缓存
            List<PosiInfo> posiInfoList = dbUtil.getSqlSessionTemplate().selectList(
                    "orderMapper.queryPosi",
                    ImmutableMap.of(
                            "Uid", uid
                    )
            );
            List<PosiInfo> result = CollectionUtils.isEmpty(posiInfoList) ? Lists.newArrayList() : posiInfoList;
            //更新缓存
            RedisStringCache.cache(suid, JsonUtil.toJson(result), CacheType.POSI);
            return result;
        } else {
            //查到 命中缓存
            return JsonUtil.fromJsonArr(posiS, PosiInfo.class);
        }
    }

    ////////////////////////////////委托类///////////////////////////////////
    public static List<OrderInfo> getOrderList(long uid) {
        //查缓存
        String suid = Long.toBinaryString(uid);
        String orderS = RedisStringCache.get(suid, CacheType.ORDER);
        if (StringUtils.isEmpty(orderS)) {
            //未查到 查库 删除缓存
            List<OrderInfo> orderInfoList = dbUtil.getSqlSessionTemplate().selectList(
                    "orderMapper.queryOrder",
                    ImmutableMap.of(
                            "Uid", uid
                    )
            );
            List<OrderInfo> result = CollectionUtils.isEmpty(orderInfoList) ? Lists.newArrayList() : orderInfoList;
            //更新缓存
            RedisStringCache.cache(suid, JsonUtil.toJson(result), CacheType.ORDER);
            return result;
        } else {
            //查到 命中缓存
            return JsonUtil.fromJsonArr(orderS, OrderInfo.class);
        }
    }

    ////////////////////////////////成交类///////////////////////////////////
    public static List<TradeInfo> getTradeList(long uid) {
        //查缓存
        String suid = Long.toString(uid);
        String tradeS = RedisStringCache.get(suid, CacheType.TRADE);
        if (StringUtils.isEmpty(tradeS)) {
            //未查到 查库
            List<TradeInfo> tmp = dbUtil.getSqlSessionTemplate().selectList(
                    "orderMapper.queryTrade",
                    ImmutableMap.of("Uid", uid)
            );
            List<TradeInfo> result =
                    CollectionUtils.isEmpty(tmp) ? Lists.newArrayList()
                            : tmp;
            //更新缓存
            RedisStringCache.cache(suid, JsonUtil.toJson(result), CacheType.TRADE);
            return result;
        } else {
            //查到 命中缓存
            return JsonUtil.fromJsonArr(tradeS, TradeInfo.class);
        }
    }

    ////////////////////////////////持仓类///////////////////////////////////
    /**
     * 由于持仓、委托、成交数据是需要频繁查询的数据，会对数据库造成很大的负担
     * （优化）：将需要频繁查询的数据在redis缓存中存储一份
     * 在查询数据前先看redis中是否命中，若无再从数据库中查找
     */

    ////////////////////////////////订单处理类///////////////////////////////////
    public static int saveOrder(OrderCmd orderCmd) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("Uid", orderCmd.uid);
        //股票代码
        param.put("Code", orderCmd.code);
        //委托方向
        param.put("Direction", orderCmd.direction.getDirection());
        //委托类型
        param.put("Type", orderCmd.orderType.getType());
        param.put("Price", orderCmd.price);
        param.put("OCount", orderCmd.volume);
        param.put("TCount", 0);
        param.put("Status", OrderStatus.NOT_SET.getCode());

        param.put("Data", TimeformatUtil.yyyyMMdd(orderCmd.timestamp));
        param.put("Time", TimeformatUtil.hhMMss(orderCmd.timestamp));

        int count = dbUtil.getSqlSessionTemplate().insert(
                "orderMapper.saveOrder", param
        );
        //判断是否成功
        return count > 0 ? Integer.parseInt(param.get("ID").toString()) : -1;
    }

    //////////////////////////////股票信息查询///////////////////////////////////////
    public static List<Map<String, Object>> queryAllStockInfo() {
        return dbUtil.getSqlSessionTemplate()
                .selectList("stockMapper.queryStock");
    }

    private SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    private void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * @PostConstruct 将sqlSessionTemplate与DbUtil进行绑定
     * 即在调用DbUtil类时同步完成sqlSessionTemplate的初始化
     */
    @PostConstruct
    private void init() {
        dbUtil = new DbUtil();
        dbUtil.setSqlSessionTemplate(this.sqlSessionTemplate);
    }

}
