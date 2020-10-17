package com.hwi.health.Activitys.AllTests.IdealDiat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class IDEAL_DIET_PLAN extends AppCompatActivity implements View.OnClickListener {

    Spinner type, gender;
    ArrayList<String> gen_list = new ArrayList<>();
    String S_type;
    ArrayAdapter<String> gen_adapter;
    EditText weight, dob, feet, inch;
    int YEAR, MONTH, DAY;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ArrayList<String> glist = new ArrayList<>();
    ArrayAdapter<String> adap;
    String strDate;
    View root;
    Button next, Reset;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L, L_protine;
    String randnoo;
    String S_gender, S_dob, S_ft, S_inc, S_weight, high;
    int S_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("FWI DIET CHART FOR WEIGHT LOSS");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal__diet__plan);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        type = (Spinner) findViewById(R.id.type);
        next = (Button) findViewById(R.id.next);
        gender = (Spinner) findViewById(R.id.gender);
        weight = (EditText) findViewById(R.id.weight);
        dob = (EditText) findViewById(R.id.dob);
        feet = (EditText) findViewById(R.id.feet);
        inch = (EditText) findViewById(R.id.inch);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        glist.add("Adult");
        glist.add("Child");

        adap = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, glist);
        type.setAdapter(adap);


        gen_list.add("male");
        gen_list.add("female");

        gen_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, gen_list);
        gender.setAdapter(gen_adapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                S_gender = gen_list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                S_type = glist.get(position);
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


            int sincePosition = gen_adapter.getPosition(sp2.getString("gender", ""));
            gender.setSelection(sincePosition);

        } catch (Exception e) {

        }

        next.setOnClickListener(this);
        dob.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(IDEAL_DIET_PLAN.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
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

    @Override
    public void onClick(View v) {
        if (v == dob) {
            datepicker();
        }

        if (v == home_L) {
            new MyIntent(IDEAL_DIET_PLAN.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(IDEAL_DIET_PLAN.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(IDEAL_DIET_PLAN.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(IDEAL_DIET_PLAN.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(IDEAL_DIET_PLAN.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == next) {
            if (S_type.equals("Adult")) {
                S_dob = dob.getText().toString();
                S_ft = feet.getText().toString();
                S_inc = inch.getText().toString();
                S_weight = weight.getText().toString();

                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();
                SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("strDate", S_dob);
                editor.putString("S_age", S_age + "");
                editor.putString("fit", S_ft);
                editor.putString("inch", S_inc);
                editor.putString("Weight", S_weight);
                editor.putString("gen", S_gender);
                editor.commit();

                Log.e("S_date", "..." + S_dob);
                new MyIntent(IDEAL_DIET_PLAN.this, Ideal_Diat_plan_Adult.class, R.anim.enter, R.anim.exit);

            } else if (S_type.equals("Child")) {
                S_ft = feet.getText().toString();
                S_dob = dob.getText().toString();
                S_inc = inch.getText().toString();
                S_weight = weight.getText().toString();
                SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("strDate", S_dob);
                editor.putString("S_age", S_age + "");
                editor.putString("fit", S_ft);
                editor.putString("inch", S_inc);
                editor.putString("Weight", S_weight);
                editor.putString("gen", S_gender);
                editor.commit();
                Log.e("checkkkkk", strDate + "..." + S_age + ".." + S_ft);
                new MyIntent(IDEAL_DIET_PLAN.this, Ideal_Diat_plan_child.class, R.anim.enter, R.anim.exit);
            }
        }
    }

    void datepicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(IDEAL_DIET_PLAN.this, R.style.DialogTheme,
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

}
