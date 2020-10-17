package com.hwi.health.Activitys.Profile.Second;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

public class Details extends AppCompatActivity implements View.OnClickListener {

    LinearLayout home_L,profile_L,log_L,plans_L,more_L;
        Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Helath Check Up");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        home_L= (LinearLayout)findViewById(R.id.home);
        profile_L= (LinearLayout)findViewById(R.id.profile);
        log_L= (LinearLayout)findViewById(R.id.log);
        plans_L= (LinearLayout)findViewById(R.id.plans);
        more_L= (LinearLayout)findViewById(R.id.more);
        next = (Button) findViewById(R.id.next);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Details.this, User_Profile_Next.class,R.anim.enter2,R.anim.exit2);
    }
    @Override
    public void onClick(View view) {
        if (view==home_L){
            new MyIntent(Details.this, HomeActivity.class,R.anim.enter2,R.anim.exit2);
        }if (view==profile_L){

        }if (view==log_L){
            new MyIntent(Details.this, LogActivity.class,R.anim.enter2,R.anim.exit2);
        }if (view==plans_L){
            new MyIntent(Details.this, PlansActivity.class,R.anim.enter2,R.anim.exit2);
        }if (view==more_L){
            new MyIntent(Details.this, MoreActivity.class,R.anim.enter2,R.anim.exit2);
        }if (view==next){
            new MyIntent(Details.this, DetailNext.class,R.anim.enter,R.anim.exit);
        }
    }
}
