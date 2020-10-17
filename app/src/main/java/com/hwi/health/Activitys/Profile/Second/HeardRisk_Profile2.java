package com.hwi.health.Activitys.Profile.Second;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.advanced_profile.Body_PARAMETERS;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.edit_profile.Edit_profile_Activity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class HeardRisk_Profile2 extends AppCompatActivity implements View.OnClickListener, BaseUrl {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L, adv_lin;
    TextView bmi_text, weight_text, risk_text, year_risk_text,text_dum,year_one,year_two,year_three,expect_age,smoke,year_text_three,year_text_four,year_four,year_five,year_text_five;
    String randnoo, result;
    LinearLayout lin1,lin2,lin3,lin4,lin5;
    ImageView adv_text;
    TextView year_text_eleven,year_text_ten,year_text_nine,year_text_eight,year_text_seven,year_text_six;
    String S_age, S_Fasting_blood_sugar, S_diabetes, S_disease, S_high_BP;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    String gender, height, weight, dob, feet, inch, is_smoke;
    ProgressDialog pd;
    Button btn_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Heart Risk Profile");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heard_risk__profile);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        lin1 = (LinearLayout) findViewById(R.id.lin1);
        lin2 = (LinearLayout) findViewById(R.id.lin2);
        lin3 = (LinearLayout) findViewById(R.id.lin3);
        lin4 = (LinearLayout) findViewById(R.id.lin4);
        lin5 = (LinearLayout) findViewById(R.id.lin5);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        adv_text =  findViewById(R.id.adv_text);
        adv_lin = (LinearLayout) findViewById(R.id.adv_lin);
        bmi_text = (TextView) findViewById(R.id.BMI);
        weight_text = (TextView) findViewById(R.id.Weight);
        risk_text = (TextView) findViewById(R.id.Risk);
        year_risk_text = (TextView) findViewById(R.id.year_risk);
        text_dum = (TextView) findViewById(R.id.text_dum);
        year_one = (TextView) findViewById(R.id.year_one);
        year_two = (TextView) findViewById(R.id.year_two);
        year_three = (TextView) findViewById(R.id.year_three);

        year_text_eleven = (TextView) findViewById(R.id.year_text_eleven);
        year_text_ten = (TextView) findViewById(R.id.year_text_ten);
        year_text_nine = (TextView) findViewById(R.id.year_text_nine);
        year_text_eight = (TextView) findViewById(R.id.year_text_eight);
        year_text_seven = (TextView) findViewById(R.id.year_text_seven);
        year_text_six = (TextView) findViewById(R.id.year_text_six);

        expect_age = (TextView) findViewById(R.id.expect_age);
        smoke = (TextView) findViewById(R.id.smoke);
        year_text_three = (TextView) findViewById(R.id.year_text_three);
        year_four = (TextView) findViewById(R.id.year_four);
        year_five = (TextView) findViewById(R.id.year_five);
        year_text_five = (TextView) findViewById(R.id.year_text_five);


        btn_profile = (Button) findViewById(R.id.btn_profile);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        btn_profile.setOnClickListener(this);

        try {

            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();

            ////////advance/////////
            is_smoke = sp.getString("is_smoke", "");
            gender = sp.getString("gender", "");
            S_age = sp.getString("age", "");
            S_diabetes = sp.getString("is_diabetic", "");
            height = sp.getString("height", "");
            weight = sp.getString("weight", "");
            dob = sp.getString("Dob", "");
            Hemoglobin = sp.getString("Hemoglobin", "");
            Hematocrit = sp.getString("Hematocrit", "");
            blood_sugar = sp.getString("blood_sugar", "");
            Total_cholesterol = sp.getString("Total_cholesterol", "");
            LDL_cholesterol = sp.getString("LDL_cholesterol", "");
            HDL_cholesterol = sp.getString("HDL_cholesterol", "");
            Triglycerides = sp.getString("Triglycerides", "");
            Vitamin_levels = sp.getString("Vitamin_levels", "");
            Senter_inc = sp.getString("Senter_inc", "");
            Senter_cmc = sp.getString("Senter_cmc", "");
            SSystolic = sp.getString("SSystolic", "");
            SDiastolic = sp.getString("SDiastolic", "");
            Vitamin_B12_levels = sp.getString("Vitamin_B12_levels", "");
            heart_disease = sp.getString("heart_disease", "");
            Bp = sp.getString("BP", "");


            String[] separated = height.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet = f;
            inch = i;

          // bmi_text.setText(sp.getString("BMI", ""));
           // weight_text.setText(sp.getString("Weight_Category", ""));
            risk_text.setText(sp.getString("This_risk_is", ""));
            year_risk_text.setText(sp.getString("This_risk_is", ""));

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();


        } catch (Exception e) {
            Log.e("errrrr", e + "");
        }
        //  Profile_completion_persentage();
        new HeardRisk_Result().execute();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(HeardRisk_Profile2.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), User_Profile.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(HeardRisk_Profile2.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(HeardRisk_Profile2.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(HeardRisk_Profile2.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(HeardRisk_Profile2.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(HeardRisk_Profile2.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == btn_profile) {
            new MyIntent(HeardRisk_Profile2.this, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);
        }
    }


    class HeardRisk_Result extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(HeardRisk_Profile2.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + hwi_heart_risk_calculator + randnoo);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("age", S_age + "");
                postDataParams.put("sex", gender);
                postDataParams.put("Weight", weight);
                postDataParams.put("dob", dob);
                postDataParams.put("ft", feet);
                postDataParams.put("inc", inch);
                postDataParams.put("waist_inch", Senter_inc);
                postDataParams.put("waist_cm", Senter_cmc);
                postDataParams.put("systolic", SSystolic);
                postDataParams.put("diastolic", SDiastolic);
                postDataParams.put("tot_cholesterol", Total_cholesterol);
                postDataParams.put("hdl_colestrol", HDL_cholesterol);
                postDataParams.put("triglycerides", Triglycerides);
                postDataParams.put("smoke", is_smoke);
                postDataParams.put("fasting_blood_sugar", blood_sugar);
                postDataParams.put("diabetes", S_diabetes);
                postDataParams.put("father_brother_heart_disease", heart_disease);
                postDataParams.put("drugs", Bp);

                Log.e("hart risk params", postDataParams.toString());

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
            Log.e("LoginActivity = ", result + "");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");

                if (status.equals("1")) {
                    pd.dismiss();
                    adv_lin.setVisibility(View.VISIBLE);
                    JSONObject joo = jobj.getJSONObject("hwi_heart_risk_calculator");
                    JSONObject jo = joo.getJSONObject("hwi_heart_risk_calculator");

                    String bmi = jo.getString("Your BMI");
                    String Weight_Category = jo.getString("Your Weight Category is:");
                    String yearrisk10 = jo.getString("10 year risk for having a heart attack or stroke in people like you is");

                    String This_risk_is = jo.getString("This risk is");
                    String tot_cholesterol_risk_persentage = jo.getString("tot_cholesterol_risk_persentage");
                    String tot_cholesterol_risk_category = jo.getString("tot_cholesterol_risk_category");
                    String systolic_or_diastolic_risk_persentage = jo.getString("systolic_or_diastolic_risk_persentage");
                    String systolic_or_diastolic_risk_category = jo.getString("systolic_or_diastolic_risk_category");
                    String year_risk_persent20 = jo.getString("20_year_risk_persent");
                    String year_risk20 = jo.getString("20_year_risk");
                    String A50 = jo.getString("A50");
                    String A41 = jo.getString("A41");
                    String tot_cholesterol_risk_title = jo.getString("tot_cholesterol_risk_title");
                    String A43 = jo.getString("A43");
                    String A44 = jo.getString("A44");
                    String A45 = jo.getString("A45");
                    String A46 = jo.getString("A46");
                    String A47 = jo.getString("A47");
                    String A48 = jo.getString("A48");
                    String To_know_how = jo.getString("To know how to manage your heart risk well, go to");
                    String B51 = jo.getString("B51");
                    String smoke_risk_amount = jo.getString("smoke_risk_amount");
                    String smoke_risk_amount_title = jo.getString("smoke_risk_amount_title");
                    String risk_again = jo.getString("risk_again");


                    new AllSharedPrefrences(HeardRisk_Profile2.this).Risk_Cal(bmi, Weight_Category, yearrisk10, This_risk_is, A44, A45, A46, A47, A48, To_know_how, year_risk_persent20, year_risk20, B51, smoke_risk_amount, smoke_risk_amount_title, risk_again, systolic_or_diastolic_risk_persentage, systolic_or_diastolic_risk_category, tot_cholesterol_risk_title, tot_cholesterol_risk_persentage, tot_cholesterol_risk_category, A41, A50);


                   /* new AllSharedPrefrences(HeardRisk_Profile.this).Risk_Cal(bmi, Weight_Category, yearrisk10, This_risk_is, "", "", "", "", "", "", year_risk_persent20, year_risk20, "", "", ""
                            , "", systolic_or_diastolic_risk_persentage, systolic_or_diastolic_risk_category, "", tot_cholesterol_risk_persentage, tot_cholesterol_risk_category, "", "");*/
                    new AllSharedPrefrences(HeardRisk_Profile2.this).keyHeartRisk("1");
                    bmi_text.setText(bmi);
                    weight_text.setText(Weight_Category);
                    risk_text.setText(This_risk_is);
                    year_risk_text.setText(yearrisk10);
                    year_three.setText(tot_cholesterol_risk_persentage+" "+tot_cholesterol_risk_category);
                    if (!A47.equals("")) {
                        year_text_eleven.setVisibility(View.VISIBLE);
                        year_text_eleven.setText(A47);
                    }

                    if (!A48.equals("")){
                        year_text_ten.setVisibility(View.VISIBLE);
                        year_text_ten.setText(A48);
                    }

                    if (!A46.equals("")){
                        year_text_nine.setVisibility(View.VISIBLE);
                        year_text_nine.setText(A46);
                    }

                    if (!A45.equals("")){
                        year_text_eight.setVisibility(View.VISIBLE);
                        year_text_eight.setText(A45);
                    }

                    if (!A43.equals("")){
                        year_text_seven.setVisibility(View.VISIBLE);
                        year_text_seven.setText(A43);
                    }

                    if (!A41.equals("")){
                        year_text_six.setVisibility(View.VISIBLE);
                        year_text_six.setText(A41);
                    }

                    if (!A50.equals("")){
                        lin1.setVisibility(View.VISIBLE);
                        expect_age.setText(A50);
                        year_one.setText(year_risk_persent20+" "+year_risk20);
                    }

                    if (!smoke_risk_amount_title.equals("")){
                        lin2.setVisibility(View.VISIBLE);
                        smoke.setText(smoke_risk_amount_title);
                        year_two.setText(tot_cholesterol_risk_persentage+" "+smoke_risk_amount);
                    }

                    if (!tot_cholesterol_risk_title.equals("")){
                        lin3.setVisibility(View.VISIBLE);
                        year_text_three.setText(smoke_risk_amount_title);
                        year_three.setText(tot_cholesterol_risk_persentage+" "+tot_cholesterol_risk_category);
                    }


                } else {
                    pd.dismiss();
                    adv_text.setVisibility(View.VISIBLE);
                    adv_lin.setVisibility(View.GONE);
                    text_dum.setVisibility(View.VISIBLE);
                    btn_profile.setVisibility(View.VISIBLE);

                    AlertDialogComplete();

                    JSONObject jj = new JSONObject(result);
                    JSONObject j = jj.getJSONObject("message");
                    String reason = j.getString("reason");

                }
            } catch (Exception e) {
                pd.dismiss();

                Log.e("Exception= ", e + "");
            }

        }
    }

    void AlertDialogComplete() {
      final Dialog  dialog3 = new Dialog(HeardRisk_Profile2.this, R.style.CustomDialog);
        dialog3.setContentView(R.layout.exercise_log_popup);
        dialog3.setCancelable(true);

        ImageView close = (ImageView) dialog3.findViewById(R.id.close);
        TextView text_popup = (TextView) dialog3.findViewById(R.id.text_popup);
        Button yes = (Button) dialog3.findViewById(R.id.yes);
        Button no = (Button) dialog3.findViewById(R.id.no);
        text_popup.setText("Complete Your Profile.");
        close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                    }
                });
        yes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                        Intent intent = new Intent(HeardRisk_Profile2.this, Edit_profile_Activity.class);
                        startActivity(intent);
                        finish();


                    }
                });
        no.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                    }
                });
        dialog3.show();
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


    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

}
