package foo.bar.com.x.cache.shard.rule;

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
    private Map<RouterRule,RouterStrategy> map=new HashMap<RouterRule, RouterStrategy>();
    {
        map.put(new UserHashRule("pin"),new UserHashRouterStrategy());
    }
    public Router explain(RouterRule rule){
         return map.get(rule).explain(rule);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        ApplicationContext a=null;

        //To change body of implemented methods use File | Settings | File Templates.
    }
}
