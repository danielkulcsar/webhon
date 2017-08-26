package jp.co.se.android.recipe.chapter07;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class Ch0703 extends Activity {
    private static final String TAG = "Ch0703";
    // 재생
    private MediaRecorder mMediaRecorder;
    private MediaPlayer mMediaPlayer;
    private String mFilePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0703_main);

        ToggleButton recSwitch = (ToggleButton) findViewById(R.id.toggleRecord);
        recSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                if (isChecked) {
                    // 녹음 시작
                    startRecord();
                } else {
                    // 녹음 정지
                    if (mMediaRecorder != null) {
                        mMediaRecorder.stop();
                        mMediaRecorder.reset();
                    }
                }
            }
        });

        // 재생 버튼 이벤트 등록
        findViewById(R.id.buttonPlay).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //녹음한 음성을 재생
                startPlay();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // MediaPlayer의 해제
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        // MediaRecorder의 해제
        if (mMediaRecorder != null) {
            if (mMediaRecorder != null) {
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        }
    }

    /**
     * MediaRecorder을 초기화하여 녹음 시작
     */
    private void startRecord() {
        // 재생 중인면 재생을 정지
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
        }

        // MediaRecorder 초기화
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }
        // 입력 소스를 마이크에 설정
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 저장 형식을 3gp로 설정
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // Audio인코딩을 기본으로 설정
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        // 외부 스토리지(microSD등)에 "hoge.3gp"이라는 이름으로 저장
        String fileName = "hoge.3gp";
        mFilePath = Environment.getExternalStorageDirectory() + "/" + fileName;
        mMediaRecorder.setOutputFile(mFilePath);

        // 녹음 준비를 완료하면 녹음 시작
        try {
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString(), e);
        }
    }

    /**
     * MediaPlayer를 초기화한 음성을 재생
     */
    private void startPlay() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        } else {
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setDataSource(mFilePath);
            // 음성 재생 준비 완료 시 호출될 리스너
            mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // 음성 재생
                    mp.start();
                }
            });
            mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(Ch0703.this,
                            R.string.text_complete_recordplay,
                            Toast.LENGTH_SHORT).show();
                }
            });
            mMediaPlayer.prepareAsync();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString(), e);
        } catch (SecurityException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString(), e);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString(), e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString(), e);
        }
    }
}
