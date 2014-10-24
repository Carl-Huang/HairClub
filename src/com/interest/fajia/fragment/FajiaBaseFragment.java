package com.interest.fajia.fragment;

import com.example.fajia.R;
import com.interest.fajia.ui.FajiaApplication;
import com.interest.fajia.util.HttpUrl;
import com.interest.framework.BaseFragment;

public abstract class FajiaBaseFragment extends BaseFragment implements HttpUrl {

	@Override
	public int getBackGroundId() {
		// TODO Auto-generated method stub
		return R.color.bg;
	}
	@Override
	public FajiaApplication getBaseApplication() {
		// TODO Auto-generated method stub
		return (FajiaApplication) baseactivity.getBaseApplication();
	}

	
}
