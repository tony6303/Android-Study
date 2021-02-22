package com.example.musicapp3;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    private static final String TAG = "MyService";
    private MediaPlayer mp;
    private final IBinder mBinder = new LocalBinder(); // mBinder 는 LocalBinder 객체의 생성자를 실행해서 만들어진다.
    //

    public MyService() {
    }

    class LocalBinder extends Binder { // 내부클래스의 함수
        MyService getService() {
            return MyService.this; // 머리를 좀 써서? 내 자신을 호출하는 Context 를 만듦
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.sample1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}