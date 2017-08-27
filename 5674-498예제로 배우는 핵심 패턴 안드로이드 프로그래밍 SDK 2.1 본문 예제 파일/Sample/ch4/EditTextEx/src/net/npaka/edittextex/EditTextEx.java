package net.npaka.edittextex;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

//텍스트 박스
public class EditTextEx extends Activity
implements View.OnClickListener {
	private EditText editText;//텍스트 박스
	private Button button; //버튼
	
	//어플리케이션 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//레이아웃 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		//텍스트 박스 생성 (1）
		editText=new EditText(this);
		editText.setText("이것은 텍스트입니다 .",
			EditText.BufferType.NORMAL);
		setLLParams(editText,240,50);
		layout.addView(editText);
		
		//버튼 생성
		button=new Button(this);
		button.setText("결과 표시");
		button.setOnClickListener(this);
		setLLParams(button);
		layout.addView(button);
	}
	
	//버튼 클릭 이벤트 처리
	public void onClick(View v) {
		showDialog(this,"",// （1）
			"에디트>"+editText.getText().toString());
	}
	
	//대화상자 표시
	private static void showDialog(final Activity activity,
		String title,String text) {
		AlertDialog.Builder ad=new AlertDialog.Builder(activity);
		ad.setTitle(title);
		ad.setMessage(text);
		ad.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int whichButton) {
				activity.setResult(Activity.RESULT_OK);
			}
		});
		ad.create();
		ad.show();
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