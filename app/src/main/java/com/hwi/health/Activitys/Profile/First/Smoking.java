package com.hwi.health.Activitys.Profile.First;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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

public class Smoking extends AppCompatActivity implements View.OnClickListener {
    ImageView next, prev;
    ActionBar ab;
    RadioButton yes, no;
    EditText count;
    String S_do = "";
    String S_often = "0",key,edit_key,get_dob,diet_weight_loss;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    LinearLayout smoke_layout;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    Class go_class;
    String randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, S_HBA1C_value, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;
    Context ctx;
    RelativeLayout R_layout;

    Class back_class,back_profile1,back_profile2,back_profile3,back_pregnant,back_profile5,back_profile6,back_profile4,back_profile7,back_profile8,back_profile9,back_profile10,back_profile11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoking);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        yes = (RadioButton) findViewById(R.id.yes);
        no = (RadioButton) findViewById(R.id.no);
        count = (EditText) findViewById(R.id.count);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        smoke_layout = (LinearLayout) findViewById(R.id.smoke);
        R_layout = (RelativeLayout) findViewById(R.id.bottom);
        ctx = this;
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
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

        go_class = Alcohol.class;
        back_class = User_Profile.class;
        back_profile1 = Profile_Step1.class;
        back_profile2 = Profile_Step2.class;
        back_profile3 = Profile_Step3.class;
        back_pregnant = Profile_Pregnant_3.class;
        back_profile5 = Profile_Breast_Feeding_5.class;
        back_profile6 = Profile_Child_Age_6.class;
        back_profile4 = Profile_Menstrul_Cycle_4.class;
        back_profile7 = Profile_Diabetic_7.class;
        back_profile8 = Profile_Insulin_8.class;
        back_profile9 = Profile_Blood_Suger_9.class;
        back_profile10 = Profile_Active_10.class;
        back_profile11 = Profile_Food_habits_11.class;

        try{
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            edit_key = sharedPreferences.getString("edit_key","");
            Log.e("edit_keyyy",edit_key+"");
            if (edit_key.equals("11")){
                go_class = Edit_profile_Activity.class;
                back_class = Edit_profile_Activity.class;
                back_profile1 = Edit_profile_Activity.class;
                back_profile2 = Edit_profile_Activity.class;
                back_profile3 = Edit_profile_Activity.class;
                back_pregnant = Edit_profile_Activity.class;
                back_profile5 = Edit_profile_Activity.class;
                back_profile6 = Edit_profile_Activity.class;
                back_profile4 = Edit_profile_Activity.class;
                back_profile7 = Edit_profile_Activity.class;
                back_profile8 = Edit_profile_Activity.class;
                back_profile9 = Edit_profile_Activity.class;
                back_profile10 = Edit_profile_Activity.class;
                back_profile11 = Edit_profile_Activity.class;
                sharedPreferences.edit().clear().commit();
            }else {

                go_class = Alcohol.class;
                back_class = User_Profile.class;
                back_profile1 = Profile_Step1.class;
                back_profile2 = Profile_Step2.class;
                back_profile3 = Profile_Step3.class;
                back_pregnant = Profile_Pregnant_3.class;
                back_profile5 = Profile_Breast_Feeding_5.class;
                back_profile6 = Profile_Child_Age_6.class;
                back_profile4 = Profile_Menstrul_Cycle_4.class;
                back_profile7 = Profile_Diabetic_7.class;
                back_profile8 = Profile_Insulin_8.class;
                back_profile9 = Profile_Blood_Suger_9.class;
                back_profile10 = Profile_Active_10.class;
                back_profile11 = Profile_Food_habits_11.class;

              /*  if (is_smoke.equals("") || is_smoke == null || smoke_how_often.equals("") || smoke_how_often == null ){
                }else {
                    new MyIntent(Smoking.this, Alcohol.class, R.anim.enter, R.anim.exit);
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
            sp.getString("is_login", "");
            diet_weight_loss = sp.getString("weg_loss", "");
            get_dob = sp.getString("Dob", "");

            S_HBA1C_value = sp.getString("HBA1C_value", "");

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

            if (is_smoke.equals("yes")) {
                yes.setChecked(true);
                smoke_layout.setVisibility(View.VISIBLE);
            } else {
                no.setChecked(true);
                smoke_layout.setVisibility(View.INVISIBLE);
            }
            count.setText(smoke_how_often);



            //  Toast.makeText(ctx, ""+is_smoke, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }

    }

    @Override
    public void onClick(View v) {
        if (v == next) {
            S_often = count.getText().toString();

            if (yes.isChecked())
                S_do = "yes";

            else if (no.isChecked())
                S_do = "no";


            if (S_often.equals("") && S_do.equals("yes")){
                Toast.makeText(ctx, "Please fill How Often in a Day", Toast.LENGTH_LONG).show();
            }else {

                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();

                if (!S_do.equals(is_smoke)) {

                   if (is_alcholic.equals("") || is_alcholic == null) {
                       // new MyIntent(Smoking.this, Alcohol.class, R.anim.enter, R.anim.exit);
                       SetSecondStepData ssd = new SetSecondStepData(Smoking.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, S_HBA1C_value, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, S_do, S_often, user_id,get_dob,diet_weight_loss,"no","13");
                       ssd.Data(Smoking.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                       ssd.DataConnection();
                    } else {
                        //new MyIntent(Smoking.this, User_Profile.class, R.anim.enter, R.anim.exit);
                       SetSecondStepData ssd = new SetSecondStepData(Smoking.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, S_HBA1C_value, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, S_do, S_often, user_id,get_dob,diet_weight_loss,"no","13");
                       ssd.Data(Smoking.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                       ssd.DataConnection();
                    }

                } else if (!S_often.equals(smoke_how_often)) {
                    //  Toast.makeText(Smoking.this, "D change", Toast.LENGTH_SHORT).show();
                   /* SetSecondStepData ssd = new SetSecondStepData(Smoking.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, S_HBA1C_value, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, S_do, S_often, user_id,get_dob,diet_weight_loss,"yes");
                    ssd.Data(Smoking.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                    ssd.DataConnection();*/
                    if (is_alcholic.equals("") || is_alcholic == null) {
                        // new MyIntent(Smoking.this, Alcohol.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Smoking.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, S_HBA1C_value, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, S_do, S_often, user_id,get_dob,diet_weight_loss,"no","13");
                        ssd.Data(Smoking.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    } else {
                        //new MyIntent(Smoking.this, User_Profile.class, R.anim.enter, R.anim.exit);
                        SetSecondStepData ssd = new SetSecondStepData(Smoking.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, S_HBA1C_value, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, S_do, S_often, user_id,get_dob,diet_weight_loss,"no","13");
                        ssd.Data(Smoking.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);

                        ssd.DataConnection();
                    }


                } else {
                    //   Toast.makeText(Smoking.this, "no change", Toast.LENGTH_SHORT).show();

                    //new MyIntent(Smoking.this, go_class, R.anim.enter, R.anim.exit);
                    if (is_alcholic.equals("") || is_alcholic == null) {
                        new MyIntent(Smoking.this, go_class, R.anim.enter, R.anim.exit,true);
                    } else {
                        new MyIntent(Smoking.this, back_class, R.anim.enter, R.anim.exit,true);
                    }

                }
            }
        }
        if (v == prev) {
            //new MyIntent(Smoking.this, back_class, R.anim.enter2, R.anim.exit2);

            ///////
          /*  SharedPreferences spss = getSharedPreferences("EditPro", MODE_PRIVATE);
            SharedPreferences.Editor editorss = spss.edit();
            editorss.putString("gokey", "12");
            editorss.commit();
            
            if (gender.equals("female")) {
                if (is_pregnent.equals("yes")) {
                    if (is_diabetic.equals("yes")){
                        if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {

                            new MyIntent(Smoking.this, back_profile11, R.anim.enter, R.anim.exit);
                        }
                        else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("")|| activity_level_two.equals("Choose your Activity") || activity_level_two == null) {

                            new MyIntent(Smoking.this, back_profile10, R.anim.enter, R.anim.exit);
                        }
                        else if(last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null){

                            new MyIntent(Smoking.this, back_profile9, R.anim.enter, R.anim.exit);
                        }
                        else if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {

                            new MyIntent(Smoking.this, back_profile8, R.anim.enter, R.anim.exit);
                        }
                        else if (child_age.equals("") || child_age == null) {
                            new MyIntent(Smoking.this, back_profile6, R.anim.enter, R.anim.exit);
                        }
                        else if (first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null){
                            new MyIntent(Smoking.this, back_profile4, R.anim.enter, R.anim.exit);
                        }
                        else if (is_pregnent.equals("") || is_pregnent == null){
                            new MyIntent(Smoking.this, back_pregnant, R.anim.enter, R.anim.exit);
                        } else if (weight.equals("") || weight == null){
                            new MyIntent(Smoking.this, back_profile3, R.anim.enter, R.anim.exit);
                        }
                        else if (height.equals("") || height == null){
                            new MyIntent(Smoking.this, back_profile2, R.anim.enter, R.anim.exit);
                        }
                        else if (age.equals("") || age == null){
                            new MyIntent(Smoking.this, back_profile1, R.anim.enter, R.anim.exit);
                        }
                        else {
                            new MyIntent(Smoking.this, back_class, R.anim.enter2, R.anim.exit2);
                        }
                    }
                    else {
                        if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {

                            new MyIntent(Smoking.this, back_profile11, R.anim.enter, R.anim.exit);
                        }
                       else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("")|| activity_level_two.equals("Choose your Activity") || activity_level_two == null) {

                            new MyIntent(Smoking.this, back_profile10, R.anim.enter, R.anim.exit);
                        }
                        else if (child_age.equals("") || child_age == null) {
                            new MyIntent(Smoking.this, back_profile6, R.anim.enter, R.anim.exit);
                        }
                        else if (first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null){
                            new MyIntent(Smoking.this, back_profile4, R.anim.enter, R.anim.exit);
                        }
                        else if (is_pregnent.equals("") || is_pregnent == null){
                            new MyIntent(Smoking.this, back_pregnant, R.anim.enter, R.anim.exit);
                        } else if (weight.equals("") || weight == null){
                            new MyIntent(Smoking.this, back_profile3, R.anim.enter, R.anim.exit);
                        }
                        else if (height.equals("") || height == null){
                            new MyIntent(Smoking.this, back_profile2, R.anim.enter, R.anim.exit);
                        }
                        else if (age.equals("") || age == null){
                            new MyIntent(Smoking.this, back_profile1, R.anim.enter, R.anim.exit);
                        }
                        else {
                            new MyIntent(Smoking.this, back_class, R.anim.enter2, R.anim.exit2);
                        }
                    }

                }
                else {
                    if (is_diabetic.equals("yes")){
                        if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {

                            new MyIntent(Smoking.this, back_profile11, R.anim.enter, R.anim.exit);
                        }
                       else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("")|| activity_level_two.equals("Choose your Activity") || activity_level_two == null) {

                            new MyIntent(Smoking.this, back_profile10, R.anim.enter, R.anim.exit);
                        }
                        else if(last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null){

                            new MyIntent(Smoking.this, back_profile9, R.anim.enter, R.anim.exit);
                        }
                        else if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {

                            new MyIntent(Smoking.this, back_profile8, R.anim.enter, R.anim.exit);
                        }
                        else if (breast_feeding.equals("") || breast_feeding == null) {
                            new MyIntent(Smoking.this, back_profile5, R.anim.enter, R.anim.exit);
                        }

                        else if (is_pregnent.equals("") || is_pregnent == null){
                            new MyIntent(Smoking.this, back_pregnant, R.anim.enter, R.anim.exit);
                        } else if (weight.equals("") || weight == null){
                            new MyIntent(Smoking.this, back_profile3, R.anim.enter, R.anim.exit);
                        }
                        else if (height.equals("") || height == null){
                            new MyIntent(Smoking.this, back_profile2, R.anim.enter, R.anim.exit);
                        }
                        else if (age.equals("") || age == null){
                            new MyIntent(Smoking.this, back_profile1, R.anim.enter, R.anim.exit);
                        }
                        else {
                            new MyIntent(Smoking.this, back_class, R.anim.enter2, R.anim.exit2);
                        }
                    }
                    else {
                        if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {

                            new MyIntent(Smoking.this, back_profile11, R.anim.enter, R.anim.exit);
                        }
                       else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("")|| activity_level_two.equals("Choose your Activity") || activity_level_two == null) {

                            new MyIntent(Smoking.this, back_profile10, R.anim.enter, R.anim.exit);
                        }
                        else if (breast_feeding.equals("") || breast_feeding == null) {
                            new MyIntent(Smoking.this, back_profile5, R.anim.enter, R.anim.exit);
                        }
                        else if (is_pregnent.equals("") || is_pregnent == null){
                            new MyIntent(Smoking.this, back_pregnant, R.anim.enter, R.anim.exit);
                        } else if (weight.equals("") || weight == null){
                            new MyIntent(Smoking.this, back_profile3, R.anim.enter, R.anim.exit);
                        }
                        else if (height.equals("") || height == null){
                            new MyIntent(Smoking.this, back_profile2, R.anim.enter, R.anim.exit);
                        }
                        else if (age.equals("") || age == null){
                            new MyIntent(Smoking.this, back_profile1, R.anim.enter, R.anim.exit);
                        }
                        else {
                            new MyIntent(Smoking.this, back_class, R.anim.enter2, R.anim.exit2);
                        }
                    }
                }
            }
            else {
                if (is_diabetic.equals("yes")){
                    if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {

                        new MyIntent(Smoking.this, back_profile11, R.anim.enter, R.anim.exit);
                    }
                   else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("")|| activity_level_two.equals("Choose your Activity") || activity_level_two == null) {

                        new MyIntent(Smoking.this, back_profile10, R.anim.enter, R.anim.exit);
                    }
                    else if(last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null){

                        new MyIntent(Smoking.this, back_profile9, R.anim.enter, R.anim.exit);
                    }
                    else if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {

                        new MyIntent(Smoking.this, back_profile8, R.anim.enter, R.anim.exit);
                    }
                    else if (weight.equals("") || weight == null){
                        new MyIntent(Smoking.this, back_profile3, R.anim.enter, R.anim.exit);
                    }
                    else if (height.equals("") || height == null){
                        new MyIntent(Smoking.this, back_profile2, R.anim.enter, R.anim.exit);
                    }
                    else if (age.equals("") || age == null){
                        new MyIntent(Smoking.this, back_profile1, R.anim.enter, R.anim.exit);
                    }
                    else {
                        new MyIntent(Smoking.this, back_class, R.anim.enter2, R.anim.exit2);
                    }
                }
                else {
                    if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {

                        new MyIntent(Smoking.this, back_profile11, R.anim.enter, R.anim.exit);
                    }
                   else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("")|| activity_level_two.equals("Choose your Activity") || activity_level_two == null) {

                        new MyIntent(Smoking.this, back_profile10, R.anim.enter, R.anim.exit);
                    }
                    else if (weight.equals("") || weight == null){
                        new MyIntent(Smoking.this, back_profile3, R.anim.enter, R.anim.exit);
                    }
                    else if (height.equals("") || height == null){
                        new MyIntent(Smoking.this, back_profile2, R.anim.enter, R.anim.exit);
                    }
                    else if (age.equals("") || age == null){
                        new MyIntent(Smoking.this, back_profile1, R.anim.enter, R.anim.exit);
                    }
                    else {
                        new MyIntent(Smoking.this, back_class, R.anim.enter2, R.anim.exit2);
                    }
                }

            }*/
          onBackPressed();


        }
        if (v == yes) {
            smoke_layout.setVisibility(View.VISIBLE);
        }
        if (v == no) {
            smoke_layout.setVisibility(View.INVISIBLE);
        }

        if (v == home_L) {
            new MyIntent(Smoking.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Smoking.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Smoking.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Smoking.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Smoking.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // new MyIntent(Smoking.this, back_class, R.anim.enter2, R.anim.exit2);
        finish();




    }
}
