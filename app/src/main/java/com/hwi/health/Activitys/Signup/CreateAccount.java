package com.hwi.health.Activitys.Signup;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.AboutApp;
import com.hwi.health.Activitys.Login.LoginActivity;
import com.hwi.health.Activitys.Profile.First.Profile_Step1;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.facebook.FacebookSdk;

import javax.net.ssl.HttpsURLConnection;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener, BaseUrl, GoogleApiClient.OnConnectionFailedListener {

    // google login
    private static final String TAG = "GoogleSignInActivity";
    private static final int RC_SIGN_IN = 007;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private ActionBar ab;
    private Button createacc;
    private TextView login;
    private String Email_S, Password_S, Name_S;
    private EditText getEmail, getPassword, getName;
    private View activity_create_account;
    private String randnoo, device_token, result, result2;
    private View root;
    private ProgressDialog pd;
    private String Username, userEmail, userProfilePhoto = " ", fb_email;
    private String password_v = "[a-zA-Z0-9]{6,15}";
    private String email_v = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    boolean b1;
    private CallbackManager callbackManager;
    private ImageView fb_login;
    private LoginButton fbLoginButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();

        FacebookSdk.sdkInitialize(CreateAccount.this);
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_create_account);
        context = this;
        activity_create_account = (View) findViewById(R.id.activity_create_account);
        createacc = (Button) findViewById(R.id.createacc);
        login = (TextView) findViewById(R.id.backTosignin);
        getPassword = (EditText) findViewById(R.id.getPassword);
        getName = (EditText) findViewById(R.id.getName);
        getEmail = (EditText) findViewById(R.id.getEmail);
        root = (View) findViewById(R.id.activity_create_account);
        fb_login = (ImageView) findViewById(R.id.fb_login);
        fbLoginButton = (LoginButton) findViewById(R.id.login_button);

        createacc.setOnClickListener(this);
        login.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("Device_token", Context.MODE_PRIVATE);
        device_token = sp.getString("device_token", "");

        //TODO----------------- Google Integration start---------------------//
        google();
        //TODO----------------- Facebook Integration Start---------------------//
        facebook();
    }

    void google() {


        ImageView mSignInButton = (ImageView) findViewById(R.id.google_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
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
    }

    void facebook() {
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
                                    userProfilePhoto = "http://graph.facebook.com/"+userId+"/picture?type=square";
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Log.e("emailidddddd", userEmail);
                                try {

                                    new SocialRegistration_Task().execute();
                                } catch (Exception e) {
                                    Log.e("error", e.toString());
                                }
                                //  http://graph.facebook.com/67563683055/picture?type=square
                                Log.e("Email_iddddd", fb_email);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(CreateAccount.this, "Login cancelled by user!", Toast.LENGTH_LONG).show();
                System.out.println("Facebook Login failed!!");
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(CreateAccount.this, "Login unsuccessful!", Toast.LENGTH_LONG).show();
                System.out.println("Facebook Login failed!!");
            }
        });

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


    }

    public boolean isMatched(String data, String patt) {
        boolean result;
        Pattern pt;
        if(data.equals(Email_S)==true)
         pt = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
        else
         pt = Pattern.compile(patt);
        Matcher mt = pt.matcher(data);

        result = mt.matches();

        return result;
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
            new SocialRegistration_Task().execute();
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
        new MyIntent(CreateAccount.this, AboutApp.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == createacc)
        {

            new AllSharedPrefrences(CreateAccount.this).ClearSignupsp();
            SharedPreferences sp = getSharedPreferences("adv", MODE_PRIVATE);
            sp.edit().clear().commit();
            SharedPreferences sp2 = getSharedPreferences("HintPage", MODE_PRIVATE);
            sp2.edit().clear().commit();
            SharedPreferences shared = getSharedPreferences("Alert_popup", MODE_PRIVATE);
            shared.edit().clear().commit();
            SharedPreferences sharedPreferences = getSharedPreferences("Edit_profile", MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();

            SharedPreferences sp3 = new AllSharedPrefrences(this).UserDataget();
            sp.edit().clear().commit();

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            Log.d("riturandom","");

            Email_S = getEmail.getText().toString();
            Name_S = getName.getText().toString();
            Password_S = getPassword.getText().toString();


            if (Name_S.isEmpty()) {
                getName.setError("Enter name first");
                return;
            }

            b1 = isMatched(Email_S, email_v);
            if (b1 == false) {
                getEmail.setError("Enter valid email id");
                return;
            }

            b1 = isMatched(Password_S, password_v);
            if (b1 == false) {
                if(getPassword.getText().toString().length() <8 || getPassword.getText().toString().length() >=15) {
                    getPassword.setError("Password must be between 8-15 characters");
                    return;
                }
                else
                {
                    getPassword.setError("Password can only contain alphabets");
                }
                return;
            }

            new Registration_Task().execute();

        }
        if (view == login)
        {
            new MyIntent(CreateAccount.this, LoginActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == fb_login) {
            fbLoginButton.performClick();
        }
    }

    class Registration_Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(CreateAccount.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();

            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            Log.d("riturandom","");

        }

        @Override
        protected String doInBackground(String... params) {
            result = "";
            try {
                URL url = new URL(URLS + registration + randnoo);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("email", Email_S);
                postDataParams.put("password", Password_S);
                postDataParams.put("device_token", device_token);
                postDataParams.put("name", Name_S);
                Log.d("params", postDataParams + "");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(150000);
                conn.setConnectTimeout(150000);
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
            Log.e("Create Account = ", result + "");
            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");
                if (status.equals("1"))
                {
                    String user_id = jobj.getString("user_id");
                    Log.e("UserId", user_id);
                    new AllSharedPrefrences(CreateAccount.this).Signupsp1(user_id);
                    new AllSharedPrefrences(CreateAccount.this).EmileId(Email_S);
                    SharedPreferences sp = getSharedPreferences("Bottom", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("bt_key", "2");
                    editor.commit();
                    AlertDialogStatus(); // here goes alert for email verification
                } else {
                    Snackbar.make(activity_create_account, message, Snackbar.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Snackbar.make(activity_create_account, "Please Try Again", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    void AlertDialogStatus() {
        final Dialog dialogstatus = new Dialog(CreateAccount.this, R.style.CustomDialog);
        dialogstatus.setContentView(R.layout.res);
        dialogstatus.setCancelable(false);
        Button close = (Button) dialogstatus.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogstatus.dismiss();
                new MyIntent(CreateAccount.this, LoginActivity.class, R.anim.enter, R.anim.exit);
            }
        });
        dialogstatus.show();
    }

    class SocialRegistration_Task extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(CreateAccount.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            result2 = "";
            try {
                URL url = new URL(URLS + social_registration_login + randnoo);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("email", userEmail);
                postDataParams.put("name", Username);
                postDataParams.put("image", userProfilePhoto);
                postDataParams.put("device_token", device_token);
                Log.e("params", postDataParams + "");
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
                        result2 += line;
                    }
                } else {
                    result2 = "";
                }
            } catch (Exception e) {
                Log.e("ERRR", e + "");
            }
            return result2;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("LoginActivity = ", result2 + "");

            try {
                JSONObject jobj = new JSONObject(result2);
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
                    String key = "Social";
                    new AllSharedPrefrences(CreateAccount.this).UserData(name, email, image, key, user_id,
                            gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle,
                            pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet,
                            last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one,
                            activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke,
                            smoke_how_often, is_login,HBA1C_value);
                    new AllSharedPrefrences(CreateAccount.this).AutoLogin("Login");

                    new AllSharedPrefrences(CreateAccount.this).Loginsp(user_id);
                    new AllSharedPrefrences(CreateAccount.this).UserDob(DOB);
                    new AllSharedPrefrences(CreateAccount.this).Weight_loss(do_you_want_a_diet_for_weight_loss);
                    new AllSharedPrefrences(CreateAccount.this).UserAdvanceData1(hemoglobin, hematocrit, fasting_blood_sugar, total_cholesterol, LDL_cholesterol, HDL_cholesterol, triglycerides, vitamin_D_levels, vitamin_B12_levels, waist_circumference_inches, waist_circumference_cms, blood_pressure1, blood_pressure2, did_your_father_get_heart_disease_before_45years_of_age, are_you_drugs_for_high_bp);

                    new AllSharedPrefrences(CreateAccount.this).AutoLogin("Login");
                    SharedPreferences sp = getSharedPreferences("Bottom", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("bt_key", "2");
                    editor.commit();
                    new MyIntent(CreateAccount.this, Profile_Step1.class, R.anim.enter, R.anim.exit);

                } else {

                    JSONObject jj = new JSONObject(result2);
                    JSONObject j = jj.getJSONObject("message");
                    String reason = j.getString("reason");
                    Snackbar.make(root, reason, Snackbar.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.e("fdfdfsdf", e.toString());
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
}
