package com.example.nonerectbutton;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    int __nPressButtonNumber = -1;
    EllipseButton[] __stEllipseButton = new EllipseButton[5];
    TextView __textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int nButtonWidth=120;
        int nButtonHeight=120;
        int nButtonGapX=85;
        int nButtonGapY=85;
        RectF rtButton = new RectF(10, 20, 10 + nButtonWidth, 20 + nButtonHeight);

        for(int i=0 ; i < 5 ; i++) {
            __stEllipseButton[i] = new EllipseButton(rtButton, Integer.toString(i+1));
            rtButton.left += nButtonGapX;
            rtButton.right += nButtonGapX;
            rtButton.top += nButtonGapY;
            rtButton.bottom += nButtonGapY;
        }

        MainView mainView = new MainView(this);
        FrameLayout frame = (FrameLayout)findViewById(R.id.mainLayout);
        frame.addView(mainView, 0);
        
        __textMessage = (TextView)findViewById(R.id.textMessage);
    }

    int GetEllipseButtonNumber(Point point) {
        int i=0;
        RectF rtButton;

        for(i=0 ; i < 5 ; i++) {
            rtButton = __stEllipseButton[i].rtArea;
            if( rtButton.contains((float)point.x, (float)point.y) )
                return i;
        }

        return -1;
    }

    public class EllipseButton {
        RectF rtArea;
        String strCaption;
        
        EllipseButton(RectF rt, String str) {
            rtArea = new RectF();
            rtArea.set(rt);
            strCaption = str.toString();
        }
    }

    protected class MainView extends View {
        public MainView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.LTGRAY);
            Paint pnt = new Paint();
            pnt.setTextSize(60);
            pnt.setTextAlign(Paint.Align.CENTER);

            int crBack;
            int crText;

            for(int i=0 ; i < 5 ; i++) {
                if( __nPressButtonNumber == i ) {
                    crBack = Color.RED;
                    crText = Color.WHITE;
                }
                else {
                    crBack = Color.BLUE;
                    crText = Color.YELLOW;
                }
                pnt.setColor(crBack);
                canvas.drawOval(__stEllipseButton[i].rtArea, pnt);

                pnt.setColor(crText);
                float fX = (__stEllipseButton[i].rtArea.left + 
                	    __stEllipseButton[i].rtArea.right) / 2;
                float fY = (__stEllipseButton[i].rtArea.top +
                	    __stEllipseButton[i].rtArea.bottom) / 2 + 25;
                canvas.drawText(__stEllipseButton[i].strCaption, fX, fY, pnt);
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                __nPressButtonNumber = GetEllipseButtonNumber(
                	    new Point((int)event.getX(), (int)event.getY()) );
                this.invalidate();
            }
            else if(event.getAction() == MotionEvent.ACTION_UP) {
                if( __nPressButtonNumber >= 0 ) {
                    String strMessage = String.format("Button %s is Released", 
                    	    __stEllipseButton[__nPressButtonNumber].strCaption);
                    __textMessage.setText(strMessage);
                    __nPressButtonNumber = -1;
                    this.invalidate();
                }
            }
            return true;
        }
    }

}
