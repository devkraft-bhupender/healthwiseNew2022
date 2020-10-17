package com.hwi.health.Activitys.More;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.Calculator_PKG.Alcohol_Tracker;
import com.hwi.health.Activitys.More.Calculator_PKG.BMI_Weighr_Loss;
import com.hwi.health.Activitys.More.Calculator_PKG.Eat_Out_Snacker;
import com.hwi.health.Activitys.More.Calculator_PKG.Family_Oil_Calculator;
import com.hwi.health.Activitys.More.Calculator_PKG.Heart_Risk_Calculator;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

public class Calculator extends AppCompatActivity implements View.OnClickListener {

    LinearLayout bmi_lay, heartcheck_lay, eatout_lay, alchohal_lay, heartrisk_lay, familyoil_lay;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Video Consult");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        bmi_lay = (LinearLayout) findViewById(R.id.bmi_lay);
        heartcheck_lay = (LinearLayout) findViewById(R.id.heartcheck_lay);
        eatout_lay = (LinearLayout) findViewById(R.id.eatout_lay);
        alchohal_lay = (LinearLayout) findViewById(R.id.alchohal_lay);
        heartrisk_lay = (LinearLayout) findViewById(R.id.heartrisk_lay);
        familyoil_lay = (LinearLayout) findViewById(R.id.familyoil_lay);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        bmi_lay.setOnClickListener(this);
        heartcheck_lay.setOnClickListener(this);
        eatout_lay.setOnClickListener(this);
        alchohal_lay.setOnClickListener(this);
        heartrisk_lay.setOnClickListener(this);
        familyoil_lay.setOnClickListener(this);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == bmi_lay) {
            new MyIntent(Calculator.this, BMI_Weighr_Loss.class, R.anim.enter, R.anim.exit);
        }
        if (view == heartcheck_lay) {
            //  new MyIntent(Calculator.this, HeartCheckup.class, R.anim.enter, R.anim.exit);
        }
        if (view == eatout_lay) {
            new MyIntent(Calculator.this, Eat_Out_Snacker.class, R.anim.enter, R.anim.exit);
        }
        if (view == alchohal_lay) {
            new MyIntent(Calculator.this, Alcohol_Tracker.class, R.anim.enter, R.anim.exit);
        }
        if (view == heartrisk_lay) {
            new MyIntent(Calculator.this, Heart_Risk_Calculator.class, R.anim.enter, R.anim.exit);
        }
        if (view == familyoil_lay) {
            new MyIntent(Calculator.this, Family_Oil_Calculator.class, R.anim.enter, R.anim.exit);
        }
        if (view == home_L) {
            new MyIntent(Calculator.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(Calculator.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(Calculator.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(Calculator.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(Calculator.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Calculator.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
    }
}
