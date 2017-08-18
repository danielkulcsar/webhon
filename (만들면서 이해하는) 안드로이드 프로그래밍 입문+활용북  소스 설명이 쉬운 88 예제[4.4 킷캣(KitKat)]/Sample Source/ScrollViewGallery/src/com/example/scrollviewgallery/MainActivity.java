package com.example.scrollviewgallery;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

public class MainActivity extends Activity 
        implements OnTouchListener {
    TextView mTextMessage;
    ImageView mImageMain;
    ImageView[] mImageView = new  ImageView[4];
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage= (TextView)findViewById(R.id.textMessage);
        mImageMain= (ImageView)findViewById(R.id.imageMain);
        initScrollView();
    }

    public void initScrollView() {
        LinearLayout scrollLayout1 = (LinearLayout)findViewById(R.id.scrollLayout1);
        for(int i=0; i < 4; i++) {
            mImageView[i] = new  ImageView(this);
            mImageView[i].setImageResource(R.drawable.puzzle01 + i);
            scrollLayout1.addView(mImageView[i]);
            LayoutParams params = mImageView[i].getLayoutParams();
            params.width = 200;
            mImageView[i].setOnTouchListener(this);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        for(int i=0; i < 4; i++) {
            if( v == mImageView[i] ) {
                mTextMessage.setText("Image - " + i);
                mImageMain.setImageResource(R.drawable.puzzle01 + i);
            }
        }
        return true;
    }

}
