package net.npaka.livefolderex;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.LiveFolders;

// 라이브 폴더 프로바이더
public class LiveFolderProvider extends ContentProvider {
	//URL
	public static final String AUTHORITY="net.npaka.livefolderex.test";
	public static final Uri CONTACTS_URI=
		Uri.parse("content://"+AUTHORITY+"/test");
	
	// 생성 시 호출됨
	@Override
		public boolean onCreate() {
		return true;
	}
	
	// 커서의 열명 (1)
	private static final String[] CURSOR_COLUMNS=new String[] {
		BaseColumns._ID,
			LiveFolders.NAME,
			LiveFolders.DESCRIPTION,
			LiveFolders.INTENT,
			LiveFolders.ICON_PACKAGE,
			LiveFolders.ICON_RESOURCE
	};
	
	// 쿼리 실행 시 호출됨
	public Cursor query(Uri uri,String[] projection,String selection,
		String[] selectionArgs,String sortOrder) {
		// 매트릭스 커서의 생성 (3)
		MatrixCursor mc=new MatrixCursor(CURSOR_COLUMNS);
		
		// 매트릭스 커서에 행 추가 (2)
		mc.addRow(new Object[]{
			0, // ID
				"Web 페이지 표시", // 이름
				"Web 페이지 표시하기", // 상세
				Uri.parse("http://npaka.net/"), // 인텐트
				getContext().getPackageName(), // 아이콘 패키지
				R.drawable.icon // 아이콘 리소스
		});
		mc.addRow(new Object[]{
			1, // ID
				"다이얼러 표시", // 이름
				"다이얼러를 표시한다", // 상세
				Uri.parse("tel:117"), // 인텐트
				getContext().getPackageName(), // 아이콘 패키지
				R.drawable.icon // 아이콘 리소스
		});
		return mc;
	}
	
	// 갱신 시 호출됨(사용하지 않음)
	@Override
		public int update(Uri uri,ContentValues values,String selection,String[] selectionArgs) {
		return 0;
	}
	
	// 삽입 시 호출됨(사용하지 않음)
	@Override
		public Uri insert(Uri uri,ContentValues initialValues) {
		return null;
	}
	
	// 삭제 시 호출됨(사용하지 않음)
	@Override
		public int delete(Uri uri,String selection,String[] selectionArgs) {
		return 0;
	}
	
	// 종류를 구함(사용하지 않음)
	@Override
		public String getType(Uri uri) {
		return null;
	}
}