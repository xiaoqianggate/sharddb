package com.x.db.shard.bean;

public class Order implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;


	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * erp       db_column: erp 
     */
	private Long erp;
    /**
     * userpin       db_column: userpin 
     */
	private String userpin;
    /**
     * fee       db_column: fee 
     */
	private Long fee;
    private Integer lotteryType;
    private String issue;
    /**
     * id       db_column: id 
     */
	private Long id;
	//columns END

    public Long getErp() {
        return erp;
    }

    public void setErp(Long erp) {
        this.erp = erp;
    }

    public String getUserpin() {
        return userpin;
    }

    public void setUserpin(String userpin) {
        this.userpin = userpin;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(Integer lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "Order{" +
                "erp=" + erp +
                ", userpin='" + userpin + '\'' +
                ", fee=" + fee +
                ", lotteryType=" + lotteryType +
                ", issue='" + issue + '\'' +
                ", id=" + id +
                '}';
    }
}

