package net.npaka.imageex;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

// 이미지 그리기
public class ImageEx extends Activity {
	// 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new ImageView(this));
	}
}