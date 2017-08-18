/* 만든이 : 정동근 (Jung, Dong-Geun)   */
/*    Mail : topofsan@naver.com        */
 
package com.example.jsliderex;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

public class JSlider extends View {
	int mColorBack;
	SliderInfo mSliderInfo; 
	boolean mFirstDraw = true;
	
	public JSlider(Context context, AttributeSet attrs) {
		super(context, attrs);
		mColorBack = Color.BLUE;
		mSliderInfo = new SliderInfo();
	}

	private JSliderListener mJSliderListener;

	public interface JSliderListener{
		void onMoved(int nValue);
		void onReleased(int nValue);
	}

	public void setListener(JSliderListener listener){
		mJSliderListener = listener;
	}

	protected class SliderInfo {
		public SliderInfo() {
			nOrientation = 0;
			nRangeMin = 0;
			nRangeMax = 100;
			nValue = 50;
			colorBackground = Color.CYAN;
			colorBarNormal = Color.MAGENTA;
			colorBarPressed = Color.RED;
			bitmapBackground = null;
			bitmapBarNormal = null;
			bitmapBarPressed = null;
			pointTouchStart = new Point(-1,-1);
			pointTouchEnd = new Point(-1,-1);
			bBarPressed = false;
			rtScreen = new Rect(10,10,200,60);
			rtBar = new Rect(0,0,70,50);
			rtBarMoveArea = new Rect(0,5,130,60);

		}
		
		int nOrientation;
		int nRangeMin;
		int nRangeMax;
		int nValue;
		int colorBackground;
		int colorBarNormal;
		int colorBarPressed;
		Bitmap bitmapBackground;
		Bitmap bitmapBarNormal;
		Bitmap bitmapBarPressed;
		Point pointTouchStart;
		Point pointTouchEnd;
		boolean bBarPressed;

		Rect rtScreen;
		Rect rtBar;
		Rect rtBarMoveArea;

	};

	public void initSizeVariable() {
		int nBarWidth=0, nBarHeight=0;
		if( mSliderInfo.nOrientation == 0 ) {
			mSliderInfo.rtScreen.set(0, 0, this.getWidth(), this.getHeight());

			if( mSliderInfo.rtBar.height() > mSliderInfo.rtScreen.height() ) {
				mSliderInfo.rtBar.top = 0;
				mSliderInfo.rtBar.bottom = mSliderInfo.rtScreen.height();
			}
			
			if( mSliderInfo.rtBar.width() > mSliderInfo.rtScreen.width() / 2 ) {
				mSliderInfo.rtBar.right = mSliderInfo.rtBar.left + (mSliderInfo.rtScreen.width() / 2) / 2;
			}

			nBarWidth = mSliderInfo.rtBar.width();
			mSliderInfo.rtBarMoveArea.left = 0;
			mSliderInfo.rtBarMoveArea.right = mSliderInfo.rtScreen.width() - nBarWidth;
			nBarHeight = mSliderInfo.rtBar.height();
			mSliderInfo.rtBarMoveArea.top = (mSliderInfo.rtScreen.height() - nBarHeight) / 2;
			mSliderInfo.rtBarMoveArea.bottom = mSliderInfo.rtBarMoveArea.top + nBarHeight;
		}

		nBarWidth = mSliderInfo.rtBar.width();
		nBarHeight = mSliderInfo.rtBar.height();
		
		Point pointBar = getBarPosition();
		mSliderInfo.rtBar.left = pointBar.x;
		mSliderInfo.rtBar.right = mSliderInfo.rtBar.left + nBarWidth;
		mSliderInfo.rtBar.top = pointBar.y;
		mSliderInfo.rtBar.bottom = mSliderInfo.rtBar.top + nBarHeight;
	}

	public Point getBarPosition() {
		double fValuePercent=0.;
		Point pointBar = new Point(-1,-1);

		fValuePercent = (double)(mSliderInfo.nValue - mSliderInfo.nRangeMin)
						/ (double)(mSliderInfo.nRangeMax - mSliderInfo.nRangeMin);

		pointBar.x = (int)((double)mSliderInfo.rtBarMoveArea.width() * fValuePercent);
		pointBar.y = mSliderInfo.rtBarMoveArea.top;

		return pointBar;
	}

	public void setBackColor(int colorBack) {
		mColorBack = colorBack;
	}	

	@Override
	public void onDraw(Canvas canvas) {
		if( mFirstDraw ) {
			mFirstDraw = false;
			initSizeVariable();
		}
		
		drawBackground(canvas);
		drawBar(canvas);
	}
	
	public void drawBackground(Canvas canvas) {
		Rect rtScreen = new Rect();
		rtScreen.set( mSliderInfo.rtScreen );
		rtScreen.left = 0;
		rtScreen.right = mSliderInfo.rtScreen.width();
		rtScreen.top = 0;
		rtScreen.bottom = mSliderInfo.rtScreen.height();
	
		if( mSliderInfo.bitmapBackground != null ) {
			canvas.drawBitmap(mSliderInfo.bitmapBackground, null, rtScreen, null);
		}
		else {
			Paint pnt = new Paint();
			pnt.setColor(mSliderInfo.colorBackground);
			canvas.drawRect(rtScreen, pnt);
		}
	}
	
	public void drawBar(Canvas canvas) {
		if( mSliderInfo.bBarPressed ) {
			if( mSliderInfo.bitmapBarPressed != null ) {
				canvas.drawBitmap(mSliderInfo.bitmapBarPressed, null, mSliderInfo.rtBar, null);
			}
			else {
				Paint pnt = new Paint();
				pnt.setColor(mSliderInfo.colorBarPressed);
				canvas.drawRect(mSliderInfo.rtBar, pnt);
			}
		}
		else {
			if( mSliderInfo.bitmapBarNormal != null ) {
				canvas.drawBitmap(mSliderInfo.bitmapBarNormal, null, mSliderInfo.rtBar, null);
			}
			else {
				Paint pnt = new Paint();
				pnt.setColor(mSliderInfo.colorBarNormal);
				canvas.drawRect(mSliderInfo.rtBar, pnt);
			}
		}
	}
	
	public void setRange(int nMin, int nMax) {
		setRange(nMin, nMax, false);
	}

	public void setRange(int nMin, int nMax, boolean bRedraw) {
		if( nMin >= nMax )
			nMax = nMin + 1;
		mSliderInfo.nRangeMin = nMin;
		mSliderInfo.nRangeMax = nMax;
		if( mSliderInfo.nValue < mSliderInfo.nRangeMin )
			mSliderInfo.nValue = mSliderInfo.nRangeMin;
		if( mSliderInfo.nValue > mSliderInfo.nRangeMax )
			mSliderInfo.nValue = mSliderInfo.nRangeMax;

		if( mFirstDraw == false )
			initSizeVariable();
		if( bRedraw )
			this.invalidate();
	}
	
	public void setValue(int nValue) {
		setValue(nValue, false);
	}

	public void setValue(int nValue, boolean bRedraw) {
		mSliderInfo.nValue = nValue;
		if( mSliderInfo.nRangeMin > mSliderInfo.nValue  )
			mSliderInfo.nRangeMin = mSliderInfo.nValue;
		if( mSliderInfo.nRangeMax < mSliderInfo.nValue )
			mSliderInfo.nRangeMax = mSliderInfo.nValue;

		if( mFirstDraw == false )
			initSizeVariable();
		if( bRedraw )
			this.invalidate();
	}

	public void setBackgroundBitmap(int imageId) {
		mSliderInfo.bitmapBackground = BitmapFactory.decodeResource(getResources(), imageId); 
	}

	public void setBarNormalBitmap(int imageId) {
		mSliderInfo.bitmapBarNormal = BitmapFactory.decodeResource(getResources(), imageId);
		mSliderInfo.rtBar.right = mSliderInfo.rtBar.left + mSliderInfo.bitmapBarNormal.getWidth();
		mSliderInfo.rtBar.bottom = mSliderInfo.rtBar.top + mSliderInfo.bitmapBarNormal.getHeight();

		if( mFirstDraw == false )
			initSizeVariable();
	}

	public void setBarPressedBitmap(int imageId) {
		mSliderInfo.bitmapBarPressed = BitmapFactory.decodeResource(getResources(), imageId);
	}

	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			mSliderInfo.bBarPressed = true;

			setBarPositionByTouchPoint(new Point((int)event.getX(), (int)event.getY()));

			this.invalidate();

			if( mJSliderListener != null )
				mJSliderListener.onMoved(mSliderInfo.nValue);
		}
		else if(event.getAction() == MotionEvent.ACTION_MOVE) {
			int nValue = mSliderInfo.nValue;

			setBarPositionByTouchPoint(new Point((int)event.getX(), (int)event.getY()));

			if( nValue == mSliderInfo.nValue )
				return true;

			this.invalidate();

			if( mJSliderListener != null )
				mJSliderListener.onMoved(mSliderInfo.nValue);
		}
		else if(event.getAction() == MotionEvent.ACTION_UP) {
			mSliderInfo.bBarPressed = false;

			this.invalidate();

			if( mJSliderListener != null )
				mJSliderListener.onReleased(mSliderInfo.nValue);
		}
		return true;
	}

	public void setBarPositionByTouchPoint(Point pointTouch) {
		int nBarWidth = mSliderInfo.rtBar.width(); 
		mSliderInfo.rtBar.left = pointTouch.x - (nBarWidth / 2);
		if( mSliderInfo.rtBar.left < 0 )
			mSliderInfo.rtBar.left = 0;

		if( mSliderInfo.rtBar.left > mSliderInfo.rtBarMoveArea.width() )
			mSliderInfo.rtBar.left = mSliderInfo.rtBarMoveArea.width();

		mSliderInfo.rtBar.right = mSliderInfo.rtBar.left + nBarWidth;

		mSliderInfo.nValue = getValueFromBarPosition();
	}

	public int getValueFromBarPosition()
	{
		double fPositonRate = 0.;
		int nValue = 0;

		fPositonRate = (double)mSliderInfo.rtBar.left / (double)mSliderInfo.rtBarMoveArea.width();

		nValue = (int)((double)(mSliderInfo.nRangeMax - mSliderInfo.nRangeMin) * fPositonRate) + mSliderInfo.nRangeMin;

		return nValue;
	}

}


