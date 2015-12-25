package com.x.db.shard.dao;

import com.x.db.shard.bean.Order;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-25
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
public interface IOrderDao {
    Order get(long id,int lotteryType);
    long add(Order order);
    int delete(long id,int lotteryType);
    int update(Order order);
}
