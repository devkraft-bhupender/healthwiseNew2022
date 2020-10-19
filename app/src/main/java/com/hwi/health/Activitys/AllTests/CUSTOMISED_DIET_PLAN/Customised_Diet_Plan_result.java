package com.hwi.health.Activitys.AllTests.CUSTOMISED_DIET_PLAN;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hwi.health.Adapters.Ideal_Customized_Adapter;
import com.hwi.health.Models.customized_model;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import java.util.ArrayList;

public class Customised_Diet_Plan_result extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<customized_model> aListModel = new ArrayList<customized_model>();
    ArrayList<customized_model> Morningsnack_list = new ArrayList<customized_model>();
    ArrayList<customized_model> Dinner_list = new ArrayList<customized_model>();
    ArrayList<customized_model> snack_list = new ArrayList<customized_model>();
    ArrayList<customized_model> Lunch_list = new ArrayList<customized_model>();

    // recycle_brView recycle_b;
    ActionBar ab;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Spinner spin;
    ArrayAdapter<String> adapter;
    ArrayList<String> spin_list = new ArrayList<>();
    String exercise,value = "1";
    LinearLayout lin_Breakfast,lin_Lunch,lin_Evening_snack,lin_Dinner,lin_Morning_snack,edit_diet;
    RecyclerView recycle_b,recycle_l,recycle_s,recycle_d,recycle_m;
    Button Custom_diet_plan;
    String  loadfromcustomize = "0";


    ArrayList<customized_model> cleanList(ArrayList<customized_model> fm, String key) {
        ArrayList<customized_model> temp = new ArrayList<customized_model>();


        for (int i = 0; i < fm.size(); i++) {
            if(key.equals("1")==true) {
                if (key.equals(value) == true && fm.get(i).getThree().equals("0.00") != true) {
                    temp.add(fm.get(i));
                }
            }
            if(key.equals("2")==true) {
                if (key.equals(value) == true && fm.get(i).getFour().equals("0.00") != true) {
                    temp.add(fm.get(i));
                }
            }
            if(key.equals("3")==true) {
                if (key.equals(value) == true && fm.get(i).getFive().equals("0.00") != true) {
                    temp.add(fm.get(i));
                }
            }
        }
        return temp;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.customised__diet__plan_result);
        text.setText("Your Standard Meal Plan");
        try {
            loadfromcustomize = (String) getIntent().getSerializableExtra("loadfromcustomize");
            if (loadfromcustomize.equals("1")) {
                text.setText("Your Customize Diet Plan");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        recycle_b = (RecyclerView) findViewById(R.id.recycle_b);

        recycle_l = (RecyclerView) findViewById(R.id.recycle_l);
        recycle_l.setNestedScrollingEnabled(false);
        recycle_s = (RecyclerView) findViewById(R.id.recycle_s);
        recycle_s.setNestedScrollingEnabled(false);
        recycle_d = (RecyclerView) findViewById(R.id.recycle_d);
        recycle_m = (RecyclerView) findViewById(R.id.recycle_m);
        lin_Breakfast = (LinearLayout) findViewById(R.id.Breakfast);
        lin_Lunch = (LinearLayout) findViewById(R.id.Lunch);
        lin_Evening_snack = (LinearLayout) findViewById(R.id.Evening_snack);
        lin_Dinner = (LinearLayout) findViewById(R.id.Dinner);
        lin_Morning_snack = (LinearLayout) findViewById(R.id.Morning_snack);
        edit_diet = (LinearLayout) findViewById(R.id.edit_diet);
        Custom_diet_plan = (Button) findViewById(R.id.get_custom_plan);

        spin = (Spinner) findViewById(R.id.spin);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        edit_diet.setOnClickListener(this);
        Custom_diet_plan.setOnClickListener(this);

        spin_list.add("Basic Diet");
        spin_list.add("Optimized Diet");
        spin_list.add("Ideal Diet for people with health risks");
        adapter = new ArrayAdapter<String>(Customised_Diet_Plan_result.this, android.R.layout.simple_spinner_dropdown_item,spin_list);
        spin.setAdapter(adapter);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exercise = spin_list.get(position);
                if (exercise.equals("Basic Diet")){
                    value = "1";
                    Ideal_Customized_Adapter rv = new Ideal_Customized_Adapter(cleanList(aListModel,value),Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);
                    GridLayoutManager lLayout = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_b.setLayoutManager(lLayout);
                    recycle_b.setItemAnimator(new DefaultItemAnimator());
                    recycle_b.setAdapter(rv);
                    rv.notifyDataSetChanged();


                    Ideal_Customized_Adapter rv1 = new Ideal_Customized_Adapter(cleanList(Lunch_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout_l = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_l.setLayoutManager(lLayout_l);
                    recycle_l.setItemAnimator(new DefaultItemAnimator());
                    recycle_l.setAdapter(rv1);
                    rv1.notifyDataSetChanged();


                    Ideal_Customized_Adapter rv2 = new Ideal_Customized_Adapter(cleanList(Dinner_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout_d = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_d.setLayoutManager(lLayout_d);
                    recycle_d.setItemAnimator(new DefaultItemAnimator());
                    recycle_d.setAdapter(rv2);
                    rv2.notifyDataSetChanged();


                    Ideal_Customized_Adapter rv3 = new Ideal_Customized_Adapter(cleanList(snack_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout_s = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_s.setLayoutManager(lLayout_s);
                    recycle_s.setItemAnimator(new DefaultItemAnimator());
                    recycle_s.setAdapter(rv3);
                    rv3.notifyDataSetChanged();

                    Ideal_Customized_Adapter rv4 = new Ideal_Customized_Adapter(cleanList(Morningsnack_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout__m = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_m.setLayoutManager(lLayout__m);
                    recycle_m.setItemAnimator(new DefaultItemAnimator());
                    recycle_m.setAdapter(rv4);
                    rv4.notifyDataSetChanged();


                }
                else if (exercise.equals("Optimized Diet")){
                    value = "2";
                    Ideal_Customized_Adapter rv = new Ideal_Customized_Adapter(cleanList(aListModel,value),Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);
                    GridLayoutManager lLayout = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_b.setLayoutManager(lLayout);
                    recycle_b.setItemAnimator(new DefaultItemAnimator());
                    recycle_b.setAdapter(rv);
                    rv.notifyDataSetChanged();

                    Ideal_Customized_Adapter rv1 = new Ideal_Customized_Adapter(cleanList(Lunch_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout_l = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_l.setLayoutManager(lLayout_l);
                    recycle_l.setItemAnimator(new DefaultItemAnimator());
                    recycle_l.setAdapter(rv1);
                    rv1.notifyDataSetChanged();


                    Ideal_Customized_Adapter rv2 = new Ideal_Customized_Adapter(cleanList(Dinner_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout_d = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_d.setLayoutManager(lLayout_d);
                    recycle_d.setItemAnimator(new DefaultItemAnimator());
                    recycle_d.setAdapter(rv2);
                    rv2.notifyDataSetChanged();


                    Ideal_Customized_Adapter rv3 = new Ideal_Customized_Adapter(cleanList(snack_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout_s = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_s.setLayoutManager(lLayout_s);
                    recycle_s.setItemAnimator(new DefaultItemAnimator());
                    recycle_s.setAdapter(rv3);
                    rv3.notifyDataSetChanged();

                    Ideal_Customized_Adapter rv4 = new Ideal_Customized_Adapter(cleanList(Morningsnack_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout__m = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_m.setLayoutManager(lLayout__m);
                    recycle_m.setItemAnimator(new DefaultItemAnimator());
                    recycle_m.setAdapter(rv4);
                    rv4.notifyDataSetChanged();

                }
                else if (exercise.equals("Ideal Diet for people with health risks")){
                    value = "3";
                    Ideal_Customized_Adapter rv = new Ideal_Customized_Adapter(cleanList(aListModel,value),Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);
                    GridLayoutManager lLayout = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_b.setLayoutManager(lLayout);
                    recycle_b.setItemAnimator(new DefaultItemAnimator());
                    recycle_b.setAdapter(rv);
                    rv.notifyDataSetChanged();

                    Ideal_Customized_Adapter rv1 = new Ideal_Customized_Adapter(cleanList(Lunch_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout_l = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_l.setLayoutManager(lLayout_l);
                    recycle_l.setItemAnimator(new DefaultItemAnimator());
                    recycle_l.setAdapter(rv1);
                    rv1.notifyDataSetChanged();


                    Ideal_Customized_Adapter rv2 = new Ideal_Customized_Adapter(cleanList(Dinner_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);
                    GridLayoutManager lLayout_d = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_d.setLayoutManager(lLayout_d);
                    recycle_d.setItemAnimator(new DefaultItemAnimator());
                    recycle_d.setAdapter(rv2);
                    rv2.notifyDataSetChanged();


                    Ideal_Customized_Adapter rv3 = new Ideal_Customized_Adapter(cleanList(snack_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout_s = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_s.setLayoutManager(lLayout_s);
                    recycle_s.setItemAnimator(new DefaultItemAnimator());
                    recycle_s.setAdapter(rv3);
                    rv3.notifyDataSetChanged();

                    Ideal_Customized_Adapter rv4 = new Ideal_Customized_Adapter(cleanList(Morningsnack_list,value), Customised_Diet_Plan_result.this,value,Customised_Diet_Plan_result.this);

                    GridLayoutManager lLayout__m = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
                    recycle_m.setLayoutManager(lLayout__m);
                    recycle_m.setItemAnimator(new DefaultItemAnimator());
                    recycle_m.setAdapter(rv4);
                    rv4.notifyDataSetChanged();
                }
                Log.e("slistttt", exercise);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
////////////////breakfast///////////
        aListModel = (ArrayList<customized_model>) getIntent().getSerializableExtra("data");
        if (aListModel.size() == 0){
            lin_Breakfast.setVisibility(View.GONE);
        }
        else {
            lin_Breakfast.setVisibility(View.VISIBLE);
        }
        Ideal_Customized_Adapter rv = new Ideal_Customized_Adapter(cleanList(aListModel,value), this,value,Customised_Diet_Plan_result.this);
        GridLayoutManager lLayout = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
        recycle_b.setLayoutManager(lLayout);
        recycle_b.setItemAnimator(new DefaultItemAnimator());
        recycle_b.setAdapter(rv);

        //////////////lunch////////////

        Lunch_list = (ArrayList<customized_model>) getIntent().getSerializableExtra("lunch");
        Ideal_Customized_Adapter rv1 = new Ideal_Customized_Adapter(cleanList(Lunch_list,value), this,value,Customised_Diet_Plan_result.this);
        GridLayoutManager lLayout_l = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
        recycle_l.setLayoutManager(lLayout_l);
        recycle_l.setItemAnimator(new DefaultItemAnimator());
        recycle_l.setAdapter(rv1);

        ///////////dinner/////////////

        Dinner_list = (ArrayList<customized_model>) getIntent().getSerializableExtra("dinner");
        Ideal_Customized_Adapter rv2 = new Ideal_Customized_Adapter(cleanList(Dinner_list,value), this,value,Customised_Diet_Plan_result.this);
        GridLayoutManager lLayout_d = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
        recycle_d.setLayoutManager(lLayout_d);
        recycle_d.setItemAnimator(new DefaultItemAnimator());
        recycle_d.setAdapter(rv2);

        ////////////////snack//////////

        snack_list = (ArrayList<customized_model>) getIntent().getSerializableExtra("Snack");
        Ideal_Customized_Adapter rv3 = new Ideal_Customized_Adapter(cleanList(snack_list,value), this,value,Customised_Diet_Plan_result.this);
        GridLayoutManager lLayout_s = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
        recycle_s.setLayoutManager(lLayout_s);
        recycle_s.setItemAnimator(new DefaultItemAnimator());
        recycle_s.setAdapter(rv3);

        ////////////////mrg_snack//////////
        Morningsnack_list = (ArrayList<customized_model>) getIntent().getSerializableExtra("MSnack");

        if (Morningsnack_list.size() == 0){
            lin_Morning_snack.setVisibility(View.GONE);
        }
        else {
            lin_Morning_snack.setVisibility(View.VISIBLE);
        }
        Ideal_Customized_Adapter rv4 = new Ideal_Customized_Adapter(cleanList(Morningsnack_list,value), this,value,Customised_Diet_Plan_result.this);
        GridLayoutManager lLayout__m = new GridLayoutManager(Customised_Diet_Plan_result.this, 2);
        recycle_m.setLayoutManager(lLayout__m);
        recycle_m.setItemAnimator(new DefaultItemAnimator());
        recycle_m.setAdapter(rv4);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Customised_Diet_Plan_result.this, PlansActivity.class, R.anim.enter, R.anim.exit);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent in = new Intent(getApplicationContext(), PlansActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Customised_Diet_Plan_result.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == profile_L) {
            new MyIntent(Customised_Diet_Plan_result.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }if (v == log_L) {
            new MyIntent(Customised_Diet_Plan_result.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == plans_L) {
            new MyIntent(Customised_Diet_Plan_result.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == more_L) {
            new MyIntent(Customised_Diet_Plan_result.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }if (v == edit_diet) {
            new MyIntent(Customised_Diet_Plan_result.this, Major_Meals_Page1.class, R.anim.enter, R.anim.exit);
        }if (v == Custom_diet_plan){
            new MyIntent(Customised_Diet_Plan_result.this, Major_Meals_Page1.class, R.anim.enter, R.anim.exit);
        }
    }
}
