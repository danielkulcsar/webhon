package com.example.lifecycle;

import android.app.*;
import android.os.*;
import android.util.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextEvent = (TextView)findViewById(R.id.textEvent);
        AddMessage("onCreate()");
    }

    public void AddMessage(String strMsg) {
        String strText = mTextEvent.getText().toString();
        strText += ("\n" + strMsg);
        mTextEvent.setText(strText);
        Log.d("Msg", "Event " + strMsg);
    }

    protected void onStart() {
        super.onStart();
        AddMessage("onStart()");
    }

    protected void onResume() {
        super.onResume();
        AddMessage("onResume()");
    }

    protected void onRestart() {
        super.onRestart();
        AddMessage("onRestart()");
    }

    protected void onPause() {
        super.onPause();
        AddMessage("onPause()");
    }

    protected void onStop() {
        super.onStop();
        AddMessage("onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        AddMessage("onDestroy()");
    }

}
