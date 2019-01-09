package com.test.webviewinterfaceforads.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.test.webviewinterfaceforads.R;
import com.test.webviewinterfaceforads.WebViewInterface;

public class GameActivity extends AppCompatActivity {
    WebView webView;
    WebViewInterface webViewInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
        webViewInterface = new WebViewInterface(
                this,
                webView,
                getIntent().getExtras().getString("gameUrl"));
        webView.addJavascriptInterface(webViewInterface, "Android");
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);


        //webView.loadUrl("file:///android_asset/ninja-ranmaru/index.html"); // load game from local
        //webView.loadUrl("https://html5.gamedistribution.com/405c00612981466cbc5d9dcef4214811/"); // sample game
        //webView.loadUrl("http://10.0.2.2/index.html"); // for run on emulator emulator ip should be 10.0.2.2
    }
}
