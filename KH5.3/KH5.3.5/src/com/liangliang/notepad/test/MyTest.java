package com.liangliang.notepad.test;

import java.util.*;

import com.liangliang.notepad.db.MySqlDao;

import android.content.ContentValues;
import android.test.AndroidTestCase;
import android.text.format.Time;
import android.util.Log;

public class MyTest extends AndroidTestCase {

	private static final String TAG = "MyTest";

	public MyTest() {
		// TODO Auto-generated constructor stub
	}

	// 时间测试
	public void showCurrentTime() {
		Time t = new Time();
		t.setToNow();
		Log.i(TAG, t.year + "/" + (t.month + 1) + "/" + t.monthDay);
		Log.i(TAG, t.hour + ":" + t.minute + ":" + t.second);
	}

	public void Count() {
		MySqlDao dao = new MySqlDao(getContext());
		int count = dao.CountDiarys(null,null);
		Log.i(TAG, "-->>总条目数为：" + count);
	}

	public void List() {
		MySqlDao dao = new MySqlDao(getContext());
		java.util.List<Map<String, String>> list = dao.listDiarys(null,null);
		Log.i(TAG, list.toString());
	}

	public void showCurrent() {
		MySqlDao dao = new MySqlDao(getContext());
		Map<String, String> map = dao.showDiary("id=?", new String[] { "4" });
		System.out.println("--->>" + map.toString());
	}

	public void delete() {
		MySqlDao dao = new MySqlDao(getContext());
		dao.deleteDiary("id=?", new String[] { "1" });
	}

	public void update() {
		MySqlDao dao = new MySqlDao(getContext());
		ContentValues values = new ContentValues();
		values.put("title", "liangliang");
		dao.updateDiary(values, "id=?", new String[] { "2" });
	}

	public void search() {
		MySqlDao dao = new MySqlDao(getContext());

		List<Map<String, String>> list = dao.searchDiary("content like ?",
				new String[] { "%哪里%" });
		System.out.println("--->>" + list.toString());

	}

	public void search1() {
		MySqlDao dao = new MySqlDao(getContext());

		List<Map<String, String>> list = dao.searchDiary("diary_date like ?",
				new String[] { "%2014%" });
		System.out.println("--->>" + list.toString());
	}
}
