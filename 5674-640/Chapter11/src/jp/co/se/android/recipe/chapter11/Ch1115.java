package jp.co.se.android.recipe.chapter11;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class Ch1115 extends Activity {
    private AirPlaneModeReciever mAirPlaneModeReciever;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1115_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 기내 모드를 검출하는 브로드캐스트 수신기를 해제
        if (mAirPlaneModeReciever != null) {
            unregisterReceiver(mAirPlaneModeReciever);
            mAirPlaneModeReciever = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
       // 기내 모드를 검출하는 브로드캐스트 수신기를 등록
        if (mAirPlaneModeReciever == null) {
            mAirPlaneModeReciever = new AirPlaneModeReciever();
            registerReceiver(mAirPlaneModeReciever, new IntentFilter(
                    Intent.ACTION_AIRPLANE_MODE_CHANGED));
        }
    }

    /**
     * 기내 모드를 검출하는 브로드캐스트 수신기.
     * 
     * @author Re:Kayo-System / m.iwasaki
     */
    private class AirPlaneModeReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 기내 모드 상태를 구하고 화면에 표시
            boolean state = intent.getBooleanExtra("state", false);
            TextView tvDescription = (TextView) findViewById(R.id.description);
            if (state) {
                tvDescription
                        .setText(getString(R.string.ch1115_label_airplanemode_enable));
            } else {
                tvDescription
                        .setText(getString(R.string.ch1115_label_airplanemode_disable));
            }
        }
    }

}
