package com.liangliang.mlauncher;

import java.util.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyGridViewAdapter extends BaseAdapter {

	private List<Map<String, Object>> list = null;

	private Context mContext;

	public MyGridViewAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.list = getAppInfo();
	}

	public MyGridViewAdapter(List<Map<String, Object>> list) {
		this.list = list;

	}

	public List<Map<String, Object>> getAppInfo() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		// 显示系统中默认显示app
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);

		PackageManager pm = mContext.getPackageManager();

		List<ResolveInfo> appInfos = pm.queryIntentActivities(intent, 0);

		for (ResolveInfo appInfo : appInfos) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", appInfo.loadIcon(pm));
			map.put("appName", appInfo.loadLabel(pm));
			list.add(map);
		}

		return list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		//System.out.println("--->>>" + position);

		// 填充view的两种方式
		// 1.通过上下文来获得layoutInflater
		LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
		View view = inflater.inflate(R.layout.app_gridviw_item, null);
		// 2.直接调用View.inflate的方法
		// View view = View.inflate(mContext, R.layout.app_gridviw_item, null);

		ImageView app_icon = (ImageView) view.findViewById(R.id.app_icon);
		TextView app_title = (TextView) view.findViewById(R.id.app_title);
		Map<String, Object> map = list.get(position);
		app_icon.setImageDrawable((Drawable) map.get("icon"));
		// app_icon.setImageResource((Integer) map.get("icon"));
		app_title.setText(map.get("appName").toString());
		return view;
	}

}
