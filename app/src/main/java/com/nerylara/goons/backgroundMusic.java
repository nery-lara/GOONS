package com.nerylara.goons;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class backgroundMusic extends Service {
    private MediaPlayer musicLoop;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        musicLoop.start();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicLoop = MediaPlayer.create(this,R.raw.music_hiphop);
        musicLoop.setLooping(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicLoop.stop();
        musicLoop.release();
    }
}
