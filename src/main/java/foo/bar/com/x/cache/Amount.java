package foo.bar.com.x.cache;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-3
 * Time: 下午2:06
 * To change this template use File | Settings | File Templates.
 */
public class Amount {
    private long fee;
    private long id;

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "fee=" + fee +
                ", id=" + id +
                '}';
    }
}
