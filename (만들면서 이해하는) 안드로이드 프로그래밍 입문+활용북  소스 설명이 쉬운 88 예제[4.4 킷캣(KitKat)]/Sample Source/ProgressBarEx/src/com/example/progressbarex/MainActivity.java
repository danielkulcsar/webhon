package com.example.progressbarex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    ProgressBar mProgressBar1;
    int mProgValue = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        
        this.setProgress(mProgValue);
        this.setProgressBarVisibility(true);
        mProgressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnLeft :
            mProgressBar1.incrementProgressBy(-2);
            mProgValue -= 200;
    	    this.setProgress(mProgValue);
            break;
        case R.id.btnRight :
            mProgressBar1.incrementProgressBy(2);
            mProgValue += 200;
    	    this.setProgress(mProgValue);
            break;
        case R.id.btnLeft2 :
            mProgressBar1.incrementSecondaryProgressBy(-2);
            break;
        case R.id.btnRight2 :
            mProgressBar1.incrementSecondaryProgressBy(2);
            break;
        }
    }

}
