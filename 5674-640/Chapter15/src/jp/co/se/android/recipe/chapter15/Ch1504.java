package jp.co.se.android.recipe.chapter15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Ch1504 extends Activity {
    private TextView mTextAlice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ch1504_main);

        mTextAlice = (TextView) findViewById(R.id.textAlice);

        readFile(R.raw.alice);
    }

    private void readFile(int resId) {
        try {
            // Raw 리소스 폴더의 리소스 ID에서 InputStream 인스턴스를 구하기
            BufferedReader bufferReader = new BufferedReader(
                    new InputStreamReader(getResources().openRawResource(resId)));
            // BufferedReader을 사용하여 1 줄씩 읽기
            String StringBuffer;
            String stringText = "";
            while ((StringBuffer = bufferReader.readLine()) != null) {
                stringText += StringBuffer;
            }
            bufferReader.close();
            // 읽은 파일 내용을 화면에 표시
            mTextAlice.setText(stringText);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mTextAlice.setText(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            mTextAlice.setText(e.toString());
        }
    }
}
