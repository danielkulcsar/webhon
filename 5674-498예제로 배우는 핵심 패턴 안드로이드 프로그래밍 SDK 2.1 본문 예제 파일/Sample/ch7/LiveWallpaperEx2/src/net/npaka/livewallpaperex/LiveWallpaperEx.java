package net.npaka.livewallpaperex;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;
import android.content.res.Resources;

// 라이브 배경 화면 서비스의 이용
public class LiveWallpaperEx extends WallpaperService {// (1)
	// 생성 시 호출된다.
	@Override
		public void onCreate() {
		super.onCreate ();
	}
	
	// 파괴 시 호출된다.
	@Override
		public void onDestroy() {
		super.onDestroy();
	}
	
	// 배경 화면 엔진 생성 시 호출된다. (2)
	@Override
		public WallpaperService.Engine onCreateEngine() {
		return new WallpaperEngine(getResources());
	}
	
	// 배경 화면 엔진
	public class WallpaperEngine extends WallpaperService.Engine {
		private final Handler handler=new Handler();
		private Bitmap image; // 이미지
		private int px=0; // X좌표
		private int py=0; // Y좌표
		private int vx=10; // X속도
		private int vy=10; // Y속도
		
		private boolean visible;//표시 상태
		private int width; //폭
		private int height; //높이
		
		// 그리기 스레드
		private final Runnable drawThread=new Runnable() {
			public void run() {
				drawFrame();
			}
		};
		
		// 생성자
		public WallpaperEngine(Resources r) {
			image=BitmapFactory.decodeResource(r,R.drawable.sample);
		}
		
		// 생성 시 호출된다.
		@Override
			public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
		}
		
		// 파괴 시 호출된다.
		@Override
			public void onDestroy() {
			super.onDestroy();
			handler.removeCallbacks(drawThread);
		}
		
		// 표시 상태 변경 시 호출된다.
		@Override
			public void onVisibilityChanged(boolean visible) {
			this.visible=visible;
			if (visible) {
				drawFrame();
			} else {
				handler.removeCallbacks(drawThread);
			}
		}
		
		// 표면 생성 시 호출된다.
		@Override
			public void onSurfaceCreated(SurfaceHolder holder) {
			super.onSurfaceCreated(holder);
		}
		
		// 표면 변경 시 호출된다.
		@Override
			public void onSurfaceChanged(SurfaceHolder holder,
			int format,int width,int height) {
			super.onSurfaceChanged(holder,format,width,height);
			this.width =width;
			this.height=height;
			drawFrame();
		}
		
		// 표면 파괴 시 호출된다.
		@Override
			public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			visible=false;
			handler.removeCallbacks(drawThread);
		}
		
		// 옵셋 변경 시 호출된다.
		@Override
			public void onOffsetsChanged(float xOffset,float yOffset,
			float xStep,float yStep,int xPixels,int yPixels) {
			drawFrame();
		}
		
		// 프레임 그리기
		private void drawFrame() {
			// 잠금
			SurfaceHolder holder=getSurfaceHolder();
			Canvas c=holder.lockCanvas();
			
			// 그리기
			c.drawColor(Color.WHITE);
			c.drawBitmap(image,px-57,py-57,null);
			
			// 잠금 해제
			holder.unlockCanvasAndPost(c);
			// 이동
			if (px<0 || width <px) vx=-vx;
			if (py<0 || height<py) vy=-vy;
			px+=vx;
			py+=vy;
			
			// 다시 그리기
			handler.removeCallbacks(drawThread);
			if (visible) handler.postDelayed(drawThread,100);
		}
	}
}