package com.example.canvasarc;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.Paint.Style;
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
            canvas.drawColor(Color.WHITE);
            Paint pnt = new Paint();
            pnt.setStyle(Style.STROKE);
            pnt.setStrokeWidth(3);
            pnt.setColor(Color.BLACK);
            pnt.setAntiAlias(true);

            canvas.drawCircle(120, 120, 90, pnt);
            canvas.drawCircle(93, 93, 9, pnt);
            canvas.drawCircle(147, 93, 9, pnt);
            RectF rect = new RectF();
            rect.set(57, 57, 183, 183);
            canvas.drawArc(rect, 30, 120, false, pnt);

            pnt.setStyle(Style.FILL);
            pnt.setColor(Color.RED);
            canvas.drawCircle(360, 120, 90, pnt);

            rect.set(30, 270, 210, 450);
            canvas.drawArc(rect, 0, 150, true, pnt);

            rect.set(270, 270, 450, 450);
            canvas.drawArc(rect, 240, 120, false, pnt);

            rect.set(30, 500, 210, 680);
            pnt.setStyle(Style.FILL);
            pnt.setColor(0xffff8800);
            canvas.drawArc(rect, 0, 120, true, pnt);
            pnt.setStyle(Style.STROKE);
            pnt.setColor(Color.BLACK);
            canvas.drawArc(rect, 0, 120, true, pnt);

            pnt.setStyle(Style.FILL);
            pnt.setColor(0xffffff00);
            canvas.drawArc(rect, 120, 80, true, pnt);
            pnt.setStyle(Style.STROKE);
            pnt.setColor(Color.BLACK);
            canvas.drawArc(rect, 120, 80, true, pnt);

            pnt.setStyle(Style.FILL);
            pnt.setColor(0xff0088ff);
            canvas.drawArc(rect, 200, 160, true, pnt);
            pnt.setStyle(Style.STROKE);
            pnt.setColor(Color.BLACK);
            canvas.drawArc(rect, 200, 160, true, pnt);
        }
    }
    
}
