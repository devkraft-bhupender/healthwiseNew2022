package com.hwi.health.Activitys.Logs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.hwi.health.Models.Alchohal_tracker_model;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.Excercise_log.ExcerciseLogActivity;
import com.hwi.health.Activitys.More.Calculator_PKG.Alcohol_Tracker;
import com.hwi.health.Activitys.More.Calculator_PKG.Eat_Out_Snacker;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.More.manage_report.Upload_file_list;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class LogActivity extends AppCompatActivity implements View.OnClickListener {

    /*  GridView gv;
      ArrayList<LogModel> llist = new ArrayList<>();
      Context ctx;
      LogGrid lg;*/

    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String diet_weight_loss, cust_key;
    LinearLayout exerciesplan, eat_out_plan, alchohal_lay, dietplan, weighttplan, Treatment, bpplan, sugarplan;
    String sat_sss1 = "";
    String sat_sss2 = "";
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("LOG");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log2);
        exerciesplan = (LinearLayout) findViewById(R.id.exerciesplan);
        eat_out_plan = (LinearLayout) findViewById(R.id.eat_out_plan);
        alchohal_lay = (LinearLayout) findViewById(R.id.alchohal_lay);
        dietplan = (LinearLayout) findViewById(R.id.dietplan);
        weighttplan = (LinearLayout) findViewById(R.id.weighttplan);
        Treatment = (LinearLayout) findViewById(R.id.Treatment);
        bpplan = (LinearLayout) findViewById(R.id.bpplan);
        sugarplan = (LinearLayout) findViewById(R.id.sugarplan);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        exerciesplan.setOnClickListener(this);
        eat_out_plan.setOnClickListener(this);
        alchohal_lay.setOnClickListener(this);
        dietplan.setOnClickListener(this);
        weighttplan.setOnClickListener(this);
        Treatment.setOnClickListener(this);
        bpplan.setOnClickListener(this);
        sugarplan.setOnClickListener(this);

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            diet_weight_loss = sp.getString("weg_loss", "");
            cust_key = sp.getString("cust_key", "");
            user_id = sp.getString("Userid", "");
        } catch (Exception e) {
        }

        new GetPercentages().execute();

        try {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String current_date = df.format(date);

            List<Alchohal_tracker_model> alcho_list = new ProductController_For_All(LogActivity.this).getMyAlcho(user_id, "1", current_date, current_date);

            List<Alchohal_tracker_model> alcho_list2 = new ProductController_For_All(LogActivity.this).getMyAlcho(user_id, "2", current_date, current_date);

            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            String gender = sp.getString("gender", "");
            if (gender.equals("female")) {

                if (alcho_list.isEmpty()) {
                    if (!alcho_list2.isEmpty()) {
                        float size = std_size(alcho_list2);
                        Log.e("F size 1", size + "");
                        if (size > 11) {
                            sat_sss1 = "no";
                            sat_sss2 = "yes";
                            alchohal_lay.setBackground(getResources().getDrawable(R.drawable.box_red));
                        }
                    }
                } else {
                    float size = std_size(alcho_list);
                    Log.e("F size 2", size + "");
                    if (size > 3) {
                        sat_sss1 = "yes";
                        sat_sss2 = "no";
                        alchohal_lay.setBackground(getResources().getDrawable(R.drawable.box_red));
                    } else {
                        float size2 = std_size(alcho_list2);
                        Log.e("F size 3", size + "");
                        if (size2 > 11) {
                            sat_sss1 = "no";
                            sat_sss2 = "yes";
                            alchohal_lay.setBackground(getResources().getDrawable(R.drawable.box_red));
                        }
                    }
                }

            } else {

                if (alcho_list.isEmpty()) {
                    if (!alcho_list2.isEmpty()) {
                        float size = std_size(alcho_list2);
                        Log.e("G size 1", size + "");
                        if (size > 14) {
                            sat_sss1 = "no";
                            sat_sss2 = "yes";
                            alchohal_lay.setBackground(getResources().getDrawable(R.drawable.box_red));
                        }
                    }
                } else {
                    float size = std_size(alcho_list);
                    Log.e("G size 2", size + "");
                    if (size > 5) {
                        sat_sss1 = "yes";
                        sat_sss2 = "no";
                        alchohal_lay.setBackground(getResources().getDrawable(R.drawable.box_red));
                    } else {
                        float size2 = std_size(alcho_list2);
                        Log.e("G size 3", size + "");
                        if (size2 > 14) {
                            sat_sss1 = "no";
                            sat_sss2 = "yes";
                            alchohal_lay.setBackground(getResources().getDrawable(R.drawable.box_red));
                        }
                    }
                }

            }


        } catch (Exception e) {
            Log.e("diet_list error", e + "");
        }

    }

    float std_size(List<Alchohal_tracker_model> alcho_list) {
        float Sf_get_sat_size1 = 0.0f;
        for (int i = 0; i < alcho_list.size(); i++) {
            float f_get_sat_size = Float.parseFloat(alcho_list.get(i).getStd_size_drinks());
            Sf_get_sat_size1 = Sf_get_sat_size1 + f_get_sat_size;
        }
        return Sf_get_sat_size1;
    }

    class GetPercentages extends AsyncTask<String, Void, String> {
        String result;
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(LogActivity.this);
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
        final Dialog dialogstatus = new Dialog(LogActivity.this, R.style.CustomDialog);
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
                    new MyIntent(LogActivity.this, Profile_Step1.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("2")){
                    new MyIntent(LogActivity.this, Profile_Step2.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("3")){
                    new MyIntent(LogActivity.this, Profile_Step3.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("4")){
                    new MyIntent(LogActivity.this, Profile_Menstrul_Cycle_4.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("5")){
                    new MyIntent(LogActivity.this, Profile_Breast_Feeding_5.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("6")){
                    new MyIntent(LogActivity.this, Profile_Child_Age_6.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("7")){
                    new MyIntent(LogActivity.this, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("8")){
                    new MyIntent(LogActivity.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("9")){
                    new MyIntent(LogActivity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("10")){
                    new MyIntent(LogActivity.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("11")){
                    new MyIntent(LogActivity.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("12")){
                    new MyIntent(LogActivity.this, Smoking.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("13")){
                    new MyIntent(LogActivity.this, Alcohol.class, R.anim.enter, R.anim.exit);
                }else  if (goKey.equals("14")){
                    new MyIntent(LogActivity.this, Edit_profile_Activity.class, R.anim.enter, R.anim.exit);
                }
            }
        });



        dialogstatus.show();
    }


    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(LogActivity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(LogActivity.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {

        }
        if (view == plans_L) {
            new MyIntent(LogActivity.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(LogActivity.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == exerciesplan) {
           /* SharedPreferences sp = getSharedPreferences("Excercise_plan",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("logKey","log");
            editor.commit();*/
            new MyIntent(LogActivity.this, ExcerciseLogActivity.class, R.anim.enter, R.anim.exit);


        }
        if (view == dietplan) {
            new MyIntent(LogActivity.this, DietLog_activity.class, R.anim.enter, R.anim.exit);
        }
        if (view == Treatment) {
            new AllSharedPrefrences(LogActivity.this).Prescription_key("2");
            new MyIntent(LogActivity.this, Upload_file_list.class, R.anim.enter, R.anim.exit);
        }
        if (view == bpplan) {
            new MyIntent(LogActivity.this, BP_log_Activity.class, R.anim.enter, R.anim.exit);
        }
        if (view == sugarplan) {
            new MyIntent(LogActivity.this, Sugar_log_Activity.class, R.anim.enter, R.anim.exit);
        }
        if (view == weighttplan) {
            if (diet_weight_loss.equals("yes")) {
                new MyIntent(LogActivity.this, WeightLog_Activity.class, R.anim.enter, R.anim.exit);
            } else {
                new MyIntent(LogActivity.this, Weight_Log_Now.class, R.anim.enter, R.anim.exit);
            }
        }
        if (view == eat_out_plan) {
            if (cust_key.equals("1")) {
                new MyIntent(LogActivity.this, Eat_Out_Snacker.class, R.anim.enter, R.anim.exit);
                SharedPreferences sp = getSharedPreferences("eat_key", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Eatkey", "LogKey");
                editor.commit();
            }
//            else {
//                Toast.makeText(this, "First fill Customised diet plan in plans", Toast.LENGTH_SHORT).show();
//            }
        }
        if (view == alchohal_lay) {
            if (sat_sss1.equals("yes") || sat_sss2.equals("yes")) {
                check_alco();
            } else {
                new MyIntent(LogActivity.this, Alcohol_Tracker.class, R.anim.enter, R.anim.exit);
                SharedPreferences sp = getSharedPreferences("eat_key", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Eatkey", "LogKey");
                editor.commit();
            }
        }

    }

    void check_alco() {

        final Dialog dialog = new Dialog(LogActivity.this);
        dialog.setContentView(R.layout.alco);
        TextView xx = (TextView) dialog.findViewById(R.id.text_popup);
        if (sat_sss1.equals("yes")) {
            xx.setText("You already consumed today's standard size of alcohol");
        } else if (sat_sss2.equals("yes")) {
            xx.setText("You already consumed 7 days standard size of alcohol");
        }
        dialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyIntent(LogActivity.this, Alcohol_Tracker.class, R.anim.enter, R.anim.exit);
                SharedPreferences sp = getSharedPreferences("eat_key", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Eatkey", "LogKey");
                editor.commit();
                dialog.dismiss();
            }
        });


        dialog.show();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(LogActivity.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
    }
}
