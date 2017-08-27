package net.npaka.sensorex;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

// 가속도 센서 이용
public class SensorEx extends Activity
implements SensorEventListener {
	private TextView textView; // 텍스트 뷰
	private TickHandler tickHandler; // 정기처리 핸들러
	private SensorManager sensorManager; // 센서 매니저
	private Sensor accelerometer; // 가속도 센서
	private Sensor orientation; // 회전 센서
	private float[] values=new float[6]; // 가속도와 기울기 값
	
	// 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// 레이아웃의 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		// 텍스트 뷰의 생성
		textView=new TextView(this);
		textView.setText("SensorEx");
		textView.setTextSize(24.0f);
		textView.setTextColor(Color.rgb(0,0,0));
		setLLParams(textView);
		layout.addView(textView);
		
		// 센서 관리자 구하기 (1)
		sensorManager=(SensorManager) getSystemService(
			Context.SENSOR_SERVICE);
		
		// 센서 구하기 (2)
		List<Sensor> list;
		list=sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if (list.size()>0) accelerometer=list.get(0);
		list=sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
		if (list.size()>0) orientation=list.get(0);
	}
	
	// 어플리케이션 시작
	@Override
		protected void onResume() {
		// 어플리케이션 재개
		super.onResume();
		
		// 센서 처리 시작 (3)
		if (accelerometer!=null) {
			sensorManager.registerListener(this,
				accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
		}
		if (orientation!=null) {
			sensorManager.registerListener(this,
				orientation,SensorManager.SENSOR_DELAY_FASTEST);
		}
		
		// 정기처리 시작
		tickHandler=new TickHandler();
		tickHandler.sleep(0);
	}
	
	// 어플리케이션 정지
	@Override
		protected void onStop() {
		// 어플리케이션 정지
		super.onStop();
		
		// 센서처리 정지 (4)
		sensorManager.unregisterListener(this);
		
		// 정기처리 정지
		tickHandler=null;
	}
	
	// 센서 이벤트 리스너 처리 (5)
	public void onSensorChanged(SensorEvent event) {
		
		// 가속도 구하기
		if (event.sensor==accelerometer) {
			values[0]=event.values[0];
			values[1]=event.values[1];
			values[2]=event.values[2];
		}
		
		// 방향 구하기
		if (event.sensor==orientation) {
			values[3]=event.values[0];
			values[4]=event.values[1];
			values[5]=event.values[2];
		}
	}
	
	// 정확도 변경 이벤트의 처리
	public void onAccuracyChanged(Sensor sensor,int accuracy) {
	}
	
	// 정기처리 핸들러
	public class TickHandler extends Handler {
		// 핸들 메시지
		@Override
			public void handleMessage(Message msg) {
			String text="SensorEx"+
				"\nX축 가속도:"+values[0]+
				"\nY축 가속도:"+values[1]+
				"\nZ축 가속도:"+values[2]+
				"\n방위:" +values[3]+
				"\n피치:" +values[4]+
				"\n롤:" +values[5];
			textView.setText(text);
			if (tickHandler!=null) tickHandler.sleep(200);
		}
		
		// 대기
		public void sleep(long delayMills) {
			removeMessages(0);
			sendMessageDelayed(obtainMessage(0),delayMills);
		}
	}
	
	// 리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
}