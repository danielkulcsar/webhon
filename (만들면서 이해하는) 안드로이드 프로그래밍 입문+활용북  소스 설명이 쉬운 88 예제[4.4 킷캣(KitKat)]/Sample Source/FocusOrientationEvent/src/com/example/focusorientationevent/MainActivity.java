package com.example.focusorientationevent;

import android.app.*;
import android.content.res.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextMessage;
    EditText mTextTouch1;
    EditText mTextTouch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
        mTextTouch1 = (EditText)findViewById(R.id.textTouch1);
        mTextTouch2 = (EditText)findViewById(R.id.textTouch2);

        mTextTouch1.setOnFocusChangeListener(mFocusListener);
        mTextTouch2.setOnFocusChangeListener(mFocusListener);
    }

    View.OnFocusChangeListener mFocusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if( v == mTextTouch1 ) {
                if( hasFocus )
                    mTextMessage.setText("Touch-1 Set Focus");
                else
                    mTextMessage.setText("Touch-1 Kill Focus");
            }
            else {
                if( hasFocus )
                    mTextMessage.setText("Touch-2 Set Focus");
                else
                    mTextMessage.setText("Touch-2 Kill Focus");
            }
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mTextMessage.setText("onConfigurationChanged-1");
        Log.d("Msg", "onConfigurationChanged-1");
        if( newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            mTextMessage.setText("Orientation - Landscape");
        } else if( newConfig.orientation == Configuration.ORIENTATION_PORTRAIT ) {
            mTextMessage.setText("Orientation - Portrait");
        }
    }

}
