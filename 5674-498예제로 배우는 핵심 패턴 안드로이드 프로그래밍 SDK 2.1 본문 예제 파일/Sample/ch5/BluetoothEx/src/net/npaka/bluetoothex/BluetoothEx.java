package net.npaka.bluetoothex;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BluetoothEx extends Activity
implements View.OnClickListener {
	//메시지 정수
	public static final int MSG_STATE_CHANGE=1;
	public static final int MSG_READ =2;
	
	// 요청 정수
	private static final int RQ_CONNECT_DEVICE=1;
	private static final int RQ_ENABLE_BT =2;
	
	// Bluetooth
	private BluetoothAdapter btAdapter;
	private BluetoothChatService chatService;
	
	// UI
	private TextView lblReceive; //수신 라벨
	private EditText edtSend; //송신 텍스트 박스
	private Button btnSend; //송신 버튼
	
	// 어플리케이션 생성 시 불린다.
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// 레이아웃의 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		// 송신 텍스트 박스의 생성
		edtSend=new EditText(this);
		edtSend.setId(2);
		edtSend.setText("",TextView.BufferType.NORMAL);
		setLLParams(edtSend,
			LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);
		layout.addView(edtSend);
		
		// 송신 버튼의 생성
		btnSend=new Button(this);
		btnSend.setText("송신");
		btnSend.setOnClickListener(this);
		setLLParams(btnSend);
		layout.addView(btnSend);
		
		// 수신 라벨의 생성
		lblReceive=new TextView(this);
		lblReceive.setId(1);
		lblReceive.setText("");
		lblReceive.setTextSize(16.0f);
		lblReceive.setTextColor(Color.rgb(0,0,0));
		setLLParams(lblReceive,
			LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);
		layout.addView(lblReceive);
		
		// Bluetooth 어댑터
		btAdapter=BluetoothAdapter.getDefaultAdapter();
	}
	
	// 어플리케이션 시작 시 불린다.
	@Override
		public void onStart() {
		super.onStart();
		if (!btAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
				BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent,RQ_ENABLE_BT);
		} else {
			if (chatService==null) chatService=
				new BluetoothChatService(this,handler);
		}
	}
	
	// 어플리케이션 재개 시 불린다.
	@Override
		public synchronized void onResume() {
		super.onResume();
		if (chatService!=null) {
			if (chatService.getState() ==BluetoothChatService.STATE_NONE) {
				// Bluetooth의 접속 대기(서버)
				chatService.start();
			}
		}
	}
	
	// 어플리케이션 파괴 시 불린다.
	@Override
		public void onDestroy() {
		super.onDestroy();
		if (chatService!=null) chatService.stop();
	}
	
	// 다른 Bluetooth 단말기로부터의 검색을 유효하게 설정 (4)
	private void ensureDiscoverable() {
		if (btAdapter.getScanMode()!=
			BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Intent discoverableIntent=new Intent(
				BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(
				BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
			startActivity(discoverableIntent);
		}
	}
	
	// 채팅 서버로부터 정보를 취득하는 핸들러
	private final Handler handler=new Handler() {
		// 핸들 메시지
		@Override
			public void handleMessage(Message msg) {
			switch (msg.what) {
				// 상태 변경
			case MSG_STATE_CHANGE:
				switch (msg.arg1) {
				case BluetoothChatService.STATE_CONNECTED:
					addText("접속 완료") ;break;
				case BluetoothChatService.STATE_CONNECTING:
					addText("접속 중") ;break;
				case BluetoothChatService.STATE_LISTEN:
				case BluetoothChatService.STATE_NONE:
					addText("미접속") ;break;
				}
				break;
				// 메시지 수신
				case MSG_READ:
					byte[] readBuf=(byte[]) msg.obj;
					addText(new String(readBuf,0,msg.arg1));
					break;
			}
		}
	};
	
	// 어플리케이션 복귀 시 불린다.
	public void onActivityResult(int requestCode,int resultCode,Intent data) {
		switch (requestCode) {
			// 단말기 검색
		case RQ_CONNECT_DEVICE:
			if (resultCode==Activity.RESULT_OK) {
				String address=data.getExtras().
					getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
				
				// Bluetooth의 접속 요구(클라이언트)
				BluetoothDevice device=btAdapter.getRemoteDevice(address);
				chatService.connect(device);
			}
			break;
			// 검색 유효
		case RQ_ENABLE_BT:
			if (resultCode==Activity.RESULT_OK) {
				chatService=new BluetoothChatService(this,handler);
			} else {
				Toast.makeText(this,"Bluetooth가 유효하지 않습니다",
					Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}
	
	// 옵션 메뉴 생성 시 불린다.
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item0=menu.add(0,0,0,"단말기 검색");
		item0.setIcon(android.R.drawable.ic_search_category_default);
		MenuItem item1=menu.add(0,1,0,"검색 유효");
		item1.setIcon(android.R.drawable.ic_menu_call);
		return true;
	}
	
	// 옵션 메뉴 선택 시 불린다.
	@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			// 검색
		case 0:
			Intent serverIntent=new Intent(this,DeviceListActivity.class);
			startActivityForResult(serverIntent,RQ_CONNECT_DEVICE);
			return true;
			// 검색 유효
		case 1:
			ensureDiscoverable();
			return true;
		}
		return false;
	}
	
	// 수신 텍스트의 추가
	private void addText(final String text) {
		// 핸들러에 의한 사용자 인터페이스 조작
		handler.post(new Runnable(){
			public void run() {
				lblReceive.setText(text+
					System.getProperty("line.separator")+
					lblReceive.getText());
			}
		});
	}
	
	// 버튼 클릭 이벤트의 처리
	public void onClick(View v) {
		if (v==btnSend) {
			try {
				// 메시지의 송신
				String message=edtSend.getText().toString();
				if (message.length()>0) {
					chatService.write(message.getBytes());
				}
				addText(message);
				edtSend.setText("",TextView.BufferType.NORMAL);
			} catch (Exception e) {
				addText("통신 실패했습니다");
			}
		}
	}
	
	// 리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
	
	// 리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view,int w,int h) {
		view.setLayoutParams(new LinearLayout.LayoutParams(w,h));
	}
}