package com.example.getaccounts;

import android.accounts.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText1 = (TextView)findViewById(R.id.textView1);
    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnGetAccounts :
            getAccounts();
            break;
        }
    }

    public void getAccounts() {
        mText1.setText("getAccounts()\n");
        AccountManager AccountMgr = AccountManager.get(this);
        Account[] accts = AccountMgr.getAccounts();
        final int count = accts.length;

        for(int i=0; i < count; i++) {
            Account acct = accts[i];
            mText1.append("\nAccount – Name : " + acct.name
            		+ " / Type : " + acct.type);
        }
    }

}
