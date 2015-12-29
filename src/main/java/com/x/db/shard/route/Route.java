package com.x.db.shard.route;

import com.x.db.shard.rule.RouterRule;

/**
 * 解析路由规则
 * User: zhanghongqiang
 * Date: 15-12-29
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
public interface Route<T> {
     T route(RouterRule rule);
}
