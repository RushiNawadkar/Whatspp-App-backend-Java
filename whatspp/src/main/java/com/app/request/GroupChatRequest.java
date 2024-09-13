package com.app.request;

import java.util.List;

public class GroupChatRequest {

	private List<Long>userid;
	private String chaName;
	private String chatImage;
	public GroupChatRequest() {
		super();
	}
	public GroupChatRequest(List<Long> userid, String chaName, String chatImage) {
		super();
		this.userid = userid;
		this.chaName = chaName;
		this.chatImage = chatImage;
	}
	public List<Long> getUserid() {
		return userid;
	}
	public void setUserid(List<Long> userid) {
		this.userid = userid;
	}
	public String getChaName() {
		return chaName;
	}
	public void setChaName(String chaName) {
		this.chaName = chaName;
	}
	public String getChatImage() {
		return chatImage;
	}
	public void setChatImage(String chatImage) {
		this.chatImage = chatImage;
	}
	@Override
	public String toString() {
		return "GroupChatRequest [userid=" + userid + ", chaName=" + chaName + ", chatImage=" + chatImage + "]";
	}
	
	
}
