package jp.co.se.android.recipe.chapter08;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Ch0809 extends Activity {

    private WebView mWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView = new WebView(this);
        setContentView(mWebView);

        setupWebStorage(mWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://android-recipe.herokuapp.com/samples/ch08/webstorage");
    }

    @SuppressWarnings("deprecation")
    private void setupWebStorage(WebView webView) {
        WebSettings ws = webView.getSettings();

        // 데이터베이스를 유효로 한다
        ws.setDatabaseEnabled(true);
        ws.setDomStorageEnabled(true);

        // 데이터베이스를 저장하는 장소를 지정한다
        if (Build.VERSION_CODES.JELLY_BEAN_MR2 <= Build.VERSION.SDK_INT) {
            File databaseDir = getDir("databases", Context.MODE_PRIVATE);
            if (!databaseDir.exists()) {
                databaseDir.mkdirs();
            }
            ws.setDatabasePath(databaseDir.getPath());
        } else {
            // Android 4.4에서 WebView의 소스 코드가 새롭게 되어서,
            // setDatabasePath 메소드를 호출하는 필요는 없어졌다.
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mWebView.stopLoading();
        ViewGroup webParent = (ViewGroup) mWebView.getParent();
        if (webParent != null) {
            webParent.removeView(mWebView);
        }
        mWebView.destroy();
    }
}
