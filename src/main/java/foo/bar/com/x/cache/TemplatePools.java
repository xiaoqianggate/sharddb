package foo.bar.com.x.cache;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 模板池
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 上午11:51
 */
public class TemplatePools {
    private Map<Integer,DataSource> singleDsMap;
    private Map<Integer,DataSource> xaDsMap;
    private TransactionTemplate transactionTemplate;
    private SqlMapClient sqlMapClient;
    public SqlMapClientTemplate getSqlTemplate(Integer type){
        DataSource ds=singleDsMap.get(type);
        return new SqlMapClientTemplate(ds,sqlMapClient);
    }
    public TransactionTemplate getTransactionTemplate(Integer type){
        DataSource ds=singleDsMap.get(type);
        return new TransactionTemplate(new DataSourceTransactionManager(ds));
    }
    public SqlMapClientTemplate getXaSqlTemplate(Integer type){
        DataSource ds=xaDsMap.get(type);
        return new SqlMapClientTemplate(ds,sqlMapClient);
    }
    public TransactionTemplate getXaTransactionTemplate(){
        return transactionTemplate;
    }

    public void setSingleDsMap(Map<Integer, DataSource> singleDsMap) {
        this.singleDsMap = singleDsMap;
    }

    public void setXaDsMap(Map<Integer, DataSource> xaDsMap) {
        this.xaDsMap = xaDsMap;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
}
