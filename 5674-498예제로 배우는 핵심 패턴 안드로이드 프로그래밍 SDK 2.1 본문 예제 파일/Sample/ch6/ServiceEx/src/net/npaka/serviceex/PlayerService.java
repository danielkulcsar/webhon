package net.npaka.serviceex;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

//플레이어 서비스
public class PlayerService extends Service {// （3）
	private NotificationManager nm;//통지 관리자
	private MediaPlayer player; //플레이어
	
	//초기화
	@Override
		public void onCreate() {
		//통지 관리자 구하기 （4）
		nm=(NotificationManager)getSystemService(
			Context.NOTIFICATION_SERVICE);
		
		//플레이어 생성
		try {
			player=MediaPlayer.create(this,R.raw.sample);
			player.prepare();
		} catch (Exception e) {
		}
	}
	
	//초기화
	@Override
		public void onStart(Intent intent,int startID) {
		//통지 표시
		showNotification(this,R.drawable.icon,
			"BGM을 재생합니다 .",
			"플레이어 서비스",
			"플레이어 서비스를 시작했습니다 .");
		
		//음악 재생
		try {
			player.seekTo(0);
			player.start();
		} catch (Exception e) {
		}
	}
	
	//마무리 처리
	@Override
		public void onDestroy() {
		//통지 취소 （6）
		nm.cancel(0);
		
		//음악의 정지 및 해제
		player.stop();
		player.release();
		
		//토스트 표시
		showToast(this,"플레이어가 정지되었습니다 .");
	}
	
	//바인드
	@Override
		public IBinder onBind(Intent intent) {
		return null;
	}
	
	//통지의 표시
	private void showNotification(Context context,
		int iconID,String ticker,String title,String message) {
		//통지 객체의 생성 （5）
		Notification notification=new Notification(iconID,
			ticker,System.currentTimeMillis());
		PendingIntent intent=PendingIntent.getActivity(context,0,
			new Intent(context,net.npaka.serviceex.ServiceEx.class),0);
		notification.setLatestEventInfo(context,
			title,message,intent);
		
		//통지의 취소 （6）
		nm.cancel(0);
		
		//통지의 표시 （7）
		nm.notify(0,notification);
	}
	
	//토스트 표시
	private static void showToast(Context context,String text) {
		Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
	}
}