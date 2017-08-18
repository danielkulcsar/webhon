package com.example.notificationex;

import android.app.*;
import android.os.*;
import android.view.*;

public class NotiStopActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noti_stop);
	    NotificationManager notiMgr = 
	            (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	    notiMgr.cancel(MainActivity.NOTI_ID);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.noti_stop, menu);
		return true;
	}

}
