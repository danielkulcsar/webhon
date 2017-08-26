package jp.co.se.android.recipe.chapter13;

import jp.co.se.android.recipe.chapter13.R;
import jp.co.se.android.recipe.common.MainActivity;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class Ch1302 extends Activity {
    /** Notification의 식별용 ID  */
    private static final int ID_NOTIFICATION_SAMPLE_ACTIVITY = 0x00;

    /** Notification의 클릭 인덴트 */
    private static final String ACTION_CLICK_DIALER = "action.click.dialer";
    private static final String ACTION_CLICK_SMS = "action.click.sms";

    /** Notification의 타입 */
    private enum NotificationType {
        BigText, BigPicture, Inbox, Button
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1302_main);

        // 큰 텍스트의 Notification을 표시 비표시
        final Switch bigTextSwitch = (Switch) findViewById(R.id.bigTextSwitch);
        final Switch bigPictureSwitch = (Switch) findViewById(R.id.bigPictureSwitch);
        final Switch inboxSwitch = (Switch) findViewById(R.id.inboxSwitch);
        final Switch buttonSwitch = (Switch) findViewById(R.id.buttonSwitch);

        bigTextSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                showNotification(isChecked, NotificationType.BigText);
            }
        });

        // 큰 이미지의 Notification을 표시 비표시
        bigPictureSwitch
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView,
                            boolean isChecked) {
                        showNotification(isChecked, NotificationType.BigPicture);
                    }
                });

        // 인덱스의 Notification을 표시 비표시
        inboxSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                showNotification(isChecked, NotificationType.Inbox);
            }
        });

        // 버튼의 Notification을 표시 비표시
        buttonSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                showNotification(isChecked, NotificationType.Button);
            }
        });

    }

    /**
     * 스테이터스바를 표시
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(boolean isShow, NotificationType type) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (isShow && type != null) {
            // 스테이터스 바를 통지
            // 브라우저를 기동하는 PendingIntent를 생성
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this,
                    ID_NOTIFICATION_SAMPLE_ACTIVITY, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            // Notification 설정
            Notification.Builder nb = new Notification.Builder(this);
            nb.setWhen(System.currentTimeMillis());
            nb.setContentIntent(contentIntent);
            nb.setSmallIcon(android.R.drawable.stat_notify_chat);
            nb.setTicker(getString(R.string.label_status_ticker));
            nb.setContentTitle(getString(R.string.label_status_style));
            nb.setDefaults(Notification.DEFAULT_SOUND
                    | Notification.DEFAULT_VIBRATE);

            Notification notification = null;
            if (type.equals(NotificationType.BigText)) {
                // 큰 텍스트의 스테이터스 바를 생성
                Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle(
                        nb);
                bigTextStyle.bigText(getString(R.string.label_status_main));
                bigTextStyle
                        .setSummaryText(getString(R.string.label_status_summary));
                notification = bigTextStyle.build();
            } else if (type.equals(NotificationType.BigPicture)) {
                // 큰 이미지의 스테이터스바를 생성
                Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(
                        nb);
                bigPictureStyle.bigPicture(BitmapFactory.decodeResource(
                        getResources(), R.drawable.ch1302_notification_img));
                notification = bigPictureStyle.build();
            } else if (type.equals(NotificationType.Inbox)) {
                // 인덱스의 스테이터스 바를 생성
                Notification.InboxStyle inboxStyle = new Notification.InboxStyle(
                        nb);
                inboxStyle.addLine(getString(R.string.label_status_showline1));
                inboxStyle.addLine(getString(R.string.label_status_showline2));
                inboxStyle.addLine(getString(R.string.label_status_showline3));
                inboxStyle.addLine(getString(R.string.label_status_showline4));
                inboxStyle
                        .setSummaryText(getString(R.string.label_status_showlinemore));
                notification = inboxStyle.build();
            } else if (type.equals(NotificationType.Button)) {
                // 버튼의 스테이터스바를 생성
                Intent dialClick = new Intent(this,
                        NotificationButtonClickListener.class);
                dialClick.setAction(ACTION_CLICK_DIALER);
                PendingIntent dialIntent = PendingIntent.getBroadcast(this, 0,
                        dialClick, 0);
                Intent smsClick = new Intent(this,
                        NotificationButtonClickListener.class);
                smsClick.setAction(ACTION_CLICK_SMS);
                PendingIntent smsIntent = PendingIntent.getBroadcast(this, 0,
                        smsClick, 0);
                nb.addAction(android.R.drawable.ic_dialog_dialer,
                        getString(R.string.label_status_button_call),
                        dialIntent);
                nb.addAction(android.R.drawable.ic_dialog_email,
                        getString(R.string.label_status_button_sms), smsIntent);
                notification = nb.build();
            }

            // Notification을 통지
            if (notification != null) {
                notificationManager.notify(ID_NOTIFICATION_SAMPLE_ACTIVITY,
                        notification);
            }
        } else {
            // 스테이터스 바를 삭제
            // Notification을 취소
            notificationManager.cancel(ID_NOTIFICATION_SAMPLE_ACTIVITY);
        }
    }

    public static class NotificationButtonClickListener extends
            BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_CLICK_DIALER)) {
                // 다이얼러를 동작
                Intent launchDialer = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:090-1234-5678"));
                launchDialer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(launchDialer);
            } else if (action.equals(ACTION_CLICK_SMS)) {
                // 메일 작성 화면을 기동
                Intent launchSms = new Intent(Intent.ACTION_SENDTO);
                launchSms.setData(Uri.parse("smsto:090-1234-5678"));
                launchSms.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(launchSms);
            }
        }
    }
}
