package com.hwi.health.Activitys.Plans.Excercize_plans;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import java.util.ArrayList;

public class ExcercisePlan_Home extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Button ok;
    TextView text_link, activity_level;
    ArrayList<String> list_one = new ArrayList<>();

    String get_ans1,get_ans2,get_ans3,get_ans4,get_ans5,get_ans6,get_ans7;
    ArrayAdapter<String> adapter1;
    String key_diet;
    Dialog dialog,dialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Exercise Plans");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_plan__home);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        ok = (Button) findViewById(R.id.ok);
        text_link = (TextView) findViewById(R.id.text_link);
        activity_level = (TextView) findViewById(R.id.activity_level);
        text_link.setText(Html.fromHtml("<u>" + "Click here" + "</u>"));

        try {

            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            activity_level.setText(sp.getString("Activity_Level", "NA"));
            key_diet = sp.getString("key_diet", "");

        } catch (Exception e) {

        }


        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        ok.setOnClickListener(this);
        text_link.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(ExcercisePlan_Home.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), PlansActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(ExcercisePlan_Home.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(ExcercisePlan_Home.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(ExcercisePlan_Home.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(ExcercisePlan_Home.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(ExcercisePlan_Home.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == ok) {
            new AllSharedPrefrences(ExcercisePlan_Home.this).Plan_Ex_key("plan");
            new MyIntent(ExcercisePlan_Home.this, ExcercisePlanActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == text_link) {
            /*String get_link = text_link.getText().toString();
            new AllSharedPrefrences(ExcercisePlan_Home.this).linkplan(get_link,"exc");
            new MyIntent(ExcercisePlan_Home.this, WebView_plan.class, R.anim.enter, R.anim.exit);*/
            AlertDialog();
        }

    }


    void AlertDialog() {

        dialog = new Dialog(ExcercisePlan_Home.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.excercise_question);
        dialog.setCancelable(true);
        final Spinner qus_one = (Spinner) dialog.findViewById(R.id.qus_one);
        final Spinner qus_two = (Spinner) dialog.findViewById(R.id.qus_two);
        final Spinner qus_three = (Spinner) dialog.findViewById(R.id.qus_three);
        final Spinner qus_four = (Spinner) dialog.findViewById(R.id.qus_four);
        final Spinner qus_five = (Spinner) dialog.findViewById(R.id.qus_five);
        final Spinner qus_six = (Spinner) dialog.findViewById(R.id.qus_six);
        final Spinner qus_seven = (Spinner) dialog.findViewById(R.id.qus_seven);


        Button submit = (Button) dialog.findViewById(R.id.submit);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        list_one.add("yes");
        list_one.add("no");

        adapter1 = new ArrayAdapter<>(ExcercisePlan_Home.this, android.R.layout.simple_spinner_dropdown_item, list_one);
        qus_one.setAdapter(adapter1);
        qus_one.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_ans1 = qus_one.getSelectedItem().toString();
                Log.e("get_exercise", get_ans1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        qus_two.setAdapter(adapter1);
        qus_two.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_ans2 = qus_two.getSelectedItem().toString();
                Log.e("get_exercise", get_ans2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        qus_three.setAdapter(adapter1);
        qus_three.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_ans3 = qus_three.getSelectedItem().toString();
                Log.e("get_exercise", get_ans3);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        qus_four.setAdapter(adapter1);
        qus_four.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_ans4 = qus_four.getSelectedItem().toString();
                Log.e("get_exercise", get_ans4);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        qus_five.setAdapter(adapter1);
        qus_five.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_ans5 = qus_five.getSelectedItem().toString();
                Log.e("get_exercise", get_ans5);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        qus_six.setAdapter(adapter1);
        qus_six.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_ans6 = qus_six.getSelectedItem().toString();
                Log.e("get_exercise", get_ans6);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        qus_seven.setAdapter(adapter1);
        qus_seven.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_ans7 = qus_seven.getSelectedItem().toString();
                Log.e("get_exercise", get_ans7);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (get_ans1.equals("yes") || get_ans2.equals("yes") || get_ans3.equals("yes")|| get_ans4.equals("yes")|| get_ans5.equals("yes")|| get_ans6.equals("yes")|| get_ans7.equals("yes")) {
                    AlertDialog2();
                    dialog.dismiss();
                }else {
                    new MyIntent(ExcercisePlan_Home.this, ExcercisePlanActivity.class, R.anim.enter, R.anim.exit);
                }
            }

        });
        dialog.show();
    }

    void AlertDialog2() {

        dialog2 = new Dialog(ExcercisePlan_Home.this, R.style.CustomDialog);
        dialog2.setContentView(R.layout.popup_page);
        dialog2.setCancelable(true);
       TextView text_popup = (TextView) dialog2.findViewById(R.id.text_popup);
       ImageView close = (ImageView) dialog2.findViewById(R.id.close);

       text_popup.setText("you should consult your doctor before exercising and get advice on what type and how much exercise you should do");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyIntent(ExcercisePlan_Home.this, ExcercisePlanActivity.class, R.anim.enter, R.anim.exit);
                dialog.dismiss();
            }
        });

        dialog2.show();
    }
}
