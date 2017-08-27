package net.npaka.rpg;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.PixelFormat;
import android.view.Window;

// RPG
public class RPG extends Activity {
	private static RPG rpg;
	
	// 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		rpg=this;
		setContentView(new RPGView(this));
	}
	
	// 종료
	public static void exit() {
		rpg.finish();
	}
}