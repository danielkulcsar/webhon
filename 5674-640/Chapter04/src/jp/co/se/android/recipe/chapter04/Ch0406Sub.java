package jp.co.se.android.recipe.chapter04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Ch0406Sub extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0406_sub);

        Intent intent = getIntent();
        if (intent != null) {
            // 전달받은 문자열 읽기
            String sValue = intent.getStringExtra("key_name");

            EditText getString = (EditText) findViewById(R.id.inputString);
            getString.setText(sValue);
        }

        findViewById(R.id.backActivity).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        EditText result = (EditText) findViewById(R.id.result);
                        // 리턴값에 문자열이 설정되어 있는 경우에만 리턴값을 설정
                        if (result.getText().length() > 0) {
                            // 리턴값을 위한 Intent를 생성
                            Intent data = new Intent();
                            // 리턴값 설정
                            data.putExtra("key_name", result.getText()
                                    .toString());
                            // 성공으로 설정
                            setResult(RESULT_OK, data);
                        }
                        finish();
                    }
                });
    }
}
