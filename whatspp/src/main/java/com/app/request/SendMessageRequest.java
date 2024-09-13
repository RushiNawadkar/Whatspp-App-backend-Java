package com.app.request;

public class SendMessageRequest {

	private String Content;
	private Long chatdid;
	private Long userid;
	public SendMessageRequest() {
		super();
	}
	public SendMessageRequest(String content, Long chatdid, Long userid) {
		super();
		Content = content;
		this.chatdid = chatdid;
		this.userid = userid;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Long getChatdid() {
		return chatdid;
	}
	public void setChatdid(Long chatdid) {
		this.chatdid = chatdid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "SendMessageRequest [Content=" + Content + ", chatdid=" + chatdid + ", userid=" + userid + "]";
	}
	
	
}

