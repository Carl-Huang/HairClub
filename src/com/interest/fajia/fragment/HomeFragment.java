package com.interest.fajia.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.fajia.R;
import com.interest.fajia.model.Advertisement;
import com.interest.fajia.model.Result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;


public class HomeFragment extends FajiaBaseFragment implements OnClickListener {

	private BannerAdapter viewPaperAdapter = new BannerAdapter();
	private ViewPager bannerVp;// banner页面
	private int PAGESIZE = 3;// 广告页数
	private List<ImageView> views;// banner图片集合
	private static int currentItem = 0;// 当前图片的索引
	private Future<?> future;// 暂停banner自动滑动
	private ScheduledExecutorService scheduledExecutorService;// banner自动滑动
	private List<View> dots;// banner下方的点
	private LinearLayout viewPaper_bottomPoint_layout;
	private List<Advertisement> advertisements = new ArrayList<Advertisement>();
	private TextView vpText;
	private int[] imgIDList={R.drawable.model,R.drawable.model2,R.drawable.model3};;
	private ImageView free;
	@Override
	protected void initView() {
		// TODO Auto-generated method stub

//		List<Object> list=new ArrayList<Object>();
//		getData(advertisement_list, list, true);
		Advertisement ad0 = new Advertisement();
		ad0.setId("0");
		ad0.setText("2014沙宣流行   美髮精剪視頻");
		initViewPager();
		Advertisement ad1 = new Advertisement();
		ad1.setId("1");
		ad1.setText("2015沙宣流行   美髮精剪視頻");
		Advertisement ad2 = new Advertisement();
		ad2.setId("2");
		ad2.setText("2016沙宣流行   美髮精剪視頻");
		advertisements.add(ad0);
		advertisements.add(ad1);
		advertisements.add(ad2);
		vpText.setText(advertisements.get(0).getText());
		free = (ImageView) getView(R.id.free_iv);
		LayoutParams params = (LayoutParams) free.getLayoutParams();
		params.setMargins(50, 50, 50, 50);
		free.setLayoutParams(params);
		
	}

	@Override
	public void onShow() {
		// TODO Auto-generated method stub
		super.onShow();
		setRightCustomView(R.drawable.personal, new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void getResult(Message msg) {
		// TODO Auto-generated method stub
		if (msg.what == advertisement_list) {
			advertisements.clear();
			Result<List<Advertisement>> result = (Result<List<Advertisement>>) msg.obj;
			List<Advertisement> cs = result.getResult();
			if (cs != null) {
				advertisements.addAll(cs);
			}
			PAGESIZE = advertisements.size();
			initViewPager();
		}
	}
	
	@Override
	protected int getViewLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_home;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return getString(R.string.HOMETITLE);
	}
	private void initViewPager() {
		viewPaper_bottomPoint_layout = (LinearLayout) getView(R.id.viewPaper_bottomPoint_layout);
		bannerVp = (ViewPager) getView(R.id.home_bannerVp);
		vpText = (TextView) getView(R.id.vpText);
		// 填充banner图片
		views = new ArrayList<ImageView>();
		for (int i = 0; i < PAGESIZE; i++) {
			ImageView bannerView = new ImageView(baseactivity);
			bannerView.setBackgroundColor(Color.TRANSPARENT);
//			ImageLoader.getInstance().displayImage(
//					advertisements.get(i).getImage(), bannerView);
			bannerView.setBackgroundResource(imgIDList[i]);
			bannerView.setScaleType(ScaleType.FIT_CENTER);
			views.add(bannerView);
		}

		// 填充下方点
		dots = new ArrayList<View>();

		for (int i = 0; i < PAGESIZE; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					10, 10);
			params.leftMargin = 5;
			params.rightMargin = 5;
			View dotV = new View(baseactivity);
			
			dotV.setBackgroundResource(R.drawable.dot2);
			dotV.setLayoutParams(params);
			viewPaper_bottomPoint_layout.addView(dotV);
			dots.add(dotV);
		}

		bannerVp.setAdapter(viewPaperAdapter);
		bannerVp.setCurrentItem(0);
		if (dots.size() > 0) {
			dots.get(0).setBackgroundResource(R.drawable.dot1);
		}

		bannerVp.setOnPageChangeListener(new OnPageChangeListener() {
			private int oldPosition = 0;

			@Override
			public void onPageSelected(int position) {
				position = position % PAGESIZE;
				dots.get(oldPosition).setBackgroundResource(R.drawable.dot2);
				oldPosition = position;
				dots.get(position).setBackgroundResource(R.drawable.dot1);
				vpText.setText(advertisements.get(position).getText());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		
	}

	
	/**
	 * 换行切换任务
	 */
	private class ScrollTask implements Runnable {

		@Override
		public void run() {
			synchronized (bannerVp) {
				if (currentItem >= views.size()) {
					currentItem = 0;
				} else {
					currentItem++;
				}
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		future = scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(),
				1, 2, TimeUnit.SECONDS);
	}

	@Override
	public void onStop() {

		super.onStop();
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
	}
	
	// 回调改变图片
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			currentItem = currentItem % PAGESIZE;
			bannerVp.setCurrentItem(currentItem);// 切换当前显示的图片
			handler.sendEmptyMessageDelayed(0, 2000);
			
		};
	};



	private class BannerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return PAGESIZE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView((ImageView) object);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			final ImageView iv = views.get(position);
			iv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					startActivity(intent);
				}
			});
			((ViewPager) container).addView(iv);
			return views.get(position);
		}

	}

	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
