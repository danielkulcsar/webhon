package com.example.multilanguage;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mText1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText1 = (TextView)findViewById(R.id.textView1);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonClick :
            mText1.setText(R.string.click_message);
            break;
        }
    }
    
}
