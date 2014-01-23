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

		// ��ð�������
		PackageManager pm = getContext().getPackageManager();
		List<ResolveInfo> packs = pm.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (ResolveInfo pi : packs) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("icon", pi.activityInfo.loadIcon(pm));// ͼ��
			map.put("appName", pi.activityInfo.loadLabel(pm));// Ӧ�ó�������
			//map.put("packageName", pi.activityInfo.packageName);// Ӧ�ó������

			list.add(map);
		}

		System.out.println("--->>" + list.toString());

	}

}
