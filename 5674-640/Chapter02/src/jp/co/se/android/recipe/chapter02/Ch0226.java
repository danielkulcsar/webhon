package jp.co.se.android.recipe.chapter02;

import jp.co.se.android.recipe.chapter02.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class Ch0226 extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView 보다 전에 Window에 ActionBar 표시를 설정
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.ch0226_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ch0226_menu, menu);

        // 메뉴 표시를 추가
        MenuItem actionItem = menu.add("프로그램에서 추가된 항목");

        // SHOW_AS_ACTION_IF_ROOM: 여유가 있으면 표시, SHOW_AS_ACTION_WITH_TEXT:텍스트도 동시에 표시
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        // 아이콘을 설정
        actionItem.setIcon(android.R.drawable.ic_menu_compass);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "선택한 항목: " + item.getTitle(), Toast.LENGTH_SHORT)
                .show();
        return true;
    }
}