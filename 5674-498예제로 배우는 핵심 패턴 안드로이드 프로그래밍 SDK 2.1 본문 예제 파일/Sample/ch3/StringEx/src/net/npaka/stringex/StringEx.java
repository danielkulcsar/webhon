package net.npaka.stringex;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

//문자열의 표시
public class StringEx extends Activity {
	//어플리케이션 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new StringView(this));
	}
}