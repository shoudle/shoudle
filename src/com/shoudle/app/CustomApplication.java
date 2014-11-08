package com.shoudle.app;

import cn.shoudle.im.v1.SdChat;
import android.app.Application;

public class CustomApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		
		SdChat.DEBUG_MODE=true;
		SdChat.getInstance(this).init();
	}
	
	/**
	 * 退出时调用;
	 */
	public void logout() {
		SdChat.getInstance(this).destory();
	}
}
