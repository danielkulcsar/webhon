package jp.co.se.android.recipe.chapter16;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDbOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "ContactDbOpenHelper";

    static final String DATABASE_NAME = "contact.db";
    static final int DATABASE_VERSION = 1;

    public ContactDbOpenHelper(Context context) {
      	// 데이터베이스 파일 이름과 버전을 지정하는 SQLiteOpenHelper 클래스를 초기화
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "ContactDbOpenHelper 컨스트럭터가 호출되었습니다");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d(TAG, "ContactDbOpenHelper.onCreate가 호출되었습니다");
        // Contact 테이블을 생성
        // @formatter:off
	database.execSQL("CREATE TABLE " 
        + Contact.TBNAME + "(" 
        + Contact._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
        + Contact.NAME + " TEXT NOT NULL, " 
        + Contact.AGE + " INTEGER " + ");");
	// @formatter:on
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "ContactDbOpenHelper.onUpgrade가 호출되었습니다");
        // Contact 테이블을 재정의하기 위하여 현재의 테이블 삭제
        db.execSQL("DROP TABLE IF EXISTS " + Contact.TBNAME);
        onCreate(db);
    }

}