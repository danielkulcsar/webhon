package com.example.imageresizerotate;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    Bitmap bitmap1;
    Bitmap bitmap2;
    Point pointCenter1 = new Point(240, 150);
    Point pointCenter2 = new Point(240, 550);
    int nImage1Size = 50;
    int nImage2Degree = 0;
    MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mainView = new MainView(this);
        FrameLayout frame = (FrameLayout)findViewById(R.id.mainLayout);
        frame.addView(mainView, 0);
        bitmap1 = BitmapFactory.decodeResource(getResources(), 
        	    R.drawable.simple_color_1);
        bitmap2 = BitmapFactory.decodeResource(getResources(), 
        	    R.drawable.simple_color_4);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnResize :
            nImage1Size += 30;
            mainView.invalidate();
            break;
        case R.id.btnRotate :
            nImage2Degree += 30;
            mainView.invalidate();
            break;
        }
    }

    protected class MainView extends View {
        public MainView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);
            Paint Pnt = new Paint();

            Rect rtDest1 = new Rect(pointCenter1.x - nImage1Size, 
                pointCenter1.y - nImage1Size, pointCenter1.x 
                + nImage1Size, pointCenter1.y + nImage1Size);
            canvas.drawBitmap(bitmap1, null, rtDest1, null);

            Rect rtDest2 = new Rect(pointCenter2.x - 50, pointCenter2.y - 50, 
            	    pointCenter2.x + 50, pointCenter2.y + 50);
            canvas.rotate(nImage2Degree, rtDest2.centerX(), rtDest2.centerY());
            canvas.drawBitmap(bitmap2, null, rtDest2, null);
        }
    }

}
