package net.npaka.buttonex;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

//버튼과 대화상자
public class ButtonEx extends Activity
implements View.OnClickListener {
	private Button button; //버튼
	private ImageButton imageButton;//이미지 버튼
	
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
		
		//버튼 생성 (1）
		button=new Button(this);
		button.setText("버튼");
		button.setOnClickListener(this);// （2）
		setLLParams(button);
		layout.addView(button);
		
		//그림 읽기
		Bitmap image=BitmapFactory.decodeResource(
			getResources(),R.drawable.sample );
		
		//이미지 버튼 생성 （3）
		imageButton=new ImageButton(this);
		imageButton.setImageBitmap(image);
		imageButton.setOnClickListener(this);
		setLLParams(imageButton);
		layout.addView(imageButton);
	}
	
	//버튼 클릭 이벤트 처리 (2）
	public void onClick(View view) {
		//버튼을 눌렀을 때 처리
		if (view==button) {
			showDialog(this,"","버튼이 눌렸음 .");
		}
		//이미지 버튼을 눌렀을 때 처리
		else if (view==imageButton) {
			showDialog(this,"","이미지 버튼을 눌렀음 .");
		}
	}
	
	//대화상자 표시 (4）
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