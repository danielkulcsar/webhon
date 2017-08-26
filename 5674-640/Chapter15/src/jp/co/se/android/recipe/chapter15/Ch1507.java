package jp.co.se.android.recipe.chapter15;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Ch1507 extends Activity {

    private ListView mListAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ch1507_main);

        mListAddress = (ListView) findViewById(R.id.listAddress);

        loadAddress();
    }

    private void loadAddress() {
        // 연락처 정보를 컨텐츠 프로바이더 경유로 구하기
        Cursor cursor = getContentResolver().query(Phone.CONTENT_URI, null,
                null, null, null);

        // 얻은 정보를 표시하기 위한 SimpleCursorAdapter를 생성
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.ch1507_listrow, cursor, new String[] {
                        Phone.DISPLAY_NAME, Phone.NUMBER }, new int[] {
                        R.id.textName, R.id.textPhone }, 0);

        // 표시용 어댑터로 생성한 SimpleCursorAdapter를 ListView에 설정
        mListAddress.setAdapter(adapter);
    }
}
