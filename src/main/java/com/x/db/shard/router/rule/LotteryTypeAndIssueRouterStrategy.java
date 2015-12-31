package com.x.db.shard.router.rule;

import com.ibatis.sqlmap.engine.mapping.sql.Router;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-7
 * Time: 下午12:26
 * To change this template use File | Settings | File Templates.
 */
public class LotteryTypeAndIssueRouterStrategy implements RouterStrategy<LotteryTypeAndIssueRule>{
    private static Map<String,String> issueRoute=new HashMap<String,String>();
    static {
        issueRoute.put("1","1");
        issueRoute.put("2","1");
        issueRoute.put("3","2");
        issueRoute.put("3","2");
        issueRoute.put("3","2");

    }
    public Router explain(LotteryTypeAndIssueRule rule){
         Router.db(rule.getLotteryType()+"");
         Router.tableName("order_"+issueRoute.get(rule.getIssue()));
        return Router.router();
    }
}
