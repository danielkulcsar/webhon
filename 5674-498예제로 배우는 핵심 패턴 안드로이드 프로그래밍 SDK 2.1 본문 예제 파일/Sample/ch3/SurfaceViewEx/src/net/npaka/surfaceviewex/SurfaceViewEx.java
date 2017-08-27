package net.npaka.surfaceviewex;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.PixelFormat;
import android.view.Window;

//표면 뷰의 이용
public class SurfaceViewEx extends Activity {
	//어플리케이션 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(new SurfaceViewView(this));
	}
}