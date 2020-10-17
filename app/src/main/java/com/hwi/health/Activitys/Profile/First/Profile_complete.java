package com.hwi.health.Activitys.Profile.First;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.Activitys.Profile.advanced_profile.Body_PARAMETERS;

public class Profile_complete extends AppCompatActivity {
    Button ok,no;
    String keyy;
    RelativeLayout hint_layout;
    ImageView start,close;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Complete profile");
        setContentView(R.layout.activity_profile_complete);
        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        ok = (Button) findViewById(R.id.ok);
        no = (Button) findViewById(R.id.no);
        start = (ImageView) findViewById(R.id.start);
        close = (ImageView) findViewById(R.id.close);
        hint_layout = (RelativeLayout) findViewById(R.id.bottom);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp2 = getSharedPreferences("HintPage",MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sp2.edit();
                editor2.putString("hint_key","2");
                editor2.commit();

                SharedPreferences sp = getSharedPreferences("adv", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("adv_key", "done");
                editor.commit();
                new MyIntent(Profile_complete.this, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);

            }
        });
        SharedPreferences sp = getSharedPreferences("HintPage",MODE_PRIVATE);
        keyy = sp.getString("hint_key","");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keyy.equals("2")) {
                    SharedPreferences sp = getSharedPreferences("adv", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("adv_key", "done");
                    editor.commit();
                    new MyIntent(Profile_complete.this, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);
                    hint_layout.setVisibility(View.GONE);
                }else {
                   hint_layout.setVisibility(View.VISIBLE);
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyIntent(Profile_complete.this, User_Profile.class, R.anim.enter, R.anim.exit);
            }
        });
    }


    void AlertDialog() {
        dialog = new Dialog(Profile_complete.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.profile_popup_page);
        dialog.setCancelable(true);

        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        TextView text_popup = (TextView) dialog.findViewById(R.id.text_popup);
        Button ok = (Button) dialog.findViewById(R.id.ok);
        text_popup.setText("Please get the results and complete your advance profile, completing the advanced profile will give you detailed health profile and heart risk profile.");

        close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
        ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new MyIntent(Profile_complete.this, User_Profile.class, R.anim.enter, R.anim.exit);
                    }
                });


        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Profile_complete.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), User_Profile.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
