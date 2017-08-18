package com.example.serviceex;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;

public class ServiceRun extends Service {
    int mInterval = 5000;

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d("tag", "Start Service");
        mInterval = intent.getIntExtra("interver", mInterval);
        mTimer.sendEmptyMessageDelayed(0, mInterval);
        return Service.START_STICKY;  
    } 
 
    public void onDestroy(){
        Log.d("Tag", "Stop Service");
        mTimer.removeMessages(0);
    }
    
    Handler mTimer = new Handler() {
        public void handleMessage(Message msg) {
            Log.d("Tag", "Timer");
            mTimer.sendEmptyMessageDelayed(0, mInterval);
        }
    };

}
