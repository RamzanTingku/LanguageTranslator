package com.example.ramzanullah.languagetranslator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Google extends AppCompatActivity {

    private ProgressBar mprogressBar;
    private WebView mwebView;

    public Google() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);

        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        mwebView = (WebView) findViewById(R.id.webView);

        WebSettings mwebSettings = mwebView.getSettings();

        mwebSettings.setJavaScriptEnabled(true);
        mwebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mwebSettings.setDomStorageEnabled(true);
        mwebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mwebSettings.setUseWideViewPort(true);
        mwebSettings.setSavePassword(true);
        mwebSettings.setSaveFormData(true);
        mwebSettings.setEnableSmoothTransition(true);


        mwebView.loadUrl("https://translate.google.com/");
        mwebView.setWebViewClient(new webviewclient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                mprogressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mprogressBar.setVisibility(View.GONE);
            }
        });

    }


}


class webviewclient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().endsWith("translate.google.com")) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }

}

