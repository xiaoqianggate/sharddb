package com.x;

import com.x.db.shard.bean.Order;
import com.x.db.shard.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        OrderService orderService= (OrderService) context.getBean("orderService");
        Order order=new Order();
        order.setErp(21111l);
        order.setFee(20000l);
        order.setIssue("1");
        order.setLotteryType(1);
        order.setUserpin("xqg");
        order.setId(Long.parseLong(order.getIssue()+""+System.currentTimeMillis()));
        orderService.buyOrder(order);
//        orderService.addOrderAndModify(order);
//        Order order1=orderService.queryOrder(11451031881267l,1);
//        System.out.println(order1);
    }
}
