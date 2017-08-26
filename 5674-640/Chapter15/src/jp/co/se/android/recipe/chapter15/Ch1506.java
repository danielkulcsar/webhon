package jp.co.se.android.recipe.chapter15;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class Ch1506 extends Activity implements ViewBinder {
    private ListView mListSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ch1506_main);

        mListSongs = (ListView) findViewById(R.id.listSongs);

        loadSongs();
    }

    private void loadSongs() {
    	//외부 스토리지 상의 음악 파일의 정보를 ContentProvider 경유로 구하기
        Cursor cursor = getContentResolver().query(Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);

        // 얻은 정보를 표시하기 위한 SimpleCursorAdapter을 생성
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.ch1506_listrow, cursor, new String[] { Media.TITLE,
                        Media.ARTIST, Media.DURATION }, new int[] {
                        R.id.textTitle, R.id.textArtist, R.id.textTime }, 0);
        // SimpleCursorAdapter 표시를 커스터마이즈하기 위한 Binder를 설정
        adapter.setViewBinder(this);

        // 표시용 어댑터로 생성한 SimpleCursorAdapter를 ListView에 설정
        mListSongs.setAdapter(adapter);
    }

    @Override
    public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
        int index = cursor.getColumnIndex(Media.DURATION);
        if (index == columnIndex) {
            TextView textTime = (TextView) view;
            long durationMs = cursor.getLong(columnIndex);
            long duration = durationMs / 1000;
            long h = duration / 3600;
            long m = (duration - h * 3600) / 60;
            long s = duration - (h * 3600 + m * 60);
            textTime.setText(String.format("%02d:%02d", m, s));
            return true;
        }
        return false;
    }
}
