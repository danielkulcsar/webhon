package com.example.checkappinstalled;

import java.util.*;

import android.app.*;
import android.content.pm.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.textResult);
    }

    public void onClick(View v) {
        boolean result = false;

        switch( v.getId() ) {
        case R.id.btnCheckKakaoTalk :
            result = checkApplication("com.kakao.talk");
            if( result )
                mTextView.setText("KakaoTalk is Installed");
            else
                mTextView.setText("KakaoTalk is Uninstalled");
            break;
        case R.id.btnCheckSamsungApps :
            result = checkApplication("com.sec.android.app.samsungapps");
            if( result )
                mTextView.setText("SamsungApps is Installed");
            else
                mTextView.setText("SamsungApps is Uninstalled");
            break;
        case R.id.btnCheckButtonEx :
            result = checkApplication("com.example.buttonex");
            if( result )
                mTextView.setText("ButtonEx is Installed");
            else
                mTextView.setText("ButtonEx is Uninstalled");
            break;
        }
    }

    private boolean checkApplication(String packageName) {
        List<ApplicationInfo> appInfos = getPackageManager().getInstalledApplications(
        	    PackageManager.GET_ACTIVITIES);
        for (int i = 0; i < appInfos.size(); i++) {
            ApplicationInfo aInfo = appInfos.get(i);
            if (aInfo.packageName.contains(packageName))
                return true;
        }
        return false;
    }

}
