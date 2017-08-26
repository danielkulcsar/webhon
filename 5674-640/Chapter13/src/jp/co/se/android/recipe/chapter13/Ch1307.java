package jp.co.se.android.recipe.chapter13;

import jp.co.se.android.recipe.chapter13.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Ch1307 extends Activity {
    private BatteryChangedReceiver mBatteryChangedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1307_main);
        // 배터리 상태를 통지 하는 브로드캐스트 리시버
        IntentFilter intentFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        mBatteryChangedReceiver = new BatteryChangedReceiver(this);
        registerReceiver(mBatteryChangedReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        if (mBatteryChangedReceiver != null) {
            unregisterReceiver(mBatteryChangedReceiver);
            mBatteryChangedReceiver = null;
        }
        super.onDestroy();
    }

    public static class BatteryChangedReceiver extends BroadcastReceiver {
        Activity mActivity;

        public BatteryChangedReceiver(Activity activity) {
            this.mActivity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            // 잔량
            TextView tvBatteryLevel = (TextView) mActivity
                    .findViewById(R.id.BatteryLevel);
            int level = intent.getIntExtra("level", 0);
            int scale = intent.getIntExtra("scale", 0);
            tvBatteryLevel.setText(level + " / " + scale + "(%)");

            // 충전 상태
            TextView tvBatteryCharge = (TextView) mActivity
                    .findViewById(R.id.BatteryCharge);
            switch (intent.getIntExtra("status", 0)) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                // 충전 중
                tvBatteryCharge.setText(context
                        .getString(R.string.label_detect_charging));
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                // 충전 중
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                tvBatteryCharge.setText(context
                        .getString(R.string.label_detect_discharging));
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                // 가득참
                tvBatteryCharge.setText(context
                        .getString(R.string.label_detect_fullbattery));
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                // 불명
                Log.d("BatteryChange",
                        context.getString(R.string.label_detect_status_unknown));
                break;
            }

            // 품질
            TextView tvBatteryQuality = (TextView) mActivity
                    .findViewById(R.id.BatteryQuality);
            switch (intent.getIntExtra("health", 0)) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                // 양호
                tvBatteryQuality.setText(context
                        .getString(R.string.label_detect_good));
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                // 발열
                tvBatteryQuality.setText(context
                        .getString(R.string.label_detect_overheart));
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                // 과전압
                tvBatteryQuality.setText(context
                        .getString(R.string.label_detect_overvoltage));
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                //고장
                tvBatteryQuality.setText(context
                        .getString(R.string.label_detect_dead));
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                // 특정 실패
                tvBatteryQuality.setText(context
                        .getString(R.string.label_detect_unspecifiedfailure));
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                // 저온
                tvBatteryQuality.setText(context
                        .getString(R.string.label_detect_cold));
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                // 불명
                tvBatteryQuality.setText(context
                        .getString(R.string.label_detect_unknown));
                break;
            }

            // 온도&전압
            TextView tvBatteryTemperature = (TextView) mActivity
                    .findViewById(R.id.BatteryTemperature);
            int temperatue = intent.getIntExtra("temperature", 0) / 10;
            float voltage = intent.getIntExtra("voltage", 0) / 1000;
            tvBatteryTemperature
                    .setText(context.getString(
                            R.string.label_detect_temperature, temperatue)
                            + " / "
                            + context.getString(R.string.label_detect_voltage,
                                    voltage));

            // 충전 방법
            TextView tvBatteryConnect = (TextView) mActivity
                    .findViewById(R.id.BatteryConnect);
            switch (intent.getIntExtra("plugged", 0)) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                // AC 전류
                tvBatteryConnect.setText(context
                        .getString(R.string.label_detect_ac));
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                // USB 전류
                tvBatteryConnect.setText(context
                        .getString(R.string.label_detect_usb));
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                // wireless 접속
                tvBatteryConnect.setText(context
                        .getString(R.string.label_detect_wireless));
                break;
            default:
                tvBatteryConnect.setText(context
                        .getString(R.string.label_detect_disconnect));
                break;

            }
        }
    };
}
