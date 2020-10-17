package com.hwi.health.Activitys.More.manage_report;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.Upload_file_data;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Plans.Treatmentplan;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Upload_file_list extends AppCompatActivity implements BaseUrl,View.OnClickListener{

    ArrayList<Upload_file_data> qusList = new ArrayList<>();
    File_Upload_Adapter adapter;
    ListView list;
    TextView text_dum;
    String randnoo, file_link,file_name,user_id,treatment,Prescription_key,result;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac2);
        TextView text = (TextView) findViewById(R.id.text);
        ImageView imageView = (ImageView) findViewById(R.id.add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Upload_file_list.this, Upload_file.class);
                startActivity(intent);
                finish();
            }
        });
        text.setText("Upload File");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file_list);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        user_id = sp.getString("Userid", "");
        Prescription_key = sp.getString("key_pres", "");

        list = (ListView) findViewById(R.id.list_view);
        text_dum = (TextView) findViewById(R.id.text_dum);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

        RandomNumber rn = new RandomNumber();
        randnoo = rn.randno();
        new Upload_File_Task().execute();

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        adapter = new File_Upload_Adapter();
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Upload_file_data beanQuestion = qusList.get(position);
                String urll = beanQuestion.getFile_url();

                Log.e("file url - ",urll);

                if (urll.equals("null")){
                    Toast.makeText(Upload_file_list.this, "No File Attach", Toast.LENGTH_SHORT).show();
                }else
                {
                    Intent in = new Intent(getApplicationContext(), WebActivity.class);
                    in.putExtra("URL",urll.replaceAll(" ","%20"));
                    startActivity(in);
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {


            if (Prescription_key.equals("1")){
                Intent in = new Intent(getApplicationContext(), Treatmentplan.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }else if(Prescription_key.equals("2")){
                Intent in = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }else {
                Intent in = new Intent(getApplicationContext(), MoreActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Prescription_key.equals("1")){
            new MyIntent(Upload_file_list.this, Treatmentplan.class, R.anim.enter2, R.anim.exit2);
        }else if(Prescription_key.equals("2")){
            new MyIntent(Upload_file_list.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }else {
            new MyIntent(Upload_file_list.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Upload_file_list.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Upload_file_list.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Upload_file_list.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Upload_file_list.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Upload_file_list.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
    //////////////////


    class Upload_File_Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Upload_file_list.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }
        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + get_uploadsfile + randnoo);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);


                Log.e("params", postDataParams+"");


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
                Log.e("ERRR",e+"");
            }
            return result;
        }
        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
            Log.e("list data files  = ", result + "");

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");


                if (status.equals("1")) {
                    pd.dismiss();
                    Toast.makeText(Upload_file_list.this, "" + message, Toast.LENGTH_SHORT).show();

                    JSONArray array = jobj.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        file_link = obj.getString("uploads_file");
                        file_name = obj.getString("file_name");
                        treatment = obj.getString("treatment");

                        Upload_file_data bean = new Upload_file_data(file_name,file_link,treatment);
                        qusList.add(bean);
                        adapter.notifyDataSetChanged();
                    }

                } else {
                    pd.dismiss();
                    text_dum.setVisibility(View.VISIBLE);
                    list.setVisibility(View.GONE);




                }
            } catch (Exception e) {
                Log.e("errrrrrrr", e.toString());
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


    //////////////////

  /*  void DataConnection() {
        pd = new ProgressDialog(Upload_file_list.this);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
        pd.show();
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + get_uploadsfile + randnoo,
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
                                Toast.makeText(Upload_file_list.this, "" + message, Toast.LENGTH_SHORT).show();

                                JSONArray array = jobj.getJSONArray("data");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    file_link = obj.getString("uploads_file");
                                    file_name = obj.getString("file_name");
                                    treatment = obj.getString("treatment");

                                    Upload_file_data bean = new Upload_file_data(file_name,file_link,treatment);
                                    qusList.add(bean);
                                    adapter.notifyDataSetChanged();
                                }

                            } else {
                                pd.dismiss();
                                text_dum.setVisibility(View.VISIBLE);
                                list.setVisibility(View.GONE);
                                JSONObject jj = new JSONObject(response);
                                JSONObject j = jj.getJSONObject("message");


                            }
                        } catch (Exception e) {
                            Log.e("errrrrrrr", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("VolleyError= ", error + "");
                text_dum.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
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
    }*/

    class File_Upload_Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return qusList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = getLayoutInflater().inflate(R.layout.upload_item, null);

            TextView file_text = (TextView) v.findViewById(R.id.file_text);
            TextView treat_text = (TextView) v.findViewById(R.id.treat_text);

            Upload_file_data arr = qusList.get(position);

            file_text.setText(arr.getFile_name());
            treat_text.setText(arr.getTreat_text());


            return v;
        }
    }
}
