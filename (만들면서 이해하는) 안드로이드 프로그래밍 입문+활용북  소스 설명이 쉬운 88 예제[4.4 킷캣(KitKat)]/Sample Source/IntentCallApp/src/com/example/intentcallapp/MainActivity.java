package com.example.intentcallapp;

import java.io.*;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent intent;

        switch(v.getId() ) {
        case R.id.btnBrowser :
            intent = new Intent(Intent.ACTION_VIEW, 
            	    Uri.parse("http://www.google.com"));
            startActivity(intent);
            break;
        case R.id.btnPhoneCall :
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1234-5678"));
            startActivity(intent);
            break;
        case R.id.btnImageViewer : {
            String strPath = "/mnt/sdcard" + "/puzzle02.jpg";
            Uri uri = Uri.fromFile(new File(strPath));
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "image/jpeg");
            startActivity(intent);
            break;
        }
        case R.id.btnSendSms :
            intent = new Intent(Intent.ACTION_VIEW); 
            intent.putExtra("address", "01012345678");
            intent.putExtra("sms_body", "SMS Test");
            intent.setType("vnd.android-dir/mms-sms");
            startActivity(intent); 
            break;
        case R.id.btnOtherApp :
            intent = new Intent(Intent.ACTION_MAIN);
            ComponentName comp = new ComponentName("com.example.buttonex",
            	    "com.example.buttonex.MainActivity");
            intent.setComponent(comp);
            startActivity(intent);
            break;
        }
    }

}
