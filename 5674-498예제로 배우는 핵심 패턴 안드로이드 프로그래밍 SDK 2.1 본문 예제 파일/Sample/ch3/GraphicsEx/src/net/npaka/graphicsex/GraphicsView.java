package net.npaka.graphicsex;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

//그래픽 그리기
public class GraphicsView extends View {
	//생성자
	public GraphicsView(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
	}
	
	//그리기
	@Override
		protected void onDraw(Canvas canvas) {
		//그리기 객체 생성
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		
		//선 그리기 （1）
		paint.setStrokeWidth(1);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.argb(255,255,0,0));
		canvas.drawLine(25,5,25,5+40,paint);
		
		//패스 그리기 （2）
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.argb(255,255,0,0));
		Path path=new Path();
		path.moveTo(55+ 0,5+ 0);
		path.lineTo(55+30,5+ 5);
		path.lineTo(55+10,5+20);
		path.lineTo(55+40,5+25);
		path.lineTo(55+ 0,5+40);
		canvas.drawPath(path,paint);
		
		//사각형 그리기 （3）
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.argb(255,0,0,255));
		canvas.drawRect(new Rect(5+0,50+0,5+40,50+40),paint);
		
		//사각형 채우기 （3）
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.argb(255,0,0,255));
		canvas.drawRect(new Rect(55+0,50+0,55+40,50+40),paint);
		
		//사각구형 그리기 （3）
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.argb(255,0,255,0));
		canvas.drawRoundRect(new RectF(5+0,100+0,5+40,100+40),10,10,paint);
		
		//사각구형 채우기 （3）
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.argb(255,0,255,0));
		canvas.drawRoundRect(new RectF(55+0,100+0,55+40,100+40),10,10,paint);
		
		//원 그리기 （4）
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.argb(255,255,255,0));
		canvas.drawCircle(25,170,20,paint);
		
		//원 채우기 （4）
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.argb(255,255,255,0));
		canvas.drawCircle(75,170,20,paint);
	}
}