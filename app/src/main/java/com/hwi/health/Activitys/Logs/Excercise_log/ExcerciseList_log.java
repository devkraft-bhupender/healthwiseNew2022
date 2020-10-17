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

import com.hwi.health.Adapters.ExcerciesAdapter_Log;
import com.hwi.health.Models.ExcerciseModel;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import java.util.ArrayList;

public class ExcerciseList_log extends AppCompatActivity implements View.OnClickListener{
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ExcerciesAdapter_Log ExcerciesAdapter_Log;
    ListView list;
    ArrayList<ExcerciseModel> elist = new ArrayList<>();
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Exercise Log");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_list);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

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

        elist.add(new ExcerciseModel("Upper Body Exercise","http://img.youtube.com/vi/wulb-euK0g0/0.jpg"));
        elist.add(new ExcerciseModel("Lower Body exercise","http://img.youtube.com/vi/nSOfiYn3v-Q/0.jpg"));
        elist.add(new ExcerciseModel("Full Body exercise","http://img.youtube.com/vi/FR7h1NRSTLI/0.jpg"));
        elist.add(new ExcerciseModel("Library","http://img.youtube.com/vi/fmlcdd1arHM/0.jpg"));

        ExcerciesAdapter_Log = new ExcerciesAdapter_Log(elist,ctx);
        list.setAdapter(ExcerciesAdapter_Log);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // new MyIntent(ExcerciseList_log.this, ExcerciseView_log.class, R.anim.enter, R.anim.exit);
                if (i == 0){
                    new MyIntent(ExcerciseList_log.this, ExcerciseView_log.class, R.anim.enter, R.anim.exit);
                    SharedPreferences sp = getSharedPreferences("Log_url",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Log_url_key","Upper Body Exercise");
                    editor.putString("play_key","1");
                    editor.putString("exercise_type","R");
                    editor.commit();
                }else if (i == 1){
                    new MyIntent(ExcerciseList_log.this, ExcerciseView_log.class, R.anim.enter, R.anim.exit);
                    SharedPreferences sp = getSharedPreferences("Log_url",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Log_url_key","Lower Body exercise");
                    editor.putString("play_key","2");
                    editor.putString("exercise_type","R");
                    editor.commit();
                }else if (i == 2){
                    new MyIntent(ExcerciseList_log.this, ExcerciseView_log.class, R.anim.enter, R.anim.exit);
                    SharedPreferences sp = getSharedPreferences("Log_url",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Log_url_key","Full Body exercise");
                    editor.putString("play_key","3");
                    editor.putString("exercise_type","R");
                    editor.commit();
                }else if (i == 3){
                    new MyIntent(ExcerciseList_log.this, Library_exercise_list.class, R.anim.enter, R.anim.exit);
                    SharedPreferences sp = getSharedPreferences("Log_url",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Log_url_key","Library exercise");
                    editor.putString("play_key","4");
                    editor.putString("exercise_type","R");
                    editor.commit();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(ExcerciseList_log.this, ExcerciseLogActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(ExcerciseList_log.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == profile_L) {
            new MyIntent(ExcerciseList_log.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }if (view == log_L) {
            new MyIntent(ExcerciseList_log.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == plans_L) {
            new MyIntent(ExcerciseList_log.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == more_L) {
            new MyIntent(ExcerciseList_log.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), ExcerciseLogActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
