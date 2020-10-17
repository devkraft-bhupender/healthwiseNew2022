package com.hwi.health.Activitys.Profile.First;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.R;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.edit_profile.Edit_profile_Activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Profile_Step1 extends AppCompatActivity implements View.OnClickListener
         {
    ActionBar ab;
    // SeekBar seekBar;
    EditText enter_age;
    ImageView next, prev;
    SharedPreferences sp;
    EditText enter_dob;
    RadioButton male, female, other;
    String genderselectin = "", key, get_dob, diet_weight_loss;
    Context ctx;
    String age = "";
    RelativeLayout R_layout;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;

    String edit_key, strDate;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String randnoo, gender, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;
    Class go_class;
    Class back_class;
    int YEAR, MONTH, DAY;
    String current_date, get_year, get_Dob;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__step1);
        // seekBar = (SeekBar) findViewById(R.id.seekBar);
        enter_age = (EditText) findViewById(R.id.enter_age);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        other = (RadioButton) findViewById(R.id.other);
        R_layout = (RelativeLayout) findViewById(R.id.bottom);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        enter_dob = (EditText) findViewById(R.id.enter_dob);

        enter_age.setClickable(false);
        enter_age.setFocusableInTouchMode(false);
        enter_age.setFocusable(false);

        ctx = this;
        //  SeekBarM();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        current_date = format.format(c.getTime());
        // enter_dob.setText(current_date + "");
        String[] d = current_date.split("-");
        get_year = (d[0]);


        SharedPreferences sp1 = getSharedPreferences("Bottom", MODE_PRIVATE);
        key = sp1.getString("bt_key", "");
       /* if (key.equals("1")){
            R_layout.setVisibility(View.VISIBLE);
        }else {
            R_layout.setVisibility(View.GONE);
        }*/
        go_class = Profile_Step2.class;
//        back_class = User_Profile.class;
        back_class = Profile_Step1.class;


        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            edit_key = sharedPreferences.getString("edit_key", "");
            Log.e("edit_keyyy", edit_key + "");
            if (edit_key.equals("1")) {

                go_class = Edit_profile_Activity.class;
                back_class = Edit_profile_Activity.class;
                sharedPreferences.edit().clear().commit();
            } else {

                go_class = Profile_Step2.class;
                back_class = Profile_Step1.class;

            }
        } catch (Exception e) {
            Log.e("editeee", e + "");
        }

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            genderselectin = sp.getString("gender", "");
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
            diet_weight_loss = sp.getString("weg_loss", "");
            get_dob = sp.getString("Dob", "");


            int aag = Integer.parseInt(age);

            enter_age.setText(aag + "");
            enter_dob.setText(get_dob);
            // seekBar.setProgress(aag);

            // Toast.makeText(this, age+"", Toast.LENGTH_SHORT).show();

            if (genderselectin.equals("male"))
                male.setChecked(true);
            else if (genderselectin.equals("female"))
                female.setChecked(true);
            else if (genderselectin.equals("others"))
                other.setChecked(true);

        } catch (Exception e) {

            Log.e("SP erro r =", e + "");
        }

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        enter_dob.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

       SharedPreferences spss = getSharedPreferences("EditPro", MODE_PRIVATE);
        SharedPreferences.Editor editorss = spss.edit();
        editorss.putString("gokey", "1");
        editorss.commit();

        if(back_class==Edit_profile_Activity.class)
        new MyIntent(Profile_Step1.this, back_class, R.anim.enter2, R.anim.exit2);
        else
            new MyIntent(Profile_Step1.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == next) {
            if (male.isChecked()) {
                gender = "male";
            }
            if (female.isChecked()) {
                gender = "female";
            }
            if (other.isChecked()) {
                gender = "others";
            }

            String ages = enter_age.getText().toString();
            String dob = enter_dob.getText().toString();
            Log.e("dobbbb", dob);

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();

            if (ages.isEmpty() || dob.isEmpty()) {
                Toast.makeText(ctx, "Fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                if (!ages.equals(age)) {

                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step1.this, go_class, randnoo, gender, ages, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, dob, diet_weight_loss, "no","2");
                    ssd.Data(Profile_Step1.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();


                } else if (!gender.equals(genderselectin)) {
                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step1.this, go_class, randnoo, gender, ages, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, dob, diet_weight_loss, "no","2");
                    ssd.Data(Profile_Step1.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();


                } else if (!dob.equals(get_dob)) {
                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step1.this, go_class, randnoo, gender, ages, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, dob, diet_weight_loss, "no","2");
                    ssd.Data(Profile_Step1.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                    ssd.DataConnection();

                } else {
                    new MyIntent(Profile_Step1.this, go_class, R.anim.enter, R.anim.exit,true);
                }
            }

        }
        if (view == prev) {
         onBackPressed();
        }


        if (view == home_L) {
            new MyIntent(Profile_Step1.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == profile_L) {
            new MyIntent(Profile_Step1.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == log_L) {
            new MyIntent(Profile_Step1.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == plans_L) {
            new MyIntent(Profile_Step1.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(Profile_Step1.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == enter_dob) {
            String date_year = enter_dob.getText().toString();
            date_pick();
        }
    }


    DatePickerDialog dpd;


    void date_pick() {
        final Calendar c = Calendar.getInstance();

        Date today = new Date();

        c.setTime(today);
        c.add(Calendar.YEAR, -18);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        // Subtract 6 months
        final long minDate = c.getTime().getTime(); // Twice!


        dpd = new DatePickerDialog(Profile_Step1.this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dpd.getDatePicker().setMinDate(minDate);
                        YEAR = year;
                        MONTH = monthOfYear;
                        DAY = dayOfMonth;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(YEAR, MONTH, DAY);


                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM, yyyy");


                        strDate = format.format(calendar.getTime());
                        String strDate2 = format2.format(calendar.getTime());

                        String[] d = strDate.split("-");
                        String yearr = (d[0]);

                        int c_year = Integer.parseInt(get_year) - Integer.parseInt(yearr);
                        enter_age.setText(c_year + "");
                        Log.e("yearr", yearr + ".." + c_year);
                        Log.e("enter_dob", enter_dob + "..");

                        enter_dob.setText(strDate + "");
                    }
                }, mYear, mMonth, mDay);


        dpd.show();

    }


}
