package com.hwi.health.Activitys.Plans;

import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.Calculator_PKG.Alcohol_Tracker;
import com.hwi.health.Activitys.More.Calculator_PKG.Eat_Out_Snacker;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class PlansHome_Activity extends AppCompatActivity implements View.OnClickListener{
      ImageView eat_out,alcohol,Health;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("PLAN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_home);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        eat_out = (ImageView) findViewById(R.id.eat_out);
        alcohol = (ImageView) findViewById(R.id.alcohol);
        Health = (ImageView) findViewById(R.id.Health);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        eat_out.setOnClickListener(this);
        alcohol.setOnClickListener(this);
        Health.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(PlansHome_Activity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == profile_L) {
            new MyIntent(PlansHome_Activity.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == log_L) {
            new MyIntent(PlansHome_Activity.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == plans_L) {

        }
        if (v == more_L) {
            new MyIntent(PlansHome_Activity.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }

        if (v == eat_out) {
            SharedPreferences sp = getSharedPreferences("eat_key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Eatkey","PlanKey");
            editor.commit();
            new MyIntent(PlansHome_Activity.this, Eat_Out_Snacker.class, R.anim.enter, R.anim.exit);


        }
        if (v == alcohol) {
            SharedPreferences sp = getSharedPreferences("eat_key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Eatkey","PlanKey");
            editor.commit();
            new MyIntent(PlansHome_Activity.this, Alcohol_Tracker.class, R.anim.enter, R.anim.exit);


        }
        if (v == Health) {

            new MyIntent(PlansHome_Activity.this, PlansActivity.class, R.anim.enter, R.anim.exit);


        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(PlansHome_Activity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
    }
}
