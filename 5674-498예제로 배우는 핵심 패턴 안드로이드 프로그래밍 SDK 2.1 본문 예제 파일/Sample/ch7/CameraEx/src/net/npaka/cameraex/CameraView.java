package net.npaka.cameraex;
import android.content.Context;
import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.FileOutputStream;

// 카메라의 제어
public class CameraView extends SurfaceView
implements SurfaceHolder.Callback,Camera.PictureCallback {
	private SurfaceHolder holder; // 홀더
	private Camera camera; // 카메라
	
	// 생성자
	public CameraView(Context context) {
		super(context);
		
		// 표면 홀더 생성
		holder=getHolder();
		holder.addCallback(this);
		// 푸쉬 버퍼 지정 (1)
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	// 표면 생성 이벤트의 처리
	public void surfaceCreated(SurfaceHolder holder) {
		// 카메라 초기화 (2)
		try {
			camera=Camera.open();
			camera.setPreviewDisplay(holder);
		} catch (Exception e) {
		}
	}
	
	// 표면 변경 이벤트 처리
	public void surfaceChanged(SurfaceHolder holder,int format,int w,int h) {
		// 카메라 미리보기 시작 (3)
		camera.startPreview();
	}
	
	// 표면 파괴 이벤트 처리
	public void surfaceDestroyed(SurfaceHolder holder) {
		// 카메라 미리보기 정지 (4)
		camera.setPreviewCallback(null);
		camera.stopPreview();
		camera.release();
		camera=null;
	}
	
	// 터치 이벤트 처리
	@Override
		public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() ==MotionEvent.ACTION_DOWN) {
			// 카메라 스크린 샷 구하기 (5)
			camera.takePicture(null,null,this);
		}
		return true;
	}
	
	// 사진 촬영 완료 시 불린다.
	public void onPictureTaken(byte[] data,Camera camera) {
		// 파일 보존과 갤러리로의 등록
		try {
			data2sd(getContext(),data,"test.jpg");
		} catch (Exception e) {
			android.util.Log.e("",""+e.toString());
		}
		
		// 미리보기 재개
		camera.startPreview();
	}
	
	// 바이트 데이터→SD 카드
	private static void data2sd(Context context,
		byte[] w,String fileName) throws Exception {
		// SD 카드에 데이터 저장 (6)
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream("/sdcard/"+fileName);
			fos.write(w);
			fos.close();
		} catch (Exception e) {
			if (fos!=null) fos.close();
			throw e;
		}
	}
}