package com.example.runvibrator;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {
    Vibrator mVib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnVibrate1:
            mVib.vibrate(500);
            break;
        case R.id.btnVibrate2:
            mVib.vibrate(new long[] {100, 50, 200, 50}, 0);
            break;
        case R.id.btnVibrateStop:
            mVib.cancel();
            break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        mVib.cancel();
    }

}
