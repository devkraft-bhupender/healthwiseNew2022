package com.hwi.health.Activitys.More;

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
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class Welcome_page extends AppCompatActivity implements View.OnClickListener{

    Button ok;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String keyy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Thank you");

        setContentView(R.layout.activity_welcome_page);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        ok = (Button) findViewById(R.id.btn);
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
        ok.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("cancer_welcome",MODE_PRIVATE);
        keyy = sp.getString("cancer_keyyy","video_keyyy");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           if (keyy.equals("video_keyyy")) {
               Intent in = new Intent(getApplicationContext(), MoreActivity.class);
               startActivity(in);
               overridePendingTransition(R.anim.enter2, R.anim.exit2);
               finish();
           }else {
               Intent in = new Intent(getApplicationContext(), PlansActivity.class);
               startActivity(in);
               overridePendingTransition(R.anim.enter2, R.anim.exit2);
               finish();
           }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (keyy.equals("video_keyyy")) {
            new MyIntent(Welcome_page.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }else {
            new MyIntent(Welcome_page.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Welcome_page.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Welcome_page.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Welcome_page.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Welcome_page.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Welcome_page.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == ok) {
            if (keyy.equals("video_keyyy")) {
                Intent intent = new Intent(Welcome_page.this, MoreActivity.class);
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(Welcome_page.this, PlansActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
