package com.liangliang.music_player;

import android.media.MediaPlayer;

public interface IService {

	public void PlaySong(int id);

	public void stopSong();

	public void pauseSong();

	public boolean isPlaying();

	public boolean isPaused();

	public int getSongDuration();

	public int getSongCurrentDuration();

}
