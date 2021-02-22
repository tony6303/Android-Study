package com.example.musicapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // View
    private ImageView btn_play_stop;
    private SeekBar seekBar;
    private TextView tvTime;

    // 음악 관련
    private int isPlaying = -1; // 1은 음악재생, -1은 음악멈춤
    private MyService myService;
    private MediaPlayer mp;

    // 쓰레드 관련
    Handler handler = new Handler();
    private Thread uiHandleThread;
    private boolean threadStatus = false;

    //BindService 만들때 필수
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 서비스가 가지고 있는 binder를 전달 받기 , 다운캐스팅
            MyService.LocalBinder mb = (MyService.LocalBinder) service;
            myService = mb.getService();
            mp = myService.getMediaPlayer();
            seekBarInit();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mp.stop();
            mp.release();
        }
    };

    public void seekBarInit(){
        seekBar.setMax(mp.getDuration());
        seekBar.setProgress(0);
    }

    public void musicStart(){
        mp.start();
        btn_play_stop.setImageResource(R.drawable.ic_pause);
    }

    public void musicPause(){
        mp.pause();
        btn_play_stop.setImageResource(R.drawable.ic_play);
    }

    public void musicStop(){ // progress가 max에 도달했을 때
        mp.seekTo(0);
        btn_play_stop.setImageResource(R.drawable.ic_play);
        seekBar.setProgress(0);
        threadStatus = true;
        isPlaying = -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play_stop = findViewById(R.id.btn_play_stop);
        seekBar = findViewById(R.id.seekBar);
        tvTime = findViewById(R.id.tv_time);

        // 서비스 바인딩 하기
        Intent musicIntent = new Intent(getApplicationContext(), MyService.class);
        bindService(musicIntent, connection, BIND_AUTO_CREATE);

        btn_play_stop.setOnClickListener(v -> {
            isPlaying = isPlaying * -1;
            if (isPlaying == 1){
                musicStart();
            }else{
                musicPause();
            }

            new Thread(new Runnable() {
                @Override
                public void run() { //run start
                    while(isPlaying == 1){
                        handler.post(new Runnable() { // 앞서 생성한 Runnable 객체를 수신 측 스레드로 보내는 것
                            @Override
                            public void run() { // run start
                                seekBar.setProgress(mp.getCurrentPosition());
                                if (mp.getCurrentPosition() >= mp.getDuration()) {
                                    musicStop();
                                }
                            } // run end
                        });
                        try {
                            Thread.sleep(1000);
                            if(threadStatus){
                                uiHandleThread.interrupt();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } // run end
            }).start();
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 유저가 SeekBar를 클릭할 때
                if (fromUser) {
                    mp.seekTo(progress);
                }

                int m = progress / 60000;
                int s = (progress % 60000) / 1000;
                String strTime = String.format("%02d:%02d", m, s);
                tvTime.setText(strTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}