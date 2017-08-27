package net.npaka.mediaplayerex;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

// 사운드의 재생
public class MediaPlayerEx extends Activity {
	private MediaPlayerView view;
	
	// 어플리케이션의 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		view=new MediaPlayerView(this);
		setContentView(view);
	}
	
	// 어플리케이션의 정지
	@Override
		public void onStop() {
		super.onStop();
		
		// 어플리케이션 종료 시 사운드 정지 (5)
		view.stopSound();
	}
}