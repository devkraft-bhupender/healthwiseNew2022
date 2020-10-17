package com.hwi.health.Activitys.Profile.First;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Usages.VerticalSeekBar;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.edit_profile.Edit_profile_Activity;

import java.util.ArrayList;

public class Profile_Step2 extends AppCompatActivity implements View.OnClickListener {
    ActionBar ab;
    //SeekBar waightseekBar;
    VerticalSeekBar hightSeekBar;
    EditText enter_centimeter;
    EditText enter_feet, enter_inch;
    ImageView next, prev;
    RelativeLayout R_layout;
    String key, edit_key, get_dob, diet_weight_loss;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String randnoo, age, genderselectin, weight, hight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;
    Class go_class, go_profile3, go_profile_pregnant, go_profile4, go_profile5, go_profile6, go_profile7, go_profile8, go_profile9, go_profile10, go_profile11, go_profile_smoke, go_profile_drink;
    Class back_class, back_profile1;
    Class go_two_class;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    ArrayList<String> feet_list = new ArrayList<>();
    ArrayList<String> inch_list = new ArrayList<>();
    ArrayList<String> cm_list = new ArrayList<>();
    ArrayAdapter<String> feetadapter;
    ArrayAdapter<String> inchadapter;
    ArrayAdapter<String> cmadapter;
    String get_feet = "1", get_inch = "0", get_cm = "0";
    float cen;


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String centimeterToFeet(String centemeter) {
        int feetPart = 0;
        int inchesPart = 0;
        if(!TextUtils.isEmpty(centemeter)) {
            double dCentimeter = Double.valueOf(centemeter);
            feetPart = (int) Math.floor((dCentimeter / 2.54) / 12);
            System.out.println((dCentimeter / 2.54) - (feetPart * 12));
            inchesPart = (int) Math.ceil((dCentimeter / 2.54) - (feetPart * 12));
        }
       return String.valueOf(feetPart);
    }

    public static String centimeterToInches(String centemeter) {
        int feetPart = 0;
        int inchesPart = 0;
        if(!TextUtils.isEmpty(centemeter)) {
            double dCentimeter = Double.valueOf(centemeter);
            feetPart = (int) Math.floor((dCentimeter / 2.54) / 12);
            System.out.println((dCentimeter / 2.54) - (feetPart * 12));
            inchesPart = (int) Math.ceil((dCentimeter / 2.54) - (feetPart * 12));
        }
        return String.valueOf(inchesPart);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__step2);

        findViewById(R.id.activity_profile__step2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });
        hightSeekBar = (VerticalSeekBar) findViewById(R.id.verticalSeekbar);
        // waightseekBar = (SeekBar) findViewById(R.id.waightseekBar);
        enter_centimeter = findViewById(R.id.centimeter);
        enter_feet = findViewById(R.id.enter_feet);
        enter_inch = findViewById(R.id.enter_inch);
        // enter_waight = (EditText) findViewById(R.id.enter_waight);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        R_layout = (RelativeLayout) findViewById(R.id.bottom);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        hightSeekBar.setVisibility(View.GONE);

        feet_list.add("1");
        feet_list.add("2");
        feet_list.add("3");
        feet_list.add("4");
        feet_list.add("5");
        feet_list.add("6");
        feet_list.add("7");
        feet_list.add("8");

        inch_list.add("0");
        inch_list.add("1");
        inch_list.add("2");
        inch_list.add("3");
        inch_list.add("4");
        inch_list.add("5");
        inch_list.add("6");
        inch_list.add("7");
        inch_list.add("8");
        inch_list.add("9");
        inch_list.add("9");
        inch_list.add("10");
        inch_list.add("11");
        inch_list.add("12");

        enter_feet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogStatus(feet_list, "Feets");
            }
        });

        enter_inch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogStatus(inch_list, "Inchs");
            }
        });

        class MyFocusChangeListener implements View.OnFocusChangeListener {

            public void onFocusChange(View v, boolean hasFocus){

                if(v.getId() == R.id.centimeter && !hasFocus) {

                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                }
            }
        }
        View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
        enter_centimeter.setOnFocusChangeListener(ofcListener);




        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        SeekBarM();
        SharedPreferences sp1 = getSharedPreferences("Bottom", MODE_PRIVATE);
        key = sp1.getString("bt_key", "");
        /*  if (key.equals("1")) {
            R_layout.setVisibility(View.VISIBLE);
        } else {
            R_layout.setVisibility(View.GONE);
        }*/

        // go_two_class = Profile_Diabetic_7.class;
        go_class = Profile_Step3.class;
        back_class = Profile_Step1.class;
        go_profile7 = Profile_Diabetic_7.class;
        go_profile8 = Profile_Insulin_8.class;
        go_profile9 = Profile_Blood_Suger_9.class;
        go_profile10 = Profile_Active_10.class;
        go_profile11 = Profile_Food_habits_11.class;
        go_profile_smoke = Smoking.class;
        go_profile_drink = Alcohol.class;
        go_profile6 = Profile_Child_Age_6.class;
        go_profile4 = Profile_Menstrul_Cycle_4.class;
        go_profile5 = Profile_Breast_Feeding_5.class;
        go_profile3 = Profile_Step3.class;
        back_profile1 = Profile_Step1.class;

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


        } catch (Exception e) {
            Log.e("Errr1 ", e + "");
        }


        enter_centimeter.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                {
                    enter_feet.setText(centimeterToFeet(enter_centimeter.getText().toString()));
                    enter_inch.setText(centimeterToInches(enter_centimeter.getText().toString()));
                }


            }
        });
        // TODO calculate feet.inch to cm
        try {

            String[] separated = hight.split("\\.");
            try {
                if (separated[0].isEmpty())
                    enter_feet.setText("1");
                else
                    enter_feet.setText(separated[0]);

                if (separated[1].isEmpty())
                    enter_inch.setText("0");
                else
                    enter_inch.setText(separated[1]);

            } catch (Exception e) {
            }


            String feet = enter_feet.getText().toString();
            float feet_cm = Float.parseFloat(feet) * 30.48f;

            String inch = enter_inch.getText().toString();
            float inch_cm = Float.parseFloat(inch) * 2.54f;
            float height_cm = feet_cm + inch_cm;
            String centi = height_cm + "";
            String cenn = centi.substring(0, Math.min(centi.length(), 6));
            try {
                enter_centimeter.setText(cenn);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            Log.e("Errr2 ", e + "");
        }

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            edit_key = sharedPreferences.getString("edit_key", "");
            Log.e("edit_keyyy", edit_key + "");
            if (edit_key.equals("2")) {

                go_two_class = Edit_profile_Activity.class;
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
                go_profile4 = Edit_profile_Activity.class;
                go_profile5 = Edit_profile_Activity.class;
                go_profile_pregnant = Edit_profile_Activity.class;
                go_profile3 = Edit_profile_Activity.class;
                back_profile1 = Edit_profile_Activity.class;
                sharedPreferences.edit().clear().commit();
            } else {
                // go_two_class = Profile_Diabetic_7.class;
                go_class = Profile_Step3.class;
                go_profile_pregnant = Profile_Pregnant_3.class;
//                back_class = User_Profile.class;
                back_class = Profile_Step2.class;
                go_profile7 = Profile_Diabetic_7.class;
                go_profile8 = Profile_Insulin_8.class;
                go_profile9 = Profile_Blood_Suger_9.class;
                go_profile10 = Profile_Active_10.class;
                go_profile11 = Profile_Food_habits_11.class;
                go_profile_smoke = Smoking.class;
                go_profile_drink = Alcohol.class;
                go_profile6 = Profile_Child_Age_6.class;
                go_profile4 = Profile_Menstrul_Cycle_4.class;
                go_profile5 = Profile_Breast_Feeding_5.class;
                go_profile3 = Profile_Step3.class;
                back_profile1 = Profile_Step1.class;
            }
        } catch (Exception e) {
            Log.e("Errr3 ", e + "");
        }

//
    }

    String gets = "";

    void AlertDialogStatus(final ArrayList<String> alist, final String typp) {

        final Dialog dialogstatus = new Dialog(Profile_Step2.this, R.style.CustomDialog);
        dialogstatus.setContentView(R.layout.get_edit);
        dialogstatus.setCancelable(false);

        Button close = (Button) dialogstatus.findViewById(R.id.close);
        ListView listView = dialogstatus.findViewById(R.id.list);
        TextView type = (TextView) dialogstatus.findViewById(R.id.type);

        feetadapter = new ArrayAdapter<String>(Profile_Step2.this, R.layout.spinnerbase, R.id.sel, alist);
        listView.setAdapter(feetadapter);

        type.setText(typp);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (typp.equals("Feets")) {
                    get_feet = alist.get(position);
                    enter_feet.setText(get_feet);

                    String feet = enter_feet.getText().toString();
                    float feet_cm = Float.parseFloat(feet) * 30.48f;
                    String inch = enter_inch.getText().toString();
                    float inch_cm = Float.parseFloat(inch) * 2.54f;
                    float height_cm = feet_cm + inch_cm;
                    String centi = height_cm + "";
                    String cenn = centi.substring(0, Math.min(centi.length(), 6));

                    try {
                        enter_centimeter.setText(cenn);
                        dialogstatus.dismiss();
                    } catch (Exception e) {
                    }
                } else if (typp.equals("Inchs")) {
                    get_inch = alist.get(position);
                    enter_inch.setText(get_inch);

                    String feet = enter_feet.getText().toString();
                    float feet_cm = Float.parseFloat(feet) * 30.48f;
                    String inch = enter_inch.getText().toString();
                    float inch_cm = Float.parseFloat(inch) * 2.54f;
                    float height_cm = feet_cm + inch_cm;
                    String centi = height_cm + "";
                    String cenn = centi.substring(0, Math.min(centi.length(), 6));
                    try {
                        enter_centimeter.setText(cenn);
                        dialogstatus.dismiss();
                    } catch (Exception e) {
                    }
                }
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogstatus.dismiss();

            }
        });
        dialogstatus.show();

    }

    public static void hideSoftKeyboard(AppCompatActivity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        AppCompatActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    //    Signupspheight
    @Override
    public void onBackPressed() {
        super.onBackPressed();

       /* SharedPreferences spss = getSharedPreferences("EditPro", MODE_PRIVATE);
        SharedPreferences.Editor editorss = spss.edit();
        editorss.putString("gokey", "2");
        editorss.commit();

        if (age.equals("") || age == null) {
            new MyIntent(Profile_Step2.this, back_profile1, R.anim.enter, R.anim.exit);
        } else {
            new MyIntent(Profile_Step2.this, back_class, R.anim.enter2, R.anim.exit2);
        }*/
       finish();
    }

    @Override
    public void onClick(View view) {
         if (view == next) {

           // TODO calculate cm to feet.inch
            String cen = enter_centimeter.getText().toString().trim();

            if (cen.equals("") || cen.equals(".")|| cen.equals(",") ){
                Toast.makeText(this, "Fill centimeters first", Toast.LENGTH_SHORT).show();
                return;
            }


            float cm = Float.parseFloat(cen);
            double feet = cm / 30.48;
            double inches = (cm / 2.54) - ((int) feet * 12);
            Log.e("DATA", "There are " + (int) feet + " feet and " + inches + " inches in " + cm + "cm");

            get_feet = (int) feet + "";
            get_inch = String.valueOf(Math.round(inches));

            String hights = get_feet;
            String inc = get_inch;

            String ft_in;
            if (hights.equals("") || hights == null) {
                ft_in = "";
            } else {
                ft_in = hights + "." + inc;
            }

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();

            if (hights.isEmpty() || inc.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                if (!ft_in.equals(hight)) {
                    if (genderselectin.equals("male")) {
                        if (weight.equals("") || weight == null) {
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile3, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                            ssd.Data(Profile_Step2.this, go_profile3, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                            ssd.DataConnection();

                        } else if (is_diabetic.equals("") || is_diabetic == null) {
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile7, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                            ssd.Data(Profile_Step2.this, go_profile7, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                            ssd.DataConnection();

                        } else {
                            if (is_diabetic.equals("yes")) {
                                if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile8, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile8, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile9, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile9, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile10, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile11, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (is_smoke.equals("") || is_smoke == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_smoke, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (is_alcholic.equals("") || is_alcholic == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_drink, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, back_class, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                }


                            } else {
                                if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile10, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile11, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (is_smoke.equals("") || is_smoke == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_smoke, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (is_alcholic.equals("") || is_alcholic == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_drink, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, back_class, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                }
                            }
                        }
                    } else {
                        if (weight.equals("") || weight == null) {
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile3, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                            ssd.Data(Profile_Step2.this, go_profile3, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                            ssd.DataConnection();
                        } else if (is_pregnent.equals("") || is_pregnent == null) {
                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_pregnant, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                            ssd.Data(Profile_Step2.this, go_profile_pregnant, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                            ssd.DataConnection();
                        } else {
                            if (is_pregnent.equals("yes")) {
                                if (first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile4, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile4, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (child_age.equals("") || child_age == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile6, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile6, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (is_diabetic.equals("") || is_diabetic == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile7, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile7, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else {
                                    if (is_diabetic.equals("yes")) {
                                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile8, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile8, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile9, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile9, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile10, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile11, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (is_smoke.equals("") || is_smoke == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_smoke, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_drink, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, back_class, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        }


                                    } else {
                                        if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile10, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile11, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (is_smoke.equals("") || is_smoke == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_smoke, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_drink, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, back_class, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        }
                                    }
                                }
                            } else {
                                if (breast_feeding.equals("") || breast_feeding == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile5, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile5, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else if (is_diabetic.equals("") || is_diabetic == null) {
                                    SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile7, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                    ssd.Data(Profile_Step2.this, go_profile7, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                    ssd.DataConnection();
                                } else {
                                    if (is_diabetic.equals("yes")) {
                                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile8, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile8, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile9, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile9, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile10, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile11, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (is_smoke.equals("") || is_smoke == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_smoke, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_drink, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, back_class, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        }


                                    } else {
                                        if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile10, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile10, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile11, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile11, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (is_smoke.equals("") || is_smoke == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_smoke, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile_smoke, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, go_profile_drink, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, go_profile_drink, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        } else {
                                            SetSecondStepData ssd = new SetSecondStepData(Profile_Step2.this, back_class, randnoo, genderselectin, age, ft_in, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, HBA1C_val, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, get_dob, diet_weight_loss, "no", "3");
                                            ssd.Data(Profile_Step2.this, back_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp);
                                            ssd.DataConnection();
                                        }
                                    }
                                }


                            }
                        }


                    }


                } else {

                    if (genderselectin.equals("male")) {
                        if (weight.equals("") || weight == null) {
                            new MyIntent(Profile_Step2.this, go_profile3, R.anim.enter, R.anim.exit,true);
                        } else if (is_diabetic.equals("") || is_diabetic == null) {
                            new MyIntent(Profile_Step2.this, go_profile7, R.anim.enter, R.anim.exit,true);
                        } else {
                            if (is_diabetic.equals("yes")) {
                                if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                                    new MyIntent(Profile_Step2.this, go_profile8, R.anim.enter, R.anim.exit,true);
                                } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                                    new MyIntent(Profile_Step2.this, go_profile9, R.anim.enter, R.anim.exit,true);
                                } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                    new MyIntent(Profile_Step2.this, go_profile10, R.anim.enter, R.anim.exit,true);
                                } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                    new MyIntent(Profile_Step2.this, go_profile11, R.anim.enter, R.anim.exit,true);
                                } else if (is_smoke.equals("") || is_smoke == null) {
                                    new MyIntent(Profile_Step2.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);
                                } else if (is_alcholic.equals("") || is_alcholic == null) {
                                    new MyIntent(Profile_Step2.this, go_profile_drink, R.anim.enter, R.anim.exit,true);
                                } else {
                                    new MyIntent(Profile_Step2.this, back_class, R.anim.enter, R.anim.exit,true);
                                }


                            } else {
                                if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                    new MyIntent(Profile_Step2.this, go_profile10, R.anim.enter, R.anim.exit,true);
                                } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                    new MyIntent(Profile_Step2.this, go_profile11, R.anim.enter, R.anim.exit,true);
                                } else if (is_smoke.equals("") || is_smoke == null) {
                                    new MyIntent(Profile_Step2.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);
                                } else if (is_alcholic.equals("") || is_alcholic == null) {
                                    new MyIntent(Profile_Step2.this, go_profile_drink, R.anim.enter, R.anim.exit,true);
                                } else {
                                    new MyIntent(Profile_Step2.this, back_class, R.anim.enter, R.anim.exit,true);
                                }
                            }
                        }
                    } else {
                        if (weight.equals("") || weight == null) {
                            new MyIntent(Profile_Step2.this, go_profile3, R.anim.enter, R.anim.exit,true);
                        } else if (is_pregnent.equals("") || is_pregnent == null) {
                            new MyIntent(Profile_Step2.this, go_profile_pregnant, R.anim.enter, R.anim.exit,true);
                        } else {
                            if (is_pregnent.equals("yes")) {
                                if (first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null) {
                                    new MyIntent(Profile_Step2.this, go_profile4, R.anim.enter, R.anim.exit,true);
                                } else if (child_age.equals("") || child_age == null) {
                                    new MyIntent(Profile_Step2.this, go_profile6, R.anim.enter, R.anim.exit,true);
                                } else if (is_diabetic.equals("") || is_diabetic == null) {
                                    new MyIntent(Profile_Step2.this, go_profile7, R.anim.enter, R.anim.exit,true);
                                } else {
                                    if (is_diabetic.equals("yes")) {
                                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                                            new MyIntent(Profile_Step2.this, go_profile8, R.anim.enter, R.anim.exit,true);
                                        } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                                            new MyIntent(Profile_Step2.this, go_profile9, R.anim.enter, R.anim.exit,true);
                                        } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                            new MyIntent(Profile_Step2.this, go_profile10, R.anim.enter, R.anim.exit,true);
                                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                            new MyIntent(Profile_Step2.this, go_profile11, R.anim.enter, R.anim.exit,true);
                                        } else if (is_smoke.equals("") || is_smoke == null) {
                                            new MyIntent(Profile_Step2.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);
                                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                                            new MyIntent(Profile_Step2.this, go_profile_drink, R.anim.enter, R.anim.exit,true);
                                        } else {
                                            new MyIntent(Profile_Step2.this, back_class, R.anim.enter, R.anim.exit,true);
                                        }


                                    } else {
                                        if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                            new MyIntent(Profile_Step2.this, go_profile10, R.anim.enter, R.anim.exit,true);
                                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                            new MyIntent(Profile_Step2.this, go_profile11, R.anim.enter, R.anim.exit,true);
                                        } else if (is_smoke.equals("") || is_smoke == null) {
                                            new MyIntent(Profile_Step2.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);
                                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                                            new MyIntent(Profile_Step2.this, go_profile_drink, R.anim.enter, R.anim.exit,true);
                                        } else {
                                            new MyIntent(Profile_Step2.this, back_class, R.anim.enter, R.anim.exit,true);
                                        }
                                    }
                                }
                            } else {
                                if (breast_feeding.equals("") || breast_feeding == null) {
                                    new MyIntent(Profile_Step2.this, go_profile5, R.anim.enter, R.anim.exit,true);
                                } else if (is_diabetic.equals("") || is_diabetic == null) {
                                    new MyIntent(Profile_Step2.this, go_profile7, R.anim.enter, R.anim.exit,true);
                                } else {
                                    if (is_diabetic.equals("yes")) {
                                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                                            new MyIntent(Profile_Step2.this, go_profile8, R.anim.enter, R.anim.exit,true);
                                        } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                                            new MyIntent(Profile_Step2.this, go_profile9, R.anim.enter, R.anim.exit,true);
                                        } else if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                            new MyIntent(Profile_Step2.this, go_profile10, R.anim.enter, R.anim.exit,true);
                                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                            new MyIntent(Profile_Step2.this, go_profile11, R.anim.enter, R.anim.exit,true);
                                        } else if (is_smoke.equals("") || is_smoke == null) {
                                            new MyIntent(Profile_Step2.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);
                                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                                            new MyIntent(Profile_Step2.this, go_profile_drink, R.anim.enter, R.anim.exit,true);
                                        } else {
                                            new MyIntent(Profile_Step2.this, back_class, R.anim.enter, R.anim.exit,true);
                                        }


                                    } else {
                                        if (activity_level_one.equals("") || activity_level_one.equals("Choose your Activity") || activity_level_one == null || activity_level_two.equals("") || activity_level_two.equals("Choose your Activity") || activity_level_two == null) {
                                            new MyIntent(Profile_Step2.this, go_profile10, R.anim.enter, R.anim.exit,true);
                                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                                            new MyIntent(Profile_Step2.this, go_profile11, R.anim.enter, R.anim.exit,true);
                                        } else if (is_smoke.equals("") || is_smoke == null) {
                                            new MyIntent(Profile_Step2.this, go_profile_smoke, R.anim.enter, R.anim.exit,true);
                                        } else if (is_alcholic.equals("") || is_alcholic == null) {
                                            new MyIntent(Profile_Step2.this, go_profile_drink, R.anim.enter, R.anim.exit,true);
                                        } else {
                                            new MyIntent(Profile_Step2.this, back_class, R.anim.enter, R.anim.exit,true);
                                        }
                                    }
                                }


                            }
                        }


                    }

                    // Toast.makeText(Profile_Step2.this, "no change", Toast.LENGTH_SHORT).show();
               /* if (genderselectin.equals("female")) {*/
                    // new MyIntent(Profile_Step2.this, go_class, R.anim.enter, R.anim.exit);
                /*} else {
                    new MyIntent(Profile_Step2.this, go_two_class, R.anim.enter, R.anim.exit);
                }
*/
                }

            }
        }

        if (view == prev) {

           /* SharedPreferences spss = getSharedPreferences("EditPro", MODE_PRIVATE);
            SharedPreferences.Editor editorss = spss.edit();
            editorss.putString("gokey", "2");
            editorss.commit();
            new MyIntent(Profile_Step2.this, back_profile1, R.anim.enter, R.anim.exit);*/
           onBackPressed();
        }
        if (view == home_L) {
            new MyIntent(Profile_Step2.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == profile_L) {
            new MyIntent(Profile_Step2.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == log_L) {
            new MyIntent(Profile_Step2.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == plans_L) {
            new MyIntent(Profile_Step2.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(Profile_Step2.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    void SeekBarM() {
        /*waightseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgress = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int fir = 30;
                // int ww = progress * 2;
                int ww = progress;
                seekBarProgress = ww;
                enter_waight.setText(seekBarProgress + "");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });*/


//        hightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                int fir = 1;
//                float progressD = (float) progress / 10;
//                float prog = progressD;
//
//                String[] arr = String.valueOf(prog).split("\\.");
////                try {
////                    int sincePosition = feetadapter.getPosition(arr[0]);
////                    enter_feet.setSelection(sincePosition);
////                } catch (Exception e) {
////                }
////
////                try {
////                    int sincePosition2 = feetadapter.getPosition(arr[1]);
////                    enter_inch.setSelection(sincePosition2);
////                } catch (Exception e) {
////                }
//
//                int fet = Integer.parseInt(arr[0]) * 12 + Integer.parseInt(arr[1]);
//                float cen = fet * 2.54f;
//                String centi = cen + "";
//                String upToNCharacters = centi.substring(0, Math.min(centi.length(), 6));
//                enter_centimeter.setText(upToNCharacters + "");
//
//
//            }
//
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//
//        });

    }

}
