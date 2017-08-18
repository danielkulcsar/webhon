package com.example.nfcsender;

import java.nio.charset.*;
import java.util.*;

import android.app.*;
import android.nfc.*;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.os.*;
import android.widget.*;

public class MainActivity extends Activity 
        implements CreateNdefMessageCallback
        , OnNdefPushCompleteCallback {
    NfcAdapter mNfcAdapter = null;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.textMessage);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if( mNfcAdapter != null )
            mTextView.setText("Tap to another NFC device. And touch screen");
        else
            mTextView.setText("This phone is not NFC enable.");

        mNfcAdapter.setNdefPushMessageCallback(this, this);
        mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        NdefMessage message = new NdefMessage( new NdefRecord[] {
        	    createTextRecord("Text sample record-1", Locale.ENGLISH),
        	    createTextRecord("한국어 sample record-2", Locale.KOREAN),
        	    createUriRecord("www.google.com"),
        	    createUriRecord("cafe.naver.com/tizenity")
        });
        return message;
    }

    public NdefRecord createTextRecord(String text, Locale locale) {
        byte[] data = byteEncoding(text, locale);
        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }

    public byte[] byteEncoding(String text, Locale locale) {
        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));
        Charset utfEncoding = Charset.forName("UTF-8");
        byte[] textBytes = text.getBytes(utfEncoding);

        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte)langBytes.length;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        return data;
    }

    public NdefRecord createUriRecord(String url) {
        byte[] uriField = url.getBytes(Charset.forName("US-ASCII"));
        byte[] payload = new byte[uriField.length + 1];
        payload[0] = 0x01;
        System.arraycopy(uriField, 0, payload, 1, uriField.length);
        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_URI, new byte[0], payload);
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {
        mHandler.obtainMessage(1).sendToTarget();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 1:
                mTextView.setText("NDEF message sending completed");
                break;
            }
        }
    };
}
