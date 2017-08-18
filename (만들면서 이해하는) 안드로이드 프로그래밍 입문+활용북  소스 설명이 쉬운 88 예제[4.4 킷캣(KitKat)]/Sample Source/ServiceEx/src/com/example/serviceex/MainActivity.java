package com.example.serviceex;

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
        Intent intent = new Intent(this, ServiceRun.class);
        intent.putExtra("interver", 3000);

        switch( v.getId() ) {
        case R.id.btnStartService : {
        	startService(intent);
            break;
        }
        case R.id.btnStopService : {
        	stopService(intent);
            break;
        }
        }
    }

}
