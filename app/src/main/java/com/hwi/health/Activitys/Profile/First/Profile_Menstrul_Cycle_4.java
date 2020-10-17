package com.hwi.health.Activitys.Profile.First;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Profile_Menstrul_Cycle_4 extends AppCompatActivity implements View.OnClickListener {
    ActionBar ab;
    SeekBar waightseekBar;
    EditText enter_waight, enter_mc_date;
    ImageView next, prev;
    int YEAR, MONTH, DAY;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String strDate = "", edit_key;
    String pre_weight, first_day, key, get_dob, diet_weight_loss;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Context ctx;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    Class go_class,go_profile6,go_profile7,go_profile8,go_profile9,go_profile10,go_profile11,go_profile_smoke,go_profile_drink;

    RelativeLayout R_layout;
    String randnoo, age, genderselectin, weight, hight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;


    Class back_class,back_profile1,back_profile2,back_profile3,back_pregnant;
    Class go_two_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__menstrul__cycle);

        waightseekBar = (SeekBar) findViewById(R.id.weight_seekBar);
        enter_waight = (EditText) findViewById(R.id.enter_waight);
        enter_mc_date = (EditText) findViewById(R.id.mc_date);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        R_layout = (RelativeLayout) findViewById(R.id.bottom);
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

        waightseekBar.setVisibility(View.GONE);

        enter_mc_date.setOnClickListener(this);
        ctx = this;

        SharedPreferences sp1 = getSharedPreferences("Bottom", MODE_PRIVATE);

        key = sp1.getString("bt_key", "");
       /* if (key.equals("1")){
            R_layout.setVisibility(View.VISIBLE);
        }else {
            R_layout.setVisibility(View.GONE);
        }*/
        SeekBarM();
        go_profile7 = Profile_Diabetic_7.class;
        go_profile8 = Profile_Insulin_8.class;
        go_profile9 = Profile_Blood_Suger_9.class;
        go_profile10 = Profile_Active_10.class;
        go_profile11 = Profile_Food_habits_11.class;
        go_profile_smoke = Smoking.class;
        go_profile_drink = Alcohol.class;
        go_profile6 = Profile_Child_Age_6.class;

        go_class = Profile_Child_Age_6.class;
        back_class = User_Profile.class;
        back_profile1 = Profile_Step1.class;
        back_profile2 = Profile_Step2.class;
        back_profile3 = Profile_Step3.class;
        back_pregnant = Profile_Pregnant_3.class;




        try{
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            edit_key = sharedPreferences.getString("edit_key", "");
            Log.e("edit_keyyy",edit_key+"");
            if (edit_key.equals("4")) {

                go_class = Edit_profile_Activity.class;
                back_class = Edit_profile_Activity.class;
                go_profile7 = Edit_profile_Activity.class;
                go_profile8 = Edit_profile_Activity.class;
                go_profile9 = Edit_profile_Activity.class;
                go_profile10 = Edit_profile_Activity.class;
                go_profile11 = Edit_profile_Activity.class;
                go_profile_smoke = Edit_profile_Activity.class;
                go_profile_drink = Edit_profile_Activity.class;
                go_profile6 = Edit_profile_Activity.class;
                back_profile1 = Edit_profile_Activity.class;
                back_profile2 = Edit_profile_Activity.class;
                back_profile3 = Edit_profile_Activity.class;
                back_pregnant = Edit_profile_Activity.class;

                sharedPreferences.edit().clear().commit();
            } else {
                go_profile7 = Profile_Diabetic_7.class;
                go_profile8 = Profile_Insulin_8.class;
                go_profile9 = Profile_Blood_Suger_9.class;
                go_profile10 = Profile_Active_10.class;
                go_profile11 = Profile_Food_habits_11.class;
                go_profile_smoke = Smoking.class;
                go_profile_drink = Alcohol.class;
                go_profile6 = Profile_Child_Age_6.class;
                go_class = Profile_Child_Age_6.class;
                back_class = User_Profile.class;
                back_profile1 = Profile_Step1.class;
                back_profile2 = Profile_Step2.class;
                back_profile3 = Profile_Step3.class;
                back_pregnant = Profile_Pregnant_3.class;
            }
        }catch (Exception e){

        }
        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            genderselectin = sp.getString("gender", "");
            age = sp.getString("age", "");
            user_id = sp.getString("Userid", "");
            sp.getString("key", "");
            hight = sp.getString("height", "");
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
            sp.getString("is_login", "");
            diet_weight_loss = sp.getString("weg_loss", "");
            get_dob = sp.getString("Dob", "");

            HBA1C_val = sp.getString("HBA1C_value", "");

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

            enter_waight.setText(pre_pregnancy_weight);
            enter_mc_date.setText(first_day_of_last_menstrual_cycle);
            // Toast.makeText(ctx, ""+pre_pregnancy_weight+"  "+first_day_of_last_menstrual_cycle, Toast.LENGTH_SHORT).show();
            int wet = Integer.parseInt(pre_pregnancy_weight);
            int wet2 = wet;
            int wet3 = wet2;
            waightseekBar.setProgress(wet3);

        } catch (Exception e) {
            Log.e("Errr ", e + "");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

       /* SharedPreferences spss = getSharedPreferences("EditPro", MODE_PRIVATE);
        SharedPreferences.Editor editorss = spss.edit();
        editorss.putString("gokey", "4");
        editorss.commit();*/
       /* if (key.equals("2")) {
            new MyIntent(Profile_Menstrul_Cycle_4.this, Profile_Pregnant_3.class, R.anim.enter, R.anim.exit);
        } else {
            new MyIntent(Profile_Menstrul_Cycle_4.this, back_class, R.anim.enter2, R.anim.exit2);
        }*/
       /* if (is_pregnent.equals("") || is_pregnent == null){
            new MyIntent(Profile_Menstrul_Cycle_4.this, back_pregnant, R.anim.enter, R.anim.exit);
        } else if (weight.equals("") || weight == null){
            new MyIntent(Profile_Menstrul_Cycle_4.this, back_profile3, R.anim.enter, R.anim.exit);
        }
        else if (hight.equals("") || hight == null){
            new MyIntent(Profile_Menstrul_Cycle_4.this, back_profile2, R.anim.enter, R.anim.exit);
        }
        else if (age.equals("") || age == null){
            new MyIntent(Profile_Menstrul_Cycle_4.this, back_profile1, R.anim.enter, R.anim.exit);
        }
        else {
            new MyIntent(Profile_Menstrul_Cycle_4.this, back_class, R.anim.enter2, R.anim.exit2);
        }*/
       finish();

    }

    @Override
    public void onClick(View view) {

        if (view == enter_mc_date) {
            DatePicker();
        }

        if (view == next) {

            SharedPreferences spss = getSharedPreferences("EditPro",MODE_PRIVATE);
            SharedPreferences.Editor editorss =spss.edit();
            if (genderselectin.equals("female")){
                editorss.putString("gokey","5");
            }else {
                editorss.putString("gokey","7");
            }
            editorss.commit();

            pre_weight = enter_waight.getText().toString();
            first_day = enter_mc_date.getText().toString();

            if (pre_weight.isEmpty() || first_day.isEmpty()){
                Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
            }else {
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();
                if (!pre_weight.equals(pre_pregnancy_weight)) {

                    if (child_age.equals("") || child_age == null) {
                        // new MyIntent(Profile_Pregnant_3.this, Profile_Child_Age_6.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile6, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                        ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile6, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();


                    } else if (is_diabetic.equals("") || is_diabetic == null) {
                        //new MyIntent(Profile_Pregnant_3.this, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile7, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                        ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile7, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();

                    } else {
                        if (is_diabetic.equals("yes")) {
                            if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                                //new MyIntent(Profile_Pregnant_3.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile8, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile8, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile9, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile9, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile10, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile11, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (is_smoke.equals("") || is_smoke == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Smoking.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile_smoke, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (is_alcholic.equals("") || is_alcholic == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Alcohol.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile_drink, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else {
                                // new MyIntent(Profile_Pregnant_3.this, User_Profile.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, back_class, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            }


                        } else {
                            if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile10, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile11, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (is_smoke.equals("") || is_smoke == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Smoking.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile_smoke, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (is_alcholic.equals("") || is_alcholic == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Alcohol.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile_drink, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else {
                                // new MyIntent(Profile_Pregnant_3.this, User_Profile.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, back_class, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            }
                        }
                    }
                    //  Toast.makeText(ctx, "w change", Toast.LENGTH_SHORT).show();

                } else if (!first_day.equals(first_day_of_last_menstrual_cycle)) {
               /* SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_class, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                ssd.Data(Profile_Menstrul_Cycle_4.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                ssd.DataConnection();*/

                    if (child_age.equals("") || child_age == null) {
                        // new MyIntent(Profile_Pregnant_3.this, Profile_Child_Age_6.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile6, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                        ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile6, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();


                    } else if (is_diabetic.equals("") || is_diabetic == null) {
                        //new MyIntent(Profile_Pregnant_3.this, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile7, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                        ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile7, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();

                    } else {
                        if (is_diabetic.equals("yes")) {
                            if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                                //new MyIntent(Profile_Pregnant_3.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile8, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile8, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile9, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile9, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile10, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile11, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (is_smoke.equals("") || is_smoke == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Smoking.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile_smoke, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (is_alcholic.equals("") || is_alcholic == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Alcohol.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile_drink, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else {
                                // new MyIntent(Profile_Pregnant_3.this, User_Profile.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, back_class, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            }


                        } else {
                            if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile10, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile11, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (is_smoke.equals("") || is_smoke == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Smoking.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile_smoke, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else if (is_alcholic.equals("") || is_alcholic == null) {
                                // new MyIntent(Profile_Pregnant_3.this, Alcohol.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, go_profile_drink, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            } else {
                                // new MyIntent(Profile_Pregnant_3.this, User_Profile.class, R.anim.enter, R.anim.exit);
                                SetSecondStepData ssd = new SetSecondStepData(Profile_Menstrul_Cycle_4.this, back_class, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day, pre_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no","5");
                                ssd.Data(Profile_Menstrul_Cycle_4.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                                ssd.DataConnection();

                            }
                        }
                    }
                    //  Toast.makeText(ctx, "d change", Toast.LENGTH_SHORT).show();
                } else {
                    // new MyIntent(Profile_Menstrul_Cycle_4.this, go_class, R.anim.enter, R.anim.exit);
                    if (child_age.equals("") || child_age == null) {
                        new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile6, R.anim.enter, R.anim.exit,true);


                    } else if (is_diabetic.equals("") || is_diabetic == null) {
                        new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile7, R.anim.enter, R.anim.exit,true);


                    } else {
                        if (is_diabetic.equals("yes")) {
                            if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile8, R.anim.enter, R.anim.exit,true);


                            } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile9, R.anim.enter, R.anim.exit,true);

                            } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile10, R.anim.enter, R.anim.exit,true);


                            } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile11, R.anim.enter, R.anim.exit,true);


                            } else if (is_smoke.equals("") || is_smoke == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);

                            } else if (is_alcholic.equals("") || is_alcholic == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile_drink, R.anim.enter, R.anim.exit,true);


                            } else {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, back_class, R.anim.enter, R.anim.exit,true);

                            }


                        } else {
                            if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile10, R.anim.enter, R.anim.exit,true);


                            } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile11, R.anim.enter, R.anim.exit,true);


                            } else if (is_smoke.equals("") || is_smoke == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);

                            } else if (is_alcholic.equals("") || is_alcholic == null) {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, go_profile_drink, R.anim.enter, R.anim.exit,true);


                            } else {
                                new MyIntent(Profile_Menstrul_Cycle_4.this, back_class, R.anim.enter, R.anim.exit,true);

                            }
                        }
                    }
                    //  Toast.makeText(ctx, "no change", Toast.LENGTH_SHORT).show();

                }
            }
        }
        if (view == prev) {
          /*  SharedPreferences spss = getSharedPreferences("EditPro", MODE_PRIVATE);
            SharedPreferences.Editor editorss = spss.edit();
            editorss.putString("gokey", "4");
            editorss.commit();*/

           /* if (key.equals("2")) {
                new MyIntent(Profile_Menstrul_Cycle_4.this, Profile_Pregnant_3.class, R.anim.enter, R.anim.exit);
            } else {
                new MyIntent(Profile_Menstrul_Cycle_4.this, back_class, R.anim.enter2, R.anim.exit2);
            }*/

           onBackPressed();
             /*   new MyIntent(Profile_Menstrul_Cycle_4.this, back_class, R.anim.enter2, R.anim.exit2);*/
        }
        if (view == home_L) {
            new MyIntent(Profile_Menstrul_Cycle_4.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == profile_L) {
            new MyIntent(Profile_Menstrul_Cycle_4.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == log_L) {
            new MyIntent(Profile_Menstrul_Cycle_4.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == plans_L) {
            new MyIntent(Profile_Menstrul_Cycle_4.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(Profile_Menstrul_Cycle_4.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    void SeekBarM() {
        waightseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgress = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //  int fir = 30;
                int ww = progress * 2;
                //  seekBarProgress = fir + ww;
                enter_waight.setText(progress + "");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });
    }


    void DatePicker() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(Profile_Menstrul_Cycle_4.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        YEAR = year;
                        MONTH = monthOfYear;
                        DAY = dayOfMonth;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(YEAR, MONTH, DAY);

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM, yyyy");

                        strDate = format.format(calendar.getTime());
                        String strDate2 = format2.format(calendar.getTime());

                        Log.e("Date = ", strDate + "    " + first_day_of_last_menstrual_cycle);


                        enter_mc_date.setText(strDate + "");
                    }
                }, mYear, mMonth, mDay);


        dpd.show();
    }


}
