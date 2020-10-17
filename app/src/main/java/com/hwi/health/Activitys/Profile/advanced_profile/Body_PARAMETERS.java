package com.hwi.health.Activitys.Profile.advanced_profile;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

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

public class Body_PARAMETERS extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ImageView next, prev;
    SeekBar WaistseekBar;
    String enter_feet, enter_inch, key, edit_key, get_dob, diet_weight_loss;
    RelativeLayout R_layout;
    EditText enter_inc, enter_cmc, Systolic, Diastolic;
    String Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    String randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;
    Class go_class, go_class2;
    Class back_class;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body__parameters);
        Diastolic = (EditText) findViewById(R.id.Diastolic);
        enter_inc = (EditText) findViewById(R.id.enter_inc);
        enter_cmc = (EditText) findViewById(R.id.enter_cmc);
        Systolic = (EditText) findViewById(R.id.Systolic);
        WaistseekBar = (SeekBar) findViewById(R.id.WaistseekBar);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        R_layout = (RelativeLayout) findViewById(R.id.bottom);

        WaistseekBar.setVisibility(View.GONE);

        go_class = LABORATORY.class;
        go_class2 = RISK_RELATED_QUESTIONS.class;
        back_class = User_Profile.class;
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        SeekBarM();

        SharedPreferences sp1 = getSharedPreferences("Bottom", MODE_PRIVATE);
        findViewById(R.id.activity_body__parameters).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });


        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            edit_key = sharedPreferences.getString("edit_key", "");
            Log.e("edit_keyyy", edit_key + "");
            if (edit_key.equals("14")) {

                go_class = Edit_profile_Activity.class;
                back_class = Edit_profile_Activity.class;
                go_class2 = Edit_profile_Activity.class;
                sharedPreferences.edit().clear().commit();
            } else {

                go_class = LABORATORY.class;
                back_class = User_Profile.class;
                go_class2 = RISK_RELATED_QUESTIONS.class;

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
            Senter_cmc = String.valueOf(Float.parseFloat(Senter_inc) * 2.54f);
            SSystolic = sp.getString("SSystolic", "");
            SDiastolic = sp.getString("SDiastolic", "");
            Vitamin_B12_levels = sp.getString("Vitamin_B12_levels", "");
            heart_disease = sp.getString("heart_disease", "");
            Bp = sp.getString("BP", "");

            Diastolic.setText(SDiastolic);

            enter_cmc.setText(Senter_cmc);
            Systolic.setText(SSystolic);

            int aag = Integer.parseInt(Senter_inc);
            enter_inc.setText(aag + "");

            //   WaistseekBar.setProgress(aag);


        } catch (Exception e) {

        }

        enter_cmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialogStatus("Enter Centimeters",enter_inc,enter_cmc);
            }
        });

        enter_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialogStatus("Enter Inchs",enter_inc,enter_cmc);
            }
        });


        enter_inc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (!s.toString().isEmpty()) {
                        float cm = Float.parseFloat(String.valueOf(s)) * 2.54f;
                        enter_cmc.setText(cm + "");
                    } else {
                        enter_cmc.setText("0");
                    }
            }
        });


//        enter_cmc.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (!enter_inc.isCursorVisible() || !enter_inc.isActivated()) {
//                    if (!s.toString().isEmpty()) {
//                        float cm = Float.parseFloat(String.valueOf(s)) / 2.54f;
//                        if (cm > 0)
//                            enter_cmc.setText(cm + "");
//                    } else {
//                        enter_inc.setText("0");
//                    }
//
//                }
//            }
//        });

        class MyFocusChangeListener implements View.OnFocusChangeListener {

            public void onFocusChange(View v, boolean hasFocus){

                if(v.getId() == R.id.enter_inc && !hasFocus) {

                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                }
            }
        }
        View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
        enter_inc.setOnFocusChangeListener(ofcListener);

    }


    String gets = "";

    void AlertDialogStatus(final String typp, final EditText enter_inch, final EditText enter_centimeter) {

        final Dialog dialogstatus = new Dialog(Body_PARAMETERS.this, R.style.CustomDialog);
        dialogstatus.setContentView(R.layout.get_edit);
        dialogstatus.setCancelable(false);

        Button close = (Button) dialogstatus.findViewById(R.id.close);
        Button Ok = (Button) dialogstatus.findViewById(R.id.Ok);
        final EditText edits = (EditText) dialogstatus.findViewById(R.id.edit);
        TextView type = (TextView) dialogstatus.findViewById(R.id.type);

        type.setText(typp);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogstatus.dismiss();

            }
        });

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gets = edits.getText().toString();

                if (typp.equals("Enter Inchs")) {
                    enter_inch.setText(gets);

                    String hight = enter_inch.getText().toString();
                    float cen = Float.parseFloat(hight) * 2.54f;
                    String centi = cen + "";
                    //   String cenn = centi.substring(0, Math.min(centi.length(), 5));
                    enter_centimeter.setText(centi + "");

                    float hig = Float.parseFloat(hight) * 10;

                    // WaistseekBar.setProgress((int) hig);

                    //   hideSoftKeyboard(Profile_Step2.this);
                } else {
                    enter_centimeter.setText(gets);
                    try {
                        float cem = Float.parseFloat(enter_centimeter.getText().toString());
                        float cent = cem / 2.54f;

                        Log.e("cent", cent + "");
                        String[] cc = String.valueOf(cent).split("\\.");
                        String inch = cc[0];

                        enter_inch.setText(inch + "");

                    } catch (Exception e) {

                    }

                    //   hideSoftKeyboard(Profile_Step2.this);
                }

                dialogstatus.dismiss();

            }
        });


        dialogstatus.show();

    }


    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Body_PARAMETERS.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Body_PARAMETERS.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Body_PARAMETERS.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Body_PARAMETERS.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Body_PARAMETERS.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == next) {
            Senter_incS = enter_inc.getText().toString();
            Senter_cmcS = enter_cmc.getText().toString();
            SSystolicS = Systolic.getText().toString();
            SDiastolicS = Diastolic.getText().toString();


            //   new MyIntent(Profile_Breast_Feeding_5.this, back_class, R.anim.enter2, R.anim.exit2);

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();

            if (!Senter_incS.equals(Senter_inc)) {


                if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null || blood_sugar.equals("") || blood_sugar == null) {
                    // new MyIntent(Body_PARAMETERS.this, go_class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                } else if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null) {

                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, go_class2, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, go_class2, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                } else {
                    // new MyIntent(Body_PARAMETERS.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                }


            } else if (!Senter_cmcS.equals(Senter_cmc)) {

                if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null || blood_sugar.equals("") || blood_sugar == null) {
                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                } else if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null) {

                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, go_class2, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, go_class2, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                } else {

                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                }

            } else if (!SSystolicS.equals(SSystolic)) {

                if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null || blood_sugar.equals("") || blood_sugar == null) {
                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                } else if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null) {

                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, go_class2, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, go_class2, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                } else {

                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                }


            } else if (!SDiastolicS.equals(SDiastolic)) {

                if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null || blood_sugar.equals("") || blood_sugar == null) {

                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                } else if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null) {

                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, go_class2, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, go_class2, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                } else {
                    SetSecondStepData ssd = new SetSecondStepData(Body_PARAMETERS.this, back_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no");
                    ssd.Data(Body_PARAMETERS.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_incS, Senter_cmcS, SSystolicS, SDiastolicS, heart_disease, Bp);
                    ssd.DataConnection();
                }


            } else {

                if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null || blood_sugar.equals("") || blood_sugar == null) {
                    new MyIntent(Body_PARAMETERS.this, go_class, R.anim.enter, R.anim.exit);
                } else if (heart_disease.equals("") || heart_disease == null || Bp.equals("") || Bp == null) {
                    new MyIntent(Body_PARAMETERS.this, go_class2, R.anim.enter, R.anim.exit);
                } else {
                    new MyIntent(Body_PARAMETERS.this, back_class, R.anim.enter, R.anim.exit);
                }
            }


        }
        if (v == prev) {
            new MyIntent(Body_PARAMETERS.this, back_class, R.anim.enter, R.anim.exit);
        }
    }

    void SeekBarM() {

        WaistseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgress = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float progressD = (float) progress;
                float prog = progressD;
                int ww = progress;
                seekBarProgress = ww;
                enter_inc.setText(seekBarProgress + "");

                float cen = prog * 2.54f;
//                float prog_cen = cen+1.0f;
                String centi = cen + "";
                String upToNCharacters = centi.substring(0, Math.min(centi.length(), 5));
                enter_cmc.setText(upToNCharacters + "");

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Body_PARAMETERS.this, back_class, R.anim.enter, R.anim.exit);
    }

}
