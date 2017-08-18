package com.example.brreceive;

import android.content.*;
import android.widget.*;

public class ReceiverUser extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        //String data1 = arg1.getStringExtra("data1");
        //Toast.makeText(arg0, "BR received - " + data1, 
        //	    Toast.LENGTH_SHORT).show();

    	Intent intentRun = new Intent(arg0, MainActivity.class);
        intentRun.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        arg0.startActivity(intentRun);
	}

}
