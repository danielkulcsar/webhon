package com.example.edittextex;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText mEditId;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditId = (EditText)findViewById(R.id.editId);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnClear : 
            mEditId.setText(""); 
            break;
        case R.id.btnHide : 
            InputMethodManager imm = (InputMethodManager)getSystemService (Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEditId.getWindowToken(), 0);
            break;
        }
    }
}
