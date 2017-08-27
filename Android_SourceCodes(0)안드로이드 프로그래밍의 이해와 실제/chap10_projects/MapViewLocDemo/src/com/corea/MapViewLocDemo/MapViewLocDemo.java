package com.corea.MapViewLocDemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MapViewLocDemo extends MapActivity {		
	
	MapController mapControl;										// google map�� �ٷ�� Ŭ����, google API�� ����ִ�.

	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.main);

	  MapView mapView = (MapView)findViewById(R.id.MapView01);		
	  mapControl = mapView.getController();						// mapView�� �ٷ� �� �ְ� �ϱ� ���� ��Ʈ�ѷ� ��ü�� �����Ѵ�.
	  
	  // Configure the map display options
	  mapView.setSatellite(true);										// ������������ �����ش�.
	  mapView.setStreetView(true);									// �Ÿ��� ǥ�õȴ�.
	  mapView.displayZoomControls(false);								// �� ��Ʈ�� ����� off��Ų��.
	  
	  // Zoom in
	  mapControl.setZoom(17);										// ������ ���� 17�� �����Ѵ�.

	  LocationManager locManager = 
                  (LocationManager)getSystemService(Context.LOCATION_SERVICE);	// ��ġ �Ŵ��� ��ü �����Ѵ�.

	  Criteria reqment = new Criteria();										// ������ �����ڸ� �����ϱ� ���� criteria�� �����Ѵ�.
	  reqment.setAccuracy(Criteria.ACCURACY_FINE);								// 
	  reqment.setAltitudeRequired(false);
	  reqment.setBearingRequired(false);
	  reqment.setCostAllowed(true);
	  reqment.setPowerRequirement(Criteria.POWER_LOW);
	  String provider = locManager.getBestProvider(reqment, true);		// �����ڸ� ã�´�.

	  Location location = locManager.getLastKnownLocation(provider);		// �������� ������ ��ġ�� location�� ��´�.
      try {
    	  locUpdate(location);
      } catch (NullPointerException e) {
    	  // DDMS���� ��ǥ���� ���� ������ �ΰ��� ��ȯ�Ѵ�.
      }
	  locManager.requestLocationUpdates(provider, 2000, 10, locationListener);
	}

	private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
        	locUpdate(location);  
        }
        public void onProviderDisabled(String provider){     
        	locUpdate(null);
        }
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

  /** Update the map with a new location */
	private void locUpdate(Location location) {
	  // Update the map location.
	  
	     Double geoLat = location.getLatitude()*1E6;									// ��ġ ��ǥ ���� GeoPoing ��ü�� ����, �浵 ǥ�ÿ� 1E6�� ��������Ѵ�.
	     Double geoLng = location.getLongitude()*1E6;		
	     GeoPoint gPoint = new GeoPoint(geoLat.intValue(), geoLng.intValue());			// ��ġ ����

	     mapControl.animateTo(gPoint);
	  
	     TextView locText = (TextView)findViewById(R.id.LocText);
		  
	     String latLngStr;
	 	 String addrStr = "No address found";

	 	  if (location != null) {
	 	    double lat = location.getLatitude();
	 	    double lng = location.getLongitude();
	 	    latLngStr = "����:" + lat + "\n�浵:" + lng;

	 	    Geocoder gCode = new Geocoder(this, Locale.getDefault());
	 	    try {
	 	      List<Address> addresses = gCode.getFromLocation(lat, lng, 1);
	 	      StringBuilder strblder = new StringBuilder();
	 	      if (addresses.size() > 0) {
	 	        Address address = addresses.get(0);

	 	        for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
	 	        	strblder.append(address.getAddressLine(i)).append("\n");

	 	       strblder.append(address.getLocality()).append("\n");
	 	       strblder.append(address.getPostalCode()).append("\n");
	 	       strblder.append(address.getCountryName());
	 	      }
	 	      addrStr = strblder.toString();
	 	    } catch (IOException e) {}
	 	  } else {
	 		 latLngStr = "��ġ ã�� ������.";
	 	  }

	 	  locText.setText("����� ���� ��ġ\n" + latLngStr + "\n\n����� �ּ�:\n" + addrStr);
    }

    @Override
    protected boolean isRouteDisplayed() {
          return false;
    }
}
