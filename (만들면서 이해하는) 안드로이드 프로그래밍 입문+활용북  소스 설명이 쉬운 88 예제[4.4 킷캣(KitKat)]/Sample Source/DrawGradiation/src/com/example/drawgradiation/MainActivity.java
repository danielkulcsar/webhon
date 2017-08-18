package com.example.drawgradiation;

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

            Rect rect = new Rect();
            rect.set(10, 50, 240, 410);

            int color1 = Color.BLUE;
            int color2 = Color.YELLOW;
            drawGradientRect(canvas, rect, color1, color2);

            rect.set(240, 50, 470, 410);
            color1 = color2;
            color2 = Color.RED;
            drawGradientRect(canvas, rect, color1, color2);
        }

        void drawGradientRect(Canvas canvas, Rect rect, int color1, int color2) {
            Paint pnt = new Paint();
            pnt.setStrokeWidth(1);

            int crR1=0, crG1=0, crB1=0;
            int crR2=0, crG2=0, crB2=0;
            int crR, crG, crB;

            crR1 = color1 >>  16 & 0xff;
            crG1 = color1 >> 8 & 0xff;
            crB1 = color1  & 0xff;
            crR2 = color2 >>  16 & 0xff;
            crG2 = color2 >> 8 & 0xff;
            crB2 = color2  & 0xff;

            int nWidth = rect.right - rect.left;
            for(int i=0; i < nWidth ; i++) {
                crR = (int)( (float)(crR2 - crR1) / 
                        (float)nWidth * (float)i + crR1);
                crG = (int)( (float)(crG2 - crG1) / 
                        (float)nWidth * (float)i + crG1);
                crB = (int)( (float)(crB2 - crB1) / 
                        (float)nWidth * (float)i + crB1);
                pnt.setARGB(255, crR,crG,crB);

                int nX1 = i+rect.left;
                int nY1 = rect.top;
                int nX2 = i+rect.left;
                int nY2 = rect.bottom;
                canvas.drawLine(nX1, nY1, nX2, nY2, pnt);
            }
        }
    }

}
