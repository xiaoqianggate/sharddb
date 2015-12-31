package com.x.db.shard.accessor;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * db访问模板获取器
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public  class JdbcDbAccessor extends DbAccessor<JdbcTemplate> {
    @Override
    protected JdbcTemplate sqlTemplate(String db) {
        return new JdbcTemplate(ds(db));
    }
}
