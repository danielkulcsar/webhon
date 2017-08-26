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

public class Ch1409 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1409_main);

        findViewById(R.id.linkYoutube).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 검색 단어를 구한다
                        EditText etSearchWord = (EditText) findViewById(R.id.intputSearchWord);
                        String searchWord = etSearchWord.getText().toString();

                        // 검색 단어가 비어 있으면 경고
                        if (TextUtils.isEmpty(searchWord)) {
                            Toast.makeText(
                                    Ch1409.this,
                                    getString(R.string.ch1409_label_input_empty),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // YouTube 연덩
                        Intent intent = new Intent(Intent.ACTION_SEARCH);
                        intent.setPackage("com.google.android.youtube");
                        // 검색 단어를 설정
                        intent.putExtra("query", searchWord);
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(
                                    Ch1409.this,
                                    getString(R.string.ch1401_label_notfound_app),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
