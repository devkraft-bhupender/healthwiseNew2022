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
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.hwi.health.Models.BeanAnswer;
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

public class Community_three extends AppCompatActivity implements BaseUrl,View.OnClickListener {

    String qus_id, question, randnoo, answer_id, answer, question_id, qus_id2, question2,date, time,user_name;
    TextView text_qus;
    Button add_ans;
    ListView listView;
    ArrayList<BeanAnswer> ans_list = new ArrayList<>();
    AnswerAdapter adapter;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("All Answer");

        setContentView(R.layout.community_three);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        text_qus = (TextView) findViewById(R.id.question);
        add_ans = (Button) findViewById(R.id.add_ans);
        listView = (ListView) findViewById(R.id.list);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);

      /*  Intent intent = getIntent();
        qus_id = intent.getStringExtra("qus_id");
        question = intent.getStringExtra("question");
        Log.e("qusssss.........", "" + qus_id);
*/


        SharedPreferences sp2 = getSharedPreferences("community", Context.MODE_PRIVATE);
        qus_id2 = sp2.getString("qusId", "");
        question2 = sp2.getString("question", "");

        text_qus.setText(question2);

        RandomNumber rn = new RandomNumber();
        randnoo = rn.randno();
        DataConnection();

        adapter = new AnswerAdapter();
        listView.setAdapter(adapter);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);


        add_ans.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Community_one.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Community_three.this, Community_one.class, R.anim.enter2, R.anim.exit2);
    }


    void DataConnection() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URLS + get_ans_by_queid + randnoo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response= ", response + "");

                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String message = jobj.getString("message");


                            if (status.equals("1")) {
                                Toast.makeText(Community_three.this, "" + message, Toast.LENGTH_SHORT).show();

                                JSONArray array = jobj.getJSONArray("data");
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject obj = array.getJSONObject(i);
                                    answer_id = obj.getString("answer_id");
                                    answer = obj.getString("answer");
                                    question_id = obj.getString("question_id");
                                    String getDate = obj.getString("is_created");
                                    user_name= obj.getString("name");

                                    String[] separated = getDate.split(" ");
                                    date =  separated[0];
                                    time =  separated[1];
                                    Log.e("date_time",date+"...."+time);
                                  /*  bean.setName(user_name);
                                    bean.setAns(answer);
                                    bean.setDate(date);
                                    bean.setTime(time);*/
                                    BeanAnswer bean = new BeanAnswer(answer,date,time,user_name);
                                    ans_list.add(bean);
                                    adapter.notifyDataSetChanged();


                                }


                            } else {

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
                params.put("question_id", qus_id2);
                Log.e("params", params + "");

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Community_three.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Community_three.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Community_three.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Community_three.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Community_three.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == add_ans) {
            Intent intent = new Intent(Community_three.this, Community_four.class);
            intent.putExtra("qusId", qus_id2);
            intent.putExtra("Question", question2);
            startActivity(intent);
            finish();
        }
    }

    class AnswerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ans_list.size();
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
            View v = getLayoutInflater().inflate(R.layout.answer_list, null);

            TextView text_qus = (TextView) v.findViewById(R.id.qus_text);
            TextView text_name = (TextView) v.findViewById(R.id.text_name);
            TextView date = (TextView) v.findViewById(R.id.date);
            TextView time = (TextView) v.findViewById(R.id.time);

            BeanAnswer arr = ans_list.get(position);

            text_qus.setText(arr.getAns());
            text_name.setText(arr.getName());
            date.setText(arr.getDate());
            time.setText(arr.getTime());


            return v;
        }
    }
}
