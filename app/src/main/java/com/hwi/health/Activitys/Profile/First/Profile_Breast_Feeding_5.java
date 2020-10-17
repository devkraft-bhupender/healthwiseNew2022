package com.hwi.health.Activitys.Profile.First;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.edit_profile.Edit_profile_Activity;

public class Profile_Breast_Feeding_5 extends AppCompatActivity implements View.OnClickListener {
    ImageView next, prev;
    ActionBar ab;
    RadioButton yes, no;
    String breastfeeding = "", key, edit_key, get_dob, diet_weight_loss;
    Context ctx;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    RelativeLayout R_layout;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    Class go_class, go_profile7, go_profile8, go_profile9, go_profile10, go_profile11, go_profile_smoke, go_profile_drink;

    String randnoo, age, genderselectin, weight, hight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;

    Class back_class, back_profile1, back_profile2, back_profile3, back_pregnant;
    Class go_two_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__breast__feeding);
        ctx = this;
        yes = (RadioButton) findViewById(R.id.yes);
        no = (RadioButton) findViewById(R.id.no);
        R_layout = (RelativeLayout) findViewById(R.id.bottom);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
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
        go_profile7 = Profile_Diabetic_7.class;
        go_profile8 = Profile_Insulin_8.class;
        go_profile9 = Profile_Blood_Suger_9.class;
        go_profile10 = Profile_Active_10.class;
        go_profile11 = Profile_Food_habits_11.class;
        go_profile_smoke = Smoking.class;
        go_profile_drink = Alcohol.class;
        go_class = Profile_Diabetic_7.class;
        back_class = User_Profile.class;
        back_profile1 = Profile_Step1.class;
        back_profile2 = Profile_Step2.class;
        back_profile3 = Profile_Step3.class;
        back_pregnant = Profile_Pregnant_3.class;

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            edit_key = sharedPreferences.getString("edit_key", "");
            Log.e("edit_keyyy", edit_key + "");
            if (edit_key.equals("13")) {

                go_class = Edit_profile_Activity.class;
                back_class = Edit_profile_Activity.class;
                go_profile7 = Edit_profile_Activity.class;
                go_profile8 = Edit_profile_Activity.class;
                go_profile9 = Edit_profile_Activity.class;
                go_profile10 = Edit_profile_Activity.class;
                go_profile11 = Edit_profile_Activity.class;
                go_profile_smoke = Edit_profile_Activity.class;
                go_profile_drink = Edit_profile_Activity.class;
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
                go_class = Profile_Diabetic_7.class;
                back_class = User_Profile.class;
                back_profile1 = Profile_Step1.class;
                back_profile2 = Profile_Step2.class;
                back_profile3 = Profile_Step3.class;
                back_pregnant = Profile_Pregnant_3.class;

            }
        } catch (Exception e) {

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


            //  Toast.makeText(this, ""+pre_pregnancy_weight+"  "+first_day_of_last_menstrual_cycle, Toast.LENGTH_SHORT).show();


            if (breast_feeding.equals("yes")) {
                yes.setChecked(true);

            } else {
                no.setChecked(true);

            }


        } catch (Exception e) {
            Log.e("Errr ", e + "");
        }


        next.setOnClickListener(this);
        prev.setOnClickListener(this);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == next) {

            if (yes.isChecked()) {
                Toast.makeText(Profile_Breast_Feeding_5.this, "This app is not designed for your needs", Toast.LENGTH_SHORT).show();
                return;


            }
            if (no.isChecked()) {
                breastfeeding = "no";
            }

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();

            if (!breastfeeding.equals(breast_feeding)) {

                if (is_diabetic.equals("") || is_diabetic == null) {
                    //  new MyIntent(Profile_Pregnant_3.this, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile7, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                    ssd.Data(Profile_Breast_Feeding_5.this, go_profile7, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                    ssd.DataConnection();

                } else {
                    if (is_diabetic.equals("yes")) {
                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                            // new MyIntent(Profile_Pregnant_3.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile8, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile8, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                            // new MyIntent(Profile_Pregnant_3.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile9, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile9, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                            // new MyIntent(Profile_Pregnant_3.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile10, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                            // new MyIntent(Profile_Pregnant_3.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile11, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else if (is_smoke.equals("") || is_smoke == null) {
                            //new MyIntent(Profile_Pregnant_3.this, Smoking.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile_smoke, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                            // new MyIntent(Profile_Pregnant_3.this, Alcohol.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile_drink, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else {
                            //  new MyIntent(Profile_Pregnant_3.this, User_Profile.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, back_class, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        }


                    } else {
                        if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                            // new MyIntent(Profile_Pregnant_3.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile10, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                            // new MyIntent(Profile_Pregnant_3.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile11, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else if (is_smoke.equals("") || is_smoke == null) {
                            //new MyIntent(Profile_Pregnant_3.this, Smoking.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile_smoke, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                            // new MyIntent(Profile_Pregnant_3.this, Alcohol.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, go_profile_drink, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        } else {
                            //  new MyIntent(Profile_Pregnant_3.this, User_Profile.class, R.anim.enter, R.anim.exit);
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Breast_Feeding_5.this, back_class, randnoo, genderselectin, age, hight, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breastfeeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "6");
                            ssd.Data(Profile_Breast_Feeding_5.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                            ssd.DataConnection();

                        }


                    }

                    //  new MyIntent(Profile_Pregnant_3.this, Profile_Diabetic_7.class,R.anim.enter,R.anim.exit);
                }


            } else {
                // Toast.makeText(Profile_Breast_Feeding_5.this, "no change", Toast.LENGTH_SHORT).show();

                //  new MyIntent(Profile_Breast_Feeding_5.this, go_class, R.anim.enter, R.anim.exit);
                if (is_diabetic.equals("") || is_diabetic == null) {
                    new MyIntent(Profile_Breast_Feeding_5.this, go_profile7, R.anim.enter, R.anim.exit,true);


                } else {
                    if (is_diabetic.equals("yes")) {
                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile8, R.anim.enter, R.anim.exit,true);


                        } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile9, R.anim.enter, R.anim.exit,true);


                        } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile10, R.anim.enter, R.anim.exit);


                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile11, R.anim.enter, R.anim.exit);


                        } else if (is_smoke.equals("") || is_smoke == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile_smoke, R.anim.enter, R.anim.exit);


                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile_drink, R.anim.enter, R.anim.exit);


                        } else {
                            new MyIntent(Profile_Breast_Feeding_5.this, back_class, R.anim.enter, R.anim.exit);


                        }


                    } else {
                        if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile10, R.anim.enter, R.anim.exit,true);


                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile11, R.anim.enter, R.anim.exit,true);


                        } else if (is_smoke.equals("") || is_smoke == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);


                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                            new MyIntent(Profile_Breast_Feeding_5.this, go_profile_drink, R.anim.enter, R.anim.exit,true);


                        } else {
                            new MyIntent(Profile_Breast_Feeding_5.this, back_class, R.anim.enter, R.anim.exit,true);


                        }
                    }

                    //  new MyIntent(Profile_Pregnant_3.this, Profile_Diabetic_7.class,R.anim.enter,R.anim.exit);
                }


            }
        }
        if (view == prev) {

            onBackPressed();
            return;
          /*  SharedPreferences spss = getSharedPreferences("EditPro", MODE_PRIVATE);
            SharedPreferences.Editor editorss = spss.edit();
            editorss.putString("gokey", "5");
            editorss.commit();

            // new MyIntent(Profile_Breast_Feeding_5.this, back_class, R.anim.enter2, R.anim.exit2);
           /* if (key.equals("2")) {
                new MyIntent(Profile_Breast_Feeding_5.this, Profile_Pregnant_3.class, R.anim.enter, R.anim.exit);
            }else {
                new MyIntent(Profile_Breast_Feeding_5.this, back_class, R.anim.enter2, R.anim.exit2);
            }*/

        }



    }
}
