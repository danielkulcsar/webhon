package com.example.fingerdraw;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
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
        Bitmap bmpBack;
        Canvas canvasBack;
        Point ptStart = new Point();

        public MainView(Context context) {
            super(context);
            /*bmpBack = BitmapFactory.decodeResource(getResources(), 
                    R.drawable.wallpaper_04);*/
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), 
            	    R.drawable.wallpaper_04);
            bmpBack = bmp.copy(Bitmap.Config.ARGB_8888, true);
            canvasBack = new Canvas(bmpBack);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawBitmap(bmpBack, null, new Rect(0, 0, this.getWidth(), 
                    this.getHeight()), null);
        }

        public boolean onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            int poX = (int)(event.getX() / (float)this.getWidth() * (float)bmpBack.getWidth());
            int poY = (int)(event.getY() / (float)this.getHeight() * (float)bmpBack.getHeight());
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d("tag", "Touch Down: " + (int)event.getX() + " / " 
                    + (int)event.getY());
            }
            else if(event.getAction() == MotionEvent.ACTION_MOVE) {
                Log.d("tag", "Touch Down: " + (int)event.getX() + " / " 
                    + (int)event.getY());
                Paint pnt = new Paint();
                pnt.setColor(Color.GREEN);
                pnt.setStrokeWidth(3);
                canvasBack.drawLine(ptStart.x, ptStart.y, poX, 
                		poY, pnt);
                this.invalidate();
            }
            ptStart.x = poX;
            ptStart.y = poY;
            return true;
        }
    }

}
