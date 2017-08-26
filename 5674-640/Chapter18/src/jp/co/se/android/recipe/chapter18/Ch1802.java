package jp.co.se.android.recipe.chapter18;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Ch1802 extends Activity {
    private static final String TAG = Ch1802.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG, "verbose 로그");
        Log.d(TAG, "debug 로그");
        Log.i(TAG, "info 로그");
        Log.w(TAG, "warning 로그");
        Log.e(TAG, "error 로그");
    }

}
