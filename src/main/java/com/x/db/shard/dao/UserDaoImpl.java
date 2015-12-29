package com.x.db.shard.dao;

import com.x.db.shard.DbAccessor;
import com.x.db.shard.bean.User;
import com.x.db.shard.rule.UserHashRule;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-25
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl implements IUserDao {
    private DbAccessor<SqlMapClientTemplate> ibatisDbAccessor;

    @Override
    public User get(long id,String userpin) {
        return (User) ibatisDbAccessor.route(new UserHashRule(userpin))
                .queryForObject("User.getById",id);
    }

    @Override
    public long add(User user) {
        ibatisDbAccessor.route(new UserHashRule(user.getUserpin()))
                .insert("User.insert",user);
        return user.getId();
    }

    @Override
    public int delete(long  id,String userpin) {
        return (Integer) ibatisDbAccessor.route(new UserHashRule(userpin))
                .insert("User.delete",id);
    }

    @Override
    public int update(User user) {
        return (Integer) ibatisDbAccessor.route(new UserHashRule(user.getUserpin()))
                .insert("User.delete",user);
    }

    public void setIbatisDbAccessor(DbAccessor ibatisDbAccessor) {
        this.ibatisDbAccessor = ibatisDbAccessor;
    }
}
