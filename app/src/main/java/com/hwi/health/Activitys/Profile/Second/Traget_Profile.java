package com.hwi.health.Activitys.Profile.Second;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Traget_Profile extends AppCompatActivity implements View.OnClickListener{
    TextView targe_weight, target_BMI, target_activity;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String tar_weight, weight_loss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Target Profile");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traget__profile);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);



        targe_weight = (TextView) findViewById(R.id.targe_weight);

        target_BMI = (TextView) findViewById(R.id.target_BMI);

        target_activity = (TextView) findViewById(R.id.target_activity);
        try {

            SharedPreferences sp2 = new AllSharedPrefrences(this).UserDataget();

            tar_weight = sp2.getString("weight_range", "");
            weight_loss = sp2.getString("weight_target", "");

            targe_weight.setText(tar_weight);
            target_BMI.setText(weight_loss);
        } catch (Exception e) {
            Log.e("errrrr", e + "");
        }

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Traget_Profile.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), User_Profile.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(Traget_Profile.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(Traget_Profile.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(Traget_Profile.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(Traget_Profile.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(Traget_Profile.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }
}
