package com.x.db.shard.router.rule;

import com.ibatis.sqlmap.engine.mapping.sql.Router;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-7
 * Time: 下午12:26
 * To change this template use File | Settings | File Templates.
 */
public class DefaultDbRouterStrategy implements RouterStrategy<LotteryTypeAndIdRule>{
    public Router explain(LotteryTypeAndIdRule rule){
         Router.db("0");
        return Router.router();
    }
}
