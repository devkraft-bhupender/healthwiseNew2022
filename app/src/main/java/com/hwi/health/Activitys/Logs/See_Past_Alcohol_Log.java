package com.hwi.health.Activitys.Logs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.More.Calculator_PKG.Alchol_Result;
import com.hwi.health.Activitys.More.Calculator_PKG.Alcohol_Tracker;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.Models.Alchohal_tracker_model;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.sqlite_database.ProductController_For_All;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class See_Past_Alcohol_Log extends AppCompatActivity implements View.OnClickListener{
    List<Alchohal_tracker_model> diet_list = new ArrayList<>();
    ListView list;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("ALCOHOL");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see__past__diet__log);
        list = (ListView) findViewById(R.id.list);
        TextView tt =  findViewById(R.id.no_data);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        String user_id = sp.getString("Userid", "");

        SharedPreferences sharedPreferences = getSharedPreferences("Date",MODE_PRIVATE);
        String strDate = sharedPreferences.getString("strDate","");
        String strDate2 = sharedPreferences.getString("strDate2","");
        String endDate = sharedPreferences.getString("endDate", "");
        String endDate2 = sharedPreferences.getString("endDate2", "");
        String select = sharedPreferences.getString("select", "5");
        Log.e("strDate",strDate);

        try {
            diet_list = new ProductController_For_All(See_Past_Alcohol_Log.this).getMyAlcho(user_id, select,strDate,endDate);

            if (diet_list.isEmpty()){
                tt.setVisibility(View.VISIBLE);
                tt.setText("No data found between " + strDate2+ " to "+endDate2);
            }else {
                tt.setVisibility(View.GONE);
                MyAdap myAdap = new MyAdap();
                list.setAdapter(myAdap);
            }
        } catch (Exception e) {
            Log.e("diet_list error", e + "");
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alchohal_tracker_model at = diet_list.get(position);

                new AllSharedPrefrences(See_Past_Alcohol_Log.this).Alchol_result(at.getCalories(), at.getStd_size_drinks(), at.getCarbs(), at.getSugar(), at.getSodium(), at.getNumbers(), at.getName(), at.getDate());
                new MyIntent(See_Past_Alcohol_Log.this, Alchol_Result.class, R.anim.enter, R.anim.exit);
            }
        });

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Alcohol_Tracker.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(See_Past_Alcohol_Log.this, Alcohol_Tracker.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(See_Past_Alcohol_Log.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == profile_L) {
            new MyIntent(See_Past_Alcohol_Log.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == log_L) {
            new MyIntent(See_Past_Alcohol_Log.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == plans_L) {
            new MyIntent(See_Past_Alcohol_Log.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(See_Past_Alcohol_Log.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    class MyAdap extends BaseAdapter {
        TextView L_protine, L_carbs, L_sugar, L_fat, L_SODIUM, L_sat, L_CHOLE, L_All, L_FIBER, L_calories;

        @Override
        public int getCount() {
            return diet_list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = getLayoutInflater().inflate(R.layout.alchol_past_log, null);
            TextView name = (TextView) v.findViewById(R.id.name);
            TextView food_type = (TextView) v.findViewById(R.id.food_type);
            TextView date = (TextView) v.findViewById(R.id.date);
            ImageView food_type_image = (ImageView)v.findViewById(R.id.food_type_image);

            LinearLayout sat_size_l = (LinearLayout)v.findViewById(R.id.sat_size_l);
            sat_size_l.setVisibility(View.VISIBLE);

            TextView sat_size = (TextView) v.findViewById(R.id.sat_size);
            L_calories = (TextView) v.findViewById(R.id.calories);
            L_protine = (TextView) v.findViewById(R.id.PROTINE);
            L_carbs = (TextView) v.findViewById(R.id.CARBS);
            L_sugar = (TextView) v.findViewById(R.id.SUGAR);
            L_fat = (TextView) v.findViewById(R.id.FAT);
            L_sat = (TextView) v.findViewById(R.id.SAT_FAT);
            L_CHOLE = (TextView) v.findViewById(R.id.CHOLE);
            L_SODIUM = (TextView) v.findViewById(R.id.SODIUM);
            L_FIBER = (TextView) v.findViewById(R.id.FIBER);

            Alchohal_tracker_model fm = diet_list.get(position);

            name.setText(fm.getName());
            L_calories.setText(Math.round(Float.parseFloat(fm.getCalories()))+"");
            L_carbs.setText(Math.round(Float.parseFloat(fm.getCarbs()))+"");
            L_sugar.setText(Math.round(Float.parseFloat(fm.getSugar()))+"");
            L_SODIUM.setText(Math.round(Float.parseFloat(fm.getSodium()))+"");
            sat_size.setText(fm.getStd_size_drinks());

            food_type_image.setVisibility(View.GONE);
            food_type.setVisibility(View.GONE);
            L_fat.setVisibility(View.GONE);
            L_sat.setVisibility(View.GONE);
            L_CHOLE.setVisibility(View.GONE);
            L_protine.setVisibility(View.GONE);

            try{
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format2 = new SimpleDateFormat("E, dd MMM, yyyy");
                Date dd = format.parse(fm.getDate());
                String ddd = format2.format(dd);
                date.setText(ddd);
            }catch (Exception e){

            }

//            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            Date date = dt.parse(date_s);


            L_FIBER.setVisibility(View.GONE);

            return v;
        }
    }
}
