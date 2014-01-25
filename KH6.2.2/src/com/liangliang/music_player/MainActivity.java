package com.liangliang.music_player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class MainActivity extends Activity {

	// private Button mSongPlay;
	// private Button mSongStop;
	private SeekBar mSongProgressBar;
	private Intent intent;
	private MyConn myConn;
	private IService mIService;

	// private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 去除顶部标题栏的图标
		// getActionBar().setDisplayShowHomeEnabled(false);
		// mediaPlayer = MediaPlayer.create(getApplicationContext(),
		// R.raw.test);

		// mSongPlay = (Button) this.findViewById(R.id.song_play);
		// mSongStop = (Button) this.findViewById(R.id.song_stop);
		mSongProgressBar = (SeekBar) this.findViewById(R.id.song_progress);
		// mSongProgressBar.setMax(mediaPlayer.getDuration());

		intent = new Intent(MainActivity.this, PlayerService.class);

		startService(intent);
		myConn = new MyConn();
		bindService(intent, myConn, BIND_AUTO_CREATE);

		System.out.println("--->>" + "主界面创建了");

	}

	Handler handler = new Handler();
	Runnable updateThread = new Runnable() {
		public void run() {
			// 获得歌曲现在播放位置并设置成播放进度条的值
			mSongProgressBar.setProgress(mIService.getSongCurrentDuration());
			// 每次延迟100毫秒再启动线程
			if (mSongProgressBar.getProgress() == mSongProgressBar.getMax()) {

			} else {
				handler.postDelayed(updateThread, 100);
			}

		}
	};

	// 播放音乐
	public void play(View view) {
		mIService.PlaySong(R.raw.test);
		mSongProgressBar.setMax(mIService.getSongDuration());
		handler.post(updateThread);
	}

	// 停止
	public void stop(View view) {
		mIService.stopSong();
		mSongProgressBar.setProgress(0);
		handler.removeCallbacks(updateThread);
	}

	// 暂停
	public void pause(View view) {
		mIService.pauseSong();
		handler.removeCallbacks(updateThread);
	}

	private class MyConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mIService = (IService) service;
			System.out.println("中间代理人返回来了");
			if (mIService.isPlaying() || mIService.isPaused()) {
				System.out
						.println("--->>" + mIService.getSongCurrentDuration());
				mSongProgressBar.setMax(mIService.getSongDuration());
				mSongProgressBar
						.setProgress(mIService.getSongCurrentDuration());

				handler.post(updateThread);
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

	}

	private void showNotification() {
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		@SuppressWarnings("deprecation")
		Notification notification = new Notification(R.drawable.icon, "音乐正在播放",
				System.currentTimeMillis());

		Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, mIntent, 0);

		notification.setLatestEventInfo(getApplicationContext(), "克罗地亚狂想曲",
				"正在播放", pendingIntent);

		// notification.defaults = Notification.STREAM_DEFAULT;
		notification.flags = Notification.FLAG_AUTO_CANCEL;

		nm.notify(0, notification);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			System.out.println("您点击了退出按钮");
			if (mIService.isPlaying()) {
				System.out.println("提示");
				showNotification();
			}
			finish();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		try {
			unbindService(myConn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.onDestroy();
	}

}
