package com.hexin.core.common.session;


public class SessionManagerFactory {
	public static final String SESSION_TYPE_SERVLET = "servlet";
	public static final String SESSION_TYPE_REDIS = "redis";
	private String sessionType;

	public String getSessionType() {
		return sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	
}
