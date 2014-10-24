package com.interest.fajia.ui;



import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import cn.sharesdk.framework.ShareSDK;

import com.example.fajia.R;
import com.interest.fajia.fragment.HomeFragment;
import com.interest.fajia.fragment.LoginFragment;
import com.interest.fajia.fragment.PersonalFragment;
import com.interest.framework.BaseActivity;
import com.interest.framework.BaseFragmentImpl;

public class MainActivity extends BaseActivity implements OnClickListener{

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitleBg(R.drawable.bg);
		add(PersonalFragment.class);
		ShareSDK.initSDK(this);
	}
	@Override
	public int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_main;
	}

	@Override
	public void getResult(Message msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getParentId() {
		// TODO Auto-generated method stub
		return R.id.content_frame;
	}
	
	public boolean isLeftBack(){
		BaseFragmentImpl impl=getOldFragment();
		if(true){
			return true;
		}
		return false;
	}

}
