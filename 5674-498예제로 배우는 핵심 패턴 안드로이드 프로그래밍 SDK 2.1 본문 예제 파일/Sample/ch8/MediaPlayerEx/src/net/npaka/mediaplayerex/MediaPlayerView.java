package net.npaka.mediaplayerex;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

// 사운드의 재생
public class MediaPlayerView extends View
implements MediaPlayer.OnCompletionListener {
	private MediaPlayer player; // 플레이어
	
	// 생성자
	public MediaPlayerView(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
		setFocusable(true);
		player=null;
	}
	
	// 그리기
	@Override
		protected void onDraw(Canvas canvas) {
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(32);
		canvas.drawText("MediaPlayerEx",0,40,paint);
	}
	
	// 터치 이벤트의 처리
	@Override
		public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() ==MotionEvent.ACTION_DOWN) {
			if (player==null) {
				playSound();
			} else {
				stopSound();
			}
		}
		return true;
	}
	
	// 사운드의 재생
	public void playSound() {
		try {
			stopSound();
			
			// 미디어 플레이어의 생성 (1)
			player=MediaPlayer.create(getContext(),R.raw.sample);
			
			// 사운드의 재생 (2)
			player.seekTo(0);
			player.start();
			
			// 사운드의 재생 완료 리스너의 지정 (3)
			player.setOnCompletionListener(this);
		} catch (Exception e) {
		}
	}
	
	// 사운드의 정지
	public void stopSound() {
		try {
			if (player==null) return;
			
			// 사운드의 정지 (4)
			player.stop();
			player.setOnCompletionListener(null);
			player.release();
			player=null;
		} catch (Exception e) {
		}
	}
	
	// 사운드 재생 종료 시 불린다. (3)
	public void onCompletion(MediaPlayer mediaPlayer) {
		stopSound();
	}
}