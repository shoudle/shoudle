package com.shoudle.ui;

import com.mr.shoudle.R;

import cn.shoudle.im.SdUserManager;
import cn.shoudle.util.PreferenceUtils;
import cn.shoudle.v1.SdConstants;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

	private static final int GO_HOME = 1;
	private static final int GO_LOGIN = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		String userName=PreferenceUtils.getPrefString(this, SdConstants.CONS_ACCOUNT, "");
		
		if(userName.equals("")||userName==null){
			mHandler.sendEmptyMessageDelayed(GO_LOGIN, 1000);
		}else {
			mHandler.sendEmptyMessageDelayed(GO_HOME,2000);
		}
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				startAnimActivity(MainActivity.class);
				finish();
				break;
			case GO_LOGIN:
				startAnimActivity(LoginActivity.class);
				finish();
				break;
			}
		}
	};
	
	public void startAnimActivity(Class<?> cla) {
		this.startActivity(new Intent(this, cla));
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	};
}
