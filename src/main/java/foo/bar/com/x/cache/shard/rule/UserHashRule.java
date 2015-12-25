package foo.bar.com.x.cache.shard.rule;

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
}
