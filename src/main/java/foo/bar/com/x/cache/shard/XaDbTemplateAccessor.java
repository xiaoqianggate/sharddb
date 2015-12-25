package foo.bar.com.x.cache.shard;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * db访问模板获取器
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public  class XaDbTemplateAccessor extends DbAccessor<JdbcTemplate> {
    private TransactionTemplate transactionTemplate;

    @Override
    public JdbcTemplate sqlTemplate(String db) {
        return new JdbcTemplate(dsMap.get(db));
    }

    @Override
    public TransactionTemplate txTemplate(String db) {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
