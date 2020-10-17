package com.hwi.health.Activitys.AllTests.RISKCALCULATOR;

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

public class RISK_CALCULATOR_2 extends AppCompatActivity implements View.OnClickListener, BaseUrl {

    ProgressDialog pd;
    String strDate2, strDate, randnoo;
    int S_age;
    int YEAR, MONTH, DAY;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Button Calculate, Reset;
    Spinner Sp_gender, Sp_smoke, Sp_diabetes, Sp_Physical, Sp_high_BP, Sp_disease;
    ArrayList<Ideal_Diat_Model> gender_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> smoke_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> diabetes_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> Physical_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> high_BP_list = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> disease_list = new ArrayList<>();
        String user_id;
    View root;
    ArrayAdapter<Ideal_Diat_Model> gender_adapter;
    ArrayAdapter<Ideal_Diat_Model> smoke_adapter;
    ArrayAdapter<Ideal_Diat_Model> diabetes_adapter;
    ArrayAdapter<Ideal_Diat_Model> Physical_adapter;
    ArrayAdapter<Ideal_Diat_Model> high_BP_adapter;
    ArrayAdapter<Ideal_Diat_Model> disease_adapter;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    TextInputEditText weight, dob, feet, inch, Waist, cm, Blood_Pressure, Diastolic, cholesterol, Triglycerides, Fasting_blood_sugar, HDL;
    String S_gender, S_smoke, S_diabetes, S_Physical, S_high_BP, S_disease, S_weight, S_dob, S_feet, S_inch, S_Waist, S_cm, S_Blood_Pressure, S_Diastolic, S_cholesterol, S_Triglycerides, S_Fasting_blood_sugar, S_HDL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("HWI HEART RISK CALCULATOR");

        setContentView(R.layout.activity_risk__calculator);
        root = (View) findViewById(R.id.activity_risk__calculator);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        // Sp_gender = (Spinner) findViewById(R.id.gender);
        Sp_smoke = (Spinner) findViewById(R.id.smoke);
        Sp_diabetes = (Spinner) findViewById(R.id.diabetes);
        Sp_Physical = (Spinner) findViewById(R.id.Physical);
        Sp_high_BP = (Spinner) findViewById(R.id.high_BP);
        Sp_disease = (Spinner) findViewById(R.id.disease);
        Reset = (Button) findViewById(R.id.Reset);
        Calculate = (Button) findViewById(R.id.Calculate);
        // weight = (TextInputEditText) findViewById(R.id.weight);
        // dob = (TextInputEditText) findViewById(R.id.dob);
        // feet = (TextInputEditText) findViewById(R.id.feet);
        // inch = (TextInputEditText) findViewById(R.id.inch);
        Waist = (TextInputEditText) findViewById(R.id.Waist);
        cm = (TextInputEditText) findViewById(R.id.cm);
        Blood_Pressure = (TextInputEditText) findViewById(R.id.Blood_Pressure);
        Diastolic = (TextInputEditText) findViewById(R.id.Diastolic);
        Triglycerides = (TextInputEditText) findViewById(R.id.Triglycerides);
        Fasting_blood_sugar = (TextInputEditText) findViewById(R.id.Fasting_blood_sugar);
        HDL = (TextInputEditText) findViewById(R.id.HDL);
        cholesterol = (TextInputEditText) findViewById(R.id.cholesterol);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        Reset.setOnClickListener(this);
        Calculate.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

//        dob.setOnClickListener(this);
        spinn();

        SharedPreferences sps = new AllSharedPrefrences(this).UserDataget();
        user_id = sps.getString("Userid", "");
        GetPeofile();

        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
        strDate = sp.getString("strDate", "");
        S_age = Integer.parseInt(sp.getString("S_age", ""));
        S_feet = sp.getString("fit", "");
        S_inch = sp.getString("inch", "");
        S_weight = sp.getString("Weight", "");
        S_gender = sp.getString("gen", "");

    }

    @Override
    public void onClick(View v) {

        if (v == Calculate) {
          /*  S_weight = weight.getText().toString();
            S_feet = feet.getText().toString();
            S_inch = inch.getText().toString();*/
            S_Waist = Waist.getText().toString();
            S_cm = cm.getText().toString();
            S_Blood_Pressure = Blood_Pressure.getText().toString();
            S_Diastolic = Diastolic.getText().toString();
            S_cholesterol = cholesterol.getText().toString();
            S_Triglycerides = Triglycerides.getText().toString();
            S_Fasting_blood_sugar = Fasting_blood_sugar.getText().toString();
            S_HDL = HDL.getText().toString();

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();


            pd = new ProgressDialog(RISK_CALCULATOR_2.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();

            DataConnection();

        }
        if (v == Reset) {

        }

        if (v == home_L) {
            new MyIntent(RISK_CALCULATOR_2.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(RISK_CALCULATOR_2.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(RISK_CALCULATOR_2.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(RISK_CALCULATOR_2.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(RISK_CALCULATOR_2.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
       /* if (v == dob) {
            datepicker(dob);
        }*/
    }

    void spinn() {
       /* gender_list.add(new Ideal_Diat_Model("Select your category", "0"));
        gender_list.add(new Ideal_Diat_Model("Male", "Male"));
        gender_list.add(new Ideal_Diat_Model("Female", "Female"));
        gender_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, gender_list);
        Sp_gender.setAdapter(gender_adapter);

        Sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = gender_list.get(position);
                S_gender = idm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
*/

        smoke_list.add(new Ideal_Diat_Model("Choose", "0"));
        smoke_list.add(new Ideal_Diat_Model("Yes", "yes"));
        smoke_list.add(new Ideal_Diat_Model("No", "no"));
        smoke_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, smoke_list);
        Sp_smoke.setAdapter(smoke_adapter);

        Sp_smoke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = smoke_list.get(position);
                S_smoke = idm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        diabetes_list.add(new Ideal_Diat_Model("Choose", "0"));
        diabetes_list.add(new Ideal_Diat_Model("Yes", "yes"));
        diabetes_list.add(new Ideal_Diat_Model("No", "no"));
        diabetes_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, diabetes_list);
        Sp_diabetes.setAdapter(diabetes_adapter);

        Sp_diabetes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = diabetes_list.get(position);
                S_diabetes = idm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Physical_list.add(new Ideal_Diat_Model("Choose your Activity", "0"));
        Physical_list.add(new Ideal_Diat_Model("No regular exercise", "Sedentary"));
        Physical_list.add(new Ideal_Diat_Model("Exercise>20 min,1-3 days/week", "Mind"));
        Physical_list.add(new Ideal_Diat_Model("Exercise 30-60 min,3-4 days/week", "Moderate"));
        Physical_list.add(new Ideal_Diat_Model("Exercise>60 min, 5-7 days/week", "Heavy"));
        Physical_list.add(new Ideal_Diat_Model("Athlete or very active", "Extreme"));

        Physical_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, Physical_list);
        Sp_Physical.setAdapter(Physical_adapter);
        Sp_Physical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = Physical_list.get(position);
                S_Physical = idm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        disease_list.add(new Ideal_Diat_Model("Choose", "0"));
        disease_list.add(new Ideal_Diat_Model("Yes", "yes"));
        disease_list.add(new Ideal_Diat_Model("No", "no"));
        disease_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, disease_list);
        Sp_disease.setAdapter(disease_adapter);
        Sp_disease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = disease_list.get(position);
                S_disease = idm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        high_BP_list.add(new Ideal_Diat_Model("Choose", "0"));
        high_BP_list.add(new Ideal_Diat_Model("Yes", "yes"));
        high_BP_list.add(new Ideal_Diat_Model("No", "no"));
        high_BP_adapter = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, high_BP_list);
        Sp_high_BP.setAdapter(high_BP_adapter);

        Sp_high_BP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model idm = high_BP_list.get(position);
                S_high_BP = idm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    /*void datepicker(final EditText et) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(RISK_CALCULATOR_2.this, R.style.DialogTheme,
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

                        S_dob = format.format(calendar.getTime());
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
        new MyIntent(RISK_CALCULATOR_2.this, RISK_CALCULATOR_1.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), RISK_CALCULATOR_1.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + hwi_heart_risk_calculator + new RandomNumber().randno(),
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


                                JSONObject joo = jobj.getJSONObject("hwi_heart_risk_calculator");
                                JSONObject jo = joo.getJSONObject("hwi_heart_risk_calculator");

                                String bmi = jo.getString("Your BMI");
                                String Weight_Category = jo.getString("Your Weight Category is:");
                                String yearrisk10 = jo.getString("10 year risk for having a heart attack or stroke in people like you is");

                                String This_risk_is = jo.getString("This risk is");
                                String A44 = jo.getString("A44");
                                String A45 = jo.getString("A45");
                                String A46 = jo.getString("A46");
                                String A47 = jo.getString("A47");
                                String A48 = jo.getString("A48");
                                String To_know_how = jo.getString("To know how to manage your heart risk well, go to");
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


                                new AllSharedPrefrences(RISK_CALCULATOR_2.this).Risk_Cal(bmi, Weight_Category, yearrisk10, This_risk_is, A44, A45, A46, A47, A48, To_know_how, year_risk_persent20, year_risk20, B51, smoke_risk_amount, smoke_risk_amount_title
                                        , risk_again, systolic_or_diastolic_risk_persentage, systolic_or_diastolic_risk_category, tot_cholesterol_risk_title, tot_cholesterol_risk_persentage, tot_cholesterol_risk_category, A41, A50);
                                new MyIntent(RISK_CALCULATOR_2.this, Risk_Result.class, R.anim.enter, R.anim.exit);


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
                params.put("tot_cholesterol", S_cholesterol);
                params.put("hdl_colestrol", S_HDL);
                params.put("triglycerides", S_Triglycerides);
                params.put("smoke", S_smoke);
                params.put("fasting_blood_sugar", S_Fasting_blood_sugar);
                params.put("tot_cholesterol", S_HDL);
                params.put("diabetes", S_diabetes);
                params.put("father_brother_heart_disease", S_disease);
                params.put("drugs", S_high_BP);

                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }


    void GetPeofile() {

        final ProgressDialog pd = new ProgressDialog(RISK_CALCULATOR_2.this);
        pd.setMessage("please wait...");
        pd.setCancelable(false);
        pd.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + getProfile + randnoo,
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


                                String is_smoke = object.getString("is_smoke");
                                String is_diabetic = object.getString("is_diabetic");
                                String activity_level_one = object.getString("activity_level_one");
                                String did_your_father = object.getString("did_your_father_get_heart_disease_before_45years_of_age");
                                String are_you_drugs_for_high_bp = object.getString("are_you_drugs_for_high_bp");

                                if (is_smoke.equalsIgnoreCase("no")) {
                                    Sp_smoke.setSelection(2);
                                } else if (is_smoke.equalsIgnoreCase("yes")) {
                                    Sp_smoke.setSelection(1);
                                }

                                if (is_diabetic.equalsIgnoreCase("no")) {
                                    Sp_diabetes.setSelection(2);
                                } else if (is_diabetic.equalsIgnoreCase("yes")) {
                                    Sp_diabetes.setSelection(1);
                                }
                                if (did_your_father.equalsIgnoreCase("no")) {
                                    Sp_disease.setSelection(2);
                                } else if (did_your_father.equalsIgnoreCase("yes")) {
                                    Sp_disease.setSelection(1);
                                }

                                if (are_you_drugs_for_high_bp.equalsIgnoreCase("no")) {
                                    Sp_high_BP.setSelection(2);
                                } else if (are_you_drugs_for_high_bp.equalsIgnoreCase("yes")) {
                                    Sp_high_BP.setSelection(1);
                                }


                                if (activity_level_one.equalsIgnoreCase("No regular exercise")) {
                                    Sp_Physical.setSelection(1);
                                } else if (activity_level_one.equalsIgnoreCase("Exercise>20 min,1-3 days/week") || activity_level_one.equalsIgnoreCase("Exercise>20 min,1-3 ")) {
                                    Sp_Physical.setSelection(2);
                                }else if (activity_level_one.equalsIgnoreCase("Exercise 30-60 min,3-4 days/week") || activity_level_one.equalsIgnoreCase("Exercise 30-60 min,3-4 ")) {
                                    Sp_Physical.setSelection(3);
                                }else if (activity_level_one.equalsIgnoreCase("Exercise>60 min, 5-7 days/week") || activity_level_one.equalsIgnoreCase("Exercise>60 min, ") || activity_level_one.equalsIgnoreCase("Exercise>60 min, 5-7 ")) {
                                    Sp_Physical.setSelection(4);
                                }else if (activity_level_one.equalsIgnoreCase("Athlete or very active")) {
                                    Sp_Physical.setSelection(5);
                                }

                                Waist.setText(waist_circumference_inches);
                                cm.setText(waist_circumference_cms);
                                Blood_Pressure.setText(blood_pressure1);
                                Diastolic.setText(blood_pressure2);
                                Fasting_blood_sugar.setText(fasting_blood_sugar);
                                cholesterol.setText(total_cholesterol);
                                HDL.setText(HDL_cholesterol);
                                Triglycerides.setText(triglycerides);


                                new AllSharedPrefrences(RISK_CALCULATOR_2.this).UserAdvanceData1(hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels, waist_circumference_inches, waist_circumference_cms, blood_pressure1, blood_pressure2, did_your_father, are_you_drugs_for_high_bp);


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
