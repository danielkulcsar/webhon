package com.corea.CriteriaLocDemo;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class CriteriaLocDemo extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		LocationManager locManager;															// ��ġ �޴��� ���� - location, locationprovider ���� �� �ִ�.
		locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);				// ��ġ �Ŵ����� ���´�.

		Criteria reqment = new Criteria();					// reqment�� ����Ͽ�, ������ provider�� ã�Ƴ� �� �ִ�.
		reqment.setAccuracy(Criteria.ACCURACY_COARSE);		// ACCURACY_FINE�� ��Ȯ�� ���� , ACCURACY_COARSE�� ��Ȯ�� ����
		reqment.setAltitudeRequired(false);				// �� ��� ����
		reqment.setBearingRequired(false);					// ���� ��� ����
		reqment.setCostAllowed(true);						// �ӵ� ��� ����
		reqment.setPowerRequirement(Criteria.POWER_LOW);	// POWER_LOW ���� �Ҹ� ����, POWER_HIGH ���� �Ҹ� ����. POWER_MEDIUM ���� �Ҹ� ���� 
		reqment.setSpeedRequired(false);					// �ӵ� ��� ����
		
		String pvder = locManager.getBestProvider(reqment, true);	// ���� ������ provider�� ã�´�. ������ null ��ȯ
		Location loc = locManager.getLastKnownLocation(pvder);	// ������ �� provider���� ���� ��ϵ� ��ǥ�� ������ �����ͼ�

		/*
			CriteriaŬ������ �������� �κ��� SharedPreferencesŬ������ �̿��Ͽ� ���� �ϸ� ���� �� �մϴ�.
			
			���� LocationManager�� LocationProvider���� ������Ͼ������ ���� Location Ŭ������ ���� �����Դϴ�.

		 */
		locUpdate(loc);									
		locManager.requestLocationUpdates(pvder, 2000, 10,			// ��ġ ���ŵǴ� ����
				locListener);
	}

	private final LocationListener locListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			locUpdate(location);
		}

		public void onProviderDisabled(String provider) {
			locUpdate(null);
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};

	/** Update UI with a new location */
	private void locUpdate(Location location) {
		TextView LocTV = (TextView) findViewById(R.id.LocTextView);

		String str;
		if (location != null) {
			double lat = location.getLatitude();							
			double lng = location.getLongitude();
			str = "  ����:" + lat + "\n  �浵:" + lng;
		} else {
			str = "��ġ ã�� ����.";
		}
		LocTV.setText("�䱸 ���ؿ� ���� ��ġ\n" + str);
	}
}
