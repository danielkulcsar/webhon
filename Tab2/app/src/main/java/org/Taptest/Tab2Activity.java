package org.Taptest;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;



public class Tab2Activity extends TabActivity {
	private Activity context;

	private BackPressCloseHandler backPressCloseHandler = new BackPressCloseHandler(this);




	protected static final String MOBILE_NETWORK = "0";
	protected static final String WIFI_NETWORK = "1";
	protected static final String DONOTCHECK_MOBILE_NETWORK = "9";
	boolean isMobile = false;
	boolean isWifi = false;
	String[] choice = { "다시 보지 않기" };
	public string msg=null;

	public class SampleWidgetProvider extends AppWidgetProvider {

		//홈 위젯 설치

	}







	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		startActivity(new Intent(this, SplashActivity.class));

		// addShortcutIcon(); //아이콘 추가

		backPressCloseHandler = new BackPressCloseHandler(this);

		MyThread t1 = new MyThread();
		t1.start();

		TabHost tabHost=getTabHost();
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator("검색",getResources().getDrawable(R.drawable.ic_home_white_18dp))
				.setContent(new Intent(this, Browser1.class)));

		tabHost.addTab(tabHost.newTabSpec("tab2")
				.setIndicator("뉴스", getResources().getDrawable(R.drawable.ic_home_white_18dp))
				.setContent(new Intent(this, Browser2.class)));

		tabHost.addTab(tabHost.newTabSpec("tab3")
				.setIndicator("쇼핑", getResources().getDrawable(R.drawable.ic_home_white_18dp))
				.setContent(new Intent(this, Browser3.class)));

		tabHost.addTab(tabHost.newTabSpec("tab4")
				.setIndicator("옥션", getResources().getDrawable(R.drawable.ic_home_white_18dp))
				.setContent(new Intent(this, Browser4.class)));

		tabHost.addTab(tabHost.newTabSpec("tab5")
				.setIndicator("네이트", getResources().getDrawable(R.drawable.ic_home_white_18dp))
				.setContent(new Intent(this, Browser4.class)));

		tabHost.addTab(tabHost.newTabSpec("tab6")
				.setIndicator("한국",getResources().getDrawable(R.drawable.ic_home_white_18dp))
				.setContent(new Intent(this, Browser1.class)));

		tabHost.addTab(tabHost.newTabSpec("tab7")
				.setIndicator("한국",getResources().getDrawable(R.drawable.ic_home_white_18dp))
				.setContent(new Intent(this, Browser4.class)));

		tabHost.setCurrentTab(0);
		/*
		Intent myintent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("MyWebView"));

		startActivity(myintent);

		*/
		//checkNetwokState().start()


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
		AlertDialog.Builder alert_internet_status = new AlertDialog.Builder(Tab2Activity.
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



	/*
	public boolean isNetworkConnected(Context context){
	    boolean isConnected = false;

	    ConnectivityManager manager =
	        (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

	    if (mobile.isConnected() || wifi.isConnected()){
	        isConnected = true;
	    }else{
	        isConnected = false;
	    }
	    return isConnected;
	}
*/
	/*
	if( !isNetworkConnected(this) ){
	    new AlertDialog.Builder(this)
	    .setIcon(android.R.drawable.ic_dialog_alert)
	    .setTitle("네트워크 연결 오류").setMessage("네트워크 연결 상태 확인 후 다시 시도해 주십시요.")
	    .setPositiveButton("확인", new DialogInterface.OnClickListener()
	    {
	        @Override
	        public void onClick( DialogInterface dialog, int which )
	        {
	            finish();
	        }
	    }).show();
	}





	private void AlertShow(String string) {
		// TODO Auto-generated method stub

	}
*/

	/*
        public boolean checkNetwokState() {
              ConnectivityManager manager =
                 (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
              NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
              NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
              NetworkInfo lte_4g = manager.getNetworkInfo(ConnectivityManager.TYPE_WIMAX);
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

    /*
        public void alert3G() {
            CharSequence[] choice = null;
            new AlertDialog.Builder(this)
                    .setTitle("3G로 접속되어 데이터 통화료가 부과됩니다.")
                    // .setMessage("3G 연결이 필요합니다. 연결시 가입하신 요금제에 따라 데이터 통화료가 부과됩니다. 연결 하시겠습니까?").setCancelable(false)
                    .setMultiChoiceItems(choice, new boolean[] { false },
                            new DialogInterface.OnMultiChoiceClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int whichButton, boolean isChecked) {
                                    saveFile(DONOTCHECK_MOBILE_NETWORK, "network");
                                }

                                private void saveFile(
                                        String donotcheckMobileNetwork,
                                        String string) {
                                    // TODO Auto-generated method stub

                                }
                            })
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dlg, int sumthin) {
                            //
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dlg, int sumthin) {
                            Tab2Activity.this.finish();
                        }
                    }).show();
        }

        // 네트웍 미연결시 alert dialog
        public void alertNetwork() {
            new AlertDialog.Builder(this).setTitle("안내")
                    .setMessage("네트워크 연결에 실패했습니다. 네트워크 연결 확인 후 다시 접속해 주세요.")
                    .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dlg, int sumthin) {
                            Tab2Activity.this.finish();
                        }
                    }).show();
        }
        /*
        public class URLReader{
            URL google = new URL("http://gurudj.woobi.co.kr/count/counter2.php/");
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        google.openStream()));

                //Log.v(TAG,inputLine);
                //in.Close();
        }
        */
	/*
	public boolean isWifiReachable() {
	    ConnectivityManager mManager =
	            (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo current = mManager.getActiveNetworkInfo();
	    if(current == null) {
	        return false;
	    }
	    return (current.getType() == ConnectivityManager.TYPE_WIFI);
	}
*/
	class MyThread extends Thread{
		public void run(){

		}
	}

	private void addShortcutIcon()
	{
		Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
		shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		shortcutIntent.setClassName(this, getClass().getName());
		shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		Intent intent = new Intent();
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
				shortcutIntent);
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				getResources().getString(R.string.app_name));
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(this, R.drawable.ic_filter_white_48dp));
		intent.putExtra("duplicate", false);
		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		sendBroadcast(intent);
	}

	public class BackPressCloseHandler {
		private long backKeyPressedTime = 0;
		private Toast toast;

		private Activity activity;

		public BackPressCloseHandler(Activity context){
			this.activity = context;
		}

		public void onBackPressed(){
			if (System.currentTimeMillis() > backKeyPressedTime + 1500) {
				backKeyPressedTime = System.currentTimeMillis();
				showGuide();
				return;
			}else if (System.currentTimeMillis() <= backKeyPressedTime + 1500) {
				activity.finish();
				toast.cancel();
			}

		}
		public void showGuide(){
			toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
			toast.show();
		}


	}





	public void onBackPressed() {

		backPressCloseHandler.onBackPressed();
	}

}



