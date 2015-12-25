package foo.bar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        SimpleDao simpleDao= (SimpleDao) context.getBean("mysqlJtaDao");
       // simpleDao.test1();
     //simpleDao.test2();
//        simpleDao.defaultSingleTx();
        simpleDao.singleTx();
     //   JdbcDaoSupport l;
     //   simpleDao.singleTx();
      //  JdbcDaoSupport d;
//        simpleDao.mongocrud();



    }
}
