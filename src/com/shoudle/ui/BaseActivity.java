package com.shoudle.ui;

import cn.shoudle.im.SdUserManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.widget.Toast;

public class BaseActivity extends ActionBarActivity{

	protected SdUserManager mSdUserManager;
	
	private Toast mToast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mSdUserManager=SdUserManager.getInstance(this);
		
	}
	
	public void startAnimActivity(Class<?> cla) {
		this.startActivity(new Intent(this, cla));
	}
	
	public void startAnimActivity(Intent intent) {
		this.startActivity(intent);
	}
	
	public void ShowToast(final int resId) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (mToast == null) {
					mToast = Toast.makeText(BaseActivity.this.getApplicationContext(), resId,
							Toast.LENGTH_LONG);
				} else {
					mToast.setText(resId);
				}
				mToast.show();
			}
		});
	}
	
	public void ShowToast(final String text) {
		if (!TextUtils.isEmpty(text)) {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (mToast == null) {
						mToast = Toast.makeText(getApplicationContext(), text,
								Toast.LENGTH_LONG);
					} else {
						mToast.setText(text);
					}
					mToast.show();
				}
			});
			
		}
	}
	
}
