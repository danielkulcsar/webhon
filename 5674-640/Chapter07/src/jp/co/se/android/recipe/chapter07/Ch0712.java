package jp.co.se.android.recipe.chapter07;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerView;

public class Ch0712 extends YouTubeBaseActivity implements
        OnInitializedListener {
	private static final String DEVELOPER_KEY = "AIzaSyAwm5CkEzzpnVo0rVxJWNuXcxmSLxhnilc";
	private static final int RECOVERY_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0712_main);

        // YoutubePlayerView에 Developer 키를 설정
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
            YouTubeInitializationResult errorReason) {
        // 초기화 실패한 경우 처리
        if (errorReason.isUserRecoverableError()) {
            // 오류 대화 상자 표시
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            // 오류 표시
            String errorMessage = String.format(
                    getString(R.string.text_error_player),
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
            YouTubePlayer player, boolean wasRestored) {
        // YouTube 동영상 ID를 설정
        if (!wasRestored) {
            player.cueVideo("CZB-CaxLjyw");
        }
    }
}
