package net.npaka.graphicsex;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

//그래픽 그리기
public class GraphicsEx extends Activity {
	//어플리케이션 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new GraphicsView(this));
	}
}