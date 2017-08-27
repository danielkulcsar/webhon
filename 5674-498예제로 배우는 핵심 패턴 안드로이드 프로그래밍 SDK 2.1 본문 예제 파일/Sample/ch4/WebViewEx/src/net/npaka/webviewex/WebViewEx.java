package net.npaka.webviewex;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

//웹 뷰
public class WebViewEx extends Activity {
	private WebView webView;//웹 뷰
	private Handler handler;//핸들러
	
	//어플리케이션 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//핸들러 생성
		handler=new Handler();
		
		//웹 뷰 생성 （1）
		webView=new WebView(this);
		WebSettings settings=webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setSavePassword(false);
		settings.setSaveFormData(false);
		settings.setSupportZoom(false);
		
		//JavaScript인터페이스 생성 (2）
		webView.addJavascriptInterface(new JSInterface(),"demo");
		
		//WebChromeClient클래스의 상속 클래스 생성 (3）
		webView.setWebChromeClient(new ChromeClient());
		
		//HTML 읽기 (4）
		webView.loadUrl("file:///android_asset/sample.html");
		setContentView(webView);
	}
	
	//JavaScript인터페이스 생성 (2）
	public final class JSInterface {
		//생성자
		public JSInterface() {
		}
		
		//JavaScript인터페이스 메소드 처리 （5）
		public void onClick() {
			handler.post(new Runnable() {
				public void run() {
					android.util.Log.e("","changeImage");
					webView.loadUrl("javascript:changeImage()");
				}
			});
		}
	}
	
	//WebChromeClient클래스의 상속 클래스 생성 (3）
	public final class ChromeClient extends WebChromeClient {
		//알람 이벤트 처리
		@Override
			public boolean onJsAlert(WebView view,
			String url,String message,JsResult result) {
			android.util.Log.e("",message);
			result.confirm();
			return true;
		}
	}
}