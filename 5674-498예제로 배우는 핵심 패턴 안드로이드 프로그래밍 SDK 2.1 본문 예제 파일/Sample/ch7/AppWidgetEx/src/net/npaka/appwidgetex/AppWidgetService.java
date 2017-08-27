package net.npaka.appwidgetex;
import java.util.*;
import android.app.*;
import android.appwidget.AppWidgetManager;
import android.content.*;
import android.os.IBinder;
import android.widget.RemoteViews;

// 홈 페이지 위젯을 제어하는 서비스
public class AppWidgetService extends Service {
	private static final String ACTION_BTNCLICK =
		"net.npaka.AppWidgetService.ACTION_BTNCLICK";
	
	// 서비스 시작 시에 호출된다.
	@Override
		public void onStart(Intent intent,int startId) {
		super.onStart(intent, startId);
		
		// 원격 뷰의 생성 (3)
		RemoteViews view=new RemoteViews(getPackageName(),R.layout.appwidget);
		
		// PendingIntent의 설정 (4)
		Intent newintent=new Intent();
		newintent.setAction(ACTION_BTNCLICK);
		PendingIntent pending=PendingIntent.getService(this,0,newintent,0);
		view.setOnClickPendingIntent(R.id.button1,pending);
		
		// 흔들기 버튼이 클릭될 때의 처리 (5)
		if (ACTION_BTNCLICK.equals(intent.getAction())) {
			btnClicked(view);
		}

		// 홈 스크린 위젯의 화면 갱신 (6)
		AppWidgetManager manager=AppWidgetManager.getInstance(this);
		ComponentName widget=new ComponentName(
			"net.npaka.appwidgetex",
			"net.npaka.appwidgetex.AppWidgetEx");
		manager.updateAppWidget(widget,view);
	}
	
	// 바인더를 되돌린다.
	@Override
		public IBinder onBind(Intent intent) {
		return null;
	}
	
	// 흔들기 버튼이 클릭되었을 때의 처리 (5)
	public void btnClicked(RemoteViews view){
		int[] ids={
			R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,
				R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
			int idx=rand(6);
			view.setImageViewResource(R.id.imageview1,ids[idx]);
	}
	
	// 난수 구하기
	private static Random rand=new Random();
	public static int rand(int num) {
		return (rand.nextInt()>>>1)%num;
	}
}
