package com.shoudle.ui;

import cn.shoudle.util.PreferenceUtils;
import cn.shoudle.v1.SdConstants;

import com.mr.shoudle.R;

import android.os.Bundle;
import android.text.TextUtils;


public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		autoLogin();
		
	}
	/**
	 * 主界面后自动登录;
	 */
	private void autoLogin(){
		
		String userName=PreferenceUtils.getPrefString(this, SdConstants.CONS_ACCOUNT,"");
		String password=PreferenceUtils.getPrefString(this, SdConstants.CONS_PASSWORD,"");
		
		if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)){
			mSdUserManager.login(userName, password,null);
		}
	}
}
