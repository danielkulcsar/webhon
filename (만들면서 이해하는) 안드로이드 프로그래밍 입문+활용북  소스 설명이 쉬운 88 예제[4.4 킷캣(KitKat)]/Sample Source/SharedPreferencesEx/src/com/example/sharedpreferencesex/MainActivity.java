package com.example.sharedpreferencesex;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText mEditPet;
    EditText mEditWeight;
    SharedPreferences mPref = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditPet = (EditText)findViewById(R.id.editPet);
        mEditWeight = (EditText)findViewById(R.id.editWeight);
        mPref = getSharedPreferences("setup", MODE_PRIVATE);
    }

    public void savePreferences() {
        SharedPreferences.Editor editor = mPref.edit();

        String strValue = mEditPet.getText().toString();
        editor.putString("pet", strValue);

        strValue = mEditWeight.getText().toString();
        float fValue = Float.parseFloat(strValue);
        editor.putFloat("weight", fValue);

        editor.commit();
        Toast.makeText(getApplication(), " SharedPreferences is saved", 
                Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonSave :
            savePreferences();
            break;
        case R.id.buttonRead :
            readPreferences();
            break;
        }
    }

    public void readPreferences() {
        String strValue = mPref.getString("pet", "");
        mEditPet.setText(strValue);

        float fValue = mPref.getFloat("weight", 17.5f);
                strValue = Float.toString(fValue);
        mEditWeight.setText(strValue);
    }

}
