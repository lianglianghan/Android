package com.liangliang.music_player;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class PlayerService extends Service {

	private MediaPlayer mediaPlayer;

	public PlayerService() {
		// TODO Auto-generated constructor stub
	}

	// ע�⣺��service������bind����ֻ�ᱻ����һ��
	// ubind����Ҳֻ�����һ��

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("--->>" + "�ɹ����а�");
		return new MyBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("--->>" + "�ɹ������");
		// if (mediaPlayer != null && mediaPlayer.isPlaying()) {
		// // System.out.println("���ڲ���");
		// }
		return super.onUnbind(intent);
	}

	private class MyBinder extends Binder implements IService {

		private boolean paused = false;

		@Override
		public void PlaySong(int id) {
			if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
				if (!paused) {
					mediaPlayer = MediaPlayer.create(getApplicationContext(),
							id);
				} else {
					paused = false;
				}
				mediaPlayer.start();
			}
		}

		@Override
		public void stopSong() {
			// TODO Auto-generated method stub
			if (mediaPlayer != null
					&& (mediaPlayer.isPlaying() || paused == true)) {
				paused = false;
				mediaPlayer.stop();
			}

		}

		@Override
		public boolean isPlaying() {
			// TODO Auto-generated method stub
			boolean flag = false;
			if (mediaPlayer != null && (mediaPlayer.isPlaying())) {
				flag = true;
			}
			return flag;
		}

		@Override
		public int getSongDuration() {
			// TODO Auto-generated method stub
			// if(mediaPlayer!=null){
			// int duration = mediaPlayer.getDuration();
			// }
			// return 0;

			return (mediaPlayer != null) ? mediaPlayer.getDuration() : 0;
		}

		@Override
		public int getSongCurrentDuration() {
			// TODO Auto-generated method stub
			if (mediaPlayer != null
					&& (mediaPlayer.isPlaying() || paused == true)) {
				return mediaPlayer.getCurrentPosition();
			} else {
				return 0;
			}

		}

		@Override
		public void pauseSong() {
			// TODO Auto-generated method stub
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				paused = true;
				mediaPlayer.pause();
			}

		}

		@Override
		public boolean isPaused() {
			// TODO Auto-generated method stub
			return paused;
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		super.onCreate();
		System.out.println("������");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

		super.onDestroy();
		System.out.println("����ֹͣ");

	}

}
