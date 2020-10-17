package com.hwi.health.Activitys.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hwi.health.Activitys.Profile.First.SetSecondStepData;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.Activitys.Signup.CreateAccount;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.AboutApp;
import com.hwi.health.Activitys.ForgotPassword.Forgot_Password;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

import com.facebook.FacebookSdk;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, BaseUrl, GoogleApiClient.OnConnectionFailedListener {
    // google login
    private static final String TAG = "GoogleSignInActivity";
    private static final int RC_SIGN_IN = 007;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    // facebook login
    private CallbackManager callbackManager;
    ImageView fb_login;
    LoginButton fbLoginButton;

    ProgressDialog pd;
    ActionBar ab;
    TextView forgotpass, signup;
    Button signin;
    String IMEI, randnoo;
    Context context;
    String S_email, S_Pass, user_id;
    View root;
    EditText getEmail, getPassword;
    String Username, userEmail, userProfilePhoto = "", fb_email, device_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        // fb
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        forgotpass = (TextView) findViewById(R.id.forgotpass);
        signup = (TextView) findViewById(R.id.backTosignin);
        signin = (Button) findViewById(R.id.signin);
        root = (View) findViewById(R.id.activity_login);
        getPassword = (EditText) findViewById(R.id.getPassword);
        getEmail = (EditText) findViewById(R.id.getEmail);
        fb_login = (ImageView) findViewById(R.id.fb_login);
        fbLoginButton = (LoginButton) findViewById(R.id.login_button);

        forgotpass.setOnClickListener(this);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);


        SharedPreferences sp = getSharedPreferences("Device_token", Context.MODE_PRIVATE);
        device_token = sp.getString("device_token", "");

        ImageView mSignInButton = (ImageView) findViewById(R.id.google_login);
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        //----------------- Google Integration End---------------------//

        //----------------- Facebook Integration Start---------------------//


        fbLoginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_friends"));

        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                System.out.println("Facebook Login Successful!");
                System.out.println("Logged in user Details : ");
                System.out.println("--------------------------");
                System.out.println("User ID  : " + loginResult.getAccessToken().getUserId());
                System.out.println("Authentication Token : " + loginResult.getAccessToken().getToken());
                // Log.e("Email_iddddd", loginResult.getAccessToken().getUserId());
                // Toast.makeText(Login_Organizer.this, "Login Successful!", Toast.LENGTH_LONG).show();

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.e("LoginActivity", response.toString());

                                // Application code
                                try {
                                    fb_email = object.getString("email");
                                    String userId = object.getString("id");
                                    Username = object.getString("name");
                                    userEmail = object.getString("email");
                                    Log.e("emailidddddd", userEmail);
                                    try {
                                        userProfilePhoto = "http://graph.facebook.com/"+userId+"/picture?type=square";
                                        SocialRegistration();
                                        return;
                                    } catch (Exception e) {
                                        Log.e("error", e.toString());
                                    }
                                    //  http://graph.facebook.com/67563683055/picture?type=square
                                    Log.e("Email_iddddd", fb_email);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }
            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login cancelled by user!", Toast.LENGTH_LONG).show();
                System.out.println("Facebook Login failed!!");
            }
            @Override
            public void onError(FacebookException e) {
                Toast.makeText(LoginActivity.this, "Login unsuccessful!", Toast.LENGTH_LONG).show();
                System.out.println("Facebook Login failed!!");
            }
        });

        //----------------- Facebook Integration End---------------------//

    }

    //------Google
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        //

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount userAccount = result.getSignInAccount();
            String userId = userAccount.getId();
            Username = userAccount.getDisplayName();
            userEmail = userAccount.getEmail();
            Log.e("emailidddddd", userEmail);
            try {
                userProfilePhoto = userAccount.getPhotoUrl().toString();
                Log.e("google plus", userProfilePhoto);
            } catch (Exception e) {
                Log.e("error", e.toString());
            }


          /*  new AllSharedPrefrences(LoginActivity.this).AutoLogin("Login");
            new AllSharedPrefrences(LoginActivity.this).UserData(Username,userEmail,userProfilePhoto,"Social");
            new MyIntent(LoginActivity.this, HomeActivity.class,R.anim.enter,R.anim.exit);
*/
            Toast.makeText(LoginActivity.this, "Google Login", Toast.LENGTH_SHORT).show();

            SocialRegistration();
            return;

        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

    }

    @Override
    protected void onStart() {

        super.onStart();
        mGoogleApiClient.connect();
       /* OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    //hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }*/
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(LoginActivity.this, AboutApp.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == forgotpass) {
            new MyIntent(LoginActivity.this, Forgot_Password.class, R.anim.enter, R.anim.exit);
        }
        if (view == signup) {
            new MyIntent(LoginActivity.this, CreateAccount.class, R.anim.enter, R.anim.exit);
        }
        if (view == signin) {

            S_email = getEmail.getText().toString();
            S_Pass = getPassword.getText().toString();

            if (S_email.equals("") || S_email == null) {
                Toast.makeText(context, "Enter Email First", Toast.LENGTH_SHORT).show();
            } else if (S_Pass.equals("") || S_Pass == null) {
                Toast.makeText(context, "Enter Password First", Toast.LENGTH_SHORT).show();
            } else {
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();

                DataConnection();
                return;
            }
        }
        if (view == fb_login) {
            fbLoginButton.performClick();
        }
    }

    void DataConnection() {

        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
        pd.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + login + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response login= ", response + "");
                        Log.e("login= ", URLS + login + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {
                                pd.dismiss();

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
                                new AllSharedPrefrences(LoginActivity.this).UserData(name, email, image, key, user_id,
                                        gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                                        pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet,
                                        last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one,
                                        activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke,
                                        smoke_how_often, is_login, HBA1C_value);

                                new AllSharedPrefrences(LoginActivity.this).AutoLogin("Login");
                                SharedPreferences sp = getSharedPreferences("Bottom", MODE_PRIVATE);

                                new AllSharedPrefrences(LoginActivity.this).UserDob(DOB);
                                new AllSharedPrefrences(LoginActivity.this).Weight_loss(do_you_want_a_diet_for_weight_loss);
                                new AllSharedPrefrences(LoginActivity.this).UserAdvanceData1(hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels, waist_circumference_inches, waist_circumference_cms, blood_pressure1, blood_pressure2, did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);

                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("bt_key", "1");
                                editor.commit();

                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();


                                if(gender.isEmpty()==true) {
                                    new MyIntent(LoginActivity.this, HomeActivity.class, R.anim.enter, R.anim.exit);

                                    }
                                else
                                {
                                    SharedPreferences.Editor editor1 = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
                                    editor1.putString("newlogin", "0");
                                    editor1.commit();
                                    SetSecondStepData ssd = new SetSecondStepData(LoginActivity.this, User_Profile.class, randnoo,
                                            gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                                            pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin,
                                            diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting,
                                            HBA1C_value, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease,
                                            is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, DOB, do_you_want_a_diet_for_weight_loss,"yes","14");
                                    ssd.Data(LoginActivity.this, User_Profile.class, randnoo, hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels,waist_circumference_inches, waist_circumference_inches, blood_pressure1, blood_pressure2,did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);
                                    ssd.DataConnection();

                                }

                            } else {
                                pd.dismiss();
                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");
                                Snackbar.make(root, reason, Snackbar.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
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
                params.put("email", S_email);
                params.put("Password", S_Pass);
                params.put("device_token", device_token);

                SharedPreferences sp = getSharedPreferences("UserVerify", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("access_keys", AccessToken);
                editor.putString("email", S_email);
                editor.putString("password", S_Pass);
                editor.putString("device_token", device_token);
                editor.commit();

                Log.e("params", params + "");

                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                600000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(postRequest);
    }

    void SocialRegistration() {
        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
        pd.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + social_registration_login + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");
                        Log.e("urll.....= ", URLS + social_registration_login + randnoo + "");
                        pd.dismiss();
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {

                                JSONObject object = jobj.getJSONObject("profile_info");
                                user_id = object.getString("user_id");

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
                                String key = "Social";

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

                                new AllSharedPrefrences(LoginActivity.this).UserData(name, email, image, key, user_id,
                                        gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                                        pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet,
                                        last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one,
                                        activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke,
                                        smoke_how_often, is_login, HBA1C_value);
                                new AllSharedPrefrences(LoginActivity.this).AutoLogin("Login");

                                new AllSharedPrefrences(LoginActivity.this).Loginsp(user_id);
                                // Profile_completion_persentage(user_id);
                                new AllSharedPrefrences(LoginActivity.this).UserDob(DOB);
                                new AllSharedPrefrences(LoginActivity.this).Weight_loss(do_you_want_a_diet_for_weight_loss);
                                new AllSharedPrefrences(LoginActivity.this).UserAdvanceData1(hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels, waist_circumference_inches, waist_circumference_cms, blood_pressure1, blood_pressure2, did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);

                                SharedPreferences sp = getSharedPreferences("Bottom", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("bt_key", "1");
                                editor.commit();


                                //new MyIntent(LoginActivity.this, HomeActivity.class, R.anim.enter, R.anim.exit);
                              //  Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();


                                if(gender.isEmpty()==true) {
                                    new MyIntent(LoginActivity.this, HomeActivity.class, R.anim.enter, R.anim.exit);
                                }
                                else
                                {
                                    SharedPreferences.Editor editor1 = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
                                    editor1.putString("newlogin", "0");
                                    editor1.commit();
                                    SetSecondStepData ssd = new SetSecondStepData(LoginActivity.this, User_Profile.class, randnoo,
                                            gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                                            pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin,
                                            diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting,
                                            HBA1C_value, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease,
                                            is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id, DOB, do_you_want_a_diet_for_weight_loss,"yes","14");
                                    ssd.Data(LoginActivity.this, User_Profile.class, randnoo, hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels,waist_circumference_inches, waist_circumference_inches, blood_pressure1, blood_pressure2,did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);
                                    ssd.DataConnection();

                                }
                                //Snackbar.make(activity_create_account,message,Snackbar.LENGTH_LONG).show();
                                // new MyIntent(LoginActivity.this, HomeActivity.class,R.anim.enter,R.anim.exit);
/*

                                new AllSharedPrefrences(LoginActivity.this).UserData(Username,userEmail,userProfilePhoto,"Social");
                                new MyIntent(LoginActivity.this, HomeActivity.class,R.anim.enter,R.anim.exit);
*/

//                                new AllSharedPrefrences(LoginActivity.this).UserData(Username,userEmail,userProfilePhoto,"Social");

                            } else {

                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");
                                Snackbar.make(root, reason, Snackbar.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            pd.dismiss();
                            Log.e("fdfdfsdf", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                pd.dismiss();
                Log.e("VolleyError= ", error + "");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                params.put("email", userEmail);
                params.put("name", Username);
                params.put("image", userProfilePhoto);
                params.put("device_token", device_token);
                Log.e("params", params + "");

                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                600000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(this).add(postRequest);
    }

    void Profile_completion_persentage(final String user_id1) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + get_profile_completion_persentage + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {
                                pd.dismiss();
                                String Total_profile_persetage = jobj.getString("Total_profile_persetage");

                                //   Toast.makeText(context, Total_profile_persetage, Toast.LENGTH_SHORT).show();

                                new AllSharedPrefrences(LoginActivity.this).Profile_complete_persentage(Total_profile_persetage);
                                new AllSharedPrefrences(LoginActivity.this).AutoLogin("Login");
                                // new MyIntent(LoginActivity.this, HomeActivity.class, R.anim.enter, R.anim.exit);

                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                //   Snackbar.make(activity_create_account,message,Snackbar.LENGTH_LONG).show();

                            } else {
                                pd.dismiss();
                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");
                                String reason = j.getString("reason");
                                Snackbar.make(root, reason, Snackbar.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
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
                params.put("user_id", user_id1);

                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

}
