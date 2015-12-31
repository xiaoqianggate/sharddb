package com.x.db.shard.dao;

import com.x.db.shard.accessor.DbAccessor;
import com.x.db.shard.bean.Order;
import com.x.db.shard.router.rule.LotteryTypeAndIdRule;
import com.x.db.shard.router.rule.LotteryTypeAndIssueRule;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-25
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
public class OrderDaoImpl implements IOrderDao {
    private DbAccessor<SqlMapClientTemplate> ibatisDbAccessor;
    public Order get(long id,int lotteryType) {
        return (Order) ibatisDbAccessor.accessor(new LotteryTypeAndIdRule(lotteryType, id))
                .queryForObject("Order.getById",id);
    }
    @Override
    public long add(Order order) {
        ibatisDbAccessor
                .accessor(new LotteryTypeAndIssueRule(order.getLotteryType(), order.getIssue()))
                .insert("Order.insert",order);
        return order.getId();
    }

    @Override
    public int delete(long id,int lotteryType) {
        return (Integer) ibatisDbAccessor.accessor(new LotteryTypeAndIdRule(lotteryType, id))
                .delete("Order.delete",id);
    }

    @Override
    public int update(Order order) {
        return (Integer) ibatisDbAccessor.accessor(new LotteryTypeAndIdRule(order.getLotteryType(), order.getId()))
                .delete("Order.update",order);
    }

    public void setIbatisDbAccessor(DbAccessor ibatisDbAccessor) {
        this.ibatisDbAccessor = ibatisDbAccessor;
    }
}
