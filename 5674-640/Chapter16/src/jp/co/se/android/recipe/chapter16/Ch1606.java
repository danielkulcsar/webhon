package jp.co.se.android.recipe.chapter16;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Ch1606 extends Activity {
    private static final String TAG = "Ch1606";
    // @formatter:off
    private String[] NAMES = new String[] {
            "Anastassia", "Juan", "Enrique",
            "Frannie", "Paloma", "Francisco",
            "Lorenzio", "Maryvonne", "Siv",
            "Georgie", "Casimir", "Catharine",
            "Joker"};
    // @formatter:on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1606_main);

        findViewById(R.id.buttonStart).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ContactDbOpenHelper helper = null;
                        SQLiteDatabase db = null;
                        try {
                            // ContactDbOpenHelper를 생성
                            helper = new ContactDbOpenHelper(Ch1606.this);
                            // 쓸 수 있는 SQLiteDatabase 인스턴스를 취득
                            db = helper.getWritableDatabase();

                            // 데이터 생성
                            createDataByTransaction(db);
                        } finally {
                            if (db != null) {
                                db.close();
                            }
                            if (helper != null) {
                                helper.close();
                            }
                        }
                    }
                });
    }

    private void createDataByTransaction(SQLiteDatabase db) {
        try {
            // 트랜잭션 시작
            db.beginTransaction();
            Log.d(TAG, "트랜잭션을 시작");

            // 현재 저장되어 있는 데이터를 전부 삭제
            db.delete(Contact.TBNAME, null, null);

            for (int i = 0; i < NAMES.length; i++) {
                // 생성하는 데이터를 저장하는 ContentValues를 생성
                ContentValues values = new ContentValues();
                values.put(Contact.NAME, NAMES[i]);
                values.put(Contact.AGE, 20);
                // 데이터 베이스에 Contact 데이터를 생성
                db.insert(Contact.TBNAME, null, values);
                Log.d(TAG, String.format("데이터를 생성[%d]", i + 1));
            }
            // 트랜잭션을 확정
            db.setTransactionSuccessful();
            Log.d(TAG, "트랜잭션을 확정");
        } finally {
            // 트랜잭션을 종료
            db.endTransaction();
            Log.d(TAG, "트랜잭션을 종료");
        }
    }

}
