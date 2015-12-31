package com.x.db.shard.accessor;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * db访问模板获取器
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public  class IbatisDbAccessor extends DbAccessor<SqlMapClientTemplate> {
    private SqlMapClient sqlMapClient;
    @Override
    public SqlMapClientTemplate sqlTemplate(String db) {
        return new SqlMapClientTemplate(ds(db),sqlMapClient);
    }
    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
}
