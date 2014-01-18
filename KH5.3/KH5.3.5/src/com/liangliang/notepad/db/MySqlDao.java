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
	 * ����ָ��������ͳ����Ӧ�ļ�¼��
	 * @param selection			where����
	 * @param selectionArgs		where����������Ҫ�Ĳ���
	 * @return �鵽�ļ�¼����
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
	 * ����ָ���������г����е��ռǵĴ������ڡ�ʱ��ͱ���
	 * @param selection			where����
	 * @param selectionArgs		where����������Ҫ�Ĳ���
	 * @return ������¼��list����
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
	 * ��ʾ��ǰѡ�е��ռ�
	 * 
	 * @param selection
	 *            where����
	 * @param selectionArgs
	 *            where�����е�ֵ
	 * @return ��¼��map����
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
	 * ����µ��ռ�
	 * 
	 * @param values
	 *            ��Ҫ������ֶε�ֵ
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
	 * ɾ���ռ�
	 * 
	 * @param whereClause
	 *            where����
	 * @param whereArgs
	 *            where�����е�ռλ����ֵ
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
	 * �����ռ�
	 * 
	 * @param values
	 *            ��Ҫ���µ�����
	 * @param whereClause
	 *            where����
	 * @param whereArgs
	 *            where�����е�ռλ����ֵ
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
	 * ���ռǽ�����Ӧ�Ĳ�ѯ�������ڻ���ʱ��)
	 * @param selection			where����
	 * @param selectionArgs		where������ֵ
	 * @return					��ѯ�����list����
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
