package com.example.radiobuttonex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mText1;
    RadioGroup mRadioGroupSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText1 = (TextView)findViewById(R.id.textView1);

        RadioGroup radioGroupPet = (RadioGroup)findViewById(R.id.radioGroupPet);
        radioGroupPet.setOnCheckedChangeListener(mRadioChange);

        mRadioGroupSnack = (RadioGroup)findViewById(R.id.radioGroupSnack);
        mRadioGroupSnack.setOnCheckedChangeListener(mRadioChange);
        mRadioGroupSnack.check(R.id.radioCookie);
    }

    RadioGroup.OnCheckedChangeListener mRadioChange =
    	    new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId() == R.id.radioGroupPet) {
                switch( checkedId ) {
                case R.id.radioCat :
                    mText1.setText("'Cat' is Selected");
                    break;
                case R.id.radioDog :
                    mText1.setText("'Dog' is Selected");
                    break;
                case R.id.radioHamster :
                    mText1.setText("'Hamster' is Selected");
                    break;
                }
            }
        }
    };

    public String getRadioResult() {
        switch( mRadioGroupSnack.getCheckedRadioButtonId() ) {
        case R.id.radioCookie :
            return "Cookie";
        case R.id.radioIcecream :
            return "Icecream";
        case R.id.radioJuice :
            return "Juice";
        }
        return "";
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnShowState :
            String strResult = getRadioResult();
            Toast.makeText(this, strResult, Toast.LENGTH_SHORT).show();
            break;
        }
    }

}
