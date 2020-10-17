package com.hwi.health.Activitys.AllTests.IdealDiat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.hwi.health.Activitys.Plans.Excercize_plans.CalorieCalculator;
import com.hwi.health.Adapters.RecyclerViewAdapter2;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.RecycleModel;
import com.hwi.health.Models.customized_model;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.AllTests.CUSTOMISED_DIET_PLAN.Customised_Diet_Plan_result;
import com.hwi.health.Activitys.AllTests.CUSTOMISED_DIET_PLAN.Major_Meals_Page1;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.sqlite_database.ProductController_For_All;
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
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by PAWAN on 04-08-2017.
 */

public class Ideal_Diat_plan_Child_result2 extends AppCompatActivity implements View.OnClickListener, BaseUrl{
    TextView grains_text, dals_text, eggs_text,fish_text,soya_text,nuts_text,milk_text,veg_text,fruits_text,oils_text,min_soya_text;
    TextView grains_bas, dals_bas, eggs_bas,fish_bas,soya_bas,nuts_bas,milk_bas,veg_bas,fruits_bas,oils_bas,min_soya_bas;
    ImageView grains_icon, dals_icon, eggs_icon,fish_icon,soya_icon,nuts_icon,milk_icon,veg_icon,fruits_icon,oils_icon,min_soya_icon;
   // TextView bmi, weight_category, Activity, RDCA;
    ActionBar ab;
    Spinner spin;
    String feet = "0", inch = "0", S_dob, strDate, get_dob, tar_weight, weight_loss, gender, height, weight, age, user_id;
    ArrayAdapter<String> adapter;
    ArrayList<String> spin_list = new ArrayList<>();
    String name, base,fwi_tips,exercise,value = "1",result,randnoo;
    private ArrayList<RecycleModel> aListModel = new ArrayList<RecycleModel>();
    RecyclerView recycle;
    ImageView close;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    RelativeLayout hint;
    Button yes,no,get_cot;
    ArrayList<customized_model> Morningsnack_list = new ArrayList<>();
    ArrayList<customized_model> Dinner_list = new ArrayList<>();
    ArrayList<customized_model> snack_list = new ArrayList<>();
    ArrayList<customized_model> Lunch_list = new ArrayList<>();
    ArrayList<customized_model> datalist = new ArrayList<>();
    ProductController_For_All controller = new ProductController_For_All(Ideal_Diat_plan_Child_result2.this);
    String key_veg_non,key_activity,key_occcp,key_gender,tar_weight_loss,cust_key;
    String S_Exercise, S_veg_non, S_want, S_Occupation, S_is_Pregnant, S_is_Breast_Feeding, S_first_day_last_mensrual_period, S_pre_pregnancy_weight, S_child_age;
    int number;
    ProgressDialog pd;
    String major_meals,Breakfast,LunchDinner,rice_type,non_veg,chicken_without_skin,fish,beef,goat_meat,lamb,
            pork, milk_type,food_oil,category_one,category_two,category_three,category_four,category_five,cereals,break_milk,vegetables;

    ArrayList<RecycleModel> cleanList(ArrayList<RecycleModel> fm,String key) {
        ArrayList<RecycleModel> temp = new ArrayList<RecycleModel>();


        for (int i = 0; i < fm.size(); i++) {

            if(key.equals("1")==true) {
                if (key.equals(value) == true && fm.get(i).getThree().equals("0") != true) {
                    temp.add(fm.get(i));
                }
            }
            if(key.equals("2")==true) {
                if (key.equals(value) == true && fm.get(i).getFour().equals("0") != true) {
                    temp.add(fm.get(i));
                }
            }
            if(key.equals("3")==true) {
                if (key.equals(value) == true && fm.get(i).getFive().equals("0") != true) {
                    temp.add(fm.get(i));
                }
            }
        }
        return temp;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      /*  View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();*/

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("ILY DIET PLAN RESULT");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal__diat_plan__child_result2);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        find_id();

        spin_list.add("Basic Diet");
        spin_list.add("Optimized Diet");
        spin_list.add("Ideal Diet for people with health risks");
        adapter = new ArrayAdapter<String>(Ideal_Diat_plan_Child_result2.this, android.R.layout.simple_spinner_dropdown_item,spin_list);
        spin.setAdapter(adapter);

        String ActivityLevel = getIntent().getStringExtra("ActivityLevel");
        String RDCAS = getIntent().getStringExtra("RDCA");
        String bmiS = getIntent().getStringExtra("bmi");
        String Category = getIntent().getStringExtra("Category");
        aListModel = (ArrayList<RecycleModel>) getIntent().getSerializableExtra("data");
       /* RDCA.setText(RDCAS);
        Activity.setText(ActivityLevel);
        bmi.setText(bmiS);
        weight_category.setText(Category);*/

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        get_cot.setOnClickListener(this);
        close.setOnClickListener(this);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exercise = spin_list.get(position);
                if (exercise.equals("Basic Diet")){
                    value = "1";
                    RecyclerViewAdapter2 rv = new RecyclerViewAdapter2(cleanList(aListModel,value),Ideal_Diat_plan_Child_result2.this,value,Ideal_Diat_plan_Child_result2.this);
                    GridLayoutManager lLayout = new GridLayoutManager(Ideal_Diat_plan_Child_result2.this, 2);
                    recycle.setLayoutManager(lLayout);
                    recycle.setItemAnimator(new DefaultItemAnimator());
                    recycle.setAdapter(rv);
                    rv.notifyDataSetChanged();

                }
                else if (exercise.equals("Optimized Diet")){
                    value = "2";
                    RecyclerViewAdapter2 rv = new RecyclerViewAdapter2(cleanList(aListModel,value),Ideal_Diat_plan_Child_result2.this,value,Ideal_Diat_plan_Child_result2.this);
                    GridLayoutManager lLayout = new GridLayoutManager(Ideal_Diat_plan_Child_result2.this, 2);
                    recycle.setLayoutManager(lLayout);
                    recycle.setItemAnimator(new DefaultItemAnimator());
                    recycle.setAdapter(rv);
                    rv.notifyDataSetChanged();
                }
                else if (exercise.equals("Ideal Diet for people with health risks")){
                    value = "3";
                    RecyclerViewAdapter2 rv = new RecyclerViewAdapter2(cleanList(aListModel,value) ,Ideal_Diat_plan_Child_result2.this,value,Ideal_Diat_plan_Child_result2.this);
                    GridLayoutManager lLayout = new GridLayoutManager(Ideal_Diat_plan_Child_result2.this, 2);
                    recycle.setLayoutManager(lLayout);
                    recycle.setItemAnimator(new DefaultItemAnimator());
                    recycle.setAdapter(rv);
                    rv.notifyDataSetChanged();

                }
                Log.e("slistttt", exercise);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RecyclerViewAdapter2 rv = new RecyclerViewAdapter2(aListModel,Ideal_Diat_plan_Child_result2.this,value,Ideal_Diat_plan_Child_result2.this);
        GridLayoutManager lLayout = new GridLayoutManager(Ideal_Diat_plan_Child_result2.this, 2);
        recycle.setLayoutManager(lLayout);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(rv);
        rv.notifyDataSetChanged();

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            gender = sp.getString("gender", "");
            age = sp.getString("age", "");
            user_id = sp.getString("Userid", "");
            sp.getString("key", "");
            height = sp.getString("height", "");
            weight = sp.getString("weight", "");
            get_dob = sp.getString("Dob", "");
            S_Exercise = sp.getString("activity_level_one", "");
            S_veg_non = sp.getString("food_habits", "");
            S_want = sp.getString("weg_loss", "").trim();
            S_is_Pregnant = sp.getString("is_pregnent", "");
            S_pre_pregnancy_weight = sp.getString("pre_pregnancy_weight", "");
            S_first_day_last_mensrual_period = sp.getString("first_day_of_last_menstrual_cycle", "");
            S_Occupation = sp.getString("activity_level_two", "");
            S_is_Breast_Feeding = sp.getString("breast_feeding", "");
            S_child_age = sp.getString("child_age", "");

            Log.e("dhsshdfkdf", gender + "..." + S_Exercise+"..."+S_veg_non+"..."+S_Occupation);
            tar_weight = sp.getString("weight_range", "");
            weight_loss = sp.getString("weight_target", "");
            cust_key = sp.getString("cust_key", "");

            major_meals  = sp.getString("MajorMeals", "");
            Breakfast = sp.getString("Breakfast", "");
            LunchDinner = sp.getString("LunchDinner", "");
            rice_type =  sp.getString("Rice", "");
            non_veg = sp.getString("Nonveg", "");
            chicken_without_skin = sp.getString("chicken", "");
            fish = sp.getString("fish", "");
            beef = sp.getString("beef", "");
            goat_meat = sp.getString("goat_meat", "");
            lamb = sp.getString("lamb", "");
            pork = sp.getString("pork", "");
            milk_type = sp.getString("milk", "");
            food_oil = sp.getString("oil", "");
            category_one = sp.getString("categ1", "");
            category_two = sp.getString("categ2", "");
            category_three = sp.getString("categ3", "");
            category_four = sp.getString("categ4", "");
            category_five = sp.getString("categ5", "");
            cereals = sp.getString("cereals", "");
            break_milk = sp.getString("break_milk", "");
            vegetables = sp.getString("vegetables", "");
            Log.e("dhsshdfkdf", tar_weight + "..." + weight_loss);
            number = Integer.parseInt(age);
            Log.e("dhsshdfkdf", S_veg_non);
            Log.e("dobbbbbb", get_dob);
            String[] separated = height.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet = f;
            inch = i;
            tar_weight_loss = S_want.toLowerCase();
            Log.e("tar_weight_loss",tar_weight_loss);

            if (gender.equals("male")){
                key_gender = "Male";
            }else {
                key_gender = "Female";
            }
            if (S_veg_non.equals("Vegetarian")){
                key_veg_non = "2";
            }else if (S_veg_non.equals("Eggetarian")){
                key_veg_non = "1";
            }else {
                key_veg_non = "3";
            }
            int number = Integer.parseInt(age);
            if (number>18){
                if (S_Exercise.equals("No regular exercise")){
                    key_activity = "1";
                }else if (S_Exercise.equals("Exercise>20 min,1-3 days/week")){
                    key_activity = "2";
                }else if (S_Exercise.equals("Exercise 30-60 min,3-4 days/week")){
                    key_activity = "3";
                }else if (S_Exercise.equals("Exercise>60 min, 5-7 days/week")){
                    key_activity = "4";
                }else if (S_Exercise.equals("Athlete or very Active")){
                    key_activity = "5";
                }
                else {
                    key_activity = "0";
                }
            }else {
                if (S_Exercise.equals("< 1 hour of sports or running/cycling etc.")){
                    key_activity = "1";
                }else if (S_Exercise.equals("1 hour or more of physical activity, 3 days/week or more")){
                    key_activity = "2";
                }else if (S_Exercise.equals(">2 hours/day of formal sports or vigorous activity")){
                    key_activity = "3";
                }else {
                    key_activity = "0";
                }
            }

            if (S_Occupation.equals("Sitting /desk jobs")){
                key_occcp = "1";
            } else if (S_Occupation.equals("Standing  for long times")){
                key_occcp = "2";
            }else if (S_Occupation.equals("Active e.g. waiter")){
                key_occcp = "3";
            }else if (S_Occupation.equals("Heavy activity, e.g. carpenter")){
                key_occcp = "4";
            }else if (S_Occupation.equals("Strenuous e.g.Labourers")){
                key_occcp = "5";
            }
            else {
                key_occcp = "0";
            }
        } catch (Exception e) {
            Log.e("SP erro r =", e + "");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), PlansActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Ideal_Diat_plan_Child_result2.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
    }

    void find_id(){

        grains_text = (TextView) findViewById(R.id.Grains_name);
        dals_text = (TextView) findViewById(R.id.dals);
        eggs_text = (TextView) findViewById(R.id.eggs);
        fish_text = (TextView) findViewById(R.id.fish);
        soya_text = (TextView) findViewById(R.id.soya);
        nuts_text = (TextView) findViewById(R.id.nuts);
        milk_text = (TextView) findViewById(R.id.milk);
        veg_text = (TextView) findViewById(R.id.veg);
        fruits_text = (TextView) findViewById(R.id.fruits);
        oils_text = (TextView) findViewById(R.id.oils);
        min_soya_text = (TextView) findViewById(R.id.soya_min);


        grains_bas = (TextView) findViewById(R.id.Grains_bas);
        dals_bas = (TextView) findViewById(R.id.dals_bas);
        eggs_bas = (TextView) findViewById(R.id.eggs_bas);
        fish_bas = (TextView) findViewById(R.id.fish_bas);
        soya_bas = (TextView) findViewById(R.id.soya_bas);
        nuts_bas = (TextView) findViewById(R.id.nuts_bas);
        milk_bas = (TextView) findViewById(R.id.milk_bas);
        veg_bas = (TextView) findViewById(R.id.veg_bas);
        fruits_bas = (TextView) findViewById(R.id.fruits_bas);
        oils_bas = (TextView) findViewById(R.id.oils_bas);
        min_soya_bas = (TextView) findViewById(R.id.soya_min_bas);



        grains_icon = (ImageView) findViewById(R.id.Grains_icon);
        dals_icon = (ImageView) findViewById(R.id.dals_info);
        eggs_icon = (ImageView) findViewById(R.id.eggs_info);
        fish_icon = (ImageView) findViewById(R.id.fish_info);
        soya_icon = (ImageView) findViewById(R.id.soya_info);
        nuts_icon = (ImageView) findViewById(R.id.nuts_info);
        milk_icon = (ImageView) findViewById(R.id.milk_info);
        veg_icon = (ImageView) findViewById(R.id.veg_info);
        fruits_icon = (ImageView) findViewById(R.id.fruits_info);
        oils_icon = (ImageView) findViewById(R.id.oils_info);
        min_soya_icon = (ImageView) findViewById(R.id.soya_min_info);
        close = (ImageView) findViewById(R.id.close);

     /*   RDCA = (TextView) findViewById(R.id.RDCA);
        bmi = (TextView) findViewById(R.id.bmi);
        weight_category = (TextView) findViewById(R.id.weight_category);
        Activity = (TextView) findViewById(R.id.Activity);*/
        recycle = (RecyclerView) findViewById(R.id.recycle);
        spin = (Spinner) findViewById(R.id.spin);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        hint = (RelativeLayout) findViewById(R.id.bottom2);
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        get_cot = (Button) findViewById(R.id.get_cot);

    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Ideal_Diat_plan_Child_result2.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Ideal_Diat_plan_Child_result2.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Ideal_Diat_plan_Child_result2.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Ideal_Diat_plan_Child_result2.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Ideal_Diat_plan_Child_result2.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }

      /*  if (v == yes) {
            new MyIntent(Ideal_Diat_plan_Child_result2.this, Major_Meals_Page1.class, R.anim.enter, R.anim.exit);
        } if (v == get_cot) {
            new MyIntent(Ideal_Diat_plan_Child_result2.this, Major_Meals_Page1.class, R.anim.enter, R.anim.exit);
        }*/

        if (v == yes) {
            if (cust_key.equals("1")){
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();
                new Task().execute();
            }
            else {
                new MyIntent(Ideal_Diat_plan_Child_result2.this, Major_Meals_Page1.class, R.anim.enter, R.anim.exit);
            }
        }
        if (v == get_cot) {
            if (cust_key.equals("1")){
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();
                new Task().execute();
            }
            else {
                new MyIntent(Ideal_Diat_plan_Child_result2.this, Major_Meals_Page1.class, R.anim.enter, R.anim.exit);
            }
        }
        if (v == no) {
           hint.setVisibility(View.GONE);
        } if (v == close) {
           hint.setVisibility(View.GONE);
        }
    }


    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ideal_Diat_plan_Child_result2.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + customize_diet_plan_and_nutrition + randnoo);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);
                postDataParams.put("gender", key_gender);
                postDataParams.put("age", age);
                postDataParams.put("wt", weight);
                postDataParams.put("want_diet_for_weight_loss", tar_weight_loss);
                postDataParams.put("ft", feet);
                postDataParams.put("inc", inch);
                postDataParams.put("meal", major_meals);
                postDataParams.put("bf", Breakfast);
                postDataParams.put("grains", LunchDinner);
                postDataParams.put("rice", rice_type);
                postDataParams.put("milk", milk_type);
                postDataParams.put("oil_combo", food_oil);
                postDataParams.put("dob", get_dob);
                postDataParams.put("veg", key_veg_non);
                postDataParams.put("exercise", key_activity);
                postDataParams.put("occupation", key_occcp);
                postDataParams.put("is_Pregnant", S_is_Pregnant);
                postDataParams.put("is_Breast_Feeding", S_is_Breast_Feeding);
                postDataParams.put("pre_pregnancy_weight", S_pre_pregnancy_weight);
                postDataParams.put("child_age", S_child_age);

                postDataParams.put("nveg", non_veg);
                postDataParams.put("chicken", chicken_without_skin);
                postDataParams.put("fish", fish);
                postDataParams.put("beef", beef);
                postDataParams.put("mutton", goat_meat);
                postDataParams.put("lamb", lamb);
                postDataParams.put("pork", pork);
                postDataParams.put("cereals", cereals);
                postDataParams.put("milk_new", break_milk);
                postDataParams.put("Vegetables", vegetables);


                postDataParams.put("oil1a", category_one);
                postDataParams.put("oil1b", category_two);
                postDataParams.put("oil2a", category_three);
                postDataParams.put("oil2b", category_four);
                postDataParams.put("oil2c", category_two);

                postDataParams.put("oil4a", category_three);
                postDataParams.put("oil4b", category_five);


                postDataParams.put("oil5a", category_three);
                postDataParams.put("oil5b", category_one);


                Log.e("params..............", postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(1500000);
                conn.setConnectTimeout(1500000);
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
                Log.e("ERRR",e+"");
            }
            return result;
        }
        boolean checkAddition(String indianDiet,String optimizedDiet,String dietWithHealthRisks)
        {
            boolean ret=true;
            if(indianDiet.equals("0.00") || indianDiet.equals(""))
            {
                ret=false;
            }
            else
            {
                return true;
            }
            if(optimizedDiet.equals("0.00") || optimizedDiet.equals(""))
            {
                ret=false;
            }
            else
            {
                return true;
            }
            if(dietWithHealthRisks.equals("0.00") ||dietWithHealthRisks.equals(""))
            {
                ret=false;
            }
            else
            {
                return true;
            }
            return ret;
        }
        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("LoginActivity = ", result + "");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");



                if (status.equals("1")) {
                    controller.addDietPlan(user_id,result);
                    JSONObject json = jobj.getJSONObject("diet_plan");
                    JSONObject jo = json.getJSONObject("MEAL_PLAN_FOR_THE_DAY");
                    JSONObject jo1 = jo.getJSONObject("MEAL");
                    JSONObject jo2 = jo1.getJSONObject("Breakfast");
                    JSONObject calorie_calculations=jobj.getJSONObject("calorie_calculations");
                    CalorieCalculator.StoreCalculatedCalories(calorie_calculations,getApplicationContext());
                    CalorieCalculator.writeCustomPlanAdult(getApplicationContext(),json.toString());





                    JSONObject Brown_Bread = jo2.getJSONObject("Brown Bread");
                    JSONObject Large_Bread = Brown_Bread.getJSONObject("1 Large Bread");
                    String Indian_Diet_Brown_Bread = Large_Bread.getString("Basic Indian Diet");
                    String Optimized_Brown_Bread = Large_Bread.getString("Optimized Indian diet");
                    String Health_Risks_Brown_Bread = Large_Bread.getString("Ideal Diet For People With Health Risks");
                    // datalist.add(new customized_model("Brown Bread", "1 Large Bread", Indian_Diet_Brown_Bread, Optimized_Brown_Bread, Health_Risks_Brown_Bread, R.drawable.bread));
                    if (!checkAddition(Indian_Diet_Brown_Bread,Optimized_Brown_Bread,Health_Risks_Brown_Bread)){}
                    else {datalist.add(new customized_model("Brown Bread", "1 Large Bread", Indian_Diet_Brown_Bread, Optimized_Brown_Bread, Health_Risks_Brown_Bread, R.drawable.bread));}



                    JSONObject White_Bread = jo2.getJSONObject("White Bread");
                    JSONObject White_Bread_Large = White_Bread.getJSONObject("1 Large Bread");
                    String Indian_Diet_White_Bread = White_Bread_Large.getString("Basic Indian Diet");
                    String Optimized_White_Bread = White_Bread_Large.getString("Optimized Indian diet");
                    String Health_Risks_White_Bread = White_Bread_Large.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("White Bread", "1 Large Bread", Indian_Diet_White_Bread, Optimized_White_Bread, Health_Risks_White_Bread, R.drawable.bread));

                    if (!checkAddition(Indian_Diet_White_Bread,Optimized_White_Bread,Health_Risks_White_Bread)){}

                    else {datalist.add(new customized_model("White Bread", "1 Large Bread", Indian_Diet_White_Bread, Optimized_White_Bread, Health_Risks_White_Bread, R.drawable.bread));}

                    JSONObject Cornflakes = jo2.getJSONObject("Cornflakes");
                    JSONObject Cup = Cornflakes.getJSONObject("1 Cup");
                    String Indian_Diet_Cornflakes = Cup.getString("Basic Indian Diet");
                    String Optimized_Cornflakes = Cup.getString("Optimized Indian diet");
                    String Health_Risks_Cornflakes = Cup.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Cornflakes", "1 Cup", Indian_Diet_Cornflakes, Optimized_Cornflakes, Health_Risks_Cornflakes, R.drawable.cornflakes));

                    if (!checkAddition(Indian_Diet_Cornflakes,Optimized_Cornflakes,Health_Risks_Cornflakes)){}
                    else {datalist.add(new customized_model("Cornflakes", "1 Cup", Indian_Diet_Cornflakes, Optimized_Cornflakes, Health_Risks_Cornflakes, R.drawable.cornflakes));}

                    JSONObject Muesli = jo2.getJSONObject("Muesli");
                    JSONObject Muesli_cup = Muesli.getJSONObject("1 Cup");
                    String Indian_Diet_Muesli = Muesli_cup.getString("Basic Indian Diet");
                    String Optimized_Muesli = Muesli_cup.getString("Optimized Indian diet");
                    String Health_Risks_Muesli = Muesli_cup.getString("Ideal Diet For People With Health Risks");
                    // datalist.add(new customized_model("Muesli", "1 Cup", Indian_Diet_Muesli, Optimized_Muesli, Health_Risks_Muesli, R.drawable.muesli));
                    if (!checkAddition(Indian_Diet_Muesli,Optimized_Muesli,Health_Risks_Muesli)){}
                    else {datalist.add(new customized_model("Muesli", "1 Cup", Indian_Diet_Muesli, Optimized_Muesli, Health_Risks_Muesli, R.drawable.muesli));}


                    JSONObject Oats = jo2.getJSONObject("Oats");
                    JSONObject Oats_cup = Oats.getJSONObject("1/2 cup cooked");
                    String Indian_Diet_Oats = Oats_cup.getString("Basic Indian Diet");
                    String Optimized_Oats = Oats_cup.getString("Optimized Indian diet");
                    String Health_Risks_Oats = Oats_cup.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Oats", "1/2 cup cooked", Indian_Diet_Oats, Optimized_Oats, Health_Risks_Oats, R.drawable.oats));

                    if (!checkAddition(Indian_Diet_Oats,Optimized_Oats,Health_Risks_Oats)){}
                    else {datalist.add(new customized_model("Oats", "1/2 cup cooked", Indian_Diet_Oats, Optimized_Oats, Health_Risks_Oats, R.drawable.oats));}

                    JSONObject Roti_in_Breakfast = jo2.getJSONObject("Roti in Breakfast");
                    JSONObject parantha = Roti_in_Breakfast.getJSONObject("1 roti or 1 parantha (excluding oil and stuffing)");
                    String Indian_Diet_parantha = parantha.getString("Basic Indian Diet");
                    String Optimized_parantha = parantha.getString("Optimized Indian diet");
                    String Health_Risks_parantha = parantha.getString("Ideal Diet For People With Health Risks");
                    // datalist.add(new customized_model("Roti in Breakfast", "1 roti or 1 parantha (excluding oil and stuffing)", Indian_Diet_parantha, Optimized_parantha, Health_Risks_parantha, R.drawable.chapati));

                    if (!checkAddition(Indian_Diet_parantha,Optimized_parantha,Health_Risks_parantha)){}
                    else {datalist.add(new customized_model("Roti in Breakfast", "1 roti or 1 parantha (excluding oil and stuffing)", Indian_Diet_parantha, Optimized_parantha, Health_Risks_parantha, R.drawable.chapati));}

                    JSONObject Milk_curd = jo2.getJSONObject("Milk/curd");
                    JSONObject Milk_curd_cup = Milk_curd.getJSONObject("1 cup= 200 ml");
                    String Indian_Diet_Milk_curd = Milk_curd_cup.getString("Basic Indian Diet");
                    String Optimized_Milk_curd = Milk_curd_cup.getString("Optimized Indian diet");
                    String Health_Risks_Milk_curd = Milk_curd_cup.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Milk/curd", "11 cup=200 ml", Indian_Diet_Milk_curd, Optimized_Milk_curd, Health_Risks_Milk_curd, R.drawable.milk));

                    if (!checkAddition(Indian_Diet_Milk_curd,Optimized_Milk_curd,Health_Risks_Milk_curd)){}
                    else {datalist.add(new customized_model("Milk/curd", "1 cup=200 ml", Indian_Diet_Milk_curd, Optimized_Milk_curd, Health_Risks_Milk_curd, R.drawable.milk));}

                    JSONObject Fruits = jo2.getJSONObject("Fruits");
                    JSONObject Fruits_cup = Fruits.getJSONObject("1 medium size fruit or 1/2 cup cut fruit OR 1/2 cup fruit juice");
                    String Indian_Diet_Fruits = Fruits_cup.getString("Basic Indian Diet");
                    String Optimized_Fruits = Fruits_cup.getString("Optimized Indian diet");
                    String Health_Risks_Fruits = Fruits_cup.getString("Ideal Diet For People With Health Risks");
                    // datalist.add(new customized_model("Fruits", "1 medium size fruit or 1/2 cup cut fruit OR 1/2 cup fruit juice", Indian_Diet_Fruits, Optimized_Fruits, Health_Risks_Fruits, R.drawable.fruit));
                    if (!checkAddition(Indian_Diet_Fruits,Optimized_Fruits,Health_Risks_Fruits)){}

                    else {datalist.add(new customized_model("Fruits", "1 medium size fruit or 1/2 cup cut fruit OR 1/2 cup fruit juice", Indian_Diet_Fruits, Optimized_Fruits, Health_Risks_Fruits, R.drawable.fruit));}
                    JSONObject Vegetables = jo2.getJSONObject("vegetables");
                    JSONObject Vegetables_cup = Vegetables.getJSONObject("1/2 cup cooked, 1 cup salad");
                    String Indian_Diet_Vegetables = Vegetables_cup.getString("Basic Indian Diet");
                    String Optimized_Vegetables = Vegetables_cup.getString("Optimized Indian diet");
                    String Health_Risks_Vegetables = Vegetables_cup.getString("Ideal Diet For People With Health Risks");
                    // datalist.add(new customized_model("Vegetables", "1/2 cup cooked, 1 cup salad", Indian_Diet_Vegetables, Optimized_Vegetables, Health_Risks_Vegetables, R.drawable.vegetables));
                    if (!checkAddition(Indian_Diet_Vegetables,Optimized_Vegetables,Health_Risks_Vegetables)){}
                    else {datalist.add(new customized_model("Vegetables", "1/2 cup cooked, 1 cup salad", Indian_Diet_Vegetables, Optimized_Vegetables, Health_Risks_Vegetables, R.drawable.vegetables));}



                    JSONObject Eggs = jo2.getJSONObject("Eggs/week");
                    JSONObject eg = Eggs.getJSONObject("1 egg");
                    String Indian_Diet_Eggs = eg.getString("Basic Indian Diet");
                    String Optimized_Eggs = eg.getString("Optimized Indian diet");
                    String Health_Risks_Eggs = eg.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Eggs/week", "1 egg", Indian_Diet_Eggs, Optimized_Eggs, Health_Risks_Eggs, R.drawable.eggs));

                    if (!checkAddition(Indian_Diet_Eggs,Optimized_Eggs,Health_Risks_Eggs)){}
                    else {datalist.add(new customized_model("Eggs/week", "1 egg", Indian_Diet_Eggs, Optimized_Eggs, Health_Risks_Eggs, R.drawable.eggs));}

                    JSONObject jo3 = jo1.getJSONObject("Lunch");

                    JSONObject rice = jo3.getJSONObject("Rice");
                    JSONObject cup_cooked = rice.getJSONObject("1/2 cup cooked");
                    String Indian_Diet_one = cup_cooked.getString("Basic Indian Diet");
                    String Optimized_one = cup_cooked.getString("Optimized Indian diet");
                    String Health_Risks_one = cup_cooked.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Rice", "1/2 cup cooked", Indian_Diet_one, Optimized_one, Health_Risks_one, R.drawable.grains));

                    if (!checkAddition(Indian_Diet_one,Optimized_one,Health_Risks_one)){}
                    else {Lunch_list.add(new customized_model("Rice", "1/2 cup cooked", Indian_Diet_one, Optimized_one, Health_Risks_one, R.drawable.grains));}

                    JSONObject Roti = jo3.getJSONObject("Roti");
                    JSONObject roti_one = Roti.getJSONObject("1 roti");
                    String Indian_Diet_roti = roti_one.getString("Basic Indian Diet");
                    String Optimized_roti = roti_one.getString("Optimized Indian diet");
                    String Health_Risks_roti = roti_one.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Roti", "1 roti", Indian_Diet_roti, Optimized_roti, Health_Risks_roti, R.drawable.chapati));

                    if (!checkAddition(Indian_Diet_roti,Optimized_roti,Health_Risks_roti)){}
                    else {Lunch_list.add(new customized_model("Roti", "1 roti", Indian_Diet_roti, Optimized_roti, Health_Risks_roti, R.drawable.chapati));}


                    JSONObject Dals_meat_fish_day = jo3.getJSONObject("daal/meat/fish");
                    JSONObject dals = Dals_meat_fish_day.getJSONObject("1/2 cup cooked dal OR 2 pieces of boneless meat each of matchbox size");
                    String Indian_Diet_Dals_meat = dals.getString("Basic Indian Diet");
                    String Optimized_Dals_meat = dals.getString("Optimized Indian diet");
                    String Health_Risks_Dals_meat = dals.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Dals/meat/fish/day", "1/2 cup cooked dal OR 2 pieces of boneless meat each of matchbox size", Indian_Diet_Dals_meat, Optimized_Dals_meat, Health_Risks_Dals_meat, R.drawable.dals));

                    if (!checkAddition(Indian_Diet_Dals_meat,Optimized_Oats,Health_Risks_Dals_meat)){}
                    else {Lunch_list.add(new customized_model("Dals/meat/fish/day", "1/2 cup cooked dal OR 2 pieces of boneless meat each of matchbox size", Indian_Diet_Dals_meat, Optimized_Dals_meat, Health_Risks_Dals_meat, R.drawable.dals));}

                    JSONObject Vegetables2 = jo3.getJSONObject("Vegetables");
                    String Indian_Diet_Vegetables2 = Vegetables2.getString("Basic Indian Diet");
                    String Optimized_Vegetables2 = Vegetables2.getString("Optimized Indian diet");
                    String Health_Risks_Vegetables2 = Vegetables2.getString("Ideal Diet For People With Health Risks");
                    //datalist.add(new customized_model("Vegetables", "1/2 cup cooked, 1 cup salad", Indian_Diet_Vegetables2, Optimized_Vegetables2, Health_Risks_Vegetables2, R.drawable.vegetables));
                    if (!checkAddition(Indian_Diet_Vegetables2,Optimized_Vegetables2,Health_Risks_Vegetables2)){}
                    else {Lunch_list.add(new customized_model("Vegetables", "1/2 cup cooked, 1 cup salad", Indian_Diet_Vegetables2, Optimized_Vegetables2, Health_Risks_Vegetables2, R.drawable.vegetables));}


                    JSONObject Milk_curd2 = jo3.getJSONObject("Milk/curd");
                    String Indian_Diet_Milk_curd2 = Milk_curd2.getString("Basic Indian Diet");
                    String Optimized_Milk_curd2 = Milk_curd2.getString("Optimized Indian diet");
                    String Health_Risks_Milk_curd2 = Milk_curd2.getString("Ideal Diet For People With Health Risks");
                    //datalist.add(new customized_model("Milk/curd", "1 cup=200 ml", Indian_Diet_Milk_curd2, Optimized_Milk_curd2, Health_Risks_Milk_curd2, R.drawable.milk));
                    if (!checkAddition(Indian_Diet_Milk_curd2,Optimized_Milk_curd2,Health_Risks_Milk_curd2)){}
                    else {Lunch_list.add(new customized_model("Milk/curd", "1 cup=200 ml", Indian_Diet_Milk_curd2, Optimized_Milk_curd2, Health_Risks_Milk_curd2, R.drawable.milk));}

                    JSONObject jo4 = jo1.getJSONObject("Evening snack");


                    JSONObject Nuts = jo4.getJSONObject("Nuts");
                    JSONObject Nuts_half = Nuts.getJSONObject("1 Oz : 22 almonds/30 peanuts/16-20 kajus/10-12 macadonia nuts/28 pecan nuts/14 walnut halfs");
                    String Indian_Diet_Nuts = Nuts_half.getString("Basic Indian Diet");
                    String Optimized_Nuts = Nuts_half.getString("Optimized Indian diet");
                    String Health_Risks_Nuts = Nuts_half.getString("Ideal Diet For People With Health Risks");
                    //datalist.add(new customized_model("Nuts", "1 Oz =22 almonds/30 peanuts/16-20 kajus/10-12 macadonia nuts/28 pecan nuts/14walnut halfs", Indian_Diet_Nuts, Optimized_Nuts, Health_Risks_Nuts, R.drawable.nuts));

                    if (!checkAddition(Indian_Diet_Nuts,Optimized_Nuts,Health_Risks_Nuts)){}
                    else {snack_list.add(new customized_model("Nuts", "1 Oz =22 almonds/30 peanuts/16-20 kajus/10-12 macadonia nuts/28 pecan nuts/14walnut halfs", Indian_Diet_Nuts, Optimized_Nuts, Health_Risks_Nuts, R.drawable.nuts));}


                    JSONObject Fruits2 = jo4.getJSONObject("Fruits");
                    String Indian_Diet_Fruits2 = Fruits2.getString("Basic Indian Diet");
                    String Optimized_Fruits2 = Fruits2.getString("Optimized Indian diet");
                    String Health_Risks_Fruits2 = Fruits2.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Fruits", "1 medium size fruit or 1/2 cup cut fruit OR 1/2 cup fruit juice", Indian_Diet_Fruits2, Optimized_Fruits2, Health_Risks_Fruits2, R.drawable.fruit));


                    if (!checkAddition(Indian_Diet_Fruits2,Optimized_Fruits2,Health_Risks_Fruits2)){}
                    else {snack_list.add(new customized_model("Fruits", "1 medium size fruit or 1/2 cup cut fruit OR 1/2 cup fruit juice", Indian_Diet_Fruits2, Optimized_Fruits2, Health_Risks_Fruits2, R.drawable.fruit));}


                    JSONObject Milk_curd3 = jo4.getJSONObject("Milk/Curd");
                    String Indian_Diet_Milk_curd3 = Milk_curd3.getString("Basic Indian Diet");
                    String Optimized_Milk_curd3 = Milk_curd3.getString("Optimized Indian diet");
                    String Health_Risks_Milk_curd3 = Milk_curd3.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Milk/curd", "11 cup=200 ml", Indian_Diet_Milk_curd3, Optimized_Milk_curd3, Health_Risks_Milk_curd3, R.drawable.milk));

                    if (!checkAddition(Indian_Diet_Fruits2,Optimized_Fruits2,Health_Risks_Fruits2)){}
                    else {snack_list.add(new customized_model("Milk/curd", "1 cup=200 ml", Indian_Diet_Milk_curd3, Optimized_Milk_curd3, Health_Risks_Milk_curd3, R.drawable.milk));}


                    JSONObject jo5 = jo1.getJSONObject("Dinner");

                    JSONObject rice1 = jo5.getJSONObject("Rice");
                    String Indian_Diet_one2 = rice1.getString("Basic Indian Diet");
                    String Optimized_one2 = rice1.getString("Optimized Indian diet");
                    String Health_Risks_one2 = rice1.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Rice", "1/2 cup cooked", Indian_Diet_one2, Optimized_one2, Health_Risks_one2, R.drawable.grains));

                    if (!checkAddition(Indian_Diet_one2,Optimized_one2,Health_Risks_one2)){}
                    else {Dinner_list.add(new customized_model("Rice", "1/2 cup cooked", Indian_Diet_one2, Optimized_one2, Health_Risks_one2, R.drawable.grains));}


                    JSONObject Dals_meat_fish_day2 = jo5.getJSONObject("daal/meat/fish");
                    JSONObject dals2 = Dals_meat_fish_day2.getJSONObject("(Number of servings per week of individual protiens Ex: Fish, Tofu etc as per the customized diet plan )");
                    String Indian_Diet_Dals_meat2 = dals2.getString("Basic Indian Diet");
                    String Optimized_Dals_meat2 = dals2.getString("Optimized Indian diet");
                    String Health_Risks_Dals_meat2 = dals2.getString("Ideal Diet For People With Health Risks");
                    // datalist.add(new customized_model("Dals/meat/fish/day", "(Number of servings per week of individual protiens Ex: Fish, Tofu etc as per the customized diet plan )", Indian_Diet_Dals_meat2, Optimized_Dals_meat2, Health_Risks_Dals_meat2, R.drawable.dals));
                    if (!checkAddition(Indian_Diet_Dals_meat2,Optimized_Dals_meat2,Health_Risks_Dals_meat2)){}
                    else {Dinner_list.add(new customized_model("Dals/meat/fish/day", "(Number of servings per week of individual protiens Ex: Fish, Tofu etc as per the customized diet plan )", Indian_Diet_Dals_meat2, Optimized_Dals_meat2, Health_Risks_Dals_meat2, R.drawable.dals));}


                    JSONObject Vegetables3 = jo5.getJSONObject("Vegetables");
                    String Indian_Diet_Vegetables3 = Vegetables3.getString("Basic Indian Diet");
                    String Optimized_Vegetables3 = Vegetables3.getString("Optimized Indian diet");
                    String Health_Risks_Vegetables3 = Vegetables3.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Vegetables", "1/2 cup cooked, 1 cup salad", Indian_Diet_Vegetables3, Optimized_Vegetables3, Health_Risks_Vegetables3, R.drawable.vegetables));

                    if (!checkAddition(Indian_Diet_Vegetables3,Optimized_Vegetables3,Health_Risks_Vegetables3)){}
                    else {Dinner_list.add(new customized_model("Vegetables", "1/2 cup cooked, 1 cup salad", Indian_Diet_Vegetables3, Optimized_Vegetables3, Health_Risks_Vegetables3, R.drawable.vegetables));}


                    JSONObject Milk_curd4 = jo5.getJSONObject("Milk/Curd");
                    String Indian_Diet_Milk_curd4 = Milk_curd4.getString("Basic Indian Diet");
                    String Optimized_Milk_curd4 = Milk_curd4.getString("Optimized Indian diet");
                    String Health_Risks_Milk_curd4 = Milk_curd4.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Milk/curd", "11 cup=200 ml", Indian_Diet_Milk_curd4, Optimized_Milk_curd4, Health_Risks_Milk_curd4, R.drawable.milk));

                    if (!checkAddition(Indian_Diet_Milk_curd4,Optimized_Milk_curd4,Health_Risks_Milk_curd4)){}
                    else {Dinner_list.add(new customized_model("Milk/curd", "1 cup=200 ml", Indian_Diet_Milk_curd4, Optimized_Milk_curd4, Health_Risks_Milk_curd4, R.drawable.milk));}

                    JSONObject Roti1 = jo5.getJSONObject("Roti");
                    String Indian_Diet_roti1 = Roti1.getString("Basic Indian Diet");
                    String Optimized_roti1 = Roti1.getString("Optimized Indian diet");
                    String Health_Risks_roti1 = Roti1.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Roti", "1 roti", Indian_Diet_roti1, Optimized_roti1, Health_Risks_roti1, R.drawable.chapati));

                    if (!checkAddition(Indian_Diet_roti1,Optimized_roti,Health_Risks_roti1)){}
                    else{ Dinner_list.add(new customized_model("Roti", "1 roti", Indian_Diet_roti1, Optimized_roti1, Health_Risks_roti1, R.drawable.chapati));}



                    JSONObject jo6 = jo1.getJSONObject("Morning snack");

                    JSONObject Milk_curd5 = jo6.getJSONObject("Milk/Curd");
                    JSONObject Milk_curd_cup1 = Milk_curd5.getJSONObject("1 cup= 200 ml");
                    String Indian_Diet_Milk_curd5 = Milk_curd_cup1.getString("Basic Indian Diet");
                    String Optimized_Milk_curd5 = Milk_curd_cup1.getString("Optimized Indian diet");
                    String Health_Risks_Milk_curd5 = Milk_curd_cup1.getString("Ideal Diet For People With Health Risks");
                    // datalist.add(new customized_model("Milk/curd", "1 cup= 200 ml", Indian_Diet_Milk_curd5, Optimized_Milk_curd5, Health_Risks_Milk_curd5, R.drawable.milk));
                    if (!checkAddition(Indian_Diet_Milk_curd5,Optimized_Milk_curd5,Health_Risks_Milk_curd5)){}
                    else{ Morningsnack_list.add(new customized_model("Milk/curd", "1 cup= 200 ml", Indian_Diet_Milk_curd5, Optimized_Milk_curd5, Health_Risks_Milk_curd5, R.drawable.milk));}



                    JSONObject Fruits6 = jo6.getJSONObject("Fruits");
                    JSONObject Fruits_cup2 = Fruits6.getJSONObject("1 medium size fruit or 1/2 cup cut fruit OR 1/2 cup fruit juice");
                    String Indian_Diet_Fruits6 = Fruits_cup2.getString("Basic Indian Diet");
                    String Optimized_Fruits6 = Fruits_cup2.getString("Optimized Indian diet");
                    String Health_Risks_Fruits6 = Fruits_cup2.getString("Ideal Diet For People With Health Risks");
//                    datalist.add(new customized_model("Fruits", "1 medium size fruit or 1/2 cup cut fruit OR 1/2 cup fruit juice", Indian_Diet_Fruits6, Optimized_Fruits6, Health_Risks_Fruits6, R.drawable.fruit));

                    if (!checkAddition(Indian_Diet_Fruits6,Optimized_Fruits6,Health_Risks_Fruits6)){}
                    else{ Morningsnack_list.add(new customized_model("Fruits", "1 medium size fruit or 1/2 cup cut fruit OR 1/2 cup fruit juice", Indian_Diet_Fruits6, Optimized_Fruits6, Health_Risks_Fruits6, R.drawable.fruit));}



                    // Toast.makeText(Vegetables_page8.this, message, Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Ideal_Diat_plan_Child_result2.this, Customised_Diet_Plan_result.class);
                    in.putExtra("data", datalist);
                    in.putExtra("lunch", Lunch_list);
                    in.putExtra("dinner", Dinner_list);
                    in.putExtra("MSnack", Morningsnack_list);
                    in.putExtra("Snack", snack_list);
                    startActivity(in);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    finish();
                    //   Snackbar.make(activity_create_account,message,Snackbar.LENGTH_LONG).show();
                } else {
                    // Toast.makeText(Vegetables_page8.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("VolleyError= ", e + "");
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
}
