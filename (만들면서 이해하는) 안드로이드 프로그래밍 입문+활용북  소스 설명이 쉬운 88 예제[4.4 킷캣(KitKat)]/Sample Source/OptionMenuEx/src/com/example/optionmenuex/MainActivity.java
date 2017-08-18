package com.example.optionmenuex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView  mTextView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView1 = (TextView)findViewById(R.id.textView1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.menu_disk :
            mTextView1.setText("Menu - Disk");
            break;
        case R.id.menu_cake :
            mTextView1.setText("Menu - Cake");
            break;
        case R.id.menu_star :
            mTextView1.setText("Menu - Star");
            break;
        case R.id.menu_glass :
            mTextView1.setText("Menu - Glass");
            break;
        case R.id.menu_heart :
            mTextView1.setText("Menu - Heart");
            break;
        case R.id.menu_watch :
            mTextView1.setText("Menu - Watch");
            break;
        }
        return false;
    }

}
