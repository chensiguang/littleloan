package com.hexin.core.common.session;


public interface SessionManager {
	public void setSession(Integer userId, String key, Object obj);
	public Object getSession(Integer userId, String key);
	public void invalidSession(Integer userId);
}
