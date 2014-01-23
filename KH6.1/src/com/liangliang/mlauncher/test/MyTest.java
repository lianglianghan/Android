package com.liangliang.mlauncher.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.test.AndroidTestCase;

public class MyTest extends AndroidTestCase {

	public MyTest() {
		// TODO Auto-generated constructor stub
	}

	public void HomeTest() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);

		// 获得包管理器
		PackageManager pm = getContext().getPackageManager();
		List<ResolveInfo> packs = pm.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (ResolveInfo pi : packs) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("icon", pi.activityInfo.loadIcon(pm));// 图标
			map.put("appName", pi.activityInfo.loadLabel(pm));// 应用程序名称
			//map.put("packageName", pi.activityInfo.packageName);// 应用程序包名

			list.add(map);
		}

		System.out.println("--->>" + list.toString());

	}

}
