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

	//콜백을 저장할 멤버변수
	private JSliderListener mJSliderListener;

	//콜백은 이 인터페이스를 상속해서 만들어야 함
	public interface JSliderListener{
		void onMoved(int nValue);
		void onReleased(int nValue);
	}

	//콜백을 세팅하는 메쏘드
	public void setListener(JSliderListener listener){
		mJSliderListener = listener;
	}

	//슬라이더 정보
	protected class SliderInfo {
		public SliderInfo() {
			// 슬라이더 정보 구조체 초기화
			nOrientation = 0;		// 방향 - 수평:0, 수직:1
			nRangeMin = 0;							// 범위 - 최소값
			nRangeMax = 100;							// 범위 - 최대값
			nValue = 50;								// 현재 설정값
			colorBackground = Color.CYAN;		// 배경 컬러
			colorBarNormal = Color.MAGENTA;	// 바 컬러 - 일반 상태
			colorBarPressed = Color.RED;	// 바 컬러 - Pressed 상태
			bitmapBackground = null;					// 배경 이미지
			bitmapBarNormal = null;					// 바 이미지 - 일반 상태
			bitmapBarPressed = null;					// 바 이미지 - Pressed 상태
			pointTouchStart = new Point(-1,-1);			// 터치 포인트 시작 위치
			pointTouchEnd = new Point(-1,-1);			// 터치 포인트 끝 위치
			bBarPressed = false;						// Bar 상태가 Pressed 인지 여부
			rtScreen = new Rect(10,10,200,60);		// 컨트롤 위치좌표 정보
			rtBar = new Rect(0,0,70,50);			// Bar의 위치좌표 정보
			rtBarMoveArea = new Rect(0,5,130,60);	// Bar가 이동하는 영역좌표

		}
		
		int nOrientation;				// 방향 - 수평:0, 수직:1
		int nRangeMin;					// 범위 - 최소값
		int nRangeMax;					// 범위 - 최대값
		int nValue;						// 현재 설정값
		int colorBackground;			// 배경 컬러
		int colorBarNormal;			// 바 컬러 - 일반 상태
		int colorBarPressed;			// 바 컬러 - Pressed 상태
		Bitmap bitmapBackground;		// 배경 이미지
		Bitmap bitmapBarNormal;		// 바 이미지 - 일반 상태
		Bitmap bitmapBarPressed;		// 바 이미지 - Pressed 상태
		Point pointTouchStart;			// 터치 포인트 시작 위치
		Point pointTouchEnd;			// 터치 포인트 끝 위치
		boolean bBarPressed;				// Bar 상태가 Pressed 인지 여부

		Rect rtScreen;				// 컨트롤 위치좌표 정보
		Rect rtBar;				// Bar의 위치좌표 정보
		Rect rtBarMoveArea;		// Bar가 이동하는 영역좌표

	};

	// 크기 변수 초기화
	public void initSizeVariable() {
		int nBarWidth=0, nBarHeight=0;
		// 슬라이더 컨트롤 방향이 수평이라면
		if( mSliderInfo.nOrientation == 0 ) {
			// 컨트롤 위치좌표 정보
			mSliderInfo.rtScreen.set(0, 0, this.getWidth(), this.getHeight());

			// Bar의 높이가 컨트롤의 높이 보다 더 크다면
			if( mSliderInfo.rtBar.height() > mSliderInfo.rtScreen.height() ) {
				mSliderInfo.rtBar.top = 0;
				mSliderInfo.rtBar.bottom = mSliderInfo.rtScreen.height();
			}
			
			// Bar의 넓이가 컨트롤의 넓이 절반 보다 더 크다면
			if( mSliderInfo.rtBar.width() > mSliderInfo.rtScreen.width() / 2 ) {
				mSliderInfo.rtBar.right = mSliderInfo.rtBar.left + (mSliderInfo.rtScreen.width() / 2) / 2;
			}

			// Bar가 이동하는 영역좌표를 구한다
			nBarWidth = mSliderInfo.rtBar.width();
			mSliderInfo.rtBarMoveArea.left = 0;
			mSliderInfo.rtBarMoveArea.right = mSliderInfo.rtScreen.width() - nBarWidth;
			nBarHeight = mSliderInfo.rtBar.height();
			mSliderInfo.rtBarMoveArea.top = (mSliderInfo.rtScreen.height() - nBarHeight) / 2;
			mSliderInfo.rtBarMoveArea.bottom = mSliderInfo.rtBarMoveArea.top + nBarHeight;
		}

		nBarWidth = mSliderInfo.rtBar.width();
		nBarHeight = mSliderInfo.rtBar.height();
		
		// Bar가 위치해야할 포지션 좌표를 구해서 반환
		Point pointBar = getBarPosition();
		mSliderInfo.rtBar.left = pointBar.x;
		mSliderInfo.rtBar.right = mSliderInfo.rtBar.left + nBarWidth;
		mSliderInfo.rtBar.top = pointBar.y;
		mSliderInfo.rtBar.bottom = mSliderInfo.rtBar.top + nBarHeight;
	}

	// Bar가 위치해야할 포지션 좌표를 구해서 반환
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
			// 크기 변수 초기화
			initSizeVariable();
		}
		
		// Canvas에 배경 그리기
		drawBackground(canvas);
		// Canvas에 Bar 그리기
		drawBar(canvas);
	}
	
	//Canvas에 배경 그리기
	public void drawBackground(Canvas canvas) {
		Rect rtScreen = new Rect();
		rtScreen.set( mSliderInfo.rtScreen );
		rtScreen.left = 0;
		rtScreen.right = mSliderInfo.rtScreen.width();
		rtScreen.top = 0;
		rtScreen.bottom = mSliderInfo.rtScreen.height();
	
		// 배경 이미지가 존재한다면
		if( mSliderInfo.bitmapBackground != null ) {
			canvas.drawBitmap(mSliderInfo.bitmapBackground, null, rtScreen, null);
		}
		// 비경 이미지가 존재하지 않는다면
		else {
			Paint pnt = new Paint();
			pnt.setColor(mSliderInfo.colorBackground);
			canvas.drawRect(rtScreen, pnt);
		}
	}
	
	//Canvas에 Bar 그리기
	public void drawBar(Canvas canvas) {
		// Bar 상태가 Pressed 라면
		if( mSliderInfo.bBarPressed ) {
			// Bar Pressed 상태 이미지가 존재한다면
			if( mSliderInfo.bitmapBarPressed != null ) {
				canvas.drawBitmap(mSliderInfo.bitmapBarPressed, null, mSliderInfo.rtBar, null);
			}
			// Bar Pressed 상태 이미지가 존재하지 않는다면
			else {
				Paint pnt = new Paint();
				pnt.setColor(mSliderInfo.colorBarPressed);
				canvas.drawRect(mSliderInfo.rtBar, pnt);
			}
		}
		// Bar 상태가 Normal 이라면
		else {
			// Bar Normal 상태 이미지가 존재한다면
			if( mSliderInfo.bitmapBarNormal != null ) {
				canvas.drawBitmap(mSliderInfo.bitmapBarNormal, null, mSliderInfo.rtBar, null);
				//Log.d("Bar Normal", " : " + mSliderInfo.rtBar.left + " / " + mSliderInfo.rtBar.top + " / " + mSliderInfo.rtBar.right + " / " + mSliderInfo.rtBar.bottom);
			}
			// Bar Normal 상태 이미지가 존재하지 않는다면
			else {
				Paint pnt = new Paint();
				pnt.setColor(mSliderInfo.colorBarNormal);
				canvas.drawRect(mSliderInfo.rtBar, pnt);
			}
		}
	}
	
	// 범위 지정
	public void setRange(int nMin, int nMax) {
		setRange(nMin, nMax, false);
	}

	// 범위 지정
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
			// 크기 변수 초기화
			initSizeVariable();
		if( bRedraw )
			// 화면을 새로 그린다
			this.invalidate();
	}
	
	// 현재 값 지정
	public void setValue(int nValue) {
		setValue(nValue, false);
	}

	// 현재 값 지정
	public void setValue(int nValue, boolean bRedraw) {
		mSliderInfo.nValue = nValue;
		if( mSliderInfo.nRangeMin > mSliderInfo.nValue  )
			mSliderInfo.nRangeMin = mSliderInfo.nValue;
		if( mSliderInfo.nRangeMax < mSliderInfo.nValue )
			mSliderInfo.nRangeMax = mSliderInfo.nValue;

		// 크기 변수 초기화
		if( mFirstDraw == false )
			initSizeVariable();
		if( bRedraw )
			// 화면을 새로 그린다
			this.invalidate();
	}

	// 배경 이미지 파일 지정
	public void setBackgroundBitmap(int imageId) {
		mSliderInfo.bitmapBackground = BitmapFactory.decodeResource(getResources(), imageId); 
	}

	// 배경 이미지 파일 지정
	public void setBarNormalBitmap(int imageId) {
		mSliderInfo.bitmapBarNormal = BitmapFactory.decodeResource(getResources(), imageId);
		// Bar의 크기를 구한다
		mSliderInfo.rtBar.right = mSliderInfo.rtBar.left + mSliderInfo.bitmapBarNormal.getWidth();
		mSliderInfo.rtBar.bottom = mSliderInfo.rtBar.top + mSliderInfo.bitmapBarNormal.getHeight();

		if( mFirstDraw == false )
			// 크기 변수 초기화
			initSizeVariable();
	}

	// 배경 이미지 파일 지정
	public void setBarPressedBitmap(int imageId) {
		mSliderInfo.bitmapBarPressed = BitmapFactory.decodeResource(getResources(), imageId);
	}

	// Touch 이벤트 함수
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		
		// Touch 이벤트 Pressed
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			//Toast.makeText(this, "Touch Event Received", 0).show();
			// Bar 상태를 Pressed 로 설정
			mSliderInfo.bBarPressed = true;

			// Touch 위치로 Bar의 위치를 지정
			setBarPositionByTouchPoint(new Point((int)event.getX(), (int)event.getY()));

			// 화면을 새로 그린다
			this.invalidate();

			// 사용자 이벤트를 부모 윈도우에 전달
			if( mJSliderListener != null )
				mJSliderListener.onMoved(mSliderInfo.nValue);
		}
		// Touch 이벤트 Moved
		else if(event.getAction() == MotionEvent.ACTION_MOVE) {
			// Bar의 현재 Value
			int nValue = mSliderInfo.nValue;

			// Touch 위치로 Bar의 위치를 지정
			setBarPositionByTouchPoint(new Point((int)event.getX(), (int)event.getY()));

			// Bar의 Value값이 변경되지 않았다면 함수 탈출
			if( nValue == mSliderInfo.nValue )
				return true;

			// 화면을 새로 그린다
			this.invalidate();

			// 사용자 이벤트를 부모 윈도우에 전달
			if( mJSliderListener != null )
				mJSliderListener.onMoved(mSliderInfo.nValue);
		}
		// Touch 이벤트 Released
		else if(event.getAction() == MotionEvent.ACTION_UP) {
			// Bar 상태를 Normal 로 설정
			mSliderInfo.bBarPressed = false;

			// 화면을 새로 그린다
			this.invalidate();

			// 사용자 이벤트를 부모 윈도우에 전달
			if( mJSliderListener != null )
				mJSliderListener.onReleased(mSliderInfo.nValue);
		}
		return true;
	}

	// Touch 위치로 Bar의 위치를 지정
	public void setBarPositionByTouchPoint(Point pointTouch) {
		// Bar의 새로운 위치를 구한다
		int nBarWidth = mSliderInfo.rtBar.width(); 
		mSliderInfo.rtBar.left = pointTouch.x - (nBarWidth / 2);
		// Bar의 위치가 범위를 벗어난다면
		if( mSliderInfo.rtBar.left < 0 )
			mSliderInfo.rtBar.left = 0;

		if( mSliderInfo.rtBar.left > mSliderInfo.rtBarMoveArea.width() )
			mSliderInfo.rtBar.left = mSliderInfo.rtBarMoveArea.width();

		mSliderInfo.rtBar.right = mSliderInfo.rtBar.left + nBarWidth;

		// Bar 위치로 Value값을 구해서 반환
		mSliderInfo.nValue = getValueFromBarPosition();
	}

	// Bar 위치로 Value값을 구해서 반환
	public int getValueFromBarPosition()
	{
		double fPositonRate = 0.;
		int nValue = 0;

		// 전체 이동 영역에 대한 Bar 위치 비율
		fPositonRate = (double)mSliderInfo.rtBar.left / (double)mSliderInfo.rtBarMoveArea.width();

		// 범위 최대값, 최소값, Bar 위치 비율로 Value값을 구한다
		nValue = (int)((double)(mSliderInfo.nRangeMax - mSliderInfo.nRangeMin) * fPositonRate) + mSliderInfo.nRangeMin;

		return nValue;
	}

}


