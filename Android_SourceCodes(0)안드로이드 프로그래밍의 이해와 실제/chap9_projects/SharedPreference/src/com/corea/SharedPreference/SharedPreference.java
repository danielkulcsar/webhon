package com.corea.SharedPreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class SharedPreference extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // Shared Preference�� �ҷ��ɴϴ�.
        EditText edit1 = (EditText)findViewById(R.id.EditText);
    	CheckBox check1 = (CheckBox)findViewById(R.id.CheckBox01);
    	CheckBox check2 = (CheckBox)findViewById(R.id.CheckBox02);
    	
        // ����� ������ �ҷ��ɴϴ�.
    	String text = pref.getString("editText", "");
    	Boolean chk1 = pref.getBoolean("check1", false);
    	Boolean chk2 = pref.getBoolean("check2", false);
    	
    	edit1.setText(text);
    	check1.setChecked(chk1);
    	check2.setChecked(chk2);
    }
    
    public void onStop(){ // ���ø����̼��� ����ɶ�
    	super.onStop();
    	SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // UI ���¸� �����մϴ�.
    	SharedPreferences.Editor editor = pref.edit(); // Editor�� �ҷ��ɴϴ�.
    	
    	EditText edit1 = (EditText)findViewById(R.id.EditText);
    	CheckBox check1 = (CheckBox)findViewById(R.id.CheckBox01);
    	CheckBox check2 = (CheckBox)findViewById(R.id.CheckBox02);
    	
                // ������ ������ �Է��մϴ�.
    	editor.putString("editText", edit1.getText().toString());
    	editor.putBoolean("check1", check1.isChecked());
    	editor.putBoolean("check2", check2.isChecked());
    	
    	editor.commit(); // �����մϴ�.
    }
}