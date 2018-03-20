package com.example.hannahkern.tankup;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by hannahkern on 14.03.18.
 */

public class BlogActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private WebView mWebView;

    public void onPause(){
        super.onPause();
        Log.i("onPause = ", "onPause: ");
    }

    public int getLayoutResource() {
        return R.layout.activity_main5;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://www.autonews.com");
    }
}
