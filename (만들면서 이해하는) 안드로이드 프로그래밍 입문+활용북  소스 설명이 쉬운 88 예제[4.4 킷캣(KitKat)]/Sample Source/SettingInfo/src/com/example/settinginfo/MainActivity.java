package com.example.settinginfo;

import android.app.*;
import android.content.*;
import android.os.*;
import android.provider.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextMessage;
    ContentResolver mCr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
        mCr = getContentResolver();
        getSettingInfo();
    }

    public void getSettingInfo() {
        int scrBright = Settings.System.getInt(mCr,
                 Settings.System.SCREEN_BRIGHTNESS, 30);
        int scrTimeout = Settings.System.getInt(mCr,
                 Settings.System.SCREEN_OFF_TIMEOUT, 0);
        int scrBrightMode = Settings.System.getInt(mCr,
                 Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
        int airplainMode = Settings.Global.getInt(mCr,
                Settings.Global.AIRPLANE_MODE_ON, 0);
        int soundEffects = Settings.System.getInt(mCr,
                Settings.System.SOUND_EFFECTS_ENABLED, 0);
        int wifiOn = Settings.Global.getInt(mCr, Settings.Global.WIFI_ON, 0);
        int bluetoothOn = Settings.Global.getInt(mCr,
                Settings.Global.BLUETOOTH_ON, 0);

        String strMessage = "Screen Brightness: " + scrBright
                + "\nScreen Off Timeout: " + scrTimeout
                + "\nScreen Brightness Mode: " + scrBrightMode
                + "\nAirplain Mode: " + airplainMode
                + "\nSound Effects Enabled: " + soundEffects
                + "\nWifi On: " + wifiOn
                + "\nBluetooth On: " + bluetoothOn;

        mTextMessage.setText(strMessage);
    }

    public void putSettingInfo1() {
        Settings.System.putInt(mCr, Settings.System.SCREEN_OFF_TIMEOUT, 600000);
        Settings.System.putInt(mCr, Settings.System.SCREEN_BRIGHTNESS_MODE, 1);
    }

    public void putSettingInfo2() {
        Settings.System.putInt(mCr, Settings.System.SCREEN_OFF_TIMEOUT, 15000);
        Settings.System.putInt(mCr, Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnSetting1 :
            putSettingInfo1();
            getSettingInfo();
            break;
        case R.id.btnSetting2 :
            putSettingInfo2();
            getSettingInfo();
            break;
        }
    }

}
