package com.hwi.health.Activitys.Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.Aerobic_result_Models;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Logs.Aerobics_log.Aerobics_Exercise_log;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

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

import javax.net.ssl.HttpsURLConnection;

public class Aerobic_result_page extends AppCompatActivity implements BaseUrl,View.OnClickListener {
    String randnoo, result, user_id;
    ListView listView;
    ArrayList<Aerobic_result_Models> list = new ArrayList<>();
    Aerobic_Adapter adapter;
    ProgressDialog pd;
    TextView adv_text;
    Button btn_profile;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Aerobic");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aerobic_result_page);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        listView = (ListView) findViewById(R.id.list_item);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        adv_text = (TextView) findViewById(R.id.adv_text);
        btn_profile = (Button) findViewById(R.id.btn_profile);


        try {

            SharedPreferences sp2 = new AllSharedPrefrences(this).UserDataget();
            user_id = sp2.getString("Userid", "");



        } catch (Exception e) {

        }
        RandomNumber rn = new RandomNumber();
        randnoo = rn.randno();
        new Aerobic_Task().execute();
        adapter = new Aerobic_Adapter();
        listView.setAdapter(adapter);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        btn_profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Aerobic_result_page.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Aerobic_result_page.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Aerobic_result_page.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Aerobic_result_page.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Aerobic_result_page.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == btn_profile) {
            new MyIntent(Aerobic_result_page.this, Aerobics_Exercise_log.class, R.anim.enter, R.anim.exit);
        }
    }

    class Aerobic_Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Aerobic_result_page.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }
        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + Get_Exercise_Intensity_Info + randnoo);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);


                Log.e("params..............", postDataParams.toString());


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
            Log.e("result....", result + "...");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");


                if (status.equals("1")) {
                    listView.setVisibility(View.VISIBLE);
                    adv_text.setVisibility(View.GONE);
                    btn_profile.setVisibility(View.GONE);
                    JSONArray array = jobj.getJSONArray("exercise_intensity_info");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String duration = object.getString("duration");
                        String heart_rate = object.getString("heart_rate");
                        String excercise_intensity = object.getString("excercise_intensity");
                        String actually_exercise = object.getString("actually_exercise");
                        String date = object.getString("date");
                        String id = object.getString("id");
                        Aerobic_result_Models aerobic = new Aerobic_result_Models(duration, heart_rate,excercise_intensity,actually_exercise,date,id);
                        list.add(aerobic);
                        adapter.notifyDataSetChanged();
                    }


                } else {


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Aerobic_result_page.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    class Aerobic_Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
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
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.aerobic_result_item, null);
            TextView duration = (TextView) view.findViewById(R.id.duration);
            TextView heart_rate = (TextView) view.findViewById(R.id.heart_rate);
            TextView excercise_intensity = (TextView) view.findViewById(R.id.excercise_intensity);
            TextView actually_exercise = (TextView) view.findViewById(R.id.actually_exercise);
            TextView date = (TextView) view.findViewById(R.id.date);

            Aerobic_result_Models data = list.get(position);
            duration.setText(data.getDuration());
            heart_rate.setText(data.getHeart_rate());
            excercise_intensity.setText(data.getExcercise_intensity());
            actually_exercise.setText(data.getActually_exercise());
            date.setText(data.getDate());
            return view;
        }
    }
}
