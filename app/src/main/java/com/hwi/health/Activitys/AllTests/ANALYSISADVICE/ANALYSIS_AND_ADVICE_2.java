package com.hwi.health.Activitys.AllTests.ANALYSISADVICE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.Ideal_Diat_Model;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class ANALYSIS_AND_ADVICE_2 extends AppCompatActivity implements View.OnClickListener, BaseUrl {

    String strDate2, strDate;
    int S_age;
    int YEAR, MONTH, DAY;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Spinner SP_gender, SP_smoke;
    ArrayList<Ideal_Diat_Model> gender_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> smoke_list = new ArrayList<>();
    ArrayAdapter<Ideal_Diat_Model> gender_adapter;
    ArrayAdapter<Ideal_Diat_Model> smoke_adapter;
    TextInputEditText et_weight, et_Hemoglobin, et_blood_sugar, et_dob, et_feet, et_inch, et_Waist, et_cm, et_Blood_Pressure, et_Diastolic, et_Hematocrit, et_Total_cholesterol, et_LDL_cholesterol, et_HDL_cholesterol, et_Triglycerides, et_Vitamin_levels, et_Vitamin_B12_levels;
    String S_gender, S_smoke, S_blood_sugar, S_weight, S_Hemoglobin, S_dob, S_feet, S_inch, S_Waist, S_cm, S_Blood_Pressure, S_Diastolic, S_Hematocrit, S_Total_cholesterol, S_LDL_cholesterol, S_HDL_cholesterol, S_Triglycerides, S_Vitamin_levels, S_Vitamin_B12_levels;
    Button Calculate, Reset;
    View root;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String randnoo;
    ProgressDialog pd;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("CHECK UP FOR ADULTS");

        setContentView(R.layout.activity_analysis__and__advice);
        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

       // SP_gender = (Spinner) findViewById(R.id.gender);
        SP_smoke = (Spinner) findViewById(R.id.smoke);

        SharedPreferences sp = getSharedPreferences("All_Text",MODE_PRIVATE);
        strDate = sp.getString("strDate","");
        S_age = Integer.parseInt(sp.getString("S_age",""));
        S_feet = sp.getString("fit","");
        S_inch = sp.getString("inch","");
        S_weight = sp.getString("weight","");
        S_gender = sp.getString("gen","");


        SharedPreferences sps = new AllSharedPrefrences(this).UserDataget();
        user_id = sps.getString("Userid", "");

        GetPeofile();

      //  et_weight = (TextInputEditText) findViewById(R.id.weight);
      //  et_dob = (TextInputEditText) findViewById(R.id.dob);
      //  et_feet = (TextInputEditText) findViewById(R.id.feet);
       // et_inch = (TextInputEditText) findViewById(R.id.inch);
        et_Waist = (TextInputEditText) findViewById(R.id.Waist);
        et_cm = (TextInputEditText) findViewById(R.id.cm);
        et_Blood_Pressure = (TextInputEditText) findViewById(R.id.Blood_Pressure);
        et_Diastolic = (TextInputEditText) findViewById(R.id.Diastolic);
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


        root = (View) findViewById(R.id.activity_analysis__and__advice);
        Calculate = (Button) findViewById(R.id.Calculate);
        Reset = (Button) findViewById(R.id.Reset);

       // et_dob.setOnClickListener(this);
        Calculate.setOnClickListener(this);
        Reset.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        spinn();
    }

    @Override
    public void onClick(View v) {

       /* if (v == et_dob) {
            datepicker(et_dob);
        }*/

        if (v == Calculate) {
          /*  S_weight = et_weight.getText().toString();
            //  S_dob = et_dob.getText().toString();
            S_feet = et_feet.getText().toString();
            S_inch = et_inch.getText().toString();*/
            S_Waist = et_Waist.getText().toString();
            S_cm = et_cm.getText().toString();
            S_Blood_Pressure = et_Blood_Pressure.getText().toString();
            S_Diastolic = et_Diastolic.getText().toString();
            S_Hematocrit = et_Hematocrit.getText().toString();
            S_Hemoglobin = et_Hemoglobin.getText().toString();
            S_blood_sugar = et_blood_sugar.getText().toString();
            S_Total_cholesterol = et_Total_cholesterol.getText().toString();
            S_LDL_cholesterol = et_LDL_cholesterol.getText().toString();
            S_HDL_cholesterol = et_HDL_cholesterol.getText().toString();
            S_Triglycerides = et_Triglycerides.getText().toString();
            S_Vitamin_levels = et_Vitamin_levels.getText().toString();
            S_Vitamin_B12_levels = et_Vitamin_B12_levels.getText().toString();


            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();


            pd = new ProgressDialog(ANALYSIS_AND_ADVICE_2.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();

            DataConnection();

        }
        if (v == Reset) {

        }
        if (v == home_L) {
            new MyIntent(ANALYSIS_AND_ADVICE_2.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(ANALYSIS_AND_ADVICE_2.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(ANALYSIS_AND_ADVICE_2.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(ANALYSIS_AND_ADVICE_2.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(ANALYSIS_AND_ADVICE_2.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    void spinn() {
/*
        gender_list.add(new Ideal_Diat_Model("Select your category", "0"));
        gender_list.add(new Ideal_Diat_Model("Male", "Male"));
        gender_list.add(new Ideal_Diat_Model("Female", "Female"));
        gender_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, gender_list);
        SP_gender.setAdapter(gender_adapter);
*/

        smoke_list.add(new Ideal_Diat_Model("Choose", "0"));
        smoke_list.add(new Ideal_Diat_Model("Yes", "yes"));
        smoke_list.add(new Ideal_Diat_Model("No", "no"));
        smoke_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, smoke_list);
        SP_smoke.setAdapter(smoke_adapter);


      /*  SP_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = gender_list.get(position);
                S_gender = idm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


        SP_smoke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = smoke_list.get(position);
                S_smoke = idm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + hwi_check_up_for_adults + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {
                                pd.dismiss();


                                JSONObject joo = jobj.getJSONObject("hwi_check_up_for_adults");
                                JSONObject jo = joo.getJSONObject("hwi_check_up_for_adults");

                                String bmi = jo.getString("Your BMI is");
                                String Weight_Category = jo.getString("Your Weight Category is");
                                String your_wc = jo.getString("As per your WC, do you have central obesity?");

                                String metabolic = jo.getString("Do you have metabolic syndrome?");
                                String Diabetes = jo.getString("Diabetes");
                                String Prediabetes = jo.getString("Prediabetes");
                                String HighBP = jo.getString("High BP");
                                String heart_risk = jo.getString("Your heart risk score (WHO/ISH)");
                                String Anaemia = jo.getString("Anaemia");
                                String vb12 = jo.getString("Vit B12 deficiency");
                                String vd = jo.getString("Vit D deficiency");


                                new AllSharedPrefrences(ANALYSIS_AND_ADVICE_2.this).Analysis(bmi, Weight_Category, your_wc, metabolic, Diabetes, Prediabetes, HighBP, heart_risk, Anaemia, vb12, vd, S_weight, String.valueOf(S_age));
                                new MyIntent(ANALYSIS_AND_ADVICE_2.this, Analysis_Result.class, R.anim.enter, R.anim.exit);


                            } else {
                                pd.dismiss();


                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");
                                Snackbar.make(root, reason, Snackbar.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            pd.dismiss();

                            Log.e("Exception= ", e + "");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("age", S_age + "");
                params.put("sex", S_gender);
                params.put("Weight", S_weight);
                params.put("dob", strDate);
                params.put("ft", S_feet);
                params.put("inc", S_inch);
                params.put("waist_inch", S_Waist);
                params.put("waist_cm", S_cm);
                params.put("systolic", S_Blood_Pressure);
                params.put("diastolic", S_Diastolic);
                params.put("hemoglobin", S_Hemoglobin);
                params.put("hematocrit", S_Hematocrit);
                params.put("fasting_blood", S_blood_sugar);
                params.put("total_colestrol", S_Total_cholesterol);
                params.put("ldl_colestrol", S_LDL_cholesterol);
                params.put("hdl_colestrol", S_HDL_cholesterol);
                params.put("triglycerides", S_Triglycerides);
                params.put("vitamin_d", S_Vitamin_levels);
                params.put("vitamin_b", S_Vitamin_B12_levels);
                params.put("smoke", S_smoke);


                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    /*void datepicker(final EditText et) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(ANALYSIS_AND_ADVICE_2.this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        YEAR = year;
                        MONTH = monthOfYear;
                        DAY = dayOfMonth;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(YEAR, MONTH, DAY);

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");

                        strDate = format.format(calendar.getTime());
                        strDate2 = format2.format(calendar.getTime());

                        et.setText(strDate2 + "");
                        S_age = mYear - year;

                        Log.e("S_age = ", S_age + "");

                    }
                }, mYear, mMonth, mDay);
//        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


        dpd.show();

    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(ANALYSIS_AND_ADVICE_2.this, ANALYSIS_AND_ADVICE_1.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), ANALYSIS_AND_ADVICE_1.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void GetPeofile() {

        final ProgressDialog pd = new ProgressDialog(ANALYSIS_AND_ADVICE_2.this);
        pd.setMessage("please wait...");
        pd.setCancelable(false);
        pd.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + getProfile + new RandomNumber().randno(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");
                        pd.dismiss();
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");

                            if (status.equals("1")) {
                                JSONObject object = jobj.getJSONObject("profile_info");
                                String is_smoke = object.getString("is_smoke");
                                String waist_circumference_inches = object.getString("waist_circumference_inches");
                                String waist_circumference_cms = object.getString("waist_circumference_cms");
                                String blood_pressure1 = object.getString("blood_pressure1");
                                String blood_pressure2 = object.getString("blood_pressure2");
                                String hemoglobin = object.getString("hemoglobin");
                                String hematocrit = object.getString("hematocrit");
                                String fasting_blood_sugar = object.getString("fasting_blood_sugar");
                                String total_cholesterol = object.getString("total_cholesterol");
                                String LDL_cholesterol = object.getString("LDL_cholesterol");
                                String HDL_cholesterol = object.getString("HDL_cholesterol");
                                String triglycerides = object.getString("triglycerides");
                                String vitamin_D_levels = object.getString("vitamin_D_levels");
                                String vitamin_B12_levels = object.getString("vitamin_B12_levels");
                                String did_your_father_get_heart_disease_before_45years_of_age = object.getString("did_your_father_get_heart_disease_before_45years_of_age");
                                String are_you_drugs_for_high_bp = object.getString("are_you_drugs_for_high_bp");

                                if (is_smoke.equalsIgnoreCase("no")){
                                //    int spinnerPosition7 = adppassenger.getPosition(obj.getString("No_of_Passengers"));
                                    SP_smoke.setSelection(2);
                                }else  if (is_smoke.equalsIgnoreCase("yes")){
                                    SP_smoke.setSelection(1);
                                }


                                et_Waist .setText(waist_circumference_inches);
                                et_cm.setText(waist_circumference_cms);
                                et_Blood_Pressure.setText(blood_pressure1);
                                et_Diastolic .setText(blood_pressure2);
                                et_Hemoglobin .setText(hemoglobin);
                                et_Hematocrit.setText(hematocrit);
                                et_blood_sugar.setText(fasting_blood_sugar);
                                et_Total_cholesterol.setText(total_cholesterol);
                                et_LDL_cholesterol.setText(LDL_cholesterol);
                                et_HDL_cholesterol .setText(HDL_cholesterol);
                                et_Triglycerides.setText(triglycerides);
                                et_Vitamin_levels.setText(vitamin_D_levels);
                                et_Vitamin_B12_levels .setText(vitamin_B12_levels);


                                new AllSharedPrefrences(ANALYSIS_AND_ADVICE_2.this).UserAdvanceData1(hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels, waist_circumference_inches, waist_circumference_cms, blood_pressure1, blood_pressure2, did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);


                            } else {
                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");
                            }
                        } catch (Exception e) {
                            pd.dismiss();
                            Log.e("error", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
                pd.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("user_id", user_id);

                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }


}
