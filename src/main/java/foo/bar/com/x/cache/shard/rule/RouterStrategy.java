package foo.bar.com.x.cache.shard.rule;

import com.ibatis.sqlmap.engine.mapping.sql.Router;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-7
 * Time: 下午12:26
 * To change this template use File | Settings | File Templates.
 */
public interface RouterStrategy {
    public Router explain(RouterRule rule);
}
