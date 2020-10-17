package com.hwi.health.Activitys.More;

import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hwi.health.Activitys.Profile.First.Alcohol;
import com.hwi.health.Activitys.Profile.First.Profile_Active_10;
import com.hwi.health.Activitys.Profile.First.Profile_Blood_Suger_9;
import com.hwi.health.Activitys.Profile.First.Profile_Breast_Feeding_5;
import com.hwi.health.Activitys.Profile.First.Profile_Child_Age_6;
import com.hwi.health.Activitys.Profile.First.Profile_Diabetic_7;
import com.hwi.health.Activitys.Profile.First.Profile_Food_habits_11;
import com.hwi.health.Activitys.Profile.First.Profile_Insulin_8;
import com.hwi.health.Activitys.Profile.First.Profile_Menstrul_Cycle_4;
import com.hwi.health.Activitys.Profile.First.Profile_Step3;
import com.hwi.health.Activitys.Profile.First.Smoking;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Web.Search;
import com.hwi.health.Activitys.AboutApp;
import com.hwi.health.Activitys.AllTests.OILCALCULATOR.FAMILY_OIL_CALCULATOR;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.manage_report.Upload_file_list;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.First.Profile_Step1;
import com.hwi.health.Activitys.Profile.First.Profile_Step2;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.community.Community_one;
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

public class MoreActivity extends AppCompatActivity implements View.OnClickListener, BaseUrl {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    LinearLayout search_website, Community, get_email, video, know_more, edit_profile, logout, Report_export, oil_cal;
    String get_email_id, user_id, randnoo;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("MORE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        search_website = (LinearLayout) findViewById(R.id.search_website);
        edit_profile = (LinearLayout) findViewById(R.id.edit_profile);
        Community = (LinearLayout) findViewById(R.id.Community);
        get_email = (LinearLayout) findViewById(R.id.get_email);
        video = (LinearLayout) findViewById(R.id.video);
        logout = (LinearLayout) findViewById(R.id.logout);
        Report_export = (LinearLayout) findViewById(R.id.Report_export);
        know_more = (LinearLayout) findViewById(R.id.know_more);
        oil_cal = (LinearLayout) findViewById(R.id.oil_cal);

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            user_id = sp.getString("Userid", "");
            new GetPercentages().execute();
        } catch (Exception e) {
        }

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        logout.setOnClickListener(this);

        know_more.setOnClickListener(this);
        video.setOnClickListener(this);
        get_email.setOnClickListener(this);
        Community.setOnClickListener(this);
        search_website.setOnClickListener(this);
        edit_profile.setOnClickListener(this);
        Report_export.setOnClickListener(this);
        oil_cal.setOnClickListener(this);

        try {
            SharedPreferences sp = getSharedPreferences("KEY", Context.MODE_PRIVATE);
            sp.edit().clear().commit();
        } catch (Exception e) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(MoreActivity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(MoreActivity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(MoreActivity.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(MoreActivity.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(MoreActivity.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }

        if (view == search_website) {
            new MyIntent(MoreActivity.this, Search.class, R.anim.enter, R.anim.exit);
        }
        if (view == Community) {
            new MyIntent(MoreActivity.this, Community_one.class, R.anim.enter, R.anim.exit);
        }
        if (view == get_email) {
            AlertDialog();
        }
        if (view == video) {
            new MyIntent(MoreActivity.this, VideoConsultatn.class, R.anim.enter, R.anim.exit);
        }
        if (view == know_more) {
            //   new MyIntent(MoreActivity.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == edit_profile) {
            new MyIntent(MoreActivity.this, Edit_profile_Activity.class, R.anim.enter, R.anim.exit);
        }
        if (view == Report_export) {
            new AllSharedPrefrences(MoreActivity.this).Prescription_key("3");
            new MyIntent(MoreActivity.this, Upload_file_list.class, R.anim.enter, R.anim.exit);
        }
        if (view == oil_cal) {
            new MyIntent(MoreActivity.this, FAMILY_OIL_CALCULATOR.class, R.anim.enter, R.anim.exit);
        }
        if (view == logout) {

            new AllSharedPrefrences(MoreActivity.this).ClearSignupsp();
            SharedPreferences sp0 = getSharedPreferences("EditPro",MODE_PRIVATE);
            sp0.edit().clear().commit();

            SharedPreferences sp = getSharedPreferences("adv", MODE_PRIVATE);
            sp.edit().clear().commit();

            SharedPreferences sp2 = getSharedPreferences("HintPage", MODE_PRIVATE);
            sp2.edit().clear().commit();

            SharedPreferences shared = getSharedPreferences("Alert_popup", MODE_PRIVATE);
            shared.edit().clear().commit();



            new MyIntent(MoreActivity.this, AboutApp.class, R.anim.enter, R.anim.exit);

        }
    }

    private void clearAppData() {
        try {
            // clearing app data
            if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                ((ActivityManager)getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData(); // note: it has a return value!
            } else {
                String packageName = getApplicationContext().getPackageName();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("pm clear "+packageName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    class GetPercentages extends AsyncTask<String, Void, String> {
        String result;
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MoreActivity.this);
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
        final Dialog dialogstatus = new Dialog(MoreActivity.this, R.style.CustomDialog);
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
                    new MyIntent(MoreActivity.this, Profile_Step1.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("2")){
                    new MyIntent(MoreActivity.this, Profile_Step2.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("3")){
                    new MyIntent(MoreActivity.this, Profile_Step3.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("4")){
                    new MyIntent(MoreActivity.this, Profile_Menstrul_Cycle_4.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("5")){
                    new MyIntent(MoreActivity.this, Profile_Breast_Feeding_5.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("6")){
                    new MyIntent(MoreActivity.this, Profile_Child_Age_6.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("7")){
                    new MyIntent(MoreActivity.this, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("8")){
                    new MyIntent(MoreActivity.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("9")){
                    new MyIntent(MoreActivity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("10")){
                    new MyIntent(MoreActivity.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("11")){
                    new MyIntent(MoreActivity.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("12")){
                    new MyIntent(MoreActivity.this, Smoking.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("13")){
                    new MyIntent(MoreActivity.this, Alcohol.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("14")){
                    new MyIntent(MoreActivity.this, Edit_profile_Activity.class, R.anim.enter, R.anim.exit);
                }
            }
        });



        dialogstatus.show();
    }

    void AlertDialog() {
        dialog = new Dialog(MoreActivity.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.subscribe_email_dialog);
        dialog.setCancelable(true);
        Button send = (Button) dialog.findViewById(R.id.send);
        Button close = (Button) dialog.findViewById(R.id.close);
        final EditText email = (EditText) dialog.findViewById(R.id.email);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_email_id = email.getText().toString();
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();
                DataConnection();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }


    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + subscribe_email + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {
                                Toast.makeText(MoreActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            } else {

                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");
                                dialog.dismiss();
                            }
                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("email", get_email_id);
                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }


}
