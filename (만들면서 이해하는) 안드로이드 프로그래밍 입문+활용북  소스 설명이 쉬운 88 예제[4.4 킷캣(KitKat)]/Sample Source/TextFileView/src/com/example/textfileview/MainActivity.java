package com.example.textfileview;

import java.io.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText mEditFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditFile = (EditText)this.findViewById(R.id.editFile);

        File file = getFileStreamPath("text.txt");
        if( file.isFile() == false ) {
            String strBuf = ReadTextAssets("text.txt");
            WriteTextFile("text.txt", strBuf);
        }
    }

    public String ReadTextAssets(String strFileName) {
        String text = null;
        try {
            InputStream is = getAssets().open(strFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            text = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text;
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonReadAssets : {
            String strBuf = ReadTextAssets("text.txt");
            mEditFile.setText(strBuf);
            break;
        }
        case R.id.buttonWriteFile : {
            String strBuf = mEditFile.getText().toString();
            if( WriteTextFile("text.txt", strBuf) )
                Toast.makeText(this, "File Saved", 0).show();
            break;
        }
        case R.id.buttonReadFile : {
            String strBuf = ReadTextFile("text.txt");
            mEditFile.setText(strBuf);
            break;
        }
        }
    }

    public boolean WriteTextFile(String strFileName, String strBuf) {
        try {
            File file = getFileStreamPath(strFileName);
            FileOutputStream fos = new FileOutputStream(file);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(strBuf);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public String ReadTextFile(String strFileName) {
        String text = null;
        try {
            File file = getFileStreamPath(strFileName);
            FileInputStream fis = new FileInputStream(file);
            Reader in = new InputStreamReader(fis);
            int size = fis.available();
            char[] buffer = new char[size];
            in.read(buffer);
            in.close();

            text = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
 
        return text;
    }

}
