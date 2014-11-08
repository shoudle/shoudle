package com.shoudle.ui;

import com.mr.shoudle.R;
import com.shoudle.ui.fragment.FoundFragment;
import com.shoudle.ui.fragment.MessageFragment;
import com.shoudle.ui.fragment.ProfileFragment;
import com.shoudle.ui.fragment.ShoudleFragment;

import cn.shoudle.util.NetWorkUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	private ShoudleFragment shoudleFragment;
	private FoundFragment foundFragment;
	private MessageFragment messageFragment;
	private ProfileFragment profileFragment;
	private Button[] mTabs;
	private String[] mTitle;
	private Fragment[] fragments;
	private int  index;
	private int currentTabIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(!NetWorkUtil.isNetworkAvailable(this)){
			ShowToast(R.string.network_tips);
		}
		
		initView();
		initTab();
	}

	private void initView(){
		mTabs = new Button[4];
		mTabs[0] = (Button) findViewById(R.id.btn_shoudle);
		mTabs[1] = (Button) findViewById(R.id.btn_found);
		mTabs[2] = (Button) findViewById(R.id.btn_message);
		mTabs[3] = (Button) findViewById(R.id.btn_profile);

		//把第一个tab设为选中状态
		mTabs[0].setSelected(true);
	}
	
	private void initTab(){
		shoudleFragment = new ShoudleFragment();
		foundFragment = new FoundFragment();
		messageFragment = new MessageFragment();
		profileFragment=new ProfileFragment();
		
		fragments = new Fragment[] {shoudleFragment, foundFragment, messageFragment,profileFragment };
		mTitle=new String []{getString(R.string.main_tab_shoudle),getString(R.string.main_tab_found),
				getString(R.string.main_tab_message),getString(R.string.main_tab_profile)};
		
		// 添加显示第一个fragment
		FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
		
		fragmentTransaction.add(R.id.fragment_container, shoudleFragment)
		.add(R.id.fragment_container, foundFragment).add(R.id.fragment_container, messageFragment)
		.add(R.id.fragment_container, profileFragment);
		
		fragmentTransaction.show(shoudleFragment).hide(foundFragment).hide(messageFragment).hide(profileFragment);
		fragmentTransaction.commit();
	}

	/**
	 * 切换Tab;
	 * @param view
	 */
	public void onTabSelect(View view) {
		
		switch (view.getId()) {
		case R.id.btn_shoudle:
			index = 0;
			break;
		case R.id.btn_found:
			index = 1;
			
			break;
		case R.id.btn_message:
			index = 2;
			break;
		case R.id.btn_profile:
			index=3;
			break;
		}
		if (currentTabIndex != index) {
			FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
			trx.hide(fragments[currentTabIndex]);
			if (!fragments[index].isAdded()) {
				trx.add(R.id.fragment_container, fragments[index]);
			}
			trx.show(fragments[index]).commit();
		}
		mTabs[currentTabIndex].setSelected(false);
		//把当前tab设为选中状态
		mTabs[index].setSelected(true);
		currentTabIndex = index;
		getActionBar().setTitle(mTitle[index]);
	}
}
