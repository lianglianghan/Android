package com.liangliang.mytirp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	// int color;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 取得GridView对象
		GridView mGridView = (GridView) findViewById(R.id.mygridView);

		mGridView.setAdapter(new MyAdapter(this));

		// color=getResources().getColor(R.color.red);

		// mGridView.setBackgroundColor(Color.BLACK);

		// 事件监听
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(MainActivity.this, "你选择了" + (position + 1) +
				// " 号图片", Toast.LENGTH_SHORT).show();
				// Toast.makeText(MainActivity.this, color+"", 0).show();
				Intent intent;
				switch (position) {

				case 2:
					intent = new Intent(MainActivity.this, CarActivity.class);
					startActivity(intent);
					break;

				case 4:
					intent = new Intent(MainActivity.this, MyTripActivity.class);
					startActivity(intent);
					break;

				case 8:
					intent = new Intent(MainActivity.this,
							DestinationActivity.class);
					startActivity(intent);
					break;

				default:
					Toast.makeText(MainActivity.this, "功能还在开发中,请耐心等待...", 0)
							.show();
					break;
				}
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
