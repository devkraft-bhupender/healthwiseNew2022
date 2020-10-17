package com.hwi.health.Web;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;

import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.More.MoreActivity;

public class WebActivity extends AppCompatActivity {
    WebView wv;
    ActionBar actionBar;
    String add,keyword;
    EditText get;
    ImageView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);
        wv = (WebView) findViewById(R.id.webView2);
        get = (EditText) findViewById(R.id.get);

        get.setVisibility(View.GONE);

        add ="https://www.healthwiseindian.com/";


        lod();


    }

    void lod(){
        wv.setWebViewClient(new WebViewClient() {
            ProgressDialog pd;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (pd.isShowing()) {
                    pd.cancel();
                }
            }

            @Override
            public void onLoadResource(WebView view, String url) {

                super.onLoadResource(view, url);
                if (pd == null) {
                    pd = new ProgressDialog(WebActivity.this);
                    pd.setMessage("Loading....");
                    pd.show();
                }
            }
        });
        wv.loadUrl(add);
        wv.getSettings().setJavaScriptEnabled(true);

    }


    @Override
    public void onBackPressed() {
        new MyIntent(WebActivity.this, MoreActivity.class,R.anim.enter2,R.anim.exit2);
    }
}
