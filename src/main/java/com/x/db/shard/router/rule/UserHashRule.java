package com.x.db.shard.router.rule;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-7
 * Time: 下午12:28
 * To change this template use File | Settings | File Templates.
 */
public class UserHashRule extends RouterRule {
    private String userpin;

    public UserHashRule(String userpin) {
        this.userpin = userpin;
    }

    public String getUserpin() {
        return userpin;
    }

    public void setUserpin(String userpin) {
        this.userpin = userpin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserHashRule)) return false;

        UserHashRule that = (UserHashRule) o;

        if (userpin != null ? !userpin.equals(that.userpin) : that.userpin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userpin != null ? userpin.hashCode() : 0;
    }
}
