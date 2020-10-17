package com.hwi.health.Activitys.Profile.First;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Plans.Excercize_plans.CalorieCalculator;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.edit_profile.Edit_profile_Activity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.hwi.health.InterFaces.Util.getActivity1Number;
import static com.hwi.health.InterFaces.Util.getActivity2Number;
import static com.hwi.health.InterFaces.Util.getVegNumber;

/**
 * Created by PAWAN on 06-07-2017.
 */

public class SetSecondStepData implements BaseUrl {

    String result, randnoo, gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, dob, loss_weg, key;
    AppCompatActivity act;
    Class cls;
    ProgressDialog pd;
    String heart_disease, BP, feet, inch;
    int number;
    Dialog dialog;
    String key_diet, key_bmi, edit_key;
    String S_HBA1C_value, Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic;
    String gokey;
    String calculate_diet_plan;

    public SetSecondStepData(AppCompatActivity act, Class cls, String randnoo, String gender, String age, String height, String weight, String is_pregnent, String first_day_of_last_menstrual_cycle, String pre_pregnancy_weight, String breast_feeding, String child_age, String is_diabetic, String taking_insulin, String diabetic_diet, String last_HBA1C, String blood_sugar_no_of_times, String blood_sugar_in, String last_fasting, String S_HBA1C_value, String pp_value, String activity_level_one, String activity_level_two, String food_habits, String any_heart_disease, String is_alcholic, String alcohol_how_often, String is_smoke, String smoke_how_often, String user_id, String dob, String loss_weg, String key, String gokey) {
        this.act = act;

        if(gokey.equals("14"))
        this.calculate_diet_plan="1";

        this.cls = cls;
        this.randnoo = randnoo;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.is_pregnent = is_pregnent;
        this.first_day_of_last_menstrual_cycle = first_day_of_last_menstrual_cycle;
        this.pre_pregnancy_weight = pre_pregnancy_weight;
        this.breast_feeding = breast_feeding;
        this.child_age = child_age;
        this.is_diabetic = is_diabetic;
        this.taking_insulin = taking_insulin;
        this.diabetic_diet = diabetic_diet;
        this.last_HBA1C = last_HBA1C;
        this.blood_sugar_no_of_times = blood_sugar_no_of_times;
        this.blood_sugar_in = blood_sugar_in;
        this.last_fasting = last_fasting;
        this.S_HBA1C_value = S_HBA1C_value;
        this.pp_value = pp_value;
        this.activity_level_one = activity_level_one;
        this.activity_level_two = activity_level_two;
        this.food_habits = food_habits;
        this.any_heart_disease = any_heart_disease;
        this.is_alcholic = is_alcholic;
        this.alcohol_how_often = alcohol_how_often;
        this.is_smoke = is_smoke;
        this.smoke_how_often = smoke_how_often;
        this.user_id = user_id;
        this.dob = dob;
        this.loss_weg = loss_weg;
        this.key = key;
        this.gokey = gokey;
        try {
            String[] separated = height.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet = f;
            inch = i;
        } catch (Exception e) {

        }
        try {
            number = Integer.parseInt(age);
            Log.e("number", number + "...");
        } catch (Exception e) {
            Log.e("eee", e + "...");
        }


    }

    public SetSecondStepData(AppCompatActivity act, Class cls, String randnoo, String gender, String age, String height, String weight, String is_pregnent, String first_day_of_last_menstrual_cycle, String pre_pregnancy_weight, String breast_feeding, String child_age, String is_diabetic, String taking_insulin, String diabetic_diet, String last_HBA1C, String blood_sugar_no_of_times, String blood_sugar_in, String last_fasting, String pp_value, String activity_level_one, String activity_level_two, String food_habits, String any_heart_disease, String is_alcholic, String alcohol_how_often, String is_smoke, String smoke_how_often, String user_id, String dob, String loss_weg, String key) {

        this.act = act;
        this.cls = cls;
        this.randnoo = randnoo;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.is_pregnent = is_pregnent;
        this.first_day_of_last_menstrual_cycle = first_day_of_last_menstrual_cycle;
        this.pre_pregnancy_weight = pre_pregnancy_weight;
        this.breast_feeding = breast_feeding;
        this.child_age = child_age;
        this.is_diabetic = is_diabetic;
        this.taking_insulin = taking_insulin;
        this.diabetic_diet = diabetic_diet;
        this.last_HBA1C = last_HBA1C;
        this.blood_sugar_no_of_times = blood_sugar_no_of_times;
        this.blood_sugar_in = blood_sugar_in;
        this.last_fasting = last_fasting;
        this.pp_value = pp_value;
        this.activity_level_one = activity_level_one;
        this.activity_level_two = activity_level_two;
        this.food_habits = food_habits;
        this.any_heart_disease = any_heart_disease;
        this.is_alcholic = is_alcholic;
        this.alcohol_how_often = alcohol_how_often;
        this.is_smoke = is_smoke;
        this.smoke_how_often = smoke_how_often;
        this.user_id = user_id;
        this.dob = dob;
        this.loss_weg = loss_weg;
        this.key = key;
        try {
            String[] separated = height.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet = f;
            inch = i;
        } catch (Exception e) {

        }
        try {
            number = Integer.parseInt(age);
            Log.e("number", number + "...");
        } catch (Exception e) {
            Log.e("eee", e + "...");
        }


    }


    public void Data(AppCompatActivity act, Class cls, String randnoo, String Hemoglobin, String Hematocrit, String blood_sugar, String Total_cholesterol, String LDL_cholesterol, String HDL_cholesterol, String Triglycerides, String Vitamin_levels, String Vitamin_B12_levels, String Senter_inc, String Senter_cmc, String SSystolic, String SDiastolic, String heart_disease, String BP) {
        this.act = act;
        this.cls = cls;
        this.randnoo = randnoo;
        this.Hemoglobin = Hemoglobin;
        this.Hematocrit = Hematocrit;
        this.blood_sugar = blood_sugar;
        this.Total_cholesterol = Total_cholesterol;
        this.LDL_cholesterol = LDL_cholesterol;
        this.HDL_cholesterol = HDL_cholesterol;
        this.Triglycerides = Triglycerides;
        this.Vitamin_levels = Vitamin_levels;
        this.Vitamin_B12_levels = Vitamin_B12_levels;
        this.Senter_inc = Senter_inc;
        this.Senter_cmc = Senter_cmc;
        this.SSystolic = SSystolic;
        this.SDiastolic = SDiastolic;
        this.heart_disease = heart_disease;
        this.BP = BP;
    }

    public void EditData(String edit_key) {

        this.edit_key = edit_key;
    }

    public void DataConnection() {
        new Task().execute();
    }

    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(act);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + manage_profile + randnoo);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);
                postDataParams.put("gender", gender);
                postDataParams.put("age", age);
                postDataParams.put("height", height);
                postDataParams.put("weight", weight);
                postDataParams.put("is_pregnent", is_pregnent);
                postDataParams.put("first_day_of_last_menstrual_cycle", first_day_of_last_menstrual_cycle);
                postDataParams.put("pre_pregnancy_weight", pre_pregnancy_weight);
                postDataParams.put("breast_feeding", breast_feeding);
                postDataParams.put("child_age", child_age);
                postDataParams.put("is_diabetic", is_diabetic);
                postDataParams.put("taking_insulin", taking_insulin);
                postDataParams.put("diabetic_diet", diabetic_diet);
                postDataParams.put("last_HBA1C", last_HBA1C);
                postDataParams.put("blood_sugar_no_of_times", blood_sugar_no_of_times);
                postDataParams.put("blood_sugar_in", blood_sugar_in);
                postDataParams.put("last_fasting", last_fasting);
                postDataParams.put("HBA1C_value", S_HBA1C_value);
                postDataParams.put("pp_value", pp_value);
                postDataParams.put("activity_level_one", activity_level_one);
                postDataParams.put("activity_level_two", activity_level_two);
                postDataParams.put("food_habits", food_habits);
                postDataParams.put("any_heart_disease", any_heart_disease);
                postDataParams.put("is_alcholic", is_alcholic);
                postDataParams.put("alcohol_how_often", alcohol_how_often);
                postDataParams.put("is_smoke", is_smoke);
                postDataParams.put("smoke_how_often", smoke_how_often);
                postDataParams.put("DOB", dob);
                postDataParams.put("do_you_want_a_diet_for_weight_loss", loss_weg);
                postDataParams.put("hemoglobin", Hemoglobin);
                postDataParams.put("hematocrit", Hematocrit);
                postDataParams.put("fasting_blood_sugar", blood_sugar);
                postDataParams.put("total_cholesterol", Total_cholesterol);
                postDataParams.put("LDL_cholesterol", LDL_cholesterol);
                postDataParams.put("HDL_cholesterol", HDL_cholesterol);
                postDataParams.put("triglycerides", Triglycerides);
                postDataParams.put("vitamin_D_levels", Vitamin_levels);
                postDataParams.put("vitamin_B12_levels", Vitamin_B12_levels);
                postDataParams.put("waist_circumference_inches", Senter_inc);
                postDataParams.put("waist_circumference_cms", Senter_cmc);
                postDataParams.put("blood_pressure1", SSystolic);
                postDataParams.put("blood_pressure2", SDiastolic);
                postDataParams.put("did_your_father_get_heart_disease_before_45years_of_age", heart_disease);
                postDataParams.put("are_you_drugs_for_high_bp", BP);
                postDataParams.put("calculate_diet_plan", calculate_diet_plan);
                if(height.isEmpty()==false) {
                    postDataParams.put("ft", height.split("\\.")[0]);
                    postDataParams.put("inc", height.split("\\.")[1]);
                }
                postDataParams.put("exercise", getActivity1Number(activity_level_one));
                postDataParams.put("occupation", getActivity2Number(activity_level_two));


                Log.e("url", url+"");
                Log.e("params", postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(150000);
                conn.setConnectTimeout(150000);
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
                Log.e("ERRR task", e + "");
            }
            return result;
        }
        public String centimeterToFeet(String centemeter) {
            int feetPart = 0;
            int inchesPart = 0;
            if(!TextUtils.isEmpty(centemeter)) {
                double dCentimeter = Double.valueOf(centemeter);
                feetPart = (int) Math.floor((dCentimeter / 2.54) / 12);
                System.out.println((dCentimeter / 2.54) - (feetPart * 12));
                inchesPart = (int) Math.ceil((dCentimeter / 2.54) - (feetPart * 12));
            }
           return   String.valueOf(feetPart);

        }

        public String centimeterToInches(String centemeter) {
            int feetPart = 0;
            int inchesPart = 0;
            if(!TextUtils.isEmpty(centemeter)) {
                double dCentimeter = Double.valueOf(centemeter);
                feetPart = (int) Math.floor((dCentimeter / 2.54) / 12);
                System.out.println((dCentimeter / 2.54) - (feetPart * 12));
                inchesPart = (int) Math.ceil((dCentimeter / 2.54) - (feetPart * 12));
            }
            return   String.valueOf(inchesPart);

        }
        @Override
        protected void onPostExecute(String s) {
            try {
                pd.dismiss();
            } catch (Exception e) {
                Log.e("pd error", e + "");
            }
            super.onPostExecute(s);
            Log.e("task  = ", result + "");
            try {
                JSONObject jboj = new JSONObject(result);
                String status = jboj.getString("status");
                String message = jboj.getString("message");

                if(jboj.has("calorie_calculations") || jboj.has("customized_meal"))
                {
                    JSONObject calorie_calculations = jboj.getJSONObject("calorie_calculations");
                    CalorieCalculator.StoreCalculatedCalories(calorie_calculations, act.getApplicationContext());
                    JSONObject default_customized_plan=jboj.getJSONObject("customized_meal");

                    CalorieCalculator.writeCustomPlanAdult(act.getApplicationContext(),default_customized_plan.toString());
                }



                if (status.equals("1")) {

                    SharedPreferences spss = act.getSharedPreferences("EditPro", MODE_PRIVATE);
                    SharedPreferences.Editor editorss = spss.edit();
                    editorss.putString("gokey", gokey);
                    editorss.commit();

                    // Toast.makeText(act, message+ "", Toast.LENGTH_SHORT).show();
                    new AllSharedPrefrences(act).UserData2(user_id,
                            gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                            pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet,
                            last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, S_HBA1C_value, pp_value, activity_level_one,
                            activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke,
                            smoke_how_often);

                    new AllSharedPrefrences(act).UserAdvanceData1(Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, BP);

                    new AllSharedPrefrences(act).UserDob(dob);
                    // Log.e("keyyyyyyheight",key+"...."+height);
                    //  Toast.makeText(act, ""+height, Toast.LENGTH_SHORT).show();

                    new AllSharedPrefrences(act).Weight_loss(loss_weg);

                    if (key.equals("no")) {
                        //Log.e("keeeee","runnnn"+"....");
                        new MyIntent(act, cls, R.anim.enter, R.anim.exit,true);
                    }
                    try {
                        if (gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                                dob.equals("") || dob == null) {
                            if (key.equals("yes")) {

                                new MyIntent(act, cls, R.anim.enter, R.anim.exit,true);
                            }
                        } else {
                            DataConnectionBmi();
                        }

                    } catch (Exception e) {
                        Log.e("bmiii", e + "");
                    }


                } else {
                    //Toast.makeText(act, message + "", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(act, message, Snackbar.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Log.e("error = ", e + "");
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

    void DataConnectionBmi() {

        final ProgressDialog pd = new ProgressDialog(act);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + weight_loss_calculator + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pd.dismiss();
                        } catch (Exception e) {
                            Log.e("pd error", e + "");
                        }
                        Log.d("responseBmiiii= ", response + " rendom =" + randnoo);

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

                                new AllSharedPrefrences(act).BMI_Result(bmi, weight_range, Weight_Category, target, weight);
                                new AllSharedPrefrences(act).keyBmi("1");
                                // new MyIntent(Alcohol.this, User_Profile.class, R.anim.enter, R.anim.exit);


                                // AlertDialog();
                                try {
                                    if (number > 18) {


                                        if (gender.equals("male")) {
                                            if (loss_weg.equals("") || loss_weg == null || activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null || food_habits.equals("") || food_habits == null || gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                                                    dob.equals("") || dob == null) {
                                                if (key.equals("yes")) {
                                                    new MyIntent(act, cls, R.anim.enter, R.anim.exit,true);
                                                }
                                            } else {

                                                DataConnectionAdult("1");
                                            }

                                        } else if (gender.equals("female"))
                                            if (is_pregnent.equals("yes")) {
                                                if (loss_weg.equals("") || loss_weg == null || activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null || food_habits.equals("") || food_habits == null || is_pregnent.equals("") || is_pregnent == null || child_age.equals("") || child_age == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null || first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                                                        dob.equals("") || dob == null) {
                                                    if (key.equals("yes")) {
                                                        new MyIntent(act, cls, R.anim.enter, R.anim.exit,true);
                                                    }
                                                } else {

                                                    DataConnectionAdult("1");
                                                }
                                            } else if (is_pregnent.equals("no")) {
                                                if (loss_weg.equals("") || loss_weg == null || activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null || food_habits.equals("") || food_habits == null || is_pregnent.equals("") || is_pregnent == null || breast_feeding.equals("") || breast_feeding == null || gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                                                        dob.equals("") || dob == null) {
                                                    if (key.equals("yes")) {
                                                        new MyIntent(act, cls, R.anim.enter, R.anim.exit,true);
                                                    }
                                                } else {

                                                    DataConnectionAdult("1");
                                                }
                                            }


                                    } else {

                                        if (activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null || gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                                                dob.equals("") || dob == null) {
                                            if (key.equals("yes")) {
                                                new MyIntent(act, cls, R.anim.enter, R.anim.exit,true);
                                            }
                                        } else {

                                            DataConnectionChild("1");
                                        }
                                    }

                                } catch (Exception e) {
                                    pd.dismiss();
                                    Log.e("Eror 1", e + "");

                                }

                            } else {

                                //  new MyIntent(Alcohol.this, go_class, R.anim.enter, R.anim.exit);
                                try {
                                    if (number > 18) {
                                        // Toast.makeText(ctx, "333", Toast.LENGTH_SHORT).show();
                                        DataConnectionAdult("0");
                                    } else {
                                        // Toast.makeText(ctx, "444", Toast.LENGTH_SHORT).show();
                                        DataConnectionChild("0");
                                    }

                                } catch (Exception e) {
                                    pd.dismiss();
                                    Log.e("Eror 2", e + "");
                                }

                            }
                        } catch (Exception e) {
                            pd.dismiss();
                            Log.d("errrrr bmi= ", e + "");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.d("VolleyError bmi= ", error + "");
                try {
                    pd.dismiss();
                } catch (Exception e) {
                    Log.e("pd error", e + "");
                }

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("gender", gender);
                params.put("age", age);
                params.put("dob", dob);
                params.put("ft", feet);
                params.put("inc", inch);
                params.put("weight", weight);

                Log.e("paramsBmiiii", params + "");

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(act).add(postRequest);
    }


    void idealDietPlanChild2(String response,String keyyyy)
    {
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
                new AllSharedPrefrences(act).Activity_Level_Diet_Plan(ActivityLevel);
                new AllSharedPrefrences(act).keydiet("1");
                if (key.equals("yes")) {
                    if (edit_key.equals("12")) {
                        new MyIntent(act, Edit_profile_Activity.class, R.anim.enter, R.anim.exit,true);
                    } else {
                        AlertDialog();
                    }


                }
            } else {
                if (keyyyy.equals("1")) {
                    if (key.equals("yes")) {
                        if (edit_key.equals("12")) {
                            new MyIntent(act, Edit_profile_Activity.class, R.anim.enter, R.anim.exit,true);
                        } else {
                            AlertDialog();
                        }
                    }
                } else {
                    new MyIntent(act, User_Profile.class, R.anim.enter, R.anim.exit,true);
                }

            }
        } catch (Exception e) {
            try {
                pd.dismiss();
            } catch (Exception ee) {
                Log.e("pd error", ee + "");
            }
        }
    }
    void DataConnectionChild(final String keyyyy) {


            if(CalorieCalculator.getIdealDietPlanAdult(getApplicationContext()).equals("defaultValue")!=true  && Edit_profile_Activity.profileEdited==false)
        {
            idealDietPlanChild2(CalorieCalculator.getIdealDietPlanAdult((getApplicationContext())),keyyyy);
            return;
        }

        if(Edit_profile_Activity.profileEdited==true)
        Edit_profile_Activity.profileEdited=false;

        final ProgressDialog pd = new ProgressDialog(act);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + ideal_diet_plan_for_child + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pd.dismiss();
                        } catch (Exception e) {
                            Log.e("pd error", e + "");
                        }

                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");

                            if (status.equals("1")) {
                               idealDietPlanChild2(response,keyyyy);
                               CalorieCalculator.writeIdealDietPlanAdult(getApplicationContext(),response);

                            } else {
                                if (keyyyy.equals("1")) {
                                    if (key.equals("yes")) {
                                        if (edit_key.equals("12")) {
                                            new MyIntent(act, Edit_profile_Activity.class, R.anim.enter, R.anim.exit,true);
                                        } else {
                                            AlertDialog();
                                        }
                                    }
                                } else {
                                    new MyIntent(act, User_Profile.class, R.anim.enter, R.anim.exit,true);
                                }

                            }
                        } catch (Exception e) {
                            try {
                                pd.dismiss();
                            } catch (Exception ee) {
                                Log.e("pd error", ee + "");
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
                try {
                    pd.dismiss();
                } catch (Exception e) {
                    Log.e("pd error", e + "");
                }
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
                params.put("dob", dob);
                params.put("ft", feet);
                params.put("inc", inch);
                params.put("exercise", getActivity1Number(activity_level_one));
                params.put("veg_nonveg", getVegNumber(food_habits));


                Log.e("params", params + "");

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(act).add(postRequest);
    }


    void DataConnectionAdult(final String keyyyy) {



        final ProgressDialog pd = new ProgressDialog(act);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();



        if(CalorieCalculator.getIdealDietPlanAdult(getApplicationContext()).equals("defaultValue")!=true && Edit_profile_Activity.profileEdited==false)
        {
            if(pd.isShowing())
                pd.dismiss();
            processIdealDietPlanAdult2(CalorieCalculator.getIdealDietPlanAdult((getApplicationContext())),keyyyy);
            return;
        }

        if(Edit_profile_Activity.profileEdited==true)
        Edit_profile_Activity.profileEdited=false;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + ideal_diet_plan_for_adult + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("responceadult ", response);
                        try {
                            pd.dismiss();

                        } catch (Exception e) {
                            Log.e("pd error", e + "");
                        }
                        CalorieCalculator.writeIdealDietPlanAdult(getApplicationContext(),response);
                        processIdealDietPlanAdult2(CalorieCalculator.getIdealDietPlanAdult((getApplicationContext())),keyyyy);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
                try {
                    pd.dismiss();
                } catch (Exception e) {
                    Log.e("pd error", e + "");
                }

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
                params.put("want_diet_for_weight_loss", loss_weg);
                params.put("dob", dob);
                params.put("ft", feet);
                params.put("inc", inch);
                params.put("exercise", getActivity1Number(activity_level_one));
                params.put("occupation", getActivity2Number(activity_level_two));
                params.put("veg_nonveg", getVegNumber(food_habits));
                params.put("is_Pregnant", is_pregnent);
                params.put("is_Breast_Feeding", breast_feeding);
                params.put("first_day_last_mensrual_period", first_day_of_last_menstrual_cycle);
                params.put("pre_pregnancy_weight", pre_pregnancy_weight);
                params.put("child_age", child_age);
                Log.e("params", params + "");
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(act).add(postRequest);
    }

    private void processIdealDietPlanAdult2(String response,String keyyyy) {
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

                new AllSharedPrefrences(act).Activity_Level_Diet_Plan(ActivityLevel);

                new AllSharedPrefrences(act).keydiet("1");


                // new MyIntent(Alcohol.this, go_class, R.anim.enter, R.anim.exit);
                if (key.equals("yes")) {
                    if (edit_key!=null && edit_key.equals("12")) {
                        new MyIntent(act, Edit_profile_Activity.class, R.anim.enter, R.anim.exit,true);
                    } else {
                        new MyIntent(act, HomeActivity.class, R.anim.enter, R.anim.exit,true);



                    }
                }

                //       new MyIntent(Ideal_Diat_plan_Adult.this, BMI_Result.class, R.anim.enter, R.anim.exit);

            } else {
                if (keyyyy.equals("1")) {
                    if (key.equals("yes")) {
                        if (edit_key!=null && edit_key.equals("12")) {
                            new MyIntent(act, Edit_profile_Activity.class, R.anim.enter, R.anim.exit,true);
                        } else {
                            new MyIntent(act, HomeActivity.class, R.anim.enter, R.anim.exit,true);


                        }
                    }
                } else {
                    new MyIntent(act, User_Profile.class, R.anim.enter, R.anim.exit,true);
                }
            }
        } catch (Exception e) {
            try {
                pd.dismiss();
            } catch (Exception ee) {
                Log.e("pd error", ee + "");
            }
            Log.e("errrrr= ", e + "");
        }
    }

    void AlertDialog() throws Exception {


        dialog = new Dialog(act, R.style.CustomDialog);
        dialog.setContentView(R.layout.activity_plan__complete);
        dialog.setCancelable(true);
        SharedPreferences sp2 = new AllSharedPrefrences(act).UserDataget();
        key_diet = sp2.getString("key_diet", "");
        key_bmi = sp2.getString("key_bmi", "");
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
                new MyIntent(act, PlansActivity.class, R.anim.enter, R.anim.exit);
            }
        });
        adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyIntent(act, User_Profile.class, R.anim.enter, R.anim.exit);
            }
        });

        dialog.show();
    }
}
