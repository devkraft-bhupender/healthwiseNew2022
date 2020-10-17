package com.hwi.health.Activitys.Logs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.Models.DietLog_Models;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.sqlite_database.ProductController_For_All;
import com.hwi.health.sqlite_database.TestAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DietLog_activity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ImageView Alcohol, drink, snack, dinner, lunch, breakfast;
    ProductController_For_All controller = new ProductController_For_All(DietLog_activity.this);
    Dialog dialog;
    AutoCompleteTextView food_name;
    ArrayAdapter<DietLog_Models> adapter;
    ArrayList<DietLog_Models> list = new ArrayList<>();
    TestAdapter mDbHelper = null;
    private int mYear, mMonth, mDay, mHour, mMinute;
    int YEAR, MONTH, DAY;
    String get_Alcohol, get_drink, get_snack, get_dinner, get_lunch, get_breakfast;
    LinearLayout see_past_lay;
    TextView see_past;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("DietLog");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_log);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        see_past_lay = (LinearLayout) findViewById(R.id.see_past_lay);
        see_past = (TextView) findViewById(R.id.see_past);

        Alcohol = (ImageView) findViewById(R.id.Alcohol);
        drink = (ImageView) findViewById(R.id.drink);
        snack = (ImageView) findViewById(R.id.snack);
        dinner = (ImageView) findViewById(R.id.dinner);
        lunch = (ImageView) findViewById(R.id.lunch);
        breakfast = (ImageView) findViewById(R.id.breakfast);

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

        Alcohol.setOnClickListener(this);
        drink.setOnClickListener(this);
        snack.setOnClickListener(this);
        dinner.setOnClickListener(this);
        lunch.setOnClickListener(this);
        breakfast.setOnClickListener(this);

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        String user_id = sp.getString("Userid", "");

        try {

            ArrayList<DietLog_Models> diet_list = new ProductController_For_All(DietLog_activity.this).getFoodData(user_id, "5", "","");
            Log.e("diet_list", diet_list.get(0).getCarbs());
            if (!diet_list.isEmpty()) {
//                check_past_log();
                see_past_lay.setVisibility(View.VISIBLE);
                see_past.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check_past_log();
                    }
                });
            }
        } catch (Exception e) {
            Log.e("diet_list error", e + "");
        }
    }

    void check_past_log() {

        final Dialog dialog = new Dialog(DietLog_activity.this,R.style.CustomDialog);
        dialog.setContentView(R.layout.exercise_log_popup);
        TextView xx = (TextView) dialog.findViewById(R.id.text_popup);
        final EditText select_date = (EditText) dialog.findViewById(R.id.select_date);
        final EditText select_To_date = (EditText) dialog.findViewById(R.id.select_To_date);
        final Button go = (Button) dialog.findViewById(R.id.go);
        final LinearLayout botm = (LinearLayout) dialog.findViewById(R.id.botm);
        select_date.setVisibility(View.VISIBLE);
        select_To_date.setVisibility(View.VISIBLE);
        go.setVisibility(View.VISIBLE);
        botm.setVisibility(View.GONE);




        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getDate = select_date.getText().toString();
                String getDateend = select_To_date.getText().toString();
                if (getDate.equals("") || getDateend.equals("")){
                    Toast.makeText(DietLog_activity.this, "Please select date", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.dismiss();
                    SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("select", "4");
                    editor.commit();
                    new MyIntent(DietLog_activity.this, See_Past_Diet_Log.class, R.anim.enter, R.anim.exit);
                }
            }
        });

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog dpd = new DatePickerDialog(DietLog_activity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                YEAR = year;
                                MONTH = monthOfYear;
                                DAY = dayOfMonth;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(YEAR, MONTH, DAY);

                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat format2 = new SimpleDateFormat("E, dd MMM, yyyy");

                                String strDate = format.format(calendar.getTime());
                                String strDate2 = format2.format(calendar.getTime());

                                SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("strDate", strDate);
                                editor.putString("strDate2", strDate2);
                                editor.commit();

                                select_date.setText(strDate2 + "");
                            }
                        }, mYear, mMonth, mDay);

                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();


            }
        });

        select_To_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog dpd = new DatePickerDialog(DietLog_activity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                YEAR = year;
                                MONTH = monthOfYear;
                                DAY = dayOfMonth;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(YEAR, MONTH, DAY);

                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat format2 = new SimpleDateFormat("E, dd MMM, yyyy");

                                String strDate = format.format(calendar.getTime());
                                String strDate2 = format2.format(calendar.getTime());

                                SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("endDate", strDate);
                                editor.putString("endDate2", strDate2);
                                editor.commit();

                                select_To_date.setText(strDate2 + "");
                            }
                        }, mYear, mMonth, mDay);

                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();


            }
        });


        dialog.show();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(DietLog_activity.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), LogActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(DietLog_activity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == profile_L) {
            new MyIntent(DietLog_activity.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == log_L) {
            new MyIntent(DietLog_activity.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (v == plans_L) {
            new MyIntent(DietLog_activity.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(DietLog_activity.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == lunch) {
            controller.trancate();
            SharedPreferences sp = getSharedPreferences("Diet_log_key", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Diet_log_key", "Lunch");
            editor.commit();
            new MyIntent(DietLog_activity.this, DietLog_Details.class, R.anim.enter, R.anim.exit);
        }
        if (v == Alcohol) {
            controller.trancate();
            SharedPreferences sp = getSharedPreferences("Diet_log_key", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Diet_log_key", "Alcohol");
            editor.commit();
            new MyIntent(DietLog_activity.this, DietLog_Details.class, R.anim.enter, R.anim.exit);
        }
        if (v == drink) {
            controller.trancate();
            SharedPreferences sp = getSharedPreferences("Diet_log_key", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Diet_log_key", "Drink");
            editor.commit();
            new MyIntent(DietLog_activity.this, DietLog_Details.class, R.anim.enter, R.anim.exit);
        }
        if (v == snack) {
            controller.trancate();
            SharedPreferences sp = getSharedPreferences("Diet_log_key", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Diet_log_key", "Snack");
            editor.commit();
            new MyIntent(DietLog_activity.this, DietLog_Details.class, R.anim.enter, R.anim.exit);
        }

        if (v == dinner) {
            controller.trancate();
            SharedPreferences sp = getSharedPreferences("Diet_log_key", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Diet_log_key", "Dinner");
            editor.commit();
            new MyIntent(DietLog_activity.this, DietLog_Details.class, R.anim.enter, R.anim.exit);
        }
        if (v == breakfast) {
            controller.trancate();
            SharedPreferences sp = getSharedPreferences("Diet_log_key", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Diet_log_key", "Breakfast");
            editor.commit();
            new MyIntent(DietLog_activity.this, DietLog_Details.class, R.anim.enter, R.anim.exit);
        }

    }


}
