package com.example.buttonimageanimation;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    int mCount = 0;
    TextView mTextMessage;
    Button mBtnAni;
    int[] mBtnImage = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
        mTimer.sendEmptyMessage(0);
        
        mBtnAni = (Button)findViewById(R.id.btnAni);
        //mBtnImage[0] = R.drawable.btn_gray_n;
        //mBtnImage[1] = R.drawable.btn_green_n;
        //mBtnImage[2] = R.drawable.btn_navy_n;
        //mBtnImage[3] = R.drawable.btn_orange_n;
        mBtnImage[0] = R.drawable.btn_image_gray;
        mBtnImage[1] = R.drawable.btn_image_green;
        mBtnImage[2] = R.drawable.btn_image_navy;
        mBtnImage[3] = R.drawable.btn_image_orange;
    }

    Handler mTimer = new Handler() {
        public void handleMessage(Message msg) {
            mTextMessage.setText("Count = " + mCount);
            mBtnAni.setBackgroundResource(mBtnImage[mCount % 4]);
            mCount++;
            mTimer.sendEmptyMessageDelayed(0, 1000);
        }
    };
}
