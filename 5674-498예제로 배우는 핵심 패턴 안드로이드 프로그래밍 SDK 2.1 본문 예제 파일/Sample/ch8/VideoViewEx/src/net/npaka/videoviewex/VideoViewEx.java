package net.npaka.videoviewex;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.VideoView;
import android.widget.MediaController;
import java.io.InputStream;
import java.io.OutputStream;

// 동영상 재생
public class VideoViewEx extends Activity {
	
	// 어플리케이션의 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		try {
			// 비디오 뷰의 생성 (1)
			VideoView videoView=new VideoView(this);
			videoView.requestFocus();
			videoView.setMediaController(new MediaController(this));			
			setContentView(videoView);
			
			// Raw 자원의 파일 저장 (2)		
			raw2file(this,R.raw.sample,"sample.mp4");
			
			// 동영상의 재생 (3)
			String path=getFilesDir().
				getAbsolutePath()+"/sample.mp4";
			videoView.setVideoPath(path);
			videoView.start();
		} catch (Exception e) {
			android.util.Log.e("",e.toString());
		}
	}
	
	// Raw 자원의 파일 보존
	private void raw2file(Context context,
		int resID,String fileName) throws Exception {
		InputStream in=context.getResources().openRawResource(resID);
		in2file(context,in,fileName);
	}
	
	// 입력 스트림의 파일 보존
	private void in2file(Context context,
		InputStream in,String fileName)
		throws Exception {
		int size;
		byte[] w=new byte[1024];
		OutputStream out=null;
		try {
			out=context.openFileOutput(fileName,
				Context.MODE_WORLD_READABLE);
			while (true) {
				size=in.read(w);
				if (size<=0) break;
				out.write(w,0,size);
			};
			out.close();
			in.close();
		} catch (Exception e) {
			try {
				if (in !=null) in.close();
				if (out!=null) out.close();
			} catch (Exception e2) {
			}
			throw e;
		}
	}
}