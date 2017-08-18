package com.example.notificationex;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {
    static final int NOTI_ID = 101;
    NotificationManager mNotiMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNotiMgr = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
    }

    public void onBtnNoti1() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

        Notification noti = new Notification.Builder(this)
            .setSmallIcon(R.drawable.ic_launcher)
            .setTicker("New Message1")
            .setContentTitle("Noti - Sound")
            .setContentText("Self Activity")
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pIntent)
            .build();
        
        noti.defaults |= Notification.DEFAULT_SOUND;
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        mNotiMgr.notify(100, noti);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnNoti1 :
            //onBtnNoti1();
            v.postDelayed(new Runnable() { 
                public void run() { onBtnNoti1(); }
            }, 5000);
            break;
        case R.id.btnNoti2 :
            v.postDelayed(new Runnable() { 
                public void run() { onBtnNoti2(); }
            }, 5000);
            break;
        }
    }

    public void onBtnNoti2() {
        Intent intent = new Intent(this, NotiStopActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(
                MainActivity.this, 0, intent, 0);
        Notification noti = new Notification.Builder(this)
            .setSmallIcon(R.drawable.ic_launcher)
            .setTicker("New Message2")
            .setContentTitle("Noti - All")
            .setContentText("Other Activity")
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pIntent)
            .build();
        noti.defaults |= Notification.DEFAULT_ALL;
        noti.flags |= Notification.FLAG_INSISTENT;
        mNotiMgr.notify(NOTI_ID, noti);
    }

}
