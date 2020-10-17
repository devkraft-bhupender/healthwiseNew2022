package com.hwi.health.Activitys.Profile.Second;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

public class User_Profile_Next extends AppCompatActivity implements View.OnClickListener {

    LinearLayout home_L,profile_L,log_L,plans_L,more_L;

    LinearLayout healthcheck,healthrisk,diteplan,activityplan,screeningplan,viccinationplan,followupplan,oiluse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("YOUR HEALTH PROFILE");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile__next);

        home_L= (LinearLayout)findViewById(R.id.home);
        profile_L= (LinearLayout)findViewById(R.id.profile);
        log_L= (LinearLayout)findViewById(R.id.log);
        plans_L= (LinearLayout)findViewById(R.id.plans);
        more_L= (LinearLayout)findViewById(R.id.more);
        healthcheck = (LinearLayout) findViewById(R.id.healthcheck);
        healthrisk = (LinearLayout) findViewById(R.id.healthrisk);
        diteplan = (LinearLayout) findViewById(R.id.activityplan);
        activityplan = (LinearLayout) findViewById(R.id.healthcheck);
        screeningplan = (LinearLayout) findViewById(R.id.screeningplan);
        viccinationplan = (LinearLayout) findViewById(R.id.viccinationplan);
        followupplan = (LinearLayout) findViewById(R.id.followupplan);
        oiluse = (LinearLayout) findViewById(R.id.oiluse);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        healthcheck.setOnClickListener(this);
        healthrisk.setOnClickListener(this);
        diteplan.setOnClickListener(this);
        activityplan.setOnClickListener(this);
        screeningplan.setOnClickListener(this);
        viccinationplan.setOnClickListener(this);
        followupplan.setOnClickListener(this);
        oiluse.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(User_Profile_Next.this, User_Profile.class,R.anim.enter2,R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view==home_L){
            new MyIntent(User_Profile_Next.this, HomeActivity.class,R.anim.enter2,R.anim.exit2);
        }if (view==profile_L){

        }if (view==log_L){
            new MyIntent(User_Profile_Next.this, LogActivity.class,R.anim.enter2,R.anim.exit2);
        }if (view==plans_L){
            new MyIntent(User_Profile_Next.this, PlansActivity.class,R.anim.enter2,R.anim.exit2);
        }if (view==more_L){
            new MyIntent(User_Profile_Next.this, MoreActivity.class,R.anim.enter2,R.anim.exit2);
        }if (view==healthcheck){
            new MyIntent(User_Profile_Next.this, Details.class,R.anim.enter,R.anim.exit);
        }if (view==healthrisk){
            new MyIntent(User_Profile_Next.this, Details.class,R.anim.enter,R.anim.exit);
        }if (view==diteplan){
            new MyIntent(User_Profile_Next.this, Details.class,R.anim.enter,R.anim.exit);
        }if (view==activityplan){
            new MyIntent(User_Profile_Next.this, Details.class,R.anim.enter,R.anim.exit);
        }if (view==screeningplan){
            new MyIntent(User_Profile_Next.this, Details.class,R.anim.enter,R.anim.exit);
        }if (view==viccinationplan){
            new MyIntent(User_Profile_Next.this, Details.class,R.anim.enter,R.anim.exit);
        }if (view==followupplan){
            new MyIntent(User_Profile_Next.this, Details.class,R.anim.enter,R.anim.exit);
        }if (view==oiluse){
            new MyIntent(User_Profile_Next.this, Details.class,R.anim.enter,R.anim.exit);
        }
    }
   
}
