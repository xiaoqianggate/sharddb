package com.x.db.shard;

import com.ibatis.sqlmap.engine.mapping.sql.Router;
import com.x.db.shard.route.Route;
import com.x.db.shard.rule.DefaultDbRule;
import com.x.db.shard.rule.RouterRule;
import com.x.db.shard.rule.RuleExecutor;
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
public  class TxAccessor implements Route<TransactionTemplate> {
    private RuleExecutor ruleExecutor=new RuleExecutor();
    protected Map<String,DataSource> dsMap;
    private TransactionTemplate transactionTemplate;
    /**
     * 根据路由规则和是否为分布式事务确定事务模板
     * @param rule
     * @return
     */
    public  TransactionTemplate route(RouterRule rule){
        Router router= ruleExecutor.explain(rule);
        DataSource ds=dsMap.get(router.db());
        return new TransactionTemplate(new DataSourceTransactionManager(ds));
    }
    /**
     * 默认路由规则，基础库，非分布式事务
     * @return
     */
    public  TransactionTemplate route(boolean xa){
        Router.resetXa();
        if(xa){
            Router.xa(true);
            return transactionTemplate;
        }else{
            return route(new DefaultDbRule());
        }
    }
    public void setDsMap(Map<String, DataSource> dsMap) {
        this.dsMap = dsMap;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
