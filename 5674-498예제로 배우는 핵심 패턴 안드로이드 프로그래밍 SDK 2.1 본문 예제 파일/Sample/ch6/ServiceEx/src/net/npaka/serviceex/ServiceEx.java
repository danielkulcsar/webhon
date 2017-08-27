package net.npaka.serviceex;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

//서비스
public class ServiceEx extends Activity
implements View.OnClickListener {
	
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
		
		//재생 버튼의 생성
		layout.addView(makeButton(0,"플레이어 서비스 시작"));
		layout.addView(makeButton(1,"플레이어 서비스 정지"));
	}
	
	//버튼의 생성
	private Button makeButton(int id,String text) {
		Button button=new Button(this);
		button.setId(id);
		button.setText(text);
		button.setOnClickListener(this);
		setLLParams(button);
		return button;
	}
	
	//버튼 클릭 이벤트의 처리
	public void onClick(View v) {
		int id=v.getId();
		//플레이어 서비스의 시작 （1）
		if (id==0) {
			Intent intent=new Intent(this,
				net.npaka.serviceex.PlayerService.class);
			startService(intent);
		}
		//플레이어 서비스의 정지 （2）
		else if (id==1) {
			Intent intent=new Intent(this,
				net.npaka.serviceex.PlayerService.class);
			stopService(intent);
		}
	}
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
}