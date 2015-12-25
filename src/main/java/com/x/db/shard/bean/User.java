package com.x.db.shard.bean;

public class User  implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;


	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: id 
     */
	private Long id;
    /**
     * userpin       db_column: userpin 
     */
	private String userpin;
	//columns END

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserpin() {
        return userpin;
    }

    public void setUserpin(String userpin) {
        this.userpin = userpin;
    }
}

