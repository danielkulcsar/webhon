package jp.co.se.android.recipe.chapter13;

import jp.co.se.android.recipe.chapter13.R;
import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class Ch1303 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1303_main);

        Switch installAppSwitch = (Switch) findViewById(R.id.InstallAppSwitch);
        installAppSwitch
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                            boolean isChecked) {
                        if (isChecked) {
                            // 서비스 시작
                            startInstallAppService();
                        } else {
                            // 서비스 종료
                            stopInstallAppService();
                        }
                    }
                });

    }

    @Override
    protected void onDestroy() {
        stopInstallAppService();
        super.onDestroy();
    }

    /**
     *  설치를 검지하는 서비스를 시작
     */
    private void startInstallAppService() {
        Intent service = new Intent(Ch1303.this, DetectInstallAppService.class);
        startService(service);
    }

    /**
     * 설치를 검지하는 서비스를 종료
     */
    private void stopInstallAppService() {
        Intent service = new Intent(Ch1303.this, DetectInstallAppService.class);
        stopService(service);
    }

    /**
     * 설치・언인스톨을 검지하는서비스,액티비티라면 포어그라운 밖에 탐지할 수 없기 때문에 서비스를 이용한다.
     */
    public static class DetectInstallAppService extends Service {
        private InstallAppReceiver mInstallAppReciever = null;

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            //인스톨, 인스톨을 검지하는 브로드 캐스드를 등록
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addDataScheme("package");
            intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
            intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
            mInstallAppReciever = new InstallAppReceiver();
            registerReceiver(mInstallAppReciever, intentFilter);
        }

        @Override
        public void onDestroy() {
            // 인스톨, 인스톨을 검지하는 브로드 캐스드를 파기
            if (mInstallAppReciever != null) {
                unregisterReceiver(mInstallAppReciever);
            }
            super.onDestroy();
        }

        /**
         * 인스톨, 언인스톨을 검지하는 방송 수신기.
         */
        private class InstallAppReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.indexOf("PACKAGE_ADDED") != -1) {
                    // 설치 검지
                    Toast.makeText(context,
                            getString(R.string.label_detect_installapp),
                            Toast.LENGTH_SHORT).show();
                }
                if (action.indexOf("PACKAGE_REMOVED") != -1) {
                    // 언인스톨 검지
                    Toast.makeText(context,
                            getString(R.string.label_detect_uninstallapp),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
