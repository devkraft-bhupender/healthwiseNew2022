package com.hwi.health.Activitys.AllTests.RISKCALCULATOR;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class Risk_Result extends AppCompatActivity implements View.OnClickListener{

    TextView years_later_20_2, For_people, years_later20, physical_activity, HDL_cholesterol, view_of_some, This_risk_is, yearrisk10, weight_category, bmi;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("CHECK UP FOR RISk");

        setContentView(R.layout.activity_risk__result);
        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        bmi = (TextView) findViewById(R.id.bmi);
        weight_category = (TextView) findViewById(R.id.weight_category);
        yearrisk10 = (TextView) findViewById(R.id.yearrisk10);
        This_risk_is = (TextView) findViewById(R.id.This_risk_is);
        view_of_some = (TextView) findViewById(R.id.view_of_some);
        HDL_cholesterol = (TextView) findViewById(R.id.HDL_cholesterol);
        physical_activity = (TextView) findViewById(R.id.physical_activity);
        years_later20 = (TextView) findViewById(R.id.years_later20);
        For_people = (TextView) findViewById(R.id.For_people);
        years_later_20_2 = (TextView) findViewById(R.id.years_later_20_2);

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        bmi.setText(sp.getString("bmi", ""));
        weight_category.setText(sp.getString("Weight_Category", ""));
        yearrisk10.setText(sp.getString("yearrisk10", ""));
        This_risk_is.setText(sp.getString("This_risk_is", ""));
        view_of_some.setText(sp.getString("", ""));
        HDL_cholesterol.setText(sp.getString("", ""));
        physical_activity.setText(sp.getString("", ""));
        years_later20.setText(sp.getString("year_risk20", ""));
        For_people.setText(sp.getString("", ""));
        years_later_20_2.setText(sp.getString("year_risk20", ""));
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
        new MyIntent(Risk_Result.this, RISK_CALCULATOR_2.class, R.anim.enter2, R.anim.exit2);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), RISK_CALCULATOR_2.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Risk_Result.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Risk_Result.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Risk_Result.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Risk_Result.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Risk_Result.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
