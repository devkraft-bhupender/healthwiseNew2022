package com.hwi.health.Activitys.Plans;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import java.util.ArrayList;

public class CancerPlan extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Button yes,no;
    String gender, age;
    Dialog dialog;
    int int_age;
    Spinner spinn;
    ArrayList<String> spin_list = new ArrayList<>();
    ArrayAdapter<String> adapter;
    String get_spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Cancer plan");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancer_plan);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
       // spinn = (Spinner) findViewById(R.id.spinn);
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);


       /* spin_list.add("No");
        spin_list.add("Yes");
        adapter = new ArrayAdapter<String>(CancerPlan.this,android.R.layout.simple_dropdown_item_1line,spin_list);
        spinn.setAdapter(adapter);*/
        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            gender = sp.getString("gender", "");
            age = sp.getString("age", "");
            int_age = Integer.parseInt(age);
        } catch (Exception e) {

        }

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
       /* spinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get_spin = spin_list.get(position);
                Log.e("slistttt", get_spin);
                if (get_spin.equals("Yes")){
                    try {
                        if (gender.equals("male")) {
                            if (int_age < 50) {
                                new AllSharedPrefrences(CancerPlan.this).Cancer_key("age less than 50 and male");
                                new MyIntent(CancerPlan.this, CancerResult.class, R.anim.enter, R.anim.exit);
                            } else if (int_age > 50) {
                                new AllSharedPrefrences(CancerPlan.this).Cancer_key("age more than 50 and male");
                                new MyIntent(CancerPlan.this, CancerResult.class, R.anim.enter, R.anim.exit);
                            }
                        } else {
                            if (int_age > 50) {
                                new AllSharedPrefrences(CancerPlan.this).Cancer_key("age more than 50 and female");
                                new MyIntent(CancerPlan.this, CancerResult.class, R.anim.enter, R.anim.exit);

                            }else  if (int_age > 21 || int_age < 50) {
                                AlertDialog();

                            }
                        }

                    } catch (Exception e) {
                        Log.e("Exception", e + "");
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(CancerPlan.this, Treatmentplan.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Treatmentplan.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(CancerPlan.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            new MyIntent(CancerPlan.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            new MyIntent(CancerPlan.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            new MyIntent(CancerPlan.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            new MyIntent(CancerPlan.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == yes) {
            new MyIntent(CancerPlan.this, Cancer_Yes_Result.class, R.anim.enter, R.anim.exit);
        }

        if (view == no) {
           // new MyIntent(CancerPlan.this, Treatmentplan.class, R.anim.enter, R.anim.exit);
            try {
                if (gender.equals("male")) {
                    if (int_age < 50) {
                        new AllSharedPrefrences(CancerPlan.this).Cancer_key("age less than 50 and male");
                        new MyIntent(CancerPlan.this, CancerResult.class, R.anim.enter, R.anim.exit);
                    } else if (int_age > 50) {
                        new AllSharedPrefrences(CancerPlan.this).Cancer_key("age more than 50 and male");
                        new MyIntent(CancerPlan.this, CancerResult.class, R.anim.enter, R.anim.exit);
                    }
                } else {
                    if (int_age > 50) {
                        new AllSharedPrefrences(CancerPlan.this).Cancer_key("age more than 50 and female");
                        new MyIntent(CancerPlan.this, CancerResult.class, R.anim.enter, R.anim.exit);

                    }else  if (int_age > 21 || int_age < 50) {
                        AlertDialog();

                    }
                }

            } catch (Exception e) {
                Log.e("Exception", e + "");
            }

        }

    }

    void AlertDialog() {
        dialog = new Dialog(CancerPlan.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.cancerplan_dialog);
        dialog.setCancelable(true);
        Button send = (Button) dialog.findViewById(R.id.send);
        Button close = (Button) dialog.findViewById(R.id.close);
        // final EditText email = (EditText) dialog.findViewById(R.id.email);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AllSharedPrefrences(CancerPlan.this).Cancer_key("age more 21 and less than 50 and female");
                new MyIntent(CancerPlan.this, CancerResult.class, R.anim.enter, R.anim.exit);
                dialog.dismiss();
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
}
