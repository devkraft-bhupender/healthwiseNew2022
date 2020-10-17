package com.hwi.health.Activitys.AllTests.BMI;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hwi.health.InterFaces.BaseUrl;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BMI_AND_WEIGHT_LOSS_CALCULATOR extends AppCompatActivity implements View.OnClickListener, BaseUrl {

    Spinner gender;
    EditText weight, dob, feet, inch;
    int YEAR, MONTH, DAY;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ArrayList<String> glist = new ArrayList<>();
    ArrayAdapter<String> adap;
    String strDate;
    View root;
    Button Calculate, Reset;
    String randnoo;
    String S_gender, S_dob, S_ft, S_inc, S_weight, high;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    int S_age;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("BMI AND WEIGHT LOSS CALCULATOR");

        setContentView(R.layout.activity_bmi__and__weight__loss__calculator);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        weight = (EditText) findViewById(R.id.weight);
        dob = (EditText) findViewById(R.id.dob);
        feet = (EditText) findViewById(R.id.feet);
        inch = (EditText) findViewById(R.id.inch);
        gender = (Spinner) findViewById(R.id.gender);
        root = (View) findViewById(R.id.activity_bmi__and__weight__loss__calculator);
        Reset = (Button) findViewById(R.id.Reset);
        Calculate = (Button) findViewById(R.id.Calculate);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);


        glist.add("male");
        glist.add("female");

        adap = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, glist);
        gender.setAdapter(adap);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                S_gender = glist.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        try {
            SharedPreferences sp2 = new AllSharedPrefrences(this).UserDataget();
            weight.setText(sp2.getString("weight", ""));
            dob.setText(sp2.getString("Dob", ""));
            high = sp2.getString("height", "");
            String[] separated = high.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet.setText(f);
            inch.setText(i);


            int sincePosition = adap.getPosition(sp2.getString("gender", ""));
            gender.setSelection(sincePosition);
            Log.e("genderrrr", sp2.getString("gender", "") + "....");
        } catch (Exception e) {

        }

        dob.setOnClickListener(this);
        Calculate.setOnClickListener(this);
        Reset.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(BMI_AND_WEIGHT_LOSS_CALCULATOR.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void datepicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(BMI_AND_WEIGHT_LOSS_CALCULATOR.this, R.style.DialogTheme,
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
                        String strDate2 = format2.format(calendar.getTime());


                        dob.setText(strDate2 + "");

                        S_age = mYear - year;

                        Log.e("S_age = ", S_age + "");

                    }
                }, mYear, mMonth, mDay);
//        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


        dpd.show();
    }

    @Override
    public void onClick(View v) {
        if (v == dob) {
            datepicker();
        }
        if (v == Reset) {
            dob.setText("");
            feet.setText("");
            inch.setText("");
            weight.setText("");
        }
        if (v == Calculate) {
            S_dob = dob.getText().toString();
            S_ft = feet.getText().toString();
            S_inc = inch.getText().toString();
            S_weight = weight.getText().toString();

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();

            pd = new ProgressDialog(BMI_AND_WEIGHT_LOSS_CALCULATOR.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();

            DataConnection();
        }
        if (v == home_L) {
            new MyIntent(BMI_AND_WEIGHT_LOSS_CALCULATOR.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(BMI_AND_WEIGHT_LOSS_CALCULATOR.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(BMI_AND_WEIGHT_LOSS_CALCULATOR.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(BMI_AND_WEIGHT_LOSS_CALCULATOR.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(BMI_AND_WEIGHT_LOSS_CALCULATOR.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }


    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + weight_loss_calculator + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        Log.e("response= ", response + " rendom =" + randnoo);

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {

                                JSONObject joo = jobj.getJSONObject("weight_loss_calculator_details");
                                JSONObject jo = joo.getJSONObject("weight_loss_calculator_details");

                                String weight_range = jo.getString("Appropriate weight range for you ( as per your gender and height) is");
                                String Weight_Category = jo.getString("Your Weight Category is");
                                String target = jo.getString("Your target weightloss should be");
                                String bmi = jo.getString("Your BMI is");

                                new AllSharedPrefrences(BMI_AND_WEIGHT_LOSS_CALCULATOR.this).BMI_Result(bmi, weight_range, Weight_Category, target, S_weight);
                                new MyIntent(BMI_AND_WEIGHT_LOSS_CALCULATOR.this, BMI_Result.class, R.anim.enter, R.anim.exit);

                            } else {

                                Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Log.e("errrrr= ", e + "");
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
                params.put("gender", S_gender);
                params.put("age", S_age + "");
                params.put("dob", S_dob);
                params.put("ft", S_ft);
                params.put("inc", S_inc);
                params.put("weight", S_weight);


                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

}
