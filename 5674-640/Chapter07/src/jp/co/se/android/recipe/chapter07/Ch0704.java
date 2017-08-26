package jp.co.se.android.recipe.chapter07;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;

/***
 * 
 * 음원은 http://maoudamashii.jokersounds.com/music_rule.html 에서 받음
 * 
 * @author yokmama
 * 
 */
@SuppressLint("NewApi")
public class Ch0704 extends Activity implements OnPreparedListener {
    private MediaPlayer mMediaPlayer1;
    private MediaPlayer mMediaPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0704_main);

        // 첫 번째 곡의 음성 데이터를 재생할 MediaPlayer 초기화
        mMediaPlayer1 = new MediaPlayer();
        mMediaPlayer1.setOnPreparedListener(this);
        // 두 번째 곡의 음성 데이터를 재생할 MediaPlayer 초기화
        mMediaPlayer2 = new MediaPlayer();
        mMediaPlayer2.setOnPreparedListener(this);

        try {
            // 첫 번째 곡의 음성 데이터를 설정
            mMediaPlayer1.setDataSource(
                    this,
                    Uri.parse("android.resource://" + getPackageName() + "/"
                            + R.raw.bgm_healing02));
            // 첫 번째 음성 데이터를 가져옴
            mMediaPlayer1.prepareAsync();
            // 두 번째 곡의 음성 데이터를 설정
            mMediaPlayer2.setDataSource(
                    this,
                    Uri.parse("android.resource://" + getPackageName() + "/"
                            + R.raw.bgm_healing03));
            // 두 번째 음성 데이터를 가져옴
            mMediaPlayer2.prepareAsync();
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
        if (mMediaPlayer1.isPlaying()) {
            mMediaPlayer1.stop();
        }
        // 미디어 플레이어가 재생 중이면 정지
        if (mMediaPlayer2.isPlaying()) {
            mMediaPlayer2.stop();
        }

        // 미디어 플레이어를 해제
        mMediaPlayer1.release();
        mMediaPlayer2.release();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (mp == mMediaPlayer1) {
            // 첫 번째 곡의 재생, 준비된 것으로 재생을 시작
            mMediaPlayer1.start();
        } else if (mp == mMediaPlayer2) {
            // 두 번째 곡의 재생, 준비된 것으로 재생을 시작
            mMediaPlayer1.setNextMediaPlayer(mMediaPlayer2);
        }
    }
}
