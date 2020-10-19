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
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.Excercize_plans.CalorieCalculator;
import com.hwi.health.Activitys.Plans.Excercize_plans.NonScrollListView;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.Models.DietLog_Models;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.sqlite_database.ProductController_For_All;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class See_Past_Diet_Log extends AppCompatActivity implements View.OnClickListener {
    ArrayList<DietLog_Models> diet_list = new ArrayList<>();
    NonScrollListView list;
    String Diet_log_key;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    RelativeLayout result_L;
    String get_calories_cal;
    String getColories,get_Energy,get_Protein,get_Fat,get_Carbs,get_Sugar,get_fibre,get_sat_fat,key_food,get_sodium,get_cholestrol;
    LinearLayout  L_protine, L_carbs, L_sugar, L_fat, L_SODIUM, L_sat, L_CHOLE,L_All,L_FIBER,L_calories;
    TextView per,name_selected;



    void getCircle(){

        name_selected.setText("");
    //    per.setText("All");
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
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see__past__diet__log);
        list = (NonScrollListView) findViewById(R.id.list);
        TextView tt = findViewById(R.id.no_data);

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


        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        String user_id = sp.getString("Userid", "");

        SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
        String strDate = sharedPreferences.getString("strDate", "");
        String strDate2 = sharedPreferences.getString("strDate2", "");
        String endDate = sharedPreferences.getString("endDate", "");
        String endDate2 = sharedPreferences.getString("endDate2", "");
        final String select = sharedPreferences.getString("select", "5");
        int insertedelement = sharedPreferences.getInt("insertedelement", 1);

        Log.e("strDate", strDate);

        try {
            diet_list = new ProductController_For_All(See_Past_Diet_Log.this).getFoodDatadietlog(user_id, select, strDate, endDate,insertedelement);

            if (diet_list.isEmpty()) {
                tt.setVisibility(View.VISIBLE);
                tt.setText("No data found between " + strDate2 + " to " + endDate2);
            } else {
                tt.setVisibility(View.GONE);
                MyAdap myAdap = new MyAdap();
                list.setAdapter(myAdap);
                if(select.equals("5"))
                {
                   result_L = (RelativeLayout) findViewById(R.id.diet_log_layout_circle_same_view1);
                   result_L.setVisibility(View.VISIBLE);
                    DietLog_Models dm = diet_list.get(0);
                    text.setText(dm.getFood_type());
                    if(dm.getFood_type().equals("Alcohol")!=true) {
                        new AllSharedPrefrences(See_Past_Diet_Log.this).Diet_log_result(dm.getColories(),
                                dm.getEnergy(), dm.getCarbs(), dm.getSugar(), dm.getProtein(), dm.getFat(), dm.getFibre(), dm.getSat_fat(), dm.getSodium(), dm.getCholestrol(), Diet_log_key);
                       if(diet_list.size() == 1)
                       getCircle();
                    }
                    else
                    {
                        Toast.makeText(See_Past_Diet_Log.this, "Cant see comparison for food type Alcohol", Toast.LENGTH_SHORT).show();
                    }



                }
            }
        } catch (Exception e) {
            Log.e("diet_list error", e + "%");
        }

        SharedPreferences sp2 = getSharedPreferences("Diet_log_key", MODE_PRIVATE);

        Diet_log_key = sp2.getString("Diet_log_key", "");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DietLog_Models dm = diet_list.get(position);


                if(dm.getFood_type().equals("Alcohol")!=true ) {
                    new AllSharedPrefrences(See_Past_Diet_Log.this).Diet_log_result(dm.getColories(),
                            dm.getEnergy(), dm.getCarbs(), dm.getSugar(), dm.getProtein(), dm.getFat(), dm.getFibre(), dm.getSat_fat(), dm.getSodium(), dm.getCholestrol(), Diet_log_key);
                    new MyIntent(See_Past_Diet_Log.this, DietLog_Result.class, R.anim.enter, R.anim.exit);
                }


            }
        });

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

//        new AllSharedPrefrences(DietLog_Details.this).Diet_log_result(total_Energy, total_Carbs, total_Sugar, total_Protein, total_Fat, total_fibre, total_sat_fat,total_souium,total_cholestrol, Diet_log_key);
//        new MyIntent(See_Past_Diet_Log.this, DietLog_Result.class, R.anim.enter, R.anim.exit);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), DietLog_activity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(See_Past_Diet_Log.this, DietLog_activity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(See_Past_Diet_Log.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == profile_L) {
            new MyIntent(See_Past_Diet_Log.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == log_L) {
            new MyIntent(See_Past_Diet_Log.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == plans_L) {
            new MyIntent(See_Past_Diet_Log.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(See_Past_Diet_Log.this, MoreActivity.class, R.anim.enter, R.anim.exit);
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
            View v = getLayoutInflater().inflate(R.layout.diet_past_log, null);
            TextView name = (TextView) v.findViewById(R.id.name);
            TextView food_type = (TextView) v.findViewById(R.id.food_type);
            TextView date = (TextView) v.findViewById(R.id.date);
            ImageView food_type_image = (ImageView) v.findViewById(R.id.food_type_image);

            L_calories = (TextView) v.findViewById(R.id.calories);
            L_protine = (TextView) v.findViewById(R.id.PROTINE);
            L_carbs = (TextView) v.findViewById(R.id.CARBS);
            L_sugar = (TextView) v.findViewById(R.id.SUGAR);
            L_fat = (TextView) v.findViewById(R.id.FAT);
            L_sat = (TextView) v.findViewById(R.id.SAT_FAT);
            L_CHOLE = (TextView) v.findViewById(R.id.CHOLE);
            L_SODIUM = (TextView) v.findViewById(R.id.SODIUM);
            L_FIBER = (TextView) v.findViewById(R.id.FIBER);

            DietLog_Models fm = diet_list.get(position);



            name.setText(fm.getFoodname());

            float _calories = 0.0f;
            float _carb = 0.0f;
            float _prot = 0.0f;
            float _fat = 0.0f;
            float _SFA = 0.0f;
            float _chole = 0.0f;
            float _sugar = 0.0f;
            float _sodium = 0.0f;
            float _fibre = 0.0f;

            if (fm.getFood_type().equals("Breakfast")) {
                _calories = CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","Calories",getApplicationContext());
                _carb =     CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","carb",getApplicationContext());
                _prot =     CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","prot",getApplicationContext());
                _fat =      CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","fat",getApplicationContext());
                _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","SFA",getApplicationContext());
                _chole =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","chole",getApplicationContext());
                _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","sugar",getApplicationContext());
                _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","sodium",getApplicationContext());
                _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","fibre",getApplicationContext());
            } else if (fm.getFood_type().equals("Lunch")) {
                _calories = _calories = CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","Calories",getApplicationContext());
                _carb =     CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","carb",getApplicationContext());
                _prot =     CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","prot",getApplicationContext());
                _fat =      CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","fat",getApplicationContext());
                _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","SFA",getApplicationContext());
                _chole =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","chole",getApplicationContext());
                _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","sugar",getApplicationContext());
                _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","sodium",getApplicationContext());
                _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","fibre",getApplicationContext());

            } else if (fm.getFood_type().equals("Dinner")) {
                _calories = CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","Calories",getApplicationContext());
                _carb =     CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","carb",getApplicationContext());
                _prot =     CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","prot",getApplicationContext());
                _fat =      CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","fat",getApplicationContext());
                _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","SFA",getApplicationContext());
                _chole =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","chole",getApplicationContext());
                _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","sugar",getApplicationContext());
                _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","sodium",getApplicationContext());
                _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","fibre",getApplicationContext());
            } else if (fm.getFood_type().equals("Snacks")) {
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




            L_calories.setText(String.valueOf(Math.round(Float.parseFloat(fm.getColories())*_calories)));
            L_protine.setText(String.valueOf(Math.round(Float.parseFloat(fm.getProtein())*_prot)));
            L_carbs.setText(String.valueOf(Math.round(Float.parseFloat(fm.getCarbs())*_carb)));
            L_sugar.setText(String.valueOf(Math.round(Float.parseFloat(fm.getSugar())*_sugar)));
            L_fat.setText(String.valueOf(Math.round(Float.parseFloat(fm.getFat())*_fat)));
            L_sat.setText(String.valueOf(Math.round(Float.parseFloat(fm.getSat_fat())*_SFA)));
            L_CHOLE.setText(String.valueOf(Math.round(Float.parseFloat(fm.getCholestrol())*_chole)));
            L_SODIUM.setText(String.valueOf(Math.round(Float.parseFloat(fm.getSodium())*_sodium)));
            L_FIBER.setText(String.valueOf(Math.round(Float.parseFloat(fm.getFibre())*_fibre)));
            food_type.setText(fm.getFood_type() + "");

            if (fm.getFood_type().equals("Lunch")) {
                food_type_image.setImageResource(R.drawable.lunch);
            } else if (fm.getFood_type().equals("Breakfast")) {
                food_type_image.setImageResource(R.drawable.breakfast);
            } else if (fm.getFood_type().equals("Dinner")) {
                food_type_image.setImageResource(R.drawable.dinner);
            } else if (fm.getFood_type().equals("Snack")) {
                food_type_image.setImageResource(R.drawable.snack);
            } else if (fm.getFood_type().equals("Drink")) {
                food_type_image.setImageResource(R.drawable.drink);
            } else if (fm.getFood_type().equals("Alcohol")) {
                food_type_image.setImageResource(R.drawable.alchol);
            }



            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format2 = new SimpleDateFormat("E, dd MMM, yyyy");
                Date dd = format.parse(fm.getDate());
                String ddd = format2.format(dd);
                date.setText(ddd);
            } catch (Exception e) {

            }


//            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            Date date = dt.parse(date_s);




            return v;
        }
    }
}
