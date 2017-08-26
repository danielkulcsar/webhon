package jp.co.se.android.recipe.chapter13;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class Ch1310GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    private String TAG = "Ch1311GcmBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Ch1311GcmBroadcastReceiver.onReceive:" + intent.getAction());
        ComponentName comp = new ComponentName(context.getPackageName(),
                Ch1310GcmIntentService.class.getName());
        // Ch1311GcmIntentService을 동작, 서비스 처리 중은 WakeLock을 보유한다
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }

}
