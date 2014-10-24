package com.interest.fajia.fragment;

import com.example.fajia.R;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonalFragment extends FajiaBaseFragment implements
		OnClickListener{

	private LinearLayout changePassword;
	private LinearLayout myBalance;
	private TextView changePassword_tv;
	private ImageView changePassword_line;
	private ImageView changePassword_ImageView;
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setLeftCustomView(R.drawable.back_bright, this);
		setRightCustomView(R.drawable.setting_bright, this);
		changePassword = (LinearLayout) getView(R.id.changePassword);
		changePassword.setOnClickListener(this);
		changePassword_tv = (TextView) getView(R.id.changePassword_tv);
		changePassword_line =(ImageView) getView(R .id.changePassword_line);
		changePassword_ImageView = (ImageView) getView(R.id.changePassword_image);
		myBalance =(LinearLayout) getView(R.id.myBalance);
		myBalance.setOnClickListener(this);
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return getResources().getString(R.string.PERSONAL);
	}

	@Override
	protected int getViewLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_personal;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.changePassword:
			changePassword_tv.setTextColor(getResources().getColor(R.color.bg));
			changePassword_line.setVisibility(View.VISIBLE);
			changePassword_ImageView.setBackgroundResource(R.drawable.password_dark);
			Log.v("click", "changed");
			break;

		case R.id.myBalance:
			baseactivity.add(LoginFragment.class);
		default:
			break;
		}
	}
}

