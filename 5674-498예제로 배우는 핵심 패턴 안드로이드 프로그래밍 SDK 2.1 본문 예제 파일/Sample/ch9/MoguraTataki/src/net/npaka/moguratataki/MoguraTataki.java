package net.npaka.moguratataki;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.PixelFormat;
import android.view.Window;

// 두더지 잡기
public class MoguraTataki extends Activity {
	// 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(new MoguraView(this));
	}
}