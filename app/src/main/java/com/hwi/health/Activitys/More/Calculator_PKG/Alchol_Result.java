package com.hwi.health.Activitys.More.Calculator_PKG;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.hwi.health.Activitys.Logs.See_Past_Alcohol_Log;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.sqlite_database.ProductController_For_All;

public class Alchol_Result extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    TextView units_text, calories_text, Sugar_text, Sodium_text, Carbs_text, Std_Size_text;
    Button edit_value;
    String current_date,food_data,total_Calories, total_Std_size_drinks, total_Carbs, total_Sugar, total_Sodium, total_count,user_id;
    Dialog dialog3,dialog;
    ProductController_For_All controller = new ProductController_For_All(Alchol_Result.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Alcohol Tracker");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alchol__result);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        units_text = (TextView) findViewById(R.id.units);
        calories_text = (TextView) findViewById(R.id.calories);
        Sugar_text = (TextView) findViewById(R.id.Sugar);
        Sodium_text = (TextView) findViewById(R.id.Sodium);
        Carbs_text = (TextView) findViewById(R.id.Carbs);
        Std_Size_text = (TextView) findViewById(R.id.Std_Size);
        edit_value = (Button) findViewById(R.id.edit_value);
        edit_value.setVisibility(View.GONE);

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            user_id = sp.getString("Userid", "");
            total_Calories = sp.getString("total_Calories", "");
            total_Std_size_drinks = sp.getString("total_Std_size_drinks", "");
            total_Carbs = sp.getString("total_Carbs", "");
            total_Sugar = sp.getString("total_Sugar", "");
            total_Sodium = sp.getString("total_Sodium", "");
            total_count = sp.getString("total_count", "");
            food_data =  sp.getString("food_data", "");
            current_date= sp.getString("current_date", "");

            Log.e("total_count_Result",total_count+",,,,"+total_Calories+",,,"+total_Std_size_drinks+",,,"+total_Carbs+",,,"+total_Sugar+",,,"+total_Sodium);
            float cal = Float.parseFloat(total_Calories);
            float std = Float.parseFloat(total_Std_size_drinks);
            float car = Float.parseFloat(total_Carbs);
            float sug = Float.parseFloat(total_Sugar);
            float sod = Float.parseFloat(total_Sodium);


            units_text.setText(total_count+" Units");
            calories_text.setText(String.format("%.2f", cal));
            Sugar_text.setText(String.format("%.2f", sug));
            Sodium_text.setText(String.format("%.2f", sod));
            Carbs_text.setText(String.format("%.2f", car));
            Std_Size_text.setText(String.format("%.2f", std));

        } catch (Exception e) {

        }
        //AlertDialog3();
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        edit_value.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Alchol_Result.this, See_Past_Alcohol_Log.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), See_Past_Alcohol_Log.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(Alchol_Result.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(Alchol_Result.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(Alchol_Result.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(Alchol_Result.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(Alchol_Result.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == edit_value) {
            new MyIntent(Alchol_Result.this, Alchol_Detail.class, R.anim.enter2, R.anim.exit2);
        }
    }

    void AlertDialog3() {
        dialog3 = new Dialog(Alchol_Result.this, R.style.CustomDialog);
        dialog3.setContentView(R.layout.alchol_popup_result);
        dialog3.setCancelable(true);
        Button Cocktails = (Button) dialog3.findViewById(R.id.Cocktails);
        Button Regular = (Button) dialog3.findViewById(R.id.Regular);
        ImageView close = (ImageView) dialog3.findViewById(R.id.close);


        //      Log.e("Cocktails",mDbHelper.Cocktails()+"");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog3.dismiss();
            }
        });
        Cocktails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                controller.SaveAlchol(food_data,current_date,total_Calories ,total_Carbs ,total_Sugar ,total_Sodium ,total_Std_size_drinks ,total_count ,user_id);
                AlertDialog();
                dialog3.dismiss();
            }
        });

        Regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog3.dismiss();
            }
        });


        dialog3.show();
    }

    void AlertDialog() {
        dialog = new Dialog(Alchol_Result.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.alchol_suss_popup);
        dialog.setCancelable(true);
        Button ok = (Button) dialog.findViewById(R.id.ok);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);



        //      Log.e("Cocktails",mDbHelper.Cocktails()+"");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        dialog.show();
    }


}
