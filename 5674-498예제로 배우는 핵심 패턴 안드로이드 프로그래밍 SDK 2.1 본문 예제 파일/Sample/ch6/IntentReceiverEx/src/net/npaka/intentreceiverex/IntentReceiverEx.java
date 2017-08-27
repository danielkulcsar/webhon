package net.npaka.intentreceiverex;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

//브로드캐스트 리시버
public class IntentReceiverEx extends Activity
implements View.OnClickListener {
	private Button btnCall;//호출 버튼
	
	//초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//레이아웃의 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		//버튼의 생성
		btnCall=new Button(this);
		btnCall.setText("인텐트 리시버 브로드캐스트");
		btnCall.setOnClickListener(this);
		setLLParams(btnCall);
		layout.addView(btnCall);
	}
	
	//버튼 클릭 이벤트의 처리
	public void onClick(View v) {
		if (v==btnCall) {
			//인텐트 브로드캐스트 （1）
			Intent intent=new Intent("android.intent.action.VIEW");
			intent.putExtra("TEXT","인텐트 리시버 테스트입니다 .");
			sendBroadcast(intent);
		}
	}
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
}