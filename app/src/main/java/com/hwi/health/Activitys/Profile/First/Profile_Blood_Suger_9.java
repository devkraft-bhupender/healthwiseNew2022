package com.hwi.health.Activitys.Profile.First;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.edit_profile.Edit_profile_Activity;

import java.util.ArrayList;

public class Profile_Blood_Suger_9 extends AppCompatActivity implements View.OnClickListener {
    ImageView next, prev;
    ActionBar ab;
    Spinner lv;
    int selection = 100000000;
    ArrayList<String> hlist = new ArrayList<>();
    //ArrayList<HB1CModel>hlist = new ArrayList<>();
//    Base adap;
    ArrayAdapter<String> adapter;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;

    String HBA1C_val, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;
    Context ctx;
    String months = "", key, edit_key;
    RelativeLayout R_layout;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L,hvc;
    RadioButton one, two, three, four, five, day, week, month;
    String S_time, S_duration, S_ppvalue, S_Last_fasting, S_HBA1C_val, S_HBA1C_value, get_dob, diet_weight_loss;
    EditText ppvalue, Last_fasting;
    Class go_class, go_profile10, go_profile11, go_profile_smoke, go_profile_drink;

    Class back_class, back_profile1, back_profile2, back_profile3, back_pregnant, back_profile5, back_profile6, back_profile4, back_profile7, back_profile8;
    Class go_two_class;
    final int sdk = android.os.Build.VERSION.SDK_INT;
    EditText HBA1C_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__blood__suger_9);
        ctx = this;
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        lv = (Spinner) findViewById(R.id.list);
        one = (RadioButton) findViewById(R.id.one);
        two = (RadioButton) findViewById(R.id.two);
        three = (RadioButton) findViewById(R.id.three);
        four = (RadioButton) findViewById(R.id.four);
        five = (RadioButton) findViewById(R.id.five);
        day = (RadioButton) findViewById(R.id.day);
        week = (RadioButton) findViewById(R.id.week);
        month = (RadioButton) findViewById(R.id.month);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        hvc = (LinearLayout) findViewById(R.id.hvc);


        HBA1C_value = (EditText) findViewById(R.id.HBA1C_value);
        ppvalue = (EditText) findViewById(R.id.ppvalue);
        Last_fasting = (EditText) findViewById(R.id.Last_fasting);
        R_layout = (RelativeLayout) findViewById(R.id.bottom);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);


        SharedPreferences sp1 = getSharedPreferences("Bottom", MODE_PRIVATE);

        key = sp1.getString("bt_key", "");
       /* if (key.equals("1")){
            R_layout.setVisibility(View.VISIBLE);
        }else {
            R_layout.setVisibility(View.GONE);
        }*/

        hlist.add("Never");
        hlist.add("1 Month");
        hlist.add("2 Months");
        hlist.add("3 Months");
        hlist.add("4 Months");
        hlist.add("5 Months");
        hlist.add("6 Months");
        hlist.add("7 Months");
        hlist.add("8 Months");
        hlist.add("9 Months");
        adapter = new ArrayAdapter<String>(Profile_Blood_Suger_9.this, android.R.layout.simple_spinner_dropdown_item, hlist);
        //  adap  = new Base();
        lv.setAdapter(adapter);

        go_class = Profile_Active_10.class;
        back_class = User_Profile.class;
        go_profile10 = Profile_Active_10.class;
        go_profile11 = Profile_Food_habits_11.class;
        go_profile_smoke = Smoking.class;
        go_profile_drink = Alcohol.class;
        back_profile1 = Profile_Step1.class;
        back_profile2 = Profile_Step2.class;
        back_profile3 = Profile_Step3.class;
        back_pregnant = Profile_Pregnant_3.class;
        back_profile5 = Profile_Breast_Feeding_5.class;
        back_profile6 = Profile_Child_Age_6.class;
        back_profile4 = Profile_Menstrul_Cycle_4.class;
        back_profile7 = Profile_Diabetic_7.class;
        back_profile8 = Profile_Insulin_8.class;

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            edit_key = sharedPreferences.getString("edit_key", "");
            Log.e("edit_keyyy", edit_key + "");
            if (edit_key.equals("8")) {
                go_class = Edit_profile_Activity.class;
                back_class = Edit_profile_Activity.class;
                go_profile10 = Edit_profile_Activity.class;
                go_profile11 = Edit_profile_Activity.class;
                go_profile_smoke = Edit_profile_Activity.class;
                go_profile_drink = Edit_profile_Activity.class;
                back_profile1 = Edit_profile_Activity.class;
                back_profile2 = Edit_profile_Activity.class;
                back_profile3 = Edit_profile_Activity.class;
                back_pregnant = Edit_profile_Activity.class;
                back_profile5 = Edit_profile_Activity.class;
                back_profile6 = Edit_profile_Activity.class;
                back_profile4 = Edit_profile_Activity.class;
                back_profile7 = Edit_profile_Activity.class;
                back_profile8 = Edit_profile_Activity.class;

                sharedPreferences.edit().clear().commit();
            } else {
                go_profile10 = Profile_Active_10.class;
                go_profile11 = Profile_Food_habits_11.class;
                go_profile_smoke = Smoking.class;
                go_profile_drink = Alcohol.class;
                back_profile1 = Profile_Step1.class;
                back_profile2 = Profile_Step2.class;
                back_profile3 = Profile_Step3.class;
                back_pregnant = Profile_Pregnant_3.class;
                back_profile5 = Profile_Breast_Feeding_5.class;
                back_profile6 = Profile_Child_Age_6.class;
                back_profile4 = Profile_Menstrul_Cycle_4.class;
                back_profile7 = Profile_Diabetic_7.class;
                back_profile8 = Profile_Insulin_8.class;

                go_class = Profile_Active_10.class;
                back_class = User_Profile.class;


            }
        } catch (Exception e) {

        }
        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            gender = sp.getString("gender", "");
            age = sp.getString("age", "");
            user_id = sp.getString("Userid", "");
            sp.getString("key", "");
            height = sp.getString("height", "");
            weight = sp.getString("weight", "");
            is_pregnent = sp.getString("is_pregnent", "");
            first_day_of_last_menstrual_cycle = sp.getString("first_day_of_last_menstrual_cycle", "");
            pre_pregnancy_weight = sp.getString("pre_pregnancy_weight", "");
            breast_feeding = sp.getString("breast_feeding", "");
            child_age = sp.getString("child_age", "");
            is_diabetic = sp.getString("is_diabetic", "");
            taking_insulin = sp.getString("taking_insulin", "");
            diabetic_diet = sp.getString("taking_diabetic_diet", "");
            last_HBA1C = sp.getString("last_HBA1C", "");
            blood_sugar_no_of_times = sp.getString("blood_sugar_no_of_times", "");
            blood_sugar_in = sp.getString("blood_sugar_in_D_W_M", "");
            last_fasting = sp.getString("Last_fasting", "");
            pp_value = sp.getString("pp_value", "");
            HBA1C_val = sp.getString("HBA1C_value", "");
            activity_level_one = sp.getString("activity_level_one", "");
            activity_level_two = sp.getString("activity_level_two", "");
            food_habits = sp.getString("food_habits", "");
            any_heart_disease = sp.getString("any_heart_disease", "");
            is_alcholic = sp.getString("is_alcholic", "");
            alcohol_how_often = sp.getString("alcohol_how_often", "");
            is_smoke = sp.getString("is_smoke", "");
            smoke_how_often = sp.getString("smoke_how_often", "");

            Hemoglobin = sp.getString("Hemoglobin", "");
            Hematocrit = sp.getString("Hematocrit", "");
            blood_sugar = sp.getString("blood_sugar", "");
            Total_cholesterol = sp.getString("Total_cholesterol", "");
            LDL_cholesterol = sp.getString("LDL_cholesterol", "");
            HDL_cholesterol = sp.getString("HDL_cholesterol", "");
            Triglycerides = sp.getString("Triglycerides", "");
            Vitamin_levels = sp.getString("Vitamin_levels", "");
            Senter_inc = sp.getString("Senter_inc", "");
            Senter_cmc = sp.getString("Senter_cmc", "");
            SSystolic = sp.getString("SSystolic", "");
            SDiastolic = sp.getString("SDiastolic", "");
            Vitamin_B12_levels = sp.getString("Vitamin_B12_levels", "");
            heart_disease = sp.getString("heart_disease", "");
            Bp = sp.getString("BP", "");
            diet_weight_loss = sp.getString("weg_loss", "");
            get_dob = sp.getString("Dob", "");
            sp.getString("is_login", "");

            int sincePosition = adapter.getPosition(last_HBA1C);
            lv.setSelection(sincePosition);

            if (blood_sugar_no_of_times.equals("one"))
                one.setChecked(true);
            else if (blood_sugar_no_of_times.equals("two"))
                two.setChecked(true);
            else if (blood_sugar_no_of_times.equals("three"))
                three.setChecked(true);
            else if (blood_sugar_no_of_times.equals("four"))
                four.setChecked(true);
            else if (blood_sugar_no_of_times.equals("five"))
                five.setChecked(true);

            if (blood_sugar_in.equals("day"))
                day.setChecked(true);
            else if (blood_sugar_in.equals("month"))
                month.setChecked(true);
            else if (blood_sugar_in.equals("week"))
                week.setChecked(true);

            ppvalue.setText(pp_value);
            HBA1C_value.setText(HBA1C_val);
            Last_fasting.setText(last_fasting);


        } catch (Exception e) {

        }

        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = position;
                months = hlist.get(position);
              if (position == 0){
                  HBA1C_value.setText("0");
                  hvc.setVisibility(View.GONE);
              }else {
                  hvc.setVisibility(View.VISIBLE);
              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == next) {


            if (one.isChecked())
                S_time = "one";
            else if (two.isChecked())
                S_time = "two";
            else if (three.isChecked())
                S_time = "three";
            else if (four.isChecked())
                S_time = "four";
            else if (five.isChecked())
                S_time = "five";


            if (day.isChecked())
                S_duration = "day";
            else if (month.isChecked())
                S_duration = "month";
            else if (week.isChecked())
                S_duration = "week";

            S_ppvalue = ppvalue.getText().toString();
            S_Last_fasting = Last_fasting.getText().toString();
            S_HBA1C_value = HBA1C_value.getText().toString();

            if (selection == 0){
                Toast.makeText(this, "Fill HBA1C value", Toast.LENGTH_SHORT).show();
                return;
            }

            if (S_ppvalue.isEmpty() || S_Last_fasting.isEmpty() ) {
                Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(getApplicationContext(), S_HBA1C_value, Toast.LENGTH_SHORT).show();
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();

                if (!S_time.equals(blood_sugar_no_of_times)) {
                    //  Toast.makeText(Profile_Blood_Suger_9.this, "time change", Toast.LENGTH_SHORT).show();

                    if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile10, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile11, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_smoke.equals("") || is_smoke == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Smoking.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_alcholic.equals("") || is_alcholic == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Alcohol.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else {
                        // new MyIntent(Profile_Blood_Suger_9.this, User_Profile.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    }


                } else if (!S_duration.equals(blood_sugar_in)) {
                    // Toast.makeText(Profile_Blood_Suger_9.this, "D change", Toast.LENGTH_SHORT).show();
               /* SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months,  S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(Profile_Blood_Suger_9.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                ssd.DataConnection();*/
                    if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile10, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile11, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_smoke.equals("") || is_smoke == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Smoking.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_alcholic.equals("") || is_alcholic == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Alcohol.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else {
                        // new MyIntent(Profile_Blood_Suger_9.this, User_Profile.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    }


                } else if (!S_HBA1C_value.equals(HBA1C_val)) {
                    // Toast.makeText(Profile_Blood_Suger_9.this, "D change", Toast.LENGTH_SHORT).show();
               /* SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months,  S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(Profile_Blood_Suger_9.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                ssd.DataConnection();*/
                    if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile10, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile11, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_smoke.equals("") || is_smoke == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Smoking.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_alcholic.equals("") || is_alcholic == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Alcohol.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else {
                        // new MyIntent(Profile_Blood_Suger_9.this, User_Profile.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    }


                } else if (!S_Last_fasting.equals(last_fasting)) {
                    //Toast.makeText(Profile_Blood_Suger_9.this, "D change", Toast.LENGTH_SHORT).show();
               /* SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months,  S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(Profile_Blood_Suger_9.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                ssd.DataConnection();*/
                    if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile10, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile11, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_smoke.equals("") || is_smoke == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Smoking.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_alcholic.equals("") || is_alcholic == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Alcohol.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else {
                        // new MyIntent(Profile_Blood_Suger_9.this, User_Profile.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    }

                } else if (!S_ppvalue.equals(pp_value)) {
                    // Toast.makeText(Profile_Blood_Suger_9.this, "D change", Toast.LENGTH_SHORT).show();
              /*  SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this,go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(Profile_Blood_Suger_9.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                ssd.DataConnection();*/
                    if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile10, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile11, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_smoke.equals("") || is_smoke == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Smoking.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_alcholic.equals("") || is_alcholic == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Alcohol.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else {
                        // new MyIntent(Profile_Blood_Suger_9.this, User_Profile.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    }


                } else if (!months.equals(last_HBA1C)) {
                    // Toast.makeText(Profile_Blood_Suger_9.this, "D change", Toast.LENGTH_SHORT).show();
               /* SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(Profile_Blood_Suger_9.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                ssd.DataConnection();*/
                    if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile10, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile11, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_smoke.equals("") || is_smoke == null) {
                        // new MyIntent(Profile_Blood_Suger_9.this, Smoking.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else if (is_alcholic.equals("") || is_alcholic == null) {
                        //new MyIntent(Profile_Blood_Suger_9.this, Alcohol.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else {
                        // new MyIntent(Profile_Blood_Suger_9.this, User_Profile.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Blood_Suger_9.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, months, S_time, S_duration, S_Last_fasting, S_HBA1C_value, S_ppvalue, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "10");
                        ssd.Data(Profile_Blood_Suger_9.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    }


                } else {
                    // Toast.makeText(Profile_Blood_Suger_9.this, "no change", Toast.LENGTH_SHORT).show();

                    //  new MyIntent(Profile_Blood_Suger_9.this, go_class, R.anim.enter, R.anim.exit);
                    if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, go_profile10, R.anim.enter, R.anim.exit,true);
                    } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, go_profile11, R.anim.enter, R.anim.exit,true);
                    } else if (is_smoke.equals("") || is_smoke == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);
                    } else if (is_alcholic.equals("") || is_alcholic == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, go_profile_drink, R.anim.enter, R.anim.exit,true);
                    } else {
                        new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter, R.anim.exit,true);
                    }
                }
            }

        }
        if (view == prev) {
            onBackPressed();

         /*   SharedPreferences spss = getSharedPreferences("EditPro", MODE_PRIVATE);
            SharedPreferences.Editor editorss = spss.edit();
            editorss.putString("gokey", "9");
            editorss.commit();

            // new MyIntent(Profile_Blood_Suger_9.this, back_class,R.anim.enter2,R.anim.exit2);
            if (gender.equals("female")) {
                if (is_pregnent.equals("yes")) {
                    if (is_diabetic.equals("yes")) {
                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {

                            new MyIntent(Profile_Blood_Suger_9.this, back_profile8, R.anim.enter, R.anim.exit);
                        } else if (child_age.equals("") || child_age == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile6, R.anim.enter, R.anim.exit);
                        } else if (first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile4, R.anim.enter, R.anim.exit);
                        } else if (is_pregnent.equals("") || is_pregnent == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_pregnant, R.anim.enter, R.anim.exit);
                        } else if (weight.equals("") || weight == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                        } else if (height.equals("") || height == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                        } else if (age.equals("") || age == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                        } else {
                            new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                        }
                    } else {
                        if (child_age.equals("") || child_age == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile6, R.anim.enter, R.anim.exit);
                        } else if (first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile4, R.anim.enter, R.anim.exit);
                        } else if (is_pregnent.equals("") || is_pregnent == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_pregnant, R.anim.enter, R.anim.exit);
                        } else if (weight.equals("") || weight == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                        } else if (height.equals("") || height == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                        } else if (age.equals("") || age == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                        } else {
                            new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                        }
                    }


                } else {
                    if (is_diabetic.equals("yes")) {
                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {

                            new MyIntent(Profile_Blood_Suger_9.this, back_profile8, R.anim.enter, R.anim.exit);
                        } else if (breast_feeding.equals("") || breast_feeding == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile5, R.anim.enter, R.anim.exit);
                        } else if (is_pregnent.equals("") || is_pregnent == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_pregnant, R.anim.enter, R.anim.exit);
                        } else if (weight.equals("") || weight == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                        } else if (height.equals("") || height == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                        } else if (age.equals("") || age == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                        } else {
                            new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                        }
                    } else {
                        if (breast_feeding.equals("") || breast_feeding == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile5, R.anim.enter, R.anim.exit);
                        } else if (is_pregnent.equals("") || is_pregnent == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_pregnant, R.anim.enter, R.anim.exit);
                        } else if (weight.equals("") || weight == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                        } else if (height.equals("") || height == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                        } else if (age.equals("") || age == null) {
                            new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                        } else {
                            new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                        }
                    }
                }
            } else {
                if (is_diabetic.equals("yes")) {
                    if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {

                        new MyIntent(Profile_Blood_Suger_9.this, back_profile8, R.anim.enter, R.anim.exit);
                    } else if (weight.equals("") || weight == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                    } else if (height.equals("") || height == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                    } else if (age.equals("") || age == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                    } else {
                        new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                    }
                } else {
                    if (weight.equals("") || weight == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                    } else if (height.equals("") || height == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                    } else if (age.equals("") || age == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                    } else {
                        new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                    }
                }

            }*/


        }
        if (view == home_L) {
            new MyIntent(Profile_Blood_Suger_9.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == profile_L) {
            new MyIntent(Profile_Blood_Suger_9.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == log_L) {
            new MyIntent(Profile_Blood_Suger_9.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == plans_L) {
            new MyIntent(Profile_Blood_Suger_9.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(Profile_Blood_Suger_9.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

      /*SharedPreferences spss = getSharedPreferences("EditPro", MODE_PRIVATE);
        SharedPreferences.Editor editorss = spss.edit();
        editorss.putString("gokey", "9");
        editorss.commit();

        // new MyIntent(Profile_Blood_Suger_9.this, back_class,R.anim.enter2,R.anim.exit2);
        if (gender.equals("female")) {
            if (is_pregnent.equals("yes")) {
                if (is_diabetic.equals("yes")) {
                    if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {

                        new MyIntent(Profile_Blood_Suger_9.this, back_profile8, R.anim.enter, R.anim.exit);
                    } else if (child_age.equals("") || child_age == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile6, R.anim.enter, R.anim.exit);
                    } else if (first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile4, R.anim.enter, R.anim.exit);
                    } else if (is_pregnent.equals("") || is_pregnent == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_pregnant, R.anim.enter, R.anim.exit);
                    } else if (weight.equals("") || weight == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                    } else if (height.equals("") || height == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                    } else if (age.equals("") || age == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                    } else {
                        new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                    }
                } else {
                    if (child_age.equals("") || child_age == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile6, R.anim.enter, R.anim.exit);
                    } else if (first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile4, R.anim.enter, R.anim.exit);
                    } else if (is_pregnent.equals("") || is_pregnent == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_pregnant, R.anim.enter, R.anim.exit);
                    } else if (weight.equals("") || weight == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                    } else if (height.equals("") || height == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                    } else if (age.equals("") || age == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                    } else {
                        new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                    }
                }


            } else {
                if (is_diabetic.equals("yes")) {
                    if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {

                        new MyIntent(Profile_Blood_Suger_9.this, back_profile8, R.anim.enter, R.anim.exit);
                    } else if (breast_feeding.equals("") || breast_feeding == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile5, R.anim.enter, R.anim.exit);
                    } else if (is_pregnent.equals("") || is_pregnent == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_pregnant, R.anim.enter, R.anim.exit);
                    } else if (weight.equals("") || weight == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                    } else if (height.equals("") || height == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                    } else if (age.equals("") || age == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                    } else {
                        new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                    }
                } else {
                    if (breast_feeding.equals("") || breast_feeding == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile5, R.anim.enter, R.anim.exit);
                    } else if (is_pregnent.equals("") || is_pregnent == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_pregnant, R.anim.enter, R.anim.exit);
                    } else if (weight.equals("") || weight == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                    } else if (height.equals("") || height == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                    } else if (age.equals("") || age == null) {
                        new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                    } else {
                        new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                    }
                }
            }
        } else {
            if (is_diabetic.equals("yes")) {
                if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {

                    new MyIntent(Profile_Blood_Suger_9.this, back_profile8, R.anim.enter, R.anim.exit);
                } else if (weight.equals("") || weight == null) {
                    new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                } else if (height.equals("") || height == null) {
                    new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                } else if (age.equals("") || age == null) {
                    new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                } else {
                    new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                }
            } else {
                if (weight.equals("") || weight == null) {
                    new MyIntent(Profile_Blood_Suger_9.this, back_profile3, R.anim.enter, R.anim.exit);
                } else if (height.equals("") || height == null) {
                    new MyIntent(Profile_Blood_Suger_9.this, back_profile2, R.anim.enter, R.anim.exit);
                } else if (age.equals("") || age == null) {
                    new MyIntent(Profile_Blood_Suger_9.this, back_profile1, R.anim.enter, R.anim.exit);
                } else {
                    new MyIntent(Profile_Blood_Suger_9.this, back_class, R.anim.enter2, R.anim.exit2);
                }
            }

        }*/


    }

/*

    class Base extends BaseAdapter {

        @Override
        public int getCount() {
            return hlist.size();

        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View v = getLayoutInflater().inflate(R.layout.hb1cbase,null);
            TextView item = (TextView)v.findViewById(R.id.item);

            HB1CModel hb = hlist.get(i);

            item.setText(hb.getName());

//            if (i == selection){
//                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                    item.setBackgroundDrawable( ContextCompat.getDrawable(ctx,R.drawable.shape_cornor_round) );
//                } else {
//                    item.setBackground( ContextCompat.getDrawable(ctx,R.drawable.shape_cornor_round));
//                }
//            }else {
//
//            }
            return v;
        }
    }
*/

}
