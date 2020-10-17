package com.hwi.health.Usages;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Profile.First.Alcohol;

/**
 * Created by PAWAN on 17-05-2017.
 */

public class MyIntent {

    public  MyIntent(Activity ctx, Class c,int f,int s,int fin) {
        Intent in = new Intent(ctx, c);
        ctx.startActivity(in);
        ctx.overridePendingTransition(f, s);
        }

    public  MyIntent(Activity ctx, Class c,int f,int s){
        Intent in = new Intent(ctx,c);
        ctx.startActivity(in);
        ctx.overridePendingTransition(f, s);
        ctx.finish();
    }


    @SuppressLint("WrongConstant")
    public  MyIntent(Activity ctx, Class c, int f, int s, boolean profile){


        Intent in = new Intent(ctx,c);

        if((ctx instanceof Alcohol) && (c == HomeActivity.class)) {
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        }
        ctx.startActivity(in);
        ctx.overridePendingTransition(f, s);
        if(profile==true)
        {

        }
        else
        ctx.finish();
    }
    public  MyIntent(Activity ctx, Class c,int f,int s,String ed){
        SharedPreferences sp  = ctx.getSharedPreferences("KEY",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key",ed);
        editor.commit();

        Intent in = new Intent(ctx,c);
        in.putExtra("get",ed);
        ctx.startActivity(in);
        ctx.overridePendingTransition(f, s);
        }
}
