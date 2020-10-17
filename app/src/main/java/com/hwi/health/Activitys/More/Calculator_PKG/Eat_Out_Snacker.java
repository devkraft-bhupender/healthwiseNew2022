package com.hwi.health.Activitys.More.Calculator_PKG;

import android.content.Context;
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

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

public class Eat_Out_Snacker extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Button next;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Eat Out Planner");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat__out__snacker);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        next = (Button) findViewById(R.id.next);

        SharedPreferences sp = getSharedPreferences("eat_key",MODE_PRIVATE);

        key = sp.getString("Eatkey","");
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
     /*   if (key.equals("PlanKey")){
            new MyIntent(Eat_Out_Snacker.this, PlansHome_Activity.class, R.anim.enter2, R.anim.exit2);
        }
        else*/ if (key.equals("plan_mng_Key")){

            new MyIntent(Eat_Out_Snacker.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        else {
            new MyIntent(Eat_Out_Snacker.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            /*if (key.equals("PlanKey")){
                Intent in = new Intent(getApplicationContext(), PlansHome_Activity.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }else*/ if (key.equals("plan_mng_Key")){
                Intent in = new Intent(getApplicationContext(), PlansActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }
            else {
                Intent in = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(Eat_Out_Snacker.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(Eat_Out_Snacker.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(Eat_Out_Snacker.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(Eat_Out_Snacker.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(Eat_Out_Snacker.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == next) {
            SharedPreferences sp1 = getSharedPreferences("Get_Food_data", Context.MODE_PRIVATE);
            sp1.edit().clear().commit();
            new MyIntent(Eat_Out_Snacker.this, Eat_out_Plan_Details.class, R.anim.enter, R.anim.exit);
        }
    }
}
