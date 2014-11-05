package com.shoudle.ui;

import cn.shoudle.listener.SaveListener;
import cn.shoudle.util.SdLog;

import com.shoudle.im.R;
import com.shoudle.util.NetWorkUtils;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity implements OnClickListener{

	private EditText et_username, et_password;
	private Button btn_login;
	private TextView btn_register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		init();
	}
	
	private void init() {
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_register = (TextView) findViewById(R.id.btn_register);
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btn_register) {
			//Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			//startActivity(intent);
		} else {
			boolean isNetConnected = NetWorkUtils.isNetworkAvailable(this);
			if(!isNetConnected){
				ShowToast(R.string.network_tips);
				return;
			}
			login();
		}
	}
	
	/**
	 * 登录用户;
	 */
	private void login(){
		String name = et_username.getText().toString();
		String password = et_password.getText().toString();

		if (TextUtils.isEmpty(name)) {
			ShowToast(R.string.error_username_null);
			return;
		}

		if (TextUtils.isEmpty(password)) {
			ShowToast(R.string.error_password_null);
			return;
		}

		final ProgressDialog progress = new ProgressDialog(
				LoginActivity.this);
		progress.setMessage("正在登陆...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		mSdUserManager.login(name, password, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						progress.setMessage("正在获取好友列表...");
					}
				});
				progress.dismiss();
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onFailure(String arg0) {
				progress.dismiss();
				SdLog.i(arg0);
				ShowToast(arg0);
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
