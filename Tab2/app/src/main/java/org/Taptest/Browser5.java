package org.Taptest;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Browser5 extends Activity {

	ProgressBar mProgressBar;
	//private String url = "http://m.naver.com";
	WebView browser;
	WebView web;
	//private boolean mActivityInPause = true;

	//DefaultHttpClient client;
	final Activity activity = this;
	ProgressDialog mProgress;

	Intent intent = null;


	/** Called when the activity is first created. */
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebView web = new WebView(this);
		web.setWebViewClient(new CustomWebClient());
		//web.setWebViewClient(new CustomWebClient1());


		//web.setWebChromeClient(new SmartWebChromeClient());

		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		//web.getSettings().setBuiltInZoomControls(true);
		//web.getSettings().setSupportZoom(true);
		//web.getSettings().setSaveFormData(true);

		web.loadUrl("http://m.naver.com");
		setContentView(web);
		// TODO Auto-generated method stub

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
	}



	private void AlertShow(String string) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert_internet_status = new AlertDialog.Builder(Browser5.
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

	}



	private class CustomWebClient extends WebViewClient{

		@Override

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub

			view.loadUrl(url);



			return true;
		}



		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			if (mProgress == null) {
				mProgress = new ProgressDialog(activity);
				mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				mProgress.setTitle("로딩중...");
				mProgress.setMessage("데이터를 읽고 있음...");
				mProgress.setCancelable(false);
				mProgress.setButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						mProgress.dismiss();
					}
				});
				mProgress.show();
			}
		}


		/**
		 * 웹페이지 로딩중 에러가 발생했을때 처리
		 */
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			Toast.makeText(activity, "인터넷 연결이 안되있습니다 " , Toast.LENGTH_SHORT).show();
    		/*
    		AlertDialog.Builder alert_internet_status = new AlertDialog.Builder(Browser1.
                    this);
            alert_internet_status.setTitle("네트워크 연결 오류");
            alert_internet_status.setMessage("Wifi 혹은 3G망이 연결되지 않았거나 원활하지 않습니다.네트워크 확인후 다시 접속해 주세요!");
                  alert_internet_status.setPositiveButton("닫기",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // 닫기
                            finish();
                        }
                    });+ description
    		*/
			finish();


			if (mProgress.isShowing()) {
				mProgress.dismiss();



			}
		}

		/**
		 * 웹페이지 로딩이 끝났을 때 처리
		 */
		public void onPageFinished(WebView view, String url) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
		}




	}


	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
	}

	private void AlertShow1(String string) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert_internet_status = new AlertDialog.Builder(Browser5.
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

	}


}    

