package jp.co.se.android.recipe.chapter11;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class Ch1106 extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1106_main);

        final TextView tvDescription = (TextView) findViewById(R.id.description);
        final Switch proximitySwitch = (Switch) findViewById(R.id.proximitySwitch);

        // 스위치 ON・OFF
        proximitySwitch
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                            boolean isChecked) {
                        if (isChecked) {
                            // 센서를 등록
                            registSensor();
                            tvDescription.setText(Ch1106.this
                                    .getString(R.string.ch1106_label_pause_of_the_call));
                            tvDescription.setVisibility(View.VISIBLE);
                        } else {
                            // 센서를 해제
                            unregistSensor();
                            tvDescription.setVisibility(View.GONE);
                        }
                    }
                });

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    protected void onDestroy() {
        // 센서를 해제
        unregistSensor();
        super.onDestroy();
    }

    /**
     * 근접 센서를 등록
     */
    private void registSensor() {
        if (mSensorManager == null) {
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            mSensorManager.registerListener(this,
                    mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                    SensorManager.SENSOR_DELAY_UI);
        }
    }

    /**
     * 근접 센서를 해제
     */
    private void unregistSensor() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
        }
        mVibrator.cancel();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 가속도 센서의 값을 구하기
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] == 0) {
                mVibrator.vibrate(new long[] { 100, 100 }, 0);
            } else {
                mVibrator.cancel();
            }
        }
    }
}
