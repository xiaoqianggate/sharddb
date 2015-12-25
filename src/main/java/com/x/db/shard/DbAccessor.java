package com.x.db.shard;

import com.ibatis.sqlmap.engine.mapping.sql.Router;
import com.x.db.shard.rule.DefaultDbRule;
import com.x.db.shard.rule.RouterRule;
import com.x.db.shard.rule.RuleExecutor;
import org.springframework.jdbc.support.JdbcAccessor;

import javax.sql.DataSource;
import java.util.Map;

/**
 * db访问模板获取器
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class DbAccessor<T extends JdbcAccessor> {
    private RuleExecutor ruleExecutor=new RuleExecutor();
    protected Map<String,DataSource> dsMap;
    protected Map<String,DataSource> xaDsMap;
    protected abstract T sqlTemplate(String db,boolean xa);

    /**
     * 路由解析获取数据模板
     * @param rule
     * @return
     */
    public  T route(RouterRule rule){
        Router router= ruleExecutor.explain(rule);
        return sqlTemplate(router.db(),Router.xa());
    }

    /**
     * 默认数据库访问模板
     * @return
     */
    public  T route(){
        Router router= ruleExecutor.explain(new DefaultDbRule());
        return sqlTemplate(router.db(),Router.xa());
    }
    public DataSource dataSource(RouterRule rule){
        Router router= ruleExecutor.explain(rule);
        return dsMap.get(router.db());
    }

    public void setDsMap(Map<String, DataSource> dsMap) {
        this.dsMap = dsMap;
    }

    public void setXaDsMap(Map<String, DataSource> xaDsMap) {
        this.xaDsMap = xaDsMap;
    }
}
