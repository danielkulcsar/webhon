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

public class Ch1401 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1401_main);

        findViewById(R.id.linkSms).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수신처와 본문을 취득
                EditText etAddress = (EditText) findViewById(R.id.intputAddress);
                EditText etBody = (EditText) findViewById(R.id.intputBody);
                String address = etAddress.getText().toString();
                String body = etBody.getText().toString();

                // 수신처나 본문 어느 한쪽이 비어 있으면 경고
                if (TextUtils.isEmpty(address) || TextUtils.isEmpty(body)) {
                    Toast.makeText(Ch1401.this,
                            getString(R.string.ch1401_label_input_empty),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // SMS 연동
                Uri uri = Uri.parse("smsto:" + address);
                // 인수로 송신지를 설정, 이 값은 setData로 설정해도 같다
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                // 본문을 설정
                intent.putExtra("sms_body", body);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Ch1401.this,
                            getString(R.string.ch1401_label_notfound_app),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
