package jp.co.se.android.recipe.chapter01;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

public class Ch0119 extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.ch0119_main);
    final EditText et = (EditText) findViewById(R.id.restriction);

    // 메일 주소용 필터를 작성
    InputFilter inputFilter = new InputFilter() {
      @Override
      public CharSequence filter(CharSequence source, int start, int end,
              Spanned dest, int dstart, int dend) {
        if (source.toString().matches("^[0-9a-zA-Z@\\.\\_\\-]+$")) {
          return source;
        } else {
          return "";
        }
      }
    };

    // 필터를 설정
    InputFilter[] filters = new InputFilter[] { inputFilter };
    // 필터를 적용
    et.setFilters(filters);

  }
}
