package com.hwi.health.Activitys.AllTests.CUSTOMISED_DIET_PLAN;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hwi.health.Activitys.Plans.Excercize_plans.CalorieCalculator;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.Ideal_Diat_Model;
import com.hwi.health.Models.customized_model;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Customised_Diet_Plan extends AppCompatActivity implements BaseUrl ,View.OnClickListener{

    Spinner S_major_meals, S_eat_in_breakfast, S_eat_grains, S_rice_type, S_milk_type, S_food_oil, S_category_one, S_category_two, S_category_three, S_category_four, S_category_five, S_non_veg,
            S_chicken_without_skin, S_fish, S_beef, S_goat_meat, S_lamb, S_pork;
    LinearLayout lin_breakfast, lin_non_veg;
    Button submit, Reset;
    String key_veg_non,key_activity,key_occcp,key_gender,tar_weight_loss;
    String randnoo, gender, height, weight, age, user_id,result;
    String feet = "0", inch = "0", S_dob, strDate, get_dob, tar_weight, weight_loss;
    String S_Exercise, S_veg_non, S_want, S_Occupation, S_is_Pregnant, S_is_Breast_Feeding, S_first_day_last_mensrual_period, S_pre_pregnancy_weight, S_child_age;
    int number, count_total;
    ArrayList<customized_model> datalist = new ArrayList<>();
    ProgressDialog pd;
    ArrayList<Ideal_Diat_Model> major_meals_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> breakfast_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> grains_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> rice_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> milk_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> non_veg_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> chicken_without_skin_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> fish_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> beef_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> goat_meat_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> lamb_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> pork_list = new ArrayList<>();

    ArrayList<Ideal_Diat_Model> food_oil_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_one_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_two_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_three_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_four_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> category_five_list = new ArrayList<>();
    ArrayAdapter<Ideal_Diat_Model> major_meals_adapter, breakfastadapter, grains_adapter, rice_adapter, milk_adapter, food_oil_adapter, category_one_adapter, category_two_adapter, category_three_adapter, category_four_adapter, category_five_adapter, non_veg_adapter,
            chicken_without_skin_adapter, fish_adapter, beef_adapter, goat_meat_adapter, lamb_adapter, pork_adapter;
    String major_meals, eat_in_breakfast, eat_grains, rice_type, milk_type, food_oil, category_one, category_two, category_three, category_four, category_five, non_veg, chicken_without_skin, fish, beef, goat_meat, lamb, pork;
    String food_oil_name;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("DIET PLAN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customised__diet__plan);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        S_major_meals = (Spinner) findViewById(R.id.major_meals);
        S_eat_in_breakfast = (Spinner) findViewById(R.id.eat_in_breakfast);
        S_eat_grains = (Spinner) findViewById(R.id.eat_grains);
        S_rice_type = (Spinner) findViewById(R.id.rice_type);
        S_milk_type = (Spinner) findViewById(R.id.milk_type);
        S_food_oil = (Spinner) findViewById(R.id.food_oil);
        S_category_one = (Spinner) findViewById(R.id.category_one);
        S_category_two = (Spinner) findViewById(R.id.category_two);
        S_category_three = (Spinner) findViewById(R.id.category_three);
        S_category_four = (Spinner) findViewById(R.id.category_four);
        S_category_five = (Spinner) findViewById(R.id.category_five);
        S_non_veg = (Spinner) findViewById(R.id.non_veg);
        S_chicken_without_skin = (Spinner) findViewById(R.id.chicken_without_skin);
        S_fish = (Spinner) findViewById(R.id.fish);
        S_beef = (Spinner) findViewById(R.id.beef);
        S_goat_meat = (Spinner) findViewById(R.id.goat_meat);
        S_lamb = (Spinner) findViewById(R.id.lamb);
        S_pork = (Spinner) findViewById(R.id.pork);

        lin_breakfast = (LinearLayout) findViewById(R.id.breakfast);
        lin_non_veg = (LinearLayout) findViewById(R.id.lin_non_veg);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);


        submit = (Button) findViewById(R.id.Submit);
        Reset = (Button) findViewById(R.id.Reset);
        spinn();
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        submit.setOnClickListener(this);

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            gender = sp.getString("gender", "");
            age = sp.getString("age", "");
            user_id = sp.getString("Userid", "");
            sp.getString("key", "");
            height = sp.getString("height", "");
            weight = sp.getString("weight", "");
            get_dob = sp.getString("Dob", "");
            S_Exercise = sp.getString("activity_level_one", "");
            S_veg_non = sp.getString("food_habits", "");
            S_want = sp.getString("weg_loss", "").trim();
            S_is_Pregnant = sp.getString("is_pregnent", "");
            S_pre_pregnancy_weight = sp.getString("pre_pregnancy_weight", "");
            S_first_day_last_mensrual_period = sp.getString("first_day_of_last_menstrual_cycle", "");
            S_Occupation = sp.getString("activity_level_two", "");
            S_is_Breast_Feeding = sp.getString("breast_feeding", "");
            S_child_age = sp.getString("child_age", "");

            Log.e("dhsshdfkdf", gender + "..." + S_Exercise+"..."+S_veg_non+"..."+S_Occupation);
            tar_weight = sp.getString("weight_range", "");
            weight_loss = sp.getString("weight_target", "");
            Log.e("dhsshdfkdf", tar_weight + "..." + weight_loss);
            number = Integer.parseInt(age);
            Log.e("dhsshdfkdf", S_veg_non);
            Log.e("dobbbbbb", get_dob);
            String[] separated = height.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet = f;
            inch = i;
            tar_weight_loss = S_want.toLowerCase();
            Log.e("tar_weight_loss",tar_weight_loss);
            if (S_veg_non.equals("Non Vegetarian")) {
                lin_non_veg.setVisibility(View.VISIBLE);
            } else {
                lin_non_veg.setVisibility(View.GONE);
            }



            if (gender.equals("male")){
                key_gender = "Male";
            }else {
                key_gender = "Female";
            }
            if (S_veg_non.equals("Vegetarian")){
                key_veg_non = "2";
            }else if (S_veg_non.equals("Eggetarian")){
                key_veg_non = "1";
            }else {
                key_veg_non = "3";
            }
            int number = Integer.parseInt(age);
            if (number>18){
            if (S_Exercise.equals("No regular exercise")){
                key_activity = "1";
            }else if (S_Exercise.equals("Exercise>20 min,1-3 days/week")){
                key_activity = "2";
            }else if (S_Exercise.equals("Exercise 30-60 min,3-4 days/week")){
                key_activity = "3";
            }else if (S_Exercise.equals("Exercise>60 min, 5-7 days/week")){
                key_activity = "4";
            }else if (S_Exercise.equals("Athlete or very Active")){
                key_activity = "5";
            }
                else {
                key_activity = "0";
            }
            }else {
                if (S_Exercise.equals("< 1 hour of sports or running/cycling etc.")){
                    key_activity = "1";
                }else if (S_Exercise.equals("1 hour or more of physical activity, 3 days/week or more")){
                    key_activity = "2";
                }else if (S_Exercise.equals(">2 hours/day of formal sports or vigorous activity")){
                    key_activity = "3";
                }else {
                    key_activity = "0";
                }
            }

            if (S_Occupation.equals("Sitting /desk jobs")){
                key_occcp = "1";
            } else if (S_Occupation.equals("Standing  for long times")){
                key_occcp = "2";
            }else if (S_Occupation.equals("Active e.g. waiter")){
                key_occcp = "3";
            }else if (S_Occupation.equals("Heavy activity, e.g. carpenter")){
                key_occcp = "4";
            }else if (S_Occupation.equals("Strenuous e.g.Labourers")){
                key_occcp = "5";
            }
            else {
                key_occcp = "0";
            }
        } catch (Exception e) {
            Log.e("SP erro r =", e + "");
        }


    }

    void spinn() {

        major_meals_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        major_meals_list.add(new Ideal_Diat_Model("Breakfast,lunch and dinner", "meal1"));
        major_meals_list.add(new Ideal_Diat_Model("Lunch and dinner", "meal2"));

        major_meals_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, major_meals_list);
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
        breakfastadapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, breakfast_list);
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

        grains_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        grains_list.add(new Ideal_Diat_Model("Only/mostly rice", "1"));
        grains_list.add(new Ideal_Diat_Model("Only/mostly roti", "2"));
        grains_list.add(new Ideal_Diat_Model("Almost equal rice and roti", "3"));

        grains_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, grains_list);
        S_eat_grains.setAdapter(grains_adapter);

        S_eat_grains.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = grains_list.get(position);
                eat_grains = idm.getId();
                Log.e("eat_grains", eat_grains + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rice_list.add(new Ideal_Diat_Model("Choose your type of Rice", "0"));
        rice_list.add(new Ideal_Diat_Model("Brown rice", "1"));
        rice_list.add(new Ideal_Diat_Model("Short grained", "2"));
        rice_list.add(new Ideal_Diat_Model("Medium grained", "3"));
        rice_list.add(new Ideal_Diat_Model("Long grained", "4"));
        rice_list.add(new Ideal_Diat_Model("Long grained,parboiled", "5"));

        rice_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, rice_list);
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
        non_veg_list.add(new Ideal_Diat_Model("Choose no.of meals per week", "0"));
        for (int i = 1; i <= 21; i++) {
            non_veg_list.add(new Ideal_Diat_Model(String.valueOf(i), String.valueOf(i)));
            Log.e("fish_list..", i + "");
        }

        non_veg_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, non_veg_list);
        S_non_veg.setAdapter(non_veg_adapter);

        S_non_veg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = non_veg_list.get(position);
                non_veg = idm.getId();
                Log.e("non_veg", non_veg + "..");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i = 0; i <= 10; i++) {
            chicken_without_skin_list.add(new Ideal_Diat_Model(String.valueOf(i), String.valueOf(i)));
        }

        chicken_without_skin_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, chicken_without_skin_list);
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

        fish_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, fish_list);
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

        beef_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, beef_list);
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

        goat_meat_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, goat_meat_list);
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

        lamb_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, lamb_list);
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

        pork_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, pork_list);
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

        milk_list.add(new Ideal_Diat_Model("Choose your Category", "0"));
        milk_list.add(new Ideal_Diat_Model("Full Cream", "1"));
        milk_list.add(new Ideal_Diat_Model("Toned", "2"));
        milk_list.add(new Ideal_Diat_Model("Double Toned", "3"));
        milk_list.add(new Ideal_Diat_Model("Skimmed", "4"));
        milk_list.add(new Ideal_Diat_Model("Buffalo's milk", "5"));
        milk_list.add(new Ideal_Diat_Model("Cow's milk", "6"));

        milk_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, milk_list);
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

        food_oil_list.add(new Ideal_Diat_Model("Select", "0"));
        food_oil_list.add(new Ideal_Diat_Model("Groundnut/rice bran/sesame+mustard/canola/soyabean", "combo1"));
        food_oil_list.add(new Ideal_Diat_Model("sunflower/safflower+mustard/canola+palm/palmolein", "combo2"));
        food_oil_list.add(new Ideal_Diat_Model("Soyabean+palmolein", "combo3"));
        food_oil_list.add(new Ideal_Diat_Model("Olive+sunflower/safflower", "combo4"));
        food_oil_list.add(new Ideal_Diat_Model("Groundnut/rice bran/sesame+sunflower/safflower", "combo5"));

        food_oil_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, food_oil_list);
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

        category_one_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, category_one_list);
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

        category_two_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, category_two_list);
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

        category_three_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, category_three_list);
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

        category_four_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, category_four_list);
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

        category_five_adapter = new ArrayAdapter<Ideal_Diat_Model>(Customised_Diet_Plan.this, android.R.layout.simple_spinner_dropdown_item, category_five_list);
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


    void DataConnectionAdult() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + customize_diet_plan_and_nutrition + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("responce ", response);
                        Log.e("responce ", URLS + customize_diet_plan_and_nutrition + randnoo);

                        pd.dismiss();
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");

                            if (status.equals("1")) {


                                //       new MyIntent(Ideal_Diat_plan_Adult.this, BMI_Result.class, R.anim.enter, R.anim.exit);

                            } else {


                            }
                        } catch (Exception e) {
                            Log.e("errrrr= ", e + "");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
                pd.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("gender", gender);
                params.put("age", age + "");
                params.put("wt", weight + "");
                params.put("want_diet_for_weight_loss", S_want);
                params.put("ft", feet);
                params.put("inc", inch);
                params.put("meal", major_meals);
                params.put("rice", rice_type);
                params.put("milk", milk_type);
                params.put("oil_combo", food_oil);
                params.put("oil_combo", food_oil);
                params.put("dob", get_dob);
                params.put("veg_nonveg", S_veg_non);
                params.put("exercise", S_Exercise);
                params.put("occupation", S_Occupation);
                params.put("is_Pregnant", S_is_Pregnant);
                params.put("is_Breast_Feeding", S_is_Breast_Feeding);
                params.put("pre_pregnancy_weight", S_pre_pregnancy_weight);
                params.put("child_age", S_child_age);

                params.put("nveg", non_veg);
                params.put("chicken", chicken_without_skin);
                params.put("fish", fish);
                params.put("beef", beef);
                params.put("mutton", goat_meat);
                params.put("lamb", lamb);
                params.put("pork", pork);


                params.put("oil1a", category_one);
                params.put("oil1b", category_two);
                params.put("oil2a", category_three);
                params.put("oil2b", category_four);
                params.put("oil2c", category_two);

                params.put("oil4a", category_three);
                params.put("oil4b", category_five);


                params.put("oil5a", category_three);
                params.put("oil5b", category_one);

                Log.e("params", params + "");
                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Customised_Diet_Plan.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Customised_Diet_Plan.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Customised_Diet_Plan.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Customised_Diet_Plan.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Customised_Diet_Plan.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == submit) {
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            try {
                try {
                    count_total = Integer.parseInt(chicken_without_skin) + Integer.parseInt(fish) + Integer.parseInt(beef) + Integer.parseInt(goat_meat) + Integer.parseInt(lamb) + Integer.parseInt(pork);
                    Log.e("count_total", count_total + "..." + non_veg);
                }
                catch (Exception e){
                    Log.e("Exception...",e+"");
                }
                if (S_veg_non.equals("Non Vegetarian")) {
                    if (Integer.parseInt(non_veg) == count_total) {
                        /*pd = new ProgressDialog(Customised_Diet_Plan.this);
                        pd.setMessage("Please Wait...");
                        pd.setCancelable(false);
                        pd.show();
                        DataConnectionAdult();*/
                        new Task().execute();
                    } else {

                        Toast.makeText(Customised_Diet_Plan.this, "Total of individual non veg meals should be equal to Non veg meals/week", Toast.LENGTH_LONG).show();
                    }
                } else {

                    new Task().execute();
                }
            }catch (Exception e){
                Log.e("Exception...",e+"");
            }
        }
    }


    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Customised_Diet_Plan.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + customize_diet_plan_and_nutrition + randnoo);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("gender", key_gender);
                postDataParams.put("age", age);
                postDataParams.put("wt", weight);
                postDataParams.put("want_diet_for_weight_loss", tar_weight_loss);
                postDataParams.put("ft", feet);
                postDataParams.put("inc", inch);
                postDataParams.put("meal", major_meals);
                postDataParams.put("rice", rice_type);
                postDataParams.put("milk", milk_type);
                postDataParams.put("oil_combo", food_oil);
                postDataParams.put("oil_combo", food_oil);
                postDataParams.put("dob", get_dob);
                postDataParams.put("veg", key_veg_non);
                postDataParams.put("exercise", key_activity);
                postDataParams.put("occupation", key_occcp);
                postDataParams.put("is_Pregnant", S_is_Pregnant);
                postDataParams.put("is_Breast_Feeding", S_is_Breast_Feeding);
                postDataParams.put("pre_pregnancy_weight", S_pre_pregnancy_weight);
                postDataParams.put("child_age", S_child_age);

                postDataParams.put("nveg", non_veg);
                postDataParams.put("chicken", chicken_without_skin);
                postDataParams.put("fish", fish);
                postDataParams.put("beef", beef);
                postDataParams.put("mutton", goat_meat);
                postDataParams.put("lamb", lamb);
                postDataParams.put("pork", pork);


                postDataParams.put("oil1a", category_one);
                postDataParams.put("oil1b", category_two);
                postDataParams.put("oil2a", category_three);
                postDataParams.put("oil2b", category_four);
                postDataParams.put("oil2c", category_two);

                postDataParams.put("oil4a", category_three);
                postDataParams.put("oil4b", category_five);


                postDataParams.put("oil5a", category_three);
                postDataParams.put("oil5b", category_one);

                Log.e("params", params + "");

                Log.e("params", params + "");


                Log.e("params", postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(1500000);
                conn.setConnectTimeout(1500000);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                } else {
                    result = "";
                }
            } catch (Exception e) {
                Log.e("ERRR",e+"");
            }
            return result;
        }

        boolean checkAddition(String indianDiet,String optimizedDiet,String dietWithHealthRisks)
        {
            boolean ret=true;
            if(indianDiet.equals("0.00") || indianDiet.equals(""))
            {
                ret=false;
            }
            else
            {
                return true;
            }
            if(optimizedDiet.equals("0.00") || optimizedDiet.equals(""))
            {
                ret=false;
            }
            else
            {
                return true;
            }
            if(dietWithHealthRisks.equals("0.00") ||dietWithHealthRisks.equals(""))
            {
                ret=false;
            }
            else
            {
                return true;
            }
            return ret;
        }
      @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("LoginActivity = ", result + "");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");


                if (status.equals("1")) {

                         JSONObject json = jobj.getJSONObject("diet_plan");
                         JSONObject jo = json.getJSONObject("CUSTOMIZED_DIET_PLAN_FOR_THE_DAY");
                    JSONObject calorie_calculations=jobj.getJSONObject("calorie_calculations");
                    CalorieCalculator.StoreCalculatedCalories(calorie_calculations,getApplicationContext());
                    CalorieCalculator.writeCustomPlanAdult(getApplicationContext(),json.toString());



                     JSONObject rice = jo.getJSONObject("Rice");
                     JSONObject cup_cooked = rice.getJSONObject("1/2 cup cooked");
                     String Indian_Diet_one = cup_cooked.getString("Basic Indian Diet");
                     String Optimized_one = cup_cooked.getString("Optimized Indian diet");
                     String Health_Risks_one = cup_cooked.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Rice", "1/2 cup cooked", Indian_Diet_one, Optimized_one, Health_Risks_one, R.drawable.grains));
                    if (checkAddition(Indian_Diet_one,Optimized_one,Health_Risks_one)){

                        datalist.add(new customized_model("Rice", "1/2 cup cooked", Indian_Diet_one, Optimized_one, Health_Risks_one, R.drawable.grains));

                    }

                     JSONObject Roti = jo.getJSONObject("Roti");
                     JSONObject roti_one = Roti.getJSONObject("1 roti");
                     String Indian_Diet_roti = roti_one.getString("Basic Indian Diet");
                     String Optimized_roti = roti_one.getString("Optimized Indian diet");
                     String Health_Risks_roti = roti_one.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Roti", "1 roti", Indian_Diet_roti, Optimized_roti, Health_Risks_roti, R.drawable.chapati));

                    if (checkAddition(Indian_Diet_roti,Optimized_roti,Health_Risks_roti)){

                        datalist.add(new customized_model("Roti", "1 roti", Indian_Diet_roti, Optimized_roti, Health_Risks_roti, R.drawable.chapati));

                    }
                        datalist.add(new customized_model("Non-milk proteins", "2 oz protein equivalent= 1/2 cup cooked dal= 2 cooked pieces of boneless meat/fish each of matchbox size", "", "", "", R.drawable.milk));


                     JSONObject Brown_Bread = jo.getJSONObject("Brown Bread");
                     JSONObject Large_Bread = Brown_Bread.getJSONObject("1 Large Bread");
                     String Indian_Diet_Brown_Bread = Large_Bread.getString("Basic Indian Diet");
                     String Optimized_Brown_Bread = Large_Bread.getString("Optimized Indian diet");
                     String Health_Risks_Brown_Bread = Large_Bread.getString("Ideal Diet For People With Health Risks");
                        datalist.add(new customized_model("Brown Bread", "1 Large Bread", Indian_Diet_Brown_Bread, Optimized_Brown_Bread, Health_Risks_Brown_Bread, R.drawable.bread));

                    if (checkAddition(Indian_Diet_Brown_Bread,Optimized_Brown_Bread,Health_Risks_Brown_Bread)){

                        datalist.add(new customized_model("Brown Bread", "1 Large Bread", Indian_Diet_Brown_Bread, Optimized_Brown_Bread, Health_Risks_Brown_Bread, R.drawable.bread));

                    }
                     JSONObject Cornflakes = jo.getJSONObject("Cornflakes");
                     JSONObject Cup = Cornflakes.getJSONObject("1 Cup");
                     String Indian_Diet_Cornflakes = Cup.getString("Basic Indian Diet");
                     String Optimized_Cornflakes = Cup.getString("Optimized Indian diet");
                     String Health_Risks_Cornflakes = Cup.getString("Ideal Diet For People With Health Risks");
                         datalist.add(new customized_model("Cornflakes", "1 Cup", Indian_Diet_Cornflakes, Optimized_Cornflakes, Health_Risks_Cornflakes, R.drawable.cornflakes));
                    if (checkAddition(Indian_Diet_Cornflakes,Optimized_Cornflakes,Health_Risks_Cornflakes)){

                        datalist.add(new customized_model("Cornflakes", "1 Cup", Indian_Diet_Cornflakes, Optimized_Cornflakes, Health_Risks_Cornflakes, R.drawable.cornflakes));

                    }

                     JSONObject White_Bread = jo.getJSONObject("White Bread");
                     JSONObject White_Bread_Large = White_Bread.getJSONObject("1 Large Bread");
                     String Indian_Diet_White_Bread = White_Bread_Large.getString("Basic Indian Diet");
                     String Optimized_White_Bread = White_Bread_Large.getString("Optimized Indian diet");
                     String Health_Risks_White_Bread = White_Bread_Large.getString("Ideal Diet For People With Health Risks");
                         datalist.add(new customized_model("White Bread", "1 Large Bread", Indian_Diet_White_Bread, Optimized_White_Bread, Health_Risks_White_Bread, R.drawable.bread));
                    if (checkAddition(Indian_Diet_White_Bread,Optimized_White_Bread,Health_Risks_White_Bread)){

                        datalist.add(new customized_model("White Bread", "1 Large Bread", Indian_Diet_White_Bread, Optimized_White_Bread, Health_Risks_White_Bread, R.drawable.bread));

                    }

                     JSONObject Muesli = jo.getJSONObject("Muesli");
                     JSONObject Muesli_cup = Muesli.getJSONObject("1 Cup");
                     String Indian_Diet_Muesli = Muesli_cup.getString("Basic Indian Diet");
                     String Optimized_Muesli = Muesli_cup.getString("Optimized Indian diet");
                     String Health_Risks_Muesli = Muesli_cup.getString("Ideal Diet For People With Health Risks");
                         datalist.add(new customized_model("Muesli", "1 Cup", Indian_Diet_Muesli, Optimized_Muesli, Health_Risks_Muesli, R.drawable.muesli));

                    if (checkAddition(Indian_Diet_Muesli,Optimized_Muesli,Health_Risks_Muesli)){

                        datalist.add(new customized_model("Muesli", "1 Cup", Indian_Diet_Muesli, Optimized_Muesli, Health_Risks_Muesli, R.drawable.muesli));

                    }
                     JSONObject Oats = jo.getJSONObject("Oats");
                     JSONObject Oats_cup = Oats.getJSONObject("1/2 cup cooked");
                     String Indian_Diet_Oats = Oats_cup.getString("Basic Indian Diet");
                     String Optimized_Oats = Oats_cup.getString("Optimized Indian diet");
                     String Health_Risks_Oats = Oats_cup.getString("Ideal Diet For People With Health Risks");
                         datalist.add(new customized_model("Oats", "1/2 cup cooked", Indian_Diet_Oats, Optimized_Oats, Health_Risks_Oats, R.drawable.oats));
                    if (checkAddition(Indian_Diet_Oats,Optimized_Oats,Health_Risks_Oats)){

                        datalist.add(new customized_model("Oats", "1/2 cup cooked", Indian_Diet_Oats, Optimized_Oats, Health_Risks_Oats, R.drawable.oats));

                    }

                     JSONObject Roti_in_Breakfast = jo.getJSONObject("Roti in Breakfast");
                     JSONObject parantha = Roti_in_Breakfast.getJSONObject("1 roti or 1 parantha (excluding oil and stuffing)");
                     String Indian_Diet_parantha = parantha.getString("Basic Indian Diet");
                     String Optimized_parantha = parantha.getString("Optimized Indian diet");
                     String Health_Risks_parantha = parantha.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Roti in Breakfast", "1 roti or 1 parantha (excluding oil and stuffing)", Indian_Diet_parantha, Optimized_parantha, Health_Risks_parantha, R.drawable.chapati));

                    if (checkAddition(Indian_Diet_parantha,Optimized_parantha,Health_Risks_parantha)){

                        datalist.add(new customized_model("Roti in Breakfast", "1 roti or 1 parantha (excluding oil and stuffing)", Indian_Diet_parantha, Optimized_parantha, Health_Risks_parantha, R.drawable.chapati));

                    }

                     datalist.add(new customized_model("Non-milk proteins meals (2 oz servings per meal) per week","", "", "", "",R.drawable.sea_food));

                     JSONObject Dals_meat_fish_day = jo.getJSONObject("Dals/meat/fish/day");
                     String Indian_Diet_Dals_meat = Dals_meat_fish_day.getString("Basic Indian Diet");
                     String Optimized_Dals_meat = Dals_meat_fish_day.getString("Optimized Indian diet");
                     String Health_Risks_Dals_meat = Dals_meat_fish_day.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Dals/meat/fish/day", "", Indian_Diet_Dals_meat, Optimized_Dals_meat, Health_Risks_Dals_meat, R.drawable.dals));

                    if (checkAddition(Indian_Diet_Dals_meat,Optimized_Dals_meat,Health_Risks_Dals_meat)){

                        datalist.add(new customized_model("Dals/meat/fish/day", "", Indian_Diet_Dals_meat, Optimized_Dals_meat, Health_Risks_Dals_meat, R.drawable.dals));

                    }

                     JSONObject soya_products = jo.getJSONObject("Tofu/soya products");
                     JSONObject soya_products_week = soya_products.getJSONObject("These are the minimum number of 2 oz size servings of soy products you should eat in a week, but you can and should eat more soy products in lace of dal or meat, if you like");
                     String Indian_Diet_soya_products = soya_products_week.getString("Basic Indian Diet");
                     String Optimized_soya_products = soya_products_week.getString("Optimized Indian diet");
                     String Health_Risks_soya_products = soya_products_week.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Tofu/soya products", "These are the minimum number of 2 oz size servings of soy products you should eat in a week, but you can and should eat more soy products in lace of dal or meat, if you like", Indian_Diet_soya_products, Optimized_soya_products, Health_Risks_soya_products, R.drawable.soya));
                    if (checkAddition(Indian_Diet_soya_products,Optimized_soya_products,Health_Risks_soya_products)){

                        datalist.add(new customized_model("Tofu/soya products", "These are the minimum number of 2 oz size servings of soy products you should eat in a week, but you can and should eat more soy products in lace of dal or meat, if you like", Indian_Diet_soya_products, Optimized_soya_products, Health_Risks_soya_products, R.drawable.soya));

                    }

                     JSONObject Eggs = jo.getJSONObject("Eggs");
                     String Indian_Diet_Eggs = Eggs.getString("Basic Indian Diet");
                     String Optimized_Eggs = Eggs.getString("Optimized Indian diet");
                     String Health_Risks_Eggs = Eggs.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Eggs", "", Indian_Diet_Eggs, Optimized_Eggs, Health_Risks_Eggs, R.drawable.eggs));

                    if (checkAddition(Indian_Diet_Eggs,Optimized_Eggs,Health_Risks_Eggs)){

                        datalist.add(new customized_model("Eggs", "", Indian_Diet_Eggs, Optimized_Eggs, Health_Risks_Eggs, R.drawable.eggs));

                    }
                     JSONObject Nuts = jo.getJSONObject("Nuts");
                     JSONObject Nuts_half = Nuts.getJSONObject("1 Oz =22 almonds/30 peanuts/16-20 kajus/10-12 macadonia nuts/28 pecan nuts/14walnut halfs");
                     String Indian_Diet_Nuts = Nuts_half.getString("Basic Indian Diet");
                     String Optimized_Nuts = Nuts_half.getString("Optimized Indian diet");
                     String Health_Risks_Nuts = Nuts_half.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Nuts", "1 Oz =22 almonds/30 peanuts/16-20 kajus/10-12 macadonia nuts/28 pecan nuts/14walnut halfs", Indian_Diet_Nuts, Optimized_Nuts, Health_Risks_Nuts, R.drawable.nuts));

                    if (checkAddition(Indian_Diet_Nuts,Optimized_Nuts,Health_Risks_Nuts)){

                        datalist.add(new customized_model("Nuts", "1 Oz =22 almonds/30 peanuts/16-20 kajus/10-12 macadonia nuts/28 pecan nuts/14walnut halfs", Indian_Diet_Nuts, Optimized_Nuts, Health_Risks_Nuts, R.drawable.nuts));

                    }
                     JSONObject Milk_curd = jo.getJSONObject("Milk/curd");
                     JSONObject Milk_curd_cup = Milk_curd.getJSONObject("1 cup=200 ml");
                     String Indian_Diet_Milk_curd = Milk_curd_cup.getString("Basic Indian Diet");
                     String Optimized_Milk_curd = Milk_curd_cup.getString("Optimized Indian diet");
                     String Health_Risks_Milk_curd = Milk_curd_cup.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Milk/curd", "11 cup=200 ml", Indian_Diet_Milk_curd, Optimized_Milk_curd, Health_Risks_Milk_curd, R.drawable.milk));
                    if (checkAddition(Indian_Diet_Milk_curd,Optimized_Milk_curd,Health_Risks_Milk_curd)){

                        datalist.add(new customized_model("Milk/curd", "1 cup=200 ml", Indian_Diet_Milk_curd, Optimized_Milk_curd, Health_Risks_Milk_curd, R.drawable.milk));

                    }

                     JSONObject Vegetables = jo.getJSONObject("Vegetables");
                     JSONObject Vegetables_cup = Vegetables.getJSONObject("1/2 cup cooked");
                     String Indian_Diet_Vegetables = Vegetables_cup.getString("Basic Indian Diet");
                     String Optimized_Vegetables = Vegetables_cup.getString("Optimized Indian diet");
                     String Health_Risks_Vegetables = Vegetables_cup.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Vegetables", "1/2 cup cooked", Indian_Diet_Vegetables, Optimized_Vegetables, Health_Risks_Vegetables, R.drawable.vegetables));

                    if (checkAddition(Indian_Diet_Vegetables,Optimized_Vegetables,Health_Risks_Vegetables)){

                        datalist.add(new customized_model("Vegetables", "1/2 cup cooked", Indian_Diet_Vegetables, Optimized_Vegetables, Health_Risks_Vegetables, R.drawable.vegetables));

                    }

                     JSONObject Fruits = jo.getJSONObject("Fruits");
                     JSONObject Fruits_cup = Fruits.getJSONObject("1 medium sized fruit= 1/2 cup cut=100 gms fruit = 1/2 cup fruit juice");
                     String Indian_Diet_Fruits = Fruits_cup.getString("Basic Indian Diet");
                     String Optimized_Fruits = Fruits_cup.getString("Optimized Indian diet");
                     String Health_Risks_Fruits = Fruits_cup.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Fruits", "1 medium sized fruit= 1/2 cup cut=100 gms fruit = 1/2 cup fruit juice", Indian_Diet_Fruits, Optimized_Fruits, Health_Risks_Fruits, R.drawable.fruit));

                    if (checkAddition(Indian_Diet_Fruits,Optimized_Fruits,Health_Risks_Fruits)){

                        datalist.add(new customized_model("Fruits", "1 medium sized fruit= 1/2 cup cut=100 gms fruit = 1/2 cup fruit juice", Indian_Diet_Fruits, Optimized_Fruits, Health_Risks_Fruits, R.drawable.fruit));

                    }
                     JSONObject Oil = jo.getJSONObject("Oil");
                     JSONObject Oil_cup = Oil.getJSONObject("1 teaspoon");
                     String Indian_Diet_Oil = Oil_cup.getString("Basic Indian Diet");
                     String Optimized_Oil = Oil_cup.getString("Optimized Indian diet");
                     String Health_Risks_Oil = Oil_cup.getString("Ideal Diet For People With Health Risks");
                     datalist.add(new customized_model("Oil", "1 teaspoon", Indian_Diet_Oil, Optimized_Oil, Health_Risks_Oil, R.drawable.oil));
                    if (checkAddition(Indian_Diet_Oil,Optimized_Oil,Health_Risks_Oil)){

                        datalist.add(new customized_model("Oil", "1 teaspoon", Indian_Diet_Oil, Optimized_Oil, Health_Risks_Oil, R.drawable.oil));

                    }
                    if (key_veg_non.equals("3")) {

                        JSONObject Chicken = jo.getJSONObject("Chicken");
                        JSONObject Chicken_week = Chicken.getJSONObject("These number of servings per week are as per your dietary prefernce, but, if you want you can have non veg proteins in all meals, only do not exceed the total");
                        String Indian_Diet_Chicken = Chicken_week.getString("Basic Indian Diet");
                        String Optimized_Chicken = Chicken_week.getString("Optimized Indian diet");
                        String Health_Risks_Chicken = Chicken_week.getString("Ideal Diet For People With Health Risks");
//                        datalist.add(new customized_model("Chicken", "These number of servings per week are as per your dietary prefernce, but, if you want you can have non veg proteins in all meals, only do not exceed the total", Indian_Diet_Chicken, Optimized_Chicken, Health_Risks_Chicken, R.drawable.chicken));

                        if (checkAddition(Indian_Diet_Chicken,Optimized_Chicken,Health_Risks_Chicken)){

                            datalist.add(new customized_model("Chicken", "These number of servings per week are as per your dietary prefernce, but, if you want you can have non veg proteins in all meals, only do not exceed the total", Indian_Diet_Chicken, Optimized_Chicken, Health_Risks_Chicken, R.drawable.chicken));

                        }
                        JSONObject Fish = jo.getJSONObject("Fish");
                     JSONObject Fish_num = Fish.getJSONObject("These are the minimum number of 2 oz size servings of fish you should eat in a week, but you can and should eat more fish in lace of dal or meat, if you like it !");
                     String Indian_Diet_Fish = Fish_num.getString("Basic Indian Diet");
                     String Optimized_Fish = Fish_num.getString("Optimized Indian diet");
                     String Health_Risks_Fish = Fish_num.getString("Ideal Diet For People With Health Risks");
                    // datalist.add(new customized_model("Fish", "These are the minimum number of 2 oz size servings of fish you should eat in a week, but you can and should eat more fish in lace of dal or meat, if you like it !", Indian_Diet_Fish, Optimized_Fish, Health_Risks_Fish, R.drawable.fish));
                        if (checkAddition(Indian_Diet_Fish,Optimized_Fish,Health_Risks_Fish)){

                            datalist.add(new customized_model("Fish", "These are the minimum number of 2 oz size servings of fish you should eat in a week, but you can and should eat more fish in lace of dal or meat, if you like it !", Indian_Diet_Fish, Optimized_Fish, Health_Risks_Fish, R.drawable.fish));

                        }

                     JSONObject Beef = jo.getJSONObject("Beef");

                     String Indian_Diet_Beef = Beef.getString("Basic Indian Diet");
                     String Optimized_Beef = Beef.getString("Optimized Indian diet");
                     String Health_Risks_Beef = Beef.getString("Ideal Diet For People With Health Risks");
                    // datalist.add(new customized_model("Beef", "", Indian_Diet_Beef, Optimized_Beef, Health_Risks_Beef, R.drawable.beef));
                        if (checkAddition(Indian_Diet_Beef,Optimized_Beef,Health_Risks_Beef)){

                            datalist.add(new customized_model("Beef", "", Indian_Diet_Beef, Optimized_Beef, Health_Risks_Beef, R.drawable.beef));

                        }

                     JSONObject Mutton = jo.getJSONObject("Mutton");

                     String Indian_Diet_Mutton = Mutton.getString("Basic Indian Diet");
                     String Optimized_Mutton = Mutton.getString("Optimized Indian diet");
                     String Health_Risks_Mutton = Mutton.getString("Ideal Diet For People With Health Risks");
                     //datalist.add(new customized_model("Mutton", "", Indian_Diet_Mutton, Optimized_Mutton, Health_Risks_Mutton, R.drawable.mutton));
                        if (checkAddition(Indian_Diet_Mutton,Optimized_Mutton,Health_Risks_Mutton)){

                            datalist.add(new customized_model("Mutton", "", Indian_Diet_Mutton, Optimized_Mutton, Health_Risks_Mutton, R.drawable.mutton));

                        }

                 }else {

                 }

                    Toast.makeText(Customised_Diet_Plan.this, message, Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Customised_Diet_Plan.this, Customised_Diet_Plan_result.class);
                    in.putExtra("data", datalist);
                    startActivity(in);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    finish();
//                       Snackbar.make(activity_create_account,message,Snackbar.LENGTH_LONG).show();

                } else {

                    Toast.makeText(Customised_Diet_Plan.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("VolleyError= ", e + "");
            }
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while (itr.hasNext()) {
            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Customised_Diet_Plan.this, PlansActivity.class, R.anim.enter, R.anim.exit);
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
}
