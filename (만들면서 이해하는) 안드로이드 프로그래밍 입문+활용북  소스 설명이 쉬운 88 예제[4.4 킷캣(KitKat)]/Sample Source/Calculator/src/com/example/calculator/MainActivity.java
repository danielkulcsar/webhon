package com.example.calculator;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextValue;
    float mValue1;
    float mValue2;
    int mCalcMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      	mTextValue = (TextView)findViewById(R.id.textValue);
      	mTextValue.setText("0");
    }

    public float getTextValueFloat() {
        float fValue=0.f;
        String strText = mTextValue.getText().toString();
        fValue = Float.parseFloat(strText);
        return fValue;
    }
    
    public void onNumberButton(String strNumber) {
        String strText = mTextValue.getText().toString();
        float fValue = getTextValueFloat();
        if( fValue == 0.f )
            strText = "";

        strText = strText + strNumber;
        mTextValue.setText(strText);
    }
    
    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.button0 :
            onNumberButton("0"); break;
        case R.id.button1 :
            onNumberButton("1"); break;
        case R.id.button2 :
            onNumberButton("2"); break;
        case R.id.button3 :
            onNumberButton("3"); break;
        case R.id.button4 :
            onNumberButton("4"); break;
        case R.id.button5 :
            onNumberButton("5"); break;
        case R.id.button6 :
            onNumberButton("6"); break;
        case R.id.button7 :
            onNumberButton("7"); break;
        case R.id.button8 :
            onNumberButton("8"); break;
        case R.id.button9 :
            onNumberButton("9"); break;
        case R.id.buttonPoint :
            onNumberButton("."); break;
        case R.id.buttonClear :
            mTextValue.setText("0"); break;
        case R.id.buttonBack : {
            String strText = mTextValue.getText().toString();
            int nLength = strText.length();
            if( nLength > 0 ) {
                strText = strText.substring(0, nLength - 1);
                if( strText.length() == 0 )
                    strText = "0";
                mTextValue.setText(strText);
            }
        }
            break;
        case R.id.buttonPlus :
            mValue1 = getTextValueFloat();
            mTextValue.setText("0");
            mCalcMode = 1;
            break;
        case R.id.buttonMinus :
            mValue1 = getTextValueFloat();
            mTextValue.setText("0");
            mCalcMode = 2;
            break;
        case R.id.buttonMultiply :
            mValue1 = getTextValueFloat();
            mTextValue.setText("0");
            mCalcMode = 3;
            break;
        case R.id.buttonDivide :
            mValue1 = getTextValueFloat();
            mTextValue.setText("0");
            mCalcMode = 4;
            break;
        case R.id.buttonEqual : {
            mValue2 = getTextValueFloat();
            switch( mCalcMode ) {
            case 1 :
                mValue1 = mValue1 + mValue2; break;
            case 2 :
                mValue1 = mValue1 - mValue2; break;
            case 3 :
                mValue1 = mValue1 * mValue2; break;
            case 4 :
                mValue1 = mValue1 / mValue2; break;
            }

            String strText = Float.toString(mValue1);
            mTextValue.setText(strText);
            mCalcMode = 0;
        }
            break;
        case R.id.buttonSquare2 : {
            mValue1 = getTextValueFloat();
            mValue1 = mValue1 * mValue1;
            String strText = Float.toString(mValue1);
            mTextValue.setText(strText);
            break;
        }
        case R.id.buttonSquare3 : {
            mValue1 = getTextValueFloat();
            mValue1 = mValue1 * mValue1 * mValue1;
            String strText = Float.toString(mValue1);
            mTextValue.setText(strText);
            break;
        }
        case R.id.buttonSqrt : {
            mValue1 = getTextValueFloat();
            mValue1 = (float)Math.sqrt(mValue1);
            String strText = Float.toString(mValue1);
            mTextValue.setText(strText);
            break;
        }
        case R.id.buttonInverseDivide : {
            mValue1 = getTextValueFloat();
            mValue1 = 1.f / mValue1;
            String strText = Float.toString(mValue1);
            mTextValue.setText(strText);
            break;
        }
        }
    }

}
