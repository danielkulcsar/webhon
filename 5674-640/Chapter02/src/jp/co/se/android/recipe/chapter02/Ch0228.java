package jp.co.se.android.recipe.chapter02;

import jp.co.se.android.recipe.chapter02.R;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Ch0228 extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ch0228_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_check) {
            // 체크상태를 반전시킴
            item.setChecked(!item.isChecked());

            final String msg;
            if (item.isChecked()) {
                msg = "체크상태입니다.";
            } else {
                msg = "체크상태가 아닙니다.";
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
