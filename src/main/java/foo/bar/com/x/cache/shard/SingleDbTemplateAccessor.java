package foo.bar.com.x.cache.shard;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * db访问模板获取器
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public  class SingleDbTemplateAccessor extends DbAccessor<JdbcTemplate> {


    @Override
    public JdbcTemplate sqlTemplate(String db) {

        return new JdbcTemplate(dsMap.get(db));
    }

    @Override
    public TransactionTemplate txTemplate(String db) {
        DataSource ds=dsMap.get(db);
        return new TransactionTemplate(new DataSourceTransactionManager(ds));
    }
}
