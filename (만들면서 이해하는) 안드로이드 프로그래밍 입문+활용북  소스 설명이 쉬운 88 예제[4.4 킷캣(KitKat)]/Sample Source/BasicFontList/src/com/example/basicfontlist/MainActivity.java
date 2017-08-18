package com.example.basicfontlist;

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
            Paint pnt = new Paint();
            pnt.setAntiAlias(true);
            pnt.setTextSize(30);
            int y = 1;

            pnt.setTypeface( Typeface.create(Typeface.DEFAULT, Typeface.NORMAL ));
            canvas.drawText("Font:Default", 10, y++ * 40, pnt);
            pnt.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, 
            	    Typeface.NORMAL));
            canvas.drawText("Font:Default Bold", 10, y++ * 40, pnt);
            pnt.setTypeface(Typeface.create(Typeface.MONOSPACE, 
            	    Typeface.NORMAL));
            canvas.drawText("Font:Monospace", 10, y++ * 40, pnt);
            pnt.setTypeface(Typeface.create(Typeface.SANS_SERIF, 
            	    Typeface.NORMAL));
            canvas.drawText("Font:Sans Serif", 10, y++ * 40, pnt);
            pnt.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
            canvas.drawText("Font:Serif", 10, y++ * 40, pnt);

            Typeface fontBalloon = Typeface.createFromAsset(getAssets(), 
            	    "balloons.ttf");
            pnt.setTypeface(fontBalloon);
            canvas.drawText("Custom Font:Balloons", 10, y++ * 40, pnt);
        }
    }

}
