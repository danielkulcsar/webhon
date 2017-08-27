package net.npaka.keyex;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;

//키 이벤트 처리
public class KeyView extends View {
	private int keyCode=-999;//키 코드
	
	//생성자
	public KeyView(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
		
		//포커스 지정 (5）
		setFocusable(true);
		setFocusableInTouchMode(true);
	}
	
	//그리기
	@Override
		protected void onDraw(Canvas canvas) {
		//페인트
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(16);
		
		//키 코드 그리기
		canvas.drawText("KeyCode>"+keyCode,0,20,paint);
	}
	
	//키 다운 이벤트 처리 (6）
	@Override
		public boolean onKeyDown(int keyCode,KeyEvent event) {
		this.keyCode=keyCode;
		return super.onKeyDown(keyCode,event);
	}
	
	//키 업 이벤트 처리 (6）
	@Override
		public boolean onKeyUp(int keyCode,KeyEvent event) {
		this.keyCode=-999;
		return super.onKeyUp(keyCode,event);
	}
}