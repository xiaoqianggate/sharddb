package foo.bar.com.x.cache;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-8
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 */
public class ThreadTest1 {
    private ThreadLocal local2=new ThreadLocal();
    void hello(){
        local2.set("ef");
    }
    public void show(){
        System.out.println(local2.get());
    }

}
