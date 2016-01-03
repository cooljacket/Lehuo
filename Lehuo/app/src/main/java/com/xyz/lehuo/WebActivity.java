package com.xyz.lehuo;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.xyz.lehuo.bean.Activity;
import com.xyz.lehuo.bean.User;
import com.xyz.lehuo.global.BaseActivity;
import com.xyz.lehuo.global.Conf;
import com.xyz.lehuo.global.MyApplication;

/**
 * Created by xyz on 15/12/30.
 */
public class WebActivity extends BaseActivity implements View.OnClickListener {

    private ImageView col;
    private ImageView like;
    private WebView webView;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initData();
        initWebview();
    }

    private void initView() {
        col = (ImageView) findViewById(R.id.collection);
        like = (ImageView) findViewById(R.id.like);
        webView = (WebView) findViewById(R.id.webview);
    }

    private void initData() {
        activity = (Activity) getIntent().getSerializableExtra("activity");
        if (Conf.isLogin == true) {
            User user = ((MyApplication)getApplication()).getUser();
            if (user.isActivityCollected(activity)) {
                col.setImageResource(R.mipmap.collection_pressed);
            } else {
                col.setImageResource(R.mipmap.collection);
            }
            if (user.isActivityFocused(activity)) {
                like.setImageResource(R.mipmap.like_pressed);
            } else {
                like.setImageResource(R.mipmap.like);
            }
        } else {
            col.setImageResource(R.mipmap.collection);
            like.setImageResource(R.mipmap.like);
        }
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
        webView.loadUrl(activity.getDetailUrl());
    }

    @Override
    public void onClick(View v) {
        if (Conf.isLogin == false) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = ((MyApplication)getApplication()).getUser();
        switch (v.getId()) {
            case R.id.collection:
                if (user.isActivityCollected(activity)) {

                } else {

                }
                break;
            case R.id.like:
                if (user.isActivityFocused(activity)) {

                } else {

                }
                break;
        }
    }
}
