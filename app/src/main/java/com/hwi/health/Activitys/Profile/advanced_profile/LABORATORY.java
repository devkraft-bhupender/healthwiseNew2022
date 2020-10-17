package com.hwi.health.Activitys.Profile.advanced_profile;

import android.content.SharedPreferences;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;

import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.First.SetSecondStepData;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.edit_profile.Edit_profile_Activity;

public class LABORATORY extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    TextInputEditText et_Hemoglobin, et_blood_sugar, et_Hematocrit, et_Total_cholesterol, et_LDL_cholesterol, et_HDL_cholesterol, et_Triglycerides, et_Vitamin_levels, et_Vitamin_B12_levels;
    String S_weight, S_Hemoglobin, S_blood_sugar, S_dob, S_feet, S_inch, S_Waist, S_cm, S_Blood_Pressure, S_Diastolic, S_Hematocrit, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels;
    ImageView next, prev;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    String randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;
    Class go_class;
    Class back_class,back_body;
    ActionBar ab;
    String edit_key,key,get_dob,diet_weight_loss;
    RelativeLayout R_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratory);

        et_Hemoglobin = (TextInputEditText) findViewById(R.id.Hemoglobin);
        et_Hematocrit = (TextInputEditText) findViewById(R.id.Hematocrit);
        et_blood_sugar = (TextInputEditText) findViewById(R.id.blood_sugar);
        et_Total_cholesterol = (TextInputEditText) findViewById(R.id.Total_cholesterol);
        et_LDL_cholesterol = (TextInputEditText) findViewById(R.id.LDL_cholesterol);
        et_HDL_cholesterol = (TextInputEditText) findViewById(R.id.HDL_cholesterol);
        et_Triglycerides = (TextInputEditText) findViewById(R.id.Triglycerides);
        et_Vitamin_levels = (TextInputEditText) findViewById(R.id.Vitamin_levels);
        et_Vitamin_B12_levels = (TextInputEditText) findViewById(R.id.Vitamin_B12_levels);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        R_layout = (RelativeLayout) findViewById(R.id.bottom);

        go_class = RISK_RELATED_QUESTIONS.class;
        back_class = User_Profile.class;
        back_body = Body_PARAMETERS.class;

     SharedPreferences sp1 = getSharedPreferences("Bottom", MODE_PRIVATE);

        key = sp1.getString("bt_key", "");
        /*   if (key.equals("1")){
            R_layout.setVisibility(View.VISIBLE);
        }else {
            R_layout.setVisibility(View.GONE);
        }*/

        try{
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            edit_key = sharedPreferences.getString("edit_key","");
            if (edit_key.equals("15")){
                go_class = Edit_profile_Activity.class;
                back_class = Edit_profile_Activity.class;
                back_body = Edit_profile_Activity.class;
                Log.e("keyyyy",edit_key+"");
                sharedPreferences.edit().clear().commit();
            }else {

                go_class = RISK_RELATED_QUESTIONS.class;
                back_class = User_Profile.class;
                back_body = Body_PARAMETERS.class;
                /*if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null ){
                }else {
                    new MyIntent(Profile_Insulin_8.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                }*/
            }
        }catch (Exception e){

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
            activity_level_one = sp.getString("activity_level_one", "");
            activity_level_two = sp.getString("activity_level_two", "");
            food_habits = sp.getString("food_habits", "");
            any_heart_disease = sp.getString("any_heart_disease", "");
            is_alcholic = sp.getString("is_alcholic", "");
            alcohol_how_often = sp.getString("alcohol_how_often", "");
            is_smoke = sp.getString("is_smoke", "");
            smoke_how_often = sp.getString("smoke_how_often", "");
//            String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;

            diet_weight_loss = sp.getString("weg_loss", "");
            get_dob = sp.getString("Dob", "");


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
            et_Hemoglobin.setText(Hemoglobin);
            et_Hematocrit.setText(Hematocrit);
            et_blood_sugar.setText(blood_sugar);
            et_Total_cholesterol.setText(Total_cholesterol);
            et_LDL_cholesterol.setText(LDL_cholesterol);
            et_HDL_cholesterol.setText(HDL_cholesterol);
            et_Triglycerides.setText(Triglycerides);
            et_Vitamin_levels.setText(Vitamin_levels);
            et_Vitamin_B12_levels.setText(Vitamin_B12_levels);




        } catch (Exception e) {

        }
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(LABORATORY.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(LABORATORY.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(LABORATORY.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(LABORATORY.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == next) {

            S_Hemoglobin = et_Hemoglobin.getText().toString();
            S_Hematocrit = et_Hematocrit.getText().toString();
            S_blood_sugar = et_blood_sugar.getText().toString();
            S_Total_cholesterol = et_Total_cholesterol.getText().toString();
            S_LDL_cholesterol = et_LDL_cholesterol.getText().toString();
            S_HDL_cholesterol = et_HDL_cholesterol.getText().toString();
            S_Triglycerides = et_Triglycerides.getText().toString();
            S_Vitamin_levels = et_Vitamin_levels.getText().toString();
            S_Vitamin_B12_levels = et_Vitamin_B12_levels.getText().toString();
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            if (!S_Hemoglobin.equals(Hemoglobin)) {

                /*SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                ssd.DataConnection();*/
                 if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                  //  new MyIntent(LABORATORY.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                     SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                     ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                     ssd.DataConnection();
                }
                else {
                   // new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
                     SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                     ssd.Data(LABORATORY.this, back_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                     ssd.DataConnection();
                }

                //   new MyIntent(Profile_Breast_Feeding_5.this, back_class, R.anim.enter2, R.anim.exit2);
            } else if (!S_Hematocrit.equals(Hematocrit)) {
              /*  SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                ssd.DataConnection();*/
                if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                    //  new MyIntent(LABORATORY.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
                else {
                    // new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, back_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
            } else if (!S_blood_sugar.equals(blood_sugar)) {
              /*  SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                ssd.DataConnection();*/
                if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                    //  new MyIntent(LABORATORY.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
                else {
                    // new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, back_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
            } else if (!S_Total_cholesterol.equals(Total_cholesterol)) {
               /* SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                ssd.DataConnection();*/
                if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                    //  new MyIntent(LABORATORY.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
                else {
                    // new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, back_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
            } else if (!S_LDL_cholesterol.equals(LDL_cholesterol)) {
               /* SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                ssd.DataConnection();*/
                if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                    //  new MyIntent(LABORATORY.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
                else {
                    // new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, back_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
            } else if (!S_HDL_cholesterol.equals(HDL_cholesterol)) {
               /* SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                ssd.DataConnection();*/
                if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                    //  new MyIntent(LABORATORY.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
                else {
                    // new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, back_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
            } else if (!S_Triglycerides.equals(Triglycerides)) {
                /*SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                ssd.DataConnection();*/
                if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                    //  new MyIntent(LABORATORY.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
                else {
                    // new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, back_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
            } else if (!S_Vitamin_levels.equals(Vitamin_levels)) {
                /*SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                ssd.DataConnection();*/
                if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                    //  new MyIntent(LABORATORY.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
                else {
                    // new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, back_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
            } else if (!S_Vitamin_B12_levels.equals(Vitamin_B12_levels)) {
               /* SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yes");
                ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                ssd.DataConnection();*/
                if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                    //  new MyIntent(LABORATORY.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, go_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
                else {
                    // new MyIntent(LABORATORY.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(LABORATORY.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"no");
                    ssd.Data(LABORATORY.this, back_class, randnoo, S_Hemoglobin, S_Hematocrit, S_blood_sugar, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();
                }
            }
            else {
               // new MyIntent(LABORATORY.this, go_class, R.anim.enter, R.anim.exit);
                if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null){
                    new MyIntent(LABORATORY.this, go_class, R.anim.enter, R.anim.exit);
                }
                else {
                    new MyIntent(LABORATORY.this, back_class, R.anim.enter, R.anim.exit);
                }
            }


        }
        if (v == prev) {
            //Toast.makeText(this, "prevvvvv", Toast.LENGTH_SHORT).show();

            if (Senter_inc.equals("") || Senter_inc == null || Senter_cmc.equals("") || Senter_cmc == null || SSystolic.equals("") || SSystolic == null || SDiastolic.equals("") || SDiastolic == null ){
                new MyIntent(LABORATORY.this, back_body, R.anim.enter, R.anim.exit);
            }
            else {
                new MyIntent(LABORATORY.this, back_class, R.anim.enter, R.anim.exit);
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Senter_inc.equals("") || Senter_inc == null || Senter_cmc.equals("") || Senter_cmc == null || SSystolic.equals("") || SSystolic == null || SDiastolic.equals("") || SDiastolic == null ){
            new MyIntent(LABORATORY.this, back_body, R.anim.enter, R.anim.exit);
        }
        else {
            new MyIntent(LABORATORY.this, back_class, R.anim.enter, R.anim.exit);
        }
    }
}