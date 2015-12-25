package com.x.db.shard.service;

import com.x.db.shard.TxAccessor;
import com.x.db.shard.bean.Order;
import com.x.db.shard.dao.IOrderDao;
import com.x.db.shard.dao.IUserDao;
import com.x.db.shard.rule.LotteryTypeAndIdRule;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

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
    public void addOrderAndModify(final Order order){
        txAccessor.route(new LotteryTypeAndIdRule(order.getLotteryType(),order.getId()),false).execute(
                new TransactionCallback<Object>() {
                    @Override
                    public Object doInTransaction(TransactionStatus status) {
                        try{
                            orderDao.add(order);
                            order.setUserpin("xdddd");
                            int count=orderDao.update(order);
                            if(count<1){
                                throw new Exception("更新失败");
                            }
                        }catch (Exception e){
                            status.setRollbackOnly();
                            throw new RuntimeException(e);
                        }
                        return null;
                    }
                }
        ) ;


    }
    public Order queryOrder(long orderId,int lotteryType){
        return orderDao.get(orderId,lotteryType);

    }

    public void setTxAccessor(TxAccessor txAccessor) {
        this.txAccessor = txAccessor;
    }
}
