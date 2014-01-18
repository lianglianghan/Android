package com.liangliang.providertest.test;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

public class DiaryProviderTest extends AndroidTestCase {

	public DiaryProviderTest() {
		// TODO Auto-generated constructor stub
	}

	public void insertTest() {
		ContentResolver resolver = getContext().getContentResolver();
		Uri url = Uri.parse("content://com.liangliang.notepad.diaryProvider/insert");
		ContentValues values = new ContentValues();
		values.put("title", "provider_test");
		values.put("content", "你好，这是用来测试备忘录内容提供者的插入方法");
		resolver.insert(url, values);
	}

	public void deleteTest() {
		ContentResolver resolver = getContext().getContentResolver();
		Uri url = Uri.parse("content://com.liangliang.notepad.diaryProvider/delete");
		resolver.delete(url, "title=?", new String[] { "provider_test" });
	}

	public void updateTest() {
		ContentResolver resolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.liangliang.notepad.diaryProvider/update");
		ContentValues values = new ContentValues();
		values.put("content", "你好，这是用来测试内容提供者的更新代码update");
		resolver.update(uri, values, "title=?",
				new String[] { "provider_test" });
	}

	public void queryTest() {
		Cursor cursor = null;
		ContentResolver resolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.liangliang.notepad.diaryProvider/query");
		cursor = resolver.query(uri, null, null, null, null);
		int col_len = cursor.getColumnCount();
		while(cursor.moveToNext()){
			Map<String, String> map = new HashMap<String, String>();
			for(int i=0;i<col_len;i++){
				String col_name = cursor.getColumnName(i);
				String col_value = cursor.getString(cursor.getColumnIndex(col_name));
				map.put(col_name, col_value);
			}
			System.out.println("--->>"+map.toString());
		}
		
		cursor.close();

	}

}
