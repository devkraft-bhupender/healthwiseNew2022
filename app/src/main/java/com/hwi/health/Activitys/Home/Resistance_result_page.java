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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.Resistance_result_Models;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
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

public class Resistance_result_page extends AppCompatActivity implements BaseUrl,View.OnClickListener{
    ProgressDialog pd;
    String randnoo, result, user_id;
    ListView listView;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ArrayList<Resistance_result_Models> list = new ArrayList<>();
    Resistance_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Resistance");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistance_result_page);

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
        try {

            SharedPreferences sp2 = new AllSharedPrefrences(this).UserDataget();
            user_id = sp2.getString("Userid", "");



        } catch (Exception e) {

        }
        RandomNumber rn = new RandomNumber();
        randnoo = rn.randno();
       new Resistance_Task().execute();
        adapter = new Resistance_Adapter();
        listView.setAdapter(adapter);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Resistance_result_page.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Resistance_result_page.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Resistance_result_page.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Resistance_result_page.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Resistance_result_page.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }

    class Resistance_Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Resistance_result_page.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }
        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + Get_Exercise_Info + randnoo);

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

                    JSONArray array = jobj.getJSONArray("exercise_intensity_info");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String check_for_date = object.getString("check_for_date");
                        String upper_body = object.getString("upper_body");
                        String lower_body = object.getString("lower_body");
                        String full_body = object.getString("full_body");
                        String library = object.getString("library");

                        Resistance_result_Models resistance = new Resistance_result_Models(upper_body, lower_body, full_body, library, check_for_date);
                        list.add(resistance);
                        adapter.notifyDataSetChanged();
                    }


                } else {

                    Toast.makeText(Resistance_result_page.this, message, Toast.LENGTH_SHORT).show();
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Resistance_result_page.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
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

    class Resistance_Adapter extends BaseAdapter {

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
            View view = inflater.inflate(R.layout.resistance_result_item, null);
            TextView upper = (TextView) view.findViewById(R.id.Upper);
            TextView lower = (TextView) view.findViewById(R.id.Lower);
            TextView full = (TextView) view.findViewById(R.id.Full);
            TextView library = (TextView) view.findViewById(R.id.Library);
            TextView date = (TextView) view.findViewById(R.id.date);

            Resistance_result_Models data = list.get(position);
            upper.setText(data.getUpper());
            lower.setText(data.getLower());
            full.setText(data.getFull());
            library.setText(data.getLibrary());
            date.setText(data.getDate());
            return view;
        }
    }
}
