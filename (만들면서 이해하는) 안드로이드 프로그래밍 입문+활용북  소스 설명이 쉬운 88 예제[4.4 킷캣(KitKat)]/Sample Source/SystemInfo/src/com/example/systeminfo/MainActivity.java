package com.example.systeminfo;

import android.app.*;
import android.os.*;
import android.util.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTextMessage = (TextView)findViewById(R.id.textMessage);
        getSystemInfo();
    }

    public void getSystemInfo() {
        String strMessage = "CPU_ABI: " + Build.CPU_ABI
                + "\nBrand: " + Build.BRAND
                + "\nModel: " + Build.MODEL
                + "\nSerial: " + Build.SERIAL
                + "\nVersion: " + Build.VERSION.RELEASE;
        DisplayMetrics disMetrics = getResources().getDisplayMetrics();
        strMessage += "\nDisplay Width: " + disMetrics.widthPixels
                + "\nDisplay Height: " + disMetrics.heightPixels
                + "\nDensityDpi: " + disMetrics.densityDpi
                + "\nHorizental Size: " +
                    (float)disMetrics.widthPixels / (float)disMetrics.densityDpi
                + "\nVertical Size: " + 
                    (float)disMetrics.heightPixels / (float)disMetrics.densityDpi;

        mTextMessage.setText(strMessage);
    }
    
}
