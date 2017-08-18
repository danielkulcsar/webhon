package com.example.textviewex;

import android.app.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextText1;
    TextView mTextText2;
    TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextText1 = (TextView)findViewById(R.id.textView1);
        mTextText1.setTextColor(Color.YELLOW);

        mTextText2 = (TextView)findViewById(R.id.textView2);
        int colorText1 = Color.rgb(255,0,0);
        mTextText2.setTextColor(colorText1);

        mTextMessage = (TextView)findViewById(R.id.textMessage);
        mTextMessage.setText("Activity Created");
        int colorText2 = Color.argb(128, 255, 255, 128);
        mTextMessage.setTextColor(colorText2);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
