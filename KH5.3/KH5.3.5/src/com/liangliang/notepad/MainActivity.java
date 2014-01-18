package com.liangliang.notepad;

import com.liangliang.notepad.db.MySqlDao;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity";
	private ImageButton edit_diary;
	private TextView mDiaryCount;
	private EditText mSearch;
	private RelativeLayout mSearchRL;
	private RelativeLayout mHeader;
	private Button mSearch_bt;
	private ListView mListView;
	// private MySqlDao MySqlDao;
	private ListViewAdapter mListViewAdapter;
	// 搜索的标示符 0---日期搜索 1---内容搜索
	private int search_flag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit_diary = (ImageButton) this.findViewById(R.id.newDiary);
		mDiaryCount = (TextView) this.findViewById(R.id.count_diary);
		mSearchRL = (RelativeLayout) this.findViewById(R.id.search_rl);
		mHeader = (RelativeLayout) this.findViewById(R.id.main_header);
		mSearch = (EditText) this.findViewById(R.id.search_et);
		mSearch_bt = (Button) this.findViewById(R.id.search_bt);
		// MySqlDao = new MySqlDao(this);
		mListView = (ListView) this.findViewById(R.id.note_lv);
		mListViewAdapter = new ListViewAdapter(this, this);
		mListViewAdapter.setDiary_count_tv(mDiaryCount);
		mListView.setAdapter(mListViewAdapter);
		MyListViewListener.setAdapter(mListViewAdapter);

	}

	public void CreateDiary(View view) {
		Intent intent = new Intent(this, EidtDiaryActivity.class);
		// startActivity(intent);
		// intent.putExtra("edit", "create");
		Bundle bundle = new Bundle();
		bundle.putString("edit", "create");
		intent.putExtras(bundle);
		startActivityForResult(intent, 0);
	}

	public void searchDiary(View view) {
		String selection = null;
		String[] selectionArgs = null;
		if (TextUtils.isEmpty(mSearch.getText().toString())) {

		} else {
			switch (search_flag) {
			case 0:
				// 日期搜索
				selection = "diary_date like ?";
				break;
			case 1:
				// 内容搜索
				selection = "content like ?";
				break;
			}
			

			selectionArgs = new String[] { "%" + mSearch.getText().toString()
					+ "%" };
		}
		mListViewAdapter.setSelection(selection, selectionArgs);
		mListViewAdapter.updateMyAdapter();
	}

	// public void updateMyListView() {
	// mListViewAdapter.setList();
	// mListViewAdapter.notifyDataSetChanged();
	// }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			switch (resultCode) {
			case RESULT_OK:
				// mListView.setAdapter(new ListViewAdapter(this));
				// this.updateMyListView();
				mListViewAdapter.updateMyAdapter();
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// return super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mymenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.search_date:
			Toast.makeText(this, "您选择了日期", 0).show();
			if (mSearchRL.getVisibility() == View.GONE) {
				mSearchRL.setVisibility(View.VISIBLE);
				mHeader.setVisibility(View.GONE);

			}
			mSearch.setText("");
			mSearch.setHint("请输入日期");
			mSearch.requestFocus();
			mListViewAdapter.setSelection(null, null);
			mListViewAdapter.updateMyAdapter();
			search_flag = 0;
			return true;
		case R.id.search_content:
			Toast.makeText(this, "您选择了内容", 0).show();
			if (mSearchRL.getVisibility() == View.GONE) {
				mSearchRL.setVisibility(View.VISIBLE);
				mHeader.setVisibility(View.GONE);

			}
			mSearch.setText("");
			mSearch.setHint("请输入要查找的内容");
			mSearch.requestFocus();
			mListViewAdapter.setSelection(null, null);
			mListViewAdapter.updateMyAdapter();
			search_flag = 1;
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	/**
	 * 对返回键进行捕获，从而能够从搜索界面回到正常界面
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mSearchRL.getVisibility() == View.VISIBLE) {
				mSearchRL.setVisibility(View.GONE);
				mHeader.setVisibility(View.VISIBLE);
				mListViewAdapter.setSelection(null, null);
				mListViewAdapter.updateMyAdapter();
				return true;
			} else {
				finish();
				return true;
			}
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}

}
