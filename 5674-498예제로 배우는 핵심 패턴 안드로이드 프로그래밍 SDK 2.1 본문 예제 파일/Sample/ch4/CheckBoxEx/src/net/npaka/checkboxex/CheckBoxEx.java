package net.npaka.checkboxex;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

//체크 박스와 라디오 버튼의 이용
public class CheckBoxEx extends Activity
implements View.OnClickListener {
	private final static String BR=//개행문자 （3）
		System.getProperty("line.separator");
	
	private CheckBox checkBox; //체크 박스
	private RadioGroup radioGroup;//라디오 버튼
	private Button button; //버튼
	
	//초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//레이아웃 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		//체크 박스 생성 (1）
		checkBox=new CheckBox(this);
		checkBox.setText("체크 박스");
		checkBox.setTextColor(Color.rgb(0,0,0));
		setLLParams(checkBox);
		layout.addView(checkBox);
		
		//라디오 버튼 생성 (2）
		RadioButton radio0=new RadioButton(this);
		radio0.setId(0);
		radio0.setText("라디오 버튼 0");
		radio0.setTextColor(Color.rgb(0,0,0));
		
		//라디오 버튼 생성 (2）
		RadioButton radio1=new RadioButton(this);
		radio1.setId(1);
		radio1.setText("라디오 버튼 1");
		radio1.setTextColor(Color.rgb(0,0,0));
		
		//라디오 버튼 생성 (2）
		radioGroup=new RadioGroup(this);
		radioGroup.addView(radio0);
		radioGroup.addView(radio1);
		radioGroup.check(0);
		setLLParams(radioGroup);
		layout.addView(radioGroup);
		
		//버튼 생성
		button=new Button(this);
		button.setText("결과 표시");
		button.setOnClickListener(this);
		setLLParams(button);
		layout.addView(button);
	}
	
	//버튼 클릭 이벤트 처리
	public void onClick(View v) {
		//체크 박스와 라디오 버튼의 상태 구하기 （1） （2）
		showDialog(this,"",
			"체크 박스 >"+checkBox.isChecked()+BR+
			"라디오 버튼 >"+radioGroup.getCheckedRadioButtonId());
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
}