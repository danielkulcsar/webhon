package net.npaka.imageex;
import android.content.res.Resources;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;

// 이미지 그리기
public class ImageView extends View {
	private Bitmap image; // 이미지
	
	// 생성자
	public ImageView(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
		
		// 그림 읽기 (1)
		Resources r=context.getResources();
		image=BitmapFactory.decodeResource(
			r,R.drawable.sample);
	}
	
	// 그리기
	@Override
		protected void onDraw(Canvas canvas) {
		// 이미지의 그리기 (2)
		canvas.drawBitmap(image,0,0,null);
		
		// 이미지 확대 축소 그리기 (3)
		int w=image.getWidth();
		int h=image.getHeight();
		Rect src=new Rect(0,0,w,h);
		Rect dst=new Rect(0,200,w*2,200+h*2);
		canvas.drawBitmap(image,src,dst,null);
	}
}