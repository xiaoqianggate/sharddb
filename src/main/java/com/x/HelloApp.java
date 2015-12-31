package com.x;

import com.x.db.shard.bean.Order;
import com.x.db.shard.bean.User;
import com.x.db.shard.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class HelloApp {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//        OrderService orderService= (OrderService) context.getBean("orderService");

//        orderService.buyOrder(order);
//        orderService.addOrderAndModify(order);
//        Order order1=orderService.queryOrder(11451031881267l,1);
//        System.out.println(order1);
//        orders();
        addOrderAndModify();
//        addOrderAndUser();
//        orders();
    }
    /**
     * 订单路由
     */
    public static void orders(){
        List<Order>orders=new ArrayList<Order>();
        Order order=new Order();
        order.setErp(21111l);
        order.setFee(20000l);
        order.setIssue("1");
        order.setLotteryType(1);
        order.setUserpin("xqg21");
        order.setId(Long.parseLong(order.getIssue()+""+System.currentTimeMillis()));
        orders.add(order);

        Order order1=new Order();
        order1.setErp(21111l);
        order1.setFee(20000l);
        order1.setIssue("3");
        order1.setLotteryType(1);
        order1.setUserpin("xqg22");
        order1.setId(Long.parseLong(order.getIssue()+""+System.currentTimeMillis()));
        orders.add(order1);

        Order order2=new Order();
        order2.setErp(21111l);
        order2.setFee(20000l);
        order2.setIssue("2");
        order2.setLotteryType(2);
        order2.setUserpin("xqg23");
        order2.setId(Long.parseLong(order.getIssue()+""+System.currentTimeMillis()));
        orders.add(order2);

        Order order3=new Order();
        order3.setErp(21111l);
        order3.setFee(20000l);
        order3.setIssue("3");
        order3.setLotteryType(2);
        order3.setUserpin("xqg24");
        order3.setId(Long.parseLong(order.getIssue()+""+System.currentTimeMillis()));
        orders.add(order3);

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        OrderService orderService= (OrderService) context.getBean("orderService");
        orderService.addOrder(orders);
    }

    /**
     * xa事务
     */
    public static void addOrderAndUser(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        OrderService orderService= (OrderService) context.getBean("orderService");
        Order order=new Order();
        order.setErp(21111l);
        order.setFee(20000l);
        order.setIssue("1");
        order.setLotteryType(1);
        order.setUserpin("xqg22");
        order.setId(Long.parseLong(order.getIssue()+""+System.currentTimeMillis()));
        //user
        User user=new User();
        user.setUserpin("xqg22");
        user.setId(System.currentTimeMillis());
        orderService.addOrderAndUser(order,user);
    }
    /**
     * 单库事务并修改用户
     */
    public static void addOrderAndModify(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        OrderService orderService= (OrderService) context.getBean("orderService");
        Order order=new Order();
        order.setErp(21111l);
        order.setFee(20000l);
        order.setIssue("1");
        order.setLotteryType(1);
        order.setUserpin("xqg22");
        order.setId(Long.parseLong(order.getIssue()+""+System.currentTimeMillis()));
        orderService.addOrderAndModify(order);
    }
    /**
     * 单库事务
     */
    public static void addOrdes(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        OrderService orderService= (OrderService) context.getBean("orderService");
        Order order=new Order();
        order.setErp(21111l);
        order.setFee(20000l);
        order.setIssue("1");
        order.setLotteryType(1);
        order.setUserpin("xqg22");
        order.setId(Long.parseLong(order.getIssue()+""+System.currentTimeMillis()));
        //user
        User user=new User();
        user.setUserpin("xqg22");
        user.setId(System.currentTimeMillis());
        orderService.addOrderAndUser(order,user);
    }
}
