package com.example.lsh.webviewtbs;

import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class WebviewVideoActivity extends Activity {
    //    private String url = "https://www.suomusic.com/dryCargoInfo.html?mt=show&id=2";  //测试网址选择1
    private String url = "https://v.qq.com/index.html";//测试网址选择2腾讯视频

    private com.tencent.smtt.sdk.WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_video);
        APIWebviewTBS.getAPIWebview().initTBSActivity(this);   //api借口注册二次封装
        loadWebvieUrl(url);
    }
//    private void initdata() {//获取手机版本信息
//        int tbsVersion = QbSdk.getTbsVersion(this);
//        String TID = QbSdk.getTID();
//        String qBVersion = QbSdk.getMiniQBVersion(this);
//        tvStatus.setText("TbsVersion:" + tbsVersion + "\nTID:" + TID + "\nMiniQBVersion:" + qBVersion);
//    }

    private void loadWebvieUrl(String url) {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView = (com.tencent.smtt.sdk.WebView) findViewById(R.id.webview_wechat);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                progressBar.setVisibility(View.GONE);
                ToastUtil.show("网页加载失败");
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                /*url1*/
//                //编写 javaScript方法  去除webview的 Title
//                String javascript = "javascript:function hideOther() {" +
//                        "document.getElementsByTagName('body')[0].innerHTML;" +
//                        "document.getElementById('app').innerHTML;" +
//                        "document.getElementsByClassName('l-full l-flex-column ssc')[0].innerHTML;" +
//                        "document.getElementsByClassName('ui-head')[0].style.display='none';" +
//                        "document.getElementsByTagName('ui-head-l')[3].style.display='none';" +
//                        "document.getElementsByClassName('ui-head-btn1')[0].style.display='none';" +
//                        "document.getElementsByClassName('ui-head-m')[0].remove();" +
//                        "var divs = document.getElementsByTagName('div');" +
//                        "var lastDiv = divs[divs.length-1];" +
//                        "lastDiv.remove();" +
//                        "document.getElementsByClassName('showme')[0].remove();" +
//                        "document.getElementsByClassName('nei-t3')[1].remove();}";
//
//                //创建方法
//                webView.loadUrl(javascript);
//                //加载方法
//                webView.loadUrl("javascript:hideOther();");
                String javascript = "javascript:function hideOther() {" +
                        "document.getElementsByTagName('body')[0].innerHTML;" +
                        "document.getElementById('app').innerHTML;" +
                        "document.getElementsByClassName('l-full l-flex-column ssc')[0].innerHTML;" +
                        "document.getElementsByClassName('ui-head').style.display='none';" +
                        "document.getElementsByClassName('ui-head-l')[3].style.display='none';" +
                        "document.getElementsByClassName('ui-head-btn1')[0].style.display='none';" +
                        "document.getElementsByClassName('ui-head-m')[0].remove();" +
                        "var divs = document.getElementsByTagName('div');" +
                        "var lastDiv = divs[divs.length-1];" +
                        "lastDiv.remove();" +
                        "document.getElementsByClassName('showme')[0].remove();" +
                        "document.getElementsByClassName('nei-t3')[1].remove();}";

                //创建方法
                webView.loadUrl(javascript);
                //加载方法
                webView.loadUrl("javascript:hideOther();");
            }
        });
        //进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  确保注销配置能够被释放
        if (this.webView != null) {
            webView.destroy();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
