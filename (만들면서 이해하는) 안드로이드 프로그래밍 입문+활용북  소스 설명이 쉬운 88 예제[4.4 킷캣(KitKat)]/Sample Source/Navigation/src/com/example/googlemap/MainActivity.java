package com.example.googlemap;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.widget.*;

import com.google.android.gms.common.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.*;

public class MainActivity extends Activity {
    GoogleMap mGoogleMap;
    Marker mMarkerStart;
    Marker mMarkerMan;
    boolean mFirstLoc = true;
    LocationManager mLocMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        initMap();
        mLocMgr = (LocationManager) getSystemService( 
                Context.LOCATION_SERVICE );
	}

    // 구글맵 초기화
    private void initMap() {
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        mGoogleMap = ((MapFragment)getFragmentManager()
                .findFragmentById( R.id.map1 )).getMap();
        //mGoogleMap = ((SupportMapFragment) 
        //       getSupportFragmentManager().findFragmentById( 
        //        R.id.map1 )).getMap();
        LatLng pos = new LatLng(37.4980, 127.027);
        mGoogleMap.moveCamera( 
                CameraUpdateFactory.newLatLngZoom(pos, 15));

        MarkerOptions moStart = new MarkerOptions();
        moStart.position(pos);
        moStart.title("출발");
        mMarkerStart = mGoogleMap.addMarker(moStart);
        mMarkerStart.showInfoWindow();

        mGoogleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplication(), "출발 위치", 
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        MarkerOptions moMan = new MarkerOptions();
        moMan.position(pos);
        moMan.icon( BitmapDescriptorFactory.fromResource( 
                R.drawable.ic_launcher ));
        mMarkerMan = mGoogleMap.addMarker( moMan );
    }

    LocationListener mLocListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            LatLng position = new LatLng(location.getLatitude(), 
                    location.getLongitude());
            if( mFirstLoc ) {
                mFirstLoc = false;
                mMarkerStart.setPosition(position);
            }

            mMarkerMan.setPosition(position);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(position) );
        }

        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    public void onResume() {
        super.onResume();
        String locProv = LocationManager.GPS_PROVIDER;
        mLocMgr.requestLocationUpdates(locProv, (long)3000, 3.f, mLocListener);
    }

    public void onPause() {
        super.onPause();
        mLocMgr.removeUpdates(mLocListener);
    }

}
