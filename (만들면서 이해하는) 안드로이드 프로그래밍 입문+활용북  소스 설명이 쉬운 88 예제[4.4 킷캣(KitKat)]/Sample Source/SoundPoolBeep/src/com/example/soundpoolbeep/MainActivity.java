package com.example.soundpoolbeep;

import android.app.*;
import android.media.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {
    SoundPool mSoundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0); 
    }

    public void playSystemBeep() {
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        am.playSoundEffect(AudioManager.FX_FOCUS_NAVIGATION_RIGHT);
    }

    public void onClick(View v) {
        int soundId = 0;
    	
        switch( v.getId() ) {
        case R.id.buttonSystemBeep :
            playSystemBeep();
            break;
        case R.id.buttonNotify :
            soundId = mSoundPool.load(this, R.raw.notify, 1);
            playSoundPool(soundId);
            break;
        case R.id.buttonKnock :
            soundId = mSoundPool.load(this, R.raw.knock, 1);
            playSoundPool(soundId);
            break;
        case R.id.buttonGabage :
            soundId = mSoundPool.load(this, R.raw.gabage, 1);
            playSoundPool(soundId);
            break;
        case R.id.buttonError :
            soundId = mSoundPool.load(this, R.raw.error, 1);
            playSoundPool(soundId);
            break;
        }
    }

    public void playSoundPool(int soundId) {
        mSoundPool.play(soundId, 1, 1, 0, 0, 1);
    }

}
