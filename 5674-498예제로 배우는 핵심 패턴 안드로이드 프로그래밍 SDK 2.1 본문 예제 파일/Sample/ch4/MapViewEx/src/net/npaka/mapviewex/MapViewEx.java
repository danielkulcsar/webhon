package net.npaka.mapviewex;
import android.os.Bundle;
import android.view.Window;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

//맵 뷰
public class MapViewEx extends MapActivity {
	private final static String API_KEY=//API키 ( 구글 홈페이지에서 만들어야 함 )
		"[Android Maps API Key]";
	
	//초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//맵 뷰의 생성 (1）
		MapView mapView=new MapView(this,API_KEY);
		mapView.setClickable(true);
		setContentView(mapView);
		
		//맵 컨트롤러의 작성 (2）
		MapController mc=mapView.getController();
		mc.setCenter(new GeoPoint(
			(int)(37.507*1E6), // 값이 크면 북쪽으로 이동
			(int)(126.867*1E6))); // 값이 크면 동쪽으로 이동
		mc.setZoom(16);
	}
	
	//경로 표시
	@Override
		protected boolean isRouteDisplayed() {
		return false;
	}
}