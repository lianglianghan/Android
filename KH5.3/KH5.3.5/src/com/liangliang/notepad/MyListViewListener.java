package com.liangliang.notepad;

import com.liangliang.notepad.db.MySqlDao;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MyListViewListener implements OnClickListener {

	private static final String TAG = "MyListViewListener";
	private int mpositon;
	private Context mcontext;
	private Activity mActivity;
	private int select_id;
	private static ListViewAdapter adapter;

	public MyListViewListener(int positon, Context context) {
		this.mpositon = positon;
		this.mcontext = context;
	}

	public MyListViewListener(int positon, int id, Context context,
			Activity activity) {
		this.mpositon = positon;
		this.select_id = id;
		this.mcontext = context;
		this.mActivity = activity;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == select_id) {
			Toast.makeText(mcontext, "您选中了某一项", 0).show();
			/*
			 * TextView content_tv = (TextView)
			 * v.findViewById(R.id.list_content); Log.i(TAG,
			 * content_tv.getText().toString()); Log.i(TAG, "content总高度为:" +
			 * content_tv.getLayoutParams().height + "content总行数为：" +
			 * content_tv.getLineCount() + "单行高度为：" +
			 * content_tv.getLineHeight()); if (content_tv.getVisibility() ==
			 * View.GONE) { content_tv.setVisibility(View.VISIBLE); } else {
			 * content_tv.setVisibility(View.GONE); }
			 */
			TextView content_tv = (TextView) v.findViewById(R.id.list_content);
			AlertDialog.Builder builder = new Builder(mcontext);
			builder.setTitle("详细信息");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(content_tv.getText().toString());

			builder.setMessage(stringBuilder.toString());
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
			builder.show();
		} else {
			String tag = (String) v.getTag();
			if (tag == "modify") {
				Log.i(TAG, "-->>" + v.getId());
				Toast.makeText(mcontext, "选中了" + mpositon, 0).show();
				Intent intent = new Intent(mcontext, EidtDiaryActivity.class);
				// intent.putExtra("edit", "modify");
				Bundle bundle = new Bundle();
				bundle.putString("edit", "modify");
				bundle.putInt("id", this.select_id);
				intent.putExtras(bundle);
				mActivity.startActivityForResult(intent, 0);
			} else if (tag == "delete") {
				Toast.makeText(mcontext, "删除", 0).show();
				MySqlDao dao = new MySqlDao(mcontext);
				dao.deleteDiary("id=?", new String[] { this.select_id + "" });
				this.adapter.updateMyAdapter();

			}
		}

	}

	public int getSelect_id() {
		return select_id;
	}

	public void setSelect_id(int select_id) {
		this.select_id = select_id;
	}

	public static ListViewAdapter getAdapter() {
		return adapter;
	}

	public static void setAdapter(ListViewAdapter adapter) {
		MyListViewListener.adapter = adapter;
	}

}
