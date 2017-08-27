package net.npaka.livefolderex;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.LiveFolders;

// 라이브 폴더의 생성
public class LiveFolderEx extends Activity {
	// 생성 시 호출됨
	@Override
		protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		// 인텐트 액션이 ACTION_CREATE_LIVE_FOLDER인지 여부를 판정 (1)
		Intent intent=getIntent();
		String action=intent.getAction();
		if (LiveFolders.ACTION_CREATE_LIVE_FOLDER.equals(action)) {
			setResult(RESULT_OK, createLiveFolder(this,
				"LiveFolderEx", // 이름
				R.drawable.icon, // 아이콘
				LiveFolders.DISPLAY_MODE_LIST)); // 표시 모드
		} else {
			setResult(RESULT_CANCELED);
		}
		finish();
	}
	
	// 라이브 폴더의 생성 (2)
	private Intent createLiveFolder(
		Context context,String name,int icon,int displayMode) {
		Intent intent=new Intent();
		intent.setData(LiveFolderProvider.CONTACTS_URI);
		intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_NAME,name);
		intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_ICON,
			Intent.ShortcutIconResource.fromContext(context,icon));
		intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_DISPLAY_MODE,
			displayMode);
		return intent;
	}
}