package com.liangliang.notepad;

import java.util.List;
import java.util.Map;

import com.liangliang.notepad.db.MySqlDao;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter {

	private static final String TAG = "ListViewAdapter";
	private Context context = null;
	private MySqlDao mySqlDao = null;
	private List<Map<String, String>> list = null;
	private Map<String, String> map = null;
	private Activity mActivity;
	private TextView diary_count_tv;
	private String selection = null;
	private String[] selectionArgs = null;
	
	
	public void setSelection(String selection,String[] selectionArgs){
		this.selection = selection;
		this.selectionArgs = selectionArgs;
	}
	
	public ListViewAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		mySqlDao = new MySqlDao(context);
		list = mySqlDao.listDiarys(selection,selectionArgs);
	}

	public ListViewAdapter(Context context, Activity activity) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.mActivity = activity;
		mySqlDao = new MySqlDao(context);
		list = mySqlDao.listDiarys(selection,selectionArgs);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return mySqlDao.CountDiarys(selection,selectionArgs);
	}

	@Override
	public Object getItem(int positon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int positon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		map = list.get(position);
		int select_id = Integer.parseInt(map.get("id"));
		ViewHolder holder = null;
		MyListViewListener myListViewListener = null;

		myListViewListener = new MyListViewListener(position, select_id,
				context, mActivity);

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.note_list_items, null);
			holder.date_tv = (TextView) convertView
					.findViewById(R.id.list_date);
			holder.time_tv = (TextView) convertView
					.findViewById(R.id.list_time);
			holder.title_tv = (TextView) convertView
					.findViewById(R.id.list_title);
			holder.content_tv = (TextView) convertView
					.findViewById(R.id.list_content);
			holder.modify_bt = (ImageButton) convertView
					.findViewById(R.id.list_modify);
			holder.delete_bt = (ImageButton) convertView
					.findViewById(R.id.list_delete);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.date_tv.setText(map.get("diary_date"));

		holder.time_tv.setText(map.get("diary_time"));

		holder.title_tv.setText(map.get("title"));

		holder.content_tv.setText(map.get("content"));
		
		Log.i(TAG, "--->>" + map.get("content"));
		
		convertView.setId(select_id);
		Log.i(TAG, "--->>" + convertView.getId());

		convertView.setOnClickListener(myListViewListener);

		holder.modify_bt.setTag("modify");
		holder.modify_bt.setOnClickListener(myListViewListener);

		holder.delete_bt.setTag("delete");
		holder.delete_bt.setOnClickListener(myListViewListener);

		return convertView;
	}

	public void updateMyAdapter() {
		this.list = mySqlDao.listDiarys(selection,selectionArgs);
		this.notifyDataSetChanged();
		diary_count_tv.setText("(" + this.mySqlDao.CountDiarys(selection,selectionArgs) + ")");
	}

	public void setList() {
		this.list = mySqlDao.listDiarys(selection,selectionArgs);
	}

	private final class ViewHolder {
		public TextView date_tv;
		public TextView time_tv;
		public TextView title_tv;
		public TextView content_tv;
		public ImageButton modify_bt;
		public ImageButton delete_bt;

	}

	public TextView getDiary_count_tv() {
		return diary_count_tv;
	}

	public void setDiary_count_tv(TextView diary_count_tv) {
		this.diary_count_tv = diary_count_tv;
		this.diary_count_tv.setText("(" + this.mySqlDao.CountDiarys(selection,selectionArgs) + ")");
	}

}
