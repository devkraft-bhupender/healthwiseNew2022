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

public class Major_Meals_Page1 extends AppCompatActivity implements View.OnClickListener{
    Spinner S_major_meals, S_eat_in_breakfast;
    ArrayList<Ideal_Diat_Model> major_meals_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> breakfast_list = new ArrayList<>();
    ArrayAdapter<Ideal_Diat_Model> major_meals_adapter, breakfastadapter;
    String major_meals, eat_in_breakfast;
    LinearLayout lin_breakfast;
    ImageView next, prev;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("DIET PLAN");
        setContentView(R.layout.activity_major__meals__page1);


        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        S_major_meals = (Spinner) findViewById(R.id.major_meals);
        S_eat_in_breakfast = (Spinner) findViewById(R.id.eat_in_breakfast);
        lin_breakfast = (LinearLayout) findViewById(R.id.breakfast);
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


    void spinn() {

        major_meals_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        major_meals_list.add(new Ideal_Diat_Model("Breakfast,lunch and dinner", "meal1"));
        major_meals_list.add(new Ideal_Diat_Model("Lunch and dinner", "meal2"));

        major_meals_adapter = new ArrayAdapter<Ideal_Diat_Model>(Major_Meals_Page1.this, android.R.layout.simple_spinner_dropdown_item, major_meals_list);
        S_major_meals.setAdapter(major_meals_adapter);
        S_major_meals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_major_meals.getSelectedItem().toString();
                Ideal_Diat_Model idm = major_meals_list.get(position);
                major_meals = idm.getId();

                if (data.equals("Breakfast,lunch and dinner")) {
                    lin_breakfast.setVisibility(View.VISIBLE);
                } else {
                    lin_breakfast.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        breakfast_list.add(new Ideal_Diat_Model("Choose your Activity", "0"));
        breakfast_list.add(new Ideal_Diat_Model("Bread (brown)large", "1"));
        breakfast_list.add(new Ideal_Diat_Model("Bread white large", "2"));
        breakfast_list.add(new Ideal_Diat_Model("Cornflakes", "3"));
        breakfast_list.add(new Ideal_Diat_Model("Muesli", "4"));
        breakfast_list.add(new Ideal_Diat_Model("Oats", "5"));
        breakfast_list.add(new Ideal_Diat_Model("Roti,parantha", "6"));
        breakfastadapter = new ArrayAdapter<Ideal_Diat_Model>(Major_Meals_Page1.this, android.R.layout.simple_spinner_dropdown_item, breakfast_list);
        S_eat_in_breakfast.setAdapter(breakfastadapter);

        S_eat_in_breakfast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = breakfast_list.get(position);
                eat_in_breakfast = idm.getId();
                Log.e("eat_in_breakfast", eat_in_breakfast + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Major_Meals_Page1.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), PlansActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        if (v == next) {
            new AllSharedPrefrences(Major_Meals_Page1.this).Major_Meals(major_meals,eat_in_breakfast);
            new MyIntent(Major_Meals_Page1.this, Grains_dinner_page2.class, R.anim.enter, R.anim.exit);
        }
        if (v == prev) {
            new MyIntent(Major_Meals_Page1.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == home_L) {
            new MyIntent(Major_Meals_Page1.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Major_Meals_Page1.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Major_Meals_Page1.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Major_Meals_Page1.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Major_Meals_Page1.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
