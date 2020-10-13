package com.gupaoedu.live.gupaoedulive.conf;


import com.gupaoedu.live.gupaoedulive.modules.sys.model.SysUser;

public class SysUserContext {
	private static final ThreadLocal<SysUser> USER_HODLER = new ThreadLocal<SysUser>();
    
	public static void setUser(SysUser user){
		USER_HODLER.set(user);
	}
	
	public static void remove(){
		USER_HODLER.remove();
	}
	
	public static SysUser getUser(){
		return USER_HODLER.get();
	}
}