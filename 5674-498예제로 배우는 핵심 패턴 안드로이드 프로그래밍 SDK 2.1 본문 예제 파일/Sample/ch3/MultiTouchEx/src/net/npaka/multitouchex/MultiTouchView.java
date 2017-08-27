package net.npaka.multitouchex;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.MotionEvent;

public class MultiTouchView extends View {
    private boolean touchFlag0=false;
    private float   touchX0;
    private float   touchY0;
    private boolean touchFlag1=false;
    private float   touchX1;
    private float   touchY1;
    private String  info0="";
    private String  info1="";
    
    public MultiTouchView(Context context) {
        super(context);
        setBackgroundColor(Color.WHITE);

        setFocusable(true);
    }
    
    // 터치 이벤트 처리
    @Override 
    protected void onDraw(Canvas canvas) {

        Paint paint=new Paint(); 
        paint.setAntiAlias(true);
        paint.setTextSize(32);
        
        canvas.drawText("MultiTouchXY>",0,40*1,paint);
        canvas.drawText(info0,0,40*2,paint);
        canvas.drawText(info1,0,40*3,paint);
    }

    // 터치 이벤트 처리
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int pointerCount=event.getPointerCount();  
        if (pointerCount<1) return false;
        final int eventCode =event.getAction();
        final int historyLen=event.getHistorySize()/pointerCount;
        final int index0=event.findPointerIndex(0);
        final int index1=(pointerCount>1?event.findPointerIndex(1):-1);
        if (historyLen>0) {
            for (int i=0;i<historyLen;i++) {
                final float x0=event.getHistoricalX(index0,i);
                final float y0=event.getHistoricalY(index0,i);
                handleMoveEvent(eventCode,x0,y0);
                if (pointerCount>1) {
                    final float x1=event.getHistoricalX(index1,i);
                    final float y1=event.getHistoricalY(index1,i);
                    handleMoveEvent(eventCode,x1,y1);
                }
            }
        } else {
            handleMoveEvent(eventCode,event.getX(),event.getY());
        }
        info0="touch0>"+(int)touchX0+","+(int)touchY0;
        info1="touch1>"+(int)touchX1+","+(int)touchY1;
        return true;
    }

    // 터치 Move 이벤트 처리
    private void handleMoveEvent(int eventCode,float x,float y) {
        if (((eventCode&MotionEvent.ACTION_MASK)==
            MotionEvent.ACTION_DOWN) ||
            ((eventCode&MotionEvent.ACTION_POINTER_1_DOWN)==
            MotionEvent.ACTION_POINTER_1_DOWN)) {
            if (!touchFlag0) {
                touchX0   =x;
                touchY0   =y;
                touchFlag0=true;
            } else if (!touchFlag1){
                touchX1   =x;
                touchY1   =y;
                touchFlag1=true;
            }
        } else if ((eventCode&MotionEvent.ACTION_MASK)==
            MotionEvent.ACTION_UP ||
            ((eventCode&MotionEvent.ACTION_POINTER_1_UP)==
            MotionEvent.ACTION_POINTER_1_UP)) {
            if (touchFlag0&&(!touchFlag1 || nearToFirst(x,y))) { 
                touchFlag0=false;
            } else if (touchFlag1) {
                touchFlag1=false;
            }
        } else if ((eventCode & MotionEvent.ACTION_MASK)==
            MotionEvent.ACTION_MOVE) {
            if (nearToFirst(x, y)) {
                touchX0=x;
                touchY0=y;
            } else {
                touchX1=x;
                touchY1=y;
            }
        }
    }

    // 터치 0과 터치 1의 어느쪽이 가까운지
    private boolean nearToFirst(float tmpX,float tmpY) {
        float distance0=(((tmpX-touchX0)*(tmpX-touchX0))+
            ((tmpY-touchY0)*(tmpY-touchY0)));
        float distance1=(((tmpX-touchX1)*(tmpX-touchX1))+
            ((tmpY-touchY1)*(tmpY-touchY1)));
        return distance0<=distance1;
    }

}