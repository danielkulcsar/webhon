/* 만든이 : 정동근 (Jung, Dong-Geun)   */
/*    Mail : topofsan@naver.com        */

package com.example.jfireworksex;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class JFireWorks extends View {
	final int FIRE_TYPE_NORMAL=0;
	final int FIRE_TYPE_BROTHER=1;
	final int FIRE_TYPE_MULTIPLY_PENTA=2;
	final int FIRE_TYPE_HYDRA=3;
	final int FIRE_TYPE_COUNT=4;
	
	final int AUDIO_TYPE_FIREWORKS_FIRE=0;
	final int AUDIO_TYPE_FIREWORKS_BOOM=1;
	final int dfTIMER_INTERVER_FIREWORKS = 30;
	final int dfAUDIO_VOLUME_FIRE = 20;
	final int dfAUDIO_VOLUME_BOOM = 35;
	
	int mColorBackground;
	ArrayList<FireworksInfo> mArrayListFire;
	ArrayList<Integer> mArrayListColor;

	Canvas mCanvas;
	Bitmap mBmpCanvas;
	double mTempRadiusAngle;
	double mTempSinValue;
	int mMultiplyPentaCount;
	Random mRand;
	JFireWorks mView;
	int mDrawCount = 0;
	
	SoundPool mPool = null;
	int mBeepFire;
	int mBeepBoom;

	public JFireWorks(Context context, AttributeSet attrs) {
		super(context, attrs);

    	mColorBackground = Color.BLACK;
    	mTempRadiusAngle = 0.;
    	mTempSinValue = 0.;
    	mMultiplyPentaCount = 0;
    	
    	mRand = new Random();
    	mArrayListFire = new ArrayList<FireworksInfo>();
    	mArrayListColor = new ArrayList<Integer>();
        mView = this;
        mPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

    	InitColorTable();
	}
	
	public void setSoundId(Context context, int fireId, int boomId) {
		if( mPool != null ) {
			mBeepFire = mPool.load(context, fireId, 1);
			mBeepBoom = mPool.load(context, boomId, 1);
		}
	}

    public class FireworksInfo {
    	FireworksInfo() {
    		poTarget = new Point();
    		poStart = new Point();
    		poNow = new Point();
    		poPrev = new Point();
    	}
    	
	 	int nFireType;
	 	Point poTarget;
	 	Point poStart;
	 	Point poNow;
	 	Point poPrev;
	 	long nTimeCount;
	 	long nMaxCount;
	 	int nDrawLevel;
	 	int crOutLine;
	 	int nLineWidth;
    };

	 public void InitColorTable() {
	 	mArrayListColor.add(new Integer( Color.rgb(255,255,255) ));
	 	mArrayListColor.add(new Integer( Color.rgb(255,128,255) ));
	 	mArrayListColor.add(new Integer( Color.rgb(255,127,128) ));
	 	mArrayListColor.add(new Integer( Color.rgb(255,255,128) ));
	 	mArrayListColor.add(new Integer( Color.rgb(128,128,255) ));
	 	mArrayListColor.add(new Integer( Color.rgb(255,142,169) ));
	 	mArrayListColor.add(new Integer( Color.rgb(128,255,128) ));
	 }

		Handler mTimerDrawFire = new Handler() {
			public void handleMessage(Message msg) {
				DrawFireworks();

				mView.invalidate();
				mTimerDrawFire.sendEmptyMessageDelayed(0, dfTIMER_INTERVER_FIREWORKS);
			}
		};

		Handler mTimerPlayAudioBoom = new Handler() {
			public void handleMessage(Message msg) {
			}
		};

		Handler mTimerPlayAudioFire = new Handler() {
			public void handleMessage(Message msg) {
			}
		};

		Handler mTimerStopAudioFire = new Handler() {
			public void handleMessage(Message msg) {
			}
		};
		
		public boolean onTouchEvent(MotionEvent event) {
			super.onTouchEvent(event);
			if(event.getAction() == MotionEvent.ACTION_DOWN) {
				if( mDrawCount == 1 )
					mDrawCount ++;
			}
			else if(event.getAction() == MotionEvent.ACTION_UP) {
				CreateFire_Level0(event);
			}
			return true;
		}

		public void CreateFire_Level0(MotionEvent event) {
			FireworksInfo fireworksInfo = new FireworksInfo();

			fireworksInfo.nFireType = mRand.nextInt(FIRE_TYPE_COUNT);
			fireworksInfo.poTarget.x = (int)event.getX();
			fireworksInfo.poTarget.y = (int)event.getY();
			fireworksInfo.poNow = GetTailStartPoint(event);
			fireworksInfo.poPrev.x = fireworksInfo.poNow.x;
			fireworksInfo.poPrev.y = fireworksInfo.poNow.y;
			fireworksInfo.nTimeCount = 0;
			fireworksInfo.nMaxCount = 0;
			fireworksInfo.nDrawLevel = 0;
			fireworksInfo.crOutLine = Color.WHITE;
			fireworksInfo.nLineWidth = 5;

			mArrayListFire.add(fireworksInfo);

			mPool.play(mBeepFire, 1, 1, 0, 0, 1);
		}

		public Point GetTailStartPoint(MotionEvent event) {
			Point poTail = new Point();
			Rect rt = GetScreenRect();

			poTail.x = (int)event.getX();
			poTail.y = rt.bottom;

			return poTail;
		}
		
		public Rect GetScreenRect() {
			Rect rtScreen = new Rect();
			rtScreen.left = this.getLeft();
			rtScreen.top = this.getTop();
			rtScreen.right = this.getRight();
			rtScreen.bottom = this.getBottom();
			
			return rtScreen;
		}

		public void DrawFireworks() {
			int i=0, nFireCount;
			FireworksInfo fireworksInfo = null;

			nFireCount = mArrayListFire.size();

			for(i = nFireCount-1 ; i >= 0 ; i-- ) {
				fireworksInfo = mArrayListFire.get(i);

				switch( fireworksInfo.nDrawLevel ) {
				case 0:
					DrawFire_Level0_Normal(fireworksInfo);

					if( fireworksInfo.nDrawLevel == 1 ){
						switch( fireworksInfo.nFireType ) {
						case FIRE_TYPE_NORMAL:
							CreateFire_Level1_Normal(i);
							break;
						case FIRE_TYPE_BROTHER:
							CreateFire_Level1_Brother(i);
							break;
						case FIRE_TYPE_MULTIPLY_PENTA:
							CreateFire_Level1_MultiplyPenta(i);
							break;
						case FIRE_TYPE_HYDRA:
							CreateFire_Level1_Hydra(i);
							break;
						default:
							break;
						}
					}
					break;
				case 1:
					switch( fireworksInfo.nFireType ) {
					case FIRE_TYPE_NORMAL:
						DrawFire_Level1_Normal(fireworksInfo);
						break;
					case FIRE_TYPE_BROTHER:
						DrawFire_Level1_Normal(fireworksInfo);
						break;
					case FIRE_TYPE_MULTIPLY_PENTA:
						DrawFire_Level1_Normal(fireworksInfo);
						if( fireworksInfo.nDrawLevel == 2 ){
							CreateFire_Level1_MultiplyPenta(i);
						}
						break;
					case FIRE_TYPE_HYDRA:
						DrawFire_Level1_Foreground(fireworksInfo);
						if( fireworksInfo.nDrawLevel == 2 ){
							ChangeFire_Hydra_Erase(fireworksInfo);
						}
						break;
					default:
						break;
					}
					break;
				case 2:
					switch( fireworksInfo.nFireType ) {
					case FIRE_TYPE_NORMAL:
						DeleteFireInfo(i);
						break;
					case FIRE_TYPE_BROTHER:
						DeleteFireInfo(i);
						break;
					case FIRE_TYPE_MULTIPLY_PENTA:
						DrawFire_Level1_Normal(fireworksInfo);
						break;
					case FIRE_TYPE_HYDRA:
						DrawFire_Level1_Foreground(fireworksInfo);
						break;
					default:
						break;
					}
					break;
				default :
					DeleteFireInfo(i);
					break;
				}
			}
		}

		public void DrawFire_Level0_Normal(FireworksInfo fireworksInfo)
		{
			int nTailLength = 80;
			Point poNext = new Point();

			if( fireworksInfo.poNow.y != fireworksInfo.poPrev.y ) {
				DrawLineInCanvas(mCanvas, fireworksInfo.poPrev, fireworksInfo.poNow, mColorBackground, fireworksInfo.nLineWidth);

				if( fireworksInfo.poNow.y <= fireworksInfo.poTarget.y ) {
					fireworksInfo.nDrawLevel ++;
					return;
				}
			}

			poNext.x = fireworksInfo.poNow.x;
			poNext.y = fireworksInfo.poNow.y - nTailLength;
			if( poNext.y < fireworksInfo.poTarget.y ) {
				poNext.y = fireworksInfo.poTarget.y;
			}

			DrawLineInCanvas(mCanvas, fireworksInfo.poNow, poNext, fireworksInfo.crOutLine, fireworksInfo.nLineWidth);

			fireworksInfo.poPrev.x = fireworksInfo.poNow.x;
			fireworksInfo.poPrev.y = fireworksInfo.poNow.y;
			fireworksInfo.poNow.x = poNext.x;
			fireworksInfo.poNow.y = poNext.y;
		}

		public void CreateFire_Level1_Normal(int nFireNum) {
			FireworksInfo fireworksInfo = mArrayListFire.get(nFireNum);

			Point poStart = new Point();
			poStart.x = fireworksInfo.poTarget.x;
			poStart.y = fireworksInfo.poTarget.y;
			int nFireType = fireworksInfo.nFireType;

			DeleteFireInfo(nFireNum);

			int nNewFireCount = 18;
			double fMoveDistance = 200.;
			int nColorCount = mArrayListColor.size();
			int nColorNum = mRand.nextInt(nColorCount);
			int color = mArrayListColor.get(nColorNum);

			for(int i=0 ; i < 7 ; i++) {
				CreateFireworks_Level1_SameDistance(nFireType, nNewFireCount, fMoveDistance, poStart, color);

				nNewFireCount = (int)((double)nNewFireCount * 0.7);
				fMoveDistance = fMoveDistance * 0.7;	
				if( nNewFireCount < 2 )
					break;
			}

			mPool.stop(mBeepFire);
			mPool.play(mBeepBoom, 1, 1, 0, 0, 1);
		}

		public void CreateFire_Level1_Brother(int nFireNum) {
			FireworksInfo fireworksInfo = mArrayListFire.get(nFireNum);

			Point poStart = new Point();
			poStart.x = fireworksInfo.poTarget.x;
			poStart.y = fireworksInfo.poTarget.y;
			int nFireType = fireworksInfo.nFireType;

			DeleteFireInfo(nFireNum);

			int nNewFireCount = 21;
			double fMoveDistance = 230.;
			int nColorCount = mArrayListColor.size();
			int nColorNum1 = mRand.nextInt(nColorCount);
			int color = mArrayListColor.get(nColorNum1);

			for(int i=0 ; i < 8 ; i++) {
				CreateFireworks_Level1_SameDistance(nFireType, nNewFireCount, fMoveDistance, poStart, color);

				nNewFireCount = (int)((double)nNewFireCount * 0.7);
				fMoveDistance = fMoveDistance * 0.7;	
				if( nNewFireCount < 2 )
					break;

				int nColorNum2 = 0;
				if( i == 1 ) {
					do {
						nColorNum2 = mRand.nextInt(nColorCount);
					} while( nColorNum2 == nColorNum1 );
					color = mArrayListColor.get(nColorNum2);
				}
			}

			mPool.stop(mBeepFire);
			mPool.play(mBeepBoom, 1, 1, 0, 0, 1);
		}

		public void CreateFire_Level1_MultiplyPenta(int nFireNum) {
			FireworksInfo fireworksInfo = mArrayListFire.get(nFireNum);

			Point poStart = new Point();
			poStart.x = fireworksInfo.poTarget.x;
			poStart.y = fireworksInfo.poTarget.y;
			int nFireType = fireworksInfo.nFireType;
			int nDrawLevel = fireworksInfo.nDrawLevel;

			DeleteFireInfo(nFireNum);

			int nNewFireCount = 5;
			double fMoveDistance = 100.;
			if( nDrawLevel == 1 ) {
				fMoveDistance = 100.;
			}
			else {
				fMoveDistance = 70.;
			}
			int nColorCount = mArrayListColor.size();
			int nColorNum = mRand.nextInt(nColorCount);
			int color = mArrayListColor.get(nColorNum);

			CreateFireworks_Level1_SameDistance(nFireType, nNewFireCount, fMoveDistance, poStart, color, nDrawLevel);

			if( nDrawLevel == 2 ) {
				mMultiplyPentaCount ++;
				mMultiplyPentaCount %= 5;

				if( mMultiplyPentaCount != 0 ) {
					return;
				}
			}

			mPool.stop(mBeepFire);
			mPool.play(mBeepBoom, 1, 1, 0, 0, 1);
		}

		public void CreateFire_Level1_Hydra(int nFireNum) {
			FireworksInfo fireworksInfo = mArrayListFire.get(nFireNum);

			Point poStart = new Point();
			poStart.x = fireworksInfo.poTarget.x;
			poStart.y = fireworksInfo.poTarget.y;
			int nFireType = fireworksInfo.nFireType;

			DeleteFireInfo(nFireNum);

			int nNewFireCount = 10;
			double fMoveDistanceMax = 230.;
			double fMoveDistanceMin = 90.;	
			int nColorCount = mArrayListColor.size();
			int nColorNum1 = mRand.nextInt(nColorCount);
			int color = mArrayListColor.get(nColorNum1);

			CreateFireworks_Level1_DifferentDistance(nFireType, nNewFireCount, fMoveDistanceMax, fMoveDistanceMin, poStart, color);

			mPool.stop(mBeepFire);
			mPool.play(mBeepBoom, 1, 1, 0, 0, 1);
		}

		public void DrawFire_Level1_Foreground(FireworksInfo fireworksInfo) {
			Point poNext = new Point();
			int nGDistanceUnit = 4;

			double fSin = 0.;
			double fRadiusAngle = (double)fireworksInfo.nTimeCount / (double)fireworksInfo.nMaxCount * Math.PI / 2.;
			if( fRadiusAngle == mTempRadiusAngle ) {
				fSin = mTempSinValue;
			}
			else {
				fSin = Math.sin(fRadiusAngle);
				mTempRadiusAngle = fRadiusAngle;
				mTempSinValue = fSin;
			}

			double fDistanceX = (double)(fireworksInfo.poTarget.x - fireworksInfo.poStart.x) * fSin;
			poNext.x = fireworksInfo.poStart.x + (int)fDistanceX;

			double fDistanceY = (double)(fireworksInfo.poTarget.y - fireworksInfo.poStart.y) * fSin;
			poNext.y = fireworksInfo.poStart.y + (int)fDistanceY;

			poNext.y += (fireworksInfo.nTimeCount * nGDistanceUnit);

			DrawLineInCanvas(mCanvas, fireworksInfo.poNow, poNext, fireworksInfo.crOutLine, fireworksInfo.nLineWidth);

			if( fireworksInfo.crOutLine != mColorBackground ) {
				int nCrR=0, nCrG=0, nCrB=0, nCrGap=30;
				nCrR = Color.red(fireworksInfo.crOutLine) + nCrGap;
				nCrG = Color.red(fireworksInfo.crOutLine) + nCrGap;
				nCrB = Color.red(fireworksInfo.crOutLine) + nCrGap;

				if( nCrR > 255 )
					nCrR = 255;
				if( nCrG > 255 )
					nCrG = 255;
				if( nCrB > 255 )
					nCrB = 255;

				DrawLineInCanvas(mCanvas, fireworksInfo.poNow, poNext, Color.WHITE, 5);
			}

			fireworksInfo.poPrev = fireworksInfo.poNow;
			fireworksInfo.poNow = poNext;
			fireworksInfo.nTimeCount ++;

			if( fireworksInfo.nTimeCount >= fireworksInfo.nMaxCount ) {
				fireworksInfo.nDrawLevel ++;
				return;
			}

			Rect rt = GetScreenRect();
			if( fireworksInfo.poNow.x < rt.left || fireworksInfo.poNow.x > rt.left + rt.width()
					|| fireworksInfo.poNow.y < rt.top || fireworksInfo.poNow.y > rt.top + rt.height() ){
				fireworksInfo.nDrawLevel ++;
				return;
			}
		}

		public void ChangeFire_Hydra_Erase(FireworksInfo fireworksInfo) {
			fireworksInfo.poNow.x = fireworksInfo.poStart.x;
			fireworksInfo.poNow.y = fireworksInfo.poStart.y;
			fireworksInfo.poPrev.x = fireworksInfo.poStart.x;
			fireworksInfo.poPrev.y = fireworksInfo.poStart.y;
			fireworksInfo.nTimeCount = 0;
			fireworksInfo.crOutLine = mColorBackground;
		}

		public void DeleteFireInfo(int nFireNum) {
			mArrayListFire.remove(nFireNum);
		}

		public void DrawLineInCanvas(Canvas canvas, Point po1, Point po2, int crLine, int nLineWidth) {
			if( canvas == null )
				return;
			
			Paint pnt = new Paint();
			pnt.setColor(crLine);
			pnt.setStrokeWidth(nLineWidth);
			pnt.setStrokeCap(Paint.Cap.ROUND);
			canvas.drawLine(po1.x, po1.y, po2.x, po2.y, pnt);
		}
		
		public void CreateFireworks_Level1_SameDistance(int nFireType, int nFireCount,
				double fMoveDistance, Point poStart, int crFire) {
			CreateFireworks_Level1_SameDistance(nFireType, nFireCount,
					fMoveDistance, poStart, crFire, 1);
		}

		public void CreateFireworks_Level1_SameDistance(int nFireType, int nFireCount,
					double fMoveDistance, Point poStart, int crFire, int nDrawLevel)
		{
			int nLineWidth = 0;
			int nMaxCount = 0;

			switch( nFireType ) {
			case FIRE_TYPE_NORMAL:
				nLineWidth = 3;
				nMaxCount = 20;
				break;
			case FIRE_TYPE_BROTHER:
				nLineWidth = 3;
				nMaxCount = 20;
				break;
			case FIRE_TYPE_MULTIPLY_PENTA:
				nLineWidth = 7;
				nMaxCount = 12;
				break;
			case FIRE_TYPE_HYDRA:
				nLineWidth = 10;
				nMaxCount = 20;
				break;
			default :
				nLineWidth = 3;
				nMaxCount = 20;
				break;
			}

			FireworksInfo fireworksInfo = null;

			double fAngleGain = (double)(int)mRand.nextInt(90) / (double)180 * Math.PI;

			for(int i=0 ; i < nFireCount ; i++ ){
				fireworksInfo = new FireworksInfo();

				fireworksInfo.nFireType = nFireType;
				fireworksInfo.nDrawLevel = nDrawLevel;
				fireworksInfo.crOutLine = crFire;
				fireworksInfo.nLineWidth = nLineWidth;
				fireworksInfo.nMaxCount = nMaxCount;
				fireworksInfo.nTimeCount = 0;
				fireworksInfo.poStart.x = poStart.x;
				fireworksInfo.poStart.y = poStart.y;
				fireworksInfo.poNow.x = poStart.x;
				fireworksInfo.poNow.y = poStart.y;
				fireworksInfo.poPrev.x = poStart.x;
				fireworksInfo.poPrev.y = poStart.y;

				double rRadianAngle = (double)i / (double)nFireCount * Math.PI * 2. + fAngleGain;
				double fCos = Math.cos(rRadianAngle);
				double fSin = Math.sin(rRadianAngle);
				fireworksInfo.poTarget.x = poStart.x + (int)(fCos * fMoveDistance);
				fireworksInfo.poTarget.y = poStart.y + (int)(fSin * fMoveDistance);

				mArrayListFire.add(fireworksInfo);

				DrawFire_Level1_Normal(fireworksInfo);
			}
		}
		
		public void CreateFireworks_Level1_DifferentDistance(int nFireType, int nFireCount,
				double fMoveDistanceMax, double fMoveDistanceMin, Point poStart, int crFire) {
			CreateFireworks_Level1_DifferentDistance(nFireType, nFireCount,
					fMoveDistanceMax, fMoveDistanceMin, poStart, crFire, 1);
		}

		public void CreateFireworks_Level1_DifferentDistance(int nFireType, int nFireCount,
				double fMoveDistanceMax, double fMoveDistanceMin, Point poStart, int crFire, int nDrawLevel) {
			int nLineWidth = 0;
			int nMaxCount = 0;

			switch( nFireType ) {
			case FIRE_TYPE_HYDRA:
				nLineWidth = 11;
				nMaxCount = 30;
				break;
			default :
				nLineWidth = 11;
				nMaxCount = 30;
				break;
			}

			FireworksInfo fireworksInfo = null;

			double fAngleGain = (double)(int)mRand.nextInt(90) / (double)180 * Math.PI;

			for(int i=0 ; i < nFireCount ; i++ ){
				fireworksInfo = new FireworksInfo();

				fireworksInfo.nFireType = nFireType;
				fireworksInfo.nDrawLevel = nDrawLevel;
				fireworksInfo.crOutLine = crFire;
				fireworksInfo.nLineWidth = nLineWidth;
				fireworksInfo.nMaxCount = nMaxCount;
				fireworksInfo.nTimeCount = 0;
				fireworksInfo.poStart.x = poStart.x;
				fireworksInfo.poStart.y = poStart.y;
				fireworksInfo.poNow.x = poStart.x;
				fireworksInfo.poNow.y = poStart.y;
				fireworksInfo.poPrev.x = poStart.x;
				fireworksInfo.poPrev.y = poStart.y;

				double rRadianAngle = (double)i / (double)nFireCount * Math.PI * 2. + fAngleGain;
				double fCos = Math.cos(rRadianAngle);
				double fSin = Math.sin(rRadianAngle);

				double fMoveDistance = (double)(mRand.nextInt() % (int)(fMoveDistanceMax - fMoveDistanceMin)) + fMoveDistanceMin;
				fireworksInfo.poTarget.x = poStart.x + (int)(fCos * fMoveDistance);
				fireworksInfo.poTarget.y = poStart.y + (int)(fSin * fMoveDistance);

				mArrayListFire.add(fireworksInfo);

				DrawFire_Level1_Foreground(fireworksInfo);
			}
		}

		public void DrawFire_Level1_Normal(FireworksInfo fireworksInfo) {
			Point poNext = new Point();

			if( fireworksInfo.poNow.x != fireworksInfo.poPrev.x || fireworksInfo.poNow.y != fireworksInfo.poPrev.y ) {
				DrawLineInCanvas(mCanvas, fireworksInfo.poPrev, fireworksInfo.poNow, mColorBackground, fireworksInfo.nLineWidth);

				if( fireworksInfo.nTimeCount >= fireworksInfo.nMaxCount ) {
					fireworksInfo.nDrawLevel ++;
					return;
				}

				Rect rt = GetScreenRect();
				if( fireworksInfo.poNow.x < rt.left || fireworksInfo.poNow.x > rt.left + rt.width()
						|| fireworksInfo.poNow.y < rt.top || fireworksInfo.poNow.y > rt.top + rt.height() ){
					fireworksInfo.nDrawLevel ++;
					return;
				}
			}

			double fSin = 0.;
			double fRadiusAngle = (double)fireworksInfo.nTimeCount / (double)fireworksInfo.nMaxCount * Math.PI / 2.;
			if( fRadiusAngle == mTempRadiusAngle ) {
				fSin = mTempSinValue;
			}
			else {
				fSin = Math.sin(fRadiusAngle);
				mTempRadiusAngle = fRadiusAngle;
				mTempSinValue = fSin;
			}

			double fDistanceX = (double)(fireworksInfo.poTarget.x - fireworksInfo.poStart.x) * fSin;
			poNext.x = fireworksInfo.poStart.x + (int)fDistanceX;

			double fDistanceY = (double)(fireworksInfo.poTarget.y - fireworksInfo.poStart.y) * fSin;
			poNext.y = fireworksInfo.poStart.y + (int)fDistanceY;

			DrawLineInCanvas(mCanvas, fireworksInfo.poNow, poNext, fireworksInfo.crOutLine, fireworksInfo.nLineWidth);

			fireworksInfo.poPrev.x = fireworksInfo.poNow.x;
			fireworksInfo.poPrev.y = fireworksInfo.poNow.y;
			fireworksInfo.poNow.x = poNext.x;
			fireworksInfo.poNow.y = poNext.y;
			fireworksInfo.nTimeCount ++;
		}

		public void onDraw(Canvas canvas) {
			Rect rtDest = new Rect(0, 0, this.getWidth(), this.getHeight());
			
			if( mDrawCount == 0 ) {
				mDrawCount ++;
				
				mBmpCanvas = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
				mCanvas = new Canvas(mBmpCanvas);
				Paint pnt = new Paint();
				pnt.setColor(Color.BLACK);
				mCanvas.drawRect(rtDest, pnt);
				
				String strText = "Touch Screen";
				pnt.setTextSize(25);
				pnt.setARGB(255,  64, 64, 64);
				mCanvas.drawText(strText, 100, this.getHeight() / 2, pnt);
				
				mTimerDrawFire.sendEmptyMessageDelayed(0, dfTIMER_INTERVER_FIREWORKS);
			}
			else if( mDrawCount == 2 ) {
				mDrawCount ++;
				Paint pnt = new Paint();
				pnt.setColor(Color.BLACK);
				mCanvas.drawRect(rtDest, pnt);				
			}
			
			canvas.drawBitmap(mBmpCanvas, null, rtDest, null);
		}
}
