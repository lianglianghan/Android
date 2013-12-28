package com.liangliang.assistant;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

	// 用来判断导航抽屉里面选择的是哪一项
	private int drawerlistIndex = 3;
	private final Activity mActivity;

	public int getDrawerlistIndex() {
		return drawerlistIndex;
	}

	public void setDrawerlistIndex(int drawerlistIndex) {
		this.drawerlistIndex = drawerlistIndex;
	}

	public MyViewPagerAdapter(FragmentManager fm, Activity activity) {
		super(fm);
		this.mActivity = activity;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub

		MyFragment f = (MyFragment) super.instantiateItem(container, position);
		Resources mResource = mActivity.getResources();
		// if (drawerlistIndex == 3) {
		// // f.setDescription(description);
		// // f.setPictures(pictures);
		//
		// switch (position) {
		// case 0:
		// f.setDescription(mResource.getStringArray(R.array.surge));
		// //f.setPictures(mResource.getIntArray(R.array.surgepic));
		// f.setPictures(Items.surgepic);
		// break;
		// case 1:
		// f.setDescription(mResource.getStringArray(R.array.hot));
		// //f.setPictures(mResource.getIntArray(R.array.hotpic));
		// f.setPictures(Items.hotpic);
		// break;
		// case 2:
		// f.setDescription(mResource.getStringArray(R.array.latest));
		// //f.setPictures(mResource.getIntArray(R.array.latestpic));
		// f.setPictures(Items.latestpic);
		// break;
		// case 3:
		// f.setDescription(mResource.getStringArray(R.array.good));
		// //f.setPictures(mResource.getIntArray(R.array.goodpic));
		// f.setPictures(Items.goodpic);
		// break;
		// }
		// } else {
		// f.setDescription(description1);
		// f.setPictures(pictures1);
		// }

		switch (drawerlistIndex) {
		case 0:
			f.setDescription(mResource.getStringArray(R.array.recommend));
			f.setPictures(Items.recomendpic);
			break;
		case 1:
			f.setDescription(mResource.getStringArray(R.array.software));
			f.setPictures(Items.sofwarepic);
			break;
		case 2:
			f.setDescription(mResource.getStringArray(R.array.game));
			f.setPictures(Items.gamepic);
			break;
		case 3:
			switch (position) {
			case 0:
				f.setDescription(mResource.getStringArray(R.array.surge));
				f.setPictures(Items.surgepic);
				break;
			case 1:
				f.setDescription(mResource.getStringArray(R.array.hot));
				f.setPictures(Items.hotpic);
				break;
			case 2:
				f.setDescription(mResource.getStringArray(R.array.latest));
				f.setPictures(Items.latestpic);
				break;
			case 3:
				f.setDescription(mResource.getStringArray(R.array.good));
				f.setPictures(Items.goodpic);
				break;
			}
			break;
		case 4:
			f.setDescription(mResource.getStringArray(R.array.management));
			f.setPictures(Items.managementpic);
			break;
		}

		return f;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return PagerAdapter.POSITION_NONE;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub

		MyFragment mFragment = new MyFragment();
		return mFragment;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

}
