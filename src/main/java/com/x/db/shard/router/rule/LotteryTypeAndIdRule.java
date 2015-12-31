package com.x.db.shard.router.rule;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-7
 * Time: 下午12:28
 * To change this template use File | Settings | File Templates.
 */
public class LotteryTypeAndIdRule extends RouterRule {
    private int lotteryType;
    private long orderId;

    public LotteryTypeAndIdRule(int lotteryType, long orderId) {
        this.lotteryType = lotteryType;
        this.orderId = orderId;
    }

    public int getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(int lotteryType) {
        this.lotteryType = lotteryType;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
