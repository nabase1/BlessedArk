package com.nabase1.blessedark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nabase1.blessedark.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ActivityMainBinding mBinding;
    private String url = "https://www.blessedark.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.swipeRefreshList.setOnRefreshListener(this);

        WebSettings myWebsettings = mBinding.webView.getSettings();
        myWebsettings.setJavaScriptEnabled(true);

       loadSite();
    }

    public void loadSite(){
        mBinding.progressBar.setVisibility(View.VISIBLE);
        mBinding.webView.loadUrl(url);
        mBinding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
               // super.onPageStarted(view, url, favicon);
                mBinding.progressBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
              //  super.onPageFinished(view, url);
                mBinding.progressBar.setVisibility(View.GONE);
                mBinding.webView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(mBinding.webView.canGoBack()){
            mBinding.webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRefresh() {
        loadSite();
        mBinding.swipeRefreshList.setRefreshing(false);
    }
}