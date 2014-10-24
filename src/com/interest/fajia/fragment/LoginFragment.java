package com.interest.fajia.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

import com.example.fajia.R;



public class LoginFragment extends FajiaBaseFragment implements OnClickListener,PlatformActionListener{

	private TextView wechat;
	private TextView qq;
	private TextView phone;
	private String type;
	private String openid;
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		wechat = (TextView) getView(R.id.wechat_login);
		qq =(TextView) getView(R.id.qq_login);
		phone =(TextView) getView(R.id.phone_login);
		phone.setOnClickListener(this);
	}

	@Override
	protected int getViewLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_login;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.wechat_login:
			authorize(new Wechat(baseactivity), true);
			break;
		case R.id.qq_login:
			authorize(new QZone(baseactivity), true);
			break;
		case R.id.phone_login:
			baseactivity.add(PersonalFragment.class);
			break;
		default:
			break;
		}
	}
	
	
	private void login(String plat, String userId) {
		String type = "";
		if (plat.equals(Wechat.NAME)) {
			type = "2";
		} else {
			type = "1";
		}
		this.type = type;
		List<Object> list=new ArrayList<Object>();
		list.add(userId);
		list.add(type);
		getData(open_login, list, true);
	}
	private void authorize(Platform plat, boolean isRemove) {
		if (plat == null) {
			return;
		}
		if (plat.equals(Wechat.NAME)) {
			type = "2";
		} else {
			type = "1";
		}
		if (plat.isValid()) {

			if (isRemove) {
				plat.removeAccount();
			} else {
				String userId = plat.getDb().getUserId();
				if (userId != null) {
					openid = userId;
					baseactivity.add(HomeFragment.class);
					return;
				}
			}

		}
		plat.setPlatformActionListener(this);
		// true不使用SSO授权，false使用SSO授权
		plat.SSOSetting(false);
		plat.showUser(null);
	}

	@Override
	public void onCancel(Platform arg0, int arg1) {
		openid = "";
	}

	@Override
	public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
		// TODO Auto-generated method stub
		login(arg0.getName(), arg0.getDb().getUserId());
		openid = arg0.getDb().getUserId();
	}

	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {
		// TODO Auto-generated method stub
		arg0.removeAccount();
		openid = "";
	}
}
