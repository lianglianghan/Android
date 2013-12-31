package com.liangliang.assistant;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private MyViewPagerAdapter mViewPagerAdapter;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerLeftList;

	// ��������һ��String�������xml�ļ���
	private String[] drawerTitles, list, list1, list2, list3;
	private Resources mResources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigationdrawerviewpager);
		
		//��ȡ��Դ���õ���Ӧ������
		mResources = this.getResources();
		drawerTitles=mResources.getStringArray(R.array.drawerlists);
		list=mResources.getStringArray(R.array.actionBarTab);
		list1=mResources.getStringArray(R.array.actionBarTab1);
		list2=mResources.getStringArray(R.array.actionBarTab2);
		list3=mResources.getStringArray(R.array.actionBarTab3);
		
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
		//��õ������������listview�������������Լ���Ӧ�ĵ���¼�
		mDrawerLeftList = (ListView) findViewById(R.id.drawerLeftList);
		mDrawerLeftList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, R.id.drawer_tv, drawerTitles));
		// mDrawerLeftList.setOnItemClickListener(new
		// DrawerItemClickListener());
		mDrawerLeftList.setOnItemClickListener(new DrawerItemClickListener());

		//���viewpager�������������Լ���Ӧ�Ĵ����¼�
		mViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
				this);
		// mViewPagerAdapter.setMyIndex(0);

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(mViewPagerAdapter);
		mViewPager.setOnPageChangeListener(new SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				getActionBar().setSelectedNavigationItem(position);
			}
		});

		//actionbar����ش���
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("����");

		Tab tab;
		
		// ��actionbar�����tab
		for (int i = 0; i < 4; i++) {
			tab = actionBar.newTab();
			tab.setText(list2[i]);
			tab.setTabListener(new MyTabListener(mViewPager));
			actionBar.addTab(tab);
		}

		actionBar.setSelectedNavigationItem(1);

	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			selectItem(position);
		}

	}

	private void selectItem(int position) {
		// ���������������viewpager
		getActionBar().setTitle(
				mResources.getStringArray(R.array.drawerlists)[position]);
		String[] temp = null;
		switch (position) {
		case 0:
			temp = list;
			break;
		case 1:
		case 2:
			temp = list1;
			break;
		case 3:
			temp = list2;
			break;
		case 4:
			temp = list3;
			break;
		}
		mViewPagerAdapter.setDrawerlistIndex(position);
		mViewPagerAdapter.notifyDataSetChanged();
		ActionBar tempActionBar = getActionBar();
		for (int i = 0; i < 4; i++) {
			tempActionBar.getTabAt(i).setText(temp[i]);
		}
		mDrawerLeftList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerLeftList);
	}

}
