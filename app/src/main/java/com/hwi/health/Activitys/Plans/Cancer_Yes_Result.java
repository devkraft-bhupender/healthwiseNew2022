package com.hwi.health.Activitys.Plans;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import com.hwi.health.Activitys.More.Welcome_page;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cancer_Yes_Result extends AppCompatActivity implements View.OnClickListener ,BaseUrl {

    EditText date, time;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView text;
    Button submit;
    String key, send_key = "0";
    int YEAR, MONTH, DAY;
    int HORE, MIN;
    java.sql.Time timeValue;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String strDate = "na", randnoo, user_id, timev;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Cancer");
        setContentView(R.layout.activity_cancer__yes__result);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        //text_data = (TextView) findViewById(R.id.text_data);
        submit = (Button) findViewById(R.id.submit);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();

            user_id = sp.getString("Userid", "");
        } catch (Exception e) {

        }
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        submit.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == date) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            final DatePickerDialog dpd = new DatePickerDialog(Cancer_Yes_Result.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            YEAR = year;
                            MONTH = monthOfYear;
                            DAY = dayOfMonth;

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(YEAR, MONTH, DAY);

                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat format2 = new SimpleDateFormat("dd MMM, yyyy");

                            strDate = format.format(calendar.getTime());
                            String strDate2 = format2.format(calendar.getTime());


                            date.setText(strDate2 + "");
                        }
                    }, mYear, mMonth, mDay);


            dpd.show();

        }
        if (v == time) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog tpd = new TimePickerDialog(Cancer_Yes_Result.this, R.style.DialogTheme,
                    new TimePickerDialog.OnTimeSetListener() {


                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            try {

                                HORE = hourOfDay;
                                MIN = minute;

                                String dtStart = HORE + ":" + MIN;
                                SimpleDateFormat format = new SimpleDateFormat("HH:mm");

                                timeValue = new java.sql.Time(format.parse(dtStart).getTime());
                                timev = timeValue + "";


                                SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
                                Date d = sd.parse(timev);
                                sd = new SimpleDateFormat("hh:mm a");

                                timev = sd.format(d);

                                time.setText(timev + "");


                                Log.e("Time 2 = ", timev + "");
                            } catch (Exception ex) {
                                Log.e("Exception = ", ex + "");
                            }
                        }
                    }, mHour, mMinute, false);

            tpd.show();
        }

        if (v == submit) {
            pd = new ProgressDialog(Cancer_Yes_Result.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            DataConnection();
        }

        if (v == home_L) {
            new MyIntent(Cancer_Yes_Result.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Cancer_Yes_Result.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Cancer_Yes_Result.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Cancer_Yes_Result.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Cancer_Yes_Result.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), CancerPlan.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Cancer_Yes_Result.this, CancerPlan.class, R.anim.enter, R.anim.exit);
    }

    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + add_consult + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {
                                Toast.makeText(Cancer_Yes_Result.this, "" + message, Toast.LENGTH_SHORT).show();
                                SharedPreferences sp = getSharedPreferences("cancer_welcome",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("cancer_keyyy","cancer_keyyy");
                                editor.commit();
                                new MyIntent(Cancer_Yes_Result.this, Welcome_page.class, R.anim.enter, R.anim.exit);
                                pd.cancel();

                            } else {
                                Toast.makeText(Cancer_Yes_Result.this, "" + message, Toast.LENGTH_SHORT).show();
                                pd.cancel();
                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");

                            }
                        } catch (Exception e) {
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
                params.put("question_id", send_key);
                params.put("user_id", user_id);
                params.put("date", strDate);
                params.put("time", timev);

                Log.e("params", params + "");
                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }



}
