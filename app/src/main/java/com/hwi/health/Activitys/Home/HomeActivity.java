package com.hwi.health.Activitys.Home;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hwi.health.Activitys.Plans.Excercize_plans.CalorieCalculator;
import com.hwi.health.Activitys.Profile.First.Alcohol;
import com.hwi.health.Activitys.Profile.First.Profile_Active_10;
import com.hwi.health.Activitys.Profile.First.Profile_Blood_Suger_9;
import com.hwi.health.Activitys.Profile.First.Profile_Breast_Feeding_5;
import com.hwi.health.Activitys.Profile.First.Profile_Child_Age_6;
import com.hwi.health.Activitys.Profile.First.Profile_Diabetic_7;
import com.hwi.health.Activitys.Profile.First.Profile_Food_habits_11;
import com.hwi.health.Activitys.Profile.First.Profile_Insulin_8;
import com.hwi.health.Activitys.Profile.First.Profile_Menstrul_Cycle_4;
import com.hwi.health.Activitys.Profile.First.Profile_Step1;
import com.hwi.health.Activitys.Profile.First.Profile_Step2;
import com.hwi.health.Activitys.Profile.First.Profile_Step3;
import com.hwi.health.Activitys.Profile.First.Profile_complete;
import com.hwi.health.Activitys.Profile.First.Smoking;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.Alchohal_tracker_model;
import com.hwi.health.Models.DietLog_Models;
import com.hwi.health.Models.ViewpagerModel;
import com.hwi.health.Activitys.AllTests.ANALYSISADVICE.ANALYSIS_AND_ADVICE_1;
import com.hwi.health.Activitys.AllTests.BMI.BMI_AND_WEIGHT_LOSS_CALCULATOR;
import com.hwi.health.Activitys.AllTests.IdealDiat.IDEAL_DIET_PLAN;
import com.hwi.health.Activitys.AllTests.OILCALCULATOR.FAMILY_OIL_CALCULATOR;
import com.hwi.health.Activitys.AllTests.RISKCALCULATOR.RISK_CALCULATOR_1;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.edit_profile.Edit_profile_Activity;
import com.hwi.health.sqlite_database.ProductController_For_All;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    //    PieChart pieChart ;
//    ArrayList<Entry> entries ;
//    ArrayList<String> PieEntryLabels ;
//    PieDataSet pieDataSet ;
//    PieData pieData ;



    Context ctx;
    Dialog dialog=null;
    TextView per, name_selected, name_selected_alcho, per_alcho;
    int vp_pos = 0;
    LinearLayout alcho_click, dite_click, excercise_click, weight_click, Dite, exercise, alcohol, weight;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    LinearLayout L_protine, L_carbs, L_sugar, L_fat, L_SODIUM, L_sat, L_CHOLE, L_All, L_FIBER, L_calories;
    LinearLayout L_All_alcho, L_carbs_alcho, L_sugar_alcho, L_SODIUM_alcho, L_calories_alcho, L_sat_size;
    ImageView arrow_left, arrow_right;
    ViewPager viewPager;
    ArrayList<ViewpagerModel> vlist = new ArrayList<ViewpagerModel>();
    LinearLayout Aerobic, Resistance;
    String user_id, get_get_sat_size, get_calories_cal, get_sodium, get_Protein, get_Fat, get_Carbs, get_Sugar, get_fibre, get_sat_fat, eat_chole, get_with_water, get_with_bevg, get_chole;
    //  TextView with_water,with_bevg;

    LinearLayout this_meal,today, This_Week, This_Month;
    LinearLayout weight_today, weight_This_Week, weight_This_Month;
    LinearLayout alcho_today, alcho_This_Week, alcho_This_Month;
    LinearLayout eat_out_layout_circle, weight_linechart, alcho_linechart;
    LinearLayout week_breakfast,week_lunch,week_dinner,week_snacks,week_click;
    LinearLayout per_meal;

    ArrayList<DietLog_Models> diet_list = new ArrayList<>();
    List<Alchohal_tracker_model> alcho_list = new ArrayList<>();
    int weak_selected=0;


    String _select_dite_type = "3";
    String _select_alcho_type = "3";

    private LineChart mChart;
    ArrayList<Entry> yVals = new ArrayList<Entry>();

    TextView alcho_std_size, alcho_consume_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        per = (TextView) findViewById(R.id.per);
        name_selected = (TextView) findViewById(R.id.name_selected);
        per_alcho = (TextView) findViewById(R.id.per_alcho);
        name_selected_alcho = (TextView) findViewById(R.id.name_selected_alcho);


        //        new MyIntent(LoginActivity.this, Profile_Step1.class, R.anim.enter, R.anim.exit);


        ctx = this;
        This_Month = (LinearLayout) findViewById(R.id.This_Month);
        This_Week = (LinearLayout) findViewById(R.id.This_Week);
        this_meal = (LinearLayout) findViewById(R.id.this_meal);
        today = (LinearLayout) findViewById(R.id.today);
        per_meal=(LinearLayout)findViewById(R.id.per_meal);

        week_breakfast = (LinearLayout) findViewById(R.id.This_Breakfast);
        week_lunch = (LinearLayout) findViewById(R.id.This_Lunch);
        week_dinner = (LinearLayout) findViewById(R.id.This_Dinner);
        week_snacks= (LinearLayout) findViewById(R.id.This_Snacks);

        weight_This_Month = (LinearLayout) findViewById(R.id.weight_This_Month);
        weight_This_Week = (LinearLayout) findViewById(R.id.weight_This_Week);
        weight_today = (LinearLayout) findViewById(R.id.weight_today);
        alcho_This_Month = (LinearLayout) findViewById(R.id.alcho_This_Month);
        alcho_This_Week = (LinearLayout) findViewById(R.id.alcho_This_Week);
        alcho_today = (LinearLayout) findViewById(R.id.alcho_today);


        alcho_consume_size = (TextView) findViewById(R.id.consume_size);
        alcho_std_size = (TextView) findViewById(R.id.std_size);

        vlist.add(new ViewpagerModel("BMI", "BMI AND WEIGHT LOSS CALCULATOR"));
        vlist.add(new ViewpagerModel("DIET", "IDEAL DIET PLAN"));
        vlist.add(new ViewpagerModel("ADULTS", "CHECK UP FOR ADULTS"));
        vlist.add(new ViewpagerModel("HEART", "HEART RISK CALCULATOR"));

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        user_id = sp.getString("Userid", "");

        new GetPercentages().execute();

        // vlist.add(new ViewpagerModel("OIL", "OIL USE/MONTH CALCULATOR"));

        try {
            CustomPagerAdapter cp = new CustomPagerAdapter(ctx);
            viewPager.setAdapter(cp);

        } catch (Exception e) {

        }

        mChart = (LineChart) findViewById(R.id.chart);
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.TRANSPARENT);


        dite_click = (LinearLayout) findViewById(R.id.dite_click);
        alcho_click = (LinearLayout) findViewById(R.id.alcho_click);
        excercise_click = (LinearLayout) findViewById(R.id.excercise_click);
        weight_click = (LinearLayout) findViewById(R.id.weight_click);
        Dite = (LinearLayout) findViewById(R.id.Dite);
        exercise = (LinearLayout) findViewById(R.id.exercise);
        alcohol = (LinearLayout) findViewById(R.id.alcohol);
        weight = (LinearLayout) findViewById(R.id.weight);
        Dite.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white4));


        Aerobic = (LinearLayout) findViewById(R.id.Aerobic);
        Resistance = (LinearLayout) findViewById(R.id.Resistance);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        L_calories = (LinearLayout) findViewById(R.id.calories);
        L_protine = (LinearLayout) findViewById(R.id.PROTINE);
        L_carbs = (LinearLayout) findViewById(R.id.CARBS);
        L_sugar = (LinearLayout) findViewById(R.id.SUGAR);
        L_fat = (LinearLayout) findViewById(R.id.FAT);
        L_sat = (LinearLayout) findViewById(R.id.SAT_FAT);
        L_CHOLE = (LinearLayout) findViewById(R.id.CHOLE);
        L_SODIUM = (LinearLayout) findViewById(R.id.SODIUM);
        L_FIBER = (LinearLayout) findViewById(R.id.FIBER);
        L_All = (LinearLayout) findViewById(R.id.All);

        arrow_left = (ImageView) findViewById(R.id.left);
        arrow_right = (ImageView) findViewById(R.id.right);


        L_SODIUM_alcho = (LinearLayout) findViewById(R.id.SODIUM_alcho);
        L_sugar_alcho = (LinearLayout) findViewById(R.id.SUGAR_alcho);
        L_carbs_alcho = (LinearLayout) findViewById(R.id.CARBS_alcho);
        L_All_alcho = (LinearLayout) findViewById(R.id.All_alcho);
        L_calories_alcho = (LinearLayout) findViewById(R.id.calories_alcho);
        L_sat_size = (LinearLayout) findViewById(R.id.sat_size_l);

/////////////////////////////////////////////
        eat_out_layout_circle = (LinearLayout) findViewById(R.id.eat_out_layout_circle);
        weight_linechart = (LinearLayout) findViewById(R.id.weight_linechart);
        alcho_linechart = (LinearLayout) findViewById(R.id.alcho_linechart);
/////////////////////////////////////////////

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        arrow_left.setOnClickListener(this);
        arrow_right.setOnClickListener(this);
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
        Aerobic.setOnClickListener(this);
        Resistance.setOnClickListener(this);

        L_SODIUM_alcho.setOnClickListener(this);
        L_sugar_alcho.setOnClickListener(this);
        L_carbs_alcho.setOnClickListener(this);
        L_All_alcho.setOnClickListener(this);
        L_calories_alcho.setOnClickListener(this);
        L_sat_size.setOnClickListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                vp_pos = position;
            }
        });

//         pieChart();

        try {
            getCircleEat_Out(_select_dite_type,"0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        listioner();
        SharedPreferences settings = getSharedPreferences("logindetails", Context.MODE_PRIVATE);
       String key=settings.getString("newlogin", "defaultValue");

       if(key.equals("0") || key.equals("1")) {
           try {
               AlertDialog();
               SharedPreferences.Editor editor1 = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
               editor1.putString("newlogin", "3");
               editor1.commit();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }


    void AlertDialog() throws Exception {


        dialog = new Dialog(HomeActivity.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.activity_plan__complete);
        dialog.setCancelable(true);
        SharedPreferences sp2 = new AllSharedPrefrences(HomeActivity.this).UserDataget();
        String key_diet = sp2.getString("key_diet", "");
        String key_bmi = sp2.getString("key_bmi", "");
        LinearLayout weight_lin = (LinearLayout) dialog.findViewById(R.id.weight_lin);
        TextView adv = (TextView) dialog.findViewById(R.id.adv);
        LinearLayout diet_lin = (LinearLayout) dialog.findViewById(R.id.diet_lin);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        LinearLayout excercise_lin = (LinearLayout) dialog.findViewById(R.id.excercise_lin);
        Button view_plan = (Button) dialog.findViewById(R.id.view_plan);
        if (key_bmi.equals("1")) {
            weight_lin.setVisibility(View.VISIBLE);
        }
        if (key_diet.equals("1")) {
            diet_lin.setVisibility(View.VISIBLE);
            excercise_lin.setVisibility(View.VISIBLE);
        }

        view_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyIntent(HomeActivity.this, PlansActivity.class, R.anim.enter, R.anim.exit);
            }
        });
        adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyIntent(HomeActivity.this, Profile_complete.class, R.anim.enter, R.anim.exit);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyIntent(HomeActivity.this, User_Profile.class, R.anim.enter, R.anim.exit);
            }
        });

        dialog.show();
    }
    class GetPercentages extends AsyncTask<String, Void, String> {
        String result;
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(HomeActivity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";
            try {
                URL url = new URL(BaseUrl.get_profile_completion_persentage + new RandomNumber().randno());
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", BaseUrl.AccessToken);
                postDataParams.put("user_id", user_id);

                Log.e("params", params + "");
                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                } else {
                    result = "";
                }
            } catch (Exception e) {
                Log.e("ERRR", e + "");
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("percent+ api = ", result + "");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");

                if (status.equals("1")) {
                    String Total_profile_persetage = jobj.getString("Total_profile_persetage");

                    if (Total_profile_persetage.equals("50")) {

                    } else if (Total_profile_persetage.equals("100")) {

                    } else {
                        AlertDialogStatus();
                    }
                } else {
                }
            } catch (Exception e) {
                Log.e("percent api error = ", e + "");
            }
        }
    }

    void AlertDialogStatus() {
        final Dialog dialogstatus = new Dialog(HomeActivity.this, R.style.CustomDialog);
        dialogstatus.setContentView(R.layout.res);
        dialogstatus.setCancelable(false);
        TextView test1 = (TextView) dialogstatus.findViewById(R.id.test1);
        TextView test2 = (TextView) dialogstatus.findViewById(R.id.test2);
        Button close = (Button) dialogstatus.findViewById(R.id.close);

        test1.setText("Your basic profile is not complete.");
        test2.setText("Please complete basic profile first.");
        close.setText("Proceed");

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        final String gender = sp.getString("gender", "");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogstatus.dismiss();

                SharedPreferences sp = getSharedPreferences("EditPro", MODE_PRIVATE);
                String goKey = sp.getString("gokey", "1");

                if (goKey.equals("1")) {
                    new MyIntent(HomeActivity.this, Profile_Step1.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("2")) {
                    new MyIntent(HomeActivity.this, Profile_Step2.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("3")) {
                    new MyIntent(HomeActivity.this, Profile_Step3.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("4")) {
                    new MyIntent(HomeActivity.this, Profile_Menstrul_Cycle_4.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("5")) {
                    new MyIntent(HomeActivity.this, Profile_Breast_Feeding_5.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("6")) {
                    new MyIntent(HomeActivity.this, Profile_Child_Age_6.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("7")) {
                    new MyIntent(HomeActivity.this, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("8")) {
                    new MyIntent(HomeActivity.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("9")) {
                    new MyIntent(HomeActivity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("10")) {
                    new MyIntent(HomeActivity.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("11")) {
                    new MyIntent(HomeActivity.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("12")) {
                    new MyIntent(HomeActivity.this, Smoking.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("13")) {
                    new MyIntent(HomeActivity.this, Alcohol.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("14")) {
                    new MyIntent(HomeActivity.this, Edit_profile_Activity.class, R.anim.enter, R.anim.exit);
                }
            }
        });

        dialogstatus.show();
    }

    void listioner() {

        This_Month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dite.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white4));
                exercise.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                alcohol.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                weight.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));

                this_meal.setBackground(getResources().getDrawable(R.drawable.orange));
                today.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.white4));


                try {
                    getCircleEat_Out(_select_dite_type,"0");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                weight_linechart.setVisibility(View.GONE);

                eat_out_layout_circle.setVisibility(View.VISIBLE);

                if (dite_click.getVisibility() == View.GONE) {
                    dite_click.setVisibility(View.VISIBLE);
                } else {
                    dite_click.setVisibility(View.GONE);
                }
                alcho_linechart.setVisibility(View.GONE);
                excercise_click.setVisibility(View.GONE);
                weight_click.setVisibility(View.GONE);
                alcho_click.setVisibility(View.GONE);

            }
        });

        This_Week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dite.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white4));
                exercise.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                alcohol.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                weight.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));

                this_meal.setBackground(getResources().getDrawable(R.drawable.orange));
                today.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.white4));


                try {
                    getCircleEat_Out(_select_dite_type,"0");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                weight_linechart.setVisibility(View.GONE);

                eat_out_layout_circle.setVisibility(View.VISIBLE);

                if (dite_click.getVisibility() == View.GONE) {
                    dite_click.setVisibility(View.VISIBLE);
                } else {
                    dite_click.setVisibility(View.GONE);
                }
                alcho_linechart.setVisibility(View.GONE);
                excercise_click.setVisibility(View.GONE);
                weight_click.setVisibility(View.GONE);
                alcho_click.setVisibility(View.GONE);

            }
        });


        Dite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dite.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white4));
                exercise.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                alcohol.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                weight.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));

                this_meal.setBackground(getResources().getDrawable(R.drawable.orange));
                today.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.white4));


                try {
                    getCircleEat_Out(_select_dite_type,"0");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                weight_linechart.setVisibility(View.GONE);

                eat_out_layout_circle.setVisibility(View.VISIBLE);

                if (dite_click.getVisibility() == View.GONE) {
                    dite_click.setVisibility(View.VISIBLE);

                } else {
                    dite_click.setVisibility(View.GONE);
                    per_meal.setVisibility(View.GONE);
                }
                alcho_linechart.setVisibility(View.GONE);
                excercise_click.setVisibility(View.GONE);
                weight_click.setVisibility(View.GONE);
                alcho_click.setVisibility(View.GONE);

            }
        });

        Dite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dite.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white4));
                exercise.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                alcohol.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                weight.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));

                this_meal.setBackground(getResources().getDrawable(R.drawable.orange));
                today.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.white4));


                try {
                    getCircleEat_Out(_select_dite_type,"0");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                weight_linechart.setVisibility(View.GONE);

                eat_out_layout_circle.setVisibility(View.VISIBLE);

                if (dite_click.getVisibility() == View.GONE) {
                    dite_click.setVisibility(View.VISIBLE);
                } else {
                    dite_click.setVisibility(View.GONE);
                    per_meal.setVisibility(View.GONE);

                }
                alcho_linechart.setVisibility(View.GONE);
                excercise_click.setVisibility(View.GONE);
                weight_click.setVisibility(View.GONE);
                alcho_click.setVisibility(View.GONE);

            }
        });

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dite.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                exercise.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white4));
                alcohol.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                weight.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));

                dite_click.setVisibility(View.GONE);
                weight_click.setVisibility(View.GONE);
                alcho_click.setVisibility(View.GONE);

                weight_linechart.setVisibility(View.GONE);
                eat_out_layout_circle.setVisibility(View.GONE);
                alcho_linechart.setVisibility(View.GONE);
                if (excercise_click.getVisibility() == View.GONE) {
                    excercise_click.setVisibility(View.VISIBLE);
                } else {
                    excercise_click.setVisibility(View.GONE);
                }
            }
        });


        alcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alcho_today.setBackground(getResources().getDrawable(R.drawable.orange));
                alcho_This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                alcho_This_Month.setBackground(getResources().getDrawable(R.drawable.white4));

                try {
                    SharedPreferences sp = new AllSharedPrefrences(HomeActivity.this).UserDataget();
                    String gender = sp.getString("gender", "");
                    if (gender.equals("female")) {
                        alcho_std_size.setText("Standard drinks 45");
                    } else {
                        alcho_std_size.setText("Standard drinks 60");
                    }

                    getCircleAlcho(_select_alcho_type);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                dite_click.setVisibility(View.GONE);
                excercise_click.setVisibility(View.GONE);
                weight_click.setVisibility(View.GONE);

                eat_out_layout_circle.setVisibility(View.GONE);
                weight_linechart.setVisibility(View.GONE);
                alcho_linechart.setVisibility(View.VISIBLE);

                if (alcho_click.getVisibility() == View.GONE) {
                    alcho_click.setVisibility(View.VISIBLE);
                    alcho_This_Month.setBackground(getResources().getDrawable(R.drawable.white4));
                } else {
                    alcho_click.setVisibility(View.GONE);
                }

                Dite.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                exercise.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                alcohol.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white4));
                weight.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
            }
        });

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Task("-31 days").execute();

                weight_today.setBackground(getResources().getDrawable(R.drawable.orange));
                weight_This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                weight_This_Month.setBackground(getResources().getDrawable(R.drawable.white4));

                alcho_click.setVisibility(View.GONE);
                dite_click.setVisibility(View.GONE);
                excercise_click.setVisibility(View.GONE);
//                 weight_click.setVisibility(View.VISIBLE);
                alcho_linechart.setVisibility(View.GONE);
                if (weight_click.getVisibility() == View.GONE) {
                    weight_click.setVisibility(View.VISIBLE);
                } else {
                    weight_click.setVisibility(View.GONE);
                }

                eat_out_layout_circle.setVisibility(View.GONE);
                weight_linechart.setVisibility(View.VISIBLE);

                Dite.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                exercise.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                alcohol.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white3));
                weight.setBackground(HomeActivity.this.getResources().getDrawable(R.drawable.white4));
            }
        });

        //////////////////////////////////


        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _select_dite_type = "1";
                today.setBackground(getResources().getDrawable(R.drawable.white4));
                this_meal.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                per_meal.setVisibility(View.VISIBLE);
                try {
                    getCircleEat_Out(_select_dite_type,"0");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        This_Week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weak_selected=1;
                today.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.white4));
                This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                this_meal.setBackground(getResources().getDrawable(R.drawable.orange));
                per_meal.setVisibility(View.VISIBLE);
                week_breakfast.setBackground(getResources().getDrawable(R.drawable.blue));
                week_lunch.setBackground(getResources().getDrawable(R.drawable.blue));
                week_dinner.setBackground(getResources().getDrawable(R.drawable.blue));
                week_snacks.setBackground(getResources().getDrawable(R.drawable.blue));

                _select_dite_type = "2";
                try {
                    getCircleEat_Out(_select_dite_type,"0");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        This_Month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weak_selected=2;
                today.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.white4));
                this_meal.setBackground(getResources().getDrawable(R.drawable.orange));
                _select_dite_type = "3";
                per_meal.setVisibility(View.VISIBLE);
                week_breakfast.setBackground(getResources().getDrawable(R.drawable.blue));
                week_lunch.setBackground(getResources().getDrawable(R.drawable.blue));
                week_dinner.setBackground(getResources().getDrawable(R.drawable.blue));
                week_snacks.setBackground(getResources().getDrawable(R.drawable.blue));
                try {
                    getCircleEat_Out(_select_dite_type,"0");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        this_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                this_meal.setBackground(getResources().getDrawable(R.drawable.white4));
                _select_dite_type = "0";
                per_meal.setVisibility(View.GONE);
                try {
                    getCircleEat_Out(_select_dite_type,"0");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

       week_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today.setBackground(getResources().getDrawable(R.drawable.orange));
                week_breakfast.setBackground(getResources().getDrawable(R.drawable.white4));
                week_lunch.setBackground(getResources().getDrawable(R.drawable.blue));
                week_dinner.setBackground(getResources().getDrawable(R.drawable.blue));
                week_snacks.setBackground(getResources().getDrawable(R.drawable.blue));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
              //  this_meal.setBackground(getResources().getDrawable(R.drawable.white4));
                _select_dite_type = "10";
                try {
                    if(weak_selected==1) {
                        This_Week.setBackground(getResources().getDrawable(R.drawable.white4));
                        This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                        getCircleEat_Out("2", "10");
                    }
                    else {
                        This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                        This_Month.setBackground(getResources().getDrawable(R.drawable.white4));
                        getCircleEat_Out("3", "10");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        week_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                week_breakfast.setBackground(getResources().getDrawable(R.drawable.blue));
                week_lunch.setBackground(getResources().getDrawable(R.drawable.white4));
                week_dinner.setBackground(getResources().getDrawable(R.drawable.blue));
                week_snacks.setBackground(getResources().getDrawable(R.drawable.blue));
                today.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
              //  this_meal.setBackground(getResources().getDrawable(R.drawable.white4));
                _select_dite_type = "11";
                if(weak_selected==1) {
                    try {
                        This_Week.setBackground(getResources().getDrawable(R.drawable.white4));
                        This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                        getCircleEat_Out("2","11");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                        This_Month.setBackground(getResources().getDrawable(R.drawable.white4));
                        getCircleEat_Out("3","11");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });


        week_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                week_breakfast.setBackground(getResources().getDrawable(R.drawable.blue));
                week_lunch.setBackground(getResources().getDrawable(R.drawable.blue));
                week_dinner.setBackground(getResources().getDrawable(R.drawable.white4));
                week_snacks.setBackground(getResources().getDrawable(R.drawable.blue));

                today.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                week_dinner.setBackground(getResources().getDrawable(R.drawable.white4));
                _select_dite_type = "12";
                if(weak_selected==1) {
                    try {
                        This_Week.setBackground(getResources().getDrawable(R.drawable.white4));
                        This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                        getCircleEat_Out("2","12");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                        This_Month.setBackground(getResources().getDrawable(R.drawable.white4));
                        getCircleEat_Out("3","12");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        week_snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today.setBackground(getResources().getDrawable(R.drawable.orange));

                week_breakfast.setBackground(getResources().getDrawable(R.drawable.blue));
                week_lunch.setBackground(getResources().getDrawable(R.drawable.blue));
                week_dinner.setBackground(getResources().getDrawable(R.drawable.blue));
                week_snacks.setBackground(getResources().getDrawable(R.drawable.white4));

                _select_dite_type = "13";
                if(weak_selected==1) {
                    try {
                        This_Week.setBackground(getResources().getDrawable(R.drawable.white4));
                        This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                        getCircleEat_Out("2","13");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                        This_Month.setBackground(getResources().getDrawable(R.drawable.white4));
                        getCircleEat_Out("3", "13");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                }
        });
        alcho_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alcho_today.setBackground(getResources().getDrawable(R.drawable.white4));
                alcho_This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                alcho_This_Month.setBackground(getResources().getDrawable(R.drawable.orange));

                SharedPreferences sp = new AllSharedPrefrences(HomeActivity.this).UserDataget();
                String gender = sp.getString("gender", "");
                if (gender.equals("female")) {
                    alcho_std_size.setText("Standard drinks 3");
                } else {
                    alcho_std_size.setText("Standard drinks 5");
                }

                _select_alcho_type = "1";
                try {
                    getCircleAlcho(_select_alcho_type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        alcho_This_Week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alcho_today.setBackground(getResources().getDrawable(R.drawable.orange));
                alcho_This_Week.setBackground(getResources().getDrawable(R.drawable.white4));
                alcho_This_Month.setBackground(getResources().getDrawable(R.drawable.orange));

                _select_alcho_type = "2";
                SharedPreferences sp = new AllSharedPrefrences(HomeActivity.this).UserDataget();
                String gender = sp.getString("gender", "");
                if (gender.equals("female")) {
                    alcho_std_size.setText("Standard drinks 11");
                } else {
                    alcho_std_size.setText("Standard drinks 14");
                }
                try {
                    getCircleAlcho(_select_alcho_type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        alcho_This_Month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _select_alcho_type = "3";
                alcho_today.setBackground(getResources().getDrawable(R.drawable.orange));
                alcho_This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                alcho_This_Month.setBackground(getResources().getDrawable(R.drawable.white4));

                SharedPreferences sp = new AllSharedPrefrences(HomeActivity.this).UserDataget();
                String gender = sp.getString("gender", "");
                if (gender.equals("female")) {
                    alcho_std_size.setText("Standard drinks 45");
                } else {
                    alcho_std_size.setText("Standard drinks 60");
                }

                try {
                    getCircleAlcho(_select_alcho_type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        weight_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weight_today.setBackground(getResources().getDrawable(R.drawable.white4));
                weight_This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                weight_This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                new Task("-0 days").execute();
            }
        });

        weight_This_Week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight_today.setBackground(getResources().getDrawable(R.drawable.orange));
                weight_This_Week.setBackground(getResources().getDrawable(R.drawable.white4));
                weight_This_Month.setBackground(getResources().getDrawable(R.drawable.orange));
                new Task("-7 days").execute();
            }
        });


        weight_This_Month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight_today.setBackground(getResources().getDrawable(R.drawable.orange));
                weight_This_Week.setBackground(getResources().getDrawable(R.drawable.orange));
                weight_This_Month.setBackground(getResources().getDrawable(R.drawable.white4));
                new Task("-31 days").execute();
            }
        });

    }


    void getCircleEat_Out(String _select_dite_type,String meal_type) throws Exception {



        get_Protein = "0";
        get_Fat = "0";
        get_Carbs = "0";
        get_Sugar = "0";
        get_fibre = "0";
        get_sat_fat = "0";
        get_chole = "0";
        get_sodium = "0";
        get_calories_cal = "0";

        diet_list = new ProductController_For_All(HomeActivity.this).getFoodData(user_id, _select_dite_type, "", "");
        //   Log.e("diet_list", diet_list.get(0).getFoodname());
        name_selected.setText("");
        per.setText("All");

        float Sf_get_Protein = 0.0f;
        float Sf_get_Fat = 0.0f;
        float Sf_get_Carbs = 0.0f;
        float Sf_get_Sugar = 0.0f;
        float Sf_get_fibre = 0.0f;
        float Sf_get_sat_fat = 0.0f;
        float Sf_get_chole = 0.0f;
        float Sf_get_sodium = 0.0f;
        float Sf_get_calories_cal = 0.0f;
        float _calories = 0.0f;
        float _carb = 0.0f;
        float _prot = 0.0f;
        float _fat = 0.0f;
        float _SFA = 0.0f;
        float _chole = 0.0f;
        float _sugar = 0.0f;
        float _sodium = 0.0f;
        float _fibre = 0.0f;

        float grand_calories = 0.0f;
        float grand_carb = 0.0f;
        float grand_prot = 0.0f;
        float grand_fat = 0.0f;
        float grand_SFA = 0.0f;
        float grand_chole = 0.0f;
        float grand_sugar = 0.0f;
        float grand_sodium = 0.0f;
        float grand_fibre = 0.0f;

        int breakfast_count = 0;
        int lunch_count = 0;
        int dinner_count = 0;
        int snack_count = 0;




        float breakfast_get_Protein = 0.0f;
        float breakfast_get_Fat = 0.0f;
        float breakfast_get_Carbs = 0.0f;
        float breakfast_get_Sugar = 0.0f;
        float breakfast_get_fibre = 0.0f;
        float breakfast_get_sat_fat = 0.0f;
        float breakfast_get_chole = 0.0f;
        float breakfast_get_sodium = 0.0f;
        float breakfast_get_calories_cal = 0.0f;

        float lunch_get_Protein = 0.0f;
        float lunch_get_Fat = 0.0f;
        float lunch_get_Carbs = 0.0f;
        float lunch_get_Sugar = 0.0f;
        float lunch_get_fibre = 0.0f;
        float lunch_get_sat_fat = 0.0f;
        float lunch_get_chole = 0.0f;
        float lunch_get_sodium = 0.0f;
        float lunch_get_calories_cal = 0.0f;

        float dinner_get_Protein = 0.0f;
        float dinner_get_Fat = 0.0f;
        float dinner_get_Carbs = 0.0f;
        float dinner_get_Sugar = 0.0f;
        float dinner_get_fibre = 0.0f;
        float dinner_get_sat_fat = 0.0f;
        float dinner_get_chole = 0.0f;
        float dinner_get_sodium = 0.0f;
        float dinner_get_calories_cal = 0.0f;

        float snack_get_Protein = 0.0f;
        float snack_get_Fat = 0.0f;
        float snack_get_Carbs = 0.0f;
        float snack_get_Sugar = 0.0f;
        float snack_get_fibre = 0.0f;
        float snack_get_sat_fat = 0.0f;
        float snack_get_chole = 0.0f;
        float snack_get_sodium = 0.0f;
        float snack_get_calories_cal = 0.0f;


        if(!( meal_type.equals("11") || meal_type.equals("12") || meal_type.equals("13"))) {
            grand_calories += CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "Calories", getApplicationContext());
            grand_carb += CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "carb", getApplicationContext());
            grand_prot += CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "prot", getApplicationContext());
            grand_fat += CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fat", getApplicationContext());
            grand_SFA += CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "SFA", getApplicationContext());
            grand_chole += CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "chole", getApplicationContext());
            grand_sugar += CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sugar", getApplicationContext());
            grand_sodium += CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sodium", getApplicationContext());
            grand_fibre += CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fibre", getApplicationContext());
        }
        if(!( meal_type.equals("10") || meal_type.equals("12") || meal_type.equals("13"))) {
            grand_calories += CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "Calories", getApplicationContext());
            grand_carb += CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "carb", getApplicationContext());
            grand_prot += CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "prot", getApplicationContext());
            grand_fat += CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fat", getApplicationContext());
            grand_SFA += CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "SFA", getApplicationContext());
            grand_chole += CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "chole", getApplicationContext());
            grand_sugar += CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sugar", getApplicationContext());
            grand_sodium += CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sodium", getApplicationContext());
            grand_fibre += CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fibre", getApplicationContext());
        }
        if(!( meal_type.equals("10") || meal_type.equals("11") || meal_type.equals("13"))) {
            grand_calories += CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "Calories", getApplicationContext());
            grand_carb += CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "carb", getApplicationContext());
            grand_prot += CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "prot", getApplicationContext());
            grand_fat += CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fat", getApplicationContext());
            grand_SFA += CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "SFA", getApplicationContext());
            grand_chole += CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "chole", getApplicationContext());
            grand_sugar += CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sugar", getApplicationContext());
            grand_sodium += CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sodium", getApplicationContext());
            grand_fibre += CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fibre", getApplicationContext());
        }
        if(!( meal_type.equals("10") || meal_type.equals("11") || meal_type.equals("12"))) {
            grand_calories += CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "Calories", getApplicationContext());
            grand_carb += CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "carb", getApplicationContext());
            grand_prot += CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "prot", getApplicationContext());
            grand_fat += CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fat", getApplicationContext());
            grand_SFA += CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "SFA", getApplicationContext());
            grand_chole += CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "chole", getApplicationContext());
            grand_sugar += CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sugar", getApplicationContext());
            grand_sodium += CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sodium", getApplicationContext());
            grand_fibre += CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fibre", getApplicationContext());
        }



        try {

            for (int i = 0; i < diet_list.size(); i++) {

                if (_select_dite_type.equals("0")) {


                    String food_type = diet_list.get(i).getFood_type();
                    if(food_type.equals("Alcohol"))
                        continue;

                    if (food_type.equals("Breakfast")) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fibre", getApplicationContext());
                    } else if (food_type.equals("Lunch")) {
                        _calories = _calories = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fibre", getApplicationContext());

                    } else if (food_type.equals("Dinner")) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fibre", getApplicationContext());
                    } else if (food_type.equals("Snack")) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fibre", getApplicationContext());
                    }
                    else
                    {
                        _calories = 1;
                        _carb = 1;
                        _prot = 1;
                        _fat = 1;
                        _SFA = 1;
                        _chole = 1;
                        _sugar = 1;
                        _sodium = 1;
                        _fibre = 1;
                    }


                    float f_get_Protein = Float.parseFloat(diet_list.get(i).getProtein()) * _prot;
                    float f_get_Fat = Float.parseFloat(diet_list.get(i).getFat()) * _fat;
                    float f_get_Carbs = Float.parseFloat(diet_list.get(i).getCarbs()) * _carb;
                    float f_get_Sugar = Float.parseFloat(diet_list.get(i).getSugar()) * _sugar;
                    float f_get_fibre = Float.parseFloat(diet_list.get(i).getFibre()) * _fibre;
                    float f_get_sat_fat = Float.parseFloat(diet_list.get(i).getSat_fat()) * _SFA;
                    float f_get_chole = Float.parseFloat(diet_list.get(i).getCholestrol()) * _chole;
                    float f_get_sodium = Float.parseFloat(diet_list.get(i).getSodium()) * _sodium;
                    float f_get_calories_cal = Float.parseFloat(diet_list.get(i).getColories()) * _calories;

                    Sf_get_Protein = Sf_get_Protein + f_get_Protein;
                    Sf_get_Fat = Sf_get_Fat + f_get_Fat;
                    Sf_get_Carbs = Sf_get_Carbs + f_get_Carbs;
                    Sf_get_Sugar = Sf_get_Sugar + f_get_Sugar;
                    Sf_get_fibre = Sf_get_fibre + f_get_fibre;
                    Sf_get_sat_fat = Sf_get_sat_fat + f_get_sat_fat;
                    Sf_get_chole = Sf_get_chole + f_get_chole;
                    Sf_get_sodium = Sf_get_sodium + f_get_sodium;
                    Sf_get_calories_cal = Sf_get_calories_cal + f_get_calories_cal;

                    get_Protein = String.valueOf(Sf_get_Protein/_prot);
                    get_Fat = String.valueOf(Sf_get_Fat/grand_fat);
                    get_Carbs = String.valueOf(Sf_get_Carbs/_carb);
                    get_Sugar = String.valueOf(Sf_get_Sugar/_sugar);
                    get_fibre = String.valueOf(Sf_get_fibre/_fibre);
                    get_sat_fat = String.valueOf(Sf_get_sat_fat/_SFA);
                    get_chole = String.valueOf(Sf_get_chole/_chole);
                    get_sodium = String.valueOf(Sf_get_sodium/_sodium);
                    get_calories_cal = String.valueOf(Sf_get_calories_cal/_calories);

                    Log.e("f_get_calories_cal", Sf_get_calories_cal + " = " + get_calories_cal);

                }

               else  if (_select_dite_type.equals("1")) {


                    String food_type = diet_list.get(i).getFood_type();
                    if (food_type.equals("Breakfast")) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fibre", getApplicationContext());
                    } else if (food_type.equals("Lunch")) {
                        _calories = _calories = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fibre", getApplicationContext());

                    } else if (food_type.equals("Dinner")) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fibre", getApplicationContext());
                    } else if (food_type.equals("Snack")) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fibre", getApplicationContext());
                    }
                    else if (!(food_type.equals("Dinner") || food_type.equals("Breakfast") || food_type.equals("Lunch") || food_type.equals("Snack")))
                    {
                        _calories = 1;
                        _carb = 1;
                        _prot = 1;
                        _fat = 1;
                        _SFA = 1;
                        _chole = 1;
                        _sugar = 1;
                        _sodium = 1;
                        _fibre = 1;
                    }


                    float f_get_Protein = Float.parseFloat(diet_list.get(i).getProtein()) * _prot;
                    float f_get_Fat = Float.parseFloat(diet_list.get(i).getFat()) * _fat;
                    float f_get_Carbs = Float.parseFloat(diet_list.get(i).getCarbs()) * _carb;
                    float f_get_Sugar = Float.parseFloat(diet_list.get(i).getSugar()) * _sugar;
                    float f_get_fibre = Float.parseFloat(diet_list.get(i).getFibre()) * _fibre;
                    float f_get_sat_fat = Float.parseFloat(diet_list.get(i).getSat_fat()) * _SFA;
                    float f_get_chole = Float.parseFloat(diet_list.get(i).getCholestrol()) * _chole;
                    float f_get_sodium = Float.parseFloat(diet_list.get(i).getSodium()) * _sodium;
                    float f_get_calories_cal = Float.parseFloat(diet_list.get(i).getColories()) * _calories;

                    Sf_get_Protein = Sf_get_Protein + f_get_Protein;
                    Sf_get_Fat = Sf_get_Fat + f_get_Fat;
                    Sf_get_Carbs = Sf_get_Carbs + f_get_Carbs;
                    Sf_get_Sugar = Sf_get_Sugar + f_get_Sugar;
                    Sf_get_fibre = Sf_get_fibre + f_get_fibre;
                    Sf_get_sat_fat = Sf_get_sat_fat + f_get_sat_fat;
                    Sf_get_chole = Sf_get_chole + f_get_chole;
                    Sf_get_sodium = Sf_get_sodium + f_get_sodium;
                    Sf_get_calories_cal = Sf_get_calories_cal + f_get_calories_cal;

                    get_Protein = String.valueOf(Sf_get_Protein/grand_prot);
                    get_Fat = String.valueOf(Sf_get_Fat/grand_fat);
                    get_Carbs = String.valueOf(Sf_get_Carbs/grand_carb);
                    get_Sugar = String.valueOf(Sf_get_Sugar/grand_sugar);
                    get_fibre = String.valueOf(Sf_get_fibre/grand_fibre);
                    get_sat_fat = String.valueOf(Sf_get_sat_fat/grand_SFA);
                    get_chole = String.valueOf(Sf_get_chole/grand_chole);
                    get_sodium = String.valueOf(Sf_get_sodium/grand_sodium);
                    get_calories_cal = String.valueOf(Sf_get_calories_cal/grand_calories);

                    Log.e("f_get_calories_cal", Sf_get_calories_cal + " = " + get_calories_cal);

                }
                else  {


                    String food_type = diet_list.get(i).getFood_type();

                    if (food_type.equals("Breakfast") && !( meal_type.equals("11") || meal_type.equals("12") || meal_type.equals("13"))) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fibre", getApplicationContext());
                    } else if (food_type.equals("Lunch") && !( meal_type.equals("10") || meal_type.equals("12") || meal_type.equals("13"))) {
                        _calories = _calories = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fibre", getApplicationContext());

                    } else if (food_type.equals("Dinner") && !( meal_type.equals("10") || meal_type.equals("11") || meal_type.equals("13"))) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fibre", getApplicationContext());
                    } else if (food_type.equals("Snack") && !( meal_type.equals("10") || meal_type.equals("11") || meal_type.equals("12"))) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fibre", getApplicationContext());
                    }

                        else if(!(food_type.equals("Dinner") || food_type.equals("Breakfast") || food_type.equals("Lunch") || food_type.equals("Snack")))
                        {
                            _calories = 1;
                            _carb = 1;
                            _prot = 1;
                            _fat = 1;
                            _SFA = 1;
                            _chole = 1;
                            _sugar = 1;
                            _sodium = 1;
                            _fibre = 1;
                        }




                    float f_get_Protein = Float.parseFloat(diet_list.get(i).getProtein()) * _prot;
                    float f_get_Fat = Float.parseFloat(diet_list.get(i).getFat()) * _fat;
                    float f_get_Carbs = Float.parseFloat(diet_list.get(i).getCarbs()) * _carb;
                    float f_get_Sugar = Float.parseFloat(diet_list.get(i).getSugar()) * _sugar;
                    float f_get_fibre = Float.parseFloat(diet_list.get(i).getFibre()) * _fibre;
                    float f_get_sat_fat = Float.parseFloat(diet_list.get(i).getSat_fat()) * _SFA;
                    float f_get_chole = Float.parseFloat(diet_list.get(i).getCholestrol()) * _chole;
                    float f_get_sodium = Float.parseFloat(diet_list.get(i).getSodium()) * _sodium;
                    float f_get_calories_cal = Float.parseFloat(diet_list.get(i).getColories()) * _calories;

                    if (food_type.equals("Breakfast")) {
                        breakfast_count++;
                        breakfast_get_Protein += f_get_Protein;
                        breakfast_get_Fat += f_get_Fat;
                        breakfast_get_Carbs += f_get_Carbs;
                        breakfast_get_Sugar += f_get_Sugar;
                        breakfast_get_fibre += f_get_fibre;
                        breakfast_get_sat_fat += f_get_sat_fat;
                        breakfast_get_chole += f_get_chole;
                        breakfast_get_sodium += +f_get_sodium;
                        breakfast_get_calories_cal += f_get_calories_cal;
                    } else if (food_type.equals("Lunch")) {
                        lunch_count++;
                        lunch_get_Protein += f_get_Protein;
                        lunch_get_Fat += f_get_Fat;
                        lunch_get_Carbs += f_get_Carbs;
                        lunch_get_Sugar += f_get_Sugar;
                        lunch_get_fibre += f_get_fibre;
                        lunch_get_sat_fat += f_get_sat_fat;
                        lunch_get_chole += f_get_chole;
                        lunch_get_sodium += f_get_sodium;
                        lunch_get_calories_cal += f_get_calories_cal;
                    } else if (food_type.equals("Dinner")) {
                        dinner_count++;
                        dinner_get_Protein += f_get_Protein;
                        dinner_get_Fat += f_get_Fat;
                        dinner_get_Carbs += f_get_Carbs;
                        dinner_get_Sugar += f_get_Sugar;
                        dinner_get_fibre += f_get_fibre;
                        dinner_get_sat_fat += f_get_sat_fat;
                        dinner_get_chole += f_get_chole;
                        dinner_get_sodium += f_get_sodium;
                        dinner_get_calories_cal += f_get_calories_cal;
                    } else if ((food_type.equals("Snack"))) {
                        snack_count++;
                        snack_get_Protein += f_get_Protein;
                        snack_get_Fat += f_get_Fat;
                        snack_get_Carbs += f_get_Carbs;
                        snack_get_Sugar += f_get_Sugar;
                        snack_get_fibre += f_get_fibre;
                        snack_get_sat_fat += f_get_sat_fat;
                        snack_get_chole += f_get_chole;
                        snack_get_sodium += f_get_sodium;
                        snack_get_calories_cal += f_get_calories_cal;
                    }

                    if (i == diet_list.size() - 1) {
                        if(breakfast_count!=0) {
                            breakfast_get_Protein = breakfast_get_Protein / breakfast_count;
                            breakfast_get_Fat = breakfast_get_Fat / breakfast_count;
                            breakfast_get_Carbs = breakfast_get_Carbs / breakfast_count;
                            breakfast_get_Sugar = breakfast_get_Sugar / breakfast_count;
                            breakfast_get_fibre = breakfast_get_fibre / breakfast_count;
                            breakfast_get_sat_fat = breakfast_get_sat_fat / breakfast_count;
                            breakfast_get_chole = breakfast_get_chole / breakfast_count;
                            breakfast_get_sodium = breakfast_get_sodium / breakfast_count;
                        }



                        if(lunch_count!=0) {
                            lunch_get_Protein = lunch_get_Protein / lunch_count;
                            lunch_get_Fat = lunch_get_Fat / lunch_count;
                            lunch_get_Carbs = lunch_get_Carbs / lunch_count;
                            lunch_get_Sugar = lunch_get_Sugar / lunch_count;
                            lunch_get_fibre = lunch_get_fibre / lunch_count;
                            lunch_get_sat_fat = lunch_get_sat_fat / lunch_count;
                            lunch_get_chole = lunch_get_chole / lunch_count;
                            lunch_get_sodium = lunch_get_sodium / lunch_count;
                        }

                        if(dinner_count!=0) {
                            dinner_get_Protein = dinner_get_Protein / dinner_count;
                            dinner_get_Fat = dinner_get_Fat / dinner_count;
                            dinner_get_Carbs = dinner_get_Carbs / dinner_count;
                            dinner_get_Sugar = dinner_get_Sugar / dinner_count;
                            dinner_get_fibre = dinner_get_fibre / dinner_count;
                            dinner_get_sat_fat = dinner_get_sat_fat / dinner_count;
                            dinner_get_chole = dinner_get_chole / dinner_count;
                            dinner_get_sodium = dinner_get_sodium / dinner_count;
                        }

                        if(snack_count!=0) {
                            snack_get_Protein = snack_get_Protein / snack_count;
                            snack_get_Fat = snack_get_Fat / snack_count;
                            snack_get_Carbs = snack_get_Carbs / snack_count;
                            snack_get_Sugar = snack_get_Sugar / snack_count;
                            snack_get_fibre = snack_get_fibre / snack_count;
                            snack_get_sat_fat = snack_get_sat_fat / snack_count;
                            snack_get_chole = snack_get_chole / snack_count;
                            snack_get_sodium = snack_get_sodium / snack_count;
                        }


                        Sf_get_Protein = breakfast_get_Protein + lunch_get_Protein + dinner_get_Protein + snack_get_Protein;
                        Sf_get_Fat = breakfast_get_Fat + lunch_get_Fat + dinner_get_Fat + snack_get_Fat;
                        Sf_get_Carbs = breakfast_get_Carbs + lunch_get_Fat + dinner_get_Carbs + snack_get_Carbs;
                        Sf_get_Sugar = breakfast_get_Sugar + lunch_get_Sugar + dinner_get_Fat + snack_get_Sugar;
                        Sf_get_fibre = breakfast_get_fibre + lunch_get_fibre + dinner_get_fibre + snack_get_fibre;
                        Sf_get_sat_fat = breakfast_get_sat_fat + lunch_get_sat_fat + dinner_get_sat_fat + snack_get_sat_fat;
                        Sf_get_chole = breakfast_get_chole + lunch_get_chole + dinner_get_chole + snack_get_chole;
                        Sf_get_sodium = breakfast_get_sodium + lunch_get_sodium + dinner_get_sodium + snack_get_sodium;
                        Sf_get_calories_cal = breakfast_get_calories_cal + lunch_get_calories_cal + dinner_get_calories_cal + snack_get_calories_cal;

                        get_Protein = String.valueOf(Sf_get_Protein/grand_prot);
                        get_Fat = String.valueOf(Sf_get_Fat/grand_fat);
                        get_Carbs = String.valueOf(Sf_get_Carbs/grand_carb);
                        get_Sugar = String.valueOf(Sf_get_Sugar/grand_sugar);
                        get_fibre = String.valueOf(Sf_get_fibre/grand_fibre);
                        get_sat_fat = String.valueOf(Sf_get_sat_fat/grand_SFA);
                        get_chole = String.valueOf(Sf_get_chole/grand_chole);
                        get_sodium = String.valueOf(Sf_get_sodium/grand_sodium);
                        get_calories_cal = String.valueOf(Sf_get_calories_cal/grand_calories);
                        Log.e("f_get_calories_cal", Sf_get_calories_cal + " = " + get_calories_cal);
                    }
                }
            }


            if (get_Protein.equals("0.0")) {

                get_Protein = "0";

            } else {

                float pro = Math.round(Float.parseFloat(get_Protein)*100.0f);
                get_Protein = pro + "";

            }

            if (get_Fat.equals("0.0")) {

                get_Fat = "0";

            } else {
                float pro = Math.round(Float.parseFloat(get_Fat)*100.0f);
                get_Fat = pro + "";
            }

            if (get_Carbs.equals("0.0")) {
                get_Carbs = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Carbs)*100.0f);
                get_Carbs = pro + "";
            }


            if (get_Sugar.equals("0.0")) {
                get_Sugar = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Sugar)*100.0f);
                get_Sugar = pro + "";
            }


            if (get_fibre.equals("0.0")) {
                get_fibre = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_fibre)*100.0f);
                get_fibre = pro + "";
            }


            if (get_sat_fat.equals("0.0")) {
                get_sat_fat = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_sat_fat)*100.0f);
                get_sat_fat = pro + "";
            }

            if (get_chole.equals("0.0")) {
                get_chole = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_chole)*100.0f);
                get_chole = pro + "";
            }


            if (get_sodium.equals("0.0")) {
                get_sodium = "0";
            } else {

                float pro = Math.round(Float.parseFloat(get_sodium)*100.0f);
                get_sodium = pro + "";
            }

            if (get_calories_cal.equals("0.0")) {
                get_calories_cal = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_calories_cal)*100.0f);
                get_calories_cal = pro + "";
            }

            piechart(valu(get_calories_cal), valu(get_Carbs), valu(get_Protein), valu(get_Fat), valu(get_fibre), valu(get_Sugar), valu(get_sat_fat), valu(get_sodium), valu(get_chole));
        } catch (Exception e) {
            Log.e("errr", e + "");
        }
    }

    void getCircleDiet(int filter)
    {

        get_Protein = "0";
        get_Fat = "0";
        get_Carbs = "0";
        get_Sugar = "0";
        get_fibre = "0";
        get_sat_fat = "0";
        get_chole = "0";
        get_sodium = "0";
        get_calories_cal = "0";

        diet_list = new ProductController_For_All(HomeActivity.this).getFoodData(user_id, _select_dite_type, "", "");
        //   Log.e("diet_list", diet_list.get(0).getFoodname());
        name_selected.setText("");
        per.setText("All");

        float Sf_get_Protein = 0.0f;
        float Sf_get_Fat = 0.0f;
        float Sf_get_Carbs = 0.0f;
        float Sf_get_Sugar = 0.0f;
        float Sf_get_fibre = 0.0f;
        float Sf_get_sat_fat = 0.0f;
        float Sf_get_chole = 0.0f;
        float Sf_get_sodium = 0.0f;
        float Sf_get_calories_cal = 0.0f;

        try {

            for (int i = 0; i < diet_list.size(); i++) {

                float f_get_Protein = Float.parseFloat(diet_list.get(i).getProtein());
                float f_get_Fat = Float.parseFloat(diet_list.get(i).getFat());
                float f_get_Carbs = Float.parseFloat(diet_list.get(i).getCarbs());
                float f_get_Sugar = Float.parseFloat(diet_list.get(i).getSugar());
                float f_get_fibre = Float.parseFloat(diet_list.get(i).getFibre());
                float f_get_sat_fat = Float.parseFloat(diet_list.get(i).getSat_fat());
                float f_get_chole = Float.parseFloat(diet_list.get(i).getCholestrol());
                float f_get_sodium = Float.parseFloat(diet_list.get(i).getSodium());
                float f_get_calories_cal = Float.parseFloat(diet_list.get(i).getColories());

                Sf_get_Protein = Sf_get_Protein + f_get_Protein;
                Sf_get_Fat = Sf_get_Fat + f_get_Fat;
                Sf_get_Carbs = Sf_get_Carbs + f_get_Carbs;
                Sf_get_Sugar = Sf_get_Sugar + f_get_Sugar;
                Sf_get_fibre = Sf_get_fibre + f_get_fibre;
                Sf_get_sat_fat = Sf_get_sat_fat + f_get_sat_fat;
                Sf_get_chole = Sf_get_chole + f_get_chole;
                Sf_get_sodium = Sf_get_sodium + f_get_sodium;
                Sf_get_calories_cal = Sf_get_calories_cal + f_get_calories_cal;

                get_Protein = String.valueOf(Sf_get_Protein);
                get_Fat = String.valueOf(Sf_get_Fat);
                get_Carbs = String.valueOf(Sf_get_Carbs);
                get_Sugar = String.valueOf(Sf_get_Sugar);
                get_fibre = String.valueOf(Sf_get_fibre);
                get_sat_fat = String.valueOf(Sf_get_sat_fat);
                get_chole = String.valueOf(Sf_get_chole);
                get_sodium = String.valueOf(Sf_get_sodium);
                get_calories_cal = String.valueOf(Sf_get_calories_cal);

                Log.e("f_get_calories_cal", Sf_get_calories_cal + " = " + get_calories_cal);

            }

            if (get_Protein.equals("0.0")) {

                get_Protein = "0";

            } else {

                float pro = Math.round(Float.parseFloat(get_Protein));
                get_Protein = pro + "";

            }

            if (get_Fat.equals("0.0")) {

                get_Fat = "0";

            } else {
                float pro = Math.round(Float.parseFloat(get_Fat));
                get_Fat = pro + "";
            }

            if (get_Carbs.equals("0.0")) {
                get_Carbs = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Carbs));
                get_Carbs = pro + "";
            }


            if (get_Sugar.equals("0.0")) {
                get_Sugar = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Sugar));
                get_Sugar = pro + "";
            }


            if (get_fibre.equals("0.0")) {
                get_fibre = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_fibre));
                get_fibre = pro + "";
            }


            if (get_sat_fat.equals("0.0")) {
                get_sat_fat = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_sat_fat));
                get_sat_fat = pro + "";
            }

            if (get_chole.equals("0.0")) {
                get_chole = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_chole));
                get_chole = pro + "";
            }


            if (get_sodium.equals("0.0")) {
                get_sodium = "0";
            } else {

                float pro = Math.round(Float.parseFloat(get_sodium));
                get_sodium = pro + "";
            }

            if (get_calories_cal.equals("0.0")) {
                get_calories_cal = "0";
            } else {
                float pro = Math.round(Float.parseFloat(get_calories_cal));
                get_calories_cal = pro + "";
            }

            piechart(valu(get_calories_cal), valu(get_Carbs), valu(get_Protein), valu(get_Fat), valu(get_fibre), valu(get_Sugar), valu(get_sat_fat), valu(get_sodium), valu(get_chole));
        } catch (Exception e) {
            Log.e("errr", e + "");
        }
    }
    void getCircleAlcho(String _select_dite_type) throws Exception {

        alcho_consume_size.setText("Consumed drinks 0");
        get_Protein = "0";
        get_Fat = "0";
        get_Carbs = "0";
        get_Sugar = "0";
        get_fibre = "0";
        get_sat_fat = "0";
        get_chole = "0";
        get_sodium = "0";
        get_calories_cal = "0";

        alcho_list = new ProductController_For_All(HomeActivity.this).getMyAlcho(user_id, _select_dite_type, "", "");
        //    Log.e("alcho_list", alcho_list.get(0).getCarbs());
        name_selected_alcho.setText("");
        per_alcho.setText("All");

        float Sf_get_Carbs = 0.0f;
        float Sf_get_Sugar = 0.0f;
        float Sf_get_sodium = 0.0f;
        float Sf_get_calories_cal = 0.0f;
        float Sf_get_sat_size = 0.0f;

        try {
            for (int i = 0; i < alcho_list.size(); i++) {

                float f_get_calories = Float.parseFloat(alcho_list.get(i).getCalories());
                float f_get_Carbs = Float.parseFloat(alcho_list.get(i).getCarbs());
                float f_get_Sugar = Float.parseFloat(alcho_list.get(i).getSugar());
                float f_get_sodium = Float.parseFloat(alcho_list.get(i).getSodium());
                float f_get_sat_size = Float.parseFloat(alcho_list.get(i).getStd_size_drinks());

                Sf_get_Carbs = Sf_get_Carbs + f_get_Carbs;
                Sf_get_Sugar = Sf_get_Sugar + f_get_Sugar;
                Sf_get_sodium = Sf_get_sodium + f_get_sodium;
                Sf_get_calories_cal = Sf_get_calories_cal + f_get_calories;
                Sf_get_sat_size = Sf_get_sat_size + f_get_sat_size;

                get_Carbs = String.valueOf(Sf_get_Carbs);
                get_Sugar = String.valueOf(Sf_get_Sugar);
                get_sodium = String.valueOf(Sf_get_sodium);
                get_calories_cal = String.valueOf(Sf_get_calories_cal);
                get_get_sat_size = String.valueOf(Sf_get_sat_size);

                Log.e("f_get_calories_cal", Sf_get_Carbs + " = " + get_Carbs);
            }

            if (get_Carbs.equals("0.0")) {
                get_Carbs = "0.0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Carbs));
                get_Carbs = pro + "";
            }

            if (get_Sugar.equals("0.0")) {
                get_Sugar = "0.0";
            } else {
                float pro = Math.round(Float.parseFloat(get_Sugar));
                get_Sugar = pro + "";
            }

            if (get_sodium.equals("0.0")) {
                get_sodium = "0.0";
            } else {

                float pro = Math.round(Float.parseFloat(get_sodium));
                get_sodium = pro + "";
            }

            if (get_calories_cal.equals("0.0")) {
                get_calories_cal = "0.0";
            } else {
                float pro = Math.round(Float.parseFloat(get_calories_cal));
                get_calories_cal = pro + "";
            }

            if (get_get_sat_size.equals("0.0")) {
                get_get_sat_size = "0.0";
            } else {
                float pro = Math.round(Float.parseFloat(get_get_sat_size));
                get_get_sat_size = pro + "";
            }

            alcho_consume_size.setText("Consumed drinks " + get_get_sat_size.replaceAll(".0", "") + "");

            Log.e("get_Carbs", get_Carbs + "");
            Log.e("get_Sugar", get_Sugar + "");
            Log.e("get_sodium", get_sodium + "");
            Log.e("get_calories_cal", get_calories_cal + "");
            Log.e("get_get_sat_size", get_get_sat_size + "");

            piechartAlch(valu(get_calories_cal), valu(get_Carbs), valu(get_Sugar), valu(get_sodium), valu(get_get_sat_size));
        } catch (Exception e) {
            Log.e("errr", e + "");
        }
    }

    int valu(String vl) {
        int gg = 0;
        try {
            String[] aa = vl.split("\\.");
            String aaa = aa[0];

            gg = Integer.parseInt(aaa);
        } catch (Exception e) {
            Log.e("ERRROROR", e + "");
        }

        return gg;
    }

    void piechartAlch(int one, int two, int three, int four, int five) {

        ProgressBar mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar_alcho);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, one);
        anim.setDuration(2500);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        ProgressBar mprogressBar2 = (ProgressBar) findViewById(R.id.circular_progress_bar2_alcho);
        ObjectAnimator anim2 = ObjectAnimator.ofInt(mprogressBar2, "progress", 0, two);
        anim2.setDuration(2500);
        anim2.setInterpolator(new DecelerateInterpolator());
        anim2.start();

        ProgressBar mprogressBar3 = (ProgressBar) findViewById(R.id.circular_progress_bar3_alcho);
        ObjectAnimator anim3 = ObjectAnimator.ofInt(mprogressBar3, "progress", 0, three);
        anim3.setDuration(2500);
        anim3.setInterpolator(new DecelerateInterpolator());
        anim3.start();

        ProgressBar mprogressBar4 = (ProgressBar) findViewById(R.id.circular_progress_bar4_alcho);
        ObjectAnimator anim4 = ObjectAnimator.ofInt(mprogressBar4, "progress", 0, four);
        anim4.setDuration(2500);
        anim4.setInterpolator(new DecelerateInterpolator());
        anim4.start();

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        String gender = sp.getString("gender", "");

        ProgressBar mprogressBar5 = (ProgressBar) findViewById(R.id.circular_progress_bar5_alcho);
        if (gender.equals("female")) {
            mprogressBar5.setMax(11);
        } else {
            mprogressBar5.setMax(14);
        }
        ObjectAnimator anim5 = ObjectAnimator.ofInt(mprogressBar5, "progress", 0, five);
        anim5.setDuration(2500);
        anim5.setInterpolator(new DecelerateInterpolator());

        anim5.start();

    }


    void piechart(int one, int two, int three, int four, int five, int six, int seven, int eaight, int nine) {

        Log.e("one", one + "");
        Log.e("two", two + "");
        Log.e("three", three + "");
        Log.e("four", four + "");
        Log.e("five", five + "");
        Log.e("six", six + "");
        Log.e("seven", seven + "");
        Log.e("eaight", eaight + "");
        Log.e("nine", nine + "");

        ProgressBar mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, one);
        anim.setDuration(2500);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        ProgressBar mprogressBar2 = (ProgressBar) findViewById(R.id.circular_progress_bar2);
        ObjectAnimator anim2 = ObjectAnimator.ofInt(mprogressBar2, "progress", 0, two);
        anim2.setDuration(2500);
        anim2.setInterpolator(new DecelerateInterpolator());
        anim2.start();

        ProgressBar mprogressBar3 = (ProgressBar) findViewById(R.id.circular_progress_bar3);
        ObjectAnimator anim3 = ObjectAnimator.ofInt(mprogressBar3, "progress", 0, three);
        anim3.setDuration(2500);
        anim3.setInterpolator(new DecelerateInterpolator());
        anim3.start();

        ProgressBar mprogressBar4 = (ProgressBar) findViewById(R.id.circular_progress_bar4);
        ObjectAnimator anim4 = ObjectAnimator.ofInt(mprogressBar4, "progress", 0, four);
        anim4.setDuration(2500);
        anim4.setInterpolator(new DecelerateInterpolator());
        anim4.start();

        ProgressBar mprogressBar5 = (ProgressBar) findViewById(R.id.circular_progress_bar5);
        ObjectAnimator anim5 = ObjectAnimator.ofInt(mprogressBar5, "progress", 0, five);
        anim5.setDuration(2500);
        anim5.setInterpolator(new DecelerateInterpolator());
        anim5.start();

        ProgressBar mprogressBar6 = (ProgressBar) findViewById(R.id.circular_progress_bar6);
        ObjectAnimator anim6 = ObjectAnimator.ofInt(mprogressBar6, "progress", 0, six);
        anim6.setDuration(2500);
        anim6.setInterpolator(new DecelerateInterpolator());
        anim6.start();

        ProgressBar mprogressBar7 = (ProgressBar) findViewById(R.id.circular_progress_bar7);
        ObjectAnimator anim7 = ObjectAnimator.ofInt(mprogressBar7, "progress", 0, seven);
        anim7.setDuration(2500);
        anim7.setInterpolator(new DecelerateInterpolator());
        anim7.start();


        ProgressBar mprogressBar8 = (ProgressBar) findViewById(R.id.circular_progress_bar8);
        ObjectAnimator anim8 = ObjectAnimator.ofInt(mprogressBar8, "progress", 0, eaight);
        anim8.setDuration(2500);
        anim8.setInterpolator(new DecelerateInterpolator());
        anim8.start();

        ProgressBar mprogressBar9 = (ProgressBar) findViewById(R.id.circular_progress_bar9);
        ObjectAnimator anim9 = ObjectAnimator.ofInt(mprogressBar9, "progress", 0, nine);
        anim9.setDuration(2500);
        anim9.setInterpolator(new DecelerateInterpolator());
        anim9.start();

    }

    public class CustomPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CustomPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            ViewpagerModel vb = vlist.get(position);
            LayoutInflater inflater = getLayoutInflater();
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.viewpager_layout, null);
            TextView vtext = (TextView) layout.findViewById(R.id.vtext);
            CardView card = (CardView) layout.findViewById(R.id.card);

            vtext.setText(vb.getName());
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vp_pos == 0) {
                        new MyIntent(HomeActivity.this, BMI_AND_WEIGHT_LOSS_CALCULATOR.class, R.anim.enter, R.anim.exit);
                    } else if (vp_pos == 1) {
                        new MyIntent(HomeActivity.this, IDEAL_DIET_PLAN.class, R.anim.enter, R.anim.exit);
                    } else if (vp_pos == 2) {
                        new MyIntent(HomeActivity.this, ANALYSIS_AND_ADVICE_1.class, R.anim.enter, R.anim.exit);
                    } else if (vp_pos == 3) {
                        new MyIntent(HomeActivity.this, RISK_CALCULATOR_1.class, R.anim.enter, R.anim.exit);
                    } else if (vp_pos == 4) {
                        new MyIntent(HomeActivity.this, FAMILY_OIL_CALCULATOR.class, R.anim.enter, R.anim.exit);
                    }
                }
            });
            collection.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return vlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }


    @Override
    public void onClick(View view) {
        if (view == home_L) {
        }
        if (view == profile_L) {
            new MyIntent(HomeActivity.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == log_L) {
            new MyIntent(HomeActivity.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == plans_L) {
            new MyIntent(HomeActivity.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(HomeActivity.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == arrow_left) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
        }
        if (view == arrow_right) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        }
        if (view == Aerobic) {
            new MyIntent(HomeActivity.this, Aerobic_result_page.class, R.anim.enter, R.anim.exit);
        }
        if (view == L_All) {
            try {
                getCircleEat_Out(_select_dite_type,"0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (view == Resistance) {
            new MyIntent(HomeActivity.this, Resistance_result_page.class, R.anim.enter, R.anim.exit);
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
            per.setText(valu(get_sodium) + "%");
            piechart(0, 0, 0, 0, 0, 0, 0, valu(get_sodium), 0);

        }
        if (view == L_CHOLE) {
            name_selected.setText("CHOLE");
            per.setText(valu(get_chole) + "%");
            piechart(0, 0, 0, 0, 0, 0, 0, 0, valu(get_chole));
        }


        if (view == L_All_alcho) {
            try {
                getCircleAlcho(_select_alcho_type);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (view == L_carbs_alcho) {
            name_selected_alcho.setText("CARBS");
            per_alcho.setText(valu(get_Carbs) + "");
            piechartAlch(0, valu(get_Carbs), 0, 0, 0);
        }
        if (view == L_sugar_alcho) {
            name_selected_alcho.setText("SUGAR");
            per_alcho.setText(valu(get_Sugar) + "");
            piechartAlch(0, 0, valu(get_Sugar), 0, 0);
        }
        if (view == L_SODIUM_alcho) {
            name_selected_alcho.setText("SODIUM");
            per_alcho.setText(valu(get_sodium) + "");
            piechartAlch(0, 0, 0, valu(get_sodium), 0);
        }
        if (view == L_calories_alcho) {

            name_selected_alcho.setText("Calories");
            per_alcho.setText(valu(get_calories_cal) + "");
            piechartAlch(valu(get_calories_cal), 0, 0, 0, 0);
        }
        if (view == L_sat_size) {
            name_selected_alcho.setText("SAT SIZE");
            per_alcho.setText(valu(get_get_sat_size) + "");
            piechartAlch(0, 0, 0, 0, valu(get_get_sat_size));
        }

    }


    class Task extends AsyncTask<String, Void, String> {

        String result, days;
        ProgressDialog pd;

        Task(String days) {
            this.days = days;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(HomeActivity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";
            try {
                URL url = new URL( BaseUrl.get_weight_log);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", BaseUrl.AccessToken);
                postDataParams.put("user_id", user_id);
                postDataParams.put("day", days);
                Log.e("params", params + "");
                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                } else {
                    result = "";
                }
            } catch (Exception e) {
                Log.e("ERRR", e + "");
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("Task api = ", result + "");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");

                if (status.equals("1")) {
                    yVals.clear();
                    JSONArray arr = jobj.getJSONArray("data");


                    for (int i = 0; i < arr.length(); i++) {

                        try {
                            yVals.add(new Entry(i, Float.parseFloat(arr.getJSONObject(i).getString("Weight_in_kg"))));

                            //    Log.e("leangth ",arr.length()+"");
                            if (i == arr.length() - 1) {
                                setData(arr.getJSONObject(0).getString("Date") + " <-> " + arr.getJSONObject(i).getString("Date"));
                            }
                        } catch (Exception e) {

                        }

                    }
                } else {
                    yVals.clear();

                    try {
                        yVals.add(new Entry(0, 0.0f));

                        setData("No log for today");

                    } catch (Exception e) {

                    }

                    //     Toast.makeText(ctx, message + "", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
            }
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


    private void setData(String date) {
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.parseColor("#F2F2F2"));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        //  leftAxis.setAxisMaximum(100f);
        //  leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTextColor(Color.RED);
        //   rightAxis.setAxisMaximum(100);
        //   rightAxis.setAxisMinimum(0);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);


        LineDataSet set1;

        set1 = new LineDataSet(yVals, date);

        //  set1.setFillAlpha(110);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(Color.parseColor("#EC7063"));
        set1.setCircleColor(Color.parseColor("#B03A2E"));
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(true);
        set1.setValueTextSize(9f);
        //   set1.setHighLightColor(Color.rgb(244, 117, 117));
        // set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        mChart.setData(data);

        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

        mChart.invalidate();
    }
}
