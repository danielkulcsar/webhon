package com.example.stringex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText mEditInput;
    TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditInput = (EditText)findViewById(R.id.editInput);
        mTextResult = (TextView)findViewById(R.id.textResult);
    }

    public void onButtonStart() {
        String strResult, strLine, strValue;
        String strInput = mEditInput.getText().toString();
        strInput.trim();
        strResult = "[String: " + strInput + " ]";

        strLine = String.format("\n\nLength : %d", strInput.length());
        strResult += strLine;
        
        int nValue = Integer.parseInt(strInput);
        int nResult = 1000 + nValue;
        strLine = String.format("\n\n1000 + Integer.parseInt(?) : %d", nResult);
        strResult += strLine;

        strValue = "ABC" + nValue;
        strLine = String.format("\n\n'ABC' + ? : %s", strValue);
        strResult += strLine;

        strValue = strInput.substring(1);
        strLine = "\n\n.substring(1) : " + strValue;
        strResult += strLine;

        strValue = strInput.substring(1,3);
        strLine = "\n\n.substring(1,3) : " + strValue;
        strResult += strLine;

        strInput = "res/drawable/BACK.png";
        strLine = "\n\n[String : " + strInput + " ]";
        strResult += strLine;
    	
        nValue = strInput.indexOf("png");
        strLine = String.format("\n\n.indexOf('png') : %d", nValue);
        strResult += strLine;

        nValue = strInput.indexOf('r',1);
        strLine = String.format("\n\n.indexOf('a',1) : %d", nValue);
        strResult += strLine;

        nValue = strInput.lastIndexOf('e');
        strLine = String.format("\n\n.lastIndexOf('e') : %d", nValue);
        strResult += strLine;

        strValue = strInput.substring(strInput.lastIndexOf('/')+1);
        strLine = "\n\n.substring(.lastIndexOf('/')+1) : " + strValue;
        strResult += strLine;

        String[] arrStr = strInput.split("/");
        strValue = arrStr[arrStr.length - 1];
        strLine = "\n\n.split('/') of last : " + strValue;
        strResult += strLine;

        strLine = "\n\n.split('/') : ";
        for(int i=0; i < arrStr.length; i++) {
            strLine += arrStr[i];
            if( i < arrStr.length - 1 )
                strLine += ", ";
        }
        strResult += strLine;
        
        strValue = strInput.toUpperCase();
        strLine = "\n\n.toUpperCase() : " + strValue;
        strResult += strLine;

        strValue = strInput.toLowerCase();
        strLine = "\n\n.toLowerCase() : " + strValue;
        strResult += strLine;
        
        char cValue = strInput.charAt(4);
        strLine = "\n\n.charAt(4) : " + cValue;
        strResult += strLine;

        nValue = strInput.codePointAt(4);
        strLine = "\n\n.codePointAt(4) : " + nValue;
        strResult += strLine;

        cValue = 97;
        strValue = String.format("%c", cValue);
        strLine = "\n\nchar(97) to String : " + strValue;
        strResult += strLine;

        mTextResult.setText(strResult);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnStart :
            onButtonStart();
            break;
        }
    }

}
