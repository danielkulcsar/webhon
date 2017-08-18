package com.example.brsend;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnSend : {
            Intent intent = new Intent();
            intent.setAction("ACTION_USER_CUSTOM");
            intent.putExtra("data1", "BR Success");
            sendBroadcast(intent);
            break;
        }
        }
    }
    
}
