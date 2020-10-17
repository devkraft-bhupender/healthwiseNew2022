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

public class Food_oil extends AppCompatActivity implements View.OnClickListener{

    Spinner S_food_oil, S_category_one, S_category_two, S_category_three, S_category_four, S_category_five;
    ArrayList<Ideal_Diat_Model> food_oil_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_one_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_two_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_three_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_four_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_five_list = new ArrayList<>();
    ArrayAdapter<Ideal_Diat_Model> food_oil_adapter, category_one_adapter, category_two_adapter, category_three_adapter, category_four_adapter, category_five_adapter;
    String food_oil, category_one, category_two, category_three, category_four, category_five,food_oil_name;
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
        setContentView(R.layout.activity_food_oil);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        S_food_oil = (Spinner) findViewById(R.id.food_oil);
        S_category_one = (Spinner) findViewById(R.id.category_one);
        S_category_two = (Spinner) findViewById(R.id.category_two);
        S_category_three = (Spinner) findViewById(R.id.category_three);
        S_category_four = (Spinner) findViewById(R.id.category_four);
        S_category_five = (Spinner) findViewById(R.id.category_five);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
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
        new MyIntent(Food_oil.this, Milk_page5.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Milk_page5.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        if (v == next) {
            new AllSharedPrefrences(Food_oil.this).Oil(food_oil,category_one,category_two,category_three,category_four,category_five);
            new MyIntent(Food_oil.this, Cereals_page6.class, R.anim.enter, R.anim.exit);
        }
        if (v == prev) {
            new MyIntent(Food_oil.this, Milk_page5.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == home_L) {
            new MyIntent(Food_oil.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Food_oil.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Food_oil.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Food_oil.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Food_oil.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
    void spinn() {
        food_oil_list.add(new Ideal_Diat_Model("Select", "0"));
        food_oil_list.add(new Ideal_Diat_Model("Groundnut/rice bran/sesame+mustard/canola/soyabean", "combo1"));
        food_oil_list.add(new Ideal_Diat_Model("sunflower/safflower+mustard/canola+palm/palmolein", "combo2"));
        food_oil_list.add(new Ideal_Diat_Model("Soyabean+palmolein", "combo3"));
        food_oil_list.add(new Ideal_Diat_Model("Olive+sunflower/safflower", "combo4"));
        food_oil_list.add(new Ideal_Diat_Model("Groundnut/rice bran/sesame+sunflower/safflower", "combo5"));

        food_oil_adapter = new ArrayAdapter<Ideal_Diat_Model>(Food_oil.this, android.R.layout.simple_spinner_dropdown_item, food_oil_list);
        S_food_oil.setAdapter(food_oil_adapter);

        S_food_oil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = food_oil_list.get(position);
                food_oil = idm.getId();
                food_oil_name = idm.getName();
                Log.e("fish", food_oil + "..");
                String data = S_food_oil.getSelectedItem().toString();

                if (data.equals("Groundnut/rice bran/sesame+mustard/canola/soyabean")) {
                    S_category_one.setVisibility(View.VISIBLE);
                    S_category_two.setVisibility(View.VISIBLE);
                    S_category_three.setVisibility(View.GONE);
                    S_category_four.setVisibility(View.GONE);
                    S_category_five.setVisibility(View.GONE);
                } else if (data.equals("sunflower/safflower+mustard/canola+palm/palmolein")) {
                    S_category_two.setVisibility(View.VISIBLE);
                    S_category_three.setVisibility(View.VISIBLE);
                    S_category_four.setVisibility(View.VISIBLE);
                    S_category_one.setVisibility(View.GONE);
                    S_category_five.setVisibility(View.GONE);
                } else if (data.equals("Olive+sunflower/safflower")) {

                    S_category_three.setVisibility(View.VISIBLE);
                    S_category_five.setVisibility(View.VISIBLE);
                    S_category_one.setVisibility(View.GONE);
                    S_category_two.setVisibility(View.GONE);
                    S_category_four.setVisibility(View.GONE);
                } else if (data.equals("Groundnut/rice bran/sesame+sunflower/safflower")) {

                    S_category_three.setVisibility(View.VISIBLE);
                    S_category_one.setVisibility(View.VISIBLE);
                    S_category_two.setVisibility(View.GONE);
                    S_category_four.setVisibility(View.GONE);
                    S_category_five.setVisibility(View.GONE);

                } else {
                    S_category_one.setVisibility(View.GONE);
                    S_category_two.setVisibility(View.GONE);
                    S_category_three.setVisibility(View.GONE);
                    S_category_four.setVisibility(View.GONE);
                    S_category_five.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        category_one_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        category_one_list.add(new Ideal_Diat_Model("Ground nut oil", "1"));
        category_one_list.add(new Ideal_Diat_Model("Rice bran oil", "2"));
        category_one_list.add(new Ideal_Diat_Model("Sesame oil", "3"));

        category_one_adapter = new ArrayAdapter<Ideal_Diat_Model>(Food_oil.this, android.R.layout.simple_spinner_dropdown_item, category_one_list);
        S_category_one.setAdapter(category_one_adapter);

        S_category_one.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = category_one_list.get(position);
                category_one = idm.getId();
                Log.e("fish", category_one + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        category_two_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        category_two_list.add(new Ideal_Diat_Model("Mustard oil", "1"));
        category_two_list.add(new Ideal_Diat_Model("Canola oil", "2"));
        category_two_list.add(new Ideal_Diat_Model("Soybean oil", "3"));

        category_two_adapter = new ArrayAdapter<Ideal_Diat_Model>(Food_oil.this, android.R.layout.simple_spinner_dropdown_item, category_two_list);
        S_category_two.setAdapter(category_two_adapter);

        S_category_two.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = category_two_list.get(position);
                category_two = idm.getId();
                Log.e("fish", category_two + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        category_three_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        category_three_list.add(new Ideal_Diat_Model("Sunflower oil", "1"));
        category_three_list.add(new Ideal_Diat_Model("Safflower oil", "2"));

        category_three_adapter = new ArrayAdapter<Ideal_Diat_Model>(Food_oil.this, android.R.layout.simple_spinner_dropdown_item, category_three_list);
        S_category_three.setAdapter(category_three_adapter);

        S_category_three.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = category_three_list.get(position);
                category_three = idm.getId();
                Log.e("fish", category_three + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        category_four_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        category_four_list.add(new Ideal_Diat_Model("Palm oil", "1"));
        category_four_list.add(new Ideal_Diat_Model("Palmolein oil", "2"));

        category_four_adapter = new ArrayAdapter<Ideal_Diat_Model>(Food_oil.this, android.R.layout.simple_spinner_dropdown_item, category_four_list);
        S_category_four.setAdapter(category_four_adapter);

        S_category_four.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = category_four_list.get(position);
                category_four = idm.getId();
                Log.e("fish", category_four + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        category_five_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        category_five_list.add(new Ideal_Diat_Model("Olive oil", "1"));
        category_five_list.add(new Ideal_Diat_Model("Palm oil", "2"));
        category_five_list.add(new Ideal_Diat_Model("Palmolein oil", "3"));

        category_five_adapter = new ArrayAdapter<Ideal_Diat_Model>(Food_oil.this, android.R.layout.simple_spinner_dropdown_item, category_five_list);
        S_category_five.setAdapter(category_five_adapter);

        S_category_five.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = category_five_list.get(position);
                category_five = idm.getId();
                Log.e("fish", category_five + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
