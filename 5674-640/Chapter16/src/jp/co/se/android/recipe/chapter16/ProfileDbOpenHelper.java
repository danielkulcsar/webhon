package jp.co.se.android.recipe.chapter16;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProfileDbOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "ProfileDbOpenHelper";
    static final String DATABASE_NAME = "profile.db";
    static final int DATABASE_VERSION = 1;

    public ProfileDbOpenHelper(Context context) {
        // 데이터베이스 파일명과 버전을 지전해 SQLiteOpenHelper 클래스를 초기화
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "ProfileDbOpenHelper 생성자가 호출되었습니다");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d(TAG, "ProfileDbOpenHelper.onCreate가 호출되었습니다");
        // Profile 테이블을 생성
        // @formatter:off
	database.execSQL("CREATE TABLE " 
        + Profile.TBNAME + "(" 
        + Profile._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
        + Profile.NAME + " TEXT NOT NULL, " 
        + Profile.PHOTOGRAPHMAGE + " blob"+ ");");
	// @formatter:on
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "ProfileDbOpenHelper.onUpgrade가 호출되었습니다");
        // Profile 테이블을 재정의하기 위해 현재 테이블을 삭제
        db.execSQL("DROP TABLE IF EXISTS " + Profile.TBNAME);
        onCreate(db);
    }

}