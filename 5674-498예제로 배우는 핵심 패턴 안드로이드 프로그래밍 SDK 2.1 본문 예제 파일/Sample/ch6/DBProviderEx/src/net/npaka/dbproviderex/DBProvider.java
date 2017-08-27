package net.npaka.dbproviderex;
import android.content.ContentProvider;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

//DB를 제공하는 컨텐츠 프로바이더
public class DBProvider extends ContentProvider {
	private final static String DB_NAME ="test.db";//DB명
	private final static String DB_TABLE="test"; //테이블명
	private final static int DB_VERSION=1; //버전
	
	private SQLiteDatabase db;//데이터베이스
	
	//컨텐츠 프로바이더의 초기화
	@Override
		public boolean onCreate() {
		//데이터베이스의 생성 （3）
		DBHelper dbHelper=new DBHelper(getContext());
		db=dbHelper.getWritableDatabase();
		return (db!=null);
	}
	
	//데이터베이스의 쿼리 명령 （4）
	@Override
		public Cursor query(Uri uri,String[] columns,String selection,
		String[] selectionArgs,String sortOrder) {
		return db.query(DB_TABLE,columns,selection,
			selectionArgs,null,null,null);
	}
	
	//데이터베이스의 갱신 명령 （5）
	@Override
		public int update(Uri uri,ContentValues values,
		String selection,String[] selectionArgs) {
		int num=db.update(DB_TABLE,values,null,null);
		if (num==0) db.insert(DB_TABLE,"",values);
		return 1;
	}
	
	//데이터베이스의 삽입 명령(미사용)
	@Override
		public Uri insert(Uri uri,ContentValues values) {
		return null;
	}
	
	//데이터베이스의 삭제 명령(미사용)
	@Override
		public int delete(Uri uri,String selection,
		String[] selectionArgs) {
		return 0;
	}
	
	//종류 구하기(미사용)
	@Override
		public String getType(Uri uri) {
		return null;
	}
	
	//데이터베이스 헬퍼 정의
	private static class DBHelper extends SQLiteOpenHelper {
		//생성자
		public DBHelper(Context context) {
			super(context,DB_NAME,null,DB_VERSION);
		}
		
		//데이터베이스의 생성
		@Override
			public void onCreate(SQLiteDatabase db) {
			db.execSQL(
				"create table if not exists "+
				DB_TABLE+"(id text primary key,info text)");
		}
		
		//데이터베이스의 업그레이드
		@Override
			public void onUpgrade(SQLiteDatabase db,
			int oldVersion,int newVersion) {
			db.execSQL(
				"drop talbe if exists "+DB_TABLE);
			onCreate(db);
		}
	}
}