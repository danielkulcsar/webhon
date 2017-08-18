package com.example.seekbarex;

import android.app.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    SeekBar mSeekBar1;
    LinearLayout mLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSeekBar1 = (SeekBar)findViewById(R.id.SeekBar1);
        mSeekBar1.setOnSeekBarChangeListener (mSeekBarListener);
        mLayout1 = (LinearLayout)findViewById(R.id.layout1);
        setLayoutBackColor();
    }

    public void setLayoutBackColor() {
        int progress = mSeekBar1.getProgress();
        int part = (int)((float)progress / 100. * 255.);
        int color = Color.argb(255, part, part, part);
        mLayout1.setBackgroundColor(color);
    }

    SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.d("Tag", "onProgressChanged - " + progress + " / " + fromUser);
            setLayoutBackColor();
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d("Tag", "onStartTrackingTouch");
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d("Tag", "onStopTrackingTouch");
        }
    };

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnLeft :
            mSeekBar1.incrementProgressBy(-2);
            break;
        case R.id.btnRight :
            mSeekBar1.incrementProgressBy(2);
            break;
        case R.id.btnLeft2 :
            mSeekBar1.incrementSecondaryProgressBy(-2);
            break;
        case R.id.btnRight2 :
            mSeekBar1.incrementSecondaryProgressBy(2);
            break;
        }
    }

}
