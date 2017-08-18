package com.example.createrandom;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextResult;
    Random mRand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		mTextResult = (TextView)this.findViewById(R.id.textResult);
		mRand = new Random();
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonRandom : {
            //int nResult = getRandom(3, 0);
            int nResult = getRandomMath(45, 1);
            mTextResult.setText("Result : " + nResult);
            break;
            }
        }
    }

    public int getRandom(int max, int offset) {
        int nResult = mRand.nextInt(max) + offset;
        return nResult;
    }

    public int getRandomMath(int max, int offset) {
        int nResult = (int)(Math.random() * max) + offset;
        return nResult;
    }

}
