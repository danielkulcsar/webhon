package net.npaka.cameraex;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

//카메라 제어
public class CameraEx extends Activity {
	//어플리케이션 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new CameraView(this));
	}
}