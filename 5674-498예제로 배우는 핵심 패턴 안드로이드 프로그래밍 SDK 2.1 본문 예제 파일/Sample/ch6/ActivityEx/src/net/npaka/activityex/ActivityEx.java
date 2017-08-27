package net.npaka.activityex;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

//액티비티 호출
public class ActivityEx extends Activity
implements View.OnClickListener {
	
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
		
		//버튼 생성
		layout.addView(makeButton(0,"Web 페이지 표시:naver"));
		layout.addView(makeButton(1,"지도 표시:seoul"));
		layout.addView(makeButton(2,"통화 개시 tel:02-1111-2222"));
		layout.addView(makeButton(3,"다이얼러 표시"));
		layout.addView(makeButton(4,"설정화면 표시"));
	}
	
	//버튼 생성
	private Button makeButton(int id,String text) {
		Button button=new Button(this);
		button.setId(id);
		button.setText(text);
		button.setOnClickListener(this);
		setLLParams(button);
		return button;
	}
	
	//버튼 클릭 이벤트 처리
	public void onClick(View v) {
		int id=v.getId();
		//Web 페이지 표시 (1）
		if (id==0) {
			Intent intent=new Intent(
				"android.intent.action.VIEW",
				Uri.parse("http://www.naver.com/"));
			startActivity(intent);
		}
		//지도 표시 （2）
		else if (id==1) {
			Intent intent=new Intent(
				"android.intent.action.VIEW",
				Uri.parse("geo:0,0?q=Seoul"));
			startActivity(intent);
		}
		//통화 시작 （3）
		else if (id==2) {
			Intent intent=new Intent(
				"android.intent.action.CALL",
				Uri.parse("tel:02-1111-2222"));
			startActivity(intent);
		}
		//다이얼러 표시 （4）
		else if (id==3) {
			Intent intent=new Intent(
				"android.intent.action.DIAL",
				Uri.parse("tel:02-1111-2222"));
			startActivity(intent);
		}
		//설정화면 표시 （5）
		else if (id==4) {
			Intent intent=new Intent(
				"android.settings.SETTINGS");
			startActivity(intent);
		}
	}
	
	//리니어 레이아웃의 파라미터 설정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
}