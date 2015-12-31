package com.x.db.shard.router.rule;

import com.ibatis.sqlmap.engine.mapping.sql.Router;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-7
 * Time: 下午12:26
 * To change this template use File | Settings | File Templates.
 */
public class UserHashRouterStrategy implements RouterStrategy<UserHashRule>{
    public Router explain(UserHashRule rule){
         Router.db("0");
         Router.tableName("user_"+(rule.getUserpin().hashCode()%4+1));
        return Router.router();
    }
}
