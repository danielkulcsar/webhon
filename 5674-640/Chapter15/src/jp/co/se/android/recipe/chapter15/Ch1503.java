package jp.co.se.android.recipe.chapter15;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class Ch1503 extends Activity {
    private static final String CONFIG_NAME = "appconfig";

    private EditText mEditConfigText;
    private CheckBox mCheckConfigCheck1;
    private CheckBox mCheckConfigCheck2;
    private Spinner mSpinnerConfigSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ch1503_main);

        mEditConfigText = (EditText) findViewById(R.id.editConfigText);
        mCheckConfigCheck1 = (CheckBox) findViewById(R.id.checkConfigCheck1);
        mCheckConfigCheck2 = (CheckBox) findViewById(R.id.checkConfigCheck2);
        mSpinnerConfigSelect = (Spinner) findViewById(R.id.spinnerConfigSelect);

        loadConfig();
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveConfig();
    }

    private void loadConfig() {
        // Private으로 CONFIG_NAME 파일을 작성하고, SharedPreferences 인스턴스를 구하기
    	SharedPreferences pref = getSharedPreferences(CONFIG_NAME,
                Context.MODE_PRIVATE);

        // editConfigText를 키로 텍스트 값을 얻기
        mEditConfigText.setText(pref.getString("editConfigText", ""));
        // checkConfigCheck1을 키로 Boolean 값을 구하기
        mCheckConfigCheck1.setChecked(pref.getBoolean("checkConfigCheck1",
                false));
        // checkConfigCheck2을 키로 Boolean 값을 구하기
        mCheckConfigCheck2.setChecked(pref.getBoolean("checkConfigCheck2",
                false));
        // spinnerConfigSelect을 키로 정수 값을 구하기
        mSpinnerConfigSelect
                .setSelection(pref.getInt("spinnerConfigSelect", 0));
    }

    private void saveConfig() {
        // Private에 CONFIG_NAME 파일을 작성하고 SharedPreferences 인스턴스를 구하기
        SharedPreferences pref = getSharedPreferences(CONFIG_NAME,
                Context.MODE_PRIVATE);
        Editor editor = pref.edit();

        // editConfigText를 키로 텍스트 값을 설정
        editor.putString("editConfigText", mEditConfigText.getText().toString());
        // checkConfigCheck1을 키로 Boolean 값을 설정
        editor.putBoolean("checkConfigCheck1", mCheckConfigCheck1.isChecked());
        // mCheckConfigCheck2을 키로 Boolean 값을 설정
        editor.putBoolean("checkConfigCheck2", mCheckConfigCheck2.isChecked());
        // spinnerConfigSelect을 키로 정수 값을 설정
        editor.putInt("spinnerConfigSelect",
                mSpinnerConfigSelect.getSelectedItemPosition());

        //설정을 반영
        editor.commit();
    }

}
