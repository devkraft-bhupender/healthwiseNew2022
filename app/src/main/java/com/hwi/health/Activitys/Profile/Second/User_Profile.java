package com.hwi.health.Activitys.Profile.Second;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hwi.health.Activitys.Profile.First.Alcohol;
import com.hwi.health.Activitys.Profile.First.Profile_Blood_Suger_9;
import com.hwi.health.Activitys.Profile.First.Profile_Breast_Feeding_5;
import com.hwi.health.Activitys.Profile.First.Profile_Child_Age_6;
import com.hwi.health.Activitys.Profile.First.Profile_Diabetic_7;
import com.hwi.health.Activitys.Profile.First.Profile_Food_habits_11;
import com.hwi.health.Activitys.Profile.First.Profile_Insulin_8;
import com.hwi.health.Activitys.Profile.First.Profile_Menstrul_Cycle_4;
import com.hwi.health.Activitys.Profile.First.Profile_Step2;
import com.hwi.health.Activitys.Profile.First.Profile_Step3;
import com.hwi.health.Activitys.Profile.First.Smoking;
import com.hwi.health.FileUploader;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.ActiveModel;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.Excercize_plans.ExcercisePlanActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Profile.First.Profile_Active_10;
import com.hwi.health.Activitys.Profile.First.Profile_Step1;
import com.hwi.health.Activitys.Profile.First.Profile_intent;
import com.hwi.health.edit_profile.Edit_profile_Activity;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class User_Profile extends AppCompatActivity implements View.OnClickListener, BaseUrl {
    ImageView health, heard_risk;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L, healthcheck;
    ImageView userimage;
    //ProgressBar progressBar;
    ProgressDialog pd;
    TextView target_activity, heard_risk_plus, health_plus;
    TextView username, user_age_gender, current_weight, targe_weight, current_BMI, target_BMI, current_activity, adanance_Complete, Complete;
    LinearLayout comp_profile;
    ArrayList<ActiveModel> slist = new ArrayList<>();
    String ideal_weight, tar_weight, weight_loss;
    String count, complete_persentage;
    File file1;
    String randnoo, randnoo2, user_id, result, adv_key, keyAdavance, keyHeartRisk;

    Dialog dialog, dialog3;
    String get_central_obesity, get_syndrome, get_Diabetes, get_Prediabetes, get_High_BP, get_heart_risk, get_Anaemia, get_Vit_deficiency, get_Vit_D;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_disease, Bp;
    String gender, height, weight, dob, feet, inch, is_smoke, age, get_dob;
    TextView email_not_verified;

    String  genderselectin, hight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, smoke_how_often;
    String  name,profile,email,diet_weight_loss,heart_diseas,key,HBA1C_value ,get_age, get_weight, get_hight, get_is_pregnent, menstrual_cycle, pre_pregnancy, feeding, get_child_age, diabetic, insulin, get_diabetic_diet, get_HBA1C, sugar_no_of_times, sugar_in, get_fasting, get_pp, get_level_one, get_level_two, habits, alcholic, get_alcohol_how_often, smoke, get_smoke_often;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac3);
        TextView text = (TextView) findViewById(R.id.text);
        LinearLayout add = (LinearLayout) findViewById(R.id.add);
        text.setText("PROFILE");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Profile.this, Edit_profile_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile2);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        comp_profile = (LinearLayout) findViewById(R.id.complete_profile);
        // current = (ImageView) findViewById(R.id.current);
        // target = (ImageView) findViewById(R.id.target);
        health = (ImageView) findViewById(R.id.health);
        heard_risk = (ImageView) findViewById(R.id.heard_risk);
        email_not_verified = findViewById(R.id.email_not_verified);
        userimage = (ImageView) findViewById(R.id.userimage);
        // edit_profile = (ImageView) findViewById(R.id.edit_profile);
        // progressBar = (ProgressBar) findViewById(R.id.progressBar);

        username = (TextView) findViewById(R.id.username);
        user_age_gender = (TextView) findViewById(R.id.user_age_gender);
        current_weight = (TextView) findViewById(R.id.current_weight);
        targe_weight = (TextView) findViewById(R.id.targe_weight);
        current_BMI = (TextView) findViewById(R.id.current_BMI);
        target_BMI = (TextView) findViewById(R.id.target_BMI);
        current_activity = (TextView) findViewById(R.id.current_activity);
        target_activity = (TextView) findViewById(R.id.target_activity);

        // profile_complete_percentage = (TextView) findViewById(R.id.profile_complete_percentage);
        Complete = (TextView) findViewById(R.id.Complete);
        adanance_Complete = (TextView) findViewById(R.id.adanance_Complete);
        heard_risk_plus = (TextView) findViewById(R.id.heard_risk_plus);
        health_plus = (TextView) findViewById(R.id.health_plus);


        new Profile_intent(User_Profile.this, Complete).sps();
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        Complete.setOnClickListener(this);
        comp_profile.setOnClickListener(this);
//        edit_profile.setOnClickListener(this);
        adanance_Complete.setOnClickListener(this);
        // current.setOnClickListener(this);
        // target.setOnClickListener(this);
        health.setOnClickListener(this);
        heard_risk.setOnClickListener(this);
        target_activity.setOnClickListener(this);
        userimage.setOnClickListener(this);

        User_Profile_Complete userProfileComplete = new User_Profile_Complete(User_Profile.this);
        userProfileComplete.giveValue();



     /*   targe_weight.setText(sp.getString("Weight","NA"));
        target_BMI.setText(sp.getString("BMI","NA"));
        target_activity.setText("NA");
*/

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();

        user_id = sp.getString("Userid", "");
        name = sp.getString("name", "");
        email = sp.getString("userEmail", "");
        profile = sp.getString("userProfilePhoto", "");
        genderselectin = sp.getString("gender", "");
        age = sp.getString("age", "");
        sp.getString("key", "");
        hight = sp.getString("height", "");
        weight = sp.getString("weight", "");
        is_pregnent = sp.getString("is_pregnent", "");
        first_day_of_last_menstrual_cycle = sp.getString("first_day_of_last_menstrual_cycle", "");
        pre_pregnancy_weight = sp.getString("pre_pregnancy_weight", "");
        breast_feeding = sp.getString("breast_feeding", "");
        child_age = sp.getString("child_age", "");
        is_diabetic = sp.getString("is_diabetic", "");
        taking_insulin = sp.getString("taking_insulin", "");
        diabetic_diet = sp.getString("taking_diabetic_diet", "");
        last_HBA1C = sp.getString("last_HBA1C", "");
        blood_sugar_no_of_times = sp.getString("blood_sugar_no_of_times", "");
        blood_sugar_in = sp.getString("blood_sugar_in_D_W_M", "");
        last_fasting = sp.getString("Last_fasting", "");
        HBA1C_value = sp.getString("HBA1C_value", "");
        pp_value = sp.getString("pp_value", "");
        activity_level_one = sp.getString("activity_level_one", "");
        activity_level_two = sp.getString("activity_level_two", "");
        food_habits = sp.getString("food_habits", "");
        any_heart_disease = sp.getString("any_heart_disease", "");
        is_alcholic = sp.getString("is_alcholic", "");
        alcohol_how_often = sp.getString("alcohol_how_often", "");
        is_smoke = sp.getString("is_smoke", "");
        smoke_how_often = sp.getString("smoke_how_often", "");
        get_dob = sp.getString("Dob", "");
        diet_weight_loss = sp.getString("weg_loss", "");

        sp.getString("is_login", "");
        Log.e("w", diet_weight_loss);

        Hemoglobin = sp.getString("Hemoglobin", "");
        Hematocrit = sp.getString("Hematocrit", "");
        blood_sugar = sp.getString("blood_sugar", "");
        Total_cholesterol = sp.getString("Total_cholesterol", "");
        LDL_cholesterol = sp.getString("LDL_cholesterol", "");
        HDL_cholesterol = sp.getString("HDL_cholesterol", "");
        Triglycerides = sp.getString("Triglycerides", "");
        Vitamin_levels = sp.getString("Vitamin_levels", "");
        Senter_inc = sp.getString("Senter_inc", "");
        if(Senter_inc.equals("")==false)
            Senter_cmc=String.valueOf(Float.parseFloat(Senter_inc) * 2.54f);
        SSystolic = sp.getString("SSystolic", "");
        SDiastolic = sp.getString("SDiastolic", "");
        Vitamin_B12_levels = sp.getString("Vitamin_B12_levels", "");
        heart_diseas = sp.getString("heart_disease", "");
        Bp = sp.getString("BP", "");
        key = sp.getString("key", "");
        username.setText(sp.getString("name", ""));
        count = sp.getString("profile_count", "");
        //  profile_complete_percentage.setText(count);


        Log.e("count_--", count + "");
        try {

            String userImage = sp.getString("userProfilePhoto", "");
            Picasso.get().load(userImage).placeholder(R.drawable.placeholder_featured_image).error(R.drawable.placeholder_featured_image).into(userimage);
        } catch (Exception e) {
            Log.e("count_error", e.toString());
            Log.e("count_--", count + "");
        }

        try {

            SharedPreferences sp2 = new AllSharedPrefrences(this).UserDataget();
            user_id = sp2.getString("Userid", "");
            Log.e("user_iddddd", user_id + "...");

            new GetPercentages().execute();
            user_age_gender.setText(sp2.getString("gender", "Gender") + ", " + sp2.getString("age", ""));

            current_weight.setText(sp2.getString("weight", "NA"));
            target_activity.setText("Check the Exercise plan");
            current_activity.setText(sp2.getString("Activity_Level", "NA"));
            current_BMI.setText(sp2.getString("BMI", "NA"));
            ideal_weight = sp2.getString("BMI", "");
            tar_weight = sp2.getString("weight_range", "");
            sp2.getString("weight_Category", "");
            weight_loss = sp2.getString("weight_target", "");

            ideal_weight = sp2.getString("BMI", "");

            sp2.getString("weight_Category", "");
            Log.e("bmi_result", sp2.getString("BMI", "") + "..." + sp2.getString("weight_range", ""));
            age = sp.getString("age", "");
            get_dob = sp.getString("Dob", "");
            ////////advance/////////
            is_smoke = sp.getString("is_smoke", "");
            gender = sp.getString("gender", "");
            user_id = sp.getString("Userid", "");
            sp.getString("key", "");
            height = sp.getString("height", "");
            weight = sp.getString("weight", "");
            dob = sp.getString("Dob", "");
            Hemoglobin = sp.getString("Hemoglobin", "");
            Hematocrit = sp.getString("Hematocrit", "");
            blood_sugar = sp.getString("blood_sugar", "");
            Total_cholesterol = sp.getString("Total_cholesterol", "");
            LDL_cholesterol = sp.getString("LDL_cholesterol", "");
            HDL_cholesterol = sp.getString("HDL_cholesterol", "");
            Triglycerides = sp.getString("Triglycerides", "");
            Vitamin_levels = sp.getString("Vitamin_levels", "");
            Senter_inc = sp.getString("Senter_inc", "");
            Senter_cmc = sp.getString("Senter_cmc", "");
            SSystolic = sp.getString("SSystolic", "");
            SDiastolic = sp.getString("SDiastolic", "");
            Vitamin_B12_levels = sp.getString("Vitamin_B12_levels", "");
            heart_disease = sp.getString("heart_disease", "");
            Bp = sp.getString("BP", "");

            get_central_obesity = sp.getString("central_obesity", "");
            get_syndrome = sp.getString("syndrome", "");
            get_Diabetes = sp.getString("Diabetes", "");
            get_Prediabetes = sp.getString("Prediabetes", "");
            get_High_BP = sp.getString("High_BP", "");
            get_heart_risk = sp.getString("Weight_Category", "");
            get_Anaemia = sp.getString("Anaemia", "");
            get_Vit_deficiency = sp.getString("Vit_B", "");
            get_Vit_D = sp.getString("Vit_D", "");

            keyAdavance = sp.getString("key_Adavance", "");
            keyHeartRisk = sp.getString("Heart_Risk", "");


            Log.e("set_data....", get_central_obesity + ".." + get_heart_risk);
            String[] separated = height.split("\\.");
            String f = separated[0];
            String i = separated[1];
            feet = f;
            inch = i;
            // complete_persentage = sp2.getString("persentage", "");
            // profile_complete_percentage.setText(complete_persentage + "% Profile Completed");
            targe_weight.setText(tar_weight);
            target_BMI.setText(weight_loss);
            //target_activity.setText(weight_loss);
            //progressBar.setProgress(Integer.parseInt(complete_persentage));
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();

            try {
                if (keyAdavance.equals("1")) {
                    health.setBackgroundResource(R.drawable.circular_shape);
                    health_plus.setTextColor(getResources().getColor(R.color.white));
                }
                if (keyHeartRisk.equals("1")) {
                    heard_risk.setBackgroundResource(R.drawable.circular_shape);
                    heard_risk_plus.setTextColor(getResources().getColor(R.color.white));
                }
            } catch (Exception e) {
                Log.e("erro == =", e + "");
            }

        } catch (Exception e) {
            Log.e("errrrr", e + "");
        }
        //  Profile_completion_persentage();
        new Task().execute();
        new VerifyEmail().execute();
        try {
            SharedPreferences sps = getSharedPreferences("KEY", Context.MODE_PRIVATE);
            sps.edit().clear().commit();
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(User_Profile.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {

        }
        /*if (view == current){
            //new MyIntent(User_Profile.this, Current_Profile.class, R.anim.enter, R.anim.exit);
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            if (gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                    get_dob.equals("") || get_dob == null || get_dob.equals("0000-00-00")) {
                Toast.makeText(this, "Please fill your basic details", Toast.LENGTH_SHORT).show();

            } else {

             *//*   pd = new ProgressDialog(PlansActivity.this);
                pd.setMessage("Please Wait...");
                pd.setCancelable(false);
                pd.show();*//*
                DataConnectionCurrent();
            }
        }
        if (view == target){
           // new MyIntent(User_Profile.this, Traget_Profile.class, R.anim.enter, R.anim.exit);

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            if (gender.equals("") || gender == null || age.equals("") || age == null || height.equals("") || height == null || weight.equals("") || weight == null ||
                    get_dob.equals("") || get_dob == null || get_dob.equals("0000-00-00")) {
                Toast.makeText(this, "Please fill your basic details", Toast.LENGTH_SHORT).show();

            } else {

             *//*   pd = new ProgressDialog(PlansActivity.this);
                pd.setMessage("Please Wait...");
                pd.setCancelable(false);
                pd.show();*//*
                DataConnection();
            }
        }*/
        if (view == health) {
            new MyIntent(User_Profile.this, Health_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == heard_risk) {
            new MyIntent(User_Profile.this, HeardRisk_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (view == Complete) {
            new Profile_intent(User_Profile.this, Complete).getCondetions1();
        }
        if (view == adanance_Complete) {
           /* String alert_key;
            SharedPreferences shared2 = getSharedPreferences("Alert_popup", MODE_PRIVATE);

            alert_key = shared2.getString("alert_key", "");
            Log.e("alert_key", alert_key);

            //new Profile_intent(User_Profile.this, Complete).getCondetions1();
            if (alert_key.equals("ok")) {*/

            new Profile_intent(User_Profile.this, adanance_Complete).getCondetions1();


           /* } else {
                AlertDialog();
            }*/
        }
        if (view == log_L) {
            new MyIntent(User_Profile.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == plans_L) {
            new MyIntent(User_Profile.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == more_L) {
            new MyIntent(User_Profile.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == target_activity) {
            new AllSharedPrefrences(User_Profile.this).Plan_Ex_key("user_profile");
            new MyIntent(User_Profile.this, ExcercisePlanActivity.class, R.anim.enter, R.anim.exit);
        }
        if (view == comp_profile) {
            AlertDialogComplete();
        }
        if(view==userimage)
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public void uploadFile() {

        String resp = null;
        String charset = "UTF-8";

        try {

            if (imagepath.equals("null")) {

            } else {
            file1 = new File(imagepath);
            }
        } catch (Exception e) {
            Log.e("error", e.toString());
        }


        try {
            FileUploader multipart = new FileUploader(URLS + "update_basic_profile/" + randnoo, charset);
            Log.e("urllll", "" + URLS + update_profile + randnoo);
            multipart.addFormField("access_keys", AccessToken);
            multipart.addFormField("user_id", user_id);
            multipart.addFormField("name", name);
            multipart.addFormField("gender", gender);
            multipart.addFormField("age", age);
            multipart.addFormField("DOB", dob);
            multipart.addFormField("height", height);
            multipart.addFormField("weight", weight);
            multipart.addFormField("is_pregnent", is_pregnent);
            multipart.addFormField("first_day_of_last_menstrual_cycle", first_day_of_last_menstrual_cycle);
            multipart.addFormField("pre_pregnancy_weight", pre_pregnancy_weight);
            multipart.addFormField("breast_feeding", breast_feeding);
            multipart.addFormField("child_age", child_age);
            multipart.addFormField("is_diabetic", is_diabetic);
            multipart.addFormField("taking_insulin",taking_insulin);
            multipart.addFormField("diabetic_diet", diabetic_diet);
            multipart.addFormField("last_HBA1C", HBA1C_value);
            multipart.addFormField("blood_sugar_no_of_times", blood_sugar_no_of_times);
            multipart.addFormField("blood_sugar_in", blood_sugar_in);
            multipart.addFormField("last_fasting", last_fasting);
            multipart.addFormField("pp_value", pp_value);
            multipart.addFormField("activity_level_one", activity_level_one);
            multipart.addFormField("activity_level_two", activity_level_two);
            multipart.addFormField("food_habits", food_habits);
            multipart.addFormField("any_heart_disease", heart_disease);
            multipart.addFormField("is_alcholic", is_alcholic);
            multipart.addFormField("alcohol_how_often", alcohol_how_often);
            multipart.addFormField("is_smoke", smoke);
            multipart.addFormField("smoke_how_often", get_smoke_often);
            multipart.addFormField("do_you_want_a_diet_for_weight_loss", diet_weight_loss);
            multipart.addFormField("hemoglobin",Hemoglobin);
            multipart.addFormField("hematocrit", Hematocrit);
            multipart.addFormField("fasting_blood_sugar", blood_sugar);
            multipart.addFormField("total_cholesterol", Total_cholesterol);
            multipart.addFormField("LDL_cholesterol", LDL_cholesterol);
            multipart.addFormField("HDL_cholesterol", HDL_cholesterol);
            multipart.addFormField("triglycerides", Triglycerides);
            multipart.addFormField("vitamin_D_levels", Vitamin_levels);
            multipart.addFormField("vitamin_B12_levels", Vitamin_B12_levels);
            multipart.addFormField("waist_circumference_inches", Senter_inc);
            multipart.addFormField("waist_circumference_cms", Senter_cmc);
            multipart.addFormField("blood_pressure1", SSystolic);
            multipart.addFormField("blood_pressure2", SDiastolic);
            multipart.addFormField("HBA1C_value", HBA1C_value);
            multipart.addFormField("did_your_father_get_heart_disease_before_45years_of_age", heart_diseas);
            multipart.addFormField("are_you_drugs_for_high_bp", Bp);
            Log.e("keyyyyyyy", dob);


            try {

                if (imagepath.equals("null")) {

                } else {
                    multipart.addFilePart("image", file1);
                    Log.e("edit_image", "" + file1);
                }
            } catch (Exception e) {
                Log.e("error", e.toString());
            }
            List<String> response = multipart.finish();

            //  System.out.println("SERVER REPLIED:");

            for (String line : response) {
                resp = line;
            }
            try {
                Log.e("hdhdjahd", "" + resp);
                final JSONObject obj = new JSONObject(resp);
                final String st = obj.getString("status");
                final String msg = obj.getString("message");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (st.equals("1")) {
                            try {
                                pd.dismiss();
                                GetPeofile();
                                JSONObject object = obj.getJSONObject("profile_info");
                                String user_id = object.getString("user_id");
                                String email = object.getString("email");
                                String get_dob = object.getString("DOB");
                                String name = object.getString("name");
                                String image = object.getString("image");
                                String gender = object.getString("gender");
                                String age = object.getString("age");
                                String height = object.getString("height");
                                String weight = object.getString("weight");
                                String is_pregnent = object.getString("is_pregnent");
                                String first_day_of_last_menstrual_cycle = object.getString("first_day_of_last_menstrual_cycle");
                                String pre_pregnancy_weight = object.getString("pre_pregnancy_weight");
                                String breast_feeding = object.getString("breast_feeding");
                                String child_age = object.getString("child_age");
                                String is_diabetic = object.getString("is_diabetic");
                                String taking_insulin = object.getString("taking_insulin");
                                String diabetic_diet = object.getString("diabetic_diet");
                                String last_HBA1C = object.getString("last_HBA1C");
                                String blood_sugar_no_of_times = object.getString("blood_sugar_no_of_times");
                                String blood_sugar_in = object.getString("blood_sugar_in");
                                String last_fasting = object.getString("last_fasting");
                                String pp_value = object.getString("pp_value");
                                String activity_level_one = object.getString("activity_level_one");
                                String activity_level_two = object.getString("activity_level_two");
                                String food_habits = object.getString("food_habits");
                                String any_heart_disease = object.getString("any_heart_disease");
                                String weight_loss = object.getString("do_you_want_a_diet_for_weight_loss");
                                String is_alcholic = object.getString("is_alcholic");
                                String alcohol_how_often = object.getString("alcohol_how_often");
                                String is_smoke = object.getString("is_smoke");
                                String smoke_how_often = object.getString("smoke_how_often");
                                String HBA1C_value = object.getString("HBA1C_value");
                                String is_login = object.getString("is_login");

                                String waist_circumference_inches = object.getString("waist_circumference_inches");
                                String waist_circumference_cms = object.getString("waist_circumference_cms");
                                String blood_pressure1 = object.getString("blood_pressure1");
                                String blood_pressure2 = object.getString("blood_pressure2");
                                String hemoglobin = object.getString("hemoglobin");
                                String hematocrit = object.getString("hematocrit");
                                String fasting_blood_sugar = object.getString("fasting_blood_sugar");
                                String total_cholesterol = object.getString("total_cholesterol");
                                String LDL_cholesterol = object.getString("LDL_cholesterol");
                                String HDL_cholesterol = object.getString("HDL_cholesterol");
                                String triglycerides = object.getString("triglycerides");
                                String vitamin_D_levels = object.getString("vitamin_D_levels");
                                String vitamin_B12_levels = object.getString("vitamin_B12_levels");
                                String did_your_father_get_heart_disease_before_45years_of_age = object.getString("did_your_father_get_heart_disease_before_45years_of_age");
                                String are_you_drugs_for_high_bp = object.getString("are_you_drugs_for_high_bp");

                                new AllSharedPrefrences(User_Profile.this).UserAdvanceData1(hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels, waist_circumference_inches, waist_circumference_cms, blood_pressure1, blood_pressure2, did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);

                                new AllSharedPrefrences(User_Profile.this).UserData(name, email, image, key, user_id,
                                        gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                                        pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet,
                                        last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one,
                                        activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke,
                                        smoke_how_often, is_login, HBA1C_value);
                                new AllSharedPrefrences(User_Profile.this).AutoLogin("Login");
                                Log.e("get_dobbb", get_dob);
                                new AllSharedPrefrences(User_Profile.this).UserDob(get_dob);
                                new AllSharedPrefrences(User_Profile.this).Weight_loss(weight_loss);


                            } catch (Exception e) {
                                Log.e("error", e.toString());
                            }

                        } else {
                            pd.dismiss();
                            Toast.makeText(User_Profile.this, "" + msg, Toast.LENGTH_LONG).show();
                        }
                    }
                });

            } catch (Exception e) {
                Log.e("error", e.toString());
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    void GetPeofile() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + getProfile + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");

                            if (status.equals("1")) {
                                JSONObject object = jobj.getJSONObject("profile_info");
                                String user_id = object.getString("user_id");
                                String email = object.getString("email");
                                String name = object.getString("name");
                                String image = object.getString("image");
                                String gender = object.getString("gender");
                                String age = object.getString("age");
                                String height = object.getString("height");
                                String weight = object.getString("weight");
                                String is_pregnent = object.getString("is_pregnent");
                                String first_day_of_last_menstrual_cycle = object.getString("first_day_of_last_menstrual_cycle");
                                String pre_pregnancy_weight = object.getString("pre_pregnancy_weight");
                                String breast_feeding = object.getString("breast_feeding");
                                String child_age = object.getString("child_age");
                                String is_diabetic = object.getString("is_diabetic");
                                String taking_insulin = object.getString("taking_insulin");
                                String diabetic_diet = object.getString("diabetic_diet");
                                String last_HBA1C = object.getString("last_HBA1C");
                                String blood_sugar_no_of_times = object.getString("blood_sugar_no_of_times");
                                String blood_sugar_in = object.getString("blood_sugar_in");
                                String last_fasting = object.getString("last_fasting");
                                String pp_value = object.getString("pp_value");
                                String activity_level_one = object.getString("activity_level_one");
                                String activity_level_two = object.getString("activity_level_two");
                                String food_habits = object.getString("food_habits");
                                String any_heart_disease = object.getString("any_heart_disease");
                                String is_alcholic = object.getString("is_alcholic");
                                String alcohol_how_often = object.getString("alcohol_how_often");
                                String is_smoke = object.getString("is_smoke");
                                String smoke_how_often = object.getString("smoke_how_often");
                                String HBA1C_value = object.getString("HBA1C_value");
                                String is_login = object.getString("is_login");

                                String waist_circumference_inches = object.getString("waist_circumference_inches");
                                String waist_circumference_cms = object.getString("waist_circumference_cms");
                                String blood_pressure1 = object.getString("blood_pressure1");
                                String blood_pressure2 = object.getString("blood_pressure2");
                                String hemoglobin = object.getString("hemoglobin");
                                String hematocrit = object.getString("hematocrit");
                                String fasting_blood_sugar = object.getString("fasting_blood_sugar");
                                String total_cholesterol = object.getString("total_cholesterol");
                                String LDL_cholesterol = object.getString("LDL_cholesterol");
                                String HDL_cholesterol = object.getString("HDL_cholesterol");
                                String triglycerides = object.getString("triglycerides");
                                String vitamin_D_levels = object.getString("vitamin_D_levels");
                                String vitamin_B12_levels = object.getString("vitamin_B12_levels");
                                String did_your_father_get_heart_disease_before_45years_of_age = object.getString("did_your_father_get_heart_disease_before_45years_of_age");
                                String are_you_drugs_for_high_bp = object.getString("are_you_drugs_for_high_bp");
                                String do_you_want_a_diet_for_weight_loss = object.getString("do_you_want_a_diet_for_weight_loss");
                                String DOB = object.getString("DOB");

                                String key = "normal";
                                new AllSharedPrefrences(User_Profile.this).UserData(name, email, image, key, user_id,
                                        gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                                        pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet,
                                        last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one,
                                        activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke,
                                        smoke_how_often, is_login, HBA1C_value);
                                new AllSharedPrefrences(User_Profile.this).AutoLogin("Login");
                                new AllSharedPrefrences(User_Profile.this).UserDob(DOB);
                                new AllSharedPrefrences(User_Profile.this).Weight_loss(do_you_want_a_diet_for_weight_loss);
                                new AllSharedPrefrences(User_Profile.this).UserAdvanceData1(hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels, waist_circumference_inches, waist_circumference_cms, blood_pressure1, blood_pressure2, did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);

                                SharedPreferences sp = getSharedPreferences("KEY", Context.MODE_PRIVATE);
                                sp.edit().clear().commit();


                                try {
                                    SharedPreferences sps = getSharedPreferences("KEY", Context.MODE_PRIVATE);
                                    sps.edit().clear().commit();
                                } catch (Exception e) {

                                }


                            } else {
                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");
                            }
                        } catch (Exception e) {
                            Log.e("error", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("user_id", user_id);

                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    public String imagepath="NULL";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            try {
                Uri selectedFileUri = data.getData();
                imagepath = getPath(this, selectedFileUri);
                Picasso.get().load(selectedFileUri).into(userimage);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            uploadFile();
                        } catch (Exception e) {
                            Log.e("error", e.toString());
                        }
                    }
                }).start();


            } catch (Exception e) {
                Log.e("img err = ", e.toString());
            }
        }
    }

    class GetPercentages extends AsyncTask<String, Void, String> {
        String result;
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(User_Profile.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";
            try {
                URL url = new URL(BaseUrl.get_profile_completion_persentage + new RandomNumber().randno());
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", BaseUrl.AccessToken);
                postDataParams.put("user_id", user_id);

                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                } else {
                    result = "";
                }
            } catch (Exception e) {
                Log.e("ERRR", e + "");
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("percent+ api = ", result + "");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");

                if (status.equals("1")) {
                    String Total_profile_persetage = jobj.getString("Total_profile_persetage");

                    if (Total_profile_persetage.equals("50")) {

                    } else if (Total_profile_persetage.equals("100")) {

                    } else {
                        AlertDialogStatus();
                    }
                } else {
                }
            } catch (Exception e) {
                Log.e("percent api error = ", e + "");
            }
        }
    }

    void AlertDialogStatus() {
        final Dialog dialogstatus = new Dialog(User_Profile.this, R.style.CustomDialog);
        dialogstatus.setContentView(R.layout.res);
        dialogstatus.setCancelable(false);
        TextView test1 = (TextView) dialogstatus.findViewById(R.id.test1);
        TextView test2 = (TextView) dialogstatus.findViewById(R.id.test2);
        Button close = (Button) dialogstatus.findViewById(R.id.close);

        test1.setText("Your basic profile is not complete.");
        test2.setText("Please complete basic profile first.");
        close.setText("Proceed");


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogstatus.dismiss();

                SharedPreferences sp = getSharedPreferences("EditPro", MODE_PRIVATE);
                String goKey = sp.getString("gokey", "1");

                if (goKey.equals("1")) {
                    new MyIntent(User_Profile.this, Profile_Step1.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("2")) {
                    new MyIntent(User_Profile.this, Profile_Step2.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("3")) {
                    new MyIntent(User_Profile.this, Profile_Step3.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("4")) {
                    new MyIntent(User_Profile.this, Profile_Menstrul_Cycle_4.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("5")) {
                    new MyIntent(User_Profile.this, Profile_Breast_Feeding_5.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("6")) {
                    new MyIntent(User_Profile.this, Profile_Child_Age_6.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("7")) {
                    new MyIntent(User_Profile.this, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("8")) {
                    new MyIntent(User_Profile.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("9")) {
                    new MyIntent(User_Profile.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("10")) {
                    new MyIntent(User_Profile.this, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("11")) {
                    new MyIntent(User_Profile.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("12")) {
                    new MyIntent(User_Profile.this, Smoking.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("13")) {
                    new MyIntent(User_Profile.this, Alcohol.class, R.anim.enter, R.anim.exit);
                } else if (goKey.equals("14")) {
                    new MyIntent(User_Profile.this, Edit_profile_Activity.class, R.anim.enter, R.anim.exit);
                }
            }
        });


        dialogstatus.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(User_Profile.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
    }


    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(User_Profile.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + get_profile_completion_persentage + randnoo);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);


                Log.e("params", params + "");
                Log.e("params", postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                } else {
                    result = "";
                }
            } catch (Exception e) {
                Log.e("ERRR", e + "");
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("Task api = ", result + "");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");


                if (status.equals("1")) {
                    comp_profile.setVisibility(View.VISIBLE);
                    String Total_profile_persetage = jobj.getString("Total_profile_persetage");


                    new AllSharedPrefrences(User_Profile.this).Profile_complete_persentage(Total_profile_persetage);
                    // new AllSharedPrefrences(User_Profile.this).AutoLogin("Login");
                    // new MyIntent(LoginActivity.this, HomeActivity.class, R.anim.enter, R.anim.exit);
                    if (Total_profile_persetage.equals("50")) {
                        adanance_Complete.setVisibility(View.VISIBLE);
                        Complete.setVisibility(View.GONE);
                    } else if (Total_profile_persetage.equals("100")) {
                        // adanance_Complete.setVisibility(View.VISIBLE);
                        Complete.setVisibility(View.GONE);
                        comp_profile.setVisibility(View.GONE);

                    }
                    //profile_complete_percentage.setText(Total_profile_persetage + "% Profile Completed");
                    //progressBar.setProgress(Integer.parseInt(Total_profile_persetage));
                    //  Toast.makeText(User_Profile.this, message, Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(activity_create_account,message,Snackbar.LENGTH_LONG).show();


                } else {

                }
            } catch (Exception e) {
            }
        }
    }


    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


    void AlertDialog() {
        dialog = new Dialog(User_Profile.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.profile_popup_page);
        dialog.setCancelable(true);

        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        Button ok = (Button) dialog.findViewById(R.id.ok);

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
                        new DownloadFile().execute("http://ninareads.com/wp-content/uploads/2017/02/Doctor-Release-Form-To-Return-To-Work-73il94km-219x300.jpg");

                    }
                });


        dialog.show();
    }


    class DownloadFile extends AsyncTask<String, Integer, Long> {
        ProgressDialog mProgressDialog = new ProgressDialog(User_Profile.this);// Change Mainactivity.this with your activity name.
        String strFolderName;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.setMessage("Downloading");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.show();
        }

        @Override
        protected Long doInBackground(String... aurl) {
            int count;
            String downloadFolder = "hwi";
            try {
                URL url = new URL((String) aurl[0]);
                URLConnection conexion = url.openConnection();
                conexion.connect();
                String targetFileName = "image" + ".jpg";//Change name and subname
                int lenghtOfFile = conexion.getContentLength();
                String PATH = Environment.getExternalStorageDirectory() + "/" + downloadFolder + "/";
                File folder = new File(PATH);
                if (!folder.exists()) {
                    folder.mkdir();//If there is no folder it will be created.
                }
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(PATH + targetFileName);
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress((int) (total * 100 / lenghtOfFile));
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            mProgressDialog.setProgress(progress[0]);
            if (mProgressDialog.getProgress() == mProgressDialog.getMax()) {
                mProgressDialog.dismiss();
                dialog.dismiss();
                //Toast.makeText(User_Profile.this, "File Downloaded", Toast.LENGTH_SHORT).show();
                SharedPreferences shared = getSharedPreferences("Alert_popup", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("alert_key", "ok");
                editor.commit();

            }
        }

        protected void onPostExecute(String result) {

        }
    }

    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + weight_loss_calculator + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        Log.e("response= ", response + " rendom =" + randnoo);

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {

                                JSONObject joo = jobj.getJSONObject("weight_loss_calculator_details");
                                JSONObject jo = joo.getJSONObject("weight_loss_calculator_details");

                                String weight_range = jo.getString("Appropriate weight range for you ( as per your gender and height) is");
                                String Weight_Category = jo.getString("Your Weight Category is");
                                String target = jo.getString("Your target weightloss should be");
                                String bmi = jo.getString("Your BMI is");

                                new AllSharedPrefrences(User_Profile.this).BMI_Result(bmi, weight_range, Weight_Category, target, weight);
                                new MyIntent(User_Profile.this, Traget_Profile.class, R.anim.enter, R.anim.exit);

                            } else {


                            }
                        } catch (Exception e) {
                            Log.e("errrrr= ", e + "");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
                pd.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("gender", gender);
                params.put("age", age);
                params.put("dob", get_dob);
                params.put("ft", feet);
                params.put("inc", inch);
                params.put("weight", weight);
                Log.e("params", params + "");
                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }


    void AlertDialogComplete() {
        dialog3 = new Dialog(User_Profile.this, R.style.CustomDialog);
        dialog3.setContentView(R.layout.exercise_log_popup);
        dialog3.setCancelable(true);

        ImageView close = (ImageView) dialog3.findViewById(R.id.close);
        TextView text_popup = (TextView) dialog3.findViewById(R.id.text_popup);
        Button yes = (Button) dialog3.findViewById(R.id.yes);
        Button no = (Button) dialog3.findViewById(R.id.no);
        text_popup.setText("Complete your profile if not completed.");
        close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                    }
                });
        yes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                        Intent intent = new Intent(User_Profile.this, Edit_profile_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        no.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog3.dismiss();
                    }
                });


        dialog3.show();
    }


    class VerifyEmail extends AsyncTask<String, Void, String> {
        String result;
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(User_Profile.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";
            try {

                SharedPreferences sp = getSharedPreferences("UserVerify", Context.MODE_PRIVATE);
                URL url = new URL(BaseUrl.login_check + new RandomNumber().randno());
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("email", sp.getString("email", ""));
                postDataParams.put("Password", sp.getString("password", ""));
                postDataParams.put("device_token", sp.getString("device_token", ""));

                Log.e("postDataParams: ", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                } else {
                    result = "";
                }
            } catch (Exception e) {
                Log.e("ERRR", e + "");
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("check login = ", result + "");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");

                if (status.equals("1")) {
                    email_not_verified.setVisibility(View.GONE);
                } else {
                    //email_not_verified.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                Log.e("check error error = ", e + "");
            }
        }
    }


}
