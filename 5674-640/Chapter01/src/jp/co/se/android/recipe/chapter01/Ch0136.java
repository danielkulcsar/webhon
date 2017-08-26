package jp.co.se.android.recipe.chapter01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Ch0136 extends Activity {
	private Handler mHandler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ch0136_main);

		final ImageView iv = (ImageView) findViewById(R.id.imageView);
		final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);

		// 3초간 로딩 상태를 구현
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// 프로그레스를 미표시 상태로 설정
				pb.setVisibility(View.GONE);
				// ImageView를 표시 상태로 설정
				iv.setVisibility(View.VISIBLE);
				iv.setImageResource(R.drawable.ic_launcher);
			}
		}, 3000);
	}
}
