package jp.co.se.android.recipe.chapter01;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

public class Ch0122 extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ch0122_main);

		// 레이아웃으로부터 인스턴스 생성
		final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingbar);
		// RatingBar인스턴스에 리스너 추가
		ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			// 레이팅을 변경하고 싶은 경우
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// 토스트 메시지 표시
				Toast.makeText(Ch0122.this,
						"New Rating: " + rating + "  /fromUser:" + fromUser,
						Toast.LENGTH_SHORT).show();
			}
		});

	}
}
