package com.example.systemproperty;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText mEditPet;
    EditText mEditPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditPet = (EditText)findViewById(R.id.editPet);
        mEditPercent = (EditText)findViewById(R.id.editPercent);
    }

    public void saveProperty() {
        String strPet = mEditPet.getText().toString();
        System.setProperty("property_pet", strPet);

        String strPercent = mEditPercent.getText().toString();
        float fValue = Float.parseFloat(strPercent) / 100.f;
        strPercent = Float.toString(fValue);
        System.setProperty("property_percent", strPercent);

        Toast.makeText(this, "Property saved : " + strPet + " / " 
                + fValue, 0).show();
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonSave :
            saveProperty();
            break;
        case R.id.buttonRead :
            readProperty();
            break;
        }
    }

    public void readProperty() {
        String strPet = System.getProperty("property_pet");
        mEditPet.setText(strPet);
        
        String strPercent = System.getProperty("property_percent", "0.5");
        float fValue = Float.parseFloat(strPercent) * 100.f;
        strPercent = Float.toString(fValue);
        mEditPercent.setText(strPercent);
    }

}
