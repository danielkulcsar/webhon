package net.npaka.mediarecorderex;
import java.io.File;
import android.app.Activity;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

// 음성 합성
public class AudioActivity extends Activity {
	private MediaRecorder recorder; // 미디어 레코더
	
	// 어플리케이션의 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// 레이아웃의 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		// 텍스트 뷰의 생성
		TextView textView=new TextView(this);
		textView.setText("녹음중(BACK으로 종료)");
		textView.setTextSize(16.0f);
		textView.setTextColor(Color.rgb(0,0,0));
		layout.addView(textView);
		
		try {
			// 녹음의 시작 (1)
			recorder=new MediaRecorder();
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			File dir=Environment.getExternalStorageDirectory();
			File file=File.createTempFile("test",".3gp",dir);
			recorder.setOutputFile(file.getAbsolutePath());
			recorder.prepare();
			recorder.start();
		} catch (Exception e) {
			android.util.Log.e("",e.toString());
		}
	}
	
	// 어플리케이션의 정지
	@Override
		public void onStop() {
		super.onStop();
		try {
			// 녹음의 정지 (2)
			recorder.stop();
			recorder.release();
		} catch (Exception e) {
		}
	}
}