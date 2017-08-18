package com.example.introfadeinout;

import android.app.*;
import android.content.*;
import android.os.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LoadIntroActivity();
    }

    public void LoadIntroActivity() {
        Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
        intent.putExtra("BackImage", R.drawable.bicky02);
        startActivity(intent);
    }
    
}
