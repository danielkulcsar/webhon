package net.npaka.dbproviderex;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

//DB를 제공하는 컨텐츠 프로바이더
public class DBProviderEx extends Activity
implements View.OnClickListener {
	private EditText editText;//텍스트 박스
	private Button btnWrite;//쓰기 버튼
	private Button btnRead; //읽기 버튼
	
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
		editText.setWidth(240);
		editText.setText("",EditText.BufferType.NORMAL);
		setLLParams(editText);
		layout.addView(editText);
		
		//쓰기 버튼의 생성
		btnWrite=new Button(this);
		btnWrite.setText("쓰기");
		btnWrite.setOnClickListener(this);
		setLLParams(btnWrite);
		layout.addView(btnWrite);
		
		//읽기 버튼의 생성
		btnRead=new Button(this);
		btnRead.setText("읽기");
		btnRead.setOnClickListener(this);
		setLLParams(btnRead);
		layout.addView(btnRead);
	}
	
	//버튼 클릭 이벤트의 처리
	public void onClick(View v) {
		if (v==btnWrite) {
			try {
				writeDB(editText.getText().toString());
			} catch (Exception e) {
				showDialog(this,"에러","쓰기 실패했습니다 .");
			}
		} else if (v==btnRead) {
			try {
				editText.setText(readDB());
			} catch (Exception e) {
				showDialog(this,"에러","읽기 실패 했습니다 ."+e.toString());
			}
		}
	}
	
	//DB에 쓰기
	private void writeDB(String info) throws Exception {
		//컨텐츠 프로바이더가 제공하는 데이터베이스를 나타내는 URI （1）
		Uri uri=Uri.parse("content://net.npaka.dbprovider/");
		
		//컨텐츠 프로바이더가 제공하는 데이터베이스로 접근 （2）
		ContentValues cv=new ContentValues();
		cv.put("id","0");
		cv.put("info",info);
		this.getContentResolver().update(uri,cv,null,null);
	}
	
	//DB로부터 읽기
	private String readDB() throws Exception {
		//컨텐츠 프로바이더가 제공하는 데이터베이스가 나타내는 URI （1）
		Uri uri=Uri.parse("content://net.npaka.dbprovider/");
		
		//컨텐츠 프로바이더가 제공하는 데이터베이스로 접근 （2）
		Cursor c=this.getContentResolver().query(uri,
			new String[]{"id","info"},"id='0'",null,null);
		if (c.getCount()==0) throw new Exception();
		c.moveToFirst();
		return c.getString(1);
	}
	
	//대화상자 표시
	private static void showDialog(final Activity activity,String title,String text){
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