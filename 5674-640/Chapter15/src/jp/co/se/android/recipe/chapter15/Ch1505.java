package jp.co.se.android.recipe.chapter15;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Ch1505 extends Activity {
    private TextView mTextTempState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ch1505_main);

        mTextTempState = (TextView) findViewById(R.id.textTempState);

        printTempfile();
    }

    private void printTempfile() {
        // 내부 스토리지의 캐쉬 폴더의 경로를 구하기
        File internalCachedir = getCacheDir();
        // 외보 스토리지의 캐쉬 폴더의 경로를 구하기
        File externalCachedir = getExternalCacheDir();

        StringBuilder buf = new StringBuilder();
        buf.append("internal Cache Dire:\n").append(internalCachedir.getPath())
                .append("\n\n");
        buf.append("external Cache Dire:\n").append(externalCachedir.getPath())
                .append("\n");

        mTextTempState.setText(buf.toString());
    }
}
