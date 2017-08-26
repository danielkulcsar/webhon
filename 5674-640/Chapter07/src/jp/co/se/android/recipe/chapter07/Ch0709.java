package jp.co.se.android.recipe.chapter07;

import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.audiofx.Equalizer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/***
 * 
 * 음원은 http://maoudamashii.jokersounds.com/music_rule.html 에서 받음
 * 
 * @author yokmama
 * 
 */
@SuppressLint("NewApi")
public class Ch0709 extends Activity implements OnClickListener,
        OnPreparedListener, OnCompletionListener {
    public static final String TAG = "Chapter07";
    private Button mButtonPlayPause;
    private MediaPlayer mMediaPlayer;
    private Equalizer mEqualizer;
    private ArrayList<View> mSeekBars = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0709_main);

        mButtonPlayPause = (Button) findViewById(R.id.buttonPlayPause);
        mButtonPlayPause.setOnClickListener(this);
        mButtonPlayPause.setEnabled(false);

        // 미디어 플레이어 초기화
        mMediaPlayer = new MediaPlayer();
        // 미디어 재생 준비 완료 통지를 받을 리스너 설정
        mMediaPlayer.setOnPreparedListener(this);
        // 미디어 재생 완료 통지를 받을 리스너 설정
        mMediaPlayer.setOnCompletionListener(this);

        // 미디어 파일용 경로 생성
        String fileName = "android.resource://" + getPackageName() + "/"
                + R.raw.bgm_healing02;
        try {
            // 미디어 파일을 MediaPlayer에 설정
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

        findViewById(R.id.checkEqulizer).setOnClickListener(this);

        // Eqaulizer 생성
        mEqualizer = new Equalizer(0, mMediaPlayer.getAudioSessionId());

        LinearLayout layoutBandlevels = (LinearLayout) findViewById(R.id.layoutBandlevels);
        LayoutInflater layoutInflater = getLayoutInflater();

        // 이퀄라이저의 밴드 수만큼 SeekBar를 생성
        short bands = mEqualizer.getNumberOfBands();
        for (int i = 0; i < bands; i++) {
            View layout = layoutInflater.inflate(R.layout.ch0709_seekbar,
                    layoutBandlevels, false);
            layoutBandlevels.addView(layout,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            mSeekBars.add(layout);
            SeekBar seekbar = (SeekBar) layout.findViewById(R.id.seekBar);
            // 리스너 중에서 어느 밴드의 SeekBar인지 판단하기 위해 태그에 값을 설정
            seekbar.setTag(i);
            // SeekBar의 터치로 SeekBar의 값을 구하고 Equalizer로 설정
            seekbar.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        SeekBar seekbar = (SeekBar) v;
                        short maxEQLevel = mEqualizer.getBandLevelRange()[1];
                        // 사전에 설정한 SeekBar 번호를 구함
                        int index = (Integer) seekbar.getTag();
                        // 변경된 SeekBar의 값을 구해 최소치에서 보정
                        short band = (short) (seekbar.getProgress() - maxEQLevel);
                        View layout = mSeekBars.get(index);
                        // 변경된 SeekBar의 값을 표시하고 이퀄라이저 밴드의 값을 갱신
                        TextView textFreq = (TextView) layout
                                .findViewById(R.id.textFreq);
                        textFreq.setText(String.format("%6d", band));
                        // 이퀄라이저에 밴드의 값을 설정
                        mEqualizer.setBandLevel((short) index, band);
                    }
                    return false;
                }
            });
        }

        // 이퀄라이저 표시를 갱신
        updateEqlizerValue();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 미디어 플레이어 재생을 정지
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        // 미디어 플레이어 해제
        mMediaPlayer.release();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonPlayPause) {
            if (mMediaPlayer.isPlaying()) {
                // 미디어 플레이어가 재생 중이면 정지
                mMediaPlayer.pause();
                setButtonText(mMediaPlayer);
            } else {
                // 미디어 플레이어가 재생 중이 아니라면 재생
                mMediaPlayer.start();
                setButtonText(mMediaPlayer);
            }
        } else if (v.getId() == R.id.checkEqulizer) {
            CheckBox checkbox = (CheckBox) v;
            // 이퀄라이저의 설정을 유효 또는 사용 불가능으로 설정
            mEqualizer.setEnabled(checkbox.isChecked());
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // 미디어 플레이어가 재생 가능하게 된 것으로 재생 버튼을 활성화
        mButtonPlayPause.setEnabled(true);
        setButtonText(mp);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // 미디어 플레이어의 재생이 끝났으므로 버튼 상태를 변경
        setButtonText(mp);
    }

    private void setButtonText(MediaPlayer mp) {
        if (mp.isPlaying()) {
            // 재생 중이며 정지를 표시
            mButtonPlayPause.setText(getString(R.string.text_stop));
        } else {
            // 정지 중이면 재생을 표시
            mButtonPlayPause.setText(getString(R.string.text_play));
        }
    }

    private void updateEqlizerValue() {
        // 이퀄라이저 밴드수를 구함
        short bands = mEqualizer.getNumberOfBands();
        // 이퀄라이저 밴드 최소값
        short minEQLevel = mEqualizer.getBandLevelRange()[0];
        // 이퀄라이저 밴드 최대값
        short maxEQLevel = mEqualizer.getBandLevelRange()[1];
        // 이퀄라이저 밴드 수만큼 SeekBar와 TextView의 초기값을 설정
        for (int i = 0; i < bands; i++) {
            if (mSeekBars.size() > i) {
                View layout = mSeekBars.get(i);
                SeekBar seekbar = (SeekBar) layout.findViewById(R.id.seekBar);
                TextView textFreq = (TextView) layout
                        .findViewById(R.id.textFreq);
                TextView textFreqMax = (TextView) layout
                        .findViewById(R.id.textFreqMax);
                seekbar.setMax(maxEQLevel + Math.abs(minEQLevel));
                // 현재 이퀄라이저 밴드의 값을 구함
                short band = mEqualizer.getBandLevel((short) i);
                // 이퀄라이저 주파수대의 값을 구함
                int freq = mEqualizer.getCenterFreq((short) i) / 1000;
                // SeekBar에 밴드의 값을 설정
                seekbar.setProgress(band + Math.abs(minEQLevel));
                textFreq.setText(String.format("%6d", band));
                textFreqMax.setText(String.format("%6dHz", freq));
            }
        }
    }
}
