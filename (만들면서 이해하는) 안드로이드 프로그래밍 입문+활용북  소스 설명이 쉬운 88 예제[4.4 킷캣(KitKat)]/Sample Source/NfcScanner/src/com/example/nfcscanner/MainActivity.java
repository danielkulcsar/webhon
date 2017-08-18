package com.example.nfcscanner;

import java.util.*;

import android.nfc.*;
import android.nfc.tech.*;
import android.os.*;
import android.provider.*;
import android.app.*;
import android.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextView;
    NfcAdapter mNfcAdapter;
    PendingIntent mPendingIntent;
    IntentFilter[] mIntentFilters;
    String[][] mNFCTechLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.textMessage);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if( mNfcAdapter == null ) {
            mTextView.setText("This phone is not NFC enable.");
            return;
        }

        mTextView.setText("Scan a NFC tag");

        Intent intent = new Intent(this, getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter iFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            iFilter.addDataType("*/*");
            mIntentFilters = new IntentFilter[] { iFilter };
        } catch (Exception e) {
            mTextView.setText("Make IntentFilter error");
        }
        mNFCTechLists = new String[][] { new String[] { NfcF.class.getName() } };
    }

    public void onResume() {
        super.onResume();
        if( mNfcAdapter != null )
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFilters, mNFCTechLists);

        if( NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction()) )
            onNewIntent(getIntent());
    }

    public void onPause() {
        super.onPause();
        if( mNfcAdapter != null )
            mNfcAdapter.disableForegroundDispatch(this);		
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonSetup : {
            if (mNfcAdapter == null) return;
            Intent intent = new Intent( Settings.ACTION_NFCSHARING_SETTINGS );
            startActivity(intent);
            break;
        }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        String action = intent.getAction();
        String tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG).toString();
        String strMsg = action + "\n\n" + tag;
        mTextView.setText(strMsg);

        Parcelable[] messages = intent.getParcelableArrayExtra(
        	    NfcAdapter.EXTRA_NDEF_MESSAGES);
        if(messages == null) return;

        for(int i=0; i < messages.length; i++)
            showMsg((NdefMessage)messages[i]);
    }
    
    public void showMsg(NdefMessage mMessage) {
        String strMsg = "", strRec="";
        NdefRecord[] recs = mMessage.getRecords();
        for (int i = 0; i < recs.length; i++) {
            NdefRecord record = recs[i];
            byte[] payload = record.getPayload();
            if( Arrays.equals(record.getType(), NdefRecord.RTD_TEXT) ) {
                strRec = byteDecoding(payload);
                strRec = "Text: " + strRec;
            }
            else if( Arrays.equals(record.getType(), NdefRecord.RTD_URI) ) {
                strRec = new String(payload, 0, payload.length);
                strRec = "URI: " + strRec;
            }
            strMsg += ("\n\nNdefRecord[" + i + "]:\n" + strRec);
        }

        mTextView.append(strMsg);
    }

    public String byteDecoding(byte[] buf) {
        String strText="";
        String textEncoding = ((buf[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
        int langCodeLen = buf[0] & 0077;

        try {
            strText = new String(buf, langCodeLen + 1, 
                    buf.length - langCodeLen - 1, textEncoding);
        } catch(Exception e) {
            Log.d("tag1", e.toString());
        }
        return strText;
    }

}
