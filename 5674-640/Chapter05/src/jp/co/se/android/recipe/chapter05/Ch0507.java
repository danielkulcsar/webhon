package jp.co.se.android.recipe.chapter05;

import jp.co.se.android.recipe.chapter05.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Ch0507 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onUserLeaveHint() {
        Toast.makeText(getApplicationContext(), "홈버튼이 클릭됨",
                Toast.LENGTH_SHORT).show();
    }

}