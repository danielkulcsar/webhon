package jp.co.se.android.recipe.chapter11;

import jp.co.se.android.recipe.utils.NfcUtil;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class Ch1112 extends Activity {
    protected NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1112_main);

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

            // IDm을 얻는다
            byte[] idm = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);

            // IDｍ을 문자열로 변환하여 표시
            TextView tvRead = (TextView) findViewById(R.id.Read);
            tvRead.setText(NfcUtil.bytesToHex(idm));
        }
    }
}