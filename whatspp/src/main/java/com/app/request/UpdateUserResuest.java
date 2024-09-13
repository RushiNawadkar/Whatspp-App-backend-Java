package com.app.request;

public class UpdateUserResuest {

	private String full_Name;
	private String profile_picture;
	
	public UpdateUserResuest() {
		super();
	}
	
	public UpdateUserResuest(String full_Name, String profile_picture) {
		super();
		this.full_Name = full_Name;
		this.profile_picture = profile_picture;
	}
	
	public String getFull_Name() {
		return full_Name;
	}
	
	public void setFull_Name(String full_Name) {
		this.full_Name = full_Name;
	}
	
	public String getProfile_picture() {
		return profile_picture;
	}
	
	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}
	
	@Override
	public String toString() {
		return "UpdateUserResuest [full_Name=" + full_Name + ", profile_picture=" + profile_picture + "]";
	}
	
	
}
