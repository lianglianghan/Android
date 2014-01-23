package com.liangliang.mlauncher;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.GridView;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GridView mGridView;
	private MyGridViewAdapter myGridViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGridView = (GridView) this.findViewById(R.id.app_gv);
		myGridViewAdapter = new MyGridViewAdapter(this);
		mGridView.setAdapter(myGridViewAdapter);

		registerForContextMenu(mGridView);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.start_app:
			Toast.makeText(this, "您点击了打开", 0).show();
			return true;
		case R.id.show_appInfo:
			Toast.makeText(this, "您点击了显示", 0).show();
			return true;
		case R.id.delete_app:
			//Toast.makeText(this, "您点击了删除", 0).show();
			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			Notification notification = new Notification(R.drawable.ulysse,
					"程序已成功删除", System.currentTimeMillis());

			// 自定义的界面
			RemoteViews remoteViews = new RemoteViews(getPackageName(),
					R.layout.mynotification);
			remoteViews.setTextViewText(R.id.notify_title, "删除提醒");
			remoteViews.setTextViewText(R.id.notify_content, "应用程序已成功删除");
			remoteViews
					.setImageViewResource(R.id.notify_img, R.drawable.ulysse);
			notification.contentView = remoteViews;

			// 延期的意图
			Intent intent = new Intent();
			intent.setClass(this, MainActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
					intent, 0);
			notification.contentIntent = pendingIntent;

			//notif的式样
			notification.defaults = Notification.DEFAULT_ALL;
			notification.flags = Notification.FLAG_AUTO_CANCEL;
			
			//显示notification
			nm.notify(0, notification);
			return true;

		default:
			return super.onContextItemSelected(item);
		}
	}

}
