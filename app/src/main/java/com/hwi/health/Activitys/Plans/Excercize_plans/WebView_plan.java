package com.hwi.health.Activitys.Plans.Excercize_plans;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.CancerPlan;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Plans.VaccinationPlan;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class WebView_plan extends AppCompatActivity implements View.OnClickListener{
    WebView wv;
    ActionBar actionBar;
    String link, key;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_plan);
        wv = (WebView) findViewById(R.id.webView2);
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


        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            link = sp.getString("link", "");
            key = sp.getString("key", "");
        }catch (Exception e){

        }
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
                    pd = new ProgressDialog(WebView_plan.this);
                    pd.setMessage("Loading....");
                    pd.show();
                }
            }
        });

            wv.loadUrl(link);
            wv.getSettings().setJavaScriptEnabled(true);


    }

    @Override
    public void onBackPressed() {
         if (key.equals("vac")) {
             Intent in = new Intent(WebView_plan.this, VaccinationPlan.class);
             startActivity(in);
             finish();
         }
         else if (key.equals("exc")){
             Intent in = new Intent(WebView_plan.this, ExcercisePlan_Home.class);
             startActivity(in);
             finish();
         }
         else {
             Intent in = new Intent(WebView_plan.this, CancerPlan.class);
             startActivity(in);
             finish();
         }


    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(WebView_plan.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(WebView_plan.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(WebView_plan.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(WebView_plan.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(WebView_plan.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
