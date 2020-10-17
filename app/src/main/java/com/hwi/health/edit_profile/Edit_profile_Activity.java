package com.hwi.health.edit_profile;

import android.annotation.TargetApi;
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
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.hwi.health.FileUploader;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.First.Alcohol;
import com.hwi.health.Activitys.Profile.First.Profile_Active_10;
import com.hwi.health.Activitys.Profile.First.Profile_Blood_Suger_9;
import com.hwi.health.Activitys.Profile.First.Profile_Breast_Feeding_5;
import com.hwi.health.Activitys.Profile.First.Profile_Child_Age_6;
import com.hwi.health.Activitys.Profile.First.Profile_Diabetic_7;
import com.hwi.health.Activitys.Profile.First.Profile_Food_habits_11;
import com.hwi.health.Activitys.Profile.First.Profile_Insulin_8;
import com.hwi.health.Activitys.Profile.First.Profile_Menstrul_Cycle_4;
import com.hwi.health.Activitys.Profile.First.Profile_Pregnant_3;
import com.hwi.health.Activitys.Profile.First.Profile_Step1;
import com.hwi.health.Activitys.Profile.First.Profile_Step2;
import com.hwi.health.Activitys.Profile.First.Profile_Step3;
import com.hwi.health.Activitys.Profile.First.Smoking;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.Activitys.Profile.advanced_profile.Body_PARAMETERS;
import com.hwi.health.Activitys.Profile.advanced_profile.LABORATORY;
import com.hwi.health.Activitys.Profile.advanced_profile.RISK_RELATED_QUESTIONS;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Edit_profile_Activity extends AppCompatActivity implements BaseUrl, View.OnClickListener {
    CircularImageView image;
    public static boolean profileEdited=false;
    EditText edit_name, edit_email, edit_dob, edit_gender, edit_age, edit_weight, edit_height, edit_pregnant, edit_breast_feeding,
            edit_menstrual, edit_pre_preg_weight, edit_child_age, edit_diabetic, edit_diabetic_diet, edit_hba, edit_insulin, edit_blood_times, edit_blood_in,
            edit_last_fasting, edit_pp, edit_activity_level_one, edit_activity_level_two, edit_food_habit, edit_heart_dis, edit_smoke, edit_smoke_often,
            edit_drink, edit_drink_often;
    Button update;
    int PICKFILE_RESULT_CODE = 1;
    String imagepath = "null", name_edit, email_edit, key, name, email, profile, strDate, get_dob, inch, feet;
    File file1;
    EditText enter_inc, enter_cmc, Systolic, Diastolic, heart_disease_age, bp;
    EditText et_HBA1C_value, et_Hemoglobin, et_blood_sugar, et_Hematocrit, et_Total_cholesterol, et_LDL_cholesterol, et_HDL_cholesterol, et_Triglycerides, et_Vitamin_levels, et_Vitamin_B12_levels;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_diseas, Bp;
    EditText weight_loss;

    TextView gen_plus, adv_plus;
    String HBA1C_value, Hemoglobin_s, Hematocrit_s, blood_sugar_s, Total_cholesterol_s, LDL_cholesterol_s, HDL_cholesterol_s, Triglycerides_s, Vitamin_levels_s, Vitamin_B12_levels_s, Senter_inc_s, Senter_cmc_s, SSystolic_s, SDiastolic_s, heart_diseas_s, Bp_s;

    ProgressDialog pd;
    String get = "na";

    int YEAR, MONTH, DAY;
    String loss_weg, diet_weight_loss;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L, general_lin, baisc_lin, advance_lin, basic, general, advance;
    //            lin_1, lin_2, lin_3, lin_4, lin_5, lin_6, lin_7, lin_8, lin_9, lin_10, lin_11,
//            lin_12, lin_13, lin_14, lin_15, lin_16, lin_17, lin_18, lin_19, lin_20, lin_21, lin_22, lin_23, lin_24, lin_25;
    private int mYear, mMonth, mDay, mHour, mMinute;

    String randnoo, age, genderselectin, weight, hight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;
    String dob, gender, get_age, get_weight, get_hight, get_is_pregnent, menstrual_cycle, pre_pregnancy, feeding, get_child_age, diabetic, insulin, get_diabetic_diet, get_HBA1C, sugar_no_of_times, sugar_in, get_fasting, get_pp, get_level_one, get_level_two, habits, heart_disease, alcholic, get_alcohol_how_often, smoke, get_smoke_often;
    LinearLayout lin_feeding, lin_menstrual, lin_child_age, lin_insulin, lin_hba1c, lin_smoke, lin_dirnk, lin_is_pregnent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Edit Profile");

        setContentView(R.layout.activity_edit_profile);
        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        find_id();
        // GetPeofile();

//        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
//        name = sp.getString("name", "");
//        email = sp.getString("userEmail", "");
//        profile = sp.getString("userProfilePhoto", "");
//        genderselectin = sp.getString("gender", "");
//        age = sp.getString("age", "");
//        hight = sp.getString("height", "");
//        weight = sp.getString("weight", "");
//        get_dob = sp.getString("Dob", "");
//        diet_weight_loss = sp.getString("weg_loss", "");

        try {
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

            new Task().execute();

            edit_name.setText(name);
            edit_email.setText(email);
            edit_gender.setText(genderselectin);
            edit_age.setText(age);
            edit_height.setText(hight);
            edit_weight.setText(weight);
            edit_dob.setText(get_dob);
            edit_activity_level_one.setText(activity_level_one);
            edit_activity_level_two.setText(activity_level_two);
            edit_food_habit.setText(food_habits);
            edit_smoke.setText(is_smoke);
            edit_drink.setText(is_alcholic);
            edit_diabetic.setText(is_diabetic);
            edit_heart_dis.setText(any_heart_disease);


            if (genderselectin.equals("female")) {
                lin_is_pregnent.setVisibility(View.VISIBLE);
                edit_pregnant.setText(is_pregnent);
                if (is_pregnent.equals("yes")) {
                    lin_menstrual.setVisibility(View.VISIBLE);
                    lin_child_age.setVisibility(View.VISIBLE);
                    edit_menstrual.setText(first_day_of_last_menstrual_cycle);
                    edit_pre_preg_weight.setText(pre_pregnancy_weight);
                    edit_child_age.setText(child_age);


                } else {
                    if (is_pregnent.equals("no")) {
                        lin_feeding.setVisibility(View.VISIBLE);
                        edit_breast_feeding.setText(breast_feeding);
                    }
                }
            } else {

            }


            if (is_diabetic.equals("yes")) {
                lin_insulin.setVisibility(View.VISIBLE);
                lin_hba1c.setVisibility(View.VISIBLE);
                edit_insulin.setText(taking_insulin);
                edit_diabetic_diet.setText(diabetic_diet);
                edit_hba.setText(last_HBA1C);
                edit_blood_times.setText(blood_sugar_no_of_times);
                edit_blood_in.setText(blood_sugar_in);
                edit_last_fasting.setText(last_fasting);
                et_HBA1C_value.setText(HBA1C_value);
                edit_pp.setText(pp_value);
            }
            if (is_smoke.equals("yes")) {
                lin_smoke.setVisibility(View.VISIBLE);
                edit_smoke_often.setText(smoke_how_often);
            }
            if (is_alcholic.equals("yes")) {
                lin_dirnk.setVisibility(View.VISIBLE);
                edit_drink_often.setText(alcohol_how_often);
            }


            Diastolic.setText(SDiastolic);
            enter_inc.setText(Senter_inc);
            enter_cmc.setText(Senter_cmc);
            Systolic.setText(SSystolic);
            et_Hemoglobin.setText(Hemoglobin);
            et_Hematocrit.setText(Hematocrit);
            et_blood_sugar.setText(blood_sugar);
            et_Total_cholesterol.setText(Total_cholesterol);
            et_LDL_cholesterol.setText(LDL_cholesterol);
            et_HDL_cholesterol.setText(HDL_cholesterol);
            et_Triglycerides.setText(Triglycerides);
            et_Vitamin_levels.setText(Vitamin_levels);
            et_Vitamin_B12_levels.setText(Vitamin_B12_levels);
            heart_disease_age.setText(heart_diseas);
            bp.setText(Bp);
            weight_loss.setText(diet_weight_loss);
            Log.e("key", "dffdgdfgfcggxg" + user_id);


            try {
                Picasso.get().load(profile).error(R.drawable.user_placeholder).placeholder(R.drawable.user_placeholder).into(image);
            } catch (Exception e) {
                Log.e("profile", e.toString());
            }
        } catch (Exception e) {
            Log.e("Errr ", e + "");
        }


        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        update.setOnClickListener(this);
        image.setOnClickListener(this);
        edit_dob.setOnClickListener(this);
        edit_gender.setOnClickListener(this);
        edit_age.setOnClickListener(this);
        edit_pregnant.setOnClickListener(this);
        edit_breast_feeding.setOnClickListener(this);
        edit_child_age.setOnClickListener(this);
        edit_diabetic.setOnClickListener(this);
        edit_hba.setOnClickListener(this);
        edit_height.setOnClickListener(this);
        edit_weight.setOnClickListener(this);
        edit_menstrual.setOnClickListener(this);
        edit_pre_preg_weight.setOnClickListener(this);
        edit_insulin.setOnClickListener(this);
        edit_diabetic_diet.setOnClickListener(this);
        edit_blood_times.setOnClickListener(this);
        edit_blood_in.setOnClickListener(this);
        edit_last_fasting.setOnClickListener(this);
        edit_pp.setOnClickListener(this);
        edit_activity_level_one.setOnClickListener(this);
        edit_activity_level_two.setOnClickListener(this);
        edit_food_habit.setOnClickListener(this);
        edit_heart_dis.setOnClickListener(this);
        edit_smoke.setOnClickListener(this);
        edit_smoke_often.setOnClickListener(this);
        edit_drink.setOnClickListener(this);
        edit_drink_often.setOnClickListener(this);
        enter_inc.setOnClickListener(this);
        enter_cmc.setOnClickListener(this);
        Systolic.setOnClickListener(this);
        Diastolic.setOnClickListener(this);
        heart_disease_age.setOnClickListener(this);
        bp.setOnClickListener(this);
        et_HBA1C_value.setOnClickListener(this);
        et_Hemoglobin.setOnClickListener(this);
        et_blood_sugar.setOnClickListener(this);
        et_Hematocrit.setOnClickListener(this);
        et_Total_cholesterol.setOnClickListener(this);
        et_LDL_cholesterol.setOnClickListener(this);
        et_HDL_cholesterol.setOnClickListener(this);
        et_Triglycerides.setOnClickListener(this);
        et_Vitamin_levels.setOnClickListener(this);
        et_Vitamin_B12_levels.setOnClickListener(this);
        general.setOnClickListener(this);
        // basic.setOnClickListener(this);
        advance.setOnClickListener(this);
        weight_loss.setOnClickListener(this);


        try {
            SharedPreferences sp = getSharedPreferences("KEY", Context.MODE_PRIVATE);

            get = sp.getString("key", "");

            if (get.equals("1")) {
                general_lin.setVisibility(View.VISIBLE);
                advance_lin.setVisibility(View.GONE);
                update.setVisibility(View.VISIBLE);


            } else if (get.equals("2")) {
                general_lin.setVisibility(View.GONE);
                advance_lin.setVisibility(View.VISIBLE);
                update.setVisibility(View.VISIBLE);
            } else {
                general_lin.setVisibility(View.GONE);
                advance_lin.setVisibility(View.GONE);
                update.setVisibility(View.GONE);
            }

        } catch (Exception e) {

        }

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    class Task extends AsyncTask<String, Void, String> {

        String result;
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Edit_profile_Activity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result = "";
            try {
                URL url = new URL(BaseUrl.get_profile_completion_persentage + randnoo);

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
                    profileEdited=true;
                    String Total_profile_persetage = jobj.getString("Total_profile_persetage");

                    if (Total_profile_persetage.equals("50")) {
                        select_one.setImageResource(R.drawable.circular_shape);
                        select_one.setPadding(10,10,10,10);
                        select_two.setImageResource(R.drawable.circle);
                        gen_plus.setTextColor(getResources().getColor(R.color.white));
                        adv_plus.setTextColor(getResources().getColor(R.color.app_green));
                    } else if (Total_profile_persetage.equals("100")) {
                        select_one.setImageResource(R.drawable.circular_shape);
                        select_two.setImageResource(R.drawable.circular_shape);
                        gen_plus.setTextColor(getResources().getColor(R.color.white));
                        adv_plus.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        select_one.setImageResource(R.drawable.circle);
                        select_two.setImageResource(R.drawable.circle);
                        gen_plus.setTextColor(getResources().getColor(R.color.app_green));
                        adv_plus.setTextColor(getResources().getColor(R.color.app_green));
                    }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICKFILE_RESULT_CODE) {
            try {
                Uri selectedFileUri = data.getData();
                imagepath = getPath(this, selectedFileUri);
                Picasso.get().load(selectedFileUri).into(image);
                update.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                Log.e("img err = ", e.toString());
            }
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
            multipart.addFormField("name", name_edit);
            multipart.addFormField("gender", gender);
            multipart.addFormField("age", get_age);
            multipart.addFormField("DOB", dob);
            multipart.addFormField("height", get_hight);
            multipart.addFormField("weight", get_weight);
            multipart.addFormField("is_pregnent", get_is_pregnent);
            multipart.addFormField("first_day_of_last_menstrual_cycle", menstrual_cycle);
            multipart.addFormField("pre_pregnancy_weight", pre_pregnancy);
            multipart.addFormField("breast_feeding", feeding);
            multipart.addFormField("child_age", get_child_age);
            multipart.addFormField("is_diabetic", diabetic);
            multipart.addFormField("taking_insulin", insulin);
            multipart.addFormField("diabetic_diet", get_diabetic_diet);
            multipart.addFormField("last_HBA1C", get_HBA1C);
            multipart.addFormField("blood_sugar_no_of_times", sugar_no_of_times);
            multipart.addFormField("blood_sugar_in", sugar_in);
            multipart.addFormField("last_fasting", get_fasting);
            multipart.addFormField("pp_value", get_pp);
            multipart.addFormField("activity_level_one", get_level_one);
            multipart.addFormField("activity_level_two", get_level_two);
            multipart.addFormField("food_habits", habits);
            multipart.addFormField("any_heart_disease", heart_disease);
            multipart.addFormField("is_alcholic", alcholic);
            multipart.addFormField("alcohol_how_often", get_alcohol_how_often);
            multipart.addFormField("is_smoke", smoke);
            multipart.addFormField("smoke_how_often", get_smoke_often);
            multipart.addFormField("do_you_want_a_diet_for_weight_loss", loss_weg);
            multipart.addFormField("hemoglobin", Hemoglobin_s);
            multipart.addFormField("hematocrit", Hematocrit_s);
            multipart.addFormField("fasting_blood_sugar", blood_sugar_s);
            multipart.addFormField("total_cholesterol", Total_cholesterol_s);
            multipart.addFormField("LDL_cholesterol", LDL_cholesterol_s);
            multipart.addFormField("HDL_cholesterol", HDL_cholesterol_s);
            multipart.addFormField("triglycerides", Triglycerides_s);
            multipart.addFormField("vitamin_D_levels", Vitamin_levels_s);
            multipart.addFormField("vitamin_B12_levels", Vitamin_B12_levels_s);
            multipart.addFormField("waist_circumference_inches", Senter_inc_s);
            multipart.addFormField("waist_circumference_cms", Senter_cmc_s);
            multipart.addFormField("blood_pressure1", SSystolic_s);
            multipart.addFormField("blood_pressure2", SDiastolic_s);
            multipart.addFormField("HBA1C_value", HBA1C_value);
            multipart.addFormField("did_your_father_get_heart_disease_before_45years_of_age", heart_diseas_s);
            multipart.addFormField("are_you_drugs_for_high_bp", Bp_s);
            Log.e("keyyyyyyy", dob);
            //multipart.addFormField("image", email_edit);


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

                                new AllSharedPrefrences(Edit_profile_Activity.this).UserAdvanceData1(hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels, waist_circumference_inches, waist_circumference_cms, blood_pressure1, blood_pressure2, did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);

                                new AllSharedPrefrences(Edit_profile_Activity.this).UserData(name, email, image, key, user_id,
                                        gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                                        pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet,
                                        last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one,
                                        activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke,
                                        smoke_how_often, is_login, HBA1C_value);
                                new AllSharedPrefrences(Edit_profile_Activity.this).AutoLogin("Login");
                                Log.e("get_dobbb", get_dob);
                                new AllSharedPrefrences(Edit_profile_Activity.this).UserDob(get_dob);
                                new AllSharedPrefrences(Edit_profile_Activity.this).Weight_loss(weight_loss);


                            } catch (Exception e) {
                                Log.e("error", e.toString());
                            }

                        } else {
                            pd.dismiss();
                            Toast.makeText(Edit_profile_Activity.this, "" + msg, Toast.LENGTH_LONG).show();
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
                                new AllSharedPrefrences(Edit_profile_Activity.this).UserData(name, email, image, key, user_id,
                                        gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                                        pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet,
                                        last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one,
                                        activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke,
                                        smoke_how_often, is_login, HBA1C_value);
                                new AllSharedPrefrences(Edit_profile_Activity.this).AutoLogin("Login");
                                new AllSharedPrefrences(Edit_profile_Activity.this).UserDob(DOB);
                                new AllSharedPrefrences(Edit_profile_Activity.this).Weight_loss(do_you_want_a_diet_for_weight_loss);
                                new AllSharedPrefrences(Edit_profile_Activity.this).UserAdvanceData1(hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels, waist_circumference_inches, waist_circumference_cms, blood_pressure1, blood_pressure2, did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);

                                SharedPreferences sp = getSharedPreferences("KEY", Context.MODE_PRIVATE);
                                sp.edit().clear().commit();

                                Intent in = new Intent(Edit_profile_Activity.this, User_Profile.class);
                                startActivity(in);
                                finish();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sp = getSharedPreferences("KEY", Context.MODE_PRIVATE);
        sp.edit().clear().commit();
        new MyIntent(Edit_profile_Activity.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            try {
                SharedPreferences sp = getSharedPreferences("KEY", Context.MODE_PRIVATE);
                sp.edit().clear().commit();
            } catch (Exception e) {

            }

            Intent in = new Intent(getApplicationContext(), User_Profile.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Edit_profile_Activity.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Edit_profile_Activity.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Edit_profile_Activity.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Edit_profile_Activity.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Edit_profile_Activity.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == edit_dob) {
            new MyIntent(Edit_profile_Activity.this, Profile_Step1.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "1");
            editor.commit();
        }
        if (v == update) {

            name_edit = edit_name.getText().toString();
            email_edit = edit_email.getText().toString();
            dob = edit_dob.getText().toString();
            gender = edit_gender.getText().toString();
            get_age = edit_age.getText().toString();
            get_weight = edit_weight.getText().toString();
            get_hight = edit_height.getText().toString();
            get_is_pregnent = edit_pregnant.getText().toString();
            menstrual_cycle = edit_menstrual.getText().toString();
            pre_pregnancy = edit_pre_preg_weight.getText().toString();
            feeding = edit_breast_feeding.getText().toString();
            get_child_age = edit_child_age.getText().toString();
            diabetic = edit_diabetic.getText().toString();
            insulin = edit_insulin.getText().toString();
            get_diabetic_diet = edit_diabetic_diet.getText().toString();
            get_HBA1C = edit_hba.getText().toString();
            sugar_no_of_times = edit_blood_times.getText().toString();
            sugar_in = edit_blood_in.getText().toString();
            get_fasting = edit_last_fasting.getText().toString();
            get_pp = edit_pp.getText().toString();
            get_level_one = edit_activity_level_one.getText().toString();
            get_level_two = edit_activity_level_two.getText().toString();
            habits = edit_food_habit.getText().toString();
            heart_disease = edit_diabetic_diet.getText().toString();
            alcholic = edit_drink.getText().toString();
            get_alcohol_how_often = edit_drink_often.getText().toString();
            smoke = edit_smoke.getText().toString();
            get_smoke_often = edit_smoke_often.getText().toString();
            loss_weg = weight_loss.getText().toString().toLowerCase();


            Hemoglobin_s = et_Hemoglobin.getText().toString();
            blood_sugar_s = et_blood_sugar.getText().toString();
            Hematocrit_s = et_Hematocrit.getText().toString();
            Total_cholesterol_s = et_Total_cholesterol.getText().toString();
            LDL_cholesterol_s = et_LDL_cholesterol.getText().toString();
            HDL_cholesterol_s = et_HDL_cholesterol.getText().toString();
            Triglycerides_s = et_Triglycerides.getText().toString();
            Vitamin_levels_s = et_Vitamin_levels.getText().toString();
            Vitamin_B12_levels_s = et_Vitamin_B12_levels.getText().toString();
            Senter_inc_s = enter_inc.getText().toString();
            Senter_cmc_s = enter_cmc.getText().toString();
            SSystolic_s = Systolic.getText().toString();
            SDiastolic_s = Diastolic.getText().toString();
            heart_diseas_s = heart_disease_age.getText().toString();
            Bp_s = bp.getText().toString();
            Log.e("yessssss", loss_weg + "");

            try {
                String[] separated = get_hight.split("\\.");
                String f = separated[0];
                String i = separated[1];
                feet = f;
                inch = i;

            } catch (Exception e) {
                e.printStackTrace();
            }

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            pd = new ProgressDialog(Edit_profile_Activity.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
            DataConnectionBmi();
            new Thread(new Runnable() {
                public void run() {
                    try {
                        uploadFile();
                    } catch (Exception e) {
                        Log.e("error", e.toString());
                    }
                }
            }).start();

        }
        if (v == image) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICKFILE_RESULT_CODE);
        }
        if (v == edit_gender) {
            new MyIntent(Edit_profile_Activity.this, Profile_Step1.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "1");
            editor.commit();
        }
        if (v == edit_age) {
            new MyIntent(Edit_profile_Activity.this, Profile_Step1.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "1");
            editor.commit();
        }
        if (v == edit_height) {
            new MyIntent(Edit_profile_Activity.this, Profile_Step2.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "2");
            editor.commit();
        }
        if (v == edit_weight) {
            new MyIntent(Edit_profile_Activity.this, Profile_Step3.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "21");
            editor.commit();
        }
        if (v == weight_loss) {
            new MyIntent(Edit_profile_Activity.this, Profile_Step3.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "21");
            editor.commit();
        }
        if (v == edit_pregnant) {
            new MyIntent(Edit_profile_Activity.this, Profile_Pregnant_3.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "3");
            editor.commit();
        }
        if (v == edit_menstrual) {
            new MyIntent(Edit_profile_Activity.this, Profile_Menstrul_Cycle_4.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "4");
            editor.commit();
        }
        if (v == edit_pre_preg_weight) {
            new MyIntent(Edit_profile_Activity.this, Profile_Menstrul_Cycle_4.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "4");
            editor.commit();
        }
        if (v == edit_child_age) {
            new MyIntent(Edit_profile_Activity.this, Profile_Child_Age_6.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "5");
            editor.commit();
        }
        if (v == edit_diabetic) {
            new MyIntent(Edit_profile_Activity.this, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "6");
            editor.commit();
        }
        if (v == edit_insulin) {
            new MyIntent(Edit_profile_Activity.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "7");
            editor.commit();
        }
        if (v == edit_diabetic_diet) {
            new MyIntent(Edit_profile_Activity.this, Profile_Insulin_8.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "7");
            editor.commit();
        }
        if (v == edit_hba) {
            new MyIntent(Edit_profile_Activity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "8");
            editor.commit();
        }
        if (v == edit_blood_times) {
            new MyIntent(Edit_profile_Activity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "8");
            editor.commit();
        }
        if (v == edit_blood_in) {
            new MyIntent(Edit_profile_Activity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "8");
            editor.commit();
        }
        if (v == edit_last_fasting) {
            new MyIntent(Edit_profile_Activity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "8");
            editor.commit();
        }
        if (v == et_HBA1C_value) {
            new MyIntent(Edit_profile_Activity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "8");
            editor.commit();
        }
        if (v == edit_pp) {
            new MyIntent(Edit_profile_Activity.this, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "8");
            editor.commit();
        }
        if (v == edit_activity_level_one) {
            new MyIntent(Edit_profile_Activity.this, Profile_Active_10.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "9");
            editor.commit();
        }
        if (v == edit_activity_level_two) {
            new MyIntent(Edit_profile_Activity.this, Profile_Active_10.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "9");
            editor.commit();
        }
        if (v == edit_food_habit) {
            new MyIntent(Edit_profile_Activity.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "10");
            editor.commit();

        }
        if (v == edit_heart_dis) {
            new MyIntent(Edit_profile_Activity.this, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "10");
            editor.commit();
        }
        if (v == edit_smoke) {
            new MyIntent(Edit_profile_Activity.this, Smoking.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "11");
            editor.commit();
        }
        if (v == edit_smoke_often) {
            new MyIntent(Edit_profile_Activity.this, Smoking.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "11");
            editor.commit();
        }
        if (v == edit_drink) {
            new MyIntent(Edit_profile_Activity.this, Alcohol.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "12");
            editor.commit();
        }
        if (v == edit_drink_often) {
            new MyIntent(Edit_profile_Activity.this, Alcohol.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "12");
            editor.commit();
        }
        if (v == edit_breast_feeding) {
            new MyIntent(Edit_profile_Activity.this, Profile_Breast_Feeding_5.class, R.anim.enter, R.anim.exit, "1");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "13");
            editor.commit();
        }
        if (v == enter_cmc) {
            new MyIntent(Edit_profile_Activity.this, Body_PARAMETERS.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "14");
            editor.commit();
        }
        if (v == enter_inc) {
            new MyIntent(Edit_profile_Activity.this, Body_PARAMETERS.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "14");
            editor.commit();
        }
        if (v == Diastolic) {
            new MyIntent(Edit_profile_Activity.this, Body_PARAMETERS.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "14");
            editor.commit();
        }
        if (v == Systolic) {
            new MyIntent(Edit_profile_Activity.this, Body_PARAMETERS.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "14");
            editor.commit();
        }
        if (v == et_Hemoglobin) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == et_Hemoglobin) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == et_blood_sugar) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == et_Hematocrit) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == et_Total_cholesterol) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == et_LDL_cholesterol) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == et_HDL_cholesterol) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == et_Triglycerides) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == et_Vitamin_levels) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == et_Vitamin_B12_levels) {
            new MyIntent(Edit_profile_Activity.this, LABORATORY.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "15");
            editor.commit();
        }
        if (v == heart_disease_age) {
            new MyIntent(Edit_profile_Activity.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "16");
            editor.commit();
        }
        if (v == bp) {
            new MyIntent(Edit_profile_Activity.this, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit, "2");
            SharedPreferences sp = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("edit_key", "16");
            editor.commit();
        }
        if (v == general) {

            if (general_lin.getVisibility() == View.VISIBLE) {
                update.setVisibility(View.GONE);
                general_lin.setVisibility(View.GONE);
                //    baisc_lin.setVisibility(View.GONE);
                advance_lin.setVisibility(View.GONE);
//                health.setBackgroundResource(R.drawable.circular_shape)
            } else {
                update.setVisibility(View.VISIBLE);
                general_lin.setVisibility(View.VISIBLE);
                //   baisc_lin.setVisibility(View.GONE);
                advance_lin.setVisibility(View.GONE);
            }
        }

        if (v == advance) {
            update.setVisibility(View.VISIBLE);
            if (advance_lin.getVisibility() == View.VISIBLE) {
                advance_lin.setVisibility(View.GONE);
                general_lin.setVisibility(View.GONE);
                // baisc_lin.setVisibility(View.GONE);
                update.setVisibility(View.GONE);


            } else {
                update.setVisibility(View.VISIBLE);
                advance_lin.setVisibility(View.VISIBLE);
                general_lin.setVisibility(View.GONE);
                //   baisc_lin.setVisibility(View.GONE);

            }
        }
    }

    ImageView select_two, select_one;

    void find_id() {
        gen_plus = findViewById(R.id.gen_plus);
        adv_plus = findViewById(R.id.adv_plus);

        select_one = (ImageView) findViewById(R.id.select_one);
        select_two = (ImageView) findViewById(R.id.select_two);
        image = (CircularImageView) findViewById(R.id.userimage);
        edit_name = (EditText) findViewById(R.id.name);
        edit_email = (EditText) findViewById(R.id.email);
        edit_dob = (EditText) findViewById(R.id.dob);
        update = (Button) findViewById(R.id.update);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        edit_gender = (EditText) findViewById(R.id.gender);
        edit_age = (EditText) findViewById(R.id.age);
        edit_weight = (EditText) findViewById(R.id.weight);
        edit_height = (EditText) findViewById(R.id.height);
        edit_pregnant = (EditText) findViewById(R.id.Pregnant);
        edit_breast_feeding = (EditText) findViewById(R.id.breast_feeding);
        edit_menstrual = (EditText) findViewById(R.id.menstrual_cycle);
        edit_pre_preg_weight = (EditText) findViewById(R.id.Pre_Pregnancy_weight);
        edit_child_age = (EditText) findViewById(R.id.child_Age);
        edit_diabetic = (EditText) findViewById(R.id.Diabetic);
        edit_diabetic_diet = (EditText) findViewById(R.id.diabetic_diet);
        edit_hba = (EditText) findViewById(R.id.HBA1C);
        edit_insulin = (EditText) findViewById(R.id.Insulin);
        edit_blood_times = (EditText) findViewById(R.id.Times);
        edit_blood_in = (EditText) findViewById(R.id.in_a);
        edit_last_fasting = (EditText) findViewById(R.id.Fasting);
        edit_pp = (EditText) findViewById(R.id.PP_Value);
        edit_activity_level_one = (EditText) findViewById(R.id.Activity_level_one);
        edit_activity_level_two = (EditText) findViewById(R.id.Activity_level_two);
        edit_food_habit = (EditText) findViewById(R.id.habits);
        edit_heart_dis = (EditText) findViewById(R.id.Disease);
        edit_smoke = (EditText) findViewById(R.id.Smoke);
        edit_smoke_often = (EditText) findViewById(R.id.Often);
        edit_drink = (EditText) findViewById(R.id.Drink);
        edit_drink_often = (EditText) findViewById(R.id.Often_Week);

        Diastolic = (EditText) findViewById(R.id.Blood_Pressure_two);
        enter_inc = (EditText) findViewById(R.id.Waist_inches);
        enter_cmc = (EditText) findViewById(R.id.Waist_cms);
        Systolic = (EditText) findViewById(R.id.Blood_Pressure_one);

        et_Hemoglobin = (EditText) findViewById(R.id.Hemoglobin);
        et_HBA1C_value = (EditText) findViewById(R.id.HBA1C_value);
        et_Hematocrit = (EditText) findViewById(R.id.Hematocrit);
        et_blood_sugar = (EditText) findViewById(R.id.blood_sugar);
        et_Total_cholesterol = (EditText) findViewById(R.id.Total_cholesterol);
        et_LDL_cholesterol = (EditText) findViewById(R.id.LDL_cholesterol);
        et_HDL_cholesterol = (EditText) findViewById(R.id.HDL_cholesterol);
        et_Triglycerides = (EditText) findViewById(R.id.Triglycerides);
        et_Vitamin_levels = (EditText) findViewById(R.id.Vitamin_D);
        et_Vitamin_B12_levels = (EditText) findViewById(R.id.Vitamin_B12);
        heart_disease_age = (EditText) findViewById(R.id.Risk_age);
        bp = (EditText) findViewById(R.id.Bp);
        weight_loss = (EditText) findViewById(R.id.weight_loss);


        lin_is_pregnent = (LinearLayout) findViewById(R.id.is_Pregnant);
        lin_feeding = (LinearLayout) findViewById(R.id.feeding);
        lin_menstrual = (LinearLayout) findViewById(R.id.menstrual);
        lin_child_age = (LinearLayout) findViewById(R.id.child_age);
        lin_insulin = (LinearLayout) findViewById(R.id.Insulin_lin);
        lin_hba1c = (LinearLayout) findViewById(R.id.HBA1C_lin);
        lin_smoke = (LinearLayout) findViewById(R.id.smoke_lin);
        lin_dirnk = (LinearLayout) findViewById(R.id.drink_lin);

        general_lin = (LinearLayout) findViewById(R.id.lin_general);
        //   baisc_lin = (LinearLayout) findViewById(R.id.lin_basic);
        advance_lin = (LinearLayout) findViewById(R.id.lin_advance);
        //   basic = (LinearLayout) findViewById(R.id.basic);
        general = (LinearLayout) findViewById(R.id.general);
        advance = (LinearLayout) findViewById(R.id.adavance);

    }

    void DataConnectionBmi() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + weight_loss_calculator + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("responseBmiiii= ", response + " rendom =" + randnoo);

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

                                new AllSharedPrefrences(Edit_profile_Activity.this).BMI_Result(bmi, weight_range, Weight_Category, target, weight);
                                // new MyIntent(Alcohol.this, User_Profile.class, R.anim.enter, R.anim.exit);

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

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("gender", gender);
                params.put("age", get_age);
                params.put("dob", dob);
                params.put("ft", feet);
                params.put("inc", inch);
                params.put("weight", get_weight);

                Log.e("paramsBmiiii", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

}
