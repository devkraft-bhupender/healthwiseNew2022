package com.hwi.health.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.sqlite_database.DBHelper;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Splash extends AppCompatActivity implements BaseUrl {

    ActionBar ab;
    Thread splashTread;
    String result, randnoo;
    String current_date, key;
    String get_date = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //test
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();

        setContentView(R.layout.activity_splash);
        RandomNumber rn = new RandomNumber();
        randnoo = rn.randno();
        hash();

        SharedPreferences sp= new AllSharedPrefrences(Splash.this).AutoLoginGet();
        key = sp.getString("key","");

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        current_date = df.format(date);

        Log.e("Time", "current_date: " + current_date);

        SharedPreferences sp2 = getSharedPreferences("current_date",MODE_PRIVATE);
        get_date = sp2.getString("datee","");

        loadDb lDb=new loadDb();
        lDb.execute();



           if (key.equals("Login")){
               Intent intent = new Intent(Splash.this, HomeActivity.class);
               overridePendingTransition(R.anim.enter, R.anim.exit);
               startActivity(intent);
               Splash.this.finish();
           }else {
               next();
           }





    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }


    void next() {
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splash.this, AboutApp.class);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    startActivity(intent);
                    Splash.this.finish();

                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splash.this.finish();
                }

            }
        };
        splashTread.start();

    }

    void hash() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.hwi.health", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
         //       Toast.makeText(this, "hash key = "+ something, Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }


    class loadDb extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... params) {


            final DBHelper helper = new DBHelper(getApplicationContext(), DBHelper.DataBaseName, null,
                    DBHelper.Version);
            try {


                helper.fillFromCsv();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

        }
    }


}
