package net.npaka.xmllayoutex;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

// XML에 의한 레이아웃 작성
public class XMLLayoutEx extends Activity
implements View.OnClickListener {
	private Button button; // 버튼
	private ImageButton imageButton;// 이미지 버튼
	
	// 초기화
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// 레이아웃의 지정 (1)
		setContentView(R.layout.sample);
		
		// 컴포넌트 관련 (2)
		button=(Button) this.findViewById(R.id.Button01);
		button.setOnClickListener(this);
		imageButton=(ImageButton) this.findViewById(
			R.id.ImageButton01);
		imageButton.setOnClickListener(this);
	}
	
	// 버튼 클릭 이벤트의 처리
	public void onClick(View view) {
		// 버튼을 눌렀을 때의 처리
		if (view==button) {
			showDialog(this,"","버튼을 눌렀다 ");
		}
		// 이미지 버튼을 눌렀을 때의 처리
		else if (view==imageButton) {
			showDialog(this,"","이미지 버튼을 눌렀다 ");
		}
	}
	
	// 대화상자의 표시
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
}