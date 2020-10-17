package com.hwi.health.Activitys.Plans.Excercize_plans;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class ExcercisePlan_View extends AppCompatActivity implements View.OnClickListener{
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Exercise Plans");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_plan__view);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
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

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(ExcercisePlan_View.this, ExcercisePlanActivity.class, R.anim.enter2, R.anim.exit2);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), ExcercisePlanActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(ExcercisePlan_View.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == profile_L) {
            new MyIntent(ExcercisePlan_View.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }if (v == log_L) {
            new MyIntent(ExcercisePlan_View.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == plans_L) {
            new MyIntent(ExcercisePlan_View.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == more_L) {
            new MyIntent(ExcercisePlan_View.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }
}
