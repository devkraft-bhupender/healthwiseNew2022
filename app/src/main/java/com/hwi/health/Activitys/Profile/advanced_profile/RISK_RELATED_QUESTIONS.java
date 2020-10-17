package com.hwi.health.Activitys.Profile.advanced_profile;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.hwi.health.InterFaces.BaseUrl;
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
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class RISK_RELATED_QUESTIONS extends AppCompatActivity implements View.OnClickListener,BaseUrl {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ImageView next, prev;
    ArrayList<String> age_list = new ArrayList<>();
    ArrayList<String> bp_list = new ArrayList<>();
    ArrayAdapter<String> adapter,adapter2;
    int selection = 100000000;
    Spinner S_age,S_bp;
    String get_age,get_bp,feet,inch,randnoo2 ;
    Dialog dialog;
    Class go_class;
    Class back_class,back_body,back_Lab;
    String key_health,key_heart;
    ActionBar ab;
    String edit_key,key,get_dob,diet_weight_loss,result1,result2,result3;
    RelativeLayout R_layout;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    String randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk__related__questions);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        S_age = (Spinner) findViewById(R.id.age);
        S_bp = (Spinner) findViewById(R.id.Bp);

        go_class = User_Profile.class;
        back_class = User_Profile.class;
        back_body = Body_PARAMETERS.class;
        back_Lab = LABORATORY.class;

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        age_list.add("Yes");
        age_list.add("No");
        adapter = new ArrayAdapter<String>(RISK_RELATED_QUESTIONS.this, android.R.layout.simple_spinner_dropdown_item,age_list);
        S_age.setAdapter(adapter);

        bp_list.add("Yes");
        bp_list.add("No");
        adapter2 = new ArrayAdapter<String>(RISK_RELATED_QUESTIONS.this, android.R.layout.simple_spinner_dropdown_item,bp_list);
        S_bp.setAdapter(adapter2);

        R_layout = (RelativeLayout) findViewById(R.id.bottom);

        SharedPreferences sp1 = getSharedPreferences("Bottom", MODE_PRIVATE);

        key = sp1.getString("bt_key", "");
       /* if (key.equals("1")){
            R_layout.setVisibility(View.VISIBLE);
        }else {
            R_layout.setVisibility(View.GONE);
        }*/
        RandomNumber rn = new RandomNumber();
        randnoo2 = rn.randno();

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            edit_key = sharedPreferences.getString("edit_key","");
            if (edit_key.equals("16")){
                go_class = Edit_profile_Activity.class;
                back_class = Edit_profile_Activity.class;
                back_body = Edit_profile_Activity.class;
                back_Lab = Edit_profile_Activity.class;

                sharedPreferences.edit().clear().commit();
            }else {

                go_class = User_Profile.class;
                back_class = User_Profile.class;
                back_body = Body_PARAMETERS.class;
                back_Lab = LABORATORY.class;

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

            String[] separated = height.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet = f;
            inch = i;


            Log.e("heart_disease...",heart_disease+"...");
            int sincePosition = adapter.getPosition(heart_disease);
            S_age.setSelection(sincePosition);
            int sincePosition2 = adapter.getPosition(Bp);
            S_bp.setSelection(sincePosition2);


        } catch (Exception e) {

        }

        S_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = position;
                get_age = age_list.get(position);
                // months = hm.getValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        S_bp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = position;
                get_bp = bp_list.get(position);
                // months = hm.getValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(RISK_RELATED_QUESTIONS.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(RISK_RELATED_QUESTIONS.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(RISK_RELATED_QUESTIONS.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(RISK_RELATED_QUESTIONS.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(RISK_RELATED_QUESTIONS.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == next) {

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            if (edit_key.equals("16")){
                new MyIntent(RISK_RELATED_QUESTIONS.this, go_class, R.anim.enter, R.anim.exit);
            }else {
                new Advance_Result().execute();
              //  new HeardRisk_Result().execute();
            }

            if (!get_age.equals(heart_disease)){
                SetSecondStepData ssd = new SetSecondStepData(RISK_RELATED_QUESTIONS.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yess");
                ssd.Data(RISK_RELATED_QUESTIONS.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, get_age, get_bp);
                ssd.DataConnection();
               // Toast.makeText(this, "change1", Toast.LENGTH_SHORT).show();



            }
            else if (!get_bp.equals(Bp)){
                SetSecondStepData ssd = new SetSecondStepData(RISK_RELATED_QUESTIONS.this, go_class, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id,get_dob,diet_weight_loss,"yess");
                ssd.Data(RISK_RELATED_QUESTIONS.this, go_class, randnoo, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, get_age, get_bp);
                ssd.DataConnection();
              // Toast.makeText(this, "change2", Toast.LENGTH_SHORT).show();


            }
            else {
                //  Toast.makeText(Profile_Active_10.this, "no change", Toast.LENGTH_SHORT).show();

               // new MyIntent(RISK_RELATED_QUESTIONS.this, go_class, R.anim.enter, R.anim.exit);
                /*Intent intent = new Intent(getApplicationContext(), go_class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();*/
               // Toast.makeText(this, "change3", Toast.LENGTH_SHORT).show();

            }


        }
        if (v == prev) {
            if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null || blood_sugar.equals("") || blood_sugar == null) {
                new MyIntent(RISK_RELATED_QUESTIONS.this, back_Lab, R.anim.enter, R.anim.exit);
            }
            else if (Senter_inc.equals("") || Senter_inc == null || Senter_cmc.equals("") || Senter_cmc == null || SSystolic.equals("") || SSystolic == null || SDiastolic.equals("") || SDiastolic == null ){
                new MyIntent(RISK_RELATED_QUESTIONS.this, back_body, R.anim.enter, R.anim.exit);
            }
            else {
                new MyIntent(RISK_RELATED_QUESTIONS.this, back_class, R.anim.enter, R.anim.exit);
            }
            }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null || blood_sugar.equals("") || blood_sugar == null) {
            new MyIntent(RISK_RELATED_QUESTIONS.this, back_Lab, R.anim.enter, R.anim.exit);
        }
        else if (Senter_inc.equals("") || Senter_inc == null || Senter_cmc.equals("") || Senter_cmc == null || SSystolic.equals("") || SSystolic == null || SDiastolic.equals("") || SDiastolic == null ){
            new MyIntent(RISK_RELATED_QUESTIONS.this, back_body, R.anim.enter, R.anim.exit);
        }
        else {
            new MyIntent(RISK_RELATED_QUESTIONS.this, back_class, R.anim.enter, R.anim.exit);
        }
    }


    class Advance_Result extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            result1 = "";

            try {
                URL url = new URL(URLS + health_checkup_and_report_analysis_and_advice + randnoo);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);
                postDataParams.put("gender", gender);
                postDataParams.put("wt", weight);
                postDataParams.put("dob", get_dob);
                postDataParams.put("ft", feet);
                postDataParams.put("inc", inch);
                postDataParams.put("waist_inch", Senter_inc);
                postDataParams.put("waist_cm", Senter_cmc);
                postDataParams.put("systolic", SSystolic);
                postDataParams.put("diastolic", SDiastolic);
                postDataParams.put("fasting_blood", blood_sugar);
                postDataParams.put("total_colestrol", Total_cholesterol);
                // postDataParams.put("tot_cholesterol", user_id);
                postDataParams.put("hdl_colestrol", HDL_cholesterol);
                postDataParams.put("ldl_colestrol", LDL_cholesterol);
                postDataParams.put("triglycerides", Triglycerides);
                postDataParams.put("smoke", is_smoke);
                postDataParams.put("hemoglobin", Hemoglobin);
                postDataParams.put("hematocrit", Hematocrit);
                postDataParams.put("vitamin_d", Vitamin_levels);
                postDataParams.put("vitamin_b", Vitamin_B12_levels);
                Log.e("Advance_Resultparams", params + "");


                Log.e("Advance_Resultparams", postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
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
                        result1 += line;
                    }
                } else {
                    result1 = "";
                }
            } catch (Exception e) {
                Log.e("ERRR", e + "");
            }
            return result1;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            Log.e("Advance_Result = ", result1 + "");

            try {
                JSONObject jobj = new JSONObject(result1);
                String status = jobj.getString("status");
                String message = jobj.getString("message");


                if (status.equals("1")) {
                    JSONObject json = jobj.getJSONObject("hwi_heart_risk_calculator");
                    JSONObject json2 = json.getJSONObject("Health_checkup_Report_Analysis_And_Adviced");
                    String BMI = json2.getString("Your BMI is:");
                    String central_obesity = json2.getString("As per your WC, do you have central obesity?");
                    String syndrome = json2.getString("Do you have metabolic syndrome?");
                    String Diabetes = json2.getString("Diabetes");
                    String Prediabetes = json2.getString("Prediabetes");
                    String High_BP = json2.getString("High BP");
                    String Weight_Category = json2.getString("Your Weight Category is:");
                    String Anaemia = json2.getString("Anaemia");
                    String Vit_B = json2.getString("Vit B12 deficiency");
                    String Vit_D = json2.getString("Vit D deficiency");
                    new AllSharedPrefrences(RISK_RELATED_QUESTIONS.this).Advance_Result(BMI, central_obesity, syndrome, Diabetes, Prediabetes, High_BP, Weight_Category, Anaemia, Vit_B, Vit_D);
                    new AllSharedPrefrences(RISK_RELATED_QUESTIONS.this).keyAdavance("1");
                    //AlertDialog();
                    new HeardRisk_Result("1").execute();


                    Log.e("message........",BMI+""+Weight_Category);
                    Log.e("message........",message);
                } else {
                    Log.e("message........",message);
                   // new MyIntent(RISK_RELATED_QUESTIONS.this, go_class, R.anim.enter, R.anim.exit);
                    new HeardRisk_Result("0").execute();
                    // Toast.makeText(Health_Profile.this, ""+message, Toast.LENGTH_LONG).show();

                }
            } catch (Exception e) {
                Log.e("Advance_Result........",e+"");
                // Toast.makeText(Health_Profile.this, ""+e+"", Toast.LENGTH_LONG).show();
            }
        }
    }

    class HeardRisk_Result extends AsyncTask<String, Void, String> {

        String statu ="";

        HeardRisk_Result (String statu){
            this.statu = statu;
        }

        @SuppressLint("LongLogTag")
        @Override
        protected String doInBackground(String... params) {
            result2 = "";

            try {
                URL url = new URL(URLS + hwi_heart_risk_calculator + randnoo);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("age", age + "");
                postDataParams.put("sex", gender);
                postDataParams.put("Weight", weight);
                postDataParams.put("dob", get_dob);
                postDataParams.put("ft", feet);
                postDataParams.put("inc", inch);
                postDataParams.put("waist_inch", Senter_inc);
                postDataParams.put("waist_cm", Senter_cmc);
                postDataParams.put("systolic", SSystolic);
                postDataParams.put("diastolic", SDiastolic);
                postDataParams.put("tot_cholesterol", Total_cholesterol);
                postDataParams.put("hdl_colestrol", HDL_cholesterol);
                postDataParams.put("triglycerides", Triglycerides);
                postDataParams.put("smoke", is_smoke);
                postDataParams.put("fasting_blood_sugar", blood_sugar);
                postDataParams.put("diabetes", is_diabetic);
                postDataParams.put("father_brother_heart_disease", get_age);
                postDataParams.put("drugs", get_bp);

                Log.e("hwi_heart_risk_calculator params", postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
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
                        result2 += line;
                    }
                } else {
                    result2 = "";
                }
            } catch (Exception e) {
                Log.e("ERRR", e + "");
            }
            return result2;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            Log.e("HeardRisk_Result = ", result2 + "");

            try {
                JSONObject jobj = new JSONObject(result2);
                String status = jobj.getString("status");
                String message = jobj.getString("message");


                if (status.equals("1")) {

                    JSONObject joo = jobj.getJSONObject("hwi_heart_risk_calculator");
                    JSONObject jo = joo.getJSONObject("hwi_heart_risk_calculator");

                    String bmi = jo.getString("Your BMI");
                    String Weight_Category = jo.getString("Your Weight Category is:");
                    String yearrisk10 = jo.getString("10 year risk for having a heart attack or stroke in people like you is");

                    String This_risk_is = jo.getString("This risk is");
                    /*String A44 = jo.getString("A44");
                    String A45 = jo.getString("A45");
                    String A46 = jo.getString("A46");
                    String A47 = jo.getString("A47");
                    String A48 = jo.getString("A48");*/
                   /* String To_know_how = jo.getString("To know how to manage your heart risk well, go to");
                    String year_risk_persent20 = jo.getString("20_year_risk_persent");
                    String year_risk20 = jo.getString("20_year_risk");
                    String B51 = jo.getString("B51");
                    String smoke_risk_amount = jo.getString("smoke_risk_amount");
                    String smoke_risk_amount_title = jo.getString("smoke_risk_amount_title");
                    String risk_again = jo.getString("risk_again");
                    String systolic_or_diastolic_risk_persentage = jo.getString("systolic_or_diastolic_risk_persentage");
                    String systolic_or_diastolic_risk_category = jo.getString("systolic_or_diastolic_risk_category");
                    String tot_cholesterol_risk_title = jo.getString("tot_cholesterol_risk_title");
                    String tot_cholesterol_risk_persentage = jo.getString("tot_cholesterol_risk_persentage");
                    String tot_cholesterol_risk_category = jo.getString("tot_cholesterol_risk_category");
                    String A41 = jo.getString("A41");
                    String A50 = jo.getString("A50");
*/
/*

                    new AllSharedPrefrences(HeardRisk_Profile.this).Risk_Cal(bmi, Weight_Category, yearrisk10, This_risk_is, A44, A45, A46, A47, A48, To_know_how, year_risk_persent20, year_risk20, B51, smoke_risk_amount, smoke_risk_amount_title
                            , risk_again, systolic_or_diastolic_risk_persentage, systolic_or_diastolic_risk_category, tot_cholesterol_risk_title, tot_cholesterol_risk_persentage, tot_cholesterol_risk_category, A41, A50);
*/


                    new AllSharedPrefrences(RISK_RELATED_QUESTIONS.this).Risk_Cal(bmi, Weight_Category, yearrisk10, This_risk_is, "", "", "", "", "", "", "", "", "", "", ""
                            , "", "", "", "", "", "", "", "");

                    new AllSharedPrefrences(RISK_RELATED_QUESTIONS.this).keyHeartRisk("1");
                    AlertDialog();

                } else {
                    if (statu.equals("1")){
                        AlertDialog();
                    }else {
                        new MyIntent(RISK_RELATED_QUESTIONS.this, go_class, R.anim.enter, R.anim.exit);
                    }
                    JSONObject jj = new JSONObject(result2);
                    JSONObject j = jj.getJSONObject("message");
                    String reason = j.getString("reason");

                }
            } catch (Exception e) {

                Log.e("Exception= ", e + "");
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

    void AlertDialog() {
        dialog = new Dialog(RISK_RELATED_QUESTIONS.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.activity_adavance_complete);
        dialog.setCancelable(true);
        SharedPreferences sp2 = new AllSharedPrefrences(this).UserDataget();
        key_health = sp2.getString("key_Adavance", "");
        key_heart = sp2.getString("Heart_Risk", "");
        LinearLayout heart_lin = (LinearLayout) dialog.findViewById(R.id.heart_lin);
        LinearLayout health_lin = (LinearLayout) dialog.findViewById(R.id.health_lin);

        Button view_plan = (Button) dialog.findViewById(R.id.view_plan);
        if (key_health.equals("1")){
            health_lin.setVisibility(View.VISIBLE);
        }
        if (key_heart.equals("1")){
            heart_lin.setVisibility(View.VISIBLE);

        }

        view_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyIntent(RISK_RELATED_QUESTIONS.this, User_Profile.class, R.anim.enter, R.anim.exit);
            }
        });



        dialog.show();
    }

}
