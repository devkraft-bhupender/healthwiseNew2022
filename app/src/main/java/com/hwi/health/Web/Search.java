package com.hwi.health.Web;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
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

public class Search extends AppCompatActivity implements View.OnClickListener{
    WebView wv;
    ActionBar actionBar;
    String add,keyword;
    EditText get;
    ImageView search;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);
        wv = (WebView) findViewById(R.id.webView2);
     //   search = (ImageView)findViewById(R.id.search);
        get = (EditText) findViewById(R.id.get);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);


//        get.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        get.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    keyword = get.getText().toString();
                            add = "https://www.forhealthyindia.com/en_US/?s="+keyword;
                    lod();
                    return true;
                }
                return false;
            }
        });




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
                    pd = new ProgressDialog(Search.this);
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
        new MyIntent(Search.this, MoreActivity.class,R.anim.enter2,R.anim.exit2);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Search.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Search.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Search.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Search.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Search.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
