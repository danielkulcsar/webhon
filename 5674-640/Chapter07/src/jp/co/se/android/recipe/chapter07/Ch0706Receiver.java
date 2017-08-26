package jp.co.se.android.recipe.chapter07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

public class Ch0706Receiver extends BroadcastReceiver {
    public static final String TAG = "Chapter07";
    private static final int KEYCODE_MEDIA_PLAY = 126;
    private static final int KEYCODE_MEDIA_PAUSE = 127;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
            KeyEvent keyEvent = (KeyEvent) intent
                    .getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
                switch (keyEvent.getKeyCode()) {
                case KEYCODE_MEDIA_PAUSE:
                case KEYCODE_MEDIA_PLAY:
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                case KeyEvent.KEYCODE_HEADSETHOOK:
                    // 재생, 일시 정지, 후크 버튼
                    Intent service = new Intent(context, Ch0706Service.class);
                    service.setAction("playpause");
                    context.startService(service);
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    // 다음곡으로
                    break;
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    // 이전곡으로
                    break;
                case KeyEvent.KEYCODE_MEDIA_STOP:
                    // 정지
                    break;
                case KeyEvent.KEYCODE_MEDIA_FAST_FORWARD:
                    // 앞으로 가기
                    break;
                case KeyEvent.KEYCODE_MEDIA_REWIND:
                    // 뒤로 가기
                    break;
                default:
                    Log.w(TAG, "잘못된 호출: " + keyEvent.getKeyCode());
                }
            }
        }
    }
}
