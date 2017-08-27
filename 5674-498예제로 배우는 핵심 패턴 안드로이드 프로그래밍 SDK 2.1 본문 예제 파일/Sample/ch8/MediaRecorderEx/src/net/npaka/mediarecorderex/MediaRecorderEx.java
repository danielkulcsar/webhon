package net.npaka.mediarecorderex;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

// 음성 녹음
public class MediaRecorderEx extends Activity
implements View.OnClickListener {
	private Button btnAudio; // 녹음 시작 버튼
	private Button btnVideo; // 녹화 시작 버튼
	
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
		
		// 녹음 시작의 생성
		btnAudio=new Button(this);
		btnAudio.setText("녹음 시작");
		btnAudio.setOnClickListener(this);
		setLLParams(btnAudio);
		layout.addView(btnAudio);
		
		// 녹화 시작의 생성
		btnVideo=new Button(this);
		btnVideo.setText("녹화 시작");
		btnVideo.setOnClickListener(this);
		setLLParams(btnVideo);
		layout.addView(btnVideo);
	}
	
	// 버튼 클릭 이벤트의 처리
	public void onClick(View v) {
		if (v==btnAudio) {
			Intent intent=new Intent(this,
				net.npaka.mediarecorderex.AudioActivity.class);
			startActivity(intent);
		} else if (v==btnVideo) {
			Intent intent=new Intent(this,
				net.npaka.mediarecorderex.VideoActivity.class);
			startActivity(intent);
		}
	}
	
	// 리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
}