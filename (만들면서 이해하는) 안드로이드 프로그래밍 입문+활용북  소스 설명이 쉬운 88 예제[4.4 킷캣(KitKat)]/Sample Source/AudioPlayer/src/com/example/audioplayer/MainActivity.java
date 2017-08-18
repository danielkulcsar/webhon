package com.example.audioplayer;

import java.io.*;

import android.app.*;
import android.media.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    MediaPlayer mPlayer = null;
    TextView mTextMessage;
    RadioGroup mRadioGroupAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);

        mRadioGroupAudio 
                = (RadioGroup)findViewById(R.id.radioGroupAudio);
        mRadioGroupAudio.check(R.id.radioInApp);
        onRadioInApp();
    }

    public void onRadioInApp() {
        mTextMessage.setText("'InApp' is Selected");
        deletePlayer();
        mPlayer = MediaPlayer.create(this, R.raw.morning);
        mPlayer.setOnCompletionListener(mCompleteListener);
    }

    public void onRadioSdCard() {
        deletePlayer();
        String sdRootPath = 
                Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = sdRootPath + "/" + "october.mp3";
        LoadMedia(filePath);

        mTextMessage.setText("'SD Card' is Selected");
    }

    public void onRadioWeb() {
        deletePlayer();
        String url = "http://ujinnie.com.ne.kr/pop/boardwalk.wma";
        LoadMedia(url);

        mTextMessage.setText("'Web' is Selected");
    }

    public void onRadioClickId(int id) {
        switch( id ) {
        case R.id.radioInApp :
            onRadioInApp();
            break;
        case R.id.radioSdCard :
            onRadioSdCard();
            break;
        case R.id.radioWeb :
            onRadioWeb();
            break;
        }
    }

    public void onRadioClick(View v) {
        onRadioClickId( v.getId() );
    }

    public void loadAduioSource() {
    	int id = mRadioGroupAudio.getCheckedRadioButtonId();
    	onRadioClickId( id );
    }

    public void deletePlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

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
             loadAduioSource();
             break;
         }
    }

    public boolean LoadMedia(String filePath) {
        try {
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(filePath);
            mPlayer.setOnCompletionListener(mCompleteListener);
        } catch (IOException e) {
            return false;
        }

        try {
            mPlayer.prepare();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    MediaPlayer.OnCompletionListener mCompleteListener =
            new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer arg0) {
            mTextMessage.setText("Play Ended!");
        }
    };

}
