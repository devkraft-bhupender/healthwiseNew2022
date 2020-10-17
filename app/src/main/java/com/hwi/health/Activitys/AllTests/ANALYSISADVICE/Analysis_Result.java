package com.hwi.health.Activitys.AllTests.ANALYSISADVICE;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class Analysis_Result extends AppCompatActivity implements View.OnClickListener {

    TextView bmi, weight_category, yourWC, metabolic_syndrome, Diabetes, Prediabetes, High_BP, heart_risk_score, Anaemia, v_B12, v_D;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("CHECK UP FOR ADULTS");

        super.onCreate(savedInstanceState);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        setContentView(R.layout.activity_analysis__result);
        bmi = (TextView) findViewById(R.id.bmi);
        weight_category = (TextView) findViewById(R.id.weight_category);
        yourWC = (TextView) findViewById(R.id.yourWC);
        metabolic_syndrome = (TextView) findViewById(R.id.metabolic_syndrome);
        Diabetes = (TextView) findViewById(R.id.Diabetes);
        Prediabetes = (TextView) findViewById(R.id.Prediabetes);
        High_BP = (TextView) findViewById(R.id.High_BP);
        heart_risk_score = (TextView) findViewById(R.id.heart_risk_score);
        Anaemia = (TextView) findViewById(R.id.Anaemia);
        v_B12 = (TextView) findViewById(R.id.v_B12);
        v_D = (TextView) findViewById(R.id.v_D);
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

        metabolic_syndrome.setText(Html.fromHtml("Do you have metabolic syndrome?<a href='https:/healthwiseindian.com/obesity-prediabetes-metabolic-syndrome-and-diabetes-the-full-spectrum/target=_blank'>Click here</a>"));

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();

        bmi.setText(sp.getString("BMI", ""));
        weight_category.setText(sp.getString("weight_Category", ""));
        yourWC.setText(sp.getString("your_wc", ""));
        metabolic_syndrome.setText(sp.getString("metabolic", ""));
        Diabetes.setText(sp.getString("Diabetes", ""));
        Prediabetes.setText(sp.getString("Prediabetes", ""));
        High_BP.setText(sp.getString("HighBP", ""));
        heart_risk_score.setText(Html.fromHtml(sp.getString("heart_risk", "")));
        Anaemia.setText(sp.getString("Anaemia", ""));
        v_B12.setText(sp.getString("vb12", ""));
        v_D.setText(sp.getString("vd", ""));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Analysis_Result.this, ANALYSIS_AND_ADVICE_2.class, R.anim.enter2, R.anim.exit2);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), ANALYSIS_AND_ADVICE_2.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Analysis_Result.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Analysis_Result.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Analysis_Result.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Analysis_Result.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Analysis_Result.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
