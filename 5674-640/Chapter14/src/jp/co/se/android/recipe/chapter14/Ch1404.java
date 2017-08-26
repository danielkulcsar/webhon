package jp.co.se.android.recipe.chapter14;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Ch1404 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1404_main);

        findViewById(R.id.linkText).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 텍스트를 구하기
                EditText etText = (EditText) findViewById(R.id.intputText);
                String text = etText.getText().toString();

                // 텍스트가 비어 있으면 경고
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(Ch1404.this,
                            getString(R.string.ch1404_label_input_empty),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 텍스트 연동
                Intent intent = new Intent();
                // 텍스트 연동을 위하여 Aciton을 설정
                intent.setAction(Intent.ACTION_SEND);
                // 텍스트 데이터 종류를 설정
                intent.setType("text/plain");
                // 텍스트 데이터를 설정
                intent.putExtra(Intent.EXTRA_TEXT, text);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Ch1404.this,
                            getString(R.string.ch1401_label_notfound_app),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
