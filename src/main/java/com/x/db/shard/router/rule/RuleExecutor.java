package com.x.db.shard.router.rule;

import com.ibatis.sqlmap.engine.mapping.sql.Router;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-4
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 */
public class RuleExecutor implements ApplicationListener {
    private Map<Class,RouterStrategy> map=new HashMap<Class, RouterStrategy>();
    {
        map.put(UserHashRule.class,new UserHashRouterStrategy());
        map.put(LotteryTypeAndIdRule.class,new LotteryTypeAndIdRouterStrategy());
        map.put(LotteryTypeAndIssueRule.class,new LotteryTypeAndIssueRouterStrategy());
        map.put(DefaultDbRule.class,new DefaultDbRouterStrategy());
    }
    public Router explain(RouterRule rule){
         return map.get(rule.getClass()).explain(rule);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        ApplicationContext a=null;
    }
}
