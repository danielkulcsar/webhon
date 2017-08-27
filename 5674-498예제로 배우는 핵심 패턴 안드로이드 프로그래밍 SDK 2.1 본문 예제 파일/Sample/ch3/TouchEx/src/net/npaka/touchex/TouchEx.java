package net.npaka.touchex;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

//터치 이벤트 처리
public class TouchEx extends Activity {
	private TouchView touchView; //터치 뷰
	private TickHandler tickHandler;//정기처리 핸들러
	
	//어플리케이션 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		touchView=new TouchView(this);
		setContentView(touchView);
	}
	
	//어플리케이션 재시작
	@Override
		public void onResume() {
		super.onResume();
		tickHandler=new TickHandler();
		tickHandler.sleep(0);
	}
	
	//어플리케이션 일시 정지
	@Override
		public void onPause() {
		super.onPause();
		tickHandler=null;
	}
	
	//정기처리 핸들러
	public class TickHandler extends Handler {
		//핸들러 메시지
		@Override
			public void handleMessage(Message msg) {
			touchView.invalidate();
			if (tickHandler!=null) tickHandler.sleep(100);
		}
		
		//슬립 (sleep)
		public void sleep(long delayMills) {
			removeMessages(0);
			sendMessageDelayed(obtainMessage(0),delayMills);
		}
	}
}