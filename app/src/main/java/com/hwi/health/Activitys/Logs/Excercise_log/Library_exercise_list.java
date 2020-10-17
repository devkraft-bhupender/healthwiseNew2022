package com.hwi.health.Activitys.Logs.Excercise_log;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hwi.health.Models.Library_exercise_models;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Library_exercise_list extends AppCompatActivity implements View.OnClickListener{

    ListView video_list;
    ArrayList<Library_exercise_models> list = new ArrayList<>();
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Library_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Exercise Log");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_exercise_list);
        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        video_list = (ListView) findViewById(R.id.list_item);

       /* list.add(new Library_exercise_models("HealthWise Indian Preventive Services - what is on offer?","http://img.youtube.com/vi/P0JvUDDGauw/0.jpg","1","P0JvUDDGauw"));
        list.add(new Library_exercise_models("Dr Binita on why HealthWise Indian","http://img.youtube.com/vi/DKbj_16eigg/0.jpg","2","DKbj_16eigg"));
        list.add(new Library_exercise_models("Be a HealthWise Indian","http://img.youtube.com/vi/Cq02EmEJBt8/0.jpg","3","Cq02EmEJBt8"));
       */ list.add(new Library_exercise_models("Bent over rows","http://img.youtube.com/vi/fmlcdd1arHM/0.jpg","4","fmlcdd1arHM"));
       /* list.add(new Library_exercise_models("HWI Upper Body Workout at home","http://img.youtube.com/vi/wulb-euK0g0/0.jpg","5","wulb-euK0g0"));
        list.add(new Library_exercise_models("HWI Lower Body strength workout","http://img.youtube.com/vi/nSOfiYn3v-Q/0.jpg","6","nSOfiYn3v-Q"));
        list.add(new Library_exercise_models("HWI Full Body workout for beginners","http://img.youtube.com/vi/FR7h1NRSTLI/0.jpg","7","FR7h1NRSTLI"));
       */ list.add(new Library_exercise_models("cool down","http://img.youtube.com/vi/0QHDGNn0HKo/0.jpg","8","0QHDGNn0HKo"));
        list.add(new Library_exercise_models("Crunches","http://img.youtube.com/vi/vJQLm8peWIo/0.jpg","9","vJQLm8peWIo"));
        list.add(new Library_exercise_models("Plank","http://img.youtube.com/vi/TdIBRSr-SsA/0.jpg","10","TdIBRSr-SsA"));
        list.add(new Library_exercise_models("Lunges","http://img.youtube.com/vi/s_WO-rr5IUM/0.jpg","11","s_WO-rr5IUM"));
        list.add(new Library_exercise_models("Push ups","http://img.youtube.com/vi/TNPzQQnGOLM/0.jpg","12","TNPzQQnGOLM"));
        list.add(new Library_exercise_models("Bridge","http://img.youtube.com/vi/uED6RucZMP0/0.jpg","13","uED6RucZMP0"));
        list.add(new Library_exercise_models("Shoulder press","http://img.youtube.com/vi/PQlBMtk47TM/0.jpg","14","PQlBMtk47TM"));
        list.add(new Library_exercise_models("Squats","http://img.youtube.com/vi/jjUAYT0-_Tg/0.jpg","15","jjUAYT0-_Tg"));
        list.add(new Library_exercise_models("Biceps curls","http://img.youtube.com/vi/xtSKGaBcgOY/0.jpg","16","xtSKGaBcgOY"));
        adapter = new Library_adapter();
        video_list.setAdapter(adapter);

        video_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Library_exercise_models get_dta = list.get(position);
                new MyIntent(Library_exercise_list.this, ExcerciseView_Librarylog.class, R.anim.enter, R.anim.exit);
               /* SharedPreferences sp = getSharedPreferences("Log_library",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Log_url_key",get_dta.getName());
                editor.putString("play_key",get_dta.getId());
                editor.putString("link",get_dta.getLink());
                editor.putString("exercise_type","R");*/
                SharedPreferences sp = getSharedPreferences("Log_url",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Log_url_key","Library exercise");
                editor.putString("play_key","4");
                editor.putString("link",get_dta.getLink());
                editor.putString("exercise_type","R");
                Log.d("gvfgffh",get_dta.getLink());
                editor.commit();
            }
        });

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Library_exercise_list.this, ExcerciseList_log.class, R.anim.enter2, R.anim.exit2);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), ExcerciseList_log.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Library_exercise_list.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == profile_L) {
            new MyIntent(Library_exercise_list.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }if (v == log_L) {
            new MyIntent(Library_exercise_list.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == plans_L) {
            new MyIntent(Library_exercise_list.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == more_L) {
            new MyIntent(Library_exercise_list.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }
    class Library_adapter extends BaseAdapter{

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
            View view = inflater.inflate(R.layout.library_item,null);
            ImageView image = (ImageView) view.findViewById(R.id.video_image);
            TextView video_name = (TextView) view.findViewById(R.id.video_name);
            Library_exercise_models data = list.get(position);
            video_name.setText(data.getName());
            Picasso.get().load(data.getImage()).error(R.drawable.excer).into(image);
            return view;
        }
    }
}
