package jp.co.se.android.recipe.chapter11;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public class Ch1113 extends Activity {
    protected NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1113_main);

        // NFC를 다루기 위한 인스턴스를 구하기
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // NFC가 탑재되어 있는지 체크
        if (mNfcAdapter != null) {
        	 // NFC 기능이 유효한지 체크
            if (!mNfcAdapter.isEnabled()) {
                // NFC 기능이 유효한 경우는 사용자에게 통지
                Toast.makeText(getApplicationContext(),
                        getString(R.string.error_nfc_disable),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
        	 // NFC 비탑재의 경우는 사용자에게 통지
            Toast.makeText(getApplicationContext(),
                    getString(R.string.error_nfc_nosupport), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
        	// 동작 중인 Activity가 우선적으로NFC를 받도록 설정
            Intent intent = new Intent(this, this.getClass())
                    .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    getApplicationContext(), 0, intent, 0);
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, null,
                    null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) {
        	//Activity가 비표시가 될 때에 우선적으로 NFC를 받는 설정을 해제
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            // 태그를 구한다
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            if (tag != null) {
                // 입력한 텍스트를 구한다
                EditText etWrite = (EditText) findViewById(R.id.Write);
                String ndefMsg = etWrite.getText().toString();
                if (!TextUtils.isEmpty(ndefMsg)) {

                    // NdefRecord 작성
                    NdefRecord[] ndefRecords = new NdefRecord[] { NdefRecord
                            .createUri(ndefMsg), };
                    // NdefMessage 작성
                    NdefMessage msg = new NdefMessage(ndefRecords);

                    // NFC 태그에 쓰기
                    write(tag, msg);
                }
            }
        }
    }

    /**
     * NFC 태그에 쓰기
     * 
     * @param tag
     * @param msg
     */
    private void write(Tag tag, NdefMessage msg) {
        try {
            List<String> techList = Arrays.asList(tag.getTechList());
            // 쓰기를 하는 태그에 NDEF 데이터가 저장되어 있는지 확인
            if (techList.contains(Ndef.class.getName())) {
                // NDEF가 포함되어 있는 경우
                Ndef ndef = Ndef.get(tag);
                try {
                    // 그대로 NDEF 데이터 상에 NDEF 메세지를 쓰기
                    ndef.connect();
                    ndef.writeNdefMessage(msg);
                } catch (IOException e) {
                    throw new RuntimeException(
                            getString(R.string.error_connect), e);
                } catch (FormatException e) {
                    throw new RuntimeException(
                            getString(R.string.error_format), e);
                } finally {
                    try {
                        ndef.close();
                    } catch (IOException e) {
                    }
                }
            } else if (techList.contains(NdefFormatable.class.getName())) {
                // NDEFFormattable가 포함되어 있는 경우
                NdefFormatable ndeffmt = NdefFormatable.get(tag);
                try {
                    // 그대로 NDEF에 포맷하고 NDEF 메세지를 쓰기
                    ndeffmt.connect();
                    ndeffmt.format(msg);
                } catch (IOException e) {
                    throw new RuntimeException(
                            getString(R.string.error_connect), e);
                } catch (FormatException e) {
                    throw new RuntimeException(
                            getString(R.string.error_format), e);
                } finally {
                    try {
                        ndeffmt.close();
                    } catch (IOException e) {
                    }
                }
            }
            // 쓰기 성공 토스트를 표시
            Toast.makeText(this, getString(R.string.write_success),
                    Toast.LENGTH_SHORT).show();
        } catch (RuntimeException e) {
            // 쓰기 실패 토스트를 표시
            Toast.makeText(this, getString(R.string.write_failure),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
