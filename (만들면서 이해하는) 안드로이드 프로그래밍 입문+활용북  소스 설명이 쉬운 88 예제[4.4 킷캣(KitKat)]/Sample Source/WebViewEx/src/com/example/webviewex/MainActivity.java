package com.example.webviewex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText mEditUrl;
    WebView mWebView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditUrl = (EditText)findViewById(R.id.textUrl);
        mWebView1 = (WebView)findViewById(R.id.webView1);
        mWebView1.setWebViewClient(new WebClient());
        WebSettings webSet = mWebView1.getSettings();
        webSet.setJavaScriptEnabled(true);
        mWebView1.loadUrl("file:///android_asset/index.html");
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnLoad :
            mWebView1.loadUrl( mEditUrl.getText().toString() );
            break;
        case R.id.btnBack :
            if( mWebView1.canGoBack() )
                mWebView1.goBack();
            break;
        case R.id.btnForward :
            if( mWebView1.canGoForward() )
                mWebView1.goForward();
            break;
        case R.id.btnZoomIn :
            mWebView1.zoomIn(); break;
        case R.id.btnZoomOut :
            mWebView1.zoomOut(); break;
        }
    }

    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
