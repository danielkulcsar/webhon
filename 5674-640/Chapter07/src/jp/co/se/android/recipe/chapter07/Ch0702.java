package jp.co.se.android.recipe.chapter07;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/***
 * 
 * 음원은 http://maoudamashii.jokersounds.com/music_rule.html 에서 받음
 * @author yokmama
 * 
 */
public class Ch0702 extends Activity implements OnClickListener,
        OnPreparedListener, OnCompletionListener {
    private Button mButtonPlayPause;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0702_main);

        mButtonPlayPause = (Button) findViewById(R.id.buttonPlayPause);
        mButtonPlayPause.setOnClickListener(this);
        mButtonPlayPause.setEnabled(false);

        // 미디어플레이어 초기화
        mMediaPlayer = new MediaPlayer();
        // 미디어 재생 준비 완료 통지 리스너 설정
        mMediaPlayer.setOnPreparedListener(this);
        // 미디어 재생 완료 통지 리스너 설정 
        mMediaPlayer.setOnCompletionListener(this);

        // 미디어 파일 경로 설정
        String fileName = "android.resource://" + getPackageName() + "/"
                + R.raw.bgm_healing02;
        try {
            // 미디어 파일을 MediaPlayer로 설정
            mMediaPlayer.setDataSource(this, Uri.parse(fileName));
            // 미디어 파일을 비동기로 읽기
            mMediaPlayer.prepareAsync();
            setButtonText(mMediaPlayer);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 미디어 플레이어가 재생 중이면 정지
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        // 미디어 플레이어를 해제
        mMediaPlayer.release();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonPlayPause) {
            if (mMediaPlayer.isPlaying()) {
                // 미디어 플레이어가 재생 중이면 일시 정지
                mMediaPlayer.pause();
                setButtonText(mMediaPlayer);
            } else {
                // 미디어 플레이어가 재생 중이 아니라면 재생
                mMediaPlayer.start();
                setButtonText(mMediaPlayer);
            }
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // 미디어 플레이어가 재생 가능하면 재생 버튼을 활성화
        mButtonPlayPause.setEnabled(true);
        setButtonText(mp);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // 미디어 플레이어의 재생이 끝나면 버튼 상태를 변경
        setButtonText(mp);
    }

    private void setButtonText(MediaPlayer mp) {
        if (mp.isPlaying()) {
            // 재생 중이면 정지를 표시
            mButtonPlayPause.setText(getString(R.string.text_stop));
        } else {
            // 정지 중이면 재생을 표시
            mButtonPlayPause.setText(getString(R.string.text_play));
        }
    }
}
