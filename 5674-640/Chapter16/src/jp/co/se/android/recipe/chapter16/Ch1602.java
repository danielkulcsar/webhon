package jp.co.se.android.recipe.chapter16;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Ch1602 extends Activity {
    private static final String TAG = "Ch1602";

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
        setContentView(R.layout.ch1602_main);

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
            // 쓰기 가능한 SQLiteDatabase 인스턴스를 구한다
            db = helper.getWritableDatabase();

            // 데이터 작성
            createData(db);
            // 데이터 갱신
            updateData(db);
            // 데이터 삭제
            deleteData(db);
        } finally {
            if (db != null) {
                db.close();
            }
            if (helper != null) {
                helper.close();
            }
        }
    }

    private void createData(SQLiteDatabase db) {
        for (int i = 0; i < NAMES.length; i++) {
            // 생성하는 데이터를 저장하는 ContentValues를 생성
            ContentValues values = new ContentValues();
            values.put(Contact.NAME, NAMES[i]);
            values.put(Contact.AGE, 20);
            // 반환 값은 생성된 데이터의_ID가 반환된다
            long id = db.insert(Contact.TBNAME, null, values);
            Log.d(TAG, "insert data:" + id);
        }
    }

    private void updateData(SQLiteDatabase db) {
        // 갱신 데이터를 저장하는 ContentValues를 생성
        ContentValues values = new ContentValues();
        // Contact.NAME에 a가 포함되는 데이터의 연령을 25로 변경
        values.put(Contact.AGE, 25);
        // 반환 값은 갱신한 수를 반환한다
        int n = db.update(Contact.TBNAME, values, Contact.NAME + " like ?",
                new String[] { "%a%" });
        Log.d(TAG, "insert data:" + n);
    }

    private void deleteData(SQLiteDatabase db) {
        // Contact.NAME이 Joker 데이터를 삭제
        // 반환 값은 삭제한 수가 반환된다
        int n = db.delete(Contact.TBNAME, Contact.NAME + " = ?",
                new String[] { "Joker" });
        Log.d(TAG, "delete data:" + n);
    }

}
