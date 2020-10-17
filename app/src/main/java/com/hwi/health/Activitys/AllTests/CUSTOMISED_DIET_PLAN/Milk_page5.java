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
import android.widget.Toast;

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

public class Milk_page5 extends AppCompatActivity implements View.OnClickListener {
    Spinner S_milk_type;
    ArrayList<Ideal_Diat_Model> milk_list = new ArrayList<>();
    ArrayAdapter<Ideal_Diat_Model> milk_adapter;
    String milk_type,S_veg_non;
    ImageView next, prev;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("DIET PLAN");
        setContentView(R.layout.activity_milk_page5);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        S_milk_type = (Spinner) findViewById(R.id.milk_type);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (S_veg_non.equals("Non Vegetarian")) {
            new MyIntent(Milk_page5.this, Non_Veg_page4.class, R.anim.enter2, R.anim.exit2);
        }else {
            new MyIntent(Milk_page5.this, Type_of_rice_page3.class, R.anim.enter2, R.anim.exit2);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (S_veg_non.equals("Non Vegetarian")) {
                Intent in = new Intent(getApplicationContext(), Non_Veg_page4.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }
            else {
                Intent in = new Intent(getApplicationContext(), Type_of_rice_page3.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        if (v == next) {
            if (milk_type.equals("1") || milk_type.equals("5") || milk_type.equals("6")){
                Toast.makeText(this, "Your choice for Milk is not suggested for Ideal diet plan...choose from optons 2,3,4", Toast.LENGTH_LONG).show();
            }
            else {
                new AllSharedPrefrences(Milk_page5.this).Milk(milk_type);
                new MyIntent(Milk_page5.this, Food_oil.class, R.anim.enter, R.anim.exit);
            }
        }
        if (v == prev) {
            if (S_veg_non.equals("Non Vegetarian")) {
                new MyIntent(Milk_page5.this, Non_Veg_page4.class, R.anim.enter2, R.anim.exit2);
            }else {
                new MyIntent(Milk_page5.this, Type_of_rice_page3.class, R.anim.enter2, R.anim.exit2);
            }

        }
        if (v == home_L) {
            new MyIntent(Milk_page5.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Milk_page5.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Milk_page5.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Milk_page5.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Milk_page5.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
    void spinn() {
        milk_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        milk_list.add(new Ideal_Diat_Model("Full Cream", "1"));
        milk_list.add(new Ideal_Diat_Model("Toned", "2"));
        milk_list.add(new Ideal_Diat_Model("Double Toned", "3"));
        milk_list.add(new Ideal_Diat_Model("Skimmed", "4"));
        milk_list.add(new Ideal_Diat_Model("Buffalo's milk", "5"));
        milk_list.add(new Ideal_Diat_Model("Cow's milk", "6"));

        milk_adapter = new ArrayAdapter<Ideal_Diat_Model>(Milk_page5.this, android.R.layout.simple_spinner_dropdown_item, milk_list);
        S_milk_type.setAdapter(milk_adapter);

        S_milk_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
