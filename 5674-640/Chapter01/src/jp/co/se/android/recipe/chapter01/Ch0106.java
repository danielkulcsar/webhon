package jp.co.se.android.recipe.chapter01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Ch0106 extends Activity {
	private Handler mHandler = new Handler();
	private int value = 0;
	private ProgressBar mProgress;
	private TextView mTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ch0106_main);

		mProgress = (ProgressBar) findViewById(R.id.progressbar);
		mTextView = (TextView) findViewById(R.id.textview);

		// 프로그레스 바의 값을 증가시킴(데모표시용)
		mHandler.post(addProgress);
	}

	@Override
	protected void onDestroy() {
		// 실행 중인 핸들러를 제거
		mHandler.removeCallbacksAndMessages(null);
		super.onDestroy();
	}

	/**
	 * 프로그래서 값을 10ms마다 증가시키는 스레드
	 */
	private Runnable addProgress = new Runnable() {
		@Override
		public void run() {
			value++;
			if (value > 100) {
				value = 0;
			}
			mProgress.setProgress(value);
			mTextView.setText(value + "%");
			mHandler.postDelayed(addProgress, 10);
		}
	};
}
