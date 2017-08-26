package jp.co.se.android.recipe.chapter07;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.RemoteControlClient;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

@SuppressLint("NewApi")
public class Ch0707Service extends Service {
    public static final String TAG = "Chapter07";
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private RemoteControlClient mRemoteControlClient;
    private ComponentName mComponentName;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
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
            registerMediaButtonEventReceiver();
        }

        mMediaPlayer.start();

        // RemoteControlClient 등록
        registerRemoteControlClient();

        // 잠금화면 상태를 재생에 설정
        mRemoteControlClient
                .setPlaybackState(RemoteControlClient.PLAYSTATE_PLAYING);
        // 잠금화면에 표시할 음악 정보를 설정
        mRemoteControlClient
                .editMetadata(true)
                .putString(MediaMetadataRetriever.METADATA_KEY_ARTIST,
                        "Sample Artist")
                .putString(MediaMetadataRetriever.METADATA_KEY_ALBUM,
                        "Sample Album")
                .putString(MediaMetadataRetriever.METADATA_KEY_TITLE,
                        "Sample Music").apply();
    }

    private void pause() {
        mMediaPlayer.pause();

        // 잠금화면 상태를 일시 정지로 설정
        mRemoteControlClient
                .setPlaybackState(RemoteControlClient.PLAYSTATE_PAUSED);
    }

    private void stop() {
        mMediaPlayer.stop();
        mMediaPlayer = null;
        unregisterMediaButtonEventReceiver();

        // RemoteControlClient 등록 해제
        unregisterRemoteControlClient();
    }

    private void registerMediaButtonEventReceiver() {
        if (mComponentName == null) {
            // MediaButtonEventReceiver를 등록
            mComponentName = new ComponentName(this, Ch0707Receiver.class);
            // MediaButtonEventReceiver를 시스템에 등록
            mAudioManager.registerMediaButtonEventReceiver(mComponentName);
        }
    }

    private void unregisterMediaButtonEventReceiver() {
        if (mComponentName != null) {
            // MediaButtonEventReceiver 등록 해제
            mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);
            mComponentName = null;
        }

    }

    private void registerRemoteControlClient() {
        if (mRemoteControlClient == null) {
            // LockScreen에서 해제 버튼의 이벤트를 받은 리시버의 PendingIntent를 생성
            Intent mediaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
            mediaButtonIntent.setComponent(mComponentName);
            PendingIntent mediaPendingIntent = PendingIntent.getBroadcast(
                    getApplicationContext(), 0, mediaButtonIntent, 0);

            // RemoteControlClient를 생성하고, PendingIntent를 설정
            mRemoteControlClient = new RemoteControlClient(mediaPendingIntent);
            mRemoteControlClient
                    .setTransportControlFlags(RemoteControlClient.FLAG_KEY_MEDIA_PLAY
                            | RemoteControlClient.FLAG_KEY_MEDIA_PAUSE
                            | RemoteControlClient.FLAG_KEY_MEDIA_NEXT
                            | RemoteControlClient.FLAG_KEY_MEDIA_STOP);

            // RemoteControlClient를 등록
            mAudioManager.registerRemoteControlClient(mRemoteControlClient);

            // AudioFocus를 취득
            mAudioManager.requestAudioFocus(new OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    Log.d(TAG, "focusChanged:" + focusChange);
                }
            }, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        }
    }

    private void unregisterRemoteControlClient() {
        if (mRemoteControlClient != null) {
            // RemoteControlClient 등록 해제
            mAudioManager.unregisterRemoteControlClient(mRemoteControlClient);
            mRemoteControlClient = null;
        }

    }

}
