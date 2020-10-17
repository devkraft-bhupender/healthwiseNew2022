package com.hwi.health.Activitys.Profile.Second;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.advanced_profile.Body_PARAMETERS;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Health_Profile extends AppCompatActivity implements View.OnClickListener,BaseUrl {
   ImageView adv_text;
   TextView text_dum;
    Button btn_profile;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L,adv_lin;
    TextView T_central_obesity, T_syndrome, T_Diabetes, T_Prediabetes, T_High_BP, T_heart_risk, T_Anaemia, T_Vit_deficiency, T_Vit_D;
    String ideal_weight, tar_weight, weight_loss;
    String count, complete_persentage;
    String randnoo, randnoo2, user_id, result1,result2,result3,adv_key;
    String get_central_obesity, get_syndrome, get_Diabetes, get_Prediabetes, get_High_BP, get_heart_risk, get_Anaemia, get_Vit_deficiency, get_Vit_D;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    String gender, height, weight, dob, feet, inch, is_smoke;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Health Profile");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__profile);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        adv_text =  findViewById(R.id.adv_text);
        T_central_obesity = (TextView) findViewById(R.id.central_obesity);
        T_syndrome = (TextView) findViewById(R.id.syndrome);
        T_Diabetes = (TextView) findViewById(R.id.Diabetes);
        T_Prediabetes = (TextView) findViewById(R.id.Prediabetes);
        T_High_BP = (TextView) findViewById(R.id.High_BP);
        T_heart_risk = (TextView) findViewById(R.id.heart_risk);
        T_Anaemia = (TextView) findViewById(R.id.Anaemia);
        T_Vit_deficiency = (TextView) findViewById(R.id.Vit_deficiency);
        T_Vit_D = (TextView) findViewById(R.id.Vit_D);
        text_dum = (TextView) findViewById(R.id.text_dum);
        adv_lin = (LinearLayout) findViewById(R.id.adv_lin);
        btn_profile = (Button) findViewById(R.id.btn_profile);


        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        btn_profile.setOnClickListener(this);


        try {

            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            user_id = sp.getString("Userid", "");


            ideal_weight = sp.getString("BMI", "");
            tar_weight = sp.getString("weight_range", "");
            sp.getString("weight_Category", "");
            weight_loss = sp.getString("weight_target", "");

            ////////advance/////////
            is_smoke = sp.getString("is_smoke", "");
            gender = sp.getString("gender", "");
            user_id = sp.getString("Userid", "");
            sp.getString("key", "");
            height = sp.getString("height", "");
            weight = sp.getString("weight", "");
            dob = sp.getString("Dob", "");
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

            get_central_obesity = sp.getString("central_obesity", "");
            get_syndrome = sp.getString("syndrome", "");
            get_Diabetes = sp.getString("Diabetes", "");
            get_Prediabetes = sp.getString("Prediabetes", "");
            get_High_BP = sp.getString("High_BP", "");
            get_heart_risk = sp.getString("Weight_Category", "");
            get_Anaemia = sp.getString("Anaemia", "");
            get_Vit_deficiency = sp.getString("Vit_B", "");
            get_Vit_D = sp.getString("Vit_D", "");

            T_central_obesity.setText(get_central_obesity);
            T_syndrome.setText(get_syndrome);
            T_Diabetes.setText(get_Diabetes);
            T_Prediabetes.setText(get_Prediabetes);
            T_High_BP.setText(get_High_BP);
            T_heart_risk.setText(get_heart_risk);
            T_Anaemia.setText(get_Anaemia);
            T_Vit_deficiency.setText(get_Vit_deficiency);
            T_Vit_D.setText(get_Vit_D);

            String[] separated = height.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet = f;
            inch = i;

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();


        } catch (Exception e) {
            Log.e("errrrr", e + "");
        }
        //  Profile_completion_persentage();
        new Task().execute();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Health_Profile.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), User_Profile.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(Health_Profile.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(Health_Profile.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(Health_Profile.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(Health_Profile.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(Health_Profile.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == btn_profile) {
            new MyIntent(Health_Profile.this, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);
        }
    }

    class Task extends AsyncTask<String, Void, String> {

        String result1;

        @Override
        protected String doInBackground(String... params) {
            result1 = "";

            try {
                URL url = new URL(get_profile_completion_persentage );

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);

                Log.e("params", postDataParams.toString());
                Log.e("url", url.toString());

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
                e.printStackTrace();
            }
            return result1;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            Log.e("Persentage = ", result1 + ":");

            try {
                JSONObject jobj = new JSONObject(result1);
                String status = jobj.getString("status");
                String message = jobj.getString("message");

                if (status.equals("1")) {

                    String Total_profile_persetage = jobj.getString("Total_profile_persetage");

                    new AllSharedPrefrences(Health_Profile.this).Profile_complete_persentage(Total_profile_persetage);

                    RandomNumber rn = new RandomNumber();
                    randnoo2 = rn.randno();

                    if (Integer.parseInt(Total_profile_persetage) >= 50){
                        new Advance_Result().execute();
                    }else {
                        adv_text.setVisibility(View.VISIBLE);
                        text_dum.setVisibility(View.VISIBLE);
                        btn_profile.setVisibility(View.VISIBLE);
                    }

                } else {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    class Advance_Result extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Health_Profile.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result2 = "";

            try {
                URL url = new URL(URLS + health_checkup_and_report_analysis_and_advice + randnoo2);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);
                postDataParams.put("gender", gender);
                postDataParams.put("wt", weight);
                postDataParams.put("dob", dob);
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


                Log.e("Advance_Result_params", postDataParams.toString());


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
                e.printStackTrace();
                Log.e("ERRR", e + "");
            }
            return result2;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("Advance_Result = ", result2 + "");

            try {
                JSONObject jobj = new JSONObject(result2);
                String status = jobj.getString("status");
                String message = jobj.getString("message");

                if (status.equals("1")) {
                    adv_text.setVisibility(View.GONE);
                    text_dum.setVisibility(View.GONE);
                    btn_profile.setVisibility(View.GONE);

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
                    new AllSharedPrefrences(Health_Profile.this).Advance_Result(BMI, central_obesity, syndrome, Diabetes, Prediabetes, High_BP, Weight_Category, Anaemia, Vit_B, Vit_D);
                    adv_lin.setVisibility(View.VISIBLE);

                    new AllSharedPrefrences(Health_Profile.this).keyAdavance("1");
                    T_central_obesity.setText(central_obesity);
                    T_syndrome.setText(syndrome);
                    T_Diabetes.setText(Diabetes);
                    T_Prediabetes.setText(Prediabetes);
                    T_High_BP.setText(High_BP);
                    T_heart_risk.setText(Weight_Category);
                    T_Anaemia.setText(Anaemia);
                    T_Vit_deficiency.setText(Vit_B);
                    T_Vit_D.setText(Vit_D);

                } else {
                    adv_lin.setVisibility(View.GONE);
                    adv_text.setVisibility(View.VISIBLE);
                    text_dum.setVisibility(View.VISIBLE);
                    btn_profile.setVisibility(View.VISIBLE);

                }
            } catch (Exception e) {
                Log.e("error........",e+"");
                e.printStackTrace();
               // Toast.makeText(Health_Profile.this, ""+e+"", Toast.LENGTH_LONG).show();
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


}
