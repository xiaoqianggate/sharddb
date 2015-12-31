package com.x.db.shard.service;

import com.x.db.shard.accessor.TxAccessor;
import com.x.db.shard.bean.Order;
import com.x.db.shard.bean.User;
import com.x.db.shard.dao.IOrderDao;
import com.x.db.shard.dao.IUserDao;
import com.x.db.shard.router.rule.LotteryTypeAndIdRule;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-25
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */
public class OrderService {
    private IOrderDao orderDao;
    private IUserDao userDao;
    private TxAccessor txAccessor;
    public IOrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    public void buyOrder(Order order){
        long id=orderDao.add(order);
        System.out.println(id);

    }
    public void addOrders(final List<Order> orders){
        if(orders!=null)
            for(Order order:orders){
                orderDao.add(order);
            }


    }
    public void addOrderAndUser(final Order order, final User user){
          txAccessor.xa().execute(new TransactionCallback<Object>() {
              @Override
              public Object doInTransaction(TransactionStatus status) {
                  boolean flag = true;
                  try {
                      orderDao.add(order);
                      userDao.add(user);
                  } catch (Exception e) {
                      e.printStackTrace();
                      flag = false;
                  } finally {
                      if (!flag) {
                          status.setRollbackOnly();
                      }
                  }
                  return flag;
              }
          }) ;

    }
    public void addOrder(final List<Order> orders){
        if(orders!=null)
            for(Order order:orders){
                orderDao.add(order);
            }
    }
    public Order queryOrder(long orderId,int lotteryType){
        return orderDao.get(orderId,lotteryType);

    }

    public void setTxAccessor(TxAccessor txAccessor) {
        this.txAccessor = txAccessor;
    }

    public void addOrderAndModify(final Order order) {
        txAccessor.tx(new LotteryTypeAndIdRule(order.getLotteryType(), order.getId())).execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try{
                    orderDao.add(order);
                    order.setFee(19000l);
                    orderDao.update(order);
                }catch (Exception e){
                    status.setRollbackOnly();
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
