package com.hwi.health.Activitys.Plans;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class PlansCategory extends AppCompatActivity implements View.OnClickListener {

    LinearLayout dinner, lunch, breakfast, snack;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Ideal Daily Diet Plan");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_category);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        breakfast = (LinearLayout) findViewById(R.id.breakfast);
        lunch = (LinearLayout) findViewById(R.id.lunch);
        dinner = (LinearLayout) findViewById(R.id.dinner);
        snack = (LinearLayout) findViewById(R.id.snack);
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

        breakfast.setOnClickListener(this);
        lunch.setOnClickListener(this);
        dinner.setOnClickListener(this);
        snack.setOnClickListener(this);

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
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(PlansCategory.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == breakfast) {
            new MyIntent(PlansCategory.this, Plan_Catge_Details.class, R.anim.enter, R.anim.exit);
        }
        if (view == lunch) {
            new MyIntent(PlansCategory.this, Plan_Catge_Details.class, R.anim.enter, R.anim.exit);
        }
        if (view == dinner) {
            new MyIntent(PlansCategory.this, Plan_Catge_Details.class, R.anim.enter, R.anim.exit);
        }
        if (view == snack) {
            new MyIntent(PlansCategory.this, Plan_Catge_Details.class, R.anim.enter, R.anim.exit);
        }
        if (view == home_L) {
            new MyIntent(PlansCategory.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == profile_L) {
            new MyIntent(PlansCategory.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == log_L) {
            new MyIntent(PlansCategory.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == plans_L) {
            new MyIntent(PlansCategory.this, PlansHome_Activity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(PlansCategory.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
