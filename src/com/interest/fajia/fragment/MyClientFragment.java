package com.interest.fajia.fragment;

import com.example.fajia.R;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MyClientFragment extends FajiaBaseFragment implements OnItemClickListener{

	private ListView myclient;
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		myclient = (ListView) getView(R.id.myclient_list);
		
	}

	@Override
	protected int getViewLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_myclient;
	}

}
