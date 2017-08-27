package net.npaka.locationex;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MapController;

// 위치 정보 구하기
public class LocationEx extends MapActivity
implements LocationListener {
	private final static String API_KEY=//API 키 (1)
		"[Android Maps API Key]";
	
	private MapView mapView; // 맵 뷰
	private MapController mapCtrl; // 맵 컨트롤러
	private LocationManager lm; // 위치 관리자
	
	// 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// 맵 뷰의 생성
		mapView=new MapView(this,API_KEY);
		setContentView(mapView);
		mapCtrl=mapView.getController();
		mapCtrl.setZoom(16);
	}
	
	// 어플리케이션 시작
	@Override
		public void onStart() {
		super.onStart();
		// 위치 관리자의 설정 (2)
		lm=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);
	}
	
	//어플리케이션 정지
	@Override
		public void onStop() {
		super.onStop();
		// 위치 관리자의 설정 (2)
		lm.removeUpdates(this);
	}
	
	// 경로 표시 여부
	@Override
		protected boolean isRouteDisplayed() {
		return false;
	}
	
	// 위치 정보 갱신 이벤트 처리 (3)
	public void onLocationChanged(Location location) {
		// 위도와 경도 구하기 (4)
		GeoPoint pos=new GeoPoint(
			(int)(location.getLatitude() *1E6),
			(int)(location.getLongitude() *1E6));
		mapCtrl.setCenter(pos);
	}
	
	// 위치 정보 취득 유효화 이벤트의 처리 (3)
	public void onProviderEnabled(String provider) {
	}
	
	// 위치 정보 취득 무효화 이벤트 처리 (3)
	public void onProviderDisabled(String provider) {
	}
	
	// 위치 정보 상태 변경 이벤트 처리 (3)
	public void onStatusChanged(String provider,int status,Bundle extras) {
	}
}
