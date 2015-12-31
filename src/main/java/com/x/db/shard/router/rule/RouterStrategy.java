package com.x.db.shard.router.rule;

import com.ibatis.sqlmap.engine.mapping.sql.Router;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-7
 * Time: 下午12:26
 * To change this template use File | Settings | File Templates.
 */
public interface RouterStrategy<T extends RouterRule> {
    public Router explain(T rule);
}
