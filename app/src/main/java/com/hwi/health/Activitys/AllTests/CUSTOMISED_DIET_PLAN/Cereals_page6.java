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

public class Cereals_page6 extends AppCompatActivity implements View.OnClickListener{
   Spinner S_Cereals;
    ArrayList<Ideal_Diat_Model> Cereals_list = new ArrayList<>();
    ArrayAdapter<Ideal_Diat_Model> Cereals_adapter;
    String cereals,get_cereals;
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
        setContentView(R.layout.activity_cereals_page6);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        S_Cereals = (Spinner) findViewById(R.id.Cereals); 
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


        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            get_cereals = sp.getString("cereals", "");
            Log.e("get_cereals",get_cereals);

        }catch (Exception e){

        }
        spinn();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Cereals_page6.this, Food_oil.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Food_oil.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        if (v == next) {
            new AllSharedPrefrences(Cereals_page6.this).Cereals(cereals);
            new MyIntent(Cereals_page6.this, Milk_Break_page7.class, R.anim.enter, R.anim.exit);
        }
        if (v == prev) {
            new MyIntent(Cereals_page6.this, Food_oil.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == home_L) {
            new MyIntent(Cereals_page6.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Cereals_page6.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Cereals_page6.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Cereals_page6.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Cereals_page6.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    void spinn() {
        Cereals_list.add(new Ideal_Diat_Model("Choose One", "0"));
        Cereals_list.add(new Ideal_Diat_Model("Roti and rice : half in each meal", "1"));
        Cereals_list.add(new Ideal_Diat_Model("Rice in lunch and roti in dinner", "2"));

        Cereals_adapter = new ArrayAdapter<Ideal_Diat_Model>(Cereals_page6.this, android.R.layout.simple_spinner_dropdown_item, Cereals_list);
        S_Cereals.setAdapter(Cereals_adapter);
        S_Cereals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_Cereals.getSelectedItem().toString();
                Ideal_Diat_Model idm = Cereals_list.get(position);
                cereals = idm.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
}
