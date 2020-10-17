package com.hwi.health.Activitys.Logs.Aerobics_log;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.Excercise_log.ExcerciseLogActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
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
import java.util.Timer;

import javax.net.ssl.HttpsURLConnection;

public class Aerobics_Exercise_log extends AppCompatActivity implements View.OnClickListener, BaseUrl {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    TextView when_start, timer;
    Button start, stop;
    Dialog dialog;
    Dialog dialog2, dialog3;
    ImageView giff;
    int count;
    Timer time;
    String get_time, get_heart, get_actually, get_exercise, result, randnoo, user_id, get_heart2, randnoo2, result2;
    ArrayList<String> actually_list = new ArrayList<>();
    ArrayList<String> exercise_list = new ArrayList<>();
    LinearLayout lin_time;
    ProgressDialog pd;
    Spinner actually, exercise_feel;
    ArrayAdapter<String> adapter_actually, adapter_exercise;
    Chronometer simpleChronometer;
    RelativeLayout back;
    Animation shake;
    String get_timess;
    Handler mHandler;
    Runnable rr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Exercise Log");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aerobics__exercise_log);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        time = new Timer();
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        when_start = (TextView) findViewById(R.id.when);
       giff = (ImageView) findViewById(R.id.giff);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        lin_time = (LinearLayout) findViewById(R.id.lin_time);
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        back = (RelativeLayout) findViewById(R.id.back);
        when_start.setText(Html.fromHtml("<u>" + "Aerobics Exercise Start" + "</u>"));
        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();

        try {

            Glide.with(Aerobics_Exercise_log.this).load("https://media.giphy.com/media/km0viH8HtjRII/giphy.gif").into(giff);
            //giff.set
        } catch (Exception e) {
            e.printStackTrace();
        }





        user_id = sp.getString("Userid", "");
        dialog2 = new Dialog(Aerobics_Exercise_log.this, R.style.CustomDialog);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        stop.setOnClickListener(this);
        start.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Aerobics_Exercise_log.this, ExcerciseLogActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(Aerobics_Exercise_log.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(Aerobics_Exercise_log.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(Aerobics_Exercise_log.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(Aerobics_Exercise_log.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(Aerobics_Exercise_log.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == start) {
            stop.setVisibility(View.VISIBLE);
            lin_time.setVisibility(View.VISIBLE);
            simpleChronometer.setBase(SystemClock.elapsedRealtime());
            simpleChronometer.start();
            shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            back.setAnimation(shake);
            get_timess = simpleChronometer.getText().toString();

//             when_start.setText("When Stop");
            start.setVisibility(View.GONE);

            simpleChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if( chronometer.getText().toString().equalsIgnoreCase("05:00")){
                        simpleChronometer.stop();

                        get_time = simpleChronometer.getText().toString();
                        AlertDialog();
                    }


                }
            });
          /*  mHandler = new Handler();

            final Runnable r = new Runnable() {
                public void run() {
                    //  Toast.makeText(Aerobics_Exercise_log.this, "runnn", Toast.LENGTH_SHORT).show();

                    get_time = simpleChronometer.getText().toString();

                    Log.e("get_timesssss",get_timess);
                    simpleChronometer.stop();
                    try {
                        if (dialog2.isShowing()) {
                            mHandler.removeCallbacksAndMessages(rr);
                        } else {
                            mHandler.removeCallbacksAndMessages(rr);
                            shake.cancel();
                            AlertDialog2();
                        }
                    } catch (Exception e) {
                        Log.e("eeeeerrrr", e + "");
                    }


                    mHandler.postDelayed(this, 30000);

//300000
                }
            };
            mHandler.postDelayed(r, 30000);

*/

        }
        if (view == stop) {
            //   time.cancel();
           // mHandler.removeCallbacksAndMessages(rr);
            //mHandler.removeCallbacks(r);
            shake.cancel();
            start.setVisibility(View.VISIBLE);
            stop.setVisibility(View.GONE);
            lin_time.setVisibility(View.GONE);
            get_time = simpleChronometer.getText().toString();

            Log.e("get_time....", get_time + ".." + get_actually + ".." + get_exercise);

            simpleChronometer.stop();
            //   simpleChronometer.setBase(SystemClock.elapsedRealtime());
            AlertDialog();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), ExcerciseLogActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void AlertDialog() {

        dialog = new Dialog(Aerobics_Exercise_log.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.aerobics_dialog_page);
        dialog.setCancelable(true);
        actually = (Spinner) dialog.findViewById(R.id.actually);
        exercise_feel = (Spinner) dialog.findViewById(R.id.exercise_feel);
        final EditText heart_rate = (EditText) dialog.findViewById(R.id.heart_rate);
        Button submit = (Button) dialog.findViewById(R.id.submit);
        actually_list.add("yes");
        actually_list.add("no");

        adapter_actually = new ArrayAdapter<>(Aerobics_Exercise_log.this, android.R.layout.simple_spinner_dropdown_item, actually_list);
        actually.setAdapter(adapter_actually);
        actually.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_actually = actually.getSelectedItem().toString();
                Log.e("get_exercise", get_actually);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        exercise_list.add("Easy");
        exercise_list.add("Slightly tough");
        exercise_list.add("Tough");
        exercise_list.add("Strenuous");


        adapter_exercise = new ArrayAdapter<>(Aerobics_Exercise_log.this, android.R.layout.simple_spinner_dropdown_item, exercise_list);
        exercise_feel.setAdapter(adapter_exercise);

        exercise_feel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_exercise = exercise_feel.getSelectedItem().toString();
                Log.e("get_exercise", get_exercise);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();
                get_heart = heart_rate.getText().toString();
                new Task().execute();
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    void AlertDialog2() {
        dialog2.setContentView(R.layout.heartreat_popup);
        dialog2.setCancelable(true);

        final EditText heart_rate = (EditText) dialog2.findViewById(R.id.heart_rate);
        Button submit = (Button) dialog2.findViewById(R.id.submit);
        ImageView close = (ImageView) dialog2.findViewById(R.id.close);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomNumber rn = new RandomNumber();
                randnoo2 = rn.randno();
                get_heart2 = heart_rate.getText().toString();
                new Task2().execute();
                dialog2.dismiss();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });


        dialog2.show();
    }


    void AlertDialogFinish() {
        dialog3 = new Dialog(Aerobics_Exercise_log.this, R.style.CustomDialog);
        dialog3.setContentView(R.layout.exercise_log_popup);
        dialog3.setCancelable(true);

        ImageView close = (ImageView) dialog3.findViewById(R.id.close);
        Button yes = (Button) dialog3.findViewById(R.id.yes);
        Button no = (Button) dialog3.findViewById(R.id.no);
        shake.cancel();
        start.setVisibility(View.VISIBLE);
        stop.setVisibility(View.GONE);
        lin_time.setVisibility(View.GONE);
        simpleChronometer.stop();
        close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new MyIntent(Aerobics_Exercise_log.this, ExcerciseLogActivity.class, R.anim.enter, R.anim.exit);
                        dialog3.dismiss();
                    }
                });
        yes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();

                        stop.setVisibility(View.VISIBLE);
                        lin_time.setVisibility(View.VISIBLE);
                        simpleChronometer.setBase(SystemClock.elapsedRealtime());
                        simpleChronometer.start();
                        shake = AnimationUtils.loadAnimation(Aerobics_Exercise_log.this, R.anim.shake);
                        back.setAnimation(shake);
                        get_timess = simpleChronometer.getText().toString();

//             when_start.setText("When Stop");
                        start.setVisibility(View.GONE);
                        simpleChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

                            @Override
                            public void onChronometerTick(Chronometer chronometer) {
                                if( chronometer.getText().toString().equalsIgnoreCase("05:00")){
                                    simpleChronometer.stop();
                                    AlertDialog();
                                }


                            }
                        });


                    }
                });
        no.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new MyIntent(Aerobics_Exercise_log.this, ExcerciseLogActivity.class, R.anim.enter, R.anim.exit);
                        dialog3.dismiss();
                    }
                });


        dialog3.show();
    }


    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Aerobics_Exercise_log.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + Insert_Exercise_Intensity_Info + randnoo);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);
                postDataParams.put("duration", get_time);
                postDataParams.put("heart_rate", get_heart);
                postDataParams.put("excercise_intensity", get_exercise);
                postDataParams.put("actually_exercise", get_actually);


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
            try {
                Log.e("result..............", result);
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");


                if (status.equals("1")) {
                    Toast.makeText(Aerobics_Exercise_log.this, message, Toast.LENGTH_SHORT).show();
                   // new MyIntent(Aerobics_Exercise_log.this, ExcerciseLogActivity.class, R.anim.enter, R.anim.exit);
                    AlertDialogFinish();
                    simpleChronometer.setText("00:00");

                } else {

                    Toast.makeText(Aerobics_Exercise_log.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("VolleyError= ", e + "");
            }
        }
    }

    class Task2 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Aerobics_Exercise_log.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result2 = "";

            try {
                URL url = new URL(URLS + insert_heart_rate + randnoo2);
                Log.e("urll", URLS + insert_heart_rate + randnoo2);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);
                postDataParams.put("heart_rate", get_heart2);

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
                        result2 += line;
                    }
                } else {
                    result2 = "";
                }
            } catch (Exception e) {
                Log.e("ERRR2", e + "");
            }
            return result2;
        }

        @Override
        protected void onPostExecute(String s) {

            pd.dismiss();
            try {
                Log.e("result..............", result2);
                JSONObject jobj = new JSONObject(result2);
                String status = jobj.getString("status");
                String message = jobj.getString("message");


                if (status.equals("1")) {
                    //Toast.makeText(Aerobics_Exercise_log.this, message, Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();
                    AlertDialogFinish();

                } else {

                    Toast.makeText(Aerobics_Exercise_log.this, message, Toast.LENGTH_SHORT).show();
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
