package com.liangliang.notepad.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlOpenHelper extends SQLiteOpenHelper {

	private final static String name = "notepad.db";
	private final static int version = 1;

	public MySqlOpenHelper(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	public MySqlOpenHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		String sql = "create table diary(id integer primary key autoincrement, title varchar(20),content varchar(20),diary_date varchar(20),diary_time varchar(20))";
		database.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
