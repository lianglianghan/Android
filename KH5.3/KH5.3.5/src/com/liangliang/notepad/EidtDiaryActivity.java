package com.liangliang.notepad;

import java.util.Calendar;
import java.util.Map;

import com.liangliang.notepad.db.MySqlDao;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EidtDiaryActivity extends Activity {

	private static final String TAG = "EidtDiaryActivity";
	private EditText title_et;
	private EditText content_et;
	private Button ok_bt;
	private Button cancel_bt;

	private MySqlDao mySqlDao;
	// private Intent intent;
	private String flag;
	private int select_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new);
		this.initComponent();
		mySqlDao = new MySqlDao(this);

		// String getStr = getIntent().getStringExtra("edit");
		Bundle bundle = getIntent().getExtras();
		flag = bundle.getString("edit");
		if (flag.equals("create")) {
			Log.i(TAG, "create");
		} else if (flag.equals("modify")) {
			select_id = bundle.getInt("id");
			Log.i(TAG, "modify" + "--->>" + select_id);
			Map<String, String> map = mySqlDao.showDiary("id=?",
					new String[] { select_id + "" });
			title_et.setText(map.get("title"));
			content_et.setText(map.get("content"));
		}

		ok_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// System.out.println("--->>" + values.toString());
				if (TextUtils.isEmpty(title_et.getText())
						&& TextUtils.isEmpty(content_et.getText())) {
					Toast.makeText(EidtDiaryActivity.this, "标题或内容不能为空!", 0)
							.show();
				} else {
					ContentValues values = new ContentValues();
					values.put("title", title_et.getText().toString());
					values.put("content", content_et.getText().toString());
					Time t = new Time();
					t.setToNow();
					String currentDate = t.year + "/" + (t.month + 1) + "/"
							+ t.monthDay;

					String currentTime = t.hour + ":"
							+ ((t.minute < 10) ? ("0" + t.minute) : (t.minute));
					values.put("diary_date", currentDate);
					values.put("diary_time", currentTime);
					if (flag.equals("create")) {
						mySqlDao.addNewDiary(values);
					} else if (flag.endsWith("modify")) {
						mySqlDao.updateDiary(values, "id=?",
								new String[] { select_id + "" });
					}

					setResult(RESULT_OK);
					finish();
				}

			}
		});

		cancel_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void initComponent() {
		title_et = (EditText) this.findViewById(R.id.title);
		content_et = (EditText) this.findViewById(R.id.content);
		ok_bt = (Button) this.findViewById(R.id.save);
		cancel_bt = (Button) this.findViewById(R.id.cancel);
	}

}
