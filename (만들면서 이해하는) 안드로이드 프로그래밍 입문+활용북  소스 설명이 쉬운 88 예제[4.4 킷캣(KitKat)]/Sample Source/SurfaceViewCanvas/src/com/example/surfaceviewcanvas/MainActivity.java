package com.example.surfaceviewcanvas;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.os.*;
import android.util.*;
import android.view.*;

public class MainActivity extends Activity {
    Point mPtCenter = new Point();
    int mMaxRadius;
    int mNowRadius;
    int mUnitRadius;
    //MainView mMainView;
    CanvasSurface mMainView;

    public void initVariable(int width, int height) {
        int scrSize = Math.min(width, height);
        mPtCenter.x = scrSize / 2;
        mPtCenter.y = scrSize / 2;
        mMaxRadius = scrSize / 2;
        mUnitRadius = mMaxRadius / 20;
        mNowRadius = mUnitRadius;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //DisplayMetrics disMetrics = getResources().getDisplayMetrics();
        //initVariable(disMetrics.widthPixels, disMetrics.heightPixels);

        //mMainView = new MainView(this);
        mMainView = new CanvasSurface(this);
        setContentView(mMainView);
        //mTimer.sendEmptyMessageDelayed(0, 30);
    }

    public void drawCanvas(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        Paint pnt = new Paint();
        pnt.setStyle(Style.STROKE);
        pnt.setStrokeWidth(3);
        pnt.setColor(Color.BLACK);
        pnt.setAntiAlias(true);

        canvas.drawCircle(mPtCenter.x, mPtCenter.y, mNowRadius, pnt);
        mNowRadius += mUnitRadius;
        if( mNowRadius > mMaxRadius )
            mNowRadius = mUnitRadius;
    }

    Handler mTimer = new Handler() {
        public void handleMessage(Message msg) {
            mMainView.invalidate();
            mTimer.sendEmptyMessageDelayed(0, 30);
        }
    };

    protected class MainView extends View {
        public MainView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            drawCanvas(canvas); 
        }
    }

    class CanvasSurface extends SurfaceView implements SurfaceHolder.Callback {
        SurfaceHolder mHolder = null;

        public CanvasSurface(Context context) {
            super(context);
            mHolder = getHolder();
            mHolder.addCallback(this);
        }

        public void surfaceCreated(SurfaceHolder holder) {
        }

        public void surfaceChanged(SurfaceHolder holder, int format, 
        	    int width, int height) {
            initVariable(width, height);
            mThreadAni.start();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
        	mThreadAni.interrupt();
        }
    }

    private Thread mThreadAni = new Thread() {
        public void run() {
        	while( !Thread.currentThread().isInterrupted() ) {
                Canvas canvas = mMainView.mHolder.lockCanvas();
                if( canvas == null )
                    return;
                drawCanvas(canvas);
                mMainView.mHolder.unlockCanvasAndPost(canvas);

                try {
                    Thread.sleep(30);
                } catch(Exception e) {;}
            }
        }
    };

}
