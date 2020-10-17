package com.hwi.health.Activitys;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.hwi.health.R;
import com.hwi.health.Activitys.Login.LoginActivity;
import com.hwi.health.Activitys.Signup.CreateAccount;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.gcm.GCMRegistrationIntentService;

public class AboutApp extends AppCompatActivity implements View.OnClickListener{
    ActionBar ab;
    VideoView videoView;
    String LINK = "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4",token;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    LinearLayout signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        videoView = (VideoView)findViewById(R.id.videoview);
        signup = (LinearLayout)findViewById(R.id.signup) ;
        login = (LinearLayout)findViewById(R.id.login) ;

//       ViewoPlayer();


        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        permition();



        //----------------------Gcm----------------//

        //Initializing our broadcast receiver
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {

            //When the broadcast received
            //We are sending the broadcast from GCMRegistrationIntentService

            @Override
            public void onReceive(Context context, Intent intent) {
                //If the broadcast has received with success
                //that means device is registered successfully
                if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_SUCCESS)){
                    //Getting the registration token from the intent
                     token = intent.getStringExtra("token");
                    Log.e("tokennn...","....... "+token);

                    //Displaying the token as toast
                    // Toast.makeText(getApplicationContext(), "Registration token:" + token, Toast.LENGTH_LONG).show();

                    //if the intent is not with success then displaying error messages
                } else if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_ERROR)){
                    Toast.makeText(getApplicationContext(), "GCM registration error!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error occurred", Toast.LENGTH_LONG).show();
                }
            }
        };

        //Checking play service is available or not
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        //if play service is not available
        if(ConnectionResult.SUCCESS != resultCode) {
            //If play service is supported but not installed
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                //Displaying message that play service is not installed
                Toast.makeText(getApplicationContext(), "Google Play Service is not install/enabled in this device!", Toast.LENGTH_LONG).show();
                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());

                //If play service is not supported
                //Displaying an error message
            } else {
                Toast.makeText(getApplicationContext(), "This device does not support for Google Play Service!", Toast.LENGTH_LONG).show();
            }

            //If play service is available
        } else {
            //Starting intent to register device
            Intent itent = new Intent(this, GCMRegistrationIntentService.class);
            startService(itent);
        }



    }

    @Override
    public void onClick(View view) {
        if (view == login){


            new MyIntent(AboutApp.this,LoginActivity.class,R.anim.enter,R.anim.exit);
        } if (view == signup){


            new MyIntent(AboutApp.this,CreateAccount.class,R.anim.enter,R.anim.exit);
        }
    }

    void ViewoPlayer(){
//        MediaController mc = new MediaController(this);
//        mc.setAnchorView(videoView);
//        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(LINK);
//        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.start();
    }



    void permition() {
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
