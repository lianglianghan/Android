package com.liangliang.data;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

public class DataService extends Service {

	private int data = 0;

	Handler handler = new Handler();
	Runnable updateThread = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 定期产生随机数
			data = (int) (Math.random() * 10000);
			System.out.println("--data-->>" + data);
			handler.postDelayed(updateThread, 5000);
		}
	};

	public DataService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("--->>>" + "服务被创建了");
		handler.post(updateThread);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private class MyBinder extends Binder implements Iservice {

		@Override
		public int getData() {
			// TODO Auto-generated method stub

			return data;
		}

	}

}
