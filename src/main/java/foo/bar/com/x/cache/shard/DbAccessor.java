package foo.bar.com.x.cache.shard;

import com.ibatis.sqlmap.engine.mapping.sql.Router;
import foo.bar.com.x.cache.shard.rule.RouterRule;
import foo.bar.com.x.cache.shard.rule.RuleExecutor;
import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.transaction.support.TransactionTemplate;

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
    protected abstract T sqlTemplate(String db);
    public  T defaultSqlTemplate(){
        return sqlTemplate("0");
    };
    public  T routeDb(RouterRule rule){
        Router router= ruleExecutor.explain(rule);
        return sqlTemplate(router.db());
    }
    protected abstract TransactionTemplate txTemplate(String type);
    public  TransactionTemplate routeTx(RouterRule rule){
        Router router= ruleExecutor.explain(rule);
        return txTemplate(router.db());
    }
    /**
     * 默认事务模板
     * @return
     */
    public TransactionTemplate defaultTxTemplate(){
        return txTemplate("0");
    }
    public DataSource dataSource(RouterRule rule){
        Router router= ruleExecutor.explain(rule);
        return dsMap.get(router.db());
    }
    /**
     * 默认数据源
     * @return
     */
    public DataSource defaultDataSource(){
        return dsMap.get(0);
    }

    public void setDsMap(Map<String, DataSource> dsMap) {
        this.dsMap = dsMap;
    }

}
