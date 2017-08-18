package com.example.imagezoomingrotate;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    Bitmap mBitmap1;
    Point mPointAxis = new Point(240, 260);
    int mRotateAngle = 0;
    int mImageSize = 100;
    int mResizeUnit = 10;
    MainView mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBitmap1 = BitmapFactory.decodeResource(getResources(), 
        	    R.drawable.simple_color_1);

        mMainView = new MainView(this);
        FrameLayout frame = (FrameLayout)findViewById(R.id.mainLayout);
        frame.addView(mMainView, 0);
    }

    Handler mTimer = new Handler() {
        public void handleMessage(Message msg) {
            int nSizeMin = 20;
            int nSizeMax = 220;

            if( mResizeUnit > 0 && mImageSize >= nSizeMax)
                mResizeUnit *= -1;
            else if( mResizeUnit < 0 && mImageSize <= nSizeMin)
                mResizeUnit *= -1;

            mImageSize += mResizeUnit;
            mRotateAngle += 15;
            mMainView.invalidate();
            mTimer.sendEmptyMessageDelayed(0,40);
        }
    };

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonStart :
            mRotateAngle = 0;
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
            canvas.rotate(mRotateAngle, mPointAxis.x, mPointAxis.y);

            Rect rtImage = new Rect();
            rtImage.left = mPointAxis.x - mImageSize;
            rtImage.right = mPointAxis.x + mImageSize;
            rtImage.top = mPointAxis.y - mImageSize;
            rtImage.bottom = mPointAxis.y + mImageSize;

            canvas.drawBitmap(mBitmap1, null, rtImage, null);
        }
    }
    
}
