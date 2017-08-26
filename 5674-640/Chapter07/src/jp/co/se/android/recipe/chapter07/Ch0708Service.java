package jp.co.se.android.recipe.chapter07;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

@SuppressLint("NewApi")
public class Ch0708Service extends Service {
    public static final String TAG = "Chapter07";
    private MediaPlayer mMediaPlayer;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if ("play".equals(action)) {
                if (mMediaPlayer == null || !mMediaPlayer.isPlaying()) {
                    play();
                }
            } else if ("pause".equals(action)) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    pause();
                }
            } else if ("stop".equals(action)) {
                if (mMediaPlayer != null) {
                    stop();
                }
            } else if ("next".equals(action)) {
                // 실행 안 함
            } else if ("back".equals(action)) {
                // 실행 안 함
            } else if ("playpause".equals(action)) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    pause();
                } else {
                    play();
                }
            }
        }

        return START_STICKY;
    }

    private void play() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            String fileName = "android.resource://" + getPackageName() + "/"
                    + R.raw.bgm_healing02;
            try {
                mMediaPlayer.setDataSource(this, Uri.parse(fileName));
                mMediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mMediaPlayer.start();

        // Notification 등록
        startForeground(1, generateNotification());
    }

    private void pause() {
        mMediaPlayer.pause();
    }

    private void stop() {
        mMediaPlayer.stop();
        mMediaPlayer = null;

        // Notification 해제
        stopForeground(true);
    }

    private Notification generateNotification() {
        // 통지 영역 탭 시 PendingIntent를 생성
        Intent actionIntent = new Intent(getApplicationContext(), Ch0708.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),
                0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 레이아웃 RemoveView를 생성
        RemoteViews mNotificationView = new RemoteViews(getPackageName(),
                R.layout.ch0708_statusbar);

        // Notification 생성
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext());
        builder.setSmallIcon(R.drawable.ic_stat_media);
        // 독자적 레이아웃을 Notificaiton으로 설정
        builder.setContent(mNotificationView);
        // true에서 항상 통지 영역에 표시
        builder.setOngoing(true);
        // 통지 영역에 초기 표시 시의 메시지를 설정
        builder.setTicker("Sample Title 재생");
        builder.setContentIntent(pi);

        // 상태 바의 레이아웃에 설정되어 있는 이미지 아이콘에 아이콘을 설정
        mNotificationView.setImageViewResource(R.id.imageicon,
                R.drawable.ic_launcher);

        // 상태 바의 레이아웃에 설정되어 있는 타이틀명에 제목을 설정
        mNotificationView.setTextViewText(R.id.textTitle, "Sample Title");
        // 상태 바의 레이아웃에 설정되어 있는 작가 이름에 아티스트를 설정
        mNotificationView.setTextViewText(R.id.textArtist, "Sample Artist");

        // [이미지 아이콘] 버튼을 터치 시 호출되는 Intent를 설정
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, Ch0708.class), Intent.FLAG_ACTIVITY_NEW_TASK);
        mNotificationView
                .setOnClickPendingIntent(R.id.imageicon, contentIntent);

        // [재생]•[정지] 버튼을 터치 시 호출되는 Intent를 설정
        mNotificationView.setOnClickPendingIntent(R.id.btnPlay,
                createPendingIntent("playpause"));

        // [다음] 버튼을 터치 시 호출되는 Intent를 설정
        mNotificationView.setOnClickPendingIntent(R.id.btnNext,
                createPendingIntent("next"));

        return builder.build();
    }

    private PendingIntent createPendingIntent(String action) {
        // Action에 따른 Service의 Intent을 유지하는 PendingIntent를 작성
        Intent service = new Intent(this, Ch0708Service.class);
        service.setAction(action);

        return PendingIntent.getService(this, 0, service, 0);
    }

}
