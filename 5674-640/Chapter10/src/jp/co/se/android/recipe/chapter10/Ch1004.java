package jp.co.se.android.recipe.chapter10;

import java.util.ArrayList;
import java.util.List;

import jp.co.se.android.recipe.chapter10.ParseJson.Leg;
import jp.co.se.android.recipe.chapter10.ParseJson.Route;
import jp.co.se.android.recipe.chapter10.ParseJson.Step;
import jp.co.se.android.recipe.chapter10.RequestDirectionsTask.RequestDirectionsTaskCallback;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class Ch1004 extends FragmentActivity {
    public static final String TRAVEL_MODE = "driving";// default

    GoogleMap mGmap;
    ArrayList<LatLng> mMarkerPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1004_main);

        // 초기화
        mMarkerPoints = new ArrayList<LatLng>();

        // GoogleMap의 인스턴스를 생성
        SupportMapFragment mapfragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mGmap = mapfragment.getMap();

        // 초기 위치를 설정
        LatLng location = new LatLng(34.802556297454004,135.53884506225586);
        mGmap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17));
        mGmap.setMyLocationEnabled(true);

        // 맵을 탭 한 때의 이벤트를 설정
        mGmap.setOnMapClickListener(new OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                // 이미 2 번 탭한 경우, 경로를 클리어 한다
                if (mMarkerPoints.size() >= 2) {
                    mMarkerPoints.clear();
                    mGmap.clear();
                }

                // 탭 한 좌표를 기록한다
                mMarkerPoints.add(point);

                // 탭 한 점에 마커를 추가
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(point);
                if (mMarkerPoints.size() == 1) {
                    markerOptions.title("A");
                } else if (mMarkerPoints.size() == 2) {
                    markerOptions.title("B");
                }
                mGmap.addMarker(markerOptions);

                // 2 번째 탭한 경우, 루트 검색을 시작한다
                if (mMarkerPoints.size() >= 2) {
                    LatLng origin = mMarkerPoints.get(0);
                    LatLng dest = mMarkerPoints.get(1);
                    routeSearch(origin, dest);
                }
            }
        });

        mGmap.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String title = marker.getTitle();
                if (title.equals("A")) {
                    marker.setSnippet("");
                } else if (title.equals("B")) {
                    marker.setSnippet("");
                }

                return false;
            }
        });
    }

    /**
     * 경로를 검색
     * 
     * @param origin
     * @param dest
     */
    private void routeSearch(LatLng origin, LatLng dest) {
        // 진척 다이얼로그를 표시
        ProgressDialogFragment.newInstance("검색 중...").show(
                getSupportFragmentManager(), ProgressDialogFragment.FRG_TAG);

        // 통신을 한다
        RequestDirectionsTask task = new RequestDirectionsTask(this, origin,
                dest, TRAVEL_MODE);
        task.setRequestDirectionsTaskCallback(
                new RequestDirectionsTaskCallback() {

                    @Override
                    public void onSucceed(List<Route> routes) {
                        //  진척 다이얼로그를 닫는다
                        FragmentManager fm = getSupportFragmentManager();
                        ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) fm
                                .findFragmentByTag(ProgressDialogFragment.FRG_TAG);
                        if (progressDialogFragment != null) {
                            progressDialogFragment.dismissAllowingStateLoss();
                        }

                        // 경로 그리기 시작
                        if (routes.size() > 0) {
                            PolylineOptions lineOptions = new PolylineOptions();
                            for (Route route : routes) {
                                for (Leg leg : route.getLegs()) {
                                    for (Step step : leg.getSteps()) {
                                        lineOptions.addAll(step
                                                .getPolylinePoints());
                                        lineOptions.width(10);
                                        lineOptions.color(0x550000ff);
                                    }
                                }
                            }
                            // 그리기
                            mGmap.addPolyline(lineOptions);
                        } else {
                            mGmap.clear();
                            Toast.makeText(Ch1004.this, "경로 정보를 얻을 수 없습니다.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        Toast.makeText(Ch1004.this,
                                "경로 검색 중에 에러가 발생했습니다.(" + e + ")",
                                Toast.LENGTH_LONG).show();
                    }
                }).execute();
    }
}
