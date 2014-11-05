package com.shoudle.ui;

import com.shoudle.im.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

	private static final int GO_HOME = 100;
	private static final int GO_LOGIN = 200;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		mHandler.sendEmptyMessageDelayed(GO_LOGIN, 2000);
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
