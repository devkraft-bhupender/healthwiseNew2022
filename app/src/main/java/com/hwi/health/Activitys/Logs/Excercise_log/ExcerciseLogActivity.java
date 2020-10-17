package com.hwi.health.Activitys.Logs.Excercise_log;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hwi.health.Adapters.ExcerciesAdapter;
import com.hwi.health.Models.ExcerciseModel;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.Aerobics_log.Aerobics_Exercise_log;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import java.util.ArrayList;

public class ExcerciseLogActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ExcerciesAdapter excerciesAdapter;
    ListView list;
    ArrayList<ExcerciseModel> elist = new ArrayList<>();
    Context ctx;
   String intent_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Exercise Log");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_log);
        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
      try {
          SharedPreferences sp = getSharedPreferences("Excercise_plan",MODE_PRIVATE);
         intent_key = sp.getString("logKey","");
      }catch (Exception e){

      }
        list = (ListView)findViewById(R.id.list);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        ctx = this;
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        elist.add(new ExcerciseModel("Aerobics Exercise","150 minutes of moderate intensity  (or 75 minutes of vigorous) aerobic exercise per week: you can do brisk walk, jogging, swimming, cycling ",R.drawable.walking));
        elist.add(new ExcerciseModel("resistance exercise","2-3 days/week involves all major muscle groups with 48 hour gap, Do the HWI Fullbody workout every alternate day, example: Monday, Wednesday, Friday Give a gap of 48 hours OR Do upper body workout on one day and lower body workout the next day, continue this cycle",R.drawable.res_exs));
        elist.add(new ExcerciseModel("flexibility exercise","2-3 days/week, which can be part of your ‘cool down’ phase of workout or Yoga",R.drawable.flexibl));
        elist.add(new ExcerciseModel("balance exercise","2-3 days/week, especially important for the elderly",R.drawable.man_bal));

        excerciesAdapter = new ExcerciesAdapter(elist,ctx);
        list.setAdapter(excerciesAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  new MyIntent(ExcercisePlanActivity.this, ExcerciseList.class, R.anim.enter, R.anim.exit);
                if (i == 0){
                   new MyIntent(ExcerciseLogActivity.this, Aerobics_Exercise_log.class, R.anim.enter, R.anim.exit);
                }else if (i == 1){
                    new MyIntent(ExcerciseLogActivity.this, ExcerciseList_log.class, R.anim.enter, R.anim.exit);
                }else if (i == 2){
                    new MyIntent(ExcerciseLogActivity.this, ExcerciseLog_View.class, R.anim.enter, R.anim.exit);
                }else if (i == 3){
                    new MyIntent(ExcerciseLogActivity.this, ExcerciseLog_View.class, R.anim.enter, R.anim.exit);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

            new MyIntent(ExcerciseLogActivity.this, LogActivity.class, R.anim.enter2, R.anim.exit2);

    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(ExcerciseLogActivity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == profile_L) {
            new MyIntent(ExcerciseLogActivity.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }if (view == log_L) {
            new MyIntent(ExcerciseLogActivity.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == plans_L) {
            new MyIntent(ExcerciseLogActivity.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == more_L) {
            new MyIntent(ExcerciseLogActivity.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

                Intent in = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
