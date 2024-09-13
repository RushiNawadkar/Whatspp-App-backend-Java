package com.app.request;

public class SingleChatRequest {

	private Long userid;

	public SingleChatRequest() {
		super();
	}

	public SingleChatRequest(Long userid) {
		super();
		this.userid = userid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "SingleChatRequest [userid=" + userid + "]";
	}
	
	
	
}
