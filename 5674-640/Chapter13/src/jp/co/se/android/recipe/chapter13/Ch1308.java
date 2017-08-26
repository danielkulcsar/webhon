package jp.co.se.android.recipe.chapter13;

import jp.co.se.android.recipe.chapter13.R;
import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class Ch1308 extends Activity {
    private Handler mHandler = new Handler();
    WifiManager mWifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1308_main);
        final Switch wifiSwitch = (Switch) findViewById(R.id.wifiSwitch);

        mWifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        wifiSwitch.setChecked(mWifiManager.isWifiEnabled());
        // 스위치 ON・OFF
        wifiSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                // Wi-Fi를 ON・OFF으로 
                mWifiManager.setWifiEnabled(isChecked);
            }
        });

        // Wi-Fi의 상태를 감시하는 핸들러를 포스트
        mHandler.post(mRnCheckWifiState);
    }

    /**
     * 300ms 간격으로 WiFi의 상태를 감시하는 스레드
     */
    Runnable mRnCheckWifiState = new Runnable() {
        @Override
        public void run() {
            TextView tvDescription = (TextView) findViewById(R.id.description);
            switch (mWifiManager.getWifiState()) {
            case WifiManager.WIFI_STATE_DISABLING:
                tvDescription
                        .setText(getString(
                                R.string.label_detect_wifistate,
                                getString(R.string.label_detect_wifistate_disable_now)));
                break;
            case WifiManager.WIFI_STATE_DISABLED:
                tvDescription.setText(getString(
                        R.string.label_detect_wifistate,
                        getString(R.string.label_detect_wifistate_disable)));
                break;
            case WifiManager.WIFI_STATE_ENABLING:
                tvDescription.setText(getString(
                        R.string.label_detect_wifistate,
                        getString(R.string.label_detect_wifistate_enable_now)));
                break;
            case WifiManager.WIFI_STATE_ENABLED:
                tvDescription.setText(getString(
                        R.string.label_detect_wifistate,
                        getString(R.string.label_detect_wifistate_enable)));
                break;
            case WifiManager.WIFI_STATE_UNKNOWN:
                tvDescription.setText(getString(
                        R.string.label_detect_wifistate,
                        getString(R.string.label_detect_wifistate_unknown)));
                break;
            }
            mHandler.postDelayed(mRnCheckWifiState, 300);
        }
    };

    @Override
    protected void onDestroy() {
        // Wi-Fi를 감시하는 핸들러를 파기
        mHandler.removeCallbacks(mRnCheckWifiState);
        super.onDestroy();
    }
}
