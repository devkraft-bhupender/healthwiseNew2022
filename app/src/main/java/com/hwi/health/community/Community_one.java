package com.hwi.health.community;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.BeanQuestion;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Community_one extends AppCompatActivity implements BaseUrl ,View.OnClickListener{

    ListView list;
    ArrayList<BeanQuestion> qusList = new ArrayList<>();
    QuestionAdapter adapter;
    String randnoo, all_qus, date, time, question_id;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    TextView text_dum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac2);
        TextView text = (TextView) findViewById(R.id.text);
        ImageView imageView = (ImageView) findViewById(R.id.add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Community_one.this, Community_two.class);
                startActivity(intent);
                finish();
            }
        });
        text.setText("Community");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_one);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        list = (ListView) findViewById(R.id.list_view);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        text_dum = (TextView) findViewById(R.id.text_dum);
        RandomNumber rn = new RandomNumber();
        randnoo = rn.randno();
        DataConnection();

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        adapter = new QuestionAdapter();
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BeanQuestion beanQuestion = qusList.get(position);
                Intent intent = new Intent(Community_one.this, Community_three.class);
                SharedPreferences sp = getSharedPreferences("community", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("qusId", beanQuestion.getQus_id());
                editor.putString("question", beanQuestion.getQus());
                editor.commit();

               /* intent.putExtra("qus_id", beanQuestion.getQus_id());
                intent.putExtra("question", beanQuestion.getQus());*/
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), MoreActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Community_one.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
    }


    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + get_all_question + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {
                                Toast.makeText(Community_one.this, "" + message, Toast.LENGTH_SHORT).show();

                                JSONArray array = jobj.getJSONArray("data");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    all_qus = obj.getString("question");
                                    date = obj.getString("date");
                                    time = obj.getString("time");
                                    question_id = obj.getString("question_id");
                                    BeanQuestion bean = new BeanQuestion(all_qus, date, time, question_id);
                                    Log.e("qussssss", all_qus + "....." + question_id);
                                    qusList.add(bean);
                                    adapter.notifyDataSetChanged();
                                }

                            } else {
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
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //   params.put("Content-Type", "application/json; charset=utf-8");
                params.put("access_keys", AccessToken);
                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Community_one.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Community_one.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Community_one.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Community_one.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Community_one.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }


    class QuestionAdapter extends BaseAdapter {

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
            View v = getLayoutInflater().inflate(R.layout.com_qus_list, null);

            TextView text_qus = (TextView) v.findViewById(R.id.qus_text);
            TextView date = (TextView) v.findViewById(R.id.date);
            TextView time = (TextView) v.findViewById(R.id.time);

            BeanQuestion arr = qusList.get(position);

            text_qus.setText(arr.getQus());
            date.setText(arr.getDate());
            time.setText(arr.getTime());


            return v;
        }
    }
}
