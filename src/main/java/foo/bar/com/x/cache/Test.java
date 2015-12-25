package foo.bar.com.x.cache;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-10-13
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[]args){
//        LocalCache.getLocalCache().put("hello","1111",12000);
//        LocalCache.getLocalCache().put("hello1","222");
//        Date d=new Date();
//        d.setSeconds(d.getSeconds() + 10);
//        LocalCache.getLocalCache().put("hello3","1111",d);
//        int i=0;
//       while(true){
//           LocalCache.getLocalCache().put("hello"+i++,"1111");
//           LocalCache.getLocalCache().print();
//           try {
//               TimeUnit.SECONDS.sleep(1);
//           } catch (InterruptedException e) {
//               e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//           }
//       }
        ThreadTest t=new ThreadTest();
        t.hello();
        ThreadTest1 t1=new ThreadTest1();
        t1.hello();
        t.hello();
        t.show();
        t1.show();



    }

}
