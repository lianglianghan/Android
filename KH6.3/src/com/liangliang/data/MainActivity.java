package com.liangliang.data;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView mTextView;

	private Iservice mIservice;

	private Myconn myconn;

	private Handler handler = new Handler() {

		// 读取消息，并更新界面
		@Override
		public void handleMessage(Message msg) {
			System.out.println("---msg.what-->>" + msg.what);
			mTextView.setText("当前数据为 : " + msg.what);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextView = (TextView) this.findViewById(R.id.data_tv);
		Intent intent = new Intent(MainActivity.this, DataService.class);
		// 开启service
		startService(intent);
		myconn = new Myconn();
		// 绑定service
		bindService(intent, myconn, BIND_AUTO_CREATE);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		try {
			// 程序退出前，解绑service
			unbindService(myconn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		super.onDestroy();
	}

	private class Myconn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mIservice = (Iservice) service;

			// 定期读取service中的数据
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					System.out.println("--更新ui界面--");
					// mTextView.setText(mIservice.getData());
					System.out.println("--主界面中data->>>" + mIservice.getData());

					handler.sendEmptyMessage(mIservice.getData());

					handler.postDelayed(this, 2000);

				}
			}).start();

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

	}

}
