package com.x.db.shard.dao;

import com.x.db.shard.bean.User;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-25
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
public interface IUserDao {
    User get(long id,String userpin);
    long add(User user);
    int delete(long  id,String userpin);
    int update(User user);
}
