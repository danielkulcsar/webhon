package com.corea.RevGeoDemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class RevGeoDemo extends Activity {
	
 @Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.main);

	  LocationManager locManager;														
	  locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);		// ��ġ�Ŵ��� ��ü ����

	  Criteria reqment = new Criteria();				// ������ �����ڸ� �������
	  reqment.setAccuracy(Criteria.ACCURACY_FINE);
	  reqment.setAltitudeRequired(false);
	  reqment.setBearingRequired(false);
	  reqment.setCostAllowed(true);
	  reqment.setPowerRequirement(Criteria.POWER_LOW);

	  String pvder = locManager.getBestProvider(reqment, true);	// criteria�� ������ ���� �����ڸ� ���´�.

	  Location loc = locManager.getLastKnownLocation(pvder);	// �����ڷκ��� �ֱ� ��ġ�� �޾ƿ´�.
	  LocUpdate(loc);
	  locManager.requestLocationUpdates(pvder, 2000, 10, locListener);		// ������Ʈ �Ǵ� ������ �����Ѵ�. 
	}
	
    private final LocationListener locListener = new LocationListener() {		// ��ġ��ȭ�� ������
       public void onLocationChanged(Location location) {
    	   LocUpdate(location);
       }
       public void onProviderDisabled(String provider){
    	   LocUpdate(null);
       }
       public void onProviderEnabled(String provider) {}
       public void onStatusChanged(String provider, int status, Bundle extras){}
    };
	
  /** Update UI with a new location */
	private void LocUpdate(Location loc) {
	  TextView LocTV = (TextView)findViewById(R.id.LocTextView);

      String str;
	  String addrStr = null;
	 	  
	  if (loc != null) {
	    double lat = loc.getLatitude();  
	    double lng = loc.getLongitude();
	    str = "(����:" + lat + ", �浵:" + lng + ")";				// ������ ��ġ�� ���� �浵�� ���

	    Geocoder gCoder = new Geocoder(this, Locale.getDefault());				// geocoder ��ü ����
	    
	    try {
	      List<Address> addrList = gCoder.getFromLocation(lat, lng, 1);		// address�� ���� �浵 �ִ´�.
	      StringBuilder strBlder = new StringBuilder();
	      if (addrList.size() > 0) {										// ��ġ�� ������
	        Address addr = addrList.get(0);

	        for (int i = 0; i < addr.getMaxAddressLineIndex(); i++)
	        	strBlder.append(addr.getAddressLine(i)).append("\n");   		

	        strBlder.append(addr.getLocality()).append("\n");					// address�� ��ġ������ ������ ���δ�.
	        strBlder.append(addr.getPostalCode()).append("\n");				// address�� �����ȣ�� ������ ���δ�.
	        strBlder.append(addr.getCountryName());							// address�� �����̸��� ������ ���δ�.
	        addrStr = strBlder.toString();
	    } else
	    	addrStr = "�ּ� ã�� �� ����.";
	    } catch (IOException e) {System.out.println("IO Exception occurred.\n");}
	  } else {
		  str = "��ġ �߰� ���� ����.";
	  }
	  LocTV.setText("��ǥ:\n" + str + "\n\n��ȯ�ּ�:\n" + addrStr);
	}
}