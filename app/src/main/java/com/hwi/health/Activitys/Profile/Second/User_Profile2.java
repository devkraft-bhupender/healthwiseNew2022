package com.hwi.health.Activitys.Profile.Second;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Profile.First.Profile_intent;

public class User_Profile2 extends AppCompatActivity implements View.OnClickListener{
    ImageView current,target,health,heard_risk;
    ImageView userimage, edit_profile;
    //ProgressBar progressBar;
    ProgressDialog pd;
    String randnoo;
    TextView username, user_age_gender;
    TextView adanance_Complete, Complete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile2);

       // current = (ImageView) findViewById(R.id.current);
       // target = (ImageView) findViewById(R.id.target);
        health = (ImageView) findViewById(R.id.health);
        heard_risk = (ImageView) findViewById(R.id.heard_risk);

        userimage = (ImageView) findViewById(R.id.userimage);
        edit_profile = (ImageView) findViewById(R.id.edit_profile);
        username = (TextView) findViewById(R.id.username);
        user_age_gender = (TextView) findViewById(R.id.user_age_gender);
        Complete = (TextView) findViewById(R.id.Complete);
        adanance_Complete = (TextView) findViewById(R.id.adanance_Complete);
        new Profile_intent(User_Profile2.this, Complete).sps();
        current.setOnClickListener(this);
        target.setOnClickListener(this);
        health.setOnClickListener(this);
        heard_risk.setOnClickListener(this);
        Complete.setOnClickListener(this);
        edit_profile.setOnClickListener(this);
        adanance_Complete.setOnClickListener(this);
        User_Profile_Complete userProfileComplete = new User_Profile_Complete(User_Profile2.this);
        userProfileComplete.giveValue();
    }

    @Override
    public void onClick(View v) {
        if (v == current){
            new MyIntent(User_Profile2.this, Current_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == target){
            new MyIntent(User_Profile2.this, Traget_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == health){
            new MyIntent(User_Profile2.this, Health_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == heard_risk){
            new MyIntent(User_Profile2.this, HeardRisk_Profile.class, R.anim.enter, R.anim.exit);
        }
    }
}
