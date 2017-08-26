package jp.co.se.android.recipe.chapter13;

import jp.co.se.android.recipe.chapter13.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class Ch1301 extends Activity {
    /** Notification의 식별용 ID */
    int ID_NOTIFICATION_SAMPLE_ACTIVITY = 0x00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1301_main);

        Switch notificationSwitch = (Switch) findViewById(R.id.notificationSwitch);
        notificationSwitch
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                            boolean isChecked) {
                        // 스위치 전환에 연동하고 상태 바를 ON・OFF
                        showNotification(isChecked);
                    }
                });

    }

    /**
     * 스테이터스바를 표시
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(boolean isShow) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (isShow) {
            // 스테이터스바를 통지
            // 브라우저를 동작하는 PendingIntent를 생성
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.url_shoeisha)));
            PendingIntent contentIntent = PendingIntent.getActivity(this,
                    ID_NOTIFICATION_SAMPLE_ACTIVITY, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            // Notification 설정
            Notification.Builder nb = new Notification.Builder(this);
            // 통지 이벤트의 타임 스탭
            nb.setWhen(System.currentTimeMillis());
            // 컨텐츠를 설정
            nb.setContentIntent(contentIntent);
            // 아이콘을 설정
            nb.setSmallIcon(android.R.drawable.stat_notify_chat);
            // 통지 시에 표시하는 문자열
            nb.setTicker(getString(R.string.label_status_ticker));
            // 스테이터스 바에 표시하는 타이틀
            nb.setContentTitle(getString(R.string.label_launch_browser));
            // 음과 바이브, 라이트
            nb.setDefaults(Notification.DEFAULT_SOUND
                    | Notification.DEFAULT_VIBRATE
                    | Notification.DEFAULT_LIGHTS);
            // 탭하면 자동으로 표시를 지운다
            nb.setAutoCancel(true);
            Notification notification = nb.build();

            // Notification를 통지
            notificationManager.notify(ID_NOTIFICATION_SAMPLE_ACTIVITY,
                    notification);
        } else {
            // 스테이터스 바를 삭제
            // Notification을  취소
            notificationManager.cancel(ID_NOTIFICATION_SAMPLE_ACTIVITY);
        }
    }
}
