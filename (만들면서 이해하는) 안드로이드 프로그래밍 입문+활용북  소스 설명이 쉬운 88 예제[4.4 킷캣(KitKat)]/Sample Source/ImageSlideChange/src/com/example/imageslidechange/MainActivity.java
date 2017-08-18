package com.example.imageslidechange;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    Bitmap mBitmap1;
    Bitmap mBitmap2;
    int mSlideDistance=0;
    MainView mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBitmap1 = BitmapFactory.decodeResource(getResources(), 
            R.drawable.bicky01);
        mBitmap2 = BitmapFactory.decodeResource(getResources(), 
            R.drawable.bicky02);

        mMainView = new MainView(this); 
        FrameLayout frame = (FrameLayout)findViewById(R.id.mainLayout); 
        frame.addView(mMainView, 0);
    }

    Handler mTimer = new Handler() {
        public void handleMessage(Message msg) {
            int nSlideUnit = 30;
            mSlideDistance += nSlideUnit;
            if (mSlideDistance > 480)
                mSlideDistance = 480;
            mMainView.invalidate();
            if (mSlideDistance < 480) {
                mTimer.sendEmptyMessageDelayed(0,40);
            }
        }
    };

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonStart :
            mSlideDistance=0;
            mTimer.sendEmptyMessage(0);
            break;
        }
    }

    protected class MainView extends View {
        public MainView(Context context) {
            super(context);
        }
 
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);
            int nScreenWidth = this.getWidth();
            int nScreenHeight = this.getHeight();

            Rect rtDest1 = new Rect(0,0,nScreenWidth,nScreenHeight);
            rtDest1.left -= mSlideDistance;
            rtDest1.right -= mSlideDistance;
            canvas.drawBitmap(mBitmap1, null, rtDest1, null);
 
            Rect rtDest2 = new Rect(nScreenWidth, 0, nScreenWidth * 2, 
            	    nScreenHeight);
            rtDest2.left -= mSlideDistance;
            rtDest2.right -= mSlideDistance;
            canvas.drawBitmap(mBitmap2, null, rtDest2, null);
        }
    }

}
