package net.npaka.socketex;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//소켓 통신
public class SocketEx extends Activity
implements View.OnClickListener {
	private final static String BR=//개행
		System.getProperty("line.separator");
	private final static String IP=//IP 주소 지정 (1）
		"127.0.0.1";
	
	private SocketEx current; //현재
	private TextView lblReceive;//수신 라벨
	private EditText edtSend; //송신 텍스트 박스
	private Button btnSend; //송신 버튼
	
	private Socket socket; //소켓
	private InputStream in; //입력 스트림
	private OutputStream out; //출력 스트림
	
	private final Handler handler=new Handler();//핸들러 （6）
	private String txtReceive;//수신 텍스트
	
	//초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		current=this;
		
		//레이아웃의 생성 （8）
		RelativeLayout layout=new RelativeLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		setContentView(layout);
		
		//수신 라벨의 생성
		lblReceive=new TextView(this);
		lblReceive.setId(1);// （9）
		lblReceive.setText("");
		lblReceive.setTextSize(16.0f);
		lblReceive.setTextColor(Color.rgb(0,0,0));
		RelativeLayout.LayoutParams param1=// （9）
			new RelativeLayout.LayoutParams(320,400);
		lblReceive.setLayoutParams(param1);
		layout.addView(lblReceive);
		
		//송신 텍스트 박스의 생성
		edtSend=new EditText(this);
		edtSend.setId(2);// （9）
		edtSend.setText("",TextView.BufferType.NORMAL);
		RelativeLayout.LayoutParams param2=// （9）
			new RelativeLayout.LayoutParams(200,50);
		param2.addRule(RelativeLayout.BELOW,1);
		edtSend.setLayoutParams(param2);
		layout.addView(edtSend);
		
		//송신 버튼의 생성
		btnSend=new Button(this);
		btnSend.setText("송신");
		btnSend.setOnClickListener(this);
		RelativeLayout.LayoutParams param3=// （9）
			new RelativeLayout.LayoutParams(200,50);
		param3.addRule(RelativeLayout.BELOW,1);
		param3.addRule(RelativeLayout.RIGHT_OF,2);
		btnSend.setLayoutParams(param3);
		layout.addView(btnSend);
		
		//스레드 생성 （2）
		(new Thread(){public void run() {
			try {
				connect(IP,8080);
			} catch (Exception e) {
			}
		}}).start();
	}
	
	//접속
	private void connect(String ip,int port) {
		int size;
		byte[] w=new byte[1024];
		txtReceive="";
		try {
			//소켓 접속 （3）
			socket=new Socket(ip,port);
			in =socket.getInputStream();
			out=socket.getOutputStream();
			//수신 반복 루프 (4）
			while (socket!=null && socket.isConnected()) {
				//데이터 수신 （5）
				size=in.read(w);
				if (size<=0) continue;
				txtReceive=new String(w,0,size,"UTF-8");
				//핸들러에 의한 사용자 인터페이스 조작 （6）
				handler.post(new Runnable(){
					public void run() {
						//라벨의 문자열 추가
						lblReceive.setText(
							lblReceive.getText()+txtReceive+BR);
					}
				});
			}
		} catch (Exception e) {
			handler.post(new Runnable(){
				public void run() {
					SocketEx.showDialog(current,"","통신 에러입니다 .");
				}
			});
		}
	}
	
	//버튼 클릭 이벤트 처리
	public void onClick(View v) {
		if (v==btnSend) {
			try {
				//데이터 송신 （7）
				if (socket!=null && socket.isConnected()) {
					byte[] w=edtSend.getText().toString().getBytes("UTF8");
					out.write(w);
					out.flush();
					edtSend.setText("",TextView.BufferType.NORMAL);
				}
			} catch (Exception e) {
				handler.post(new Runnable(){
					public void run() {
						SocketEx.showDialog(current,"","통신 에러입니다 .");
					}
				});
			}
		}
	}
	
	//대화상자 표시
	public static void showDialog(final Activity activity,String title,String text) {
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