package jp.co.se.android.recipe.chapter01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Ch0123 extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ch0123_main);

		// 어댑터를 작성
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.add("red");
		adapter.add("green");
		adapter.add("blue");

		// 스피너에 어댑터를 설정
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(adapter);

		// 스피너 선택 이벤트를 구함
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String item = (String) parent.getSelectedItem();
				Toast.makeText(Ch0123.this, item + "이(가) 선택되었습니다.",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}
}