package com.lidong.demo.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.lidong.demo.AppComponent;
import com.lidong.demo.BaseActivity;
import com.lidong.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeiXinWebViewActivity extends BaseActivity {

    @Bind(R.id.webView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_web_view);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        final String url = intent.getExtras().getString("url");
        String title = intent.getExtras().getString("title");
        if(!"多图上传".equals(title) && title.length()>12){
            title = title.substring(0,12)+"...";
        }
        Toast.makeText(this,title,Toast.LENGTH_SHORT).show();
        setActivityTitle(title);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
