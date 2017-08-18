package com.example.createsubactivity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class ActivitySub extends Activity {
    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        mEdit = (EditText)findViewById(R.id.editInput);
        Intent intent = getIntent();
        String text = intent.getStringExtra("TextMsg");
        if (text != null)
            mEdit.setText(text);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnBack :
            Intent intent = new Intent();
            intent.putExtra("TextMsg", mEdit.getText().toString());
            int nNumber = 42650730;
            intent.putExtra("IntMsg", nNumber);
            setResult(Activity.RESULT_OK, intent);
            finish();
            break;
        }
    }

}
