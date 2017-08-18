package com.example.imagefileview;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        MainView mainView = new MainView(this);
        setContentView(mainView);
    }

    protected class MainView extends View {
        public MainView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);
            Paint Pnt = new Paint();

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), 
            	    R.drawable.bicky02); 
            Rect rtDest = new Rect(20,10,460,350); 
            canvas.drawBitmap(bitmap, null, rtDest, null);
            
            DrawImageInRectangle(canvas, bitmap, new Rect(rtDest.left, 
                	rtDest.top+360, rtDest.right, rtDest.bottom+360));
        }
    }

    void DrawImageInRectangle(Canvas canvas, Bitmap bitmap, Rect rtArea) {
        int nSourceWidth=0, nSourceHeight=0, nDestWidth=0, nDestHeight=0;
        int nDestX=0, nDestY=0;
        double fRateSource=0., fRateDest=0.;
        Rect rtSource, rtDest;

        nDestWidth = rtArea.right - rtArea.left;
        nDestHeight = rtArea.bottom - rtArea.top;
        nSourceWidth = bitmap.getWidth();
        nSourceHeight = bitmap.getHeight();

        rtSource = new Rect(0, 0, nSourceWidth, nSourceHeight);
        fRateSource = (double)nSourceWidth / (double)nSourceHeight;
        fRateDest = (double)nDestWidth / (double)nDestHeight;

        if( fRateSource > fRateDest )
            nDestHeight = (int)((double)nDestWidth / fRateSource);
        else
            nDestWidth = (int)((double)nDestHeight * fRateSource);
        nDestX = rtArea.left + (rtArea.right - rtArea.left - nDestWidth) / 2;
        nDestY = rtArea.top + (rtArea.bottom - rtArea.top - nDestHeight) / 2;
        rtDest = new Rect(nDestX, nDestY, nDestX + nDestWidth, 
            nDestY + nDestHeight);

        canvas.drawBitmap(bitmap, null, rtDest, null); 
    }
}
