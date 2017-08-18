/* 만든이 : 정동근 (Jung, Dong-Geun)   */
/*    Mail : topofsan@naver.com        */

package com.example.introfadeinout;

import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.os.*;
import android.view.*;

public class IntroActivity extends Activity {
	final int TIMER_INTERVER = 40;
	final int TIMER_COUNT_MAX = 20;
	int mTimerIndex = 0;
	
	int mScreenWidth = 0, mScreenHeight = 0;
	float mFontSize = 20;
	Bitmap mBmpBack = null;
	Rect mRtCanvas = new Rect(0, 0, 10, 10);
    ViewCanvas mViewCanvas = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		requestWindowFeature( Window.FEATURE_NO_TITLE );

		mViewCanvas = new ViewCanvas(this);
        setContentView(mViewCanvas);
        
        Intent intent = getIntent();
        int id = intent.getIntExtra("BackImage", 0);
        if( id > 0 )
        	mBmpBack = loadImage(id);
        
        initialize();
    }
    
    private void initialize() {
    	timerFadeIn.sendEmptyMessageDelayed(0, TIMER_INTERVER);
    }
    
	Handler timerFadeIn = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//finish();
			mTimerIndex ++;
			
			if( mTimerIndex < TIMER_COUNT_MAX) {
				mViewCanvas.invalidate();
				timerFadeIn.sendEmptyMessageDelayed(0, TIMER_INTERVER);
			}
			else {
				timerFreeze.sendEmptyMessageDelayed(0, 1500);
			}
		}
    };
    
    Handler timerFreeze = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			timerFadeOut.sendEmptyMessageDelayed(0, TIMER_INTERVER);
		}
    };

	Handler timerFadeOut = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			mTimerIndex --;
			
			if( mTimerIndex > 0) {
				mViewCanvas.invalidate();
				timerFadeOut.sendEmptyMessageDelayed(0, TIMER_INTERVER);
			}
			else {
				finish();
			}
		}
    };
    
	public Bitmap loadImage(int resImage) {
		Resources res = getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res, resImage);

		return bitmap;
	}

	public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);
		
		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		Display dsp = wm.getDefaultDisplay();

		mScreenWidth = dsp.getWidth();
		mScreenHeight = dsp.getHeight();
		mRtCanvas.right = mScreenWidth;
		mRtCanvas.bottom = mScreenHeight;

		mFontSize = 21.f / 480.f * (float)mScreenWidth;
	}

    protected class ViewCanvas extends View {
    	public ViewCanvas(Context context) {
    		super(context);
    	}

    	public void onDraw(Canvas canvas) {
    		super.onDraw(canvas);
    		if( mScreenWidth < 1 )
    			return;

    		if( mBmpBack != null )
    			drawImage(canvas, mBmpBack, mRtCanvas);
    		
    		int alpha = (int)(255.f * (float)(TIMER_COUNT_MAX - mTimerIndex) / (float)TIMER_COUNT_MAX);
    		int color = Color.argb(alpha, 255, 255, 255);
    		Paint paint = new Paint();
    		paint.setStyle(Style.FILL);
    		paint.setColor(color);
    		canvas.drawRect(mRtCanvas, paint);
    	}
    	
    	public void drawImage(Canvas canvas, Bitmap bitmap, Rect rtImage) {
    		int imageWidth = 0, imageHeight = 0;
    		imageWidth = bitmap.getWidth();
    		imageHeight = bitmap.getHeight();
    		Rect rtSrc = new Rect(0, 0, imageWidth, imageHeight);
    		
    		float imageRatio = 0.f, screenRatio = 0.f;
    		imageRatio = (float)imageHeight / (float)imageWidth;
    		screenRatio = (float)rtImage.height() / (float)rtImage.width();
    		
    		if( imageRatio > screenRatio ) {
    			rtSrc.bottom = (int)((float)imageWidth * screenRatio);
    		}
    		else {
    			imageWidth = (int)((float)imageHeight / screenRatio);
    			rtSrc.left = (rtSrc.width() - imageWidth) / 2;
    			rtSrc.right = rtSrc.left + imageWidth;
    		}
    		
    		canvas.drawBitmap(bitmap, rtSrc, rtImage, null);
    	}
    }
}

