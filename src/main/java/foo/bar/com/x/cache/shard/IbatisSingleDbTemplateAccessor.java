package foo.bar.com.x.cache.shard;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * db访问模板获取器
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public  class IbatisSingleDbTemplateAccessor extends DbAccessor<SqlMapClientTemplate> {
    private SqlMapClient sqlMapClient;


    @Override
    public SqlMapClientTemplate sqlTemplate(String db) {
        return new SqlMapClientTemplate(dsMap.get(db),sqlMapClient);
    }

    @Override
    public TransactionTemplate txTemplate(String db) {
        DataSource ds=dsMap.get(db);
        return new TransactionTemplate(new DataSourceTransactionManager(ds));
    }

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
}
