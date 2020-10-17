package com.hwi.health.Activitys.Plans.Excercize_plans;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hwi.health.Adapters.ExcerciesAdapter;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Models.ExcerciseModel;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

import java.util.ArrayList;

public class ExcerciseList extends AppCompatActivity implements View.OnClickListener{
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ExcerciesAdapter excerciesAdapter;
    ListView list;
    ArrayList<ExcerciseModel> elist = new ArrayList<>();
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Exercise Plans");
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

        elist.add(new ExcerciseModel("Upper Body Exercise","Lorem Ipsum is simply dummy text of the printing and typesetting industry.",R.drawable.excer));
        elist.add(new ExcerciseModel("Lower Body exercise","Lorem Ipsum is simply dummy text of the printing and typesetting industry.",R.drawable.excer));
        elist.add(new ExcerciseModel("Full Body exercise","Lorem Ipsum is simply dummy text of the printing and typesetting industry.",R.drawable.excer));
        elist.add(new ExcerciseModel("Chest exercise","Lorem Ipsum is simply dummy text of the printing and typesetting industry.",R.drawable.excer));

        excerciesAdapter = new ExcerciesAdapter(elist,ctx);
        list.setAdapter(excerciesAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new MyIntent(ExcerciseList.this, ExcerciseView.class, R.anim.enter, R.anim.exit);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(ExcerciseList.this, ExcercisePlanActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(ExcerciseList.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == profile_L) {
            new MyIntent(ExcerciseList.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }if (view == log_L) {
            new MyIntent(ExcerciseList.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == plans_L) {
            new MyIntent(ExcerciseList.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == more_L) {
            new MyIntent(ExcerciseList.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), ExcercisePlanActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
