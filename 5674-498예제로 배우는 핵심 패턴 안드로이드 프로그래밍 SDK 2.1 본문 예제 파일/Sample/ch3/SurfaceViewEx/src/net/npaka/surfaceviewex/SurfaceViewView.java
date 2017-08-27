package net.npaka.surfaceviewex;
import android.content.res.Resources;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// 표면 뷰의 이용
public class SurfaceViewView extends SurfaceView
implements SurfaceHolder.Callback,Runnable {
	private SurfaceHolder holder; // 표면 홀더
	private Thread thread; // 스레드
	
	private Bitmap image; // 이미지
	private int px=0; // X좌표
	private int py=0; // Y좌표
	private int vx=10; // X속도
	private int vy=10; // Y속도
	
	// 생성자
	public SurfaceViewView(Context context) {
		super(context);
		
		// 그림 읽기
		Resources r=getResources();
		image=BitmapFactory.decodeResource(r,R.drawable.sample);
		
		// 표면 홀더의 생성 (2)
		holder=getHolder();
		holder.addCallback(this);
		holder.setFixedSize(getWidth(),getHeight());
	}
	
	// 표면의 생성 (1)
	public void surfaceCreated(SurfaceHolder holder) {
		// 스레드 시작 (3)
		thread=new Thread(this);
		thread.start();
	}
	
	// 표면 변경 (1)
	public void surfaceChanged(SurfaceHolder holder,
		int format,int w,int h) {
	}
	
	// 표면 파괴 (1)
	public void surfaceDestroyed(SurfaceHolder holder) {
		thread=null;
	}
	
	// 스레드 처리
	public void run() {
		Canvas canvas;
		while(thread!=null) {
			// 더블 버퍼링 (4)
			canvas=holder.lockCanvas();
			canvas.drawColor(Color.WHITE);
			canvas.drawBitmap(image,px-40,py-40,null);
			holder.unlockCanvasAndPost(canvas);
			
			// 이동
			if (px<0 || getWidth()<px) vx=-vx;
			if (py<0 || getHeight()<py) vy=-vy;
			px+=vx;
			py+=vy;
			
			// 잠시 대기 (5)
			try {
				Thread.sleep(50);
			} catch (Exception e) {
			}
		}
	}
}