package jp.co.se.android.recipe.chapter08;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class Ch0807 extends Activity {
    private static final String TAG = Ch0807.class.getSimpleName();

    private WebView mWebView;
    private ImageView mFaviconView;
    private TextView mTitleView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupWebIconDatabase();

        setContentView(R.layout.ch0807_main);
        mWebView = (WebView) findViewById(R.id.web);
        mFaviconView = (ImageView) findViewById(R.id.imgFavicon);
        mTitleView = (TextView) findViewById(R.id.textTitle);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitleView.setText(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                Log.d(TAG, "onReceivedIcon");
                mFaviconView.setImageBitmap(icon);
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url,
                    boolean precomposed) {
                Log.d(TAG, "onReceivedTouchIconUrl, url=" + url);
            }
        });
        //mWebView.loadUrl("http://android-recipe.herokuapp.com/samples/ch08/favicon");
        mWebView.loadUrl("http://www.google.com/");
    }

    @SuppressWarnings("deprecation")
    private void setupWebIconDatabase() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // 아이콘이 있는 디렉터리를 구함
            File databasesDir = getDir("icons", Context.MODE_PRIVATE);
            if (!databasesDir.exists()) {
                // 디렉터리가 없으면 생성
                databasesDir.mkdirs();
            }

            // 정상적으로 생성된 경우는 WebIconDatabase의 저장 장소로 지정
            android.webkit.WebIconDatabase.getInstance().open(
                    databasesDir.getAbsolutePath());
            Log.v(TAG, "iconDatabaseDir=" + databasesDir);
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
