package com.hwi.health.Activitys.Plans;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.Excercize_plans.WebView_plan;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class CancerResult extends AppCompatActivity implements View.OnClickListener {

    TextView less_f_link, more_f, more_f_f, woman_link;
    LinearLayout more_t;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String key;
    LinearLayout less_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Cancer plan");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancer_result);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        more_t = (LinearLayout) findViewById(R.id.more_t);
        less_f = (LinearLayout) findViewById(R.id.less_f);
        more_f = (TextView) findViewById(R.id.more_f);
        more_f_f = (TextView) findViewById(R.id.more_f_f);
        less_f_link = (TextView) findViewById(R.id.less_f_link);
        woman_link = (TextView) findViewById(R.id.woman_link);

        less_f_link.setText(Html.fromHtml("<u>" + "http://www.sixstepscreening.org/self-exam/" + "</u>"));
        woman_link.setText(Html.fromHtml("<u>" + "http://www.breastcancer.org/symptoms/testing/types/sef_exam/bse_steps" + "</u>"));

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            key = sp.getString("cancer_key", "");
            if (key.equals("age less than 50 and male")) {
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                less_f.setVisibility(View.VISIBLE);

            } else if (key.equals("age more than 50 and male")) {
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                more_f.setVisibility(View.VISIBLE);

            } else if (key.equals("age more 21 and less than 50 and female")) {
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                more_t.setVisibility(View.VISIBLE);

            } else if (key.equals("age more than 50 and female")) {
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                more_f_f.setVisibility(View.VISIBLE);
            }
            Log.e("checkkk",sp.getString("cancer_key", "")+"");
        } catch (Exception e) {
           Log.e("Exception",e+"..");
        }

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        less_f_link.setOnClickListener(this);
        woman_link.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(CancerResult.this, CancerPlan.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), CancerPlan.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(CancerResult.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(CancerResult.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(CancerResult.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(CancerResult.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(CancerResult.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }

        if (view == less_f_link) {
            String get_link = less_f_link.getText().toString();
            new AllSharedPrefrences(CancerResult.this).linkplan(get_link, "can_less");
            new MyIntent(CancerResult.this, WebView_plan.class, R.anim.enter, R.anim.exit);
        }
        if (view == woman_link) {
            String get_link = woman_link.getText().toString();
            new AllSharedPrefrences(CancerResult.this).linkplan(get_link, "can_women");
            new MyIntent(CancerResult.this, WebView_plan.class, R.anim.enter, R.anim.exit);
        }

    }
}
