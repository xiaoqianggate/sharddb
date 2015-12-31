package com.x.db.shard.router;

import com.ibatis.sqlmap.engine.mapping.sql.Router;
import com.x.db.shard.router.rule.RouterRule;

/**
 * 解析路由规则
 * User: zhanghongqiang
 * Date: 15-12-29
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
 public interface IRouter {
     Router route(RouterRule rule);
}
