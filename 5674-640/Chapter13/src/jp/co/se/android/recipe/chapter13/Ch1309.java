package jp.co.se.android.recipe.chapter13;

import jp.co.se.android.recipe.chapter13.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class Ch1309 extends Activity {
    private ScreenReceiver mScreenReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1309_main);
        final TextView tvDescription = (TextView) findViewById(R.id.description);
        final Switch unlockSwitch = (Switch) findViewById(R.id.screenSwitch);

        // 스위치 ON・OFF
        unlockSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                if (isChecked) {
                    // 스크린의 점등을 검지하는 브로드캐스트를 등록 
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(Intent.ACTION_SCREEN_ON);
                    intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
                    mScreenReciever = new ScreenReceiver();
                    registerReceiver(mScreenReciever, intentFilter);
                    tvDescription
                            .setText(getString(R.string.label_detect_screen));
                    tvDescription.setVisibility(View.VISIBLE);
                } else {
                    // 스크린의 점등을 검지하는 브로드캐스트를 파기
                    if (mScreenReciever != null) {
                        unregisterReceiver(mScreenReciever);
                        mScreenReciever = null;
                    }
                    tvDescription.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mScreenReciever != null) {
            unregisterReceiver(mScreenReciever);
            mScreenReciever = null;
        }
        super.onDestroy();
    }

    /**
     * 스크린의 점등을 검지하는 브로드캐스트.
     */
    public static class ScreenReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Vibrator vib = (Vibrator) context
                    .getSystemService(VIBRATOR_SERVICE);
            // 스크린 ON
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                // 짧게 진동을 울린다
                vib.vibrate(300);
            // 스크린 OFF
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                // 긴 진동을 울린다
                vib.vibrate(1000);
            }
        }
    }
}
