package com.example.framelayout_tabscreen;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
	ImageView mImageView1;
	LinearLayout mLinearLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mImageView1 = (ImageView)findViewById(R.id.imageView1);
        mLinearLayout1 = (LinearLayout)findViewById(R.id.linearLayout1);
    }

    public void onClick(View v) {
    	mImageView1.setVisibility(View.INVISIBLE);
    	mLinearLayout1.setVisibility(View.INVISIBLE);
    	
    	switch( v.getId() ) {
    	case R.id.btnTab1 :
    		mImageView1.setVisibility(View.VISIBLE);
    		break;
    	case R.id.btnTab2 :
    		mLinearLayout1.setVisibility(View.VISIBLE);
    		break;
    	}
    }

}
