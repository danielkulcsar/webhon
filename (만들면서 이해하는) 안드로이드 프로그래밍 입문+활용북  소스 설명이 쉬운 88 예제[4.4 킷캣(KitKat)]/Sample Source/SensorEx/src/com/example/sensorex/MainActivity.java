package com.example.sensorex;

import java.util.*;

import android.app.*;
import android.content.*;
import android.hardware.*;
import android.os.*;
import android.widget.*;

public class MainActivity extends Activity
        implements SensorEventListener {
    TextView mTextMessage;
    TextView mTextAcceler;
    TextView mTextMagnetic;
    TextView mTextLight;
    SensorManager mSensorMgr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
        mTextAcceler = (TextView)findViewById(R.id.textAcceler);
        mTextMagnetic = (TextView)findViewById(R.id.textMagnetic);
        mTextLight = (TextView)findViewById(R.id.textLight);

        mSensorMgr = 
                (SensorManager)getSystemService( Context.SENSOR_SERVICE );
        showSensorList();

        Sensor sensorAcceler = 
                mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if( sensorAcceler != null )
            mSensorMgr.registerListener(this, sensorAcceler, 
                    SensorManager.SENSOR_DELAY_UI);

        Sensor sensorMagnetic = 
                mSensorMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if( sensorMagnetic != null )
            mSensorMgr.registerListener(this, sensorMagnetic, 
                    SensorManager.SENSOR_DELAY_UI);

        Sensor sensorLight = mSensorMgr.getDefaultSensor(Sensor.TYPE_LIGHT);
        if( sensorLight != null )
            mSensorMgr.registerListener(this, sensorLight, 
                    SensorManager.SENSOR_DELAY_UI);
    }

    public void showSensorList() {
        String strMsg = "Sensor Type";

        List<Sensor> listSensor = mSensorMgr.getSensorList(Sensor.TYPE_ALL);
        for( int i=0; i < listSensor.size(); i++ ) {
            Sensor sensor = listSensor.get(i);
            strMsg += " - " + sensor.getType();
        }
        mTextMessage.setText(strMsg);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void onSensorChanged(SensorEvent event) {
        String strMsg = "";
        float v[] = event.values;

        switch( event.sensor.getType() ) {
        case Sensor.TYPE_ACCELEROMETER :
            strMsg = "Acceler - X: " + cut2(v[0]) + " / Y: " + cut2(v[1]) 
                    + " / Z: " + cut2(v[2]);
            mTextAcceler.setText(strMsg);
            break;
        case Sensor.TYPE_MAGNETIC_FIELD :
            strMsg = "Magnetic - X: " + cut2(v[0]) + " / Y: " + cut2(v[1])
                    + " / Z: " + cut2(v[2]);
            mTextMagnetic.setText(strMsg);
            break;
        case Sensor.TYPE_LIGHT :
            strMsg = "Light : " + cut2(v[0]);
            mTextLight.setText(strMsg);
            break;
        }
    }

    public double cut2(double orig) {
        double d = Double.parseDouble(String.format("%.2f", orig));
        return d;
    }

}
