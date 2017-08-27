package net.npaka.myactivityex;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

//내가 만든 액티비티
public class MyActivity extends Activity
implements View.OnClickListener {
	private EditText editText;//텍스트 박스
	private Button btnOK; //OK 버튼
	
	//초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//인텐트로부터의 파라미터 구하기 （5）
		String text="";
		Bundle extras=getIntent().getExtras();
		if (extras!=null) text=extras.getString("text");
		
		//레이아웃의 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		//OK 버튼의 생성
		btnOK=new Button(this);
		btnOK.setText("OK");
		btnOK.setOnClickListener(this);
		setLLParams(btnOK);
		layout.addView(btnOK);
		
		//텍스트 박스의 생성
		editText=new EditText(this);
		editText.setText(text);
		setLLParams(editText);
		layout.addView(editText);
	}
	
	//버튼 클릭 이벤트의 처리
	public void onClick(View v) {
		if (v==btnOK) {
			//반환값 파라미터의 저장
			SharedPreferences pref=getSharedPreferences(
				"PREVIOUS_RESULT",MODE_PRIVATE);
			SharedPreferences.Editor editor=pref.edit();
			editor.putString("text",editText.getText().toString());
			editor.commit();
			
			//액티비티 종료 （6）
			setResult(RESULT_OK);
			finish();
		}
	}
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
}