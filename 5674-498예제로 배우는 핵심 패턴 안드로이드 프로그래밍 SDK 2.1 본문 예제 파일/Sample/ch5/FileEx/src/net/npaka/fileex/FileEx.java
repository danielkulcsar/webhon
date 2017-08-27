package net.npaka.fileex;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

//파일 읽고 쓰기
public class FileEx extends Activity
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
		editText.setText("",EditText.BufferType.NORMAL);
		setLLParams(editText,240,50);
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
	
	//버튼 클릭 이벤트 처리
	public void onClick(View v) {
		if (v==btnWrite) {
			try {
				String str=editText.getText().toString();
				str2file(this,str,"test.txt");
			} catch (Exception e) {
				showDialog(this,"에러","쓰기 실패했습니다");
			}
		} else if (v==btnRead) {
			try {
				String str=file2str(this,"test.txt");
				editText.setText(str,TextView.BufferType.EDITABLE);
			} catch (Exception e) {
				showDialog(this,"에러","읽기 실패 했습니다");
			}
		}
	}
	
	//문자열 -> 파일
	private static void str2file(Context context,
		String str,String fileName) throws Exception {
		//문자열 바이트 배열 변환 （1）
		data2file(context,str.getBytes(),fileName);
	}
	
	//문자열 -> 바이트 데이터
	private static String file2str(Context context,
		String fileName) throws Exception {
		byte[] w=file2data(context,fileName);
		//바이트 배열의 문자열 변환 (2）
		return new String(w);
	}
	
	//바이트 데이터 -> 파일
	private static void data2file(Context context,
		byte[] w,String fileName) throws Exception {
		OutputStream out=null;
		try {
			//파일 출력 스트림의 개방 （3）
			out=context.openFileOutput(fileName,
				Context.MODE_PRIVATE);
			
			//바이트 배열의 쓰기 (4）
			out.write(w,0,w.length);
			
			//파일 출력 스트림의 닫기 (5）
			out.close();
		} catch (Exception e) {
			try {
				if (out!=null) out.close();
			} catch (Exception e2) {
			}
			throw e;
		}
	}
	
	//파일 -> 바이트 데이터
	private static byte[] file2data(Context context,
		String fileName) throws Exception {
		int size;
		byte[] w=new byte[1024];
		InputStream in=null;
		ByteArrayOutputStream out=null;
		try {
			//파일 입력 스트림의 개방 （6）
			in=context.openFileInput(fileName);
			
			//바이트 배열의 읽기 （7）
			out=new ByteArrayOutputStream();
			while (true) {
				size=in.read(w);
				if (size<=0) break;
				out.write(w,0,size);
			}
			out.close();
			
			//파일 입력 스트림의 닫기 (8）
			in.close();
			
			//ByteArrayOutputStream 객체의 바이트 배열화 (9）
			return out.toByteArray();
		} catch (Exception e) {
			try {
				if (in!=null) in.close();
				if (out!=null) out.close();
			} catch (Exception e2) {
			}
			throw e;
		}
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
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view,int w,int h) {
		view.setLayoutParams(new LinearLayout.LayoutParams(w,h));
	}
}