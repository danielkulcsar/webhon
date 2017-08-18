package com.example.createsubactivity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText mEdit;
    final static int ACT_SUB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdit = (EditText)findViewById(R.id.editInput);
    }

    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ActivitySub.class);
        intent.putExtra("TextMsg", mEdit.getText().toString());
        //startActivity(intent);
        startActivityForResult(intent, ACT_SUB);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case ACT_SUB:
            if (resultCode == Activity.RESULT_OK) {
                mEdit.setText(data.getStringExtra("TextMsg"));
                String strMsg="Number : ";
                strMsg += data.getIntExtra("IntMsg", 1);
                Toast.makeText(getApplication(), 
                	    strMsg, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

}
