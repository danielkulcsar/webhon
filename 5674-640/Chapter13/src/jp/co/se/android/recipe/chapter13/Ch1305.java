package jp.co.se.android.recipe.chapter13;

import jp.co.se.android.recipe.chapter13.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Ch1305 extends Activity {
    private UnlockReciever mUnlockReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1305_main);
        final TextView tvDescription = (TextView) findViewById(R.id.description);
        final Switch unlockSwitch = (Switch) findViewById(R.id.unlockSwitch);

        // 스위치 ON・OFF
        unlockSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                if (isChecked) {
                	//잠금 해제를 검지하는 브로드캐스트를 등록
                    IntentFilter intentFilter = new IntentFilter(
                            Intent.ACTION_USER_PRESENT);
                    mUnlockReciever = new UnlockReciever();
                    registerReceiver(mUnlockReciever, intentFilter);

                    tvDescription
                            .setText(getString(R.string.label_detect_unlock));
                    tvDescription.setVisibility(View.VISIBLE);
                } else {
                	//잠금 해제를 검지하는 브로드캐스트를 파기
                    if (mUnlockReciever != null) {
                        unregisterReceiver(mUnlockReciever);
                        mUnlockReciever = null;
                    }
                    tvDescription.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mUnlockReciever != null) {
            unregisterReceiver(mUnlockReciever);
            mUnlockReciever = null;
        }
        super.onDestroy();
    }

    /**
    *디바이스의 잠금 상태를 검지하는 브로드캐스트.
     */
    public static class UnlockReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                Toast.makeText(context,
                        context.getString(R.string.label_detect_unlocked),
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
