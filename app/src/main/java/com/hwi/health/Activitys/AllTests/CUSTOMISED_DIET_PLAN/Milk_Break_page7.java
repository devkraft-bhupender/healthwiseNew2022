package com.hwi.health.Activitys.AllTests.CUSTOMISED_DIET_PLAN;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hwi.health.Models.Ideal_Diat_Model;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import java.util.ArrayList;

public class Milk_Break_page7 extends AppCompatActivity implements View.OnClickListener{

    Spinner S_milk;
    ArrayList<Ideal_Diat_Model> milk_list = new ArrayList<>();
    ArrayAdapter<Ideal_Diat_Model> milk_adapter;
    String milk_type;
    ImageView next, prev;
    ActionBar ab;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("DIET PLAN");
        setContentView(R.layout.activity_milk__break_page7);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        S_milk = (Spinner) findViewById(R.id.Milk);

        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        spinn();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Milk_Break_page7.this, Cereals_page6.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Cereals_page6.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        if (v == next) {
            new AllSharedPrefrences(Milk_Break_page7.this).Break_milk(milk_type);
            new MyIntent(Milk_Break_page7.this, Vegetables_page8.class, R.anim.enter, R.anim.exit);
        }
        if (v == prev) {
            new MyIntent(Milk_Break_page7.this, Cereals_page6.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == home_L) {
            new MyIntent(Milk_Break_page7.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Milk_Break_page7.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Milk_Break_page7.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Milk_Break_page7.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Milk_Break_page7.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    void spinn() {
        milk_list.add(new Ideal_Diat_Model("Choose One", "0"));
        milk_list.add(new Ideal_Diat_Model("Breakfast and evening snack: half in each meal", "1"));
        milk_list.add(new Ideal_Diat_Model("Breakfast,lunch and dinner: one third in each meal", "2"));

        milk_adapter = new ArrayAdapter<Ideal_Diat_Model>(Milk_Break_page7.this, android.R.layout.simple_spinner_dropdown_item, milk_list);
        S_milk.setAdapter(milk_adapter);

        S_milk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = milk_list.get(position);
                milk_type = idm.getId();
                Log.e("fish", milk_type + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
