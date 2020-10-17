package com.hwi.health.Activitys.ForgotPassword;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hwi.health.Activitys.Login.LoginActivity;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

public class Forgot_Password_Done extends AppCompatActivity implements View.OnClickListener {
    ActionBar ab;
    LinearLayout backTosignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password__done);

        backTosignin = (LinearLayout) findViewById(R.id.backTosignin);

        backTosignin.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Forgot_Password_Done.this, LoginActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == backTosignin) {
            new MyIntent(Forgot_Password_Done.this, LoginActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }
}
