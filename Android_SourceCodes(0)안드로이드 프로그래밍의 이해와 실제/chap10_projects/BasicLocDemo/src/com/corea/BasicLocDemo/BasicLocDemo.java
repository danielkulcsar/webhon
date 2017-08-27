package com.corea.BasicLocDemo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class BasicLocDemo extends Activity {
    /** Called when the activity is first created. */
	
	LocationManager locManager;							// �����̼� �޴��� ��ü ����// ��ġ������ ��� �����̼� �޴������� ����, ó���Ѵ�.
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String loc_context = Context.LOCATION_SERVICE;		// 
        locManager = (LocationManager)getSystemService(loc_context);
        locationProviders();
    }
    
    public void locationProviders() {
    	TextView tv = (TextView)findViewById(R.id.TextView01);
		
    	/*
    	 1. String ��ü�� �Һ��̱� ������ ������ �ʴ� ���ڿ��� String�� ����Ѵ�. 
		 2. StringBuilder�� �񵿱����̱� ������ Single Thread ȯ���Ͽ���, ��ȭ�Ǵ� ���ڿ��� ����Ѵ�. 
		 3. StringBuffer ���������� ����Ǳ� ������ ��Ƽ������� �����ϰų� ���ڿ��� ����� ��쿡 ����Ѵ�.
    	 */
    	
    	List<String> pvdGroup = locManager.getProviders(true);		// ���� ����Ҽ� �ִ� �����ڸ� ����Ʈ�� �ҷ����δ�.
    	
    	/*
    	public void requestLocationUpdates (String provider, long minTime, float minDistance, LocationListener listener) 
    	provider: ����� ���ι��̴��� �̸�
    	minTime: �� �и��� �������� �����Ұ����� ����
    	minDistance �ּ� ����, ������ ����
    	listener � �����ʰ� �����Ǿ����� ���
    	 */
    	StringBuilder str = new StringBuilder("");
    	for(String pvder : pvdGroup) {									// ������ ����Ʈ�� ũ�⸸ŭ �ݺ�
        	str.append("��ġ ������ ");
    		locManager.requestLocationUpdates
    		       (pvder, 2000, 0, new LocationListener() {				// ���� ����Ҽ��ִ� ���ι��̴��� ������ ����, 1�� �������� ������Ʈ
    		    	   public void onLocationChanged(Location location) {}		// ��ġ ��ȭ����
    		    	   public void onProviderDisabled(String provider) {}		// ���ι��̴� ���� ����
    		    	   public void onProviderEnabled(String provider) {}		// ���ι��̴� Ȱ�� ����
    		    	   public void onStatusChanged								// ���º�ȭ ����
    		    	        (String provider, int status, Bundle extras) {}
    		       });
    		str.append(":").append(pvder).append("\n");						// ���⼭ Ȱ��ȭ�� ��ġ �������� �̸��� �ϳ� �����. ���������� GPS�� ���ð���
    		
    		Location loc = locManager.getLastKnownLocation(pvder);	// ��ġ�޴����κ��� �������� �˷��� ��ġ�� ��ǥ�� �ִ´�.
    		if(loc != null) {												// ���� ������ ��ġ�� �־��ٸ�
    			double lat = loc.getLatitude();      // ����: ����			// �� ��ġ�� �޾ƿ� ���̰�,
    			double lng = loc.getLongitude();     // �浵: ���� 
                        str.append("����:").append(lat).append(", �浵:").append(lng+"\n");	// ǥ������ ���̴�.
    		} else {
    			str.append("��ġ�� ã�� �� ����");									// ���࿡ ��ġ�� ��ã�Ҵٸ�, ��ġ�� ã�� �� ���ٰ� ǥ�õɰ��̴�.
    		}
    		
    	}
    	tv.setText(str); 
    }
}