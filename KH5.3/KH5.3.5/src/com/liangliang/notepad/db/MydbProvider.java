package com.liangliang.notepad.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.preference.PreferenceActivity.Header;

public class MydbProvider extends ContentProvider {

	private MySqlOpenHelper helper;
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static String authority = "com.liangliang.notepad.diaryProvider";

	private static final int INSERT = 1;
	private static final int DELETE = 2;
	private static final int UPDATE = 3;
	private static final int QUERY = 4;
	static {
		matcher.addURI(authority, "insert", INSERT);
		matcher.addURI(authority, "delete", DELETE);
		matcher.addURI(authority, "update", UPDATE);
		matcher.addURI(authority, "query", QUERY);
	}

	public MydbProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		if (matcher.match(uri) == DELETE) {
			SQLiteDatabase db = helper.getWritableDatabase();
			db.delete("diary", selection, selectionArgs);
		} else {
			throw new IllegalArgumentException("路径不匹配，不能执行删除操作");
		}
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		if (matcher.match(uri) == INSERT) {
			SQLiteDatabase db = helper.getWritableDatabase();
			long id = db.insert("diary", null, values);
			System.out.println("-->>"+id);
		} else {
			throw new IllegalArgumentException("路径不匹配，不能执行插入操作");
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		helper = new MySqlOpenHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		if (matcher.match(uri) == QUERY) {
			SQLiteDatabase db = helper.getReadableDatabase();
			Cursor cursor = db.query("diary", projection, selection,
					selectionArgs, null, null, null);
			return cursor;
		} else {
			throw new IllegalArgumentException("路径不匹配，无法进行查询操作");
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		if(matcher.match(uri)==UPDATE){
			SQLiteDatabase db = helper.getWritableDatabase();
			db.update("diary", values, selection, selectionArgs);
		}else{
			throw new IllegalArgumentException("路径不匹配，无法进行删除操作");
		}
		return 0;
	}

}
