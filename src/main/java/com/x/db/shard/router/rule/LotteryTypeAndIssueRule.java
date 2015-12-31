package com.x.db.shard.router.rule;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-12-7
 * Time: 下午12:28
 * To change this template use File | Settings | File Templates.
 */
public class LotteryTypeAndIssueRule extends RouterRule {
    private int lotteryType;
    private String issue;

    public LotteryTypeAndIssueRule(int lotteryType, String issue) {
        this.lotteryType = lotteryType;
        this.issue = issue;
    }

    public int getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(int lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
