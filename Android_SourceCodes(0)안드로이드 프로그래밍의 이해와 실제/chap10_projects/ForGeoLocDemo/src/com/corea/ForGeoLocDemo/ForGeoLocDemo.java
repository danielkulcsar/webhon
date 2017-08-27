package com.corea.ForGeoLocDemo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.corea.ForGeoLocDemo.R;

public class ForGeoLocDemo extends Activity {
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.geoaddress);

		final EditText name = (EditText) findViewById(R.id.named);
		final Geocoder coder = new Geocoder(getApplicationContext());
		final TextView coord = (TextView) findViewById(R.id.transform);
		final Button mapButton = (Button) findViewById(R.id.map);

		Button gButton = (Button) findViewById(R.id.gcode);
		
		gButton.setOnClickListener(new View.OnClickListener() {			// ��ư�� ������
			public void onClick(View v) {
				String placeName = name.getText().toString();			// �����̸�: �� �Է��� ���� String���� ��������
				try {
					List<Address> addrList = coder.getFromLocationName(	
							placeName, 3);			
					Iterator<Address> addrs = addrList.iterator();

					String infoAddr = "";
					double lat = 0f;
					double lng = 0f;
					while (addrs.hasNext()) {
						Address loc = addrs.next();
						infoAddr += String.format("��ȯ ��ǥ: %f,%f",
								loc.getLatitude(), loc.getLongitude());
						lat = loc.getLatitude();
						lng = loc.getLongitude();
					}
					coord.setText(infoAddr);

					final String gURIForm = String.format("geo:%f,%f", lat, lng);
					mapButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							Uri gURI = Uri.parse(gURIForm);
							Intent gMapIntent = new Intent(Intent.ACTION_VIEW, gURI);
							startActivity(gMapIntent);
						}
					});
					mapButton.setVisibility(View.VISIBLE);
				} catch (IOException e) {
					Log.e("GeoAddress", "��ġ ���� ���� ����.", e);
				}
			}
		});
	}
}
