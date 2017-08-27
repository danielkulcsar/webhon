package net.npaka.optionmenuex;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

//옵션 메뉴
public class OptionMenuEx extends Activity {
	//메뉴 항목 ID
	private static final int
		MENU_ITEM0=0,
		MENU_ITEM1=1,
		MENU_ITEM2=2;
	
	//어플리케이션 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		//레이아웃 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
	}
	
	//옵션 메뉴 생성 (1）
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		//메뉴 항목0 추가 (2）
		MenuItem item0=menu.add(0,MENU_ITEM0,
			0,R.string.menu_item0);
		item0.setIcon(android.R.drawable.ic_menu_add);
		
		//메뉴 항목1 추가
		MenuItem item1=menu.add(0,MENU_ITEM1,
			0,R.string.menu_item1);
		item1.setIcon(android.R.drawable.ic_menu_call);
		
		//메뉴 항목2 추가
		MenuItem item2=menu.add(0,MENU_ITEM2,
			0,R.string.menu_item2);
		item2.setIcon(android.R.drawable.ic_menu_save);
		return true;
	}
	
	//메뉴 항목 선택 이벤트 처리 （3）
	@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ITEM0:
			showDialog(this,"","항목0이 눌렸음");
			return true;
		case MENU_ITEM1:
			showDialog(this,"","항목1이 눌렸음");
			return true;
		case MENU_ITEM2:
			showDialog(this,"","항목2가 눌렸음");
			return true;
		}
		return true;
	}
	
	//대화상자 표시
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