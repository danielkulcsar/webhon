package net.npaka.httpex;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

//HTTP 통신
public class HttpEx extends Activity
implements View.OnClickListener {
	//텍스트 파일의 URL 지정 （1）
	private final static String URL=
		"http://www.saturn.dti.ne.jp/~npaka/android/test.txt";
	
	private EditText editText;//텍스트 박스
	private Button btnRead; //읽고 쓰기 버튼
	
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
		
		//읽고 쓰기 버튼의 생성
		btnRead=new Button(this);
		btnRead.setText("HTTP 통신");
		btnRead.setOnClickListener(this);
		setLLParams(btnRead);
		layout.addView(btnRead);
	}
	
	//버튼 클릭 이벤트 처리
	public void onClick(View v) {
		if (v==btnRead) {
			//HTTP 통신
			try {
				String str=http2str(this,URL);
				editText.setText(str,TextView.BufferType.EDITABLE);
			} catch (Exception e) {
				showDialog(this,"에러","읽고 쓰기 실패");
			}
		}
	}
	
	//HTTP 통신 -> 문자열
	private static String http2str(Context context,
		String path) throws Exception {
		byte[] w=http2data(context,path);
		return new String(w);
	}
	
	//HTTP 통신
	public static byte[] http2data(Context context,
		String path) throws Exception {
		int size;
		byte[] w=new byte[1024];
		HttpURLConnection c=null;
		InputStream in=null;
		ByteArrayOutputStream out=null;
		try {
			//HTTP 접속 옵션 (2）
			URL url=new URL(path);
			c=(HttpURLConnection)url.openConnection();
			c.setRequestMethod("GET");
			c.connect();
			in=c.getInputStream();
			
			//바이트 배열의 읽기
			out=new ByteArrayOutputStream();
			while (true) {
				size=in.read(w);
				if (size<=0) break;
				out.write(w,0,size);
			}
			out.close();
			
			//HTTP 접속 종료 (3）
			in.close();
			c.disconnect();
			return out.toByteArray();
		} catch (Exception e) {
			try {
				if (c!=null) c.disconnect();
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
	
	//리니어 레이아웃의 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
	
	//리니어 레이아웃의 지정
	private static void setLLParams(View view,int w,int h) {
		view.setLayoutParams(new LinearLayout.LayoutParams(w,h));
	}
}