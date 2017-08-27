package net.npaka.myactivityex;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//내가 만든 액티비티 호출
public class MyActivityEx extends Activity
implements View.OnClickListener {
	private final static int REQUEST_TEXT=0;//텍스트ID
	
	private TextView textView;//텍스트 뷰
	private Button btnCall; //호출 버튼
	
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
		
		//호출 버튼의 생성
		btnCall=new Button(this);
		btnCall.setText("MyActivity의 호출");
		btnCall.setOnClickListener(this);
		setLLParams(btnCall);
		layout.addView(btnCall);
		
		//텍스트 뷰의 생성
		textView=new TextView(this);
		textView.setText("This is TEST!");
		textView.setTextColor(Color.rgb(0,0,0));
		setLLParams(textView);
		layout.addView(textView);
	}
	
	//버튼 클릭 이벤트 처리
	public void onClick(View v) {
		if (v==btnCall) {
			//명시적 인텐트 생성 (1）
			Intent intent=new Intent(this,
				net.npaka.myactivityex.MyActivity.class);
			
			//인텐트의 파라미터 지정 (2）
			intent.putExtra("text",textView.getText().toString());
			
			//요청 코드를 부착한 인텐트 호출 （3）
			startActivityForResult(intent,REQUEST_TEXT);
		}
	}
	
	//액티비티 호출 결과 구하기 (4）
	@Override
		protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
		if (requestCode==REQUEST_TEXT && resultCode==RESULT_OK) {
			//반환값 파라미터 구하기
			SharedPreferences pref=getSharedPreferences(
				"PREVIOUS_RESULT",MODE_PRIVATE);
			textView.setText(pref.getString("text",""));
		}
	}
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
}