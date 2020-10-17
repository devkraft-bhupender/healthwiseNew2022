package com.hwi.health.Activitys.AllTests.BMI;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.Logs.Weight_Log_Now;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class BMI_Result extends AppCompatActivity implements View.OnClickListener {

    TextView target, weight_category, weight_rang, bmi;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Button btn_log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("YOUR BMI AND WEIGHT CATEGORY");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__result);
        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        bmi = (TextView) findViewById(R.id.bmi);
        weight_rang = (TextView) findViewById(R.id.weight_rang);
        weight_category = (TextView) findViewById(R.id.weight_category);
        target = (TextView) findViewById(R.id.target);
        btn_log = (Button) findViewById(R.id.btn_log);
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
        btn_log.setOnClickListener(this);

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        bmi.setText(sp.getString("BMI", ""));
        weight_rang.setText(sp.getString("weight_range", ""));
        weight_category.setText(sp.getString("weight_Category", ""));
        target.setText(sp.getString("weight_target", ""));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(BMI_Result.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), PlansActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(BMI_Result.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == profile_L) {
            new MyIntent(BMI_Result.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }if (v == log_L) {
            new MyIntent(BMI_Result.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == plans_L) {
            new MyIntent(BMI_Result.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == more_L) {
            new MyIntent(BMI_Result.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == btn_log) {
            new MyIntent(BMI_Result.this, Weight_Log_Now.class, R.anim.enter2, R.anim.exit2);
        }
    }
}
