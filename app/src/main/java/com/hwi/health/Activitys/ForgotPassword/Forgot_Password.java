package com.hwi.health.Activitys.ForgotPassword;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Login.LoginActivity;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Forgot_Password extends AppCompatActivity implements View.OnClickListener, BaseUrl {
    ActionBar ab;
    LinearLayout backTosignin;
    Button forgotnext;
    View root;
    String randnoo;
    EditText getEmail;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        backTosignin = (LinearLayout) findViewById(R.id.backTosignin);
        forgotnext = (Button) findViewById(R.id.forgotnext);
        root = (View) findViewById(R.id.activity_forgot__password);
        getEmail = (EditText) findViewById(R.id.getEmail);


        backTosignin.setOnClickListener(this);
        forgotnext.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        new MyIntent(Forgot_Password.this, LoginActivity.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == backTosignin) {

            new MyIntent(Forgot_Password.this, LoginActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == forgotnext) {
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();

            email = getEmail.getText().toString();

            DataConnection();

        }
    }


    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + forgot_password + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");
                        Log.e("URL= ", URLS + forgot_password + randnoo + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");

                            if (status.equals("1")) {
                                Toast.makeText(Forgot_Password.this, message, Toast.LENGTH_SHORT).show();
                                new MyIntent(Forgot_Password.this, Forgot_Password_Done.class, R.anim.enter, R.anim.exit);

                            } else {
                                Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
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
                params.put("email", email);

                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }
}
