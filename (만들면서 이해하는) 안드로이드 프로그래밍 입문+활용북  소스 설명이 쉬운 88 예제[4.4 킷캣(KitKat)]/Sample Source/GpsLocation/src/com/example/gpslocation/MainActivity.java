package com.example.gpslocation;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextMsg;
    TextView mTextLat;
    TextView mTextLng;
    TextView mTextAlt;
    LocationManager mLocMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTextMsg = (TextView)findViewById(R.id.textMsg);
        mTextLat = (TextView)findViewById(R.id.textLat);
        mTextLng = (TextView)findViewById(R.id.textLng);
        mTextAlt = (TextView)findViewById(R.id.textAlt);

        mLocMgr = (LocationManager) 
                getSystemService(Context.LOCATION_SERVICE);

    }

    LocationListener mLocListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            mTextLat.setText("Lat: " + location.getLatitude());
            mTextLng.setText("Lng: " + location.getLongitude());
            mTextAlt.setText("Alt: " + location.getAltitude());
        }

        public void onProviderDisabled(String provider) {
            mTextMsg.setText("Provider Disabled");
        }

        public void onProviderEnabled(String provider) {
            mTextMsg.setText("Provider Enabled");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                mTextMsg.setText("Provider Out of Service");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                mTextMsg.setText("Provider Temporarily Unavailable");
                break;
            case LocationProvider.AVAILABLE:
                mTextMsg.setText("Provider Available");
                break;
            }
        }
    };

    public void onResume() {
        super.onResume();
        String locProv = mLocMgr.getBestProvider(getCriteria(), true);
        mLocMgr.requestLocationUpdates( locProv, 3000, 3, mLocListener );
        mTextMsg.setText("Location Service Start");
    }

    public void onPause() {
        super.onPause();
        mLocMgr.removeUpdates(mLocListener);
        mTextMsg.setText("Location Service Stop");
    }

    public static Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        criteria.setSpeedRequired(true);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);  
        return criteria;
    }

}
