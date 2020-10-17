package com.hwi.health.Activitys.More;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Plans.Treatmentplan;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

public class VideoConsultatn extends AppCompatActivity implements View.OnClickListener {

    LinearLayout hWI_Prevention, quit_Smoking, Diabetes, alcohol, Pre_conception, Weight_loss;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String Reports_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("video consultant");
        setContentView(R.layout.activity_video_consultatn);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        hWI_Prevention = (LinearLayout) findViewById(R.id.bmi_lay);
        quit_Smoking = (LinearLayout) findViewById(R.id.heartcheck_lay);
        Diabetes = (LinearLayout) findViewById(R.id.heartrisk_lay);
        alcohol = (LinearLayout) findViewById(R.id.familyoil_lay);
        Pre_conception = (LinearLayout) findViewById(R.id.eatout_lay);
        Weight_loss = (LinearLayout) findViewById(R.id.alchohal_lay);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        hWI_Prevention.setOnClickListener(this);
        quit_Smoking.setOnClickListener(this);
        Diabetes.setOnClickListener(this);
        alcohol.setOnClickListener(this);
        Pre_conception.setOnClickListener(this);
        Weight_loss.setOnClickListener(this);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            Reports_key = sp.getString("key_rep", "");
        }catch (Exception e){

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Reports_key.equals("1")){
            new MyIntent(VideoConsultatn.this, Treatmentplan.class, R.anim.enter2, R.anim.exit2);
        }
        else {
            new MyIntent(VideoConsultatn.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (Reports_key.equals("1")) {
                Intent in = new Intent(getApplicationContext(), Treatmentplan.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }else {
                Intent in = new Intent(getApplicationContext(), MoreActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == hWI_Prevention) {
            Intent intent = new Intent(VideoConsultatn.this, VideoConsultatn_dialog.class);
            intent.putExtra("key", "hWI_Prevention");
            startActivity(intent);
            finish();
        }
        if (v == quit_Smoking) {
            Intent intent = new Intent(VideoConsultatn.this, VideoConsultatn_dialog.class);
            intent.putExtra("key", "quit_Smoking");
            startActivity(intent);
            finish();
        }
        if (v == Diabetes) {
            Intent intent = new Intent(VideoConsultatn.this, VideoConsultatn_dialog.class);
            intent.putExtra("key", "Diabetes");
            startActivity(intent);
            finish();
        }
        if (v == alcohol) {
            Intent intent = new Intent(VideoConsultatn.this, VideoConsultatn_dialog.class);
            intent.putExtra("key", "alcohol");
            startActivity(intent);
            finish();
        }
        if (v == Pre_conception) {
            Intent intent = new Intent(VideoConsultatn.this, VideoConsultatn_dialog.class);
            intent.putExtra("key", "Pre conception");
            startActivity(intent);
            finish();
        }
        if (v == Weight_loss) {
            Intent intent = new Intent(VideoConsultatn.this, VideoConsultatn_dialog.class);
            intent.putExtra("key", "Weight loss");
            startActivity(intent);
            finish();
        }
        if (v == home_L) {
            new MyIntent(VideoConsultatn.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(VideoConsultatn.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(VideoConsultatn.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(VideoConsultatn.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(VideoConsultatn.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
