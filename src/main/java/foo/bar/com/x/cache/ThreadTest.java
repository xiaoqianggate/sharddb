package foo.bar.com.x.cache;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-8
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 */
public class ThreadTest {
    private ThreadLocal local=new ThreadLocal();
    void hello(){
        local.set("ok");
    }
    public void show(){
        System.out.println(local.get());
    }

}
