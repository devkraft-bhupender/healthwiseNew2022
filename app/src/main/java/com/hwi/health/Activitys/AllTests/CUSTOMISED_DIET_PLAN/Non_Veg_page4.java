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
import android.widget.ScrollView;
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

public class Non_Veg_page4 extends AppCompatActivity implements View.OnClickListener{

    Spinner S_non_veg,S_chicken_without_skin, S_fish, S_beef, S_goat_meat, S_lamb, S_pork;
    ArrayList<Ideal_Diat_Model> non_veg_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> chicken_without_skin_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> fish_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> beef_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> goat_meat_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> lamb_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> pork_list = new ArrayList<>();
    String non_veg, chicken_without_skin, fish, beef, goat_meat, lamb, pork;
    ArrayAdapter<Ideal_Diat_Model> non_veg_adapter,
            chicken_without_skin_adapter, fish_adapter, beef_adapter, goat_meat_adapter, lamb_adapter, pork_adapter;
    ImageView next, prev;
    int number, count_total;
    ActionBar ab;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ScrollView scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("DIET PLAN");
        setContentView(R.layout.activity_non__veg_page3);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        scroll = findViewById(R.id.scroll);
        S_non_veg = (Spinner) findViewById(R.id.non_veg);
        S_chicken_without_skin = (Spinner) findViewById(R.id.chicken_without_skin);
        S_fish = (Spinner) findViewById(R.id.fish);
        S_beef = (Spinner) findViewById(R.id.beef);
        S_goat_meat = (Spinner) findViewById(R.id.goat_meat);
        S_lamb = (Spinner) findViewById(R.id.lamb);
        S_pork = (Spinner) findViewById(R.id.pork);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        spinn();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Non_Veg_page4.this, Type_of_rice_page3.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Type_of_rice_page3.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        if (v == next) {
            count_total = Integer.parseInt(chicken_without_skin) + Integer.parseInt(fish) + Integer.parseInt(beef) + Integer.parseInt(goat_meat) + Integer.parseInt(lamb) + Integer.parseInt(pork);
            Log.e("count_total", count_total + "..." + non_veg);

//            if (count_total == 0){
//                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show();
//            }else {
                new AllSharedPrefrences(Non_Veg_page4.this).Non_Veg(non_veg,chicken_without_skin, fish, beef, goat_meat,lamb,pork);
                if (Integer.parseInt(non_veg) == count_total) {
                    Log.e("count_total", count_total + "..." + non_veg);
                    new MyIntent(Non_Veg_page4.this, Milk_page5.class, R.anim.enter, R.anim.exit);
                }else {
                    scroll.fullScroll(ScrollView.FOCUS_DOWN);
                    Toast.makeText(Non_Veg_page4.this, "Total of individual non veg meals should be equal to Non veg meals/week", Toast.LENGTH_LONG).show();
                    Log.e("count_total", count_total + "..." + non_veg);
                }
//            }

        }
        if (v == prev) {
            new MyIntent(Non_Veg_page4.this, Type_of_rice_page3.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == home_L) {
            new MyIntent(Non_Veg_page4.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Non_Veg_page4.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Non_Veg_page4.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Non_Veg_page4.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Non_Veg_page4.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    void spinn() {
        non_veg_list.add(new Ideal_Diat_Model("Choose no.of meals per week", "0"));
        for (int i = 1; i <= 21; i++) {
            non_veg_list.add(new Ideal_Diat_Model(String.valueOf(i), String.valueOf(i)));
            Log.e("fish_list..", i + "");
        }

        non_veg_adapter = new ArrayAdapter<Ideal_Diat_Model>(Non_Veg_page4.this, android.R.layout.simple_spinner_dropdown_item, non_veg_list);
        S_non_veg.setAdapter(non_veg_adapter);

        S_non_veg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = non_veg_list.get(position);
                non_veg = idm.getId();
                Log.e("non_veg", non_veg + "..");

                if (position != 0){
                    scroll.fullScroll(ScrollView.FOCUS_DOWN);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i = 0; i <= 10; i++) {
            chicken_without_skin_list.add(new Ideal_Diat_Model(String.valueOf(i), String.valueOf(i)));
        }

        chicken_without_skin_adapter = new ArrayAdapter<Ideal_Diat_Model>(Non_Veg_page4.this, android.R.layout.simple_spinner_dropdown_item, chicken_without_skin_list);
        S_chicken_without_skin.setAdapter(chicken_without_skin_adapter);

        S_chicken_without_skin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = chicken_without_skin_list.get(position);
                chicken_without_skin = idm.getId();
                Log.e("non_veg", chicken_without_skin + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        for (int i = 0; i <= 10; i++) {
            fish_list.add(new Ideal_Diat_Model(String.valueOf(i), String.valueOf(i)));
            Log.e("fish_list..", i + "");
        }

        fish_adapter = new ArrayAdapter<Ideal_Diat_Model>(Non_Veg_page4.this, android.R.layout.simple_spinner_dropdown_item, fish_list);
        S_fish.setAdapter(fish_adapter);

        S_fish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = fish_list.get(position);
                fish = idm.getId();
                Log.e("fish", fish + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i = 0; i <= 10; i++) {
            beef_list.add(new Ideal_Diat_Model(String.valueOf(i), String.valueOf(i)));
        }

        beef_adapter = new ArrayAdapter<Ideal_Diat_Model>(Non_Veg_page4.this, android.R.layout.simple_spinner_dropdown_item, beef_list);
        S_beef.setAdapter(beef_adapter);

        S_beef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = beef_list.get(position);
                beef = idm.getId();
                Log.e("fish", beef + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i = 0; i <= 10; i++) {
            goat_meat_list.add(new Ideal_Diat_Model(String.valueOf(i), String.valueOf(i)));
        }

        goat_meat_adapter = new ArrayAdapter<Ideal_Diat_Model>(Non_Veg_page4.this, android.R.layout.simple_spinner_dropdown_item, goat_meat_list);
        S_goat_meat.setAdapter(goat_meat_adapter);

        S_goat_meat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = goat_meat_list.get(position);
                goat_meat = idm.getId();
                Log.e("fish", goat_meat + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        for (int i = 0; i <= 10; i++) {
            lamb_list.add(new Ideal_Diat_Model(String.valueOf(i), String.valueOf(i)));
        }

        lamb_adapter = new ArrayAdapter<Ideal_Diat_Model>(Non_Veg_page4.this, android.R.layout.simple_spinner_dropdown_item, lamb_list);
        S_lamb.setAdapter(lamb_adapter);

        S_lamb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = lamb_list.get(position);
                lamb = idm.getId();
                Log.e("fish", lamb + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        for (int i = 0; i <= 10; i++) {
            pork_list.add(new Ideal_Diat_Model(String.valueOf(i), String.valueOf(i)));
        }

        pork_adapter = new ArrayAdapter<Ideal_Diat_Model>(Non_Veg_page4.this, android.R.layout.simple_spinner_dropdown_item, pork_list);
        S_pork.setAdapter(pork_adapter);

        S_pork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = pork_list.get(position);
                pork = idm.getId();
                Log.e("fish", pork + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
