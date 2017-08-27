package net.npaka.keyex;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

//키 이벤트 처리
public class KeyEx extends Activity {
	private KeyView keyView; //키 뷰
	private TickHandler tickHandler;//정기처리 핸들러
	
	//어플리케이션 초기화 (1)
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		keyView=new KeyView(this);
		setContentView(keyView);
	}
	
	//어플리케이션 재시작 (1）
	@Override
		public void onResume() {
		super.onResume();
		
		//정기처리 핸들러의 시작 (3）
		tickHandler=new TickHandler();
		tickHandler.sleep(0);
	}
	
	//어플리케이션 일시 정지 (1）
	@Override
		public void onPause() {
		super.onPause();
		
		//정기처리 핸들러의 정지 (4）
		tickHandler=null;
	}
	
	//정기처리 핸들러 (2）
	public class TickHandler extends Handler {
		//정기처리
		@Override
			public void handleMessage(Message msg) {
			keyView.invalidate();
			if (tickHandler!=null) tickHandler.sleep(100);
		}
		
		//슬립 (sleep)
		public void sleep(long delayMills) {
			removeMessages(0);
			sendMessageDelayed(obtainMessage(0),delayMills);
		}
	}
}