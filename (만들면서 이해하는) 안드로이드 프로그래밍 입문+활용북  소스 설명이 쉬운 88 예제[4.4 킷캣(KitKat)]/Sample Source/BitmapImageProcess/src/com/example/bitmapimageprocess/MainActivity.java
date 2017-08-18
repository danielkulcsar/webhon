package com.example.bitmapimageprocess;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    Bitmap mBitmap1;
    Bitmap mBitmap2;
    MainView mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBitmap1 = BitmapFactory.decodeResource(getResources(), 
        	    R.drawable.background);
        mBitmap2 = mBitmap1.copy(Bitmap.Config.ARGB_8888, true );

        mMainView = new MainView(this);
        FrameLayout frame = (FrameLayout)findViewById(R.id.mainLayout);
        frame.addView(mMainView, 0);
    }

    protected class MainView extends View {
        public MainView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);
            Rect rtDest = new Rect(0, 0, this.getWidth(), this.getHeight());
            canvas.drawBitmap(mBitmap2, null, rtDest, null);
        }
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnGrayColor :
        	bitmap2GrayColor(mBitmap2);
        	mMainView.invalidate();
            break;
        }
    }

    public void bitmap2GrayColor(Bitmap bmp) {
        int nWidth = bmp.getWidth();
        int nHeight = bmp.getHeight();
        int nRed, nGreen, nBlue, nNew;

        for(int j=0 ; j < nHeight ; j++) {
            for(int i=0 ; i < nWidth ; i++) {
                int crPixel = bmp.getPixel(i, j);
                nRed = Color.red(crPixel);
                nGreen = Color.green(crPixel);
                nBlue = Color.blue(crPixel);
                nNew = (nRed + nGreen + nBlue) / 3;
                crPixel = Color.argb(255, nNew, nNew, nNew);
                bmp.setPixel(i, j, crPixel);
            }
        }
    }

}
