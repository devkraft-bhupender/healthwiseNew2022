package com.hwi.health.Activitys.AllTests.IdealDiat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.hwi.health.Adapters.RecyclerViewAdapter;
import com.hwi.health.Models.RecycleModel;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Plans.PlansActivity;

import java.util.ArrayList;

public class Ideal_Diat_plan_Adult_result extends AppCompatActivity {
    private ArrayList<RecycleModel> aListModel = new ArrayList<RecycleModel>();
    RecyclerView recycle;
    ActionBar ab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ab = getSupportActionBar();
        ab.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal__diat_plan__adult_result);
        recycle = (RecyclerView) findViewById(R.id.recycle);

        aListModel = (ArrayList<RecycleModel>) getIntent().getSerializableExtra("data");

        RecyclerViewAdapter rv = new RecyclerViewAdapter(aListModel, this);
        GridLayoutManager lLayout = new GridLayoutManager(Ideal_Diat_plan_Adult_result.this, 1);
        recycle.setLayoutManager(lLayout);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(rv);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Ideal_Diat_plan_Adult_result.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
    }
}
