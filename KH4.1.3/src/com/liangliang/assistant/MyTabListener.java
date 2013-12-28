package com.liangliang.assistant;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.app.ActionBar;

public class MyTabListener implements ActionBar.TabListener {

	
	private final ViewPager mViewPager;

	public MyTabListener(ViewPager mViewPager){
		this.mViewPager=mViewPager;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

		mViewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
