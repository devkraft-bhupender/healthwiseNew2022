package com.hwi.health.Activitys.Logs;

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

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class DietLog_Result extends AppCompatActivity implements View.OnClickListener{
   // TextView Energy_text,Protein_text,Fat_text,Carbs_text,Sugar_text,fibre_text,sat_fat_text;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Button edit_value;
    String get_calories_cal;
    String getColories,get_Energy,get_Protein,get_Fat,get_Carbs,get_Sugar,get_fibre,get_sat_fat,key_food,get_sodium,get_cholestrol;
    LinearLayout  L_protine, L_carbs, L_sugar, L_fat, L_SODIUM, L_sat, L_CHOLE,L_All,L_FIBER,L_calories;
    TextView per,name_selected;


    float with_water_cal = 0.0f;
    float with_begrage_cal = 0.0f;
    float calories_cal = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Diet Log");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_log__result);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        per = (TextView)findViewById(R.id.per);
        name_selected = (TextView)findViewById(R.id.name_selected);


        L_calories = (LinearLayout) findViewById(R.id.calories);
        L_protine = (LinearLayout) findViewById(R.id.PROTINE);
        L_carbs = (LinearLayout) findViewById(R.id.CARBS);
        L_sugar = (LinearLayout) findViewById(R.id.SUGAR);
        L_fat = (LinearLayout) findViewById(R.id.FAT);
        L_SODIUM = (LinearLayout) findViewById(R.id.SODIUM);
        L_sat = (LinearLayout) findViewById(R.id.SAT_FAT);
        L_CHOLE = (LinearLayout) findViewById(R.id.CHOLE);
        L_FIBER = (LinearLayout) findViewById(R.id.FIBER);
        L_All = (LinearLayout) findViewById(R.id.All);


        try {



            getCircle();

        } catch (Exception e) {

        }

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);


        L_calories.setOnClickListener(this);
        L_protine.setOnClickListener(this);
        L_carbs.setOnClickListener(this);
        L_sugar.setOnClickListener(this);
        L_fat.setOnClickListener(this);
        L_SODIUM.setOnClickListener(this);
        L_sat.setOnClickListener(this);
        L_CHOLE.setOnClickListener(this);
        L_FIBER.setOnClickListener(this);
        L_All.setOnClickListener(this);
    }

    void getCircle(){

        name_selected.setText("");
        //per.setText("All");
        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            //get_Energy = sp.getString("eat_sodium", "");

            getColories = sp.getString("total_Calories", "0.0");
            get_Energy = sp.getString("total_Energy", "0.0");
            get_Protein = sp.getString("total_Protein", "0.0");
            get_Fat = sp.getString("total_Fat", "0.0");
            get_Carbs = sp.getString("total_carbs", "0.0");
            get_Sugar = sp.getString("total_sugar", "0.0");
            get_fibre = sp.getString("total_fibre", "0.0");
            get_sat_fat = sp.getString("total_sat_fat", "0.0");
            get_sodium  =sp.getString("total_souium", "0.0");
            get_cholestrol = sp.getString("total_cholestrol", "0.0");

            Log.e("get_Protein",get_Protein);

//           float eng = Float.parseFloat(get_Energy);
//            float pro = Float.parseFloat(get_Protein);
//            float ft = Float.parseFloat(get_Fat);
//            float car = Float.parseFloat(get_Carbs);
//            float sug = Float.parseFloat(get_Sugar);
//            float fib = Float.parseFloat(get_fibre);
//            float sat = Float.parseFloat(get_sat_fat);
//            float chole = Float.parseFloat(get_cholestrol);
//            float sodi = Float.parseFloat(get_sodium);

//            float Carbs_mul = car * 3.2f;
//            float Protein_mul = pro * 4.0f;
//            float Fat_mul = ft * 9.0f;
//
//            calories_cal = Carbs_mul + Protein_mul + Fat_mul;

            get_calories_cal = getColories+"";


            Log.e("one", get_Protein + "");
            Log.e("two", get_Fat + "");
            Log.e("three", get_Carbs + "");
            Log.e("four", get_Sugar + "");
            Log.e("five", get_fibre + "");
            Log.e("six", get_sat_fat + "");
            Log.e("seven", get_cholestrol + "");
            Log.e("eaight", get_sodium + "");
            Log.e("eaight", get_calories_cal + "");

            if (get_Protein.equals("0.0")) {
                get_Protein = "0";
            } else {
                float pro1 = Math.round(Float.parseFloat(get_Protein)*100);
                get_Protein = pro1+"";
            }

            if (get_Fat.equals("0.0")) {
                get_Fat = "0";
            } else {
                float pro2 = Math.round(Float.parseFloat(get_Fat)*100);
                get_Fat = pro2+"%";
            }

            if (get_Carbs.equals("0.0")) {
                get_Carbs = "0";
            } else {
                float pro3 = Math.round(Float.parseFloat(get_Carbs)*100);
                get_Carbs = pro3+"%";
            }

            if (get_Sugar.equals("0.0")) {
                get_Sugar = "0";
            } else {
                float pro4 = Math.round(Float.parseFloat(get_Sugar)*100);
                get_Sugar = pro4+"%";
            }

            if (get_fibre.equals("0.0")) {
                get_fibre = "0";
            } else {
                float pro5 = Math.round(Float.parseFloat(get_fibre)*100);
                get_fibre = pro5+"%";
            }

            if (get_sat_fat.equals("0.0")) {
                get_sat_fat = "0";
            } else {
                float pro6 = Math.round(Float.parseFloat(get_sat_fat)*100);
                get_sat_fat = pro6+"%";
            }

            if (get_cholestrol.equals("0.0")) {
                get_cholestrol = "0";
            } else {
                float pro7 = Math.round(Float.parseFloat(get_cholestrol)*100);
                get_cholestrol = pro7+"%";
            }

            if (get_sodium.equals("0.0")) {
                get_sodium = "0";
            } else {
                float pro8 = Math.round(Float.parseFloat(get_sodium)*100);
                get_sodium = pro8+"%";
            }

            if (get_calories_cal.equals("0.0")) {
                get_calories_cal = "0";
            } else {
                float pro9 = Math.round(Float.parseFloat(get_calories_cal)*100);
                get_calories_cal = pro9+"%";
            }

            Log.e("2one", get_Protein + "");
            Log.e("2two", get_Fat + "");
            Log.e("2three", get_Carbs + "");
            Log.e("2four", get_Sugar + "");
            Log.e("2five", get_fibre + "");
            Log.e("2six", get_sat_fat + "");
            Log.e("2seven", get_cholestrol + "");
            Log.e("2eaight", get_sodium + "");

            piechart(valu(get_calories_cal), valu(get_Carbs), valu(get_Protein), valu(get_Fat), valu(get_fibre), valu(get_Sugar), valu(get_sat_fat), valu(get_sodium), valu(get_cholestrol));
        } catch (Exception e) {
            Log.e("errr", e + "");
        }
    }

    int valu(String vl) {
        String [] aa = vl.split("\\.");
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
        new MyIntent(DietLog_Result.this, See_Past_Diet_Log.class, R.anim.enter, R.anim.exit);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), See_Past_Diet_Log.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(DietLog_Result.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(DietLog_Result.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(DietLog_Result.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(DietLog_Result.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(DietLog_Result.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
       if (view == L_All) {
            getCircle();
        }

        if (view == L_calories) {
            name_selected.setText("CALORIES");
            per.setText(valu(get_calories_cal)+"%");

            piechart(valu(get_calories_cal), 0, 0, 0, 0, 0, 0,0,0);
        }

        if (view == L_carbs) {
            name_selected.setText("CARBS");
            per.setText(valu(get_Carbs)+"%");

            piechart(0,valu(get_Carbs), 0, 0, 0, 0, 0, 0,0);
        }

        if (view == L_protine) {
            name_selected.setText("PROTEIN");
            per.setText(valu(get_Protein)+"%");

            piechart(0,0,valu(get_Protein), 0, 0, 0, 0, 0, 0);
        }
        if (view == L_fat) {
            name_selected.setText("FAT");
            per.setText(valu(get_Fat)+"%");

            piechart(0, 0,0,valu(get_Fat),  0, 0, 0, 0,0);
        }
        if (view == L_FIBER) {
            name_selected.setText("FIBER");
            per.setText(valu(get_fibre)+"%");

            piechart(0, 0,0, 0,valu(get_fibre),  0, 0, 0,0);
        }
        if (view == L_sugar) {
            name_selected.setText("SUGAR");
            per.setText(valu(get_Sugar)+"%");

            piechart(0, 0, 0, 0,0,valu(get_Sugar),  0, 0,0);
        }

        if (view == L_sat) {
            name_selected.setText("SAT.FAT");
            per.setText(valu(get_sat_fat)+"%");

            piechart(0, 0, 0, 0, 0,0, valu(get_sat_fat), 0,0);
        }
        if (view == L_SODIUM) {
            name_selected.setText("SODIUM");
            per.setText(valu(get_sodium)+"%");
            piechart(0, 0, 0, 0, 0, 0, 0, valu(get_sodium), 0);

        } if (view == L_CHOLE) {
            name_selected.setText("CHOLE");
            per.setText(valu(get_cholestrol)+"%");
            piechart(0, 0, 0, 0, 0, 0, 0,0, valu(get_cholestrol));
        }
        if (view == L_All) {
            getCircle();
        }
    }
}
