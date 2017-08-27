package net.npaka.touchex;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.MotionEvent;

//터치 이벤트 처리
public class TouchView extends View {
	private int touchX=0; //터치X좌표
	private int touchY=0; //터치Y좌표
	private int touchAction=-999;//터치 액션
	private int ballX=0; //볼X좌표
	private int ballY=0; //볼Y좌표
	private int ballAction=-999; //볼 액션
	
	//생성자
	public TouchView(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
		
		//포커스 지정
		setFocusable(true);
	}
	
	//그리기
	@Override
		protected void onDraw(Canvas canvas) {
		String str;
		
		//그리기 객체 생성
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(16);
		
		//터치XY좌표 그리기
		canvas.drawText("TouchXY>"+touchX+","+touchY,0,20*1,paint);
		
		//터치 액션 그리기
		str="NONE";
		if (touchAction==MotionEvent.ACTION_DOWN) str="ACTION_DOWN";
		if (touchAction==MotionEvent.ACTION_MOVE) str="ACTION_MOVE";
		if (touchAction==MotionEvent.ACTION_UP) str="ACTION_UP";
		if (touchAction==MotionEvent.ACTION_CANCEL) str="ACTION_CANCEL";
		canvas.drawText("TouchAction>"+str,0,20*2,paint);
		
		//볼XY좌표 그리기
		canvas.drawText("TrackballXY>"+ballX+","+ballY,0,20*3,paint);
		
		//볼 액션 그리기
		str="NONE";
		if (ballAction==MotionEvent.ACTION_DOWN) str="ACTION_DOWN";
		if (ballAction==MotionEvent.ACTION_MOVE) str="ACTION_MOVE";
		if (ballAction==MotionEvent.ACTION_UP) str="ACTION_UP";
		if (ballAction==MotionEvent.ACTION_CANCEL) str="ACTION_CANCEL";
		canvas.drawText("TrackballAction>"+str,0,20*4,paint);
	}
	
	//터치 이벤트 처리 (1）
	@Override
		public boolean onTouchEvent(MotionEvent event) {
		touchX=(int)event.getX();
		touchY=(int)event.getY();
		touchAction=event.getAction();
		return true;
	}
	
	//트랙볼 이벤트 처리 （2）
	@Override
		public boolean onTrackballEvent(MotionEvent event) {
		ballX=(int)(event.getX()*100);
		ballY=(int)(event.getY()*100);
		ballAction=event.getAction();
		return true;
	}
}