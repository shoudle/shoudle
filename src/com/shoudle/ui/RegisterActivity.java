package com.shoudle.ui;

import cn.shoudle.listener.SaveListener;
import cn.shoudle.util.NetWorkUtil;
import cn.shoudle.util.SdLog;
import cn.shoudle.im.v1.SdConstants;

import com.mr.shoudle.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends BaseActivity {

	Button btn_register;
	EditText et_username, et_password, et_email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		et_email = (EditText) findViewById(R.id.et_email);

		btn_register = (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				register();
			}
		});
	}
	
	private void register(){
		final String name = et_username.getText().toString();
		final String password = et_password.getText().toString();
		String pwd_again = et_email.getText().toString();
		
		if (TextUtils.isEmpty(name)) {
			ShowToast(R.string.error_username_null);
			return;
		}

		if (TextUtils.isEmpty(password)) {
			ShowToast(R.string.error_password_null);
			return;
		}
		if (!pwd_again.equals(password)) {
			ShowToast(R.string.error_comfirm_password);
			return;
		}
		
		boolean isNetConnected = NetWorkUtil.isNetworkAvailable(this);
		if(!isNetConnected){
			ShowToast(R.string.network_tips);
			return;
		}
		
		final ProgressDialog progress = new ProgressDialog(RegisterActivity.this);
		progress.setMessage("正在注册...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		
		mSdUserManager.register(name, password, new SaveListener() {
			
			@Override
			public void onSuccess() {
				
				progress.dismiss();
				ShowToast("注册成功");
				
				mSdUserManager.login(name, password, null);
				sendBroadcast(new Intent(SdConstants.CONS_ACTION_REGISTER_SUCCESS_FINISH));
				
				Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
			
			@Override
			public void onFailure(String arg0) {
				SdLog.i(arg0);
				ShowToast(arg0);
			}
		});
	}
}
