package com.example.hardwarekeyinput;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextMessage;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch( keyCode ) {
        case KeyEvent.KEYCODE_BACK :
            mTextMessage.append("Back Key\n"); 
            finish(); 
            break;
        case KeyEvent.KEYCODE_HOME :
            mTextMessage.append("Home Key\n"); break;
        case KeyEvent.KEYCODE_MENU :
            mTextMessage.append("Menu Key\n"); break;
        case KeyEvent.KEYCODE_POWER :
            mTextMessage.append("Power Key\n"); break;
        case KeyEvent.KEYCODE_VOLUME_UP :
            mTextMessage.append("Volume Up Key\n"); break;
        case KeyEvent.KEYCODE_VOLUME_DOWN :
            mTextMessage.append("Volume Down Key\n"); break;
        default :
            mTextMessage.append("Unknown Key\n"); break;
        }
        return true;
        //return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d("Msg", "Key Up - " + keyCode);
        return super.onKeyUp(keyCode, event);
    }

}
