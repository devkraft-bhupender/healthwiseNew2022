package com.hwi.health.Activitys.More.Calculator_PKG;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

public class Eat_out_Plan_Result extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Button edit;
    TextView Energy_text, Protein_text, Fat_text, Carbs_text, Sugar_text, fibre_text, sat_fat_text, cale_text, with_water, with_bevg;
    String get_Energy, get_Protein, get_Fat, get_Carbs, get_Sugar, get_fibre, get_sat_fat, eat_chole, get_with_water, get_with_bevg, get_chole,
            get_calories_cal, get_sodium;
    TextView per, name_selected;
    LinearLayout L_protine, L_carbs, L_sugar, L_fat, L_SODIUM, L_sat, L_suger1, L_CHOLE, L_All, L_FIBER, L_calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Eat Out Result");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_out__plan__result);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        per = (TextView) findViewById(R.id.per);
        name_selected = (TextView) findViewById(R.id.name_selected);

        L_calories = (LinearLayout) findViewById(R.id.calories);
        L_protine = (LinearLayout) findViewById(R.id.PROTINE);
        L_carbs = (LinearLayout) findViewById(R.id.CARBS);
        L_sugar = (LinearLayout) findViewById(R.id.SUGAR);
        L_fat = (LinearLayout) findViewById(R.id.FAT);
        L_SODIUM = (LinearLayout) findViewById(R.id.SODIUM);
        L_sat = (LinearLayout) findViewById(R.id.SAT_FAT);
//        L_suger1 = (LinearLayout) findViewById(R.id.SUGAR1);
        L_CHOLE = (LinearLayout) findViewById(R.id.CHOLE);
        L_FIBER = (LinearLayout) findViewById(R.id.FIBER);
        L_All = (LinearLayout) findViewById(R.id.All);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        edit = (Button) findViewById(R.id.edit);


        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            //get_Energy = sp.getString("eat_sodium", "");
            get_Protein = sp.getString("Protein_div", "0.0");
            get_Fat = sp.getString("Fat_div", "0.0");
            get_Carbs = sp.getString("Carbs_div", "0.0");
            get_Sugar = sp.getString("Sugar_div", "0.0");
            get_fibre = sp.getString("fibre_div", "0.0");
            get_sat_fat = sp.getString("sat_fat_div", "0.0");
            get_with_bevg = sp.getString("with_begrage_cal", "0.0");
            get_with_water = sp.getString("with_water_cal", "0.0");
            get_chole = sp.getString("chole_div", "0.0");
            get_sodium = sp.getString("sodium_div", "0.0");
            get_calories_cal = sp.getString(",calories_cal", "0.0");


            Log.e("get_Protein", get_Protein);
            Log.e("get_Fat", get_Fat);
            Log.e("get_Carbs", get_Carbs);
            Log.e("get_Sugar", get_Sugar);
            Log.e("get_fibre", get_fibre);
            Log.e("get_sat_fat", get_sat_fat);
            Log.e("get_with_bevg", get_with_bevg);
            Log.e("get_with_water", get_with_water);
            Log.e("get_sodium", get_sodium);
            Log.e("get_chole", get_chole);

            //  eat_chole = sp.getString("eat_chole", "");

            //  float eng = Float.parseFloat(get_Energy);
            float pro = Float.parseFloat(get_Protein);
            float ft = Float.parseFloat(get_Fat);
            float car = Float.parseFloat(get_Carbs);
            float sug = Float.parseFloat(get_Sugar);
            float fib = Float.parseFloat(get_fibre);
            float sat = Float.parseFloat(get_sat_fat);
            float chole = Float.parseFloat(get_chole);
            float sodum = Float.parseFloat(get_sodium);
            float calories_cal = Float.parseFloat(get_calories_cal);
            float water = Float.parseFloat(get_with_bevg);
            float bevg = Float.parseFloat(get_with_water);

            Log.e("get_Protein", pro + "");
            Log.e("get_Fat", ft + "");
            Log.e("get_Carbs", car + "");
            Log.e("get_Sugar", sug + "");
            Log.e("get_fibre", fib + "");
            Log.e("get_sat_fat", sat + "");
            Log.e("get_with_bevg", bevg + "");
            Log.e("get_with_water", water + "");
            Log.e("get_sodium", sodum + "");
            Log.e("get_chole", chole + "");
            Log.e("calories_cal", calories_cal + "");



        } catch (Exception e) {
            Log.e("ERRORR", e + "");
        }


        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        edit.setOnClickListener(this);

        L_calories.setOnClickListener(this);
        L_protine.setOnClickListener(this);
        L_carbs.setOnClickListener(this);
        L_sugar.setOnClickListener(this);
        L_fat.setOnClickListener(this);
        L_SODIUM.setOnClickListener(this);
        L_sat.setOnClickListener(this);
        //    L_suger1.setOnClickListener(this);
        L_CHOLE.setOnClickListener(this);
        L_FIBER.setOnClickListener(this);
        L_All.setOnClickListener(this);


        getCircle();
    }


    void getCircle() {

        name_selected.setText("");
        per.setText("All");
        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            //get_Energy = sp.getString("eat_sodium", "");
            get_Protein = sp.getString("Protein_div", "0");
            get_Fat = sp.getString("Fat_div", "0");
            get_Carbs = sp.getString("Carbs_div", "0");
            get_Sugar = sp.getString("Sugar_div", "0");
            get_fibre = sp.getString("fibre_div", "0");
            get_sat_fat = sp.getString("sat_fat_div", "0");
            get_with_bevg = sp.getString("with_begrage_cal", "0");
            get_with_water = sp.getString("with_water_cal", "0");
            get_chole = sp.getString("chole_div", "0");
            get_sodium = sp.getString("sodium_div", "0");
            get_calories_cal = sp.getString("calories_cal", "0.0");
            



            if (get_Protein.equals("0.0")) {
                get_Protein = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Protein));
                get_Protein = pro + "%";
            }

            if (get_Fat.equals("0.0")) {
                get_Fat = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Fat));
                get_Fat = pro + "%";

            }

            if (get_Carbs.equals("0.0")) {
                get_Carbs = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Carbs));
                get_Carbs = pro + "%";
            }


            if (get_Sugar.equals("0.0")) {
                get_Sugar = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Sugar));
                get_Sugar = pro + "%";
            }


            if (get_fibre.equals("0.0")) {
                get_fibre = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_fibre));
                get_fibre = pro + "%";
            }


            if (get_sat_fat.equals("0.0")) {
                get_sat_fat = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_sat_fat));
                get_sat_fat = pro + "%";
            }

            if (get_chole.equals("0.0")) {
                get_chole = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_chole));
                get_chole = pro + "%";
            }


            if (get_sodium.equals("0.0")) {
                get_sodium = "0";
            } else {

                float pro = Math.round(Float.parseFloat(get_sodium));
                get_sodium = pro + "%";
            }

            if (get_calories_cal.equals("0.0")) {
                get_calories_cal = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_calories_cal));
                get_calories_cal = pro + "%";
            }


            piechart(valu(get_calories_cal), valu(get_Protein), valu(get_Carbs), valu(get_Fat), valu(get_fibre), valu(get_sat_fat), valu(get_sodium), valu(get_chole), valu(get_Sugar));
        } catch (Exception e) {
            Log.e("errr", e + "");
        }
    }

    int valu(String vl) {
        String[] aa = vl.split("\\.");
        String aaa = aa[0];
        int gg = Integer.parseInt(aaa);
        return gg;
    }

    void piechart(int one, int two, int three, int four, int five, int six, int seven, int eaight, int nine) {


        ProgressBar mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, one);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        ProgressBar mprogressBar2 = (ProgressBar) findViewById(R.id.circular_progress_bar2);
        ObjectAnimator anim2 = ObjectAnimator.ofInt(mprogressBar2, "progress", 0, two);
        anim2.setDuration(4000);
        anim2.setInterpolator(new DecelerateInterpolator());
        anim2.start();

        ProgressBar mprogressBar3 = (ProgressBar) findViewById(R.id.circular_progress_bar3);
        ObjectAnimator anim3 = ObjectAnimator.ofInt(mprogressBar3, "progress", 0, three);
        anim3.setDuration(4000);
        anim3.setInterpolator(new DecelerateInterpolator());
        anim3.start();

        ProgressBar mprogressBar4 = (ProgressBar) findViewById(R.id.circular_progress_bar4);
        ObjectAnimator anim4 = ObjectAnimator.ofInt(mprogressBar4, "progress", 0, four);
        anim4.setDuration(4000);
        anim4.setInterpolator(new DecelerateInterpolator());
        anim4.start();

        ProgressBar mprogressBar5 = (ProgressBar) findViewById(R.id.circular_progress_bar5);
        ObjectAnimator anim5 = ObjectAnimator.ofInt(mprogressBar5, "progress", 0, five);
        anim5.setDuration(4000);
        anim5.setInterpolator(new DecelerateInterpolator());
        anim5.start();

        ProgressBar mprogressBar6 = (ProgressBar) findViewById(R.id.circular_progress_bar6);
        ObjectAnimator anim6 = ObjectAnimator.ofInt(mprogressBar6, "progress", 0, six);
        anim6.setDuration(4000);
        anim6.setInterpolator(new DecelerateInterpolator());
        anim6.start();

        ProgressBar mprogressBar7 = (ProgressBar) findViewById(R.id.circular_progress_bar7);
        ObjectAnimator anim7 = ObjectAnimator.ofInt(mprogressBar7, "progress", 0, seven);
        anim7.setDuration(4000);
        anim7.setInterpolator(new DecelerateInterpolator());
        anim7.start();


        ProgressBar mprogressBar8 = (ProgressBar) findViewById(R.id.circular_progress_bar8);
        ObjectAnimator anim8 = ObjectAnimator.ofInt(mprogressBar8, "progress", 0, eaight);
        anim8.setDuration(4000);
        anim8.setInterpolator(new DecelerateInterpolator());
        anim8.start();

        ProgressBar mprogressBar9 = (ProgressBar) findViewById(R.id.circular_progress_bar9);
        ObjectAnimator anim9 = ObjectAnimator.ofInt(mprogressBar9, "progress", 0, nine);
        anim9.setDuration(4000);
        anim9.setInterpolator(new DecelerateInterpolator());
        anim9.start();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent in = new Intent(getApplicationContext(), Eat_out_Plan_Details.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(Eat_out_Plan_Result.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(Eat_out_Plan_Result.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(Eat_out_Plan_Result.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(Eat_out_Plan_Result.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(Eat_out_Plan_Result.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == edit) {
          finish();
        }


        if (view == L_calories) {
            name_selected.setText("CALORIES");
            per.setText(valu(get_calories_cal) + "%");
            piechart(valu(get_calories_cal), 0, 0, 0, 0, 0, 0, 0, 0);
        }

        if (view == L_carbs) {
            name_selected.setText("CARBS");
            per.setText(valu(get_Carbs) + "%");
            piechart(0, valu(get_Carbs), 0, 0, 0, 0, 0, 0, 0);
        }

        if (view == L_protine) {
            name_selected.setText("PROTEIN");
            per.setText(valu(get_Protein) + "%");

            piechart(0, 0, valu(get_Protein), 0, 0, 0, 0, 0, 0);
        }
        if (view == L_fat) {
            name_selected.setText("FAT");
            per.setText(valu(get_Fat) + "%");

            piechart(0, 0, 0, valu(get_Fat), 0, 0, 0, 0, 0);
        }
        if (view == L_FIBER) {
            name_selected.setText("FIBER");
            per.setText(valu(get_fibre) + "%");

            piechart(0, 0, 0, 0, valu(get_fibre), 0, 0, 0, 0);
        }
        if (view == L_sugar) {
            name_selected.setText("SUGAR");
            per.setText(valu(get_Sugar) + "%");

            piechart(0, 0, 0, 0, 0, valu(get_Sugar), 0, 0, 0);
        }

        if (view == L_sat) {
            name_selected.setText("SAT.FAT");
            per.setText(valu(get_sat_fat) + "%");

            piechart(0, 0, 0, 0, 0, 0, valu(get_sat_fat), 0, 0);
        }
        if (view == L_SODIUM) {
            name_selected.setText("SODIUM");
            per.setText(valu(get_sodium) + "");
            piechart(0, 0, 0, 0, 0, 0, 0, valu(get_sodium), 0);

        }
        if (view == L_CHOLE) {
            name_selected.setText("CHOLE");
            per.setText(valu(get_chole) + "%");
            piechart(0, 0, 0, 0, 0, 0, 0, 0, valu(get_chole));
        }
        if (view == L_All) {
            getCircle();
        }
    }
}
