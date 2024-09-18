package com.example.musicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private static final String TAG = "MusicService";
    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        player = MediaPlayer.create(this, R.raw.getup);
        player.setLooping(false);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MusicService 가 종료되었습니다.", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy()");
        player.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "MusicService 가 시작되었습니다.", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart()");
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
