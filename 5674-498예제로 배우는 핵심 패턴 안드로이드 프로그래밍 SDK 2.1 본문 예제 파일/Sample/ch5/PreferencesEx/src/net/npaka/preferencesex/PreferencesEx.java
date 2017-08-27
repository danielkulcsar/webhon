package net.npaka.preferencesex;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

//프리퍼런스 읽고 쓰기
public class PreferencesEx extends Activity
implements View.OnClickListener {
	private EditText editText;//텍스트 박스
	private Button btnWrite;//읽기 버튼
	private Button btnRead; //쓰기 버튼
	
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
		
		//텍스트 박스의 생성
		editText=new EditText(this);
		editText.setText("",EditText.BufferType.NORMAL);
		setLLParams(editText,240,50);
		layout.addView(editText);
		
		//쓰기 버튼의 생성
		btnWrite=new Button(this);
		btnWrite.setText("프리퍼런스 쓰기");
		btnWrite.setOnClickListener(this);
		setLLParams(btnWrite);
		layout.addView(btnWrite);
		
		//버튼 클릭 이벤트 처리
		btnRead=new Button(this);
		btnRead.setText("프리퍼런스 읽기");
		btnRead.setOnClickListener(this);
		setLLParams(btnRead);
		layout.addView(btnRead);
	}
	
	//프리퍼런스의 쓰기
	public void onClick(View v) {
		if (v==btnWrite) {
			//SharedPreferences 객체 구하기 （1）
			SharedPreferences pref=getSharedPreferences(
				"PreferencesEx",MODE_PRIVATE);
			
			//프리퍼런스의 쓰기 （2）
			SharedPreferences.Editor editor=pref.edit();
			editor.putString("text",editText.getText().toString());
			editor.commit();
		} else if (v==btnRead) {
			//SharedPreferences 객체 구하기 （1）
			SharedPreferences pref=getSharedPreferences(
				"PreferencesEx",MODE_PRIVATE);
			
			//프리퍼런스로부터 읽기 （3）
			editText.setText(pref.getString("text",""));
		}
	}
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view,int w,int h) {
		view.setLayoutParams(new LinearLayout.LayoutParams(w,h));
	}
}