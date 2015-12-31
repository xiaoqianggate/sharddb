package com.x.db.shard.accessor;

import com.ibatis.sqlmap.engine.mapping.sql.Router;
import com.x.db.shard.router.IRouter;
import com.x.db.shard.router.rule.RouterRule;
import com.x.db.shard.router.rule.RuleExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 事务模板获取器
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public  class TxAccessor implements IRouter{
    private RuleExecutor ruleExecutor=new RuleExecutor();
    protected Map<String,DataSource> dsMap;
    private TransactionTemplate transactionTemplate;
    /**
     * 根据路由规则和是否为分布式事务确定事务模板
     * @param rule
     * @return
     */
    public Router route(RouterRule rule){
        return  ruleExecutor.explain(rule);
    }
    public TransactionTemplate tx(RouterRule rule){
        Router.resetXa();
        return new TransactionTemplate(new DataSourceTransactionManager(dsMap.get(route(rule).db())));
    }
    /**
     * 默认路由规则，基础库，非分布式事务
     * @return
     */
    public  TransactionTemplate xa(){
        Router.resetXa();
        Router.xa(true);
        return transactionTemplate;
    }
    public void setDsMap(Map<String, DataSource> dsMap) {
        this.dsMap = dsMap;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
