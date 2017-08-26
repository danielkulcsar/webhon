package jp.co.se.android.recipe.chapter11;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Ch1104 extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    TextView mTvAzimuth;
    private float[] mAcMatrix = new float[3];
    private float[] mMgMatrix = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1104_main);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mTvAzimuth = (TextView) findViewById(R.id.Azimuth);
    }

    @Override
    protected void onPause() {
        // 센서 해제
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 센서 등록
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);

        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
        case Sensor.TYPE_ACCELEROMETER: // 가속도 센서 값을 구한다
            mAcMatrix = event.values.clone();
            break;
        case Sensor.TYPE_MAGNETIC_FIELD:// 자기 센서 값을 구한다
            mMgMatrix = event.values.clone();
            break;
        }

        if (mMgMatrix != null && mAcMatrix != null) {
            float[] orientation = new float[3];
            float[] R = new float[16];
            float[] I = new float[16];

            // 가속도 센서, 자기 센서의 값을 바탕으로 회전 행렬을 계산
            SensorManager.getRotationMatrix(R, I, mAcMatrix, mMgMatrix);

            // 디바이스의 방향에 따라서 회전 행렬을 계산
            SensorManager.getOrientation(R, orientation);

            // 라디안에서 각도로 변환
            float angle = (float) Math.floor(Math.toDegrees(orientation[0]));

            // 각도 범위를 0~360도로 조정
            if (angle >= 0) {
                orientation[0] = angle;
            } else if (angle < 0) {
                orientation[0] = 360 + angle;
            }

            // 구한 각도를 화면에 표시
            mTvAzimuth.setText(String.valueOf(orientation[0]));
        }
    }
}