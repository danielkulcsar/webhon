package net.npaka.multitouchex;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class MultiTouchEx extends Activity {
    private MultiTouchView touchView;
    private TickHandler    tickHandler;
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        touchView=new MultiTouchView(this);
        setContentView(touchView);
    }

    @Override
    public void onResume() {
        super.onResume();
        tickHandler=new TickHandler();
        tickHandler.sleep(0);
    }    
    
    @Override
    public void onPause() {
        super.onPause();
        tickHandler=null;
    }    
    
    public class TickHandler extends Handler {
        @Override 
        public void handleMessage(Message msg) {
            touchView.invalidate();
            if (tickHandler!=null) tickHandler.sleep(100);
        }
        
        public void sleep(long delayMills) {
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0),delayMills);
        }
    }
}