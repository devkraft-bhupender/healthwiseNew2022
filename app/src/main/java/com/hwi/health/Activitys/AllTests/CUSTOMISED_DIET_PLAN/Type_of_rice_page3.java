package com.hwi.health.Activitys.AllTests.CUSTOMISED_DIET_PLAN;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Type_of_rice_page3 extends AppCompatActivity implements View.OnClickListener{
    Spinner S_rice_type;
    ArrayList<Ideal_Diat_Model> rice_list = new ArrayList<>();
    ArrayAdapter<Ideal_Diat_Model> rice_adapter;
    String rice_type,S_veg_non,key_veg_non;
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
        setContentView(R.layout.activity_type_of_rice_page3);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        S_rice_type = (Spinner) findViewById(R.id.rice_type);
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

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();

            S_veg_non = sp.getString("food_habits", "");


        } catch (Exception e) {
            Log.e("SP erro r =", e + "");
        }

    }
    void spinn() {
        rice_list.add(new Ideal_Diat_Model("Choose your type of Rice", "0"));
        rice_list.add(new Ideal_Diat_Model("Brown rice", "1"));
        rice_list.add(new Ideal_Diat_Model("Short grained", "2"));
        rice_list.add(new Ideal_Diat_Model("Medium grained", "3"));
        rice_list.add(new Ideal_Diat_Model("Long grained", "4"));
        rice_list.add(new Ideal_Diat_Model("Long grained,parboiled", "5"));

        rice_adapter = new ArrayAdapter<Ideal_Diat_Model>(Type_of_rice_page3.this, android.R.layout.simple_spinner_dropdown_item, rice_list);
        S_rice_type.setAdapter(rice_adapter);

        S_rice_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = rice_list.get(position);
                rice_type = idm.getId();
                Log.e("rice_type", rice_type + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Type_of_rice_page3.this, Grains_dinner_page2.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Grains_dinner_page2.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        if (v == next) {
            new AllSharedPrefrences(Type_of_rice_page3.this).Rice(rice_type);

            if (S_veg_non.equals("Non Vegetarian")) {
                new MyIntent(Type_of_rice_page3.this, Non_Veg_page4.class, R.anim.enter, R.anim.exit);
            }else {
                new MyIntent(Type_of_rice_page3.this, Milk_page5.class, R.anim.enter, R.anim.exit);
            }
        }
        if (v == prev) {
            new MyIntent(Type_of_rice_page3.this, Grains_dinner_page2.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == home_L) {
            new MyIntent(Type_of_rice_page3.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Type_of_rice_page3.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Type_of_rice_page3.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Type_of_rice_page3.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Type_of_rice_page3.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
