package jp.co.se.android.recipe.chapter14;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ch1403 extends Activity {
    private static final int RESULT_PICK_FILENAME = 1;
    private Uri mAttachFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1402_main);

        findViewById(R.id.selectAttach).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_PICK,
                                Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_PICK_FILENAME);

                    }
                });

        findViewById(R.id.linkMail).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수신처와 본문을 구하기
                EditText etAddress = (EditText) findViewById(R.id.intputAddress);
                EditText etSubject = (EditText) findViewById(R.id.intputSubject);
                EditText etBody = (EditText) findViewById(R.id.intputBody);
                String address = etAddress.getText().toString();
                String subject = etSubject.getText().toString();
                String body = etBody.getText().toString();

                // 수신처, 제목이나 본문 어느 한쪽이 비어 있으면 경고
                if (TextUtils.isEmpty(address) || TextUtils.isEmpty(subject)
                        || TextUtils.isEmpty(body)) {
                    Toast.makeText(Ch1403.this,
                            getString(R.string.ch1403_label_input_empty),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 메일 연동
                Uri uri = Uri.parse("mailto:" + address);
               // 인수로 송신지를 설정, 이 값은 setData로 설정해도 같은 뜻이다
                Intent intent = new Intent(Intent.ACTION_SEND, uri);
                // 여러 개의 송신지가 있는 경우는 Intent.EXTRA_EMAIL을 사용 설정할 수 있다
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { address });
               //설정하는 메일 본문이 Text인 경우는 text/plain, HTML인 경우는 text/html을 설정
                intent.setType("text/plain");
                // 제목을 설정
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                // 본문을 설정
                intent.putExtra(Intent.EXTRA_TEXT, body);
                // 첨부 파일을 설정
                if (mAttachFile != null) {
                    intent.putExtra(Intent.EXTRA_STREAM, mAttachFile);
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(Ch1403.this,
                                getString(R.string.ch1403_label_input_empty),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_PICK_FILENAME && resultCode == RESULT_OK
                && null != data) {
            mAttachFile = data.getData();

            String[] filePathColumn = { Media.DATA };

            Cursor cursor = getContentResolver().query(mAttachFile,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            TextView textAttachFile = (TextView) findViewById(R.id.textAttachFile);
            textAttachFile.setText(picturePath);
        }
    }
}
