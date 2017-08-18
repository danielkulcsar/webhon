package com.example.customwidget;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

public class CustomView extends View {
    int mColorBack;
    
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mColorBack = Color.BLUE;
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);
        Paint pnt = new Paint();
        pnt.setColor(mColorBack);
        Rect rtDest = new Rect(0, 0, this.getWidth(), this.getHeight());

        canvas.drawRect(rtDest, pnt);
    }

    public void setBackColor(int colorBack) {
        mColorBack = colorBack;
        this.invalidate();
    }
    
    public interface EventListener {
        void onTouch(int x, int y);
    }

    private EventListener mEventListener = null;

    public void setListener(EventListener listener){
        mEventListener = listener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if( mEventListener != null )
                mEventListener.onTouch((int)event.getX(), (int)event.getY());
        }
        return true;
    }

}
