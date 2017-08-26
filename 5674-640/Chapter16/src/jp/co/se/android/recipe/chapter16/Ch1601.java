package jp.co.se.android.recipe.chapter16;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Ch1601 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1601_main);

        findViewById(R.id.buttonStart).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startTest();
                    }
                });
    }

    private void startTest() {
        // ContactDbOpenHelper을 생성
        ContactDbOpenHelper helper = new ContactDbOpenHelper(this);
         // 작성 가능한 SQLiteDatabase 인스턴스를 구하기
        SQLiteDatabase db = helper.getWritableDatabase();
        // 데이터 베이스를 닫는다
        db.close();
        helper.close();
    }

}
