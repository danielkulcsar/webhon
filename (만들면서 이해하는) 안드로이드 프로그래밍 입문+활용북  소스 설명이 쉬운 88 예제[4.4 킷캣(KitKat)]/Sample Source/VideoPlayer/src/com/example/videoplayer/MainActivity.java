package com.example.videoplayer;

import java.io.*;

import android.app.*;
import android.media.*;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity 
        implements SurfaceHolder.Callback, OnPreparedListener {
    SurfaceHolder mSh;
    MediaPlayer mPlayer;
    boolean mFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView sv = (SurfaceView)findViewById(R.id.svVideo);
        mSh = sv.getHolder();
        mSh.addCallback(this);
    }

    public void loadVideoSource() {
        String sdRootPath = 
                Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = sdRootPath + "/" + "2011-07-02.mp4";

        try { 
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(filePath);
            mPlayer.setDisplay(mSh);
            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            return;
        }
    }

    public void onPrepared(MediaPlayer mp) {
        if( mFirst ) {
            mFirst = false;
            mPlayer.start();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        loadVideoSource();
        mSh.setFixedSize(mPlayer.getVideoWidth(), mPlayer.getVideoHeight());
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    public void surfaceDestroyed(SurfaceHolder holder) {}

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonPlay :
            mPlayer.start();
            break;
        case R.id.buttonPause :
            mPlayer.pause();
            break;
        case R.id.buttonStop :
            deletePlayer();
            loadVideoSource();
            break;
        }
    }

    public void deletePlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

}
