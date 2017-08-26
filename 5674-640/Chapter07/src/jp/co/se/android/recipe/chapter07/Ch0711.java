package jp.co.se.android.recipe.chapter07;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class Ch0711 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0711_main);

        VideoView videoView = (VideoView) findViewById(R.id.videoView1);
        // raw 폴더의 파일을 재생
        videoView.setVideoURI(Uri.parse("android.resource://"
                + getPackageName() + "/" + R.raw.sample));
        // 동영상 재생 시작
        videoView.start();
    }

}
