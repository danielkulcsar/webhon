package org.Taptest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {


	protected Activity Tap2Activity;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Handler handler = new Handler () {
			@Override
			public void handleMessage(Message msg) {
				finish();
			}
		};
        /*
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiAvail = ni.isAvailable();
        boolean isWifiConn = ni.isConnected();
        ni = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileAvail = ni.isAvailable();
        boolean isMobileConn = ni.isConnected();

        String status = "WiFi\nAvail = " + isWifiAvail + "\nConn = "
          + isWifiConn + "\nMobile\nAvail = " + isMobileAvail
          + "\nConn = " + isMobileConn + "\n";

        if (!isWifiConn && !isMobileConn) {
            AlertShow("Wifi 혹은 3G망이 연결되지 않았거나 원활하지 않습니다.네트워크 확인후 다시 접속해 주세요!");
        }
        */
		handler.sendEmptyMessageDelayed(0, 3000);


	}


	public boolean checkNetwokState() {
		ConnectivityManager manager =
				(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo lte_4g = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		boolean blte_4g = false;
		if(lte_4g != null)
			blte_4g = lte_4g.isConnected();
		if (mobile.isConnected() || wifi.isConnected() || blte_4g)
			return true;
		else {
			AlertDialog.Builder dlg = new AlertDialog.Builder(SplashActivity.this);
			dlg.setTitle("네트워크 오류");
			dlg.setMessage("네트워크 상태를 확인해 주십시요.");
			dlg.setIcon(R.drawable.icon);
			dlg.setNegativeButton("종료", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					finish();  // 끝내버렷
				}
			});
			dlg.setCancelable(false);
			dlg.show();
			return false;
		}
	}
}

    /*
    private void AlertShow(String string) {
		// TODO Auto-generated method stub
		 AlertDialog.Builder alert_internet_status = new AlertDialog.Builder(
                    this);
            alert_internet_status.setTitle("네트워크 연결 오류");
            alert_internet_status.setMessage(string);
            alert_internet_status.setPositiveButton("닫기",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // 닫기
                        finish();
                        }


                    });
            //alert_internet_status.show();
        }


}

/*
public void alertNetwork() {
	new AlertDialog.Builder(this).setTitle("안내")
			.setMessage("네트워크 연결에 실패했습니다. 네트워크 연결 확인 후 다시 접속해 주세요.")
			.setNeutralButton("닫기", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dlg, int sumthin) {
					Tab2Activity.this.finish();
				}
			}).show();

*/