package com.xyz.lehuo.first;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.xyz.lehuo.R;
import com.xyz.lehuo.global.BaseActivity;

/**
 * Created by xyz on 15/12/30.
 */
public class WebActivity extends BaseActivity {

    private ImageView col;
    private ImageView like;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initWebview();
    }

    private void initView() {
        col = (ImageView) findViewById(R.id.collection);
        like = (ImageView) findViewById(R.id.like);
        webView = (WebView) findViewById(R.id.webview);
    }

    private void initWebview() {
        webView.setFocusable(true);
        webView.requestFocus(View.FOCUS_DOWN);
        webView.requestFocusFromTouch();
        webView.setVerticalScrollBarEnabled(true);
        WebSettings settings = webView.getSettings();
        settings.setSupportMultipleWindows(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient());
    }
}
