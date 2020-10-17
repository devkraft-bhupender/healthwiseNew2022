package com.hwi.health.Activitys.Plans;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hwi.health.Activitys.AllTests.CUSTOMISED_DIET_PLAN.Major_Meals_Page1;
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
import com.hwi.health.Activitys.Profile.First.Smoking;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.IdealDietPlanDBModel;
import com.hwi.health.Models.RecycleModel;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.AllTests.BMI.BMI_Result;
import com.hwi.health.Activitys.AllTests.IdealDiat.Ideal_Diat_plan_Adult_result2;
import com.hwi.health.Activitys.AllTests.IdealDiat.Ideal_Diat_plan_Child_result2;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.Calculator_PKG.Alcohol_Tracker;
import com.hwi.health.Activitys.More.Calculator_PKG.Eat_Out_Snacker;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.Excercize_plans.ExcercisePlan_Home;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.edit_profile.Edit_profile_Activity;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static com.hwi.health.InterFaces.Util.getActivity1Number;
import static com.hwi.health.InterFaces.Util.getActivity2Number;

public class PlansActivity extends AppCompatActivity implements View.OnClickListener, BaseUrl {

    LinearLayout exerciesplan, dietplan, weighttplan, Treatmentplan, bpplan, sugarplan;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String randnoo, gender, height, weight, age, user_id;
    String feet = "0", inch = "0", S_dob, strDate, get_dob, tar_weight, weight_loss;
    String key_diet, key_bmi,cust_key;
    ProgressDialog pd;
    Dialog dialog;
    String S_Exercise, S_veg_non, S_want, S_Occupation, S_is_Pregnant, S_is_Breast_Feeding, S_first_day_last_mensrual_period, S_pre_pregnancy_weight, S_child_age;
    int number;
    int YEAR, MONTH, DAY;
    LinearLayout eat_out_plan, alchohal_lay;
    ImageView diet_img, bmi_img,img_treat,img_exe;
    TextView diet_text, bmi_text, text_exe,text_Treatmentplan,eat_text,alcohal_text;;
    EditText dob;
    ArrayList<RecycleModel> datalist = new ArrayList<>();
    ArrayList<RecycleModel> datalist2 = new ArrayList<>();
    private int mYear, mMonth, mDay, mHour, mMinute;
    ProductController_For_All controller = new ProductController_For_All(PlansActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("PLAN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        /*//back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
*/
        exerciesplan = (LinearLayout) findViewById(R.id.exerciesplan);
        dietplan = (LinearLayout) findViewById(R.id.dietplan);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        weighttplan = (LinearLayout) findViewById(R.id.weighttplan);
        Treatmentplan = (LinearLayout) findViewById(R.id.Treatment);
        bpplan = (LinearLayout) findViewById(R.id.bpplan);
        sugarplan = (LinearLayout) findViewById(R.id.sugarplan);
        eat_out_plan = (LinearLayout) findViewById(R.id.eat_out_plan);
        alchohal_lay = (LinearLayout) findViewById(R.id.alchohal_lay);

        bmi_text = (TextView) findViewById(R.id.bmi_text);
        eat_text = (TextView) findViewById(R.id.text_eatout);
        alcohal_text = (TextView) findViewById(R.id.text_alcohol);
        text_Treatmentplan = (TextView) findViewById(R.id.text_Treatmentplan);
        diet_text = (TextView) findViewById(R.id.diet_text);
        text_exe = (TextView) findViewById(R.id.text_exe);
        diet_img = (ImageView) findViewById(R.id.diet_img);
        bmi_img = (ImageView) findViewById(R.id.bmi_img);
        img_exe = (ImageView) findViewById(R.id.img_exe);
        img_treat = (ImageView) findViewById(R.id.img_treat);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        exerciesplan.setOnClickListener(this);
        dietplan.setOnClickListener(this);
        weighttplan.setOnClickListener(this);
        Treatmentplan.setOnClickListener(this);
        bpplan.setOnClickListener(this);
        sugarplan.setOnClickListener(this);
        eat_out_plan.setOnClickListener(this);
        alchohal_lay.setOnClickListener(this);


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
            key_diet = sp.getString("key_diet", "");
            key_bmi = sp.getString("key_bmi", "");

            cust_key = sp.getString("cust_key", "");
            tar_weight = sp.getString("weight_range", "");
            weight_loss = sp.getString("weight_target", "");
            Log.e("dhsshdfkdf", tar_weight + "..." + weight_loss);
            number = Integer.parseInt(age);
            Log.e("dhsshdfkdf", S_veg_non);
            Log.e("dobbbbbb", get_dob);
            String[] separated = height.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet = f;
            inch = i;
            Log.e("erro == =", key_diet + "..."+key_bmi);


            try {
                if (key_diet.equals("1")) {
                    dietplan.setBackgroundResource(R.drawable.green_back);
                    eat_out_plan.setBackgroundResource(R.drawable.green_back);
                    alchohal_lay.setBackgroundResource(R.drawable.green_back);
                    exerciesplan.setBackgroundResource(R.drawable.green_back);
                    Treatmentplan.setBackgroundResource(R.drawable.green_back);
                    eat_text.setTextColor(getResources().getColor(R.color.white));
                    alcohal_text.setTextColor(getResources().getColor(R.color.white));
                    diet_text.setTextColor(getResources().getColor(R.color.white));
                    text_exe.setTextColor(getResources().getColor(R.color.white));
                    text_Treatmentplan.setTextColor(getResources().getColor(R.color.white));
                    diet_img.setImageResource(R.drawable.plan_image_one);
                    img_exe.setImageResource(R.drawable.plan_image_three);
                    img_treat.setImageResource(R.drawable.plan_image_two);

                }
                if (key_bmi.equals("1")) {
                    weighttplan.setBackgroundResource(R.drawable.green_back);
                    bmi_img.setImageResource(R.drawable.plan_image_four);
                    bmi_text.setTextColor(getResources().getColor(R.color.white));
                }
            } catch (Exception e) {
                Log.e("erro == =", e + "");
            }
        } catch (Exception e) {
            Log.e("SP erro r =", e + "");
        }

        new GetPercentages().execute();

    }

    class GetPercentages extends AsyncTask<String, Void, String> {
        String result;
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(PlansActivity.this);
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

    void AlertDialogStatus() {
        final Dialog dialogstatus = new Dialog(PlansActivity.this, R.style.CustomDialog);
        dialogstatus.setContentView(R.layout.res);
        dialogstatus.setCancelable(false);
        TextView test1 = (TextView) dialogstatus.findViewById(R.id.test1);
        TextView test2 = (TextView) dialogstatus.findViewById(R.id.test2);
        Button close = (Button) dialogstatus.findViewById(R.id.close);

        test1.setText("Your basic profile is not complete.");
        test2.setText("Please complete basic profile first.");
        close.setText("Proceed");


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogstatus.dismiss();

                SharedPreferences sp = getSharedPreferences("EditPro",MODE_PRIVATE);
                String goKey = sp.getString("gokey","1");

                if (goKey.equals("1")){
                    new MyIntent(PlansActivity.this, Profile_Step1.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("2")){
                    new MyIntent(PlansActivity.this, Profile_Step2.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("3")){
                    new MyIntent(PlansActivity.this, Profile_Step3.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("4")){
                    new MyIntent(PlansActivity.this, Profile_Menstrul_Cycle_4.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("5")){
                    new MyIntent(PlansActivity.this, Profile_Breast_Feeding_5.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("6")){
                    new MyIntent(PlansActivity.this, Profile_Child_Age_6.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("7")){
                    new MyIntent(PlansActivity.this, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("8")){
                    new MyIntent(PlansActivity.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("9")){
                    new MyIntent(PlansActivity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("10")){
                    new MyIntent(PlansActivity.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("11")){
                    new MyIntent(PlansActivity.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("12")){
                    new MyIntent(PlansActivity.this, Smoking.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("13")){
                    new MyIntent(PlansActivity.this, Alcohol.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("14")){
                    new MyIntent(PlansActivity.this, Edit_profile_Activity.class, R.anim.enter, R.anim.exit);
                }
            }
        });



        dialogstatus.show();
    }



    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(PlansActivity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(PlansActivity.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(PlansActivity.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            // new MyIntent(PlansActivity.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(PlansActivity.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == Treatmentplan) {
            new MyIntent(PlansActivity.this, Treatmentplan.class, R.anim.enter, R.anim.exit);
        }
        if (view == sugarplan) {
            new MyIntent(PlansActivity.this, SugarPlan_activity.class, R.anim.enter, R.anim.exit);
        }
        if (view == bpplan) {
            new MyIntent(PlansActivity.this, BPLogPlan_activity.class, R.anim.enter, R.anim.exit);
        }
        if (view == dietplan) {
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            if (number > 18) {

                Toast.makeText(this, "adult", Toast.LENGTH_SHORT).show();
                if (gender.equals("male")) {
                    if (S_want.equals("") || S_want == null || S_Exercise.equals("") || S_Exercise == null || S_Occupation.equals("") || S_Occupation == null || S_veg_non.equals("") || S_veg_non == null || gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                            get_dob.equals("") || get_dob == null) {
                        Toast.makeText(this, "Please fill your basic details", Toast.LENGTH_SHORT).show();
                        AlertDialogStatus("B");
                    } else {
                        pd = new ProgressDialog(PlansActivity.this);
                        pd.setMessage("Please Wait...");
                        pd.setCancelable(false);
                        pd.show();
                        DataConnectionAdult();
                    }

                } else if (gender.equals("female"))
                    if (S_is_Pregnant.equals("yes")) {
                        if (S_want.equals("") || S_want == null || S_Exercise.equals("") || S_Exercise == null || S_Occupation.equals("") || S_Occupation == null || S_veg_non.equals("") || S_veg_non == null || S_is_Pregnant.equals("") || S_is_Pregnant == null || S_child_age.equals("") || S_child_age == null || S_pre_pregnancy_weight.equals("") || S_pre_pregnancy_weight == null || S_first_day_last_mensrual_period.equals("") || S_first_day_last_mensrual_period == null || gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                                get_dob.equals("") || get_dob == null) {
                            Toast.makeText(this, "Please fill your basic details", Toast.LENGTH_SHORT).show();
                            AlertDialogStatus("B");
                        } else {
                            pd = new ProgressDialog(PlansActivity.this);
                            pd.setMessage("Please Wait...");
                            pd.setCancelable(false);
                            pd.show();
                            DataConnectionAdult();
                        }
                    } else if (S_is_Pregnant.equals("no")) {
                        if (S_want.equals("") || S_want == null || S_Exercise.equals("") || S_Exercise == null || S_Occupation.equals("") || S_Occupation == null || S_veg_non.equals("") || S_veg_non == null || S_is_Pregnant.equals("") || S_is_Pregnant == null || S_is_Breast_Feeding.equals("") || S_is_Breast_Feeding == null || gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                                get_dob.equals("") || get_dob == null) {
                            Toast.makeText(this, "Please fill your basic details", Toast.LENGTH_SHORT).show();
                            AlertDialogStatus("B");
                        } else {
                            pd = new ProgressDialog(PlansActivity.this);
                            pd.setMessage("Please Wait...");
                            pd.setCancelable(false);
                            pd.show();
                            DataConnectionAdult();
                        }
                    }

            } else {
                Toast.makeText(this, "child", Toast.LENGTH_SHORT).show();

                if (S_Exercise.equals("") || S_Exercise == null || S_veg_non.equals("") || S_veg_non == null || gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                        get_dob.equals("") || get_dob == null) {
                    Toast.makeText(this, "Please fill your basic details", Toast.LENGTH_SHORT).show();
                    AlertDialogStatus("B");
                } else {
                    pd = new ProgressDialog(PlansActivity.this);
                    pd.setMessage("Please Wait...");
                    pd.setCancelable(false);
                    pd.show();
                    DataConnectionChild();
                }
            }
            // new MyIntent(PlansActivity.this, PlansCategory.class, R.anim.enter, R.anim.exit);
        }
        if (view == exerciesplan) {
           /* SharedPreferences sp = getSharedPreferences("Excercise_plan",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("logKey","plan");
            editor.commit();*/
            new MyIntent(PlansActivity.this, ExcercisePlan_Home.class, R.anim.enter, R.anim.exit);
        }
        if (view == weighttplan) {
            if (gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                    get_dob.equals("") || get_dob == null || get_dob.equals("0000-00-00")) {
                Toast.makeText(this, "Please fill your basic details", Toast.LENGTH_SHORT).show();
                AlertDialogStatus("B");
            } else {
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();
                DataConnection();
            }
        }
        if (view == eat_out_plan) {
            new MyIntent(PlansActivity.this, Eat_Out_Snacker.class, R.anim.enter, R.anim.exit);
            SharedPreferences sp = getSharedPreferences("eat_key", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Eatkey", "plan_mng_Key");
            editor.commit();

        }
        if (view == alchohal_lay) {
            new MyIntent(PlansActivity.this, Alcohol_Tracker.class, R.anim.enter, R.anim.exit);
            SharedPreferences sp = getSharedPreferences("eat_key", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Eatkey", "plan_mng_Key");
            editor.commit();
        }
    }

    void AlertDialogStatus(final String type) {
        //   Toast.makeText(this, "xdl;kj", Toast.LENGTH_SHORT).show();
        final Dialog  dialogstatus = new Dialog(PlansActivity.this, R.style.CustomDialog);
        dialogstatus.setContentView(R.layout.res);
        dialogstatus.setCancelable(false);

        Button close = (Button) dialogstatus.findViewById(R.id.close);
        Button Complete = (Button) dialogstatus.findViewById(R.id.Complete);
        TextView test1 = (TextView)dialogstatus.findViewById(R.id.test1);
        TextView test2 = (TextView)dialogstatus.findViewById(R.id.test2);

        if (type.equals("C")){
            test1.setText("Your Customised Diet Not Completed");
            test2.setText("Please Complete Your Customised Diet Plan First");
        }else {
            test1.setText("Your Basic Profile Not Completed");
            test2.setText("Please Complete Your Profile First");
        }


        Complete.setVisibility(View.VISIBLE);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogstatus.dismiss();
            }
        });

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogstatus.dismiss();
                if (type.equals("C")){
                    new MyIntent(PlansActivity.this, Major_Meals_Page1.class, R.anim.enter, R.anim.exit);
                }else {
                    new MyIntent(PlansActivity.this, Edit_profile_Activity.class, R.anim.enter, R.anim.exit);
                }
            }
        });


        dialogstatus.show();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(PlansActivity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
    }

    void processIdealDietChildPlan2(String response)
    {
        try {
            JSONObject jobj = new JSONObject(response);
            String status = jobj.getString("status");
            String message = jobj.getString("message");

            if (status.equals("1")) {
                new AllSharedPrefrences(PlansActivity.this).keydiet("1");
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
                // datalist.add(new RecycleModel("Grains/cereals", GrainsCereals_Servings_equals, GrainsCereals_BasicIndianDiet, GrainsCereals_OptimizedIndiandiet, GrainsCereals_IdealDietForPeopleWithHealthRisks, GrainsCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.grains));

                if (!GrainsCereals_BasicIndianDiet.equals("0") || !GrainsCereals_OptimizedIndiandiet.equals("0")||!GrainsCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist.add(new RecycleModel("Grains/cereals", GrainsCereals_Servings_equals, GrainsCereals_BasicIndianDiet, GrainsCereals_OptimizedIndiandiet, GrainsCereals_IdealDietForPeopleWithHealthRisks, GrainsCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.grains));

                }


                JSONObject Dalsmeatsday = jdite_plan.getJSONObject("Dals/meats/day");
                String DalCereals_Servings_equals = Dalsmeatsday.getString("1 Servings equals");
                JSONObject DalCereals_NumberofServings = Dalsmeatsday.getJSONObject("Number of Servings");
                String DalCereals_BasicIndianDiet = DalCereals_NumberofServings.getString("Basic Indian Diet");
                String DalCereals_OptimizedIndiandiet = DalCereals_NumberofServings.getString("Optimized Indian diet");
                String DalCereals_IdealDietForPeopleWithHealthRisks = DalCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject DalCereals_Importantconsiderations = Dalsmeatsday.getJSONObject("Important considerations");
                String DalCereals_ToknowmoregotoFWItips = DalCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist.add(new RecycleModel("Dals/meats/day", DalCereals_Servings_equals, DalCereals_BasicIndianDiet, DalCereals_OptimizedIndiandiet, DalCereals_IdealDietForPeopleWithHealthRisks, DalCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.dals));

                if (!DalCereals_BasicIndianDiet.equals("0") || !DalCereals_OptimizedIndiandiet.equals("0") || !DalCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist.add(new RecycleModel("Dals/meats/day", DalCereals_Servings_equals, DalCereals_BasicIndianDiet, DalCereals_OptimizedIndiandiet, DalCereals_IdealDietForPeopleWithHealthRisks, DalCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.dals));

                }

                JSONObject Nuts = jdite_plan.getJSONObject("Nuts/seeds/day");
                String NutsCereals_Servings_equals = Nuts.getString("1 Servings equals");
                JSONObject NutsCereals_NumberofServings = Nuts.getJSONObject("Number of Servings");
                String NutsaCereals_BasicIndianDiet = NutsCereals_NumberofServings.getString("Basic Indian Diet");
                String NutsCereals_OptimizedIndiandiet = NutsCereals_NumberofServings.getString("Optimized Indian diet");
                String NutsCereals_IdealDietForPeopleWithHealthRisks = NutsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject NutsCereals_Importantconsiderations = Nuts.getJSONObject("Important considerations");
                String NutsCereals_ToknowmoregotoFWItips = NutsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist.add(new RecycleModel("Nuts/seeds/day", NutsCereals_Servings_equals, NutsaCereals_BasicIndianDiet, NutsCereals_OptimizedIndiandiet, NutsCereals_IdealDietForPeopleWithHealthRisks, NutsCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.nuts));

                if (!NutsaCereals_BasicIndianDiet.equals("0") || !NutsCereals_OptimizedIndiandiet.equals("0") || !NutsCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist.add(new RecycleModel("Nuts/seeds/day", NutsCereals_Servings_equals, NutsaCereals_BasicIndianDiet, NutsCereals_OptimizedIndiandiet, NutsCereals_IdealDietForPeopleWithHealthRisks, NutsCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.nuts));

                }
                JSONObject Milk = jdite_plan.getJSONObject("Milk/curd");
                String MilkCereals_Servings_equals = Milk.getString("1 Servings equals");
                JSONObject MilkCereals_NumberofServings = Milk.getJSONObject("Number of Servings");
                String MilkCereals_BasicIndianDiet = MilkCereals_NumberofServings.getString("Basic Indian Diet");
                String MilkCereals_OptimizedIndiandiet = MilkCereals_NumberofServings.getString("Optimized Indian diet");
                String MilkCereals_IdealDietForPeopleWithHealthRisks = MilkCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MilkCereals_Importantconsiderations = Milk.getJSONObject("Important considerations");
                String MilksCereals_ToknowmoregotoFWItips = MilkCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                // datalist.add(new RecycleModel("Milk/curd", MilkCereals_Servings_equals, MilkCereals_BasicIndianDiet, MilkCereals_OptimizedIndiandiet, MilkCereals_IdealDietForPeopleWithHealthRisks, MilksCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.milk));
                if (!MilkCereals_BasicIndianDiet.equals("0") || !MilkCereals_OptimizedIndiandiet.equals("0") || !MilkCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist.add(new RecycleModel("Milk/curd", MilkCereals_Servings_equals, MilkCereals_BasicIndianDiet, MilkCereals_OptimizedIndiandiet, MilkCereals_IdealDietForPeopleWithHealthRisks, MilksCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.milk));

                }
                JSONObject Vegetables = jdite_plan.getJSONObject("Vegetables");
                String vegCereals_Servings_equals = Vegetables.getString("1 Servings equals");
                JSONObject vegCereals_NumberofServings = Vegetables.getJSONObject("Number of Servings");
                String vegCereals_BasicIndianDiet = vegCereals_NumberofServings.getString("Basic Indian Diet");
                String vegCereals_OptimizedIndiandiet = vegCereals_NumberofServings.getString("Optimized Indian diet");
                String vegCereals_IdealDietForPeopleWithHealthRisks = vegCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject vegCereals_Importantconsiderations = Vegetables.getJSONObject("Important considerations");
                String vegCereals_ToknowmoregotoFWItips = vegCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist.add(new RecycleModel("Vegetables", vegCereals_Servings_equals, vegCereals_BasicIndianDiet, vegCereals_OptimizedIndiandiet, vegCereals_IdealDietForPeopleWithHealthRisks, vegCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.vegetables));
                if (!vegCereals_BasicIndianDiet.equals("0") || !vegCereals_OptimizedIndiandiet.equals("0") || !vegCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {
                    datalist.add(new RecycleModel("Vegetables", vegCereals_Servings_equals, vegCereals_BasicIndianDiet, vegCereals_OptimizedIndiandiet, vegCereals_IdealDietForPeopleWithHealthRisks, vegCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.vegetables));
                }

                JSONObject Fruits = jdite_plan.getJSONObject("Fruits");
                String FruitsCereals_Servings_equals = Fruits.getString("1 Servings equals");
                JSONObject FruitsCereals_NumberofServings = Fruits.getJSONObject("Number of Servings");
                String FruitsCereals_BasicIndianDiet = FruitsCereals_NumberofServings.getString("Basic Indian Diet");
                String FruitsCereals_OptimizedIndiandiet = FruitsCereals_NumberofServings.getString("Optimized Indian diet");
                String FruitsCereals_IdealDietForPeopleWithHealthRisks = FruitsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject FruitsCereals_Importantconsiderations = Fruits.getJSONObject("Important considerations");
                String FruitsCereals_ToknowmoregotoFWItips = FruitsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist.add(new RecycleModel("Fruits", FruitsCereals_Servings_equals, FruitsCereals_BasicIndianDiet, FruitsCereals_OptimizedIndiandiet, FruitsCereals_IdealDietForPeopleWithHealthRisks, FruitsCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.fruit));
                if (!FruitsCereals_BasicIndianDiet.equals("0") || !FruitsCereals_OptimizedIndiandiet.equals("0") || !FruitsCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {
                    datalist.add(new RecycleModel("Fruits", FruitsCereals_Servings_equals, FruitsCereals_BasicIndianDiet, FruitsCereals_OptimizedIndiandiet, FruitsCereals_IdealDietForPeopleWithHealthRisks, FruitsCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.fruit));
                }



                JSONObject Oils = jdite_plan.getJSONObject("Oils");
                String OilsCereals_Servings_equals = Oils.getString("1 Servings equals");
                JSONObject OilsCereals_NumberofServings = Oils.getJSONObject("Number of Servings");
                String OilsCereals_BasicIndianDiet = OilsCereals_NumberofServings.getString("Basic Indian Diet");
                String OilsCereals_OptimizedIndiandiet = OilsCereals_NumberofServings.getString("Optimized Indian diet");
                String OilsCereals_IdealDietForPeopleWithHealthRisks = OilsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject OilsCereals_Importantconsiderations = Oils.getJSONObject("Important considerations");
                String OilsCereals_ToknowmoregotoFWItips = OilsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                // datalist.add(new RecycleModel("Oils", OilsCereals_Servings_equals, OilsCereals_BasicIndianDiet, OilsCereals_OptimizedIndiandiet, OilsCereals_IdealDietForPeopleWithHealthRisks, OilsCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.oil));
                controller.add_basic_oil(user_id,OilsCereals_BasicIndianDiet);
                if (!OilsCereals_BasicIndianDiet.equals("0") || !OilsCereals_OptimizedIndiandiet .equals("0") || !OilsCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {
                    datalist.add(new RecycleModel("Oils", OilsCereals_Servings_equals, OilsCereals_BasicIndianDiet, OilsCereals_OptimizedIndiandiet, OilsCereals_IdealDietForPeopleWithHealthRisks, OilsCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.oil));
                }


                JSONObject MaxmEggsweek = jdite_plan.getJSONObject("Maxm. Eggs/week");
                String MaxmCereals_Servings_equals = MaxmEggsweek.getString("1 Servings equals");
                JSONObject MaxmCereals_NumberofServings = MaxmEggsweek.getJSONObject("Number of Servings");
                String MaxmCereals_BasicIndianDiet = MaxmCereals_NumberofServings.getString("Basic Indian Diet");
                String MaxmCereals_OptimizedIndiandiet = MaxmCereals_NumberofServings.getString("Optimized Indian diet");
                String MaxmCereals_IdealDietForPeopleWithHealthRisks = MaxmCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MaxmCereals_Importantconsiderations = MaxmEggsweek.getJSONObject("Important considerations");
                String MaxmCereals_ToknowmoregotoFWItips = MaxmCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                // datalist.add(new RecycleModel("Maxm. Eggs/week", MaxmCereals_Servings_equals, MaxmCereals_BasicIndianDiet, MaxmCereals_OptimizedIndiandiet, MaxmCereals_IdealDietForPeopleWithHealthRisks, MaxmCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.eggs));
                if (!MaxmCereals_BasicIndianDiet.equals("0") || !MaxmCereals_OptimizedIndiandiet.equals("0") || !MaxmCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {
                    datalist.add(new RecycleModel("Maxm. Eggs/week", MaxmCereals_Servings_equals, MaxmCereals_BasicIndianDiet, MaxmCereals_OptimizedIndiandiet, MaxmCereals_IdealDietForPeopleWithHealthRisks, MaxmCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.eggs));
                }

                JSONObject MinmFishseafoodweek = jdite_plan.getJSONObject("Minm. Fish/seafood/week");
                String MinmCereals_Servings_equals = MinmFishseafoodweek.getString("1 Servings equals");
                JSONObject MinmCereals_NumberofServings = MinmFishseafoodweek.getJSONObject("Number of Servings");
                String MinmCereals_BasicIndianDiet = MinmCereals_NumberofServings.getString("Basic Indian Diet");
                String MinmCereals_OptimizedIndiandiet = MinmCereals_NumberofServings.getString("Optimized Indian diet");
                String MinmCereals_IdealDietForPeopleWithHealthRisks = MinmCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MinmCereals_Importantconsiderations = MinmFishseafoodweek.getJSONObject("Important considerations");
                String MinmCereals_ToknowmoregotoFWItips = MinmCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist.add(new RecycleModel("Minm. Fish/seafood/week", MinmCereals_Servings_equals, MinmCereals_BasicIndianDiet, MinmCereals_OptimizedIndiandiet, MinmCereals_IdealDietForPeopleWithHealthRisks, MinmCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.sea_food));
                if (!MinmCereals_BasicIndianDiet.equals("0") || !MinmCereals_OptimizedIndiandiet.equals("0") || !MinmCereals_IdealDietForPeopleWithHealthRisks.equals("0")  ) {

                    datalist.add(new RecycleModel("Minm. Fish/seafood/week", MinmCereals_Servings_equals, MinmCereals_BasicIndianDiet, MinmCereals_OptimizedIndiandiet, MinmCereals_IdealDietForPeopleWithHealthRisks, MinmCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.sea_food));

                }
                JSONObject nonvegetarians = jdite_plan.getJSONObject("Soya products/week for non-vegetarians");
                String nvCereals_Servings_equals = nonvegetarians.getString("1 Servings equals");
                JSONObject nvCereals_NumberofServings = nonvegetarians.getJSONObject("Number of Servings");
                String nvCereals_BasicIndianDiet = nvCereals_NumberofServings.getString("Basic Indian Diet");
                String nvCereals_OptimizedIndiandiet = nvCereals_NumberofServings.getString("Optimized Indian diet");
                String nvCereals_IdealDietForPeopleWithHealthRisks = nvCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject nvCereals_Importantconsiderations = nonvegetarians.getJSONObject("Important considerations");
                String nvCereals_ToknowmoregotoFWItips = nvCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist.add(new RecycleModel("Soya products/week for non-vegetarians", nvCereals_Servings_equals, nvCereals_BasicIndianDiet, nvCereals_OptimizedIndiandiet, nvCereals_IdealDietForPeopleWithHealthRisks, nvCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.soya));

                if (!nvCereals_BasicIndianDiet.equals("0")||!nvCereals_OptimizedIndiandiet.equals("0")||!nvCereals_IdealDietForPeopleWithHealthRisks.equals("0") ) {

                    datalist.add(new RecycleModel("Soya products/week for non-vegetarians", nvCereals_Servings_equals, nvCereals_BasicIndianDiet, nvCereals_OptimizedIndiandiet, nvCereals_IdealDietForPeopleWithHealthRisks, nvCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.soya));

                }


                JSONObject Soyagetarians = jdite_plan.getJSONObject("Min. Soya products/week");
                String SoyaCereals_Servings_equals = Soyagetarians.getString("1 Servings equals");
                JSONObject SoyaCereals_NumberofServings = Soyagetarians.getJSONObject("Number of Servings");
                String SoyaCereals_BasicIndianDiet = SoyaCereals_NumberofServings.getString("Basic Indian Diet");
                String SoyaCereals_OptimizedIndiandiet = SoyaCereals_NumberofServings.getString("Optimized Indian diet");
                String SoyaCereals_IdealDietForPeopleWithHealthRisks = SoyaCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject SoyaCereals_Importantconsiderations = Soyagetarians.getJSONObject("Important considerations");
                String SoyaCereals_ToknowmoregotoFWItips = SoyaCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist.add(new RecycleModel("Min. Soya products/week", SoyaCereals_Servings_equals, SoyaCereals_BasicIndianDiet, SoyaCereals_OptimizedIndiandiet, SoyaCereals_IdealDietForPeopleWithHealthRisks, SoyaCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.soya));

                if (!SoyaCereals_BasicIndianDiet.equals("0") ||  !SoyaCereals_OptimizedIndiandiet.equals("0") ||!SoyaCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist.add(new RecycleModel("Min. Soya products/week", SoyaCereals_Servings_equals, SoyaCereals_BasicIndianDiet, SoyaCereals_OptimizedIndiandiet, SoyaCereals_IdealDietForPeopleWithHealthRisks, SoyaCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.soya));

                }

                SharedPreferences sharedPreferences = getSharedPreferences("MyDiet",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("DataDite", new Gson().toJson(datalist2));
                editor.commit();

                Intent in = new Intent(getApplicationContext(), Ideal_Diat_plan_Child_result2.class);
                in.putExtra("data", datalist);
                in.putExtra("ActivityLevel", getActivity1Number(ActivityLevel));
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

            }
        } catch (Exception e) {
        }
    }
    void DataConnectionChild() {

        if(CalorieCalculator.getIdealDietPlanAdult(getApplicationContext()).equals("defaultValue")!=true && Edit_profile_Activity.profileEdited==false)
        {
            processIdealDietChildPlan2(CalorieCalculator.getIdealDietPlanAdult((getApplicationContext())));
            return;
        }
        if(Edit_profile_Activity.profileEdited==true)
        {
            Edit_profile_Activity.profileEdited=false;
        }
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + ideal_diet_plan_for_child + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        Log.e("url= ", URLS + ideal_diet_plan_for_child + "");
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");

                            if (status.equals("1")) {
                                CalorieCalculator.writeIdealDietPlanAdult(getApplicationContext(),response);
                                processIdealDietPlanAdult1(response);

                            } else {
                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
                params.put("age", age + "");
                params.put("sex", gender);
                params.put("Weight", weight);
                params.put("dob", get_dob);
                params.put("ft", feet);
                params.put("inc", inch);
                params.put("exercise", getActivity1Number(S_Exercise));
                params.put("veg_nonveg", getVegNumber(S_veg_non));


                Log.e("params", params + "");

                return params;
            }
        };

        postRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 500000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 500000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }

    void DataConnection() {

        pd = new ProgressDialog(PlansActivity.this);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
        pd.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + weight_loss_calculator + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        Log.e("response= ", response + " rendom =" + randnoo);

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {

                                JSONObject joo = jobj.getJSONObject("weight_loss_calculator_details");
                                JSONObject jo = joo.getJSONObject("weight_loss_calculator_details");

                                String weight_range = jo.getString("Appropriate weight range for you ( as per your gender and height) is");
                                String Weight_Category = jo.getString("Your Weight Category is");
                                String target = jo.getString("Your target weightloss should be");
                                String bmi = jo.getString("Your BMI is");

                                new AllSharedPrefrences(PlansActivity.this).BMI_Result(bmi, weight_range, Weight_Category, target, weight);
                                new AllSharedPrefrences(PlansActivity.this).keyBmi("1");
                                new MyIntent(PlansActivity.this, BMI_Result.class, R.anim.enter, R.anim.exit);

                            } else {
                            }
                        } catch (Exception e) {
                            pd.dismiss();
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
                params.put("gender", gender);
                params.put("age", age);
                params.put("dob", get_dob);
                params.put("ft", feet);
                params.put("inc", inch);
                params.put("weight", weight);

                Log.e("params", params + "");

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(postRequest);
    }


    String getVegNumber(String food_habits){
        if (food_habits.equals("Non Vegetarian")) {
            return "3";
        } else if (food_habits.equals("Vegetarian")) {
            return "2";
        } else if (food_habits.equals("Eggetarian")) {
            return "1";
        }

        return food_habits;
    }

    void processIdealDietPlanAdult1(String response)
    {
        try {
            JSONObject jobj = new JSONObject(response);
            String status = jobj.getString("status");
            String message = jobj.getString("message");

            if (status.equals("1")) {


                JSONObject joo = jobj.getJSONObject("ideal_diet_plan_for_adult");
                JSONObject jo = joo.getJSONObject("ideal_diet_plan_for_adult");

                String weight_range = jo.getString("Your weight loss target (over 3-6 months)");
                String Weight_Category = jo.getString("Your Weight Category is");
//                                String target = jo.getString("Your target weightloss should be");
                Log.e("weight_rangeeee", weight_range + "..." + Weight_Category);
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

                new AllSharedPrefrences(PlansActivity.this).IDEAL_Diet_Plan(bmi, tar_weight, Weight_Category, weight_loss, ActivityLevel, RDCA, DailyCalorieAllowance,
                        Category, Gender, Age, Height, Pregnancy, Trimester, PrePregnancyWeight, BreastFeeding, ChildAge, Weight);

                new AllSharedPrefrences(PlansActivity.this).keydiet("1");
                JSONObject jdite_plan = jo.getJSONObject("YOUR BASIC DIET PLAN IS");
                JSONObject GrainsCereals = jdite_plan.getJSONObject("Grains/cereals");
                String GrainsCereals_Servings_equals = GrainsCereals.getString("1 Servings equals");
                JSONObject GrainsCereals_NumberofServings = GrainsCereals.getJSONObject("Number of Servings");
                String GrainsCereals_BasicIndianDiet = GrainsCereals_NumberofServings.getString("Basic Indian Diet");
                String GrainsCereals_OptimizedIndiandiet = GrainsCereals_NumberofServings.getString("Optimized Indian diet");
                String GrainsCereals_IdealDietForPeopleWithHealthRisks = GrainsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject GrainsCereals_Importantconsiderations = GrainsCereals.getJSONObject("Important considerations");
                String GrainsCereals_ToknowmoregotoFWItips = GrainsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                if (!GrainsCereals_BasicIndianDiet.equals("0") || !GrainsCereals_OptimizedIndiandiet.equals("0")||!GrainsCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist2.add(new RecycleModel("Grains/cereals", GrainsCereals_Servings_equals, GrainsCereals_BasicIndianDiet, GrainsCereals_OptimizedIndiandiet, GrainsCereals_IdealDietForPeopleWithHealthRisks, GrainsCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.grains));

                }

                JSONObject Dalsmeatsday = jdite_plan.getJSONObject("Dals/meats/day");
                String DalCereals_Servings_equals = Dalsmeatsday.getString("1 Servings equals");
                JSONObject DalCereals_NumberofServings = Dalsmeatsday.getJSONObject("Number of Servings");
                String DalCereals_BasicIndianDiet = DalCereals_NumberofServings.getString("Basic Indian Diet");
                String DalCereals_OptimizedIndiandiet = DalCereals_NumberofServings.getString("Optimized Indian diet");
                String DalCereals_IdealDietForPeopleWithHealthRisks = DalCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject DalCereals_Importantconsiderations = Dalsmeatsday.getJSONObject("Important considerations");
                String DalCereals_ToknowmoregotoFWItips = DalCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                // datalist2.add(new RecycleModel("Dals/meats/day", DalCereals_Servings_equals, DalCereals_BasicIndianDiet, DalCereals_OptimizedIndiandiet, DalCereals_IdealDietForPeopleWithHealthRisks, DalCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.dals));

                if (!DalCereals_BasicIndianDiet.equals("0") || !DalCereals_OptimizedIndiandiet.equals("0") || !DalCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist2.add(new RecycleModel("Dals/meats/day", DalCereals_Servings_equals, DalCereals_BasicIndianDiet, DalCereals_OptimizedIndiandiet, DalCereals_IdealDietForPeopleWithHealthRisks, DalCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.dals));

                }

                JSONObject Nuts = jdite_plan.getJSONObject("Nuts/seeds/day");
                String NutsCereals_Servings_equals = Nuts.getString("1 Servings equals");
                JSONObject NutsCereals_NumberofServings = Nuts.getJSONObject("Number of Servings");
                String NutsaCereals_BasicIndianDiet = NutsCereals_NumberofServings.getString("Basic Indian Diet");
                String NutsCereals_OptimizedIndiandiet = NutsCereals_NumberofServings.getString("Optimized Indian diet");
                String NutsCereals_IdealDietForPeopleWithHealthRisks = NutsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject NutsCereals_Importantconsiderations = Nuts.getJSONObject("Important considerations");
                String NutsCereals_ToknowmoregotoFWItips = NutsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                // datalist2.add(new RecycleModel("Nuts/seeds/day", NutsCereals_Servings_equals, NutsaCereals_BasicIndianDiet, NutsCereals_OptimizedIndiandiet, NutsCereals_IdealDietForPeopleWithHealthRisks, NutsCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.nuts));

                if (!NutsaCereals_BasicIndianDiet.equals("0") || !NutsCereals_OptimizedIndiandiet.equals("0") || !NutsCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist2.add(new RecycleModel("Nuts/seeds/day", NutsCereals_Servings_equals, NutsaCereals_BasicIndianDiet, NutsCereals_OptimizedIndiandiet, NutsCereals_IdealDietForPeopleWithHealthRisks, NutsCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.nuts));

                }
                JSONObject Milk = jdite_plan.getJSONObject("Milk/curd");
                String MilkCereals_Servings_equals = Milk.getString("1 Servings equals");
                JSONObject MilkCereals_NumberofServings = Milk.getJSONObject("Number of Servings");
                String MilkCereals_BasicIndianDiet = MilkCereals_NumberofServings.getString("Basic Indian Diet");
                String MilkCereals_OptimizedIndiandiet = MilkCereals_NumberofServings.getString("Optimized Indian diet");
                String MilkCereals_IdealDietForPeopleWithHealthRisks = MilkCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MilkCereals_Importantconsiderations = Milk.getJSONObject("Important considerations");
                String MilksCereals_ToknowmoregotoFWItips = MilkCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                // datalist2.add(new RecycleModel("Milk/curd", MilkCereals_Servings_equals, MilkCereals_BasicIndianDiet, MilkCereals_OptimizedIndiandiet, MilkCereals_IdealDietForPeopleWithHealthRisks, MilksCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.milk));

                if (!MilkCereals_BasicIndianDiet.equals("0") || !MilkCereals_OptimizedIndiandiet.equals("0") || !MilkCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist2.add(new RecycleModel("Milk/curd", MilkCereals_Servings_equals, MilkCereals_BasicIndianDiet, MilkCereals_OptimizedIndiandiet, MilkCereals_IdealDietForPeopleWithHealthRisks, MilksCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.milk));

                }
                JSONObject Vegetables = jdite_plan.getJSONObject("Vegetables");
                String vegCereals_Servings_equals = Vegetables.getString("1 Servings equals");
                JSONObject vegCereals_NumberofServings = Vegetables.getJSONObject("Number of Servings");
                String vegCereals_BasicIndianDiet = vegCereals_NumberofServings.getString("Basic Indian Diet");
                String vegCereals_OptimizedIndiandiet = vegCereals_NumberofServings.getString("Optimized Indian diet");
                String vegCereals_IdealDietForPeopleWithHealthRisks = vegCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject vegCereals_Importantconsiderations = Vegetables.getJSONObject("Important considerations");
                String vegCereals_ToknowmoregotoFWItips = vegCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist2.add(new RecycleModel("Vegetables", vegCereals_Servings_equals, vegCereals_BasicIndianDiet, vegCereals_OptimizedIndiandiet, vegCereals_IdealDietForPeopleWithHealthRisks, vegCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.vegetables));

                if (!vegCereals_BasicIndianDiet.equals("0") || !vegCereals_OptimizedIndiandiet.equals("0") || !vegCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {
                    datalist2.add(new RecycleModel("Vegetables", vegCereals_Servings_equals, vegCereals_BasicIndianDiet, vegCereals_OptimizedIndiandiet, vegCereals_IdealDietForPeopleWithHealthRisks, vegCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.vegetables));
                }
                JSONObject Fruits = jdite_plan.getJSONObject("Fruits");
                String FruitsCereals_Servings_equals = Fruits.getString("1 Servings equals");
                JSONObject FruitsCereals_NumberofServings = Fruits.getJSONObject("Number of Servings");
                String FruitsCereals_BasicIndianDiet = FruitsCereals_NumberofServings.getString("Basic Indian Diet");
                String FruitsCereals_OptimizedIndiandiet = FruitsCereals_NumberofServings.getString("Optimized Indian diet");
                String FruitsCereals_IdealDietForPeopleWithHealthRisks = FruitsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject FruitsCereals_Importantconsiderations = Fruits.getJSONObject("Important considerations");
                String FruitsCereals_ToknowmoregotoFWItips = FruitsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                // datalist2.add(new RecycleModel("Fruits", FruitsCereals_Servings_equals, FruitsCereals_BasicIndianDiet, FruitsCereals_OptimizedIndiandiet, FruitsCereals_IdealDietForPeopleWithHealthRisks, FruitsCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.fruit));

                if (!FruitsCereals_BasicIndianDiet.equals("0") || !FruitsCereals_OptimizedIndiandiet.equals("0") || !FruitsCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {
                    datalist2.add(new RecycleModel("Fruits", FruitsCereals_Servings_equals, FruitsCereals_BasicIndianDiet, FruitsCereals_OptimizedIndiandiet, FruitsCereals_IdealDietForPeopleWithHealthRisks, FruitsCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.fruit));
                }

                JSONObject Oils = jdite_plan.getJSONObject("Oils");
                String OilsCereals_Servings_equals = Oils.getString("1 Servings equals");
                JSONObject OilsCereals_NumberofServings = Oils.getJSONObject("Number of Servings");
                String OilsCereals_BasicIndianDiet = OilsCereals_NumberofServings.getString("Basic Indian Diet");
                String OilsCereals_OptimizedIndiandiet = OilsCereals_NumberofServings.getString("Optimized Indian diet");
                String OilsCereals_IdealDietForPeopleWithHealthRisks = OilsCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject OilsCereals_Importantconsiderations = Oils.getJSONObject("Important considerations");
                String OilsCereals_ToknowmoregotoFWItips = OilsCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                controller.add_basic_oil(user_id,OilsCereals_BasicIndianDiet);

                if (!OilsCereals_BasicIndianDiet.equals("0") || !OilsCereals_OptimizedIndiandiet .equals("0") || !OilsCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {
                    datalist2.add(new RecycleModel("Oils", OilsCereals_Servings_equals, OilsCereals_BasicIndianDiet, OilsCereals_OptimizedIndiandiet, OilsCereals_IdealDietForPeopleWithHealthRisks, OilsCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.oil));
                }

                JSONObject MaxmEggsweek = jdite_plan.getJSONObject("Maxm. Eggs/week");
                String MaxmCereals_Servings_equals = MaxmEggsweek.getString("1 Servings equals");
                JSONObject MaxmCereals_NumberofServings = MaxmEggsweek.getJSONObject("Number of Servings");
                String MaxmCereals_BasicIndianDiet = MaxmCereals_NumberofServings.getString("Basic Indian Diet");
                String MaxmCereals_OptimizedIndiandiet = MaxmCereals_NumberofServings.getString("Optimized Indian diet");
                String MaxmCereals_IdealDietForPeopleWithHealthRisks = MaxmCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MaxmCereals_Importantconsiderations = MaxmEggsweek.getJSONObject("Important considerations");
                String MaxmCereals_ToknowmoregotoFWItips = MaxmCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                // datalist2.add(new RecycleModel("Maxm. Eggs/week", MaxmCereals_Servings_equals, MaxmCereals_BasicIndianDiet, MaxmCereals_OptimizedIndiandiet, MaxmCereals_IdealDietForPeopleWithHealthRisks, MaxmCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.eggs));
                if (!MaxmCereals_BasicIndianDiet.equals("0") || !MaxmCereals_OptimizedIndiandiet.equals("0") || !MaxmCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist2.add(new RecycleModel("Maxm. Eggs/week", MaxmCereals_Servings_equals, MaxmCereals_BasicIndianDiet, MaxmCereals_OptimizedIndiandiet, MaxmCereals_IdealDietForPeopleWithHealthRisks, MaxmCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.eggs));

                }

                JSONObject MinmFishseafoodweek = jdite_plan.getJSONObject("Minm. Fish/seafood/week");
                String MinmCereals_Servings_equals = MinmFishseafoodweek.getString("1 Servings equals");
                JSONObject MinmCereals_NumberofServings = MinmFishseafoodweek.getJSONObject("Number of Servings");
                String MinmCereals_BasicIndianDiet = MinmCereals_NumberofServings.getString("Basic Indian Diet");
                String MinmCereals_OptimizedIndiandiet = MinmCereals_NumberofServings.getString("Optimized Indian diet");
                String MinmCereals_IdealDietForPeopleWithHealthRisks = MinmCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject MinmCereals_Importantconsiderations = MinmFishseafoodweek.getJSONObject("Important considerations");
                String MinmCereals_ToknowmoregotoFWItips = MinmCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist2.add(new RecycleModel("Minm. Fish/seafood/week", MinmCereals_Servings_equals, MinmCereals_BasicIndianDiet, MinmCereals_OptimizedIndiandiet, MinmCereals_IdealDietForPeopleWithHealthRisks, MinmCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.sea_food));
                if (!MinmCereals_BasicIndianDiet.equals("0") || !MinmCereals_OptimizedIndiandiet.equals("0") || !MinmCereals_IdealDietForPeopleWithHealthRisks.equals("0")  ) {

                    datalist2.add(new RecycleModel("Minm. Fish/seafood/week", MinmCereals_Servings_equals, MinmCereals_BasicIndianDiet, MinmCereals_OptimizedIndiandiet, MinmCereals_IdealDietForPeopleWithHealthRisks, MinmCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.sea_food));

                }

                JSONObject nonvegetarians = jdite_plan.getJSONObject("Soya products/week for non-vegetarians");
                String nvCereals_Servings_equals = nonvegetarians.getString("1 Servings equals");
                JSONObject nvCereals_NumberofServings = nonvegetarians.getJSONObject("Number of Servings");
                String nvCereals_BasicIndianDiet = nvCereals_NumberofServings.getString("Basic Indian Diet");
                String nvCereals_OptimizedIndiandiet = nvCereals_NumberofServings.getString("Optimized Indian diet");
                String nvCereals_IdealDietForPeopleWithHealthRisks = nvCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject nvCereals_Importantconsiderations = nonvegetarians.getJSONObject("Important considerations");
                String nvCereals_ToknowmoregotoFWItips = nvCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist2.add(new RecycleModel("Soya products/week for non-vegetarians", nvCereals_Servings_equals, nvCereals_BasicIndianDiet, nvCereals_OptimizedIndiandiet, nvCereals_IdealDietForPeopleWithHealthRisks, nvCereals_ToknowmoregotoFWItips, "#f4f4f4",R.drawable.soya));

                if (!nvCereals_BasicIndianDiet.equals("0")||!nvCereals_OptimizedIndiandiet.equals("0")||!nvCereals_IdealDietForPeopleWithHealthRisks.equals("0") ) {

                    datalist2.add(new RecycleModel("Soya products/week for non-vegetarians", nvCereals_Servings_equals, nvCereals_BasicIndianDiet, nvCereals_OptimizedIndiandiet, nvCereals_IdealDietForPeopleWithHealthRisks, nvCereals_ToknowmoregotoFWItips, "#f4f4f4", R.drawable.soya));

                }
                JSONObject Soyagetarians = jdite_plan.getJSONObject("Min. Soya products/week");
                String SoyaCereals_Servings_equals = Soyagetarians.getString("1 Servings equals");
                JSONObject SoyaCereals_NumberofServings = Soyagetarians.getJSONObject("Number of Servings");
                String SoyaCereals_BasicIndianDiet = SoyaCereals_NumberofServings.getString("Basic Indian Diet");
                String SoyaCereals_OptimizedIndiandiet = SoyaCereals_NumberofServings.getString("Optimized Indian diet");
                String SoyaCereals_IdealDietForPeopleWithHealthRisks = SoyaCereals_NumberofServings.getString("Ideal Diet For People With Health Risks");
                JSONObject SoyaCereals_Importantconsiderations = Soyagetarians.getJSONObject("Important considerations");
                String SoyaCereals_ToknowmoregotoFWItips = SoyaCereals_Importantconsiderations.getString("( To know more, go to FWI tips)");
                //datalist2.add(new RecycleModel("Min. Soya products/week", SoyaCereals_Servings_equals, SoyaCereals_BasicIndianDiet, SoyaCereals_OptimizedIndiandiet, SoyaCereals_IdealDietForPeopleWithHealthRisks, SoyaCereals_ToknowmoregotoFWItips, "#ffffff",R.drawable.soya));

                if (!SoyaCereals_BasicIndianDiet.equals("0") ||  !SoyaCereals_OptimizedIndiandiet.equals("0") ||!SoyaCereals_IdealDietForPeopleWithHealthRisks.equals("0")) {

                    datalist2.add(new RecycleModel("Min. Soya products/week", SoyaCereals_Servings_equals, SoyaCereals_BasicIndianDiet, SoyaCereals_OptimizedIndiandiet, SoyaCereals_IdealDietForPeopleWithHealthRisks, SoyaCereals_ToknowmoregotoFWItips, "#ffffff", R.drawable.soya));

                }
                new AllSharedPrefrences(PlansActivity.this).IDEAL_Diet_Plan_Data(GrainsCereals_Servings_equals, GrainsCereals_BasicIndianDiet, GrainsCereals_OptimizedIndiandiet, GrainsCereals_IdealDietForPeopleWithHealthRisks, GrainsCereals_ToknowmoregotoFWItips, DalCereals_Servings_equals, DalCereals_BasicIndianDiet, DalCereals_OptimizedIndiandiet, DalCereals_IdealDietForPeopleWithHealthRisks, DalCereals_ToknowmoregotoFWItips, MaxmCereals_Servings_equals, MaxmCereals_BasicIndianDiet, MaxmCereals_OptimizedIndiandiet, MaxmCereals_IdealDietForPeopleWithHealthRisks, MaxmCereals_ToknowmoregotoFWItips
                        , MinmCereals_Servings_equals, MinmCereals_BasicIndianDiet, MinmCereals_OptimizedIndiandiet, MinmCereals_IdealDietForPeopleWithHealthRisks, MinmCereals_ToknowmoregotoFWItips, nvCereals_Servings_equals, nvCereals_BasicIndianDiet, nvCereals_OptimizedIndiandiet, nvCereals_IdealDietForPeopleWithHealthRisks, nvCereals_ToknowmoregotoFWItips, SoyaCereals_Servings_equals, SoyaCereals_BasicIndianDiet, SoyaCereals_OptimizedIndiandiet, SoyaCereals_IdealDietForPeopleWithHealthRisks, SoyaCereals_ToknowmoregotoFWItips, NutsCereals_Servings_equals, NutsaCereals_BasicIndianDiet, NutsCereals_OptimizedIndiandiet, NutsCereals_IdealDietForPeopleWithHealthRisks, NutsCereals_ToknowmoregotoFWItips,
                        MilkCereals_Servings_equals, MilkCereals_BasicIndianDiet, MilkCereals_OptimizedIndiandiet, MilkCereals_IdealDietForPeopleWithHealthRisks, MilksCereals_ToknowmoregotoFWItips, vegCereals_Servings_equals, vegCereals_BasicIndianDiet, vegCereals_OptimizedIndiandiet, vegCereals_IdealDietForPeopleWithHealthRisks, vegCereals_ToknowmoregotoFWItips, FruitsCereals_Servings_equals, FruitsCereals_BasicIndianDiet, FruitsCereals_OptimizedIndiandiet, FruitsCereals_IdealDietForPeopleWithHealthRisks, FruitsCereals_ToknowmoregotoFWItips, OilsCereals_Servings_equals, OilsCereals_BasicIndianDiet, OilsCereals_OptimizedIndiandiet, OilsCereals_IdealDietForPeopleWithHealthRisks, OilsCereals_ToknowmoregotoFWItips);


                new ProductController_For_All(PlansActivity.this).addIdealDietPlan( new IdealDietPlanDBModel(user_id,GrainsCereals_Servings_equals, GrainsCereals_BasicIndianDiet, GrainsCereals_OptimizedIndiandiet, GrainsCereals_IdealDietForPeopleWithHealthRisks, GrainsCereals_ToknowmoregotoFWItips, DalCereals_Servings_equals, DalCereals_BasicIndianDiet, DalCereals_OptimizedIndiandiet, DalCereals_IdealDietForPeopleWithHealthRisks, DalCereals_ToknowmoregotoFWItips, MaxmCereals_Servings_equals, MaxmCereals_BasicIndianDiet, MaxmCereals_OptimizedIndiandiet, MaxmCereals_IdealDietForPeopleWithHealthRisks, MaxmCereals_ToknowmoregotoFWItips
                        , MinmCereals_Servings_equals, MinmCereals_BasicIndianDiet, MinmCereals_OptimizedIndiandiet, MinmCereals_IdealDietForPeopleWithHealthRisks, MinmCereals_ToknowmoregotoFWItips, nvCereals_Servings_equals, nvCereals_BasicIndianDiet, nvCereals_OptimizedIndiandiet, nvCereals_IdealDietForPeopleWithHealthRisks, nvCereals_ToknowmoregotoFWItips, SoyaCereals_Servings_equals, SoyaCereals_BasicIndianDiet, SoyaCereals_OptimizedIndiandiet, SoyaCereals_IdealDietForPeopleWithHealthRisks, SoyaCereals_ToknowmoregotoFWItips, NutsCereals_Servings_equals, NutsaCereals_BasicIndianDiet, NutsCereals_OptimizedIndiandiet, NutsCereals_IdealDietForPeopleWithHealthRisks, NutsCereals_ToknowmoregotoFWItips,
                        MilkCereals_Servings_equals, MilkCereals_BasicIndianDiet, MilkCereals_OptimizedIndiandiet, MilkCereals_IdealDietForPeopleWithHealthRisks, MilksCereals_ToknowmoregotoFWItips, vegCereals_Servings_equals, vegCereals_BasicIndianDiet, vegCereals_OptimizedIndiandiet, vegCereals_IdealDietForPeopleWithHealthRisks, vegCereals_ToknowmoregotoFWItips, FruitsCereals_Servings_equals, FruitsCereals_BasicIndianDiet, FruitsCereals_OptimizedIndiandiet, FruitsCereals_IdealDietForPeopleWithHealthRisks, FruitsCereals_ToknowmoregotoFWItips, OilsCereals_Servings_equals, OilsCereals_BasicIndianDiet, OilsCereals_OptimizedIndiandiet, OilsCereals_IdealDietForPeopleWithHealthRisks, OilsCereals_ToknowmoregotoFWItips));

                Intent in = new Intent(getApplicationContext(), Ideal_Diat_plan_Adult_result2.class);
                in.putExtra("data_2", datalist2);
                startActivity(in);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();


                SharedPreferences sharedPreferences = getSharedPreferences("MyDiet",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("DataDite", new Gson().toJson(datalist2));
                editor.commit();
                //  new Gso

            } else {
            }
        } catch (Exception e) {
            Log.e("errrrr= ", e + "");
        }
    }
    void DataConnectionAdult() {

        if(CalorieCalculator.getIdealDietPlanAdult(getApplicationContext()).equals("defaultValue")!=true && Edit_profile_Activity.profileEdited==false)
        {
            if(pd.isShowing())
                pd.dismiss();
            processIdealDietPlanAdult1(CalorieCalculator.getIdealDietPlanAdult((getApplicationContext())));
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
                                processIdealDietPlanAdult1(response);


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
                params.put("sex", gender);
                params.put("age", age + "");
                params.put("Weight", weight + "");
                params.put("want_diet_for_weight_loss", S_want);
                params.put("dob", get_dob);
                params.put("ft", feet);
                params.put("inc", inch);
                params.put("exercise", getActivity1Number(S_Exercise));
                params.put("occupation", getActivity2Number(S_Occupation));
                params.put("veg_nonveg", getVegNumber(S_veg_non));
                params.put("is_Pregnant", S_is_Pregnant);
                params.put("is_Breast_Feeding", S_is_Breast_Feeding);
                params.put("first_day_last_mensrual_period", S_first_day_last_mensrual_period);
                params.put("pre_pregnancy_weight", S_pre_pregnancy_weight);
                params.put("child_age", S_child_age);
                Log.e("params adults", params + "");
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(postRequest);
    }

}
