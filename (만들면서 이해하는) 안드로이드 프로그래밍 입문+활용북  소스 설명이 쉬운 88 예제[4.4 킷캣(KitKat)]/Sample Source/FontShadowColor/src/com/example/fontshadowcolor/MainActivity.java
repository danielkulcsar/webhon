package com.example.fontshadowcolor;

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
            String strText = "Hello World!";
            Paint pnt = new Paint();

            int x=50, y=100;
            pnt.setTextSize(30);
            pnt.setColor(Color.DKGRAY);
            canvas.drawText(strText, x+2, y+2, pnt);

            pnt.setARGB(255, 255, 64,64);
            canvas.drawText(strText, x, y, pnt);
        }
    }

}
