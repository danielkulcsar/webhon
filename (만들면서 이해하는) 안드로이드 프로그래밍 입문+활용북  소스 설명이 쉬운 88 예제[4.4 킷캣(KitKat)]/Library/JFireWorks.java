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

        // 멤버변수 초기화
    	mColorBackground = Color.BLACK;
    	mTempRadiusAngle = 0.;		// 임시 각도
    	mTempSinValue = 0.;			// 임시 Sin값
    	mMultiplyPentaCount = 0;		// MultiplyPenta 불꽃 오디오 재생용 카운트
    	
    	mRand = new Random();			// 난수 발생기 생성
    	// Array 변수 생성
    	mArrayListFire = new ArrayList<FireworksInfo>();
    	mArrayListColor = new ArrayList<Integer>();
        mView = this;
        // 효과음 재생기 생성
        mPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

    	// 컬러 테이블 초기화
    	InitColorTable();
	}
	
	public void setSoundId(Context context, int fireId, int boomId) {
		if( mPool != null ) {
			// 효과음 파일 로딩
			mBeepFire = mPool.load(context, fireId, 1);
			mBeepBoom = mPool.load(context, boomId, 1);
		}
	}

    // 불꽃 정보 구조체 클래스
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

	 // 컬러 테이블 초기화
	 public void InitColorTable() {
	 	mArrayListColor.add(new Integer( Color.rgb(255,255,255) ));
	 	mArrayListColor.add(new Integer( Color.rgb(255,128,255) ));
	 	mArrayListColor.add(new Integer( Color.rgb(255,127,128) ));
	 	mArrayListColor.add(new Integer( Color.rgb(255,255,128) ));
	 	mArrayListColor.add(new Integer( Color.rgb(128,128,255) ));
	 	mArrayListColor.add(new Integer( Color.rgb(255,142,169) ));
	 	mArrayListColor.add(new Integer( Color.rgb(128,255,128) ));
	 }

		// 불꽃을 화면에 그리는 타이머
		Handler mTimerDrawFire = new Handler() {
			public void handleMessage(Message msg) {
				// 모든 불꽃을 화면에 그리는 함수
				DrawFireworks();

				// 불꽃을 화면에 표시
				mView.invalidate();
				// 타이머 재가동
				mTimerDrawFire.sendEmptyMessageDelayed(0, dfTIMER_INTERVER_FIREWORKS);
			}
		};

		// 불꽃 폭발 소리를 재생하는 타이머
		Handler mTimerPlayAudioBoom = new Handler() {
			public void handleMessage(Message msg) {
			}
		};

		// 불꽃 발사 소리를 재생하는 타이머
		Handler mTimerPlayAudioFire = new Handler() {
			public void handleMessage(Message msg) {
			}
		};

		// 불꽃 발사 소리를 중지하는 타이머
		Handler mTimerStopAudioFire = new Handler() {
			public void handleMessage(Message msg) {
			}
		};
		
		// Touch 이벤트 함수
		public boolean onTouchEvent(MotionEvent event) {
			super.onTouchEvent(event);
			if(event.getAction() == MotionEvent.ACTION_DOWN) {
				if( mDrawCount == 1 )
					mDrawCount ++;
			}
			else if(event.getAction() == MotionEvent.ACTION_UP) {
				// 한개의 불꽃을 생성
				CreateFire_Level0(event);
			}
			return true;
		}

		// 한개의 불꽃을 생성
		public void CreateFire_Level0(MotionEvent event) {
			// 불꽃 정보 구조체 생성
			FireworksInfo fireworksInfo = new FireworksInfo();

			// < 불꽃 정보 구조체 초기화 >
			// 불꽃 종류
			fireworksInfo.nFireType = mRand.nextInt(FIRE_TYPE_COUNT);
			// 목표 지점
			fireworksInfo.poTarget.x = (int)event.getX();
			fireworksInfo.poTarget.y = (int)event.getY();
			// 꼬리가 시작되는 위치를 계산해서 반환
			fireworksInfo.poNow = GetTailStartPoint(event);
			fireworksInfo.poPrev.x = fireworksInfo.poNow.x;
			fireworksInfo.poPrev.y = fireworksInfo.poNow.y;
			fireworksInfo.nTimeCount = 0;
			fireworksInfo.nMaxCount = 0;
			fireworksInfo.nDrawLevel = 0;
			fireworksInfo.crOutLine = Color.WHITE;
			fireworksInfo.nLineWidth = 5;

			// 불꽃 정보 구조체를 Array 변수에 추가
			mArrayListFire.add(fireworksInfo);

			// 불꽃 발사 오디오 파일 재생
			mPool.play(mBeepFire, 1, 1, 0, 0, 1);
		}

		// 꼬리가 시작되는 위치를 계산해서 반환
		public Point GetTailStartPoint(MotionEvent event) {
			Point poTail = new Point();
			Rect rt = GetScreenRect();

			poTail.x = (int)event.getX();
			poTail.y = rt.bottom;

			return poTail;
		}
		
		// 화면 영역좌표 반환
		public Rect GetScreenRect() {
			Rect rtScreen = new Rect();
			rtScreen.left = this.getLeft();
			rtScreen.top = this.getTop();
			rtScreen.right = this.getRight();
			rtScreen.bottom = this.getBottom();
			
			return rtScreen;
		}

		// 모든 불꽃을 화면에 그리는 함수
		public void DrawFireworks() {
			int i=0, nFireCount;
			FireworksInfo fireworksInfo = null;

			// Array 변수에 저장된 불꽃의 개수를 구한다
			nFireCount = mArrayListFire.size();

			// 불꽃 정보를 하나씩 추출하여 화면에 그린다
			for(i = nFireCount-1 ; i >= 0 ; i-- ) {
				fireworksInfo = mArrayListFire.get(i);

				// 그리기 단계에 따라서 구분 처리
				switch( fireworksInfo.nDrawLevel ) {
				// 0단계 일때
				case 0:
					// 0단계의 불꽃을 그리는 함수
					DrawFire_Level0_Normal(fireworksInfo);

					// 불꽃 그리기 단계가 증가했다면
					if( fireworksInfo.nDrawLevel == 1 ){
						// 불꽃 종류에 따라서 구분 처리
						switch( fireworksInfo.nFireType ) {
						case FIRE_TYPE_NORMAL:
							// Normal 종류의 1단계 불꽃을 생성
							CreateFire_Level1_Normal(i);
							break;
						case FIRE_TYPE_BROTHER:
							// Brother 종류의 1단계 불꽃을 생성
							CreateFire_Level1_Brother(i);
							break;
						case FIRE_TYPE_MULTIPLY_PENTA:
							// Multiply Penta 종류의 1단계 불꽃을 생성
							CreateFire_Level1_MultiplyPenta(i);
							break;
						case FIRE_TYPE_HYDRA:
							// Hydra 종류의 1단계 불꽃을 생성
							CreateFire_Level1_Hydra(i);
							break;
						default:
							break;
						}
					}
					break;
				// 1단계 일때
				case 1:
					// 불꽃 종류에 따라서 구분 처리
					switch( fireworksInfo.nFireType ) {
					case FIRE_TYPE_NORMAL:
						// 1단계의 불꽃을 그리는 함수(이전 라인을 지우고 새로운 라인을 그린다)
						DrawFire_Level1_Normal(fireworksInfo);
						break;
					case FIRE_TYPE_BROTHER:
						// 1단계의 불꽃을 그리는 함수(이전 라인을 지우고 새로운 라인을 그린다)
						DrawFire_Level1_Normal(fireworksInfo);
						break;
					case FIRE_TYPE_MULTIPLY_PENTA:
						// 1단계의 불꽃을 그리는 함수(이전 라인을 지우고 새로운 라인을 그린다)
						DrawFire_Level1_Normal(fireworksInfo);
						// 불꽃 그리기 단계가 증가했다면
						if( fireworksInfo.nDrawLevel == 2 ){
							// Multiply Penta 종류의 1단계 불꽃을 생성
							CreateFire_Level1_MultiplyPenta(i);
						}
						break;
					case FIRE_TYPE_HYDRA:
						// 1단계의 불꽃 라인을 그리는 함수(새로운 라인만을 그린다)
						DrawFire_Level1_Foreground(fireworksInfo);
						// 불꽃 그리기 단계가 증가했다면
						if( fireworksInfo.nDrawLevel == 2 ){
							// Hydra 불꽃 정보를 삭제 모드로 변환
							ChangeFire_Hydra_Erase(fireworksInfo);
						}
						break;
					default:
						break;
					}
					break;
					// 2단계 일때
				case 2:
					// 불꽃 종류에 따라서 구분 처리
					switch( fireworksInfo.nFireType ) {
					case FIRE_TYPE_NORMAL:
						// Array 변수에서 1개의 불꽃 정보를 삭제
						DeleteFireInfo(i);
						break;
					case FIRE_TYPE_BROTHER:
						// Array 변수에서 1개의 불꽃 정보를 삭제
						DeleteFireInfo(i);
						break;
					case FIRE_TYPE_MULTIPLY_PENTA:
						// 1단계의 불꽃을 그리는 함수(이전 라인을 지우고 새로운 라인을 그린다)
						DrawFire_Level1_Normal(fireworksInfo);
						break;
					case FIRE_TYPE_HYDRA:
						// 1단계의 불꽃 라인을 그리는 함수(새로운 라인만을 그린다)
						DrawFire_Level1_Foreground(fireworksInfo);
						break;
					default:
						break;
					}
					break;
				// 3단계 일때
				default :
					// Array 변수에서 1개의 불꽃 정보를 삭제
					DeleteFireInfo(i);
					break;
				}
			}
		}

		// 0단계의 불꽃을 그리는 함수
		public void DrawFire_Level0_Normal(FireworksInfo fireworksInfo)
		{
			int nTailLength = 80;	// 불꽃 꼬리 길이
			Point poNext = new Point();		// 다음번 불꽃 위치

			// 이전에 그렸던 불꽃 꼬리를 지운다
			if( fireworksInfo.poNow.y != fireworksInfo.poPrev.y ) {
				// 이전에 Canvas에 그렸던 불꽃 꼬리를 지운다
				DrawLineInCanvas(mCanvas, fireworksInfo.poPrev, fireworksInfo.poNow, mColorBackground, fireworksInfo.nLineWidth);

				// 꼬리가 목표 지점에 도달했다면 함수 탈출
				if( fireworksInfo.poNow.y <= fireworksInfo.poTarget.y ) {
					fireworksInfo.nDrawLevel ++;
					return;
				}
			}

			// 불꽃 꼬리의 다음 위치를 구한다
			poNext.x = fireworksInfo.poNow.x;
			poNext.y = fireworksInfo.poNow.y - nTailLength;
			if( poNext.y < fireworksInfo.poTarget.y ) {
				poNext.y = fireworksInfo.poTarget.y;
			}

			// Canvas에 불꽃 꼬리를 그린다
			DrawLineInCanvas(mCanvas, fireworksInfo.poNow, poNext, fireworksInfo.crOutLine, fireworksInfo.nLineWidth);

			// 이전 위치와 현재 위치를 갱신
			fireworksInfo.poPrev.x = fireworksInfo.poNow.x;
			fireworksInfo.poPrev.y = fireworksInfo.poNow.y;
			fireworksInfo.poNow.x = poNext.x;
			fireworksInfo.poNow.y = poNext.y;
		}

		// Normal 종류의 1단계 불꽃을 생성
		public void CreateFire_Level1_Normal(int nFireNum) {
			// Array 변수에서 불꽃 정보 구조체를 구한다
			FireworksInfo fireworksInfo = mArrayListFire.get(nFireNum);

			// 불꽃 시작 위치를 구한다
			Point poStart = new Point();
			poStart.x = fireworksInfo.poTarget.x;
			poStart.y = fireworksInfo.poTarget.y;
			int nFireType = fireworksInfo.nFireType;

			// Array 변수에서 0단계의 불꽃 정보를 삭제
			DeleteFireInfo(nFireNum);

			// < 가장 바깥쪽 불꽃의 정보 구조체 생성해서 Array 변수에 추가 >
			int nNewFireCount = 18;			// 새로운 불꽃 개수
			double fMoveDistance = 200.;	// 최대 이동 거리
			int nColorCount = mArrayListColor.size();	// 선택 가능한 컬러 개수
			int nColorNum = mRand.nextInt(nColorCount);
			int color = mArrayListColor.get(nColorNum);

			// < 불꽃의 정보 구조체 생성해서 Array 변수에 추가 >
			for(int i=0 ; i < 7 ; i++) {
				// 1단계 동일한 거리에 있는 불꽃들을 생성
				CreateFireworks_Level1_SameDistance(nFireType, nNewFireCount, fMoveDistance, poStart, color);

				// 불꽃의 위치와 개수를 조정
				nNewFireCount = (int)((double)nNewFireCount * 0.7);			// 새로운 불꽃 개수
				fMoveDistance = fMoveDistance * 0.7;	// 최대 이동 거리
				if( nNewFireCount < 2 )
					break;
			}

			// 불꽃 발사 오디오 파일 재생 중지
			mPool.stop(mBeepFire);
			// 불꽃 폭발 오디오 파일 재생
			mPool.play(mBeepBoom, 1, 1, 0, 0, 1);
		}

		// Brother 종류의 1단계 불꽃을 생성
		public void CreateFire_Level1_Brother(int nFireNum) {
			// Array 변수에서 불꽃 정보 구조체를 구한다
			FireworksInfo fireworksInfo = mArrayListFire.get(nFireNum);

			// 불꽃 시작 위치를 구한다
			Point poStart = new Point();
			poStart.x = fireworksInfo.poTarget.x;
			poStart.y = fireworksInfo.poTarget.y;
			int nFireType = fireworksInfo.nFireType;

			// Array 변수에서 0단계의 불꽃 정보를 삭제
			DeleteFireInfo(nFireNum);

			// < 가장 바깥쪽 불꽃의 정보 구조체 생성해서 Array 변수에 추가 >
			int nNewFireCount = 21;			// 새로운 불꽃 개수
			double fMoveDistance = 230.;	// 최대 이동 거리
			int nColorCount = mArrayListColor.size();	// 선택 가능한 컬러 개수
			int nColorNum1 = mRand.nextInt(nColorCount);	// 난수로 컬러 번호 선택
			int color = mArrayListColor.get(nColorNum1);

			// < 불꽃의 정보 구조체 생성해서 Array 변수에 추가 >
			for(int i=0 ; i < 8 ; i++) {
				// 1단계 동일한 거리에 있는 불꽃들을 생성
				CreateFireworks_Level1_SameDistance(nFireType, nNewFireCount, fMoveDistance, poStart, color);

				// 불꽃의 위치와 개수를 조정
				nNewFireCount = (int)((double)nNewFireCount * 0.7);			// 새로운 불꽃 개수
				fMoveDistance = fMoveDistance * 0.7;	// 최대 이동 거리
				if( nNewFireCount < 2 )
					break;

				// 안쪽 불꽃의 컬러를 변경
				int nColorNum2 = 0;
				if( i == 1 ) {
					do {
						nColorNum2 = mRand.nextInt(nColorCount);	// 난수로 컬러 번호 선택
					} while( nColorNum2 == nColorNum1 );
					color = mArrayListColor.get(nColorNum2);
				}
			}

			// 불꽃 발사 오디오 파일 재생 중지
			mPool.stop(mBeepFire);
			// 불꽃 폭발 오디오 파일 재생
			mPool.play(mBeepBoom, 1, 1, 0, 0, 1);
		}

		// Multiply Penta 종류의 1단계 불꽃을 생성
		public void CreateFire_Level1_MultiplyPenta(int nFireNum) {
			// Array 변수에서 불꽃 정보 구조체를 구한다
			FireworksInfo fireworksInfo = mArrayListFire.get(nFireNum);

			// 불꽃 시작 위치를 구한다
			Point poStart = new Point();
			poStart.x = fireworksInfo.poTarget.x;
			poStart.y = fireworksInfo.poTarget.y;
			int nFireType = fireworksInfo.nFireType;
			int nDrawLevel = fireworksInfo.nDrawLevel;

			// Array 변수에서 0단계의 불꽃 정보를 삭제
			DeleteFireInfo(nFireNum);

			// < 불꽃의 정보 구조체 생성해서 Array 변수에 추가 >
			int nNewFireCount = 5;			// 새로운 불꽃 개수
			double fMoveDistance = 100.;	// 최대 이동 거리
			if( nDrawLevel == 1 ) {
				fMoveDistance = 100.;	// 최대 이동 거리
			}
			else {
				fMoveDistance = 70.;	// 최대 이동 거리
			}
			int nColorCount = mArrayListColor.size();	// 선택 가능한 컬러 개수
			int nColorNum = mRand.nextInt(nColorCount);
			int color = mArrayListColor.get(nColorNum);

			// < 불꽃의 정보 구조체 생성해서 Array 변수에 추가 >
			// 1단계 동일한 거리에 있는 불꽃들을 생성
			CreateFireworks_Level1_SameDistance(nFireType, nNewFireCount, fMoveDistance, poStart, color, nDrawLevel);

			// 그리기 단계가 2단계라면
			if( nDrawLevel == 2 ) {
				// MultiplyPenta 불꽃 오디오 재생용 카운트
				mMultiplyPentaCount ++;
				mMultiplyPentaCount %= 5;

				if( mMultiplyPentaCount != 0 ) {
					return;
				}
			}

			// 불꽃 발사 오디오 파일 재생 중지
			mPool.stop(mBeepFire);
			// 불꽃 폭발 오디오 파일 재생
			mPool.play(mBeepBoom, 1, 1, 0, 0, 1);
		}

		// Hydra 종류의 1단계 불꽃을 생성
		public void CreateFire_Level1_Hydra(int nFireNum) {
			// Array 변수에서 불꽃 정보 구조체를 구한다
			FireworksInfo fireworksInfo = mArrayListFire.get(nFireNum);

			// 불꽃 시작 위치를 구한다
			Point poStart = new Point();
			poStart.x = fireworksInfo.poTarget.x;
			poStart.y = fireworksInfo.poTarget.y;
			int nFireType = fireworksInfo.nFireType;

			// Array 변수에서 0단계의 불꽃 정보를 삭제
			DeleteFireInfo(nFireNum);

			// < 가장 바깥쪽 불꽃의 정보 구조체 생성해서 Array 변수에 추가 >
			int nNewFireCount = 10;			// 새로운 불꽃 개수
			double fMoveDistanceMax = 230.;	// 최대 이동 거리
			double fMoveDistanceMin = 90.;	// 최소 이동 거리
			int nColorCount = mArrayListColor.size();	// 선택 가능한 컬러 개수
			int nColorNum1 = mRand.nextInt(nColorCount);	// 난수로 컬러 번호 선택
			int color = mArrayListColor.get(nColorNum1);

			// < 불꽃의 정보 구조체 생성해서 Array 변수에 추가 >
			// 1단계 서로 다른 거리에 있는 불꽃들을 생성
			CreateFireworks_Level1_DifferentDistance(nFireType, nNewFireCount, fMoveDistanceMax, fMoveDistanceMin, poStart, color);

			// 불꽃 발사 오디오 파일 재생 중지
			mPool.stop(mBeepFire);
			// 불꽃 폭발 오디오 파일 재생
			mPool.play(mBeepBoom, 1, 1, 0, 0, 1);
		}

		// 1단계의 불꽃 라인을 그리는 함수
		public void DrawFire_Level1_Foreground(FireworksInfo fireworksInfo) {
			Point poNext = new Point();
			int nGDistanceUnit = 4;		// 중력 이동 거리 단위

			// <불꽃 꼬리의 다음 위치를 구한다>
			double fSin = 0.;	// Sin값 결과
			// Sin값을 구하기 위한 각도 계산
			double fRadiusAngle = (double)fireworksInfo.nTimeCount / (double)fireworksInfo.nMaxCount * Math.PI / 2.;
			// 각도가 멤버변수에 저장된 값과 동일하다면
			if( fRadiusAngle == mTempRadiusAngle ) {
				// Sin값 결과
				fSin = mTempSinValue;
			}
			else {
				// Sin값 결과
				fSin = Math.sin(fRadiusAngle);
				// 계산된 Sin값을 멤버변수에 저장
				mTempRadiusAngle = fRadiusAngle;
				mTempSinValue = fSin;
			}

			// 불꽃 꼬리 다음 위치 X좌표
			double fDistanceX = (double)(fireworksInfo.poTarget.x - fireworksInfo.poStart.x) * fSin;
			poNext.x = fireworksInfo.poStart.x + (int)fDistanceX;

			// 불꽃 꼬리 다음 위치 Y좌표
			double fDistanceY = (double)(fireworksInfo.poTarget.y - fireworksInfo.poStart.y) * fSin;
			poNext.y = fireworksInfo.poStart.y + (int)fDistanceY;

			// 중력에 의한 이동 거리를 추가한다
			poNext.y += (fireworksInfo.nTimeCount * nGDistanceUnit);

			// Canvas에 불꽃 꼬리를 그린다
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

			// 이전 위치와 현재 위치를 갱신
			fireworksInfo.poPrev = fireworksInfo.poNow;
			fireworksInfo.poNow = poNext;
			fireworksInfo.nTimeCount ++;

			// 꼬리가 목표 지점에 도달했다면 함수 탈출
			if( fireworksInfo.nTimeCount >= fireworksInfo.nMaxCount ) {
				fireworksInfo.nDrawLevel ++;
				return;
			}

			// 화면 영역좌표
			Rect rt = GetScreenRect();
			if( fireworksInfo.poNow.x < rt.left || fireworksInfo.poNow.x > rt.left + rt.width()
					|| fireworksInfo.poNow.y < rt.top || fireworksInfo.poNow.y > rt.top + rt.height() ){
				fireworksInfo.nDrawLevel ++;
				return;
			}
		}

		// Hydra 불꽃 정보를 삭제 모드로 변환
		public void ChangeFire_Hydra_Erase(FireworksInfo fireworksInfo) {
			// 현재 위치를 시작 위치로 초기화
			fireworksInfo.poNow.x = fireworksInfo.poStart.x;
			fireworksInfo.poNow.y = fireworksInfo.poStart.y;
			fireworksInfo.poPrev.x = fireworksInfo.poStart.x;
			fireworksInfo.poPrev.y = fireworksInfo.poStart.y;
			fireworksInfo.nTimeCount = 0;
			fireworksInfo.crOutLine = mColorBackground;
		}

		// Array 변수에서 1개의 불꽃 정보를 삭제
		public void DeleteFireInfo(int nFireNum) {
			// Array 변수에서 불꽃 정보 구조체를 삭제
			mArrayListFire.remove(nFireNum);
		}

		// Canvas에 컬러와 두께를 지정해서 라인을 그리는 함수
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

		// 1단계 동일한 거리에 있는 불꽃들을 생성
		public void CreateFireworks_Level1_SameDistance(int nFireType, int nFireCount,
					double fMoveDistance, Point poStart, int crFire, int nDrawLevel)
		{
			int nLineWidth = 0;		// 라인 두께
			int nMaxCount = 0;		// 그리기 회수

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

			// 랜덤으로 각도에 변화를 준다
			double fAngleGain = (double)(int)mRand.nextInt(90) / (double)180 * Math.PI;

			for(int i=0 ; i < nFireCount ; i++ ){
				// 불꽃 정보 구조체 생성
				fireworksInfo = new FireworksInfo();

				// 불꽃 종류
				fireworksInfo.nFireType = nFireType;
				//pInfo->pPlayer = null;
				// 그리기 단계 설정
				fireworksInfo.nDrawLevel = nDrawLevel;
				// 바깥쪽 라인 컬러
				fireworksInfo.crOutLine = crFire;
				// 라인 두께
				fireworksInfo.nLineWidth = nLineWidth;
				// 최대 그리기 회수
				fireworksInfo.nMaxCount = nMaxCount;
				// 현재까지 그린 회수
				fireworksInfo.nTimeCount = 0;
				// 시작 위치 초기화
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

				// 새로 생성한 불꽃 정보를 Array 변수에 추가
				mArrayListFire.add(fireworksInfo);

				// 1단계의 불꽃을 그리는 함수(이전 라인을 지우고 새로운 라인을 그린다)
				DrawFire_Level1_Normal(fireworksInfo);
			}
		}
		
		public void CreateFireworks_Level1_DifferentDistance(int nFireType, int nFireCount,
				double fMoveDistanceMax, double fMoveDistanceMin, Point poStart, int crFire) {
			CreateFireworks_Level1_DifferentDistance(nFireType, nFireCount,
					fMoveDistanceMax, fMoveDistanceMin, poStart, crFire, 1);
		}

		// 1단계 서로 다른 거리에 있는 불꽃들을 생성
		public void CreateFireworks_Level1_DifferentDistance(int nFireType, int nFireCount,
				double fMoveDistanceMax, double fMoveDistanceMin, Point poStart, int crFire, int nDrawLevel) {
			int nLineWidth = 0;		// 라인 두께
			int nMaxCount = 0;		// 그리기 회수

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

			// 랜덤으로 각도에 변화를 준다
			double fAngleGain = (double)(int)mRand.nextInt(90) / (double)180 * Math.PI;

			for(int i=0 ; i < nFireCount ; i++ ){
				// 불꽃 정보 구조체 생성
				fireworksInfo = new FireworksInfo();

				// 불꽃 종류
				fireworksInfo.nFireType = nFireType;
				// 그리기 단계 설정
				fireworksInfo.nDrawLevel = nDrawLevel;
				// 바깥쪽 라인 컬러
				fireworksInfo.crOutLine = crFire;
				// 라인 두께
				fireworksInfo.nLineWidth = nLineWidth;
				// 최대 그리기 회수
				fireworksInfo.nMaxCount = nMaxCount;
				// 현재까지 그린 회수
				fireworksInfo.nTimeCount = 0;
				// 시작 위치 초기화
				fireworksInfo.poStart.x = poStart.x;
				fireworksInfo.poStart.y = poStart.y;
				fireworksInfo.poNow.x = poStart.x;
				fireworksInfo.poNow.y = poStart.y;
				fireworksInfo.poPrev.x = poStart.x;
				fireworksInfo.poPrev.y = poStart.y;

				double rRadianAngle = (double)i / (double)nFireCount * Math.PI * 2. + fAngleGain;
				double fCos = Math.cos(rRadianAngle);
				double fSin = Math.sin(rRadianAngle);

				// 난수로 최대 이동거리를 구한다
				double fMoveDistance = (double)(mRand.nextInt() % (int)(fMoveDistanceMax - fMoveDistanceMin)) + fMoveDistanceMin;
				fireworksInfo.poTarget.x = poStart.x + (int)(fCos * fMoveDistance);
				fireworksInfo.poTarget.y = poStart.y + (int)(fSin * fMoveDistance);

				// 새로 생성한 불꽃 정보를 Array 변수에 추가
				mArrayListFire.add(fireworksInfo);

				// 1단계의 불꽃 라인을 그리는 함수
				DrawFire_Level1_Foreground(fireworksInfo);
			}
		}

		// Normal 형식의 1단계의 불꽃을 그리는 함수(이전 라인을 지우고 새로운 라인을 그린다)
		public void DrawFire_Level1_Normal(FireworksInfo fireworksInfo) {
			Point poNext = new Point();

			// 이전에 그렸던 불꽃 꼬리를 지운다
			if( fireworksInfo.poNow.x != fireworksInfo.poPrev.x || fireworksInfo.poNow.y != fireworksInfo.poPrev.y ) {
				// 이전에 Canvas에 그렸던 불꽃 꼬리를 지운다
				DrawLineInCanvas(mCanvas, fireworksInfo.poPrev, fireworksInfo.poNow, mColorBackground, fireworksInfo.nLineWidth);

				// 꼬리가 목표 지점에 도달했다면 함수 탈출
				if( fireworksInfo.nTimeCount >= fireworksInfo.nMaxCount ) {
					fireworksInfo.nDrawLevel ++;
					return;
				}

				// 화면 영역좌표
				Rect rt = GetScreenRect();
				if( fireworksInfo.poNow.x < rt.left || fireworksInfo.poNow.x > rt.left + rt.width()
						|| fireworksInfo.poNow.y < rt.top || fireworksInfo.poNow.y > rt.top + rt.height() ){
					fireworksInfo.nDrawLevel ++;
					return;
				}
			}

			// <불꽃 꼬리의 다음 위치를 구한다>
			double fSin = 0.;	// Sin값 결과
			// Sin값을 구하기 위한 각도 계산
			double fRadiusAngle = (double)fireworksInfo.nTimeCount / (double)fireworksInfo.nMaxCount * Math.PI / 2.;
			// 각도가 멤버변수에 저장된 값과 동일하다면
			if( fRadiusAngle == mTempRadiusAngle ) {
				// Sin값 결과
				fSin = mTempSinValue;
			}
			else {
				// Sin값 결과
				fSin = Math.sin(fRadiusAngle);
				// 계산된 Sin값을 멤버변수에 저장
				mTempRadiusAngle = fRadiusAngle;
				mTempSinValue = fSin;
			}

			// 불꽃 꼬리 다음 위치 X좌표
			double fDistanceX = (double)(fireworksInfo.poTarget.x - fireworksInfo.poStart.x) * fSin;
			poNext.x = fireworksInfo.poStart.x + (int)fDistanceX;

			// 불꽃 꼬리 다음 위치 Y좌표
			double fDistanceY = (double)(fireworksInfo.poTarget.y - fireworksInfo.poStart.y) * fSin;
			poNext.y = fireworksInfo.poStart.y + (int)fDistanceY;

			// Canvas에 불꽃 꼬리를 그린다
			DrawLineInCanvas(mCanvas, fireworksInfo.poNow, poNext, fireworksInfo.crOutLine, fireworksInfo.nLineWidth);

			// 이전 위치와 현재 위치를 갱신
			fireworksInfo.poPrev.x = fireworksInfo.poNow.x;
			fireworksInfo.poPrev.y = fireworksInfo.poNow.y;
			fireworksInfo.poNow.x = poNext.x;
			fireworksInfo.poNow.y = poNext.y;
			fireworksInfo.nTimeCount ++;
		}

		public void onDraw(Canvas canvas) {
			Rect rtDest = new Rect(0, 0, this.getWidth(), this.getHeight());
			
			// 처음으로 화면을 그린다면
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
				
				// 불꽃 그리기 타이머 시작
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
