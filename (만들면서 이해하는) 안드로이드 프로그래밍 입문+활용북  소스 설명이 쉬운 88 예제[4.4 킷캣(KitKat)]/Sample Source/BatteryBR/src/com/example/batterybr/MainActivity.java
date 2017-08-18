package com.example.batterybr;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextLevel;
    TextView mTextAction;
    TextView mTextStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextLevel = (TextView)findViewById(R.id.textLevel);
        mTextAction = (TextView)findViewById(R.id.textAction);
        mTextStatus = (TextView)findViewById(R.id.textStatus);

        //Intent intent = registerReceiver(null, 
        	    //new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        //showLevel(intent);

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(mBatteryRecv, filter);
    }
    
    public void showLevel(Intent intent) {
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int percent = (int)((float)level / (float)scale * 100.);
        mTextLevel.setText("Level - " + percent + "%");
    }

    BroadcastReceiver mBatteryRecv = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            showLevel(intent);
            showAction(intent);
            showStatus(intent);
        }
    };

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBatteryRecv);
    }

    public void showAction(Intent intent) {
        String strAction = intent.getAction();

        if( strAction == Intent.ACTION_BATTERY_CHANGED )
            mTextAction.setText("Battery Changed");
        else if( strAction == Intent.ACTION_BATTERY_LOW )
            mTextAction.setText("Battery Low");
        else if( strAction == Intent.ACTION_BATTERY_OKAY )
            mTextAction.setText("Battery OK");
        else if( strAction == Intent.ACTION_POWER_CONNECTED )
            mTextAction.setText("Power Connected");
        else if( strAction == Intent.ACTION_POWER_DISCONNECTED )
            mTextAction.setText("Power Disonnected");
    }

    public void showStatus(Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 
        	    BatteryManager.BATTERY_STATUS_UNKNOWN);
        switch( status ) {
        case BatteryManager.BATTERY_STATUS_CHARGING :
            mTextStatus.setText("Charging");
             break;
        case BatteryManager.BATTERY_STATUS_DISCHARGING :
            mTextStatus.setText("Discharging");
            break;
        case BatteryManager.BATTERY_STATUS_FULL :
            mTextStatus.setText("Full");
            break;
        case BatteryManager.BATTERY_STATUS_NOT_CHARGING :
            mTextStatus.setText("Not Charging");
            break;
        case BatteryManager.BATTERY_STATUS_UNKNOWN :
            mTextStatus.setText("Unknown");
            break;
        }
    }

}
