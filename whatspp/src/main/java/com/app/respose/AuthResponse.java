package com.app.respose;

public class AuthResponse {

	private String Jwt;
	private boolean isAuth;
	
	public AuthResponse() {
		super();
	}
	
	public AuthResponse(String jwt, boolean isAuth) {
		super();
		Jwt = jwt;
		this.isAuth = isAuth;
	}
	
	public String getJwt() {
		return Jwt;
	}
	
	public void setJwt(String jwt) {
		Jwt = jwt;
	}
	
	public boolean isAuth() {
		return isAuth;
	}
	
	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
	
	@Override
	public String toString() {
		return "AuthResponse [Jwt=" + Jwt + ", isAuth=" + isAuth + "]";
	}
	
	
}
