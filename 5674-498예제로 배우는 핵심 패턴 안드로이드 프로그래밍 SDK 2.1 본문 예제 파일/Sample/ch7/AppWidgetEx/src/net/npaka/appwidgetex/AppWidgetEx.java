package net.npaka.appwidgetex;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

// 홈 스크린 위젯 프로바이더 (1)
public class AppWidgetEx extends AppWidgetProvider {
	//홈 스크린 위젯 변경 시 호출된다 .
	@Override
		public void onUpdate(Context context,
		AppWidgetManager appWidgetManager,int[] appWidgetIds) {
		// 홈 스크린 위젯 이벤트 처리를 담당하는 서비스 시작 (2)
		Intent intent=new Intent(context,AppWidgetService.class);
		context.startService(intent);
	}
}