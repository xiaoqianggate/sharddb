package com.x.db.shard.accessor;

import com.ibatis.sqlmap.engine.mapping.sql.Router;
import com.x.db.shard.router.IRouter;
import com.x.db.shard.router.rule.DefaultDbRule;
import com.x.db.shard.router.rule.RouterRule;
import com.x.db.shard.router.rule.RuleExecutor;
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
public abstract class DbAccessor<T extends JdbcAccessor> implements IRouter {
    private RuleExecutor ruleExecutor=new RuleExecutor();
    protected Map<String,DataSource> dsMap;
    protected Map<String,DataSource> xaDsMap;

    /**
     * 找到数据源
     * 决定是否是分布式的
     * @param db
     * @return
     */
    protected  DataSource ds(String db){
        return Router.xa()?xaDsMap.get(db):dsMap.get(db);
    }
    /**
     * 路由解析
     * @param rule
     * @return
     */
    public Router route(RouterRule rule){
        return ruleExecutor.explain(rule);
    }
    /**
     * 获取数据访问模板
     * @param db
     * @return
     */
    protected abstract T sqlTemplate(String db);

    /**
     * 获取访问模板
     * @return
     */
    public  T accessor(RouterRule routerRule){
        return sqlTemplate(route(routerRule).db());
    }
    /**
     * 默认访问模板
     * @return
     */
    public  T accessor(){
        return sqlTemplate(route(new DefaultDbRule()).db());
    }
    /**
     * 获取数据源
     * @param rule
     * @returnds
     */
    public DataSource ds(RouterRule rule){
       return ds(route(rule).db());
    }
    /**
     * 获取默认数据源
     * @returnds
     */
    public DataSource ds(){
        return ds(route(new DefaultDbRule()).db());
    }

    public void setDsMap(Map<String, DataSource> dsMap) {
        this.dsMap = dsMap;
    }

    public void setXaDsMap(Map<String, DataSource> xaDsMap) {
        this.xaDsMap = xaDsMap;
    }
}
