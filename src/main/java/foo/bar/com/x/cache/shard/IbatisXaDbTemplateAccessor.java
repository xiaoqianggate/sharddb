package foo.bar.com.x.cache.shard;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * db访问模板获取器
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public  class IbatisXaDbTemplateAccessor extends DbAccessor<SqlMapClientTemplate> {
    private SqlMapClient sqlMapClient;
    private TransactionTemplate transactionTemplate;

    @Override
    public SqlMapClientTemplate sqlTemplate(String db) {
        return new SqlMapClientTemplate(dsMap.get(db),sqlMapClient);
    }

    @Override
    public TransactionTemplate txTemplate(String db) {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
}
