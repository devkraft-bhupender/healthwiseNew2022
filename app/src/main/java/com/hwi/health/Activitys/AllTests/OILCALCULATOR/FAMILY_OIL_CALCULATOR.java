package com.hwi.health.Activitys.AllTests.OILCALCULATOR;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hwi.health.Models.Ideal_Diat_Model;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import java.util.ArrayList;

public class FAMILY_OIL_CALCULATOR extends AppCompatActivity implements View.OnClickListener{


    Spinner S_Family_memebrs, S_ADULT_or_CHILD1, S_ADULT_or_CHILD2, S_ADULT_or_CHILD3, S_ADULT_or_CHILD4, S_ADULT_or_CHILD5, S_ADULT_or_CHILD6,
            S_ADULT_or_CHILD7, S_ADULT_or_CHILD8, S_ADULT_or_CHILD9, S_ADULT_or_CHILD10;

    Spinner S_gender1, S_gender2, S_gender3, S_gender4, S_gender5, S_gender6, S_gender7, S_gender8, S_gender9, S_gender10;
    Spinner S_weight1, S_weight2, S_weight3, S_weight4, S_weight5, S_weight6, S_weight7, S_weight8, S_weight9, S_weight10;
    Spinner S_vegetarian1, S_vegetarian2, S_vegetarian3, S_vegetarian4, S_vegetarian5, S_vegetarian6, S_vegetarian7, S_vegetarian8,
            S_vegetarian9, S_vegetarian10;

    Spinner S_milk1, S_milk2, S_milk3, S_milk4, S_milk5, S_milk6, S_milk7, S_milk8, S_milk9, S_milk10;
    Spinner S_Exercise1, S_Exercise2, S_Exercise3, S_Exercise4, S_Exercise5, S_Exercise6, S_Exercise7, S_Exercise8, S_Exercise9, S_Exercise10;
    Spinner S_Occupation1, S_Occupation2, S_Occupation3, S_Occupation4, S_Occupation5,
            S_Occupation6, S_Occupation7, S_Occupation8, S_Occupation9, S_Occupation10;

    ArrayAdapter<Ideal_Diat_Model> member_adapter;
    ArrayList<Ideal_Diat_Model> member_list = new ArrayList<>();

    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linear_main;
    LinearLayout activity_level1, activity_level2, activity_level3, activity_level4,
            activity_level5, activity_level6, activity_level7, activity_level8, activity_level9, activity_level10;
    ArrayAdapter<String> ADULT_or_CHILD_adapter;
    ArrayList<String> ADULT_or_CHILD_list = new ArrayList<>();

    ArrayAdapter<Ideal_Diat_Model> gender_adapter;
    ArrayList<Ideal_Diat_Model> gender_list = new ArrayList<>();

    ArrayAdapter<Ideal_Diat_Model> weight_adapter;
    ArrayList<Ideal_Diat_Model> weight_list = new ArrayList<>();

    ArrayAdapter<Ideal_Diat_Model> vegetarian_adapter;
    ArrayList<Ideal_Diat_Model> vegetarian_list = new ArrayList<>();

    ArrayAdapter<Ideal_Diat_Model> milk_adapter;
    ArrayList<Ideal_Diat_Model> milk_list = new ArrayList<>();

    ArrayAdapter<Ideal_Diat_Model> Exercise_adapter;
    ArrayList<Ideal_Diat_Model> Exercise_list = new ArrayList<>();

    ArrayAdapter<Ideal_Diat_Model> Occupation_adapter;
    ArrayList<Ideal_Diat_Model> Occupation_list = new ArrayList<>();
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("OIL CALCULATOR");

        setContentView(R.layout.family__oil__calculator);
//back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        Idfind();
        spinn();
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);


    }

    void spinn() {

        member_list.add(new Ideal_Diat_Model("Tell us your famliy size", "0"));
        member_list.add(new Ideal_Diat_Model("1", "1fam"));
        member_list.add(new Ideal_Diat_Model("2", "2fam"));
        member_list.add(new Ideal_Diat_Model("3", "3fam"));
        member_list.add(new Ideal_Diat_Model("4", "4fam"));
        member_list.add(new Ideal_Diat_Model("5", "5fam"));
        member_list.add(new Ideal_Diat_Model("6", "6fam"));
        member_list.add(new Ideal_Diat_Model("7", "7fam"));
        member_list.add(new Ideal_Diat_Model("8", "8fam"));
        member_list.add(new Ideal_Diat_Model("9", "9fam"));
        member_list.add(new Ideal_Diat_Model("10", "10fam"));

        member_adapter = new ArrayAdapter<Ideal_Diat_Model>(FAMILY_OIL_CALCULATOR.this, android.R.layout.simple_spinner_dropdown_item, member_list);
        S_Family_memebrs.setAdapter(member_adapter);
        S_Family_memebrs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_Family_memebrs.getSelectedItem().toString();
                if (data.equals("1")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else if (data.equals("2")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else if (data.equals("3")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else if (data.equals("4")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else if (data.equals("5")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else if (data.equals("6")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    linearLayout6.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else if (data.equals("7")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    linearLayout6.setVisibility(View.VISIBLE);
                    linearLayout7.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else if (data.equals("8")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    linearLayout6.setVisibility(View.VISIBLE);
                    linearLayout7.setVisibility(View.VISIBLE);
                    linearLayout8.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else if (data.equals("9")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    linearLayout6.setVisibility(View.VISIBLE);
                    linearLayout7.setVisibility(View.VISIBLE);
                    linearLayout8.setVisibility(View.VISIBLE);
                    linearLayout9.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else if (data.equals("10")) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    linearLayout6.setVisibility(View.VISIBLE);
                    linearLayout7.setVisibility(View.VISIBLE);
                    linearLayout8.setVisibility(View.VISIBLE);
                    linearLayout9.setVisibility(View.VISIBLE);
                    linearLayout10.setVisibility(View.VISIBLE);
                    linear_main.setVisibility(View.VISIBLE);
                } else {
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                    linearLayout5.setVisibility(View.GONE);
                    linearLayout6.setVisibility(View.GONE);
                    linearLayout7.setVisibility(View.GONE);
                    linearLayout8.setVisibility(View.GONE);
                    linearLayout9.setVisibility(View.GONE);
                    linearLayout10.setVisibility(View.GONE);
                    linear_main.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gender_list.add(new Ideal_Diat_Model("Select your category", "0"));
        gender_list.add(new Ideal_Diat_Model("Male", "Male"));
        gender_list.add(new Ideal_Diat_Model("Female", "Female"));
        gender_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, gender_list);
        S_gender1.setAdapter(gender_adapter);
        S_gender2.setAdapter(gender_adapter);
        S_gender3.setAdapter(gender_adapter);
        S_gender4.setAdapter(gender_adapter);
        S_gender5.setAdapter(gender_adapter);
        S_gender6.setAdapter(gender_adapter);
        S_gender7.setAdapter(gender_adapter);
        S_gender8.setAdapter(gender_adapter);
        S_gender9.setAdapter(gender_adapter);
        S_gender10.setAdapter(gender_adapter);

        ADULT_or_CHILD_list.add("Select your category");
        ADULT_or_CHILD_list.add("Adult");
        ADULT_or_CHILD_list.add("Child");
        ADULT_or_CHILD_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ADULT_or_CHILD_list);
        S_ADULT_or_CHILD1.setAdapter(ADULT_or_CHILD_adapter);
        S_ADULT_or_CHILD2.setAdapter(ADULT_or_CHILD_adapter);
        S_ADULT_or_CHILD3.setAdapter(ADULT_or_CHILD_adapter);
        S_ADULT_or_CHILD4.setAdapter(ADULT_or_CHILD_adapter);
        S_ADULT_or_CHILD5.setAdapter(ADULT_or_CHILD_adapter);
        S_ADULT_or_CHILD6.setAdapter(ADULT_or_CHILD_adapter);
        S_ADULT_or_CHILD7.setAdapter(ADULT_or_CHILD_adapter);
        S_ADULT_or_CHILD8.setAdapter(ADULT_or_CHILD_adapter);
        S_ADULT_or_CHILD9.setAdapter(ADULT_or_CHILD_adapter);
        S_ADULT_or_CHILD10.setAdapter(ADULT_or_CHILD_adapter);

        S_ADULT_or_CHILD1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD1.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level1.setVisibility(View.VISIBLE);

                } else {
                    activity_level1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        S_ADULT_or_CHILD2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD2.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level2.setVisibility(View.VISIBLE);

                } else {
                    activity_level2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        S_ADULT_or_CHILD3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD3.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level3.setVisibility(View.VISIBLE);

                } else {
                    activity_level3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        S_ADULT_or_CHILD4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD4.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level4.setVisibility(View.VISIBLE);

                } else {
                    activity_level4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        S_ADULT_or_CHILD5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD5.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level5.setVisibility(View.VISIBLE);

                } else {
                    activity_level5.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        S_ADULT_or_CHILD6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD6.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level6.setVisibility(View.VISIBLE);

                } else {
                    activity_level6.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        S_ADULT_or_CHILD7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD7.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level7.setVisibility(View.VISIBLE);

                } else {
                    activity_level7.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        S_ADULT_or_CHILD8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD8.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level8.setVisibility(View.VISIBLE);

                } else {
                    activity_level8.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        S_ADULT_or_CHILD9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD9.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level9.setVisibility(View.VISIBLE);

                } else {
                    activity_level9.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        S_ADULT_or_CHILD10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = S_ADULT_or_CHILD10.getSelectedItem().toString();

                if (data.equals("Adult") || data.equals("Child")) {
                    activity_level10.setVisibility(View.VISIBLE);

                } else {
                    activity_level10.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        weight_list.add(new Ideal_Diat_Model("Select your category", "0"));
        weight_list.add(new Ideal_Diat_Model("Yes", "yes1"));
        weight_list.add(new Ideal_Diat_Model("No", "no1"));
        weight_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, weight_list);
        S_weight1.setAdapter(weight_adapter);
        S_weight2.setAdapter(weight_adapter);
        S_weight3.setAdapter(weight_adapter);
        S_weight4.setAdapter(weight_adapter);
        S_weight5.setAdapter(weight_adapter);
        S_weight6.setAdapter(weight_adapter);
        S_weight7.setAdapter(weight_adapter);
        S_weight8.setAdapter(weight_adapter);
        S_weight9.setAdapter(weight_adapter);
        S_weight10.setAdapter(weight_adapter);

        vegetarian_list.add(new Ideal_Diat_Model("Select your category", "0"));
        vegetarian_list.add(new Ideal_Diat_Model("Vegetarian ,who does eat eggs", "1"));
        vegetarian_list.add(new Ideal_Diat_Model("Vegetarian ,who does not eat egg", "2"));
        vegetarian_list.add(new Ideal_Diat_Model("Non-vegetarian", "3"));
        vegetarian_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, vegetarian_list);
        S_vegetarian1.setAdapter(vegetarian_adapter);
        S_vegetarian2.setAdapter(vegetarian_adapter);
        S_vegetarian3.setAdapter(vegetarian_adapter);
        S_vegetarian4.setAdapter(vegetarian_adapter);
        S_vegetarian5.setAdapter(vegetarian_adapter);
        S_vegetarian6.setAdapter(vegetarian_adapter);
        S_vegetarian7.setAdapter(vegetarian_adapter);
        S_vegetarian8.setAdapter(vegetarian_adapter);
        S_vegetarian9.setAdapter(vegetarian_adapter);
        S_vegetarian10.setAdapter(vegetarian_adapter);

        milk_list.add(new Ideal_Diat_Model("Select your category", "0"));
        milk_list.add(new Ideal_Diat_Model("Toned", "Toned"));
        milk_list.add(new Ideal_Diat_Model("Double Toned", "Double Toned"));
        milk_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, milk_list);
        S_milk1.setAdapter(milk_adapter);
        S_milk2.setAdapter(milk_adapter);
        S_milk3.setAdapter(milk_adapter);
        S_milk4.setAdapter(milk_adapter);
        S_milk5.setAdapter(milk_adapter);
        S_milk6.setAdapter(milk_adapter);
        S_milk7.setAdapter(milk_adapter);
        S_milk8.setAdapter(milk_adapter);
        S_milk9.setAdapter(milk_adapter);
        S_milk10.setAdapter(milk_adapter);

        Exercise_list.add(new Ideal_Diat_Model("Choose your Activity", "0"));
        Exercise_list.add(new Ideal_Diat_Model("No regular exercise", "1"));
        Exercise_list.add(new Ideal_Diat_Model("Exercise>20 min,1-3 days/week", "2"));
        Exercise_list.add(new Ideal_Diat_Model("Exercise 30-60 min,3-4 days/week", "3"));
        Exercise_list.add(new Ideal_Diat_Model("Exercise>60 min, 5-7 days/week", "4"));
        Exercise_list.add(new Ideal_Diat_Model("Athlete or very active", "5"));

        Exercise_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, Exercise_list);
        S_Exercise1.setAdapter(Exercise_adapter);
        S_Exercise2.setAdapter(Exercise_adapter);
        S_Exercise3.setAdapter(Exercise_adapter);
        S_Exercise4.setAdapter(Exercise_adapter);
        S_Exercise5.setAdapter(Exercise_adapter);
        S_Exercise6.setAdapter(Exercise_adapter);
        S_Exercise7.setAdapter(Exercise_adapter);
        S_Exercise8.setAdapter(Exercise_adapter);
        S_Exercise9.setAdapter(Exercise_adapter);
        S_Exercise10.setAdapter(Exercise_adapter);

        Occupation_list.add(new Ideal_Diat_Model("Choose your Activity", "0"));
        Occupation_list.add(new Ideal_Diat_Model("Sitting /desk jobs", "1"));
        Occupation_list.add(new Ideal_Diat_Model("Standing  for long times", "2"));
        Occupation_list.add(new Ideal_Diat_Model("Active e.g. waiter", "3"));
        Occupation_list.add(new Ideal_Diat_Model("Heavy activity, e.g. carpenter", "4"));
        Occupation_list.add(new Ideal_Diat_Model("Strenuous e.g.Labourers .", "5"));
        Occupation_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, Occupation_list);
        S_Occupation1.setAdapter(Occupation_adapter);
        S_Occupation2.setAdapter(Occupation_adapter);
        S_Occupation3.setAdapter(Occupation_adapter);
        S_Occupation4.setAdapter(Occupation_adapter);
        S_Occupation5.setAdapter(Occupation_adapter);
        S_Occupation6.setAdapter(Occupation_adapter);
        S_Occupation7.setAdapter(Occupation_adapter);
        S_Occupation8.setAdapter(Occupation_adapter);
        S_Occupation9.setAdapter(Occupation_adapter);
        S_Occupation10.setAdapter(Occupation_adapter);

    }


    void Idfind() {
        S_Family_memebrs = (Spinner) findViewById(R.id.Family_memebrs);

        S_ADULT_or_CHILD1 = (Spinner) findViewById(R.id.ADULT_or_CHILD);
        S_ADULT_or_CHILD2 = (Spinner) findViewById(R.id.ADULT_or_CHILD2);
        S_ADULT_or_CHILD3 = (Spinner) findViewById(R.id.ADULT_or_CHILD3);
        S_ADULT_or_CHILD4 = (Spinner) findViewById(R.id.ADULT_or_CHILD4);
        S_ADULT_or_CHILD5 = (Spinner) findViewById(R.id.ADULT_or_CHILD5);
        S_ADULT_or_CHILD6 = (Spinner) findViewById(R.id.ADULT_or_CHILD6);
        S_ADULT_or_CHILD7 = (Spinner) findViewById(R.id.ADULT_or_CHILD7);
        S_ADULT_or_CHILD8 = (Spinner) findViewById(R.id.ADULT_or_CHILD8);
        S_ADULT_or_CHILD9 = (Spinner) findViewById(R.id.ADULT_or_CHILD9);
        S_ADULT_or_CHILD10 = (Spinner) findViewById(R.id.ADULT_or_CHILD10);

        S_gender1 = (Spinner) findViewById(R.id.gender1);
        S_gender2 = (Spinner) findViewById(R.id.gender2);
        S_gender3 = (Spinner) findViewById(R.id.gender3);
        S_gender4 = (Spinner) findViewById(R.id.gender4);
        S_gender5 = (Spinner) findViewById(R.id.gender5);
        S_gender6 = (Spinner) findViewById(R.id.gender6);
        S_gender7 = (Spinner) findViewById(R.id.gender7);
        S_gender8 = (Spinner) findViewById(R.id.gender8);
        S_gender9 = (Spinner) findViewById(R.id.gender9);
        S_gender10 = (Spinner) findViewById(R.id.gender10);

        S_weight1 = (Spinner) findViewById(R.id.weight_loss);
        S_weight2 = (Spinner) findViewById(R.id.weight_loss2);
        S_weight3 = (Spinner) findViewById(R.id.weight_loss3);
        S_weight4 = (Spinner) findViewById(R.id.weight_loss4);
        S_weight5 = (Spinner) findViewById(R.id.weight_loss5);
        S_weight6 = (Spinner) findViewById(R.id.weight_loss6);
        S_weight7 = (Spinner) findViewById(R.id.weight_loss7);
        S_weight8 = (Spinner) findViewById(R.id.weight_loss8);
        S_weight9 = (Spinner) findViewById(R.id.weight_loss9);
        S_weight10 = (Spinner) findViewById(R.id.weight_loss10);

        S_vegetarian1 = (Spinner) findViewById(R.id.veg_non_vegetarian);
        S_vegetarian2 = (Spinner) findViewById(R.id.veg_non_vegetarian2);
        S_vegetarian3 = (Spinner) findViewById(R.id.veg_non_vegetarian3);
        S_vegetarian4 = (Spinner) findViewById(R.id.veg_non_vegetarian4);
        S_vegetarian5 = (Spinner) findViewById(R.id.veg_non_vegetarian5);
        S_vegetarian6 = (Spinner) findViewById(R.id.veg_non_vegetarian6);
        S_vegetarian7 = (Spinner) findViewById(R.id.veg_non_vegetarian7);
        S_vegetarian8 = (Spinner) findViewById(R.id.veg_non_vegetarian8);
        S_vegetarian9 = (Spinner) findViewById(R.id.veg_non_vegetarian9);
        S_vegetarian10 = (Spinner) findViewById(R.id.veg_non_vegetarian10);

        S_milk1 = (Spinner) findViewById(R.id.kind_of_milk);
        S_milk2 = (Spinner) findViewById(R.id.kind_of_milk2);
        S_milk3 = (Spinner) findViewById(R.id.kind_of_milk3);
        S_milk4 = (Spinner) findViewById(R.id.kind_of_milk4);
        S_milk5 = (Spinner) findViewById(R.id.kind_of_milk5);
        S_milk6 = (Spinner) findViewById(R.id.kind_of_milk6);
        S_milk7 = (Spinner) findViewById(R.id.kind_of_milk7);
        S_milk8 = (Spinner) findViewById(R.id.kind_of_milk8);
        S_milk9 = (Spinner) findViewById(R.id.kind_of_milk9);
        S_milk10 = (Spinner) findViewById(R.id.kind_of_milk10);

        linearLayout1 = (LinearLayout) findViewById(R.id.layout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.layout2);
        linearLayout3 = (LinearLayout) findViewById(R.id.layout3);
        linearLayout4 = (LinearLayout) findViewById(R.id.layout4);
        linearLayout5 = (LinearLayout) findViewById(R.id.layout5);
        linearLayout6 = (LinearLayout) findViewById(R.id.layout6);
        linearLayout7 = (LinearLayout) findViewById(R.id.layout7);
        linearLayout8 = (LinearLayout) findViewById(R.id.layout8);
        linearLayout9 = (LinearLayout) findViewById(R.id.layout9);
        linearLayout10 = (LinearLayout) findViewById(R.id.layout10);
        linear_main = (LinearLayout) findViewById(R.id.assume);

        activity_level1 = (LinearLayout) findViewById(R.id.activity_level1);
        activity_level2 = (LinearLayout) findViewById(R.id.activity_level2);
        activity_level3 = (LinearLayout) findViewById(R.id.activity_level3);
        activity_level4 = (LinearLayout) findViewById(R.id.activity_level4);
        activity_level5 = (LinearLayout) findViewById(R.id.activity_level5);
        activity_level6 = (LinearLayout) findViewById(R.id.activity_level6);
        activity_level7 = (LinearLayout) findViewById(R.id.activity_level7);
        activity_level8 = (LinearLayout) findViewById(R.id.activity_level8);
        activity_level9 = (LinearLayout) findViewById(R.id.activity_level9);
        activity_level10 = (LinearLayout) findViewById(R.id.activity_level10);

        S_Exercise1 = (Spinner) findViewById(R.id.Exercise1);
        S_Exercise2 = (Spinner) findViewById(R.id.Exercise2);
        S_Exercise3 = (Spinner) findViewById(R.id.Exercise3);
        S_Exercise4 = (Spinner) findViewById(R.id.Exercise4);
        S_Exercise5 = (Spinner) findViewById(R.id.Exercise5);
        S_Exercise6 = (Spinner) findViewById(R.id.Exercise6);
        S_Exercise7 = (Spinner) findViewById(R.id.Exercise7);
        S_Exercise8 = (Spinner) findViewById(R.id.Exercise8);
        S_Exercise9 = (Spinner) findViewById(R.id.Exercise9);
        S_Exercise10 = (Spinner) findViewById(R.id.Exercise10);

        S_Occupation1 = (Spinner) findViewById(R.id.Occupation1);
        S_Occupation2 = (Spinner) findViewById(R.id.Occupation2);
        S_Occupation3 = (Spinner) findViewById(R.id.Occupation3);
        S_Occupation4 = (Spinner) findViewById(R.id.Occupation4);
        S_Occupation5 = (Spinner) findViewById(R.id.Occupation5);
        S_Occupation6 = (Spinner) findViewById(R.id.Occupation6);
        S_Occupation7 = (Spinner) findViewById(R.id.Occupation7);
        S_Occupation8 = (Spinner) findViewById(R.id.Occupation8);
        S_Occupation9 = (Spinner) findViewById(R.id.Occupation9);
        S_Occupation10 = (Spinner) findViewById(R.id.Occupation10);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(FAMILY_OIL_CALCULATOR.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), MoreActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(FAMILY_OIL_CALCULATOR.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(FAMILY_OIL_CALCULATOR.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(FAMILY_OIL_CALCULATOR.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(FAMILY_OIL_CALCULATOR.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(FAMILY_OIL_CALCULATOR.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
