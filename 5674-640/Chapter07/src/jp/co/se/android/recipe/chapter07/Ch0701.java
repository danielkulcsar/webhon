package jp.co.se.android.recipe.chapter07;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/***
 * 
 * 음원은 http://maoudamashii.jokersounds.com/music_rule.html 에서 받음
 * 
 * @author yokmama
 * 
 */
public class Ch0701 extends Activity implements OnClickListener,
        OnLoadCompleteListener {
    private SoundPool mSoundPool;
    private int mSoundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0701_main);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button1).setEnabled(false);

        // SoundPool의 초기화
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        // 음성 데이터 읽기 완료를 검지할 리스너 설정
        mSoundPool.setOnLoadCompleteListener(this);
        // 음성 데이터 읽기 시작
        mSoundID = mSoundPool.load(this, R.raw.se_quetion, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // SoundPool 해제
        mSoundPool.release();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            // 음성 재생
            mSoundPool.play(mSoundID, 1.0F, 1.0F, 0, 0, 1.0F);
        }
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        // 음성 읽기가 완료되면 재생 버튼을 활성화함
        findViewById(R.id.button1).setEnabled(true);
    }
}
