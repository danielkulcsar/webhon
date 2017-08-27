package net.npaka.stringex;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

//문자열의 표시
public class StringView extends View {
	//생성자
	public StringView(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
	}
	
	//그리기
	@Override
		protected void onDraw(Canvas canvas) {
		//그리기 객체의 생성
		Paint paint=new Paint();
		paint.setAntiAlias(true);// （1）
		
		//문자 크기와 문자색의 지정 (2）
		paint.setTextSize(12);
		paint.setColor(Color.argb(255,0,0,0));
		
		//화면 크기의 취득 (3）
		canvas.drawText("화면 크기:"+
			getWidth()+"x"+getHeight(),0,30,paint);
		
		//문자열 폭의 취득 （4）
		canvas.drawText("문자폭:"+
			(int)paint.measureText("A"),0,30*2,paint);
		
		//어센트의 취득 （5）
		canvas.drawText("어센트:"+
			(int)paint.ascent(),0,30*3,paint);
		canvas.drawText("디센트:"+
			(int)paint.descent(),0,30*4,paint);
		
		//12도트 문자열의 표시 （6）
		paint.setTextSize(12);
		paint.setColor(Color.argb(255,255,0,0));
		canvas.drawText("12dot",0,30*5,paint);
		
		//16도트 문자열의 표시
		paint.setTextSize(16);
		paint.setColor(Color.argb(255,0,255,0));
		canvas.drawText("16dot",0,30*6,paint);
		
		//24도트 문자열의 표시
		paint.setTextSize(24);
		paint.setColor(Color.argb(255,0,0,255));
		canvas.drawText("24dot",0,30*7,paint);
	}
}