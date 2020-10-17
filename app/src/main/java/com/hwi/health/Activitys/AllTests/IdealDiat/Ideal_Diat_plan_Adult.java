package com.hwi.health.Activitys.AllTests.IdealDiat;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
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
import android.widget.Toast;

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
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.edit_profile.Edit_profile_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.hwi.health.InterFaces.Util.getActivity1Number;
import static com.hwi.health.InterFaces.Util.getActivity2Number;
import static com.hwi.health.InterFaces.Util.getVegNumber;

public class Ideal_Diat_plan_Adult extends AppCompatActivity implements View.OnClickListener, BaseUrl {

    String strDate2;
    Spinner Exercise, Occupation, veg_non, want_weight_loss, Are_you_Pregnant, Breast_Feeding, child_age;
    TextView bf, ayp, child_age_des;
    EditText last_mc, pre_pregnancy_weight;
    TextInputLayout last_mc_des, pre_pregnancy_weight_des;
    int YEAR, MONTH, DAY;
    private int mYear, mMonth, mDay;
    ArrayList<RecycleModel> datalist = new ArrayList<>();
    ProgressDialog pd;
    ArrayList<String> glist = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> childlist = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> alist = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> blist = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> wlist = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> elist = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> olist = new ArrayList<>();
    ArrayList<Ideal_Diat_Model> vnlist = new ArrayList<>();
    ArrayAdapter<String> adap;
    ArrayAdapter<Ideal_Diat_Model> childadap;
    ArrayAdapter<Ideal_Diat_Model> aadap;
    ArrayAdapter<Ideal_Diat_Model> badap;
    ArrayAdapter<Ideal_Diat_Model> wadap;
    ArrayAdapter<Ideal_Diat_Model> eadap;
    ArrayAdapter<Ideal_Diat_Model> oadap;
    ArrayAdapter<Ideal_Diat_Model> vnadap;
    String strDate;
    View root;
    Button Calculate, Reset;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String randnoo;
    String S_gender, S_dob, S_ft, S_inc, S_weight, S_Exercise, S_Occupation, S_veg_non, S_want;
    int S_age;
    String S_is_Pregnant = "null", S_is_Breast_Feeding = "null", S_first_day_last_mensrual_period = "null", S_pre_pregnancy_weight = "null", S_child_age = "null";
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("T PLAN");


        setContentView(R.layout.activity_ideal__diat_plan__next);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        last_mc = (EditText) findViewById(R.id.last_mc);
        pre_pregnancy_weight = (EditText) findViewById(R.id.pre_pregnancy_weight);
       /* weight = (EditText) findViewById(R.id.weight);
        dob = (EditText) findViewById(R.id.dob);
        feet = (EditText) findViewById(R.id.feet);
        inch = (EditText) findViewById(R.id.inch);
        gender = (Spinner) findViewById(R.id.gender);*/
        Exercise = (Spinner) findViewById(R.id.Exercise);
        Occupation = (Spinner) findViewById(R.id.Occupation);
        veg_non = (Spinner) findViewById(R.id.veg_non);
        Are_you_Pregnant = (Spinner) findViewById(R.id.Are_you_Pregnant);
        Breast_Feeding = (Spinner) findViewById(R.id.Breast_Feeding);
        want_weight_loss = (Spinner) findViewById(R.id.want_weight_loss);
        child_age = (Spinner) findViewById(R.id.child_age);
        root = (View) findViewById(R.id.activity_bmi__and__weight__loss__calculator);
        Reset = (Button) findViewById(R.id.Reset);
        Calculate = (Button) findViewById(R.id.Calculate);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        last_mc_des = (TextInputLayout) findViewById(R.id.last_mc_des);
        pre_pregnancy_weight_des = (TextInputLayout) findViewById(R.id.pre_pregnancy_weight_des);

        bf = (TextView) findViewById(R.id.bf);
        ayp = (TextView) findViewById(R.id.ayp);
        child_age_des = (TextView) findViewById(R.id.child_age_des);

        spinn();
        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
        S_dob = sp.getString("strDate", "");
        S_age = Integer.parseInt(sp.getString("S_age", ""));
        S_ft = sp.getString("fit", "");
        S_inc = sp.getString("inch", "");
        S_weight = sp.getString("Weight", "");
        S_gender = sp.getString("gen", "");

        // dob.setOnClickListener(this);

        if (S_gender.equalsIgnoreCase("female")) {
            Are_you_Pregnant.setVisibility(View.VISIBLE);
            Breast_Feeding.setVisibility(View.VISIBLE);
            bf.setVisibility(View.VISIBLE);
            ayp.setVisibility(View.VISIBLE);

//                    child_age.setVisibility(View.VISIBLE);
//                    child_age_des.setVisibility(View.VISIBLE);
//                    last_mc_des.setVisibility(View.VISIBLE);
//                    pre_pregnancy_weight_des.setVisibility(View.VISIBLE);

//                    last_mc.setVisibility(View.VISIBLE);
//                    pre_pregnancy_weight.setVisibility(View.VISIBLE);

        } else {
            Are_you_Pregnant.setVisibility(View.GONE);
            Breast_Feeding.setVisibility(View.GONE);
            bf.setVisibility(View.GONE);
            ayp.setVisibility(View.GONE);

            child_age.setVisibility(View.GONE);
            child_age_des.setVisibility(View.GONE);

            last_mc_des.setVisibility(View.GONE);
            pre_pregnancy_weight_des.setVisibility(View.GONE);
            // last_mc.setVisibility(View.GONE);
//                    pre_pregnancy_weight.setVisibility(View.GONE);
        }

        Calculate.setOnClickListener(this);
        Reset.setOnClickListener(this);
        last_mc_des.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Ideal_Diat_plan_Adult.this, IDEAL_DIET_PLAN.class, R.anim.enter2, R.anim.exit2);
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



    void spinn() {

        elist.add(new Ideal_Diat_Model("No regular exercise", "1"));
        elist.add(new Ideal_Diat_Model("Exercise>20 min,1-3 days/week", "2"));
        elist.add(new Ideal_Diat_Model("Exercise>60 min, 5-7 days/week", "3"));
        elist.add(new Ideal_Diat_Model("Athlete or very active", "4"));

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

        olist.add(new Ideal_Diat_Model("Sitting /desk jobs", "1"));
        olist.add(new Ideal_Diat_Model("Standing  for long times", "2"));
        olist.add(new Ideal_Diat_Model("Active e.g. waiter", "3"));
        olist.add(new Ideal_Diat_Model("Heavy activity, e.g. carpenter", "4"));
        olist.add(new Ideal_Diat_Model("Strenuous e.g.Labourers .", "5"));
        oadap = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, olist);
        Occupation.setAdapter(oadap);

        Occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model inm = olist.get(position);

                S_Occupation = inm.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        vnlist.add(new Ideal_Diat_Model("Vegetarian ,who does eat eggs", "1"));
        vnlist.add(new Ideal_Diat_Model("Vegetarian, who does not eat egg", "2"));
        vnlist.add(new Ideal_Diat_Model("Non-vegetarian", "3"));
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

        wlist.add(new Ideal_Diat_Model("No", "wt_no"));
        wlist.add(new Ideal_Diat_Model("Yes", "wt_yes"));
        wadap = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, wlist);
        want_weight_loss.setAdapter(wadap);

        want_weight_loss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model inm = wlist.get(position);
                S_want = inm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        alist.add(new Ideal_Diat_Model("No", "no"));
        alist.add(new Ideal_Diat_Model("Yes", "yes"));
        aadap = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, alist);
        Are_you_Pregnant.setAdapter(aadap);

        Are_you_Pregnant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model inm = alist.get(position);
                S_is_Pregnant = inm.getId();

                if (S_gender.equalsIgnoreCase("female")) {

                    if (S_is_Pregnant.equalsIgnoreCase("no")) {
                        Breast_Feeding.setVisibility(View.VISIBLE);
                        bf.setVisibility(View.VISIBLE);
                        last_mc.setVisibility(View.GONE);
                        pre_pregnancy_weight.setVisibility(View.GONE);
                        child_age.setVisibility(View.GONE);
                        child_age_des.setVisibility(View.GONE);

                        last_mc_des.setVisibility(View.GONE);
                        pre_pregnancy_weight_des.setVisibility(View.GONE);

                        Toast.makeText(Ideal_Diat_plan_Adult.this, "no", Toast.LENGTH_SHORT).show();

                    } else {
                        Breast_Feeding.setVisibility(View.GONE);
                        bf.setVisibility(View.GONE);
                        last_mc.setVisibility(View.VISIBLE);
                        pre_pregnancy_weight.setVisibility(View.VISIBLE);

                        child_age.setVisibility(View.GONE);
                        child_age_des.setVisibility(View.GONE);

                        last_mc_des.setVisibility(View.VISIBLE);
                        pre_pregnancy_weight_des.setVisibility(View.VISIBLE);

                        Toast.makeText(Ideal_Diat_plan_Adult.this, "Yes", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        blist.add(new Ideal_Diat_Model("No", "nob"));
        blist.add(new Ideal_Diat_Model("Yes", "yesb"));
        badap = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, blist);
        Breast_Feeding.setAdapter(badap);

        Breast_Feeding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model inm = blist.get(position);
                S_is_Breast_Feeding = inm.getId();

                if (S_gender.equalsIgnoreCase("female")) {

                    if (S_is_Breast_Feeding.equalsIgnoreCase("nob")) {
                        Breast_Feeding.setVisibility(View.VISIBLE);
                        bf.setVisibility(View.VISIBLE);
                        last_mc.setVisibility(View.GONE);
                        pre_pregnancy_weight.setVisibility(View.GONE);
                        child_age.setVisibility(View.GONE);
                        child_age_des.setVisibility(View.GONE);

                        last_mc_des.setVisibility(View.GONE);
                        pre_pregnancy_weight_des.setVisibility(View.GONE);


                        Are_you_Pregnant.setVisibility(View.VISIBLE);
                        ayp.setVisibility(View.VISIBLE);


                        Toast.makeText(Ideal_Diat_plan_Adult.this, "no", Toast.LENGTH_SHORT).show();

                    } else {
                        Breast_Feeding.setVisibility(View.VISIBLE);
                        bf.setVisibility(View.VISIBLE);
                        Are_you_Pregnant.setVisibility(View.GONE);
                        ayp.setVisibility(View.GONE);

                        last_mc.setVisibility(View.VISIBLE);
                        pre_pregnancy_weight.setVisibility(View.VISIBLE);

                        child_age.setVisibility(View.VISIBLE);
                        child_age_des.setVisibility(View.VISIBLE);

                        last_mc_des.setVisibility(View.GONE);
                        pre_pregnancy_weight_des.setVisibility(View.VISIBLE);

                        Toast.makeText(Ideal_Diat_plan_Adult.this, "Yes", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    Breast_Feeding.setVisibility(View.GONE);
//                    bf.setVisibility(View.GONE);
//                    child_age.setVisibility(View.GONE);
//                    child_age_des.setVisibility(View.GONE);
//                    last_mc.setVisibility(View.GONE);
//                    pre_pregnancy_weight.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        childlist.add(new Ideal_Diat_Model("6 months or younger", "6 months or younger"));
        childlist.add(new Ideal_Diat_Model(" Older than 6 months", "older than 6 months"));
        childadap = new ArrayAdapter<Ideal_Diat_Model>(this, android.R.layout.simple_dropdown_item_1line, childlist);
        child_age.setAdapter(childadap);

        child_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ideal_Diat_Model inm = blist.get(position);
                S_child_age = inm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SharedPreferences sps = new AllSharedPrefrences(this).UserDataget();
        user_id = sps.getString("Userid", "");
     //   GetPeofile();

    }

    @Override
    public void onClick(View v) {

       /* if (v == dob) {
            datepicker(dob);

        }*/

        if (v == home_L) {
            new MyIntent(Ideal_Diat_plan_Adult.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Ideal_Diat_plan_Adult.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Ideal_Diat_plan_Adult.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Ideal_Diat_plan_Adult.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Ideal_Diat_plan_Adult.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == last_mc) {
            datepicker(last_mc);
        }
        if (v == Reset) {
//            dob.setText("");
//            feet.setText("");
//            inch.setText("");
//            weight.setText("");
            veg_non.setSelection(0);
            Occupation.setSelection(0);
            Exercise.setSelection(0);
        }
        if (v == Calculate) {
           /* S_dob = dob.getText().toString();
            S_ft = feet.getText().toString();
            S_inc = inch.getText().toString();
            S_weight = weight.getText().toString();*/

            if (S_gender.equalsIgnoreCase("female")) {
                S_pre_pregnancy_weight = pre_pregnancy_weight.getText().toString();
                S_first_day_last_mensrual_period = last_mc.getText().toString();
            } else {
                S_pre_pregnancy_weight = "null";
                S_first_day_last_mensrual_period = "null";
            }


            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();


            pd = new ProgressDialog(Ideal_Diat_plan_Adult.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();

            DataConnection();
        }
    }

    void datepicker(final EditText et) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(Ideal_Diat_plan_Adult.this, R.style.DialogTheme,
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

    }

    void processIdealPlanAdult(String response)
    {
        JSONObject jobj = null;
        try {
            jobj = new JSONObject(response);
            String status;
            status = jobj.getString("status");
            String message = jobj.getString("message");

            if (status.equals("1")) {
              //  CalorieCalculator.writeIdealDietPlanAdult(getApplicationContext(),response);

                JSONObject joo = jobj.getJSONObject("ideal_diet_plan_for_adult");
                JSONObject jo = joo.getJSONObject("ideal_diet_plan_for_adult");

                String weight_range = jo.getString("Your weight loss target (over 3-6 months)");
                String Weight_Category = jo.getString("Your Weight Category is");
//                                String target = jo.getString("Your target weightloss should be");
                String bmi = jo.getString("Your BMI is");
                String ActivityLevel = jo.getString("Your Activity Level is");
                String RDCA = jo.getString("Your RDCA (Recommended Daily Calorie Allowance)");
                String DailyCalorieAllowance = jo.getString("Your Recommended Daily Calorie Allowance rounded to nearest 200 cal multiple");

                String Category = jo.getString("Category");
                String Gender = jo.getString("Gender");
                String Age = jo.getString("Age");
                String Height = jo.getString("Height");
                String Pregnancy = jo.getString("Pregnancy");
                String Trimester = jo.getString("Trimester");
                String PrePregnancyWeight = jo.getString("Pre Pregnancy Weight");
                String BreastFeeding = jo.getString("Breast Feeding");
                String ChildAge = jo.getString("Child Age");
                String Weight = jo.getString("Weight");

                new AllSharedPrefrences(Ideal_Diat_plan_Adult.this).IDEAL_Diet_Plan(bmi, weight_range, Weight_Category, "", ActivityLevel, RDCA, DailyCalorieAllowance,
                        Category, Gender, Age, Height, Pregnancy, Trimester, PrePregnancyWeight, BreastFeeding, ChildAge, Weight);


                JSONObject jdite_plan = jo.getJSONObject("YOUR BASIC DIET PLAN IS");
                JSONObject GrainsCereals = jdite_plan.getJSONObject("Grains/cereals");
                String GrainsCereals_Servings_equals = GrainsCereals.getString("1 Servings equals");
                JSONObject GrainsCereals_NumberofServings = GrainsCereals.getJSONObject("Number of Servings");
                String GrainsCereals_BasicIndianDiet = GrainsCereals_NumberofServings.getString("Basic Indian Diet");
                String GrainsCereals_OptimizedIndiandiet = GrainsCereals_NumberofServings.getString("Optimized Indian diet");
                String GrainsCereals_IdealDietForPeopleWithHealthRisks = GrainsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject GrainsCereals_Importantconsiderations = GrainsCereals.getJSONObject("Important considerations");
                String GrainsCereals_ToknowmoregotoFWItips = GrainsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Grains/cereals", GrainsCereals_Servings_equals, GrainsCereals_BasicIndianDiet, GrainsCereals_OptimizedIndiandiet, GrainsCereals_IdealDietForPeopleWithHealthRisks, GrainsCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.fish));


                JSONObject Dalsmeatsday = jdite_plan.getJSONObject("Dals/meats/day");
                String DalCereals_Servings_equals = Dalsmeatsday.getString("1 Servings equals");
                JSONObject DalCereals_NumberofServings = Dalsmeatsday.getJSONObject("Number of Servings");
                String DalCereals_BasicIndianDiet = DalCereals_NumberofServings.getString("Basic Indian Diet");
                String DalCereals_OptimizedIndiandiet = DalCereals_NumberofServings.getString("Optimized Indian diet");
                String DalCereals_IdealDietForPeopleWithHealthRisks = DalCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject DalCereals_Importantconsiderations = Dalsmeatsday.getJSONObject("Important considerations");
                String DalCereals_ToknowmoregotoFWItips = DalCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Dals/meats/day", DalCereals_Servings_equals, DalCereals_BasicIndianDiet, DalCereals_OptimizedIndiandiet, DalCereals_IdealDietForPeopleWithHealthRisks, DalCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.fish));

                JSONObject MaxmEggsweek = jdite_plan.getJSONObject("Maxm. Eggs/week");
                String MaxmCereals_Servings_equals = MaxmEggsweek.getString("1 Servings equals");
                JSONObject MaxmCereals_NumberofServings = MaxmEggsweek.getJSONObject("Number of Servings");
                String MaxmCereals_BasicIndianDiet = MaxmCereals_NumberofServings.getString("Basic Indian Diet");
                String MaxmCereals_OptimizedIndiandiet = MaxmCereals_NumberofServings.getString("Optimized Indian diet");
                String MaxmCereals_IdealDietForPeopleWithHealthRisks = MaxmCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MaxmCereals_Importantconsiderations = MaxmEggsweek.getJSONObject("Important considerations");
                String MaxmCereals_ToknowmoregotoFWItips = MaxmCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Maxm. Eggs/week", MaxmCereals_Servings_equals, MaxmCereals_BasicIndianDiet, MaxmCereals_OptimizedIndiandiet, MaxmCereals_IdealDietForPeopleWithHealthRisks, MaxmCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.fish));


                JSONObject MinmFishseafoodweek = jdite_plan.getJSONObject("Minm. Fish/seafood/week");
                String MinmCereals_Servings_equals = MinmFishseafoodweek.getString("1 Servings equals");
                JSONObject MinmCereals_NumberofServings = MinmFishseafoodweek.getJSONObject("Number of Servings");
                String MinmCereals_BasicIndianDiet = MinmCereals_NumberofServings.getString("Basic Indian Diet");
                String MinmCereals_OptimizedIndiandiet = MinmCereals_NumberofServings.getString("Optimized Indian diet");
                String MinmCereals_IdealDietForPeopleWithHealthRisks = MinmCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MinmCereals_Importantconsiderations = MinmFishseafoodweek.getJSONObject("Important considerations");
                String MinmCereals_ToknowmoregotoFWItips = MinmCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Minm. Fish/seafood/week", MinmCereals_Servings_equals, MinmCereals_BasicIndianDiet, MinmCereals_OptimizedIndiandiet, MinmCereals_IdealDietForPeopleWithHealthRisks, MinmCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.fish));


                JSONObject nonvegetarians = jdite_plan.getJSONObject("Soya products/week for non-vegetarians");
                String nvCereals_Servings_equals = nonvegetarians.getString("1 Servings equals");
                JSONObject nvCereals_NumberofServings = nonvegetarians.getJSONObject("Number of Servings");
                String nvCereals_BasicIndianDiet = nvCereals_NumberofServings.getString("Basic Indian Diet");
                String nvCereals_OptimizedIndiandiet = nvCereals_NumberofServings.getString("Optimized Indian diet");
                String nvCereals_IdealDietForPeopleWithHealthRisks = nvCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject nvCereals_Importantconsiderations = nonvegetarians.getJSONObject("Important considerations");
                String nvCereals_ToknowmoregotoFWItips = nvCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Soya products/week for non-vegetarians", nvCereals_Servings_equals, nvCereals_BasicIndianDiet, nvCereals_OptimizedIndiandiet, nvCereals_IdealDietForPeopleWithHealthRisks, nvCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.fish));


                JSONObject Soyagetarians = jdite_plan.getJSONObject("Min. Soya products/week");
                String SoyaCereals_Servings_equals = Soyagetarians.getString("1 Servings equals");
                JSONObject SoyaCereals_NumberofServings =Soyagetarians.getJSONObject("Number of Servings");
                String SoyaCereals_BasicIndianDiet = SoyaCereals_NumberofServings.getString("Basic Indian Diet");
                String SoyaCereals_OptimizedIndiandiet = SoyaCereals_NumberofServings.getString("Optimized Indian diet");
                String SoyaCereals_IdealDietForPeopleWithHealthRisks = SoyaCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject SoyaCereals_Importantconsiderations = Soyagetarians.getJSONObject("Important considerations");
                String SoyaCereals_ToknowmoregotoFWItips = SoyaCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Min. Soya products/week", SoyaCereals_Servings_equals, SoyaCereals_BasicIndianDiet, SoyaCereals_OptimizedIndiandiet, SoyaCereals_IdealDietForPeopleWithHealthRisks, SoyaCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.fish));


                JSONObject Nuts = jdite_plan.getJSONObject("Nuts/seeds/day");
                String NutsCereals_Servings_equals = Nuts.getString("1 Servings equals");
                JSONObject NutsCereals_NumberofServings = Nuts.getJSONObject("Number of Servings");
                String NutsaCereals_BasicIndianDiet = NutsCereals_NumberofServings.getString("Basic Indian Diet");
                String NutsCereals_OptimizedIndiandiet = NutsCereals_NumberofServings.getString("Optimized Indian diet");
                String NutsCereals_IdealDietForPeopleWithHealthRisks = NutsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject NutsCereals_Importantconsiderations = Nuts.getJSONObject("Important considerations");
                String NutsCereals_ToknowmoregotoFWItips = NutsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Nuts/seeds/day", NutsCereals_Servings_equals, NutsaCereals_BasicIndianDiet, NutsCereals_OptimizedIndiandiet, NutsCereals_IdealDietForPeopleWithHealthRisks, NutsCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.fish));

                JSONObject Milk = jdite_plan.getJSONObject("Milk/curd");
                String MilkCereals_Servings_equals = Milk.getString("1 Servings equals");
                JSONObject MilkCereals_NumberofServings = Milk.getJSONObject("Number of Servings");
                String MilkCereals_BasicIndianDiet = MilkCereals_NumberofServings.getString("Basic Indian Diet");
                String MilkCereals_OptimizedIndiandiet = MilkCereals_NumberofServings.getString("Optimized Indian diet");
                String MilkCereals_IdealDietForPeopleWithHealthRisks = MilkCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MilkCereals_Importantconsiderations = Milk.getJSONObject("Important considerations");
                String MilksCereals_ToknowmoregotoFWItips = MilkCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Milk/curd", MilkCereals_Servings_equals, MilkCereals_BasicIndianDiet, MilkCereals_OptimizedIndiandiet, MilkCereals_IdealDietForPeopleWithHealthRisks, MilksCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.fish));


                JSONObject Vegetables = jdite_plan.getJSONObject("Vegetables");
                String vegCereals_Servings_equals = Vegetables.getString("1 Servings equals");
                JSONObject vegCereals_NumberofServings = Vegetables.getJSONObject("Number of Servings");
                String vegCereals_BasicIndianDiet = vegCereals_NumberofServings.getString("Basic Indian Diet");
                String vegCereals_OptimizedIndiandiet = vegCereals_NumberofServings.getString("Optimized Indian diet");
                String vegCereals_IdealDietForPeopleWithHealthRisks = vegCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject vegCereals_Importantconsiderations = Vegetables.getJSONObject("Important considerations");
                String vegCereals_ToknowmoregotoFWItips = vegCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Vegetables", vegCereals_Servings_equals, vegCereals_BasicIndianDiet, vegCereals_OptimizedIndiandiet, vegCereals_IdealDietForPeopleWithHealthRisks, vegCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.fish));


                JSONObject Fruits = jdite_plan.getJSONObject("Fruits");
                String FruitsCereals_Servings_equals = Fruits.getString("1 Servings equals");
                JSONObject FruitsCereals_NumberofServings = Fruits.getJSONObject("Number of Servings");
                String FruitsCereals_BasicIndianDiet = FruitsCereals_NumberofServings.getString("Basic Indian Diet");
                String FruitsCereals_OptimizedIndiandiet = FruitsCereals_NumberofServings.getString("Optimized Indian diet");
                String FruitsCereals_IdealDietForPeopleWithHealthRisks = FruitsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject FruitsCereals_Importantconsiderations = Fruits.getJSONObject("Important considerations");
                String FruitsCereals_ToknowmoregotoFWItips = FruitsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Fruits", FruitsCereals_Servings_equals, FruitsCereals_BasicIndianDiet, FruitsCereals_OptimizedIndiandiet, FruitsCereals_IdealDietForPeopleWithHealthRisks, FruitsCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.fish));


                JSONObject Oils = jdite_plan.getJSONObject("Oils");
                String OilsCereals_Servings_equals = Oils.getString("1 Servings equals");
                JSONObject OilsCereals_NumberofServings = Oils.getJSONObject("Number of Servings");
                String OilsCereals_BasicIndianDiet = OilsCereals_NumberofServings.getString("Basic Indian Diet");
                String OilsCereals_OptimizedIndiandiet = OilsCereals_NumberofServings.getString("Optimized Indian diet");
                String OilsCereals_IdealDietForPeopleWithHealthRisks = OilsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject OilsCereals_Importantconsiderations = Oils.getJSONObject("Important considerations");
                String OilsCereals_ToknowmoregotoFWItips = OilsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                datalist.add(new RecycleModel("Oils", OilsCereals_Servings_equals, OilsCereals_BasicIndianDiet, OilsCereals_OptimizedIndiandiet, OilsCereals_IdealDietForPeopleWithHealthRisks, OilsCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.fish));


                new AllSharedPrefrences(Ideal_Diat_plan_Adult.this).IDEAL_Diet_Plan_Data(GrainsCereals_Servings_equals, GrainsCereals_BasicIndianDiet, GrainsCereals_OptimizedIndiandiet, GrainsCereals_IdealDietForPeopleWithHealthRisks, GrainsCereals_ToknowmoregotoFWItips, DalCereals_Servings_equals, DalCereals_BasicIndianDiet, DalCereals_OptimizedIndiandiet, DalCereals_IdealDietForPeopleWithHealthRisks, DalCereals_ToknowmoregotoFWItips, MaxmCereals_Servings_equals, MaxmCereals_BasicIndianDiet, MaxmCereals_OptimizedIndiandiet, MaxmCereals_IdealDietForPeopleWithHealthRisks, MaxmCereals_ToknowmoregotoFWItips
                        , MinmCereals_Servings_equals, MinmCereals_BasicIndianDiet, MinmCereals_OptimizedIndiandiet, MinmCereals_IdealDietForPeopleWithHealthRisks, MinmCereals_ToknowmoregotoFWItips, nvCereals_Servings_equals, nvCereals_BasicIndianDiet, nvCereals_OptimizedIndiandiet, nvCereals_IdealDietForPeopleWithHealthRisks, nvCereals_ToknowmoregotoFWItips, SoyaCereals_Servings_equals, SoyaCereals_BasicIndianDiet, SoyaCereals_OptimizedIndiandiet, SoyaCereals_IdealDietForPeopleWithHealthRisks, SoyaCereals_ToknowmoregotoFWItips, NutsCereals_Servings_equals, NutsaCereals_BasicIndianDiet, NutsCereals_OptimizedIndiandiet, NutsCereals_IdealDietForPeopleWithHealthRisks, NutsCereals_ToknowmoregotoFWItips,
                        MilkCereals_Servings_equals, MilkCereals_BasicIndianDiet, MilkCereals_OptimizedIndiandiet, MilkCereals_IdealDietForPeopleWithHealthRisks, MilksCereals_ToknowmoregotoFWItips, vegCereals_Servings_equals, vegCereals_BasicIndianDiet, vegCereals_OptimizedIndiandiet, vegCereals_IdealDietForPeopleWithHealthRisks, vegCereals_ToknowmoregotoFWItips, FruitsCereals_Servings_equals, FruitsCereals_BasicIndianDiet, FruitsCereals_OptimizedIndiandiet, FruitsCereals_IdealDietForPeopleWithHealthRisks, FruitsCereals_ToknowmoregotoFWItips, OilsCereals_Servings_equals, OilsCereals_BasicIndianDiet, OilsCereals_OptimizedIndiandiet, OilsCereals_IdealDietForPeopleWithHealthRisks, OilsCereals_ToknowmoregotoFWItips);


                Intent in = new Intent(getApplicationContext(), Ideal_Diat_plan_Adult_result.class);
                in.putExtra("data", datalist);
                startActivity(in);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
                //       new MyIntent(Ideal_Diat_plan_Adult.this, BMI_Result.class, R.anim.enter, R.anim.exit);

            } else {

                Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }





    }
    void DataConnection() {

        if(CalorieCalculator.getIdealDietPlanAdult(getApplicationContext()).equals("defaultValue")!=true && Edit_profile_Activity.profileEdited==false)
        {

            processIdealPlanAdult(CalorieCalculator.getIdealDietPlanAdult((getApplicationContext())));
            return;
        }

        if(Edit_profile_Activity.profileEdited==true)
            Edit_profile_Activity.profileEdited=false;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + ideal_diet_plan_for_adult + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("responce ", response);

                        pd.dismiss();
                        try {

                            JSONObject jobj = new JSONObject(response);

                            String status = jobj.getString("status");
                            String message = jobj.getString("message");

                            if (status.equals("1")) {
                                CalorieCalculator.writeIdealDietPlanAdult(getApplicationContext(),response);
                                processIdealPlanAdult(response);

                                //       new MyIntent(Ideal_Diat_plan_Adult.this, BMI_Result.class, R.anim.enter, R.anim.exit);

                            } else {

                                Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Log.e("errrrr= ", e + "");
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
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("sex", S_gender);
                params.put("age", S_age + "");
                params.put("Weight", S_weight + "");
                params.put("want_diet_for_weight_loss", S_want);
                params.put("dob", S_dob);
                params.put("ft", S_ft);
                params.put("inc", S_inc);
                params.put("exercise", getActivity1Number(S_Exercise));
                params.put("occupation", getActivity2Number(S_Occupation));
                params.put("veg_nonveg", getVegNumber(S_veg_non));
                params.put("is_Pregnant", S_is_Pregnant);
                params.put("is_Breast_Feeding", S_is_Breast_Feeding);
                params.put("first_day_last_mensrual_period", S_first_day_last_mensrual_period);
                params.put("pre_pregnancy_weight", S_pre_pregnancy_weight);
                params.put("child_age", S_child_age);
                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }


    void GetPeofile() {

        final ProgressDialog pd = new ProgressDialog(Ideal_Diat_plan_Adult.this);
        pd.setMessage("please wait...");
        pd.setCancelable(false);
        pd.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + getProfile + new RandomNumber().randno(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");
                        pd.dismiss();
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");

                            if (status.equals("1")) {
                                JSONObject object = jobj.getJSONObject("profile_info");
                                String do_you_want_a_diet_for_weight_loss = object.getString("do_you_want_a_diet_for_weight_loss");
                                String activity_level_one = object.getString("activity_level_one");
                                String activity_level_two = object.getString("activity_level_two");
                                String food_habits = object.getString("food_habits");
                                String is_pregnent = object.getString("is_pregnent");
                                String breast_feeding = object.getString("breast_feeding");
                                String child_age = object.getString("child_age");

                                try {
                                    if (do_you_want_a_diet_for_weight_loss.equalsIgnoreCase("no")) {
                                        want_weight_loss.setSelection(1);
                                    } else if (do_you_want_a_diet_for_weight_loss.equalsIgnoreCase("yes")) {
                                        want_weight_loss.setSelection(2);
                                    }
                                } catch (Exception e) {

                                }

                                if (S_gender.equalsIgnoreCase("female")) {
                                    try {
                                        if (is_pregnent.equalsIgnoreCase("no")) {
                                            Are_you_Pregnant.setSelection(0);
                                        } else if (is_pregnent.equalsIgnoreCase("yes")) {
                                            Are_you_Pregnant.setSelection(1);
                                        }
                                    } catch (Exception e) {

                                    }
                                    try {

                                        if (breast_feeding.equalsIgnoreCase("no")) {
                                            Breast_Feeding.setSelection(0);
                                        } else if (breast_feeding.equalsIgnoreCase("yes")) {
                                            Breast_Feeding.setSelection(1);
                                        }
                                    } catch (Exception e) {

                                    }
                                }
//      ------------------------------------------------

                                try {
                                    if (food_habits.equalsIgnoreCase("Eggetarian")) {
                                        want_weight_loss.setSelection(0);
                                    } else if (food_habits.equalsIgnoreCase("Vegetarian")) {
                                        want_weight_loss.setSelection(1);
                                    } else if (food_habits.equalsIgnoreCase("Non Vegetarian")) {
                                        want_weight_loss.setSelection(2);
                                    }
                                } catch (Exception e) {

                                }
                                try {


                                    if (activity_level_one.equalsIgnoreCase("No regular exercise")) {
                                        Exercise.setSelection(0);
                                    } else if (activity_level_one.equalsIgnoreCase("Exercise>20 min,1-3 days/week")) {
                                        Exercise.setSelection(1);
                                    } else if (activity_level_one.equalsIgnoreCase("Exercise 30-60 min,3-4 days/week")) {
                                        Exercise.setSelection(2);
                                    } else if (activity_level_one.equalsIgnoreCase("Exercise>60 min, 5-7 days/week")) {
                                        Exercise.setSelection(3);
                                    } else if (activity_level_one.equalsIgnoreCase("Athlete or very active")) {
                                        Exercise.setSelection(4);
                                    }
                                } catch (Exception e) {

                                }
                                try {
                                    if (activity_level_two.equalsIgnoreCase("Sitting /desk jobs")) {
                                        Occupation.setSelection(0);
                                    } else if (activity_level_two.equalsIgnoreCase("Standing for long times")) {
                                        Occupation.setSelection(1);
                                    } else if (activity_level_two.equalsIgnoreCase("Active e.g. waiter")) {
                                        Occupation.setSelection(2);
                                    } else if (activity_level_two.equalsIgnoreCase("Heavy activity, e.g. carpenter")) {
                                        Occupation.setSelection(3);
                                    } else if (activity_level_two.equalsIgnoreCase("Strenuous e.g.Labourers")) {
                                        Occupation.setSelection(4);
                                    }
                                } catch (Exception e) {

                                }

                                //     Exercise, Occupation, veg_non, want_weight_loss, Are_you_Pregnant, Breast_Feeding, child_age

                            } else {
                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");
                            }
                        } catch (Exception e) {
                            pd.dismiss();
                            Log.e("error", e.toString());
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
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("user_id", user_id);

                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }


}
