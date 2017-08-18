package com.example.checkboxex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mText1;
    CheckBox mCheckCheese;
    CheckBox mCheckPepper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText1 = (TextView)findViewById(R.id.textView1);

        mCheckCheese = (CheckBox)findViewById(R.id.checkCheese);
        mCheckCheese.setOnCheckedChangeListener(mCheckChange);

        mCheckPepper = (CheckBox)findViewById(R.id.checkPepper);
        mCheckPepper.setOnCheckedChangeListener(mCheckChange);
        mCheckPepper.setChecked(true);
    }

    CompoundButton.OnCheckedChangeListener mCheckChange =
    	    new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged (CompoundButton v, boolean isChecked) {
            switch( v.getId() ) {
            case R.id.checkCheese :
                if (isChecked)
                    mText1.setText("'Cheese' is Selected");
                else
                    mText1.setText("'Cheese' is Unselected");
                break;
            case R.id.checkPepper :
                if (isChecked)
                    mText1.setText("'Pepper' is Selected");
                else
                    mText1.setText("'Pepper' is Unselected");
                break;
            }
        }
    };

    public String getCheckResult() {
        String strResult = "";
        int count = 0;
		
        if( mCheckCheese.isChecked() ) {
            strResult = "'Cheese'";
            count ++;
        }
		
        if( mCheckPepper.isChecked() ) {
            if( count > 0 )
                strResult += " & ";
            strResult += "'Pepper'";
            count ++;
        }

        if( count > 0 )
            strResult += " is Selected";
        else 
            strResult = "No selected";
        return strResult;
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnShowState :
            String strResult = getCheckResult();
            Toast.makeText(this, strResult, Toast.LENGTH_SHORT).show();
            break;
        }
    }

}
