package jp.co.se.android.recipe.chapter14;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Ch1402 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1402_main);

        findViewById(R.id.linkMail).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수신처·제목과 본문을 구하기
                EditText etAddress = (EditText) findViewById(R.id.intputAddress);
                EditText etSubject = (EditText) findViewById(R.id.intputSubject);
                EditText etBody = (EditText) findViewById(R.id.intputBody);
                String address = etAddress.getText().toString();
                String subject = etSubject.getText().toString();
                String body = etBody.getText().toString();

                // 수신처·제목과 본문이 비어 있으면 경고
                if (TextUtils.isEmpty(address) || TextUtils.isEmpty(subject)
                        || TextUtils.isEmpty(body)) {
                    Toast.makeText(Ch1402.this,
                            getString(R.string.ch1401_label_notfound_app),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 메일 연동
                Uri uri = Uri.parse("mailto:" + address);
                // 인수로 송신지를 설정, 이 값은 setData로 설정해도 같은 뜻이다
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                //설정할 메일 본문이 Text인 경우는 text/plain, HTML의 경우는 text/html을 설정              
                intent.setType("text/plain");
                // 제목을 설정
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                // 본문을 설정
                intent.putExtra(Intent.EXTRA_TEXT, body);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Ch1402.this,
                            getString(R.string.ch1402_label_input_empty),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
