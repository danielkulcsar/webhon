package jp.co.se.android.recipe.chapter10;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Ch1002 extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1001_main);
        SupportMapFragment fragment = ((SupportMapFragment) 
getSupportFragmentManager()
                .findFragmentById(R.id.map));
        final GoogleMap gMap = fragment.getMap();
        gMap.setOnMapClickListener(new OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                // 핀을 세우기 위해서 마커를 작성
                MarkerOptions mOptions = new MarkerOptions();
                // 핀의 타이틀을 설정
                mOptions.title(getString(R.string.ch1002_pinpoint));
                // 핀의 스니펫을 설정
                String sLatitude = getString(R.string.ch1002_latitude,point.latitude);
                String sLongitude = getString(R.string.ch1002_longitude,point.longitude);
                mOptions.snippet(sLatitude + "," + sLongitude);
                // 핀 위도 경도를 설정
                mOptions.position(new LatLng(point.latitude, point.longitude));
                // 맵에 마커를 추가
                gMap.addMarker(mOptions);
            }
        });
    }
}
