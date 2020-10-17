package com.hwi.health.Activitys.Plans.Excercize_plans;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

public class ExcerciseView extends AppCompatActivity implements View.OnClickListener{

    VideoView videoView;
    String LINK = "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4";
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Exercise Plans");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_view);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        videoView = (VideoView)findViewById(R.id.videoview);
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

        ViewoPlayer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(ExcerciseView.this, ExcerciseList.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(ExcerciseView.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == profile_L) {
            new MyIntent(ExcerciseView.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }if (view == log_L) {
            new MyIntent(ExcerciseView.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == plans_L) {
            new MyIntent(ExcerciseView.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == more_L) {
            new MyIntent(ExcerciseView.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }

    void ViewoPlayer(){
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(LINK);
        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.start();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), ExcerciseList.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
