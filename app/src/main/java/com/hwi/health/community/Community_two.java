package com.hwi.health.community;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Community_two extends AppCompatActivity implements BaseUrl,View.OnClickListener {

    EditText edit_qus;
    Button submit;
    String add_qus,randnoo,user_id,strDate,timev;
    private int mYear, mMonth, mDay, mHour, mMinute;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;

    java.sql.Time timeValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Add Question");
        setContentView(R.layout.community_two);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        edit_qus = (EditText) findViewById(R.id.add_qus);
        submit = (Button) findViewById(R.id.submit);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();

            user_id = sp.getString("Userid", "");
        }catch (Exception e){

        }
        submit.setOnClickListener(this);

        //---------- get current date----------//
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDay);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        strDate = format.format(calendar.getTime());
        Log.e("dateeeee",strDate);
//---------- get current time----------//


        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        String dtStart = mHour + ":" + mMinute;
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");

        try {
            timeValue = new java.sql.Time(format2.parse(dtStart).getTime());
            timev = timeValue+"";
            SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
            Date d = sd.parse(timev);
            sd = new SimpleDateFormat("hh:mm a");

            timev = sd.format(d);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.e("Timeeee",timev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Community_two.this, Community_one.class, R.anim.enter2, R.anim.exit2);
    }

    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + post_question + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {
                                Toast.makeText(Community_two.this, ""+message, Toast.LENGTH_SHORT).show();
                                new MyIntent(Community_two.this, Community_one.class, R.anim.enter2, R.anim.exit2);

                            } else {

                                JSONObject jj = new JSONObject(response);
                                JSONObject j  = jj.getJSONObject("message");
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
                params.put("question", add_qus);
                params.put("user_id", user_id);
                params.put("date", strDate);
                params.put("time", timev);

                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Community_one.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Community_two.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Community_two.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Community_two.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Community_two.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Community_two.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == submit) {
            add_qus = edit_qus.getText().toString();
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            DataConnection();
        }
    }
}
