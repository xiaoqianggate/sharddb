package com.x.db.shard.router.rule;

import com.ibatis.sqlmap.engine.mapping.sql.Router;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-7
 * Time: 下午12:26
 * To change this template use File | Settings | File Templates.
 */
public class LotteryTypeAndIdRouterStrategy implements RouterStrategy<LotteryTypeAndIdRule>{
    public Router explain(LotteryTypeAndIdRule rule){
         Router.db(rule.getLotteryType()+"");
         Router.tableName("order_"+(String.valueOf(rule.getOrderId()).substring(0,1)));
        return Router.router();
    }
}
