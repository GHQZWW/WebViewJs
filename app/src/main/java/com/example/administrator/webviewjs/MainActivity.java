package com.example.administrator.webviewjs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/**
 * 1.布局
 * 2.权限
 * 3.开启支持JS的操作
 */

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private WebSettings mSettings;
    public String HTML_URL = "http://huixinguiyu.cn/test.html";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView)findViewById(R.id.show_wb);
        mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        mWebView.addJavascriptInterface(new Object(){
            @JavascriptInterface
            public void showToast(String str){
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        },"Android");
    }
    //调用JS暴露的方法,格式固定:webVIew对象.loadUrl("javascript:js方法名(参数)");
    public void call(View v){

        mWebView.loadUrl("javascript:changeInputValue('你真帅')");


    }
    public void load(View v){

        mWebView.loadUrl(HTML_URL);

    }
}
