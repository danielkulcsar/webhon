package jp.co.se.android.recipe.chapter16;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Ch1603 extends Activity {
    private static final String TAG = "Ch1603";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1603_main);

        findViewById(R.id.buttonStart).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startTest();
                    }
                });
    }

    private void startTest() {
        ContactDbOpenHelper helper = null;
        SQLiteDatabase db = null;
        try {
            // ContactDbOpenHelper을 생성
            helper = new ContactDbOpenHelper(this);
            // 쓰기 가능한 SQLiteDatabase 인스턴스를 구하기
            db = helper.getWritableDatabase();

            // 데이터 검색
            searchData(db);
        } finally {
            if (db != null) {
                db.close();
            }
            if (helper != null) {
                helper.close();
            }
        }
    }

    private void searchData(SQLiteDatabase db) {
        Cursor cursor = null;
        try {
            // Comments 테이블 의 모든 데이터를 구하기
            cursor = db.query(Contact.TBNAME, null, Contact.AGE + " > ?",
                    new String[] { Integer.toString(20) }, null, null,
                    Contact.NAME);
            // Cursor에 데이터가 1 건 이상 있는 경우는 처리한다
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // 이름을 구하기
                    String name = cursor.getString(cursor
                            .getColumnIndex(Contact.NAME));
                    // 연령을 구하기
                    int age = cursor.getInt(cursor.getColumnIndex(Contact.AGE));
                    Log.d(TAG, name + ":" + age);

                    // 다음의 데이터로 Cursor를 이동
                }
                ;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
