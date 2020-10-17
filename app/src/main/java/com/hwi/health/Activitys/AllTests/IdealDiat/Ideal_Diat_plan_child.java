package com.hwi.health.Activitys.AllTests.IdealDiat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hwi.health.Activitys.Plans.Excercize_plans.CalorieCalculator;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.Ideal_Diat_Model;
import com.hwi.health.Models.RecycleModel;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.edit_profile.Edit_profile_Activity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.hwi.health.InterFaces.Util.getActivity1Number;
import static com.hwi.health.InterFaces.Util.getVegNumber;

public class Ideal_Diat_plan_child extends AppCompatActivity implements View.OnClickListener, BaseUrl {
    Spinner gender, Exercise, veg_non;
    ArrayList<String> glist = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> elist = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> vnlist = new ArrayList<>();
    EditText weight, dob, feet, inch;
    int YEAR, MONTH, DAY;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ArrayAdapter<String> adap;
    ArrayAdapter<Ideal_Diat_Model> eadap;
    ArrayAdapter<Ideal_Diat_Model> vnadap;
    Button Calculate, Reset;
    String randnoo;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String S_gender, S_dob, S_ft, S_inc, S_weight, S_Exercise, S_Occupation, S_veg_non, S_want;
    int S_age;
    String S_is_Pregnant, S_is_Breast_Feeding, S_first_day_last_mensrual_period, S_pre_pregnancy_weight, S_child_age;
    String strDate, strDate2;
    View root;
    ProgressDialog pd;
    ArrayList<RecycleModel> datalist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("CHILDREN IDEAL DIET PLAN");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal__diat_plan_child);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
      /*  weight = (EditText) findViewById(R.id.weight);
        dob = (EditText) findViewById(R.id.dob);
        feet = (EditText) findViewById(R.id.feet);
        inch = (EditText) findViewById(R.id.inch);
        gender = (Spinner) findViewById(R.id.gender);*/
        Exercise = (Spinner) findViewById(R.id.Exercise);
        veg_non = (Spinner) findViewById(R.id.veg_non);
        Reset = (Button) findViewById(R.id.Reset);
        Calculate = (Button) findViewById(R.id.Calculate);
        root = (View) findViewById(R.id.activity_ideal__diat_plan_child);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);


        // dob.setOnClickListener(this);
        Calculate.setOnClickListener(this);
        Reset.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("UserData",MODE_PRIVATE);
        strDate = sp.getString("strDate","");
        S_age = Integer.parseInt(sp.getString("S_age",""));
        S_ft = sp.getString("fit","");
        S_inc = sp.getString("inch","");
        S_weight = sp.getString("Weight","");
        S_gender = sp.getString("gen","");
         Log.e("jgdsdgsjds",S_ft+".."+S_inc);
        spinner();

    }

    @Override
    public void onClick(View v) {
       /* if (v == dob) {
            datepicker(dob);

        }
*/
        if (v == Reset) {
            dob.setText("");
            feet.setText("");
            inch.setText("");
            weight.setText("");

        }
        if (v == Calculate) {
          /*  S_dob = dob.getText().toString();
            S_ft = feet.getText().toString();
            S_inc = inch.getText().toString();
            S_weight = weight.getText().toString();*/
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();


            pd = new ProgressDialog(Ideal_Diat_plan_child.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
            DataConnection();
        }
        if (v == home_L) {
            new MyIntent(Ideal_Diat_plan_child.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Ideal_Diat_plan_child.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Ideal_Diat_plan_child.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Ideal_Diat_plan_child.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Ideal_Diat_plan_child.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Ideal_Diat_plan_child.this, IDEAL_DIET_PLAN.class, R.anim.enter2, R.anim.exit2);
    }

    void spinner() {
      /*  glist.add("Male");
        glist.add("Female");

        adap = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, glist);
        gender.setAdapter(adap);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                S_gender = glist.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


        elist.add(new Ideal_Diat_Model("< 1 hour of sports or running/cycling etc.", "1"));
        elist.add(new Ideal_Diat_Model("1 hour or more of physical activity, 3 days/week or more", "2"));
        elist.add(new Ideal_Diat_Model(">2 hours/day of formal sports or vigorous activity", "3"));

        eadap = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, elist);
        Exercise.setAdapter(eadap);
        Exercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model im = elist.get(position);
                S_Exercise = im.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        vnlist.add(new Ideal_Diat_Model("Vegetarian ,who does eat eggs", "1"));
        vnlist.add(new Ideal_Diat_Model(" Vegetarian, who does not eat egg", "2"));
        vnlist.add(new Ideal_Diat_Model(" Non-vegetarian", "3"));
        vnadap = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, vnlist);
        veg_non.setAdapter(vnadap);

        veg_non.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model inm = vnlist.get(position);
                S_veg_non = inm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

   /* void datepicker(final EditText et) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(Ideal_Diat_plan_child.this, R.style.DialogTheme,
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
                        strDate2 = format2.format(calendar.getTime());

                        et.setText(strDate2 + "");
                        S_age = mYear - year;

                        Log.e("S_age = ", S_age + "");

                    }
                }, mYear, mMonth, mDay);
//        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


        dpd.show();

    }*/

    void DataConnection() {

        if(CalorieCalculator.getIdealDietPlanAdult(getApplicationContext()).equals("defaultValue")!=true
                 && Edit_profile_Activity.profileEdited==false)
        {
            processIdealPlanChild(CalorieCalculator.getIdealDietPlanAdult((getApplicationContext())));
            return;
        }


        if(Edit_profile_Activity.profileEdited==true)
            Edit_profile_Activity.profileEdited=false;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + ideal_diet_plan_for_child + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");

                            if (status.equals("1")) {
                                CalorieCalculator.writeIdealDietPlanAdult(getApplicationContext(),response);
                                processIdealPlanChild(response);

                            } else {
                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");
                                Snackbar.make(root, reason, Snackbar.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
                pd.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("age", S_age + "");
                params.put("sex", S_gender);
                params.put("Weight", S_weight);
                params.put("dob", strDate);
                params.put("ft", S_ft);
                params.put("inc", S_inc);
                params.put("exercise", getActivity1Number(S_Exercise));
                params.put("veg_nonveg", getVegNumber(S_veg_non));

                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    private void processIdealPlanChild(String response) {
        try {
            JSONObject jobj = new JSONObject(response);
            String status = jobj.getString("status");
            String message = jobj.getString("message");

            if (status.equals("1")) {

                JSONObject joo = jobj.getJSONObject("ideal_diet_plan_for_child");
                JSONObject jo = joo.getJSONObject("ideal_diet_plan_for_child");

                //  String weight_range = jo.getString("Your weight loss target (over 3-6 months)");
                String Weight_Category = jo.getString("Your Child healthy weight Category is");
                String bmi = jo.getString("Your Child BMI is");
                String ActivityLevel = jo.getString("Your Child Activity Level is");
                String RDCA = jo.getString("Your Child RDCA (Recommended Daily Calorie Allowance)");
                String DailyCalorieAllowance = jo.getString("Your Child Recommended Daily Calorie Allowance rounded to nearest 200 cal multiple");

                // String Category = jo.getString("Category");
                String Gender = jo.getString("Gender");
                String Age = jo.getString("Age");
                String Height = jo.getString("Height");


                JSONObject jdite_plan = jo.getJSONObject("YOUR BASIC DIET PLAN IS");
                JSONObject GrainsCereals = jdite_plan.getJSONObject("Grains/cereals");
                String GrainsCereals_Servings_equals = GrainsCereals.getString("1 Servings equals");
                JSONObject GrainsCereals_NumberofServings = GrainsCereals.getJSONObject("Number of Servings");
                String GrainsCereals_BasicIndianDiet = GrainsCereals_NumberofServings.getString("Basic Indian Diet");
                String GrainsCereals_OptimizedIndiandiet = GrainsCereals_NumberofServings.getString("Optimized Indian diet");
                String GrainsCereals_IdealDietForPeopleWithHealthRisks = GrainsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject GrainsCereals_Importantconsiderations = GrainsCereals.getJSONObject("Important considerations");
                String GrainsCereals_ToknowmoregotoFWItips = GrainsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Grains/cereals", GrainsCereals_Servings_equals, GrainsCereals_BasicIndianDiet, GrainsCereals_OptimizedIndiandiet, GrainsCereals_IdealDietForPeopleWithHealthRisks, GrainsCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.fish));

                JSONObject Dalsmeatsday = jdite_plan.getJSONObject("Dals/meats/day");
                String DalCereals_Servings_equals = Dalsmeatsday.getString("1 Servings equals");
                JSONObject DalCereals_NumberofServings = Dalsmeatsday.getJSONObject("Number of Servings");
                String DalCereals_BasicIndianDiet = DalCereals_NumberofServings.getString("Basic Indian Diet");
                String DalCereals_OptimizedIndiandiet = DalCereals_NumberofServings.getString("Optimized Indian diet");
                String DalCereals_IdealDietForPeopleWithHealthRisks = DalCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject DalCereals_Importantconsiderations = Dalsmeatsday.getJSONObject("Important considerations");
                String DalCereals_ToknowmoregotoFWItips = DalCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Dals/meats/day", DalCereals_Servings_equals, DalCereals_BasicIndianDiet, DalCereals_OptimizedIndiandiet, DalCereals_IdealDietForPeopleWithHealthRisks, DalCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.fish));


                JSONObject Nuts = jdite_plan.getJSONObject("Nuts/seeds/day");
                String NutsCereals_Servings_equals = Nuts.getString("1 Servings equals");
                JSONObject NutsCereals_NumberofServings = Nuts.getJSONObject("Number of Servings");
                String NutsaCereals_BasicIndianDiet = NutsCereals_NumberofServings.getString("Basic Indian Diet");
                String NutsCereals_OptimizedIndiandiet = NutsCereals_NumberofServings.getString("Optimized Indian diet");
                String NutsCereals_IdealDietForPeopleWithHealthRisks = NutsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject NutsCereals_Importantconsiderations = Nuts.getJSONObject("Important considerations");
                String NutsCereals_ToknowmoregotoFWItips = NutsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Nuts/seeds/day", NutsCereals_Servings_equals, NutsaCereals_BasicIndianDiet, NutsCereals_OptimizedIndiandiet, NutsCereals_IdealDietForPeopleWithHealthRisks, NutsCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.fish));

                JSONObject Milk = jdite_plan.getJSONObject("Milk/curd");
                String MilkCereals_Servings_equals = Milk.getString("1 Servings equals");
                JSONObject MilkCereals_NumberofServings = Milk.getJSONObject("Number of Servings");
                String MilkCereals_BasicIndianDiet = MilkCereals_NumberofServings.getString("Basic Indian Diet");
                String MilkCereals_OptimizedIndiandiet = MilkCereals_NumberofServings.getString("Optimized Indian diet");
                String MilkCereals_IdealDietForPeopleWithHealthRisks = MilkCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MilkCereals_Importantconsiderations = Milk.getJSONObject("Important considerations");
                String MilksCereals_ToknowmoregotoFWItips = MilkCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Milk/curd", MilkCereals_Servings_equals, MilkCereals_BasicIndianDiet, MilkCereals_OptimizedIndiandiet, MilkCereals_IdealDietForPeopleWithHealthRisks, MilksCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.fish));


                JSONObject Vegetables = jdite_plan.getJSONObject("Vegetables");
                String vegCereals_Servings_equals = Vegetables.getString("1 Servings equals");
                JSONObject vegCereals_NumberofServings = Vegetables.getJSONObject("Number of Servings");
                String vegCereals_BasicIndianDiet = vegCereals_NumberofServings.getString("Basic Indian Diet");
                String vegCereals_OptimizedIndiandiet = vegCereals_NumberofServings.getString("Optimized Indian diet");
                String vegCereals_IdealDietForPeopleWithHealthRisks = vegCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject vegCereals_Importantconsiderations = Vegetables.getJSONObject("Important considerations");
                String vegCereals_ToknowmoregotoFWItips = vegCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Vegetables", vegCereals_Servings_equals, vegCereals_BasicIndianDiet, vegCereals_OptimizedIndiandiet, vegCereals_IdealDietForPeopleWithHealthRisks, vegCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.fish));


                JSONObject Fruits = jdite_plan.getJSONObject("Fruits");
                String FruitsCereals_Servings_equals = Fruits.getString("1 Servings equals");
                JSONObject FruitsCereals_NumberofServings = Fruits.getJSONObject("Number of Servings");
                String FruitsCereals_BasicIndianDiet = FruitsCereals_NumberofServings.getString("Basic Indian Diet");
                String FruitsCereals_OptimizedIndiandiet = FruitsCereals_NumberofServings.getString("Optimized Indian diet");
                String FruitsCereals_IdealDietForPeopleWithHealthRisks = FruitsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject FruitsCereals_Importantconsiderations = Fruits.getJSONObject("Important considerations");
                String FruitsCereals_ToknowmoregotoFWItips = FruitsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Fruits", FruitsCereals_Servings_equals, FruitsCereals_BasicIndianDiet, FruitsCereals_OptimizedIndiandiet, FruitsCereals_IdealDietForPeopleWithHealthRisks, FruitsCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.fish));


                JSONObject Oils = jdite_plan.getJSONObject("Oils");
                String OilsCereals_Servings_equals = Oils.getString("1 Servings equals");
                JSONObject OilsCereals_NumberofServings = Oils.getJSONObject("Number of Servings");
                String OilsCereals_BasicIndianDiet = OilsCereals_NumberofServings.getString("Basic Indian Diet");
                String OilsCereals_OptimizedIndiandiet = OilsCereals_NumberofServings.getString("Optimized Indian diet");
                String OilsCereals_IdealDietForPeopleWithHealthRisks = OilsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject OilsCereals_Importantconsiderations = Oils.getJSONObject("Important considerations");
                String OilsCereals_ToknowmoregotoFWItips = OilsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");

                datalist.add(new RecycleModel("Oils", OilsCereals_Servings_equals, OilsCereals_BasicIndianDiet, OilsCereals_OptimizedIndiandiet, OilsCereals_IdealDietForPeopleWithHealthRisks, OilsCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.fish));
                JSONObject MaxmEggsweek = jdite_plan.getJSONObject("Maxm. Eggs/week");
                String MaxmCereals_Servings_equals = MaxmEggsweek.getString("1 Servings equals");
                JSONObject MaxmCereals_NumberofServings = MaxmEggsweek.getJSONObject("Number of Servings");
                String MaxmCereals_BasicIndianDiet = MaxmCereals_NumberofServings.getString("Basic Indian Diet");
                String MaxmCereals_OptimizedIndiandiet = MaxmCereals_NumberofServings.getString("Optimized Indian diet");
                String MaxmCereals_IdealDietForPeopleWithHealthRisks = MaxmCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MaxmCereals_Importantconsiderations = MaxmEggsweek.getJSONObject("Important considerations");
                String MaxmCereals_ToknowmoregotoFWItips = MaxmCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Maxm. Eggs/week", MaxmCereals_Servings_equals, MaxmCereals_BasicIndianDiet, MaxmCereals_OptimizedIndiandiet, MaxmCereals_IdealDietForPeopleWithHealthRisks, MaxmCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.fish));


                JSONObject MinmFishseafoodweek = jdite_plan.getJSONObject("Minm. Fish/seafood/week");
                String MinmCereals_Servings_equals = MinmFishseafoodweek.getString("1 Servings equals");
                JSONObject MinmCereals_NumberofServings = MinmFishseafoodweek.getJSONObject("Number of Servings");
                String MinmCereals_BasicIndianDiet = MinmCereals_NumberofServings.getString("Basic Indian Diet");
                String MinmCereals_OptimizedIndiandiet = MinmCereals_NumberofServings.getString("Optimized Indian diet");
                String MinmCereals_IdealDietForPeopleWithHealthRisks = MinmCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MinmCereals_Importantconsiderations = MinmFishseafoodweek.getJSONObject("Important considerations");
                String MinmCereals_ToknowmoregotoFWItips = MinmCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Minm. Fish/seafood/week", MinmCereals_Servings_equals, MinmCereals_BasicIndianDiet, MinmCereals_OptimizedIndiandiet, MinmCereals_IdealDietForPeopleWithHealthRisks, MinmCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.fish));


                JSONObject nonvegetarians = jdite_plan.getJSONObject("Soya products/week for non-vegetarians");
                String nvCereals_Servings_equals = nonvegetarians.getString("1 Servings equals");
                JSONObject nvCereals_NumberofServings = MinmFishseafoodweek.getJSONObject("Number of Servings");
                String nvCereals_BasicIndianDiet = nvCereals_NumberofServings.getString("Basic Indian Diet");
                String nvCereals_OptimizedIndiandiet = nvCereals_NumberofServings.getString("Optimized Indian diet");
                String nvCereals_IdealDietForPeopleWithHealthRisks = nvCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject nvCereals_Importantconsiderations = nonvegetarians.getJSONObject("Important considerations");
                String nvCereals_ToknowmoregotoFWItips = nvCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Soya products/week for non-vegetarians", nvCereals_Servings_equals, nvCereals_BasicIndianDiet, nvCereals_OptimizedIndiandiet, nvCereals_IdealDietForPeopleWithHealthRisks, nvCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.fish));


                JSONObject Soyagetarians = jdite_plan.getJSONObject("Min. Soya products/week");
                String SoyaCereals_Servings_equals = Soyagetarians.getString("1 Servings equals");
                JSONObject SoyaCereals_NumberofServings = MinmFishseafoodweek.getJSONObject("Number of Servings");
                String SoyaCereals_BasicIndianDiet = SoyaCereals_NumberofServings.getString("Basic Indian Diet");
                String SoyaCereals_OptimizedIndiandiet = SoyaCereals_NumberofServings.getString("Optimized Indian diet");
                String SoyaCereals_IdealDietForPeopleWithHealthRisks = SoyaCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject SoyaCereals_Importantconsiderations = Soyagetarians.getJSONObject("Important considerations");
                String SoyaCereals_ToknowmoregotoFWItips = SoyaCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Min. Soya products/week", SoyaCereals_Servings_equals, SoyaCereals_BasicIndianDiet, SoyaCereals_OptimizedIndiandiet, SoyaCereals_IdealDietForPeopleWithHealthRisks, SoyaCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.fish));



                Intent in = new Intent(getApplicationContext(), Ideal_Diat_plan_Child_result.class);
                in.putExtra("data", datalist);
                in.putExtra("ActivityLevel", ActivityLevel);
                in.putExtra("RDCA", RDCA);
                in.putExtra("Category", Weight_Category);
                in.putExtra("bmi", bmi);
                startActivity(in);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();

            } else {
                JSONObject jj = new JSONObject(response);
                JSONObject j = jj.getJSONObject("message");
                String reason = j.getString("reason");
                Snackbar.make(root, reason, Snackbar.LENGTH_LONG).show();
            }
        } catch (Exception e) {
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), IDEAL_DIET_PLAN.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
