package net.npaka.mediarecorderex;
import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

// 녹화
public class VideoActivity extends Activity {
	// 어플리케이션의 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new CameraView(this));
	}
	
	// 카메라 뷰
	class CameraView extends SurfaceView
		implements SurfaceHolder.Callback {
		private SurfaceHolder holder; // 홀더
		private MediaRecorder recorder;// 미디어 레코더
		
		// 생성자
		public CameraView(Context context) {
			super(context);
			
			// 표면 홀더
			holder=getHolder();
			holder.addCallback(this);
			
			// 푸쉬 버퍼 지정
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		
		// 표면 생성 이벤트의 처리
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				// 녹화의 시작 (3)
				recorder=new MediaRecorder();
				recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
				recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
				recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
				File dir=Environment.getExternalStorageDirectory();
				File file=File.createTempFile("test",".mp4",dir);
				recorder.setOutputFile(file.getAbsolutePath());
				recorder.setPreviewDisplay(holder.getSurface());
				recorder.prepare();
				recorder.start();
			} catch (Exception e) {
				android.util.Log.e("",e.toString());
			}
		}
		
		// 표면 변경 이벤트의 처리
		public void surfaceChanged(SurfaceHolder holder,
			int format,int w,int h) {
		}
		
		// 표면 해제 이벤트의 처리
		public void surfaceDestroyed(SurfaceHolder holder) {
			try {
				// 녹화의 정지 (4)
				recorder.stop();
				recorder.release();
			} catch (Exception e) {
			}
		}
	}
}