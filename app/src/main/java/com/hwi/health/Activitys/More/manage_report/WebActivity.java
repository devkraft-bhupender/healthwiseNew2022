package com.hwi.health.Activitys.More.manage_report;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.hwi.health.InterFaces.BaseUrl.IMAGEURL;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {
    WebView wv;
    ActionBar actionBar;
    String add, key;
    ImageView image_view;
    String lastOne;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Upload File");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_open__upload__file);
        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        wv = (WebView) findViewById(R.id.webView2);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        image_view = (ImageView) findViewById(R.id.image_view);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        Intent in = getIntent();
        add = in.getStringExtra("URL");
        Log.e("urlllll", add + "");

            try {
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
                            pd.setCancelable(false);
                            pd.show();
                        }
                    }
                });
                wv.getSettings().setJavaScriptEnabled(true);
                String url = IMAGEURL + add;
//                wv.loadUrl(url);
                if (url.endsWith(".pdf")) {
                    try {
                        String urlEncoded = URLEncoder.encode(url, "UTF-8");
                        url = "http://docs.google.com/viewer?url=" + urlEncoded;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                wv.loadUrl(url);

                wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setLoadWithOverviewMode(true);
                wv.getSettings().setUseWideViewPort(true);

                wv.getSettings().setSupportZoom(true);
                wv.getSettings().setBuiltInZoomControls(true);
                wv.getSettings().setDisplayZoomControls(false);

                wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                wv.setScrollbarFadingEnabled(false);
                Log.e("pdffff", url);

            } catch (Exception e) {
                Log.e("errrrrrr", e + "");
            }

        }


    @Override
    public void onBackPressed() {
//        if(wv.canGoBack())
//            wv.goBack();
//        else
//            super.onBackPressed();

        Intent in = new Intent(WebActivity.this, Upload_file_list.class);
        startActivity(in);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Upload_file_list.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(WebActivity.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(WebActivity.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(WebActivity.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(WebActivity.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(WebActivity.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
