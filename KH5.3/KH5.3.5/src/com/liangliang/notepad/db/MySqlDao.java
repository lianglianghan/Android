package com.liangliang.notepad.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MySqlDao {

	private MySqlOpenHelper helper = null;

	public MySqlDao(Context context) {
		// TODO Auto-generated constructor stub
		helper = new MySqlOpenHelper(context);
	}

	/**
	 * 按照指定的条件统计相应的记录数
	 * @param selection			where条件
	 * @param selectionArgs		where条件中所需要的参数
	 * @return 查到的记录总数
	 */
	public int CountDiarys( String selection, String[] selectionArgs) {
		int count = 0;

		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = helper.getReadableDatabase();
			cursor = db.query("diary", null, selection, selectionArgs, null, null, null);
			while (cursor.moveToNext()) {
				count++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			cursor.close();
			db.close();
		}

		return count;
	}

	/**
	 * 按照指定的条件列出所有的日记的创建日期、时间和标题
	 * @param selection			where条件
	 * @param selectionArgs		where条件中所需要的参数
	 * @return 各个记录的list集合
	 */
	public List<Map<String, String>> listDiarys( String selection, String[] selectionArgs) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;

		try {
			db = helper.getReadableDatabase();
			String[] columns = new String[] { "diary_date", "diary_time",
					"title", "id", "content" };
			cursor = db.query("diary", columns, selection, selectionArgs, null, null, null);
			int col_len = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				map = new HashMap<String, String>();
				for (int i = 0; i < col_len; i++) {
					String col_name = cursor.getColumnName(i);
					String col_value = cursor.getString(cursor
							.getColumnIndex(col_name));
					map.put(col_name, col_value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			cursor.close();
			db.close();
		}
		return list;
	}

	/**
	 * 显示当前选中的日记
	 * 
	 * @param selection
	 *            where条件
	 * @param selectionArgs
	 *            where条件中的值
	 * @return 记录的map集合
	 */
	public Map<String, String> showDiary(String selection,
			String[] selectionArgs) {
		Map<String, String> map = new HashMap<String, String>();
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = helper.getReadableDatabase();
			String[] columns = new String[] { "title", "content" };
			cursor = db.query("diary", columns, selection, selectionArgs, null,
					null, null);

			int col_len = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				for (int i = 0; i < col_len; i++) {
					String col_name = cursor.getColumnName(i);
					String col_value = cursor.getString(cursor
							.getColumnIndex(col_name));
					map.put(col_name, col_value);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}

		}

		return map;
	}

	/**
	 * 添加新的日记
	 * 
	 * @param values
	 *            需要传入的字段的值
	 */
	public void addNewDiary(ContentValues values) {
		SQLiteDatabase db = null;
		try {
			db = helper.getWritableDatabase();
			db.insert("diary", null, values);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}

		}
	}

	/**
	 * 删除日记
	 * 
	 * @param whereClause
	 *            where条件
	 * @param whereArgs
	 *            where条件中的占位符的值
	 */
	public void deleteDiary(String whereClause, String[] whereArgs) {
		SQLiteDatabase db = null;
		try {
			db = helper.getWritableDatabase();
			db.delete("diary", whereClause, whereArgs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	/**
	 * 更新日记
	 * 
	 * @param values
	 *            需要更新的内容
	 * @param whereClause
	 *            where条件
	 * @param whereArgs
	 *            where条件中的占位符的值
	 */
	public void updateDiary(ContentValues values, String whereClause,
			String[] whereArgs) {
		SQLiteDatabase db = null;

		try {
			db = helper.getWritableDatabase();
			db.update("diary", values, whereClause, whereArgs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	/**
	 * 对日记进行相应的查询（按日期或者时间)
	 * @param selection			where条件
	 * @param selectionArgs		where条件中值
	 * @return					查询结果的list集合
	 */
	public List<Map<String, String>> searchDiary(String selection,
			String[] selectionArgs) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;

		try {
			db = helper.getReadableDatabase();
			String[] columns = new String[] { "diary_date", "diary_time",
					"title", "id", "content" };
			cursor = db.query("diary", columns, selection, selectionArgs, null,
					null, null);
			int col_len = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				map = new HashMap<String, String>();
				for (int i = 0; i < col_len; i++) {
					String col_name = cursor.getColumnName(i);
					String col_value = cursor.getString(cursor
							.getColumnIndex(col_name));
					map.put(col_name, col_value);
				}
				list.add(map);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

		return list;

	}
}
