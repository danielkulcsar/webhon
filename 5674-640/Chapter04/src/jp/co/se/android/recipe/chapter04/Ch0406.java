package jp.co.se.android.recipe.chapter04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Ch0406 extends Activity {

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0406_main);

        findViewById(R.id.launchActivity).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // 호출할 화면 Intent를 작성
                        final Intent intent = new Intent(Ch0406.this,
                                Ch0406Sub.class);
                        // Intent에 KEY_NAME의 키에 문자열을 설정
                        EditText inputString = (EditText) findViewById(R.id.inputString);
                        String value = inputString.getText().toString();
                        intent.putExtra("key_name", value);

                        // 리턴값을 돌려받는 방법으로 Activity를 시작
                        startActivityForResult(intent, REQUEST_CODE);

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // startActivityForResult 실행시에 인수로 설정한 RequestCode를 비교
        if (requestCode == REQUEST_CODE) {
            // Activity 종료시의 플레그를 판정
            if (resultCode == RESULT_OK) {
                // 리턴값으로 설정한 KEY_NAME 값 구함
                String value = data.getStringExtra("key_name");
                EditText result = (EditText) findViewById(R.id.result);
                result.setText(value);
            }
        }
    }
}
