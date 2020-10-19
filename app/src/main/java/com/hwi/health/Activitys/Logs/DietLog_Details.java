package com.hwi.health.Activitys.Logs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.Activitys.Plans.Excercize_plans.CalorieCalculator;
import com.hwi.health.Activitys.Plans.Excercize_plans.NonScrollListView;
import com.hwi.health.Activitys.Plans.Search_Activity;
import com.hwi.health.Models.DietLog_Models;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.sqlite_database.DBHelper;
import com.hwi.health.sqlite_database.ProductController_For_All;
import com.hwi.health.sqlite_database.TestAdapter;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DietLog_Details extends AppCompatActivity implements View.OnClickListener {

    ImageView food_image;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    AutoCompleteTextView search_food;
    ArrayAdapter<DietLog_Models> adapter;
    ArrayList<DietLog_Models> list2 = new ArrayList<>();
    Dialog dialog;
    List<DietLog_Models> list_get = new ArrayList<>();
    TestAdapter mDbHelper = null;
    String Diet_log_key, current_date, user_id, food_data = "";
    LAdapter3 adapter2;
    float total_count = 0;
    Button checkss;
    private int mYear, mMonth, mDay, mHour, mMinute;
    int YEAR, MONTH, DAY;
    ListView lists;
    int number = 1;
    String total_Energy, total_Carbs, total_Sugar, total_Protein, total_Fat, total_fibre, total_sat_fat, total_souium, total_cholestrol;
    float totalEnergy, totalCarbs, totalSugar, totalProtein, totalFat, totalfibre, totalsat_fat, totalsouium, totalcholestrol;
    ProductController_For_All controller = new ProductController_For_All(DietLog_Details.this);
    private static int code = 104;
    ScrollView dietLogScroll=null;
    int Totalinsertedelement = 0;

    DBHelper helper=null;

    void check_past_log() {

        final Dialog dialog = new Dialog(DietLog_Details.this,R.style.CustomDialog);
        dialog.setContentView(R.layout.diet_log_popup_date);
        TextView xx = (TextView) dialog.findViewById(R.id.text_popup);
        final EditText select_date = (EditText) dialog.findViewById(R.id.select_date);
        final Button go = (Button) dialog.findViewById(R.id.yes);
        select_date.setVisibility(View.VISIBLE);
        go.setVisibility(View.VISIBLE);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear,mMonth,mDay);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("E, dd MMM, yyyy");

        String strDate = format.format(calendar.getTime());
        String strDate2 = format2.format(calendar.getTime());

        SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("strDate", strDate);
        editor.putString("strDate2", strDate2);
        editor.commit();

        select_date.setText(strDate2);
        current_date=strDate;


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


            }
        });

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog dpd = new DatePickerDialog(DietLog_Details.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                YEAR = year;
                                MONTH = monthOfYear;
                                DAY = dayOfMonth;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(YEAR, MONTH, DAY);

                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat format2 = new SimpleDateFormat("E, dd MMM, yyyy");

                                String strDate = format.format(calendar.getTime());
                                String strDate2 = format2.format(calendar.getTime());

                                SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("strDate", strDate);
                                editor.putString("strDate2", strDate2);
                                editor.commit();
                                current_date=strDate;
                                select_date.setText(strDate2 + "");
                            }
                        }, mYear, mMonth, mDay);

                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();


            }
        });



        dialog.show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        helper = new DBHelper(getApplicationContext(), DBHelper.DataBaseName, null,
                DBHelper.Version);
        SharedPreferences sp2 = getSharedPreferences("Diet_log_key", MODE_PRIVATE);
        Diet_log_key = sp2.getString("Diet_log_key", "");

        text.setText(Diet_log_key);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_log__details);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        search_food = (AutoCompleteTextView) findViewById(R.id.search_food);
        food_image = (ImageView) findViewById(R.id.food_image);
        checkss = (Button) findViewById(R.id.checkss);
        lists = (NonScrollListView) findViewById(R.id.lists);
        dietLogScroll=(ScrollView)findViewById(R.id.DietLogScroll) ;

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        current_date = df.format(date);


        try {
            SharedPreferences sp1 = new AllSharedPrefrences(this).UserDataget();

            user_id = sp1.getString("Userid", "");
        } catch (Exception e) {

        }
        SharedPreferences sp = getSharedPreferences("Diet_log_key", MODE_PRIVATE);

        Diet_log_key = sp.getString("Diet_log_key", "");

        try {
            if (Diet_log_key.equals("Lunch")) {
                food_image.setImageResource(R.drawable.lunch);
            } else if (Diet_log_key.equals("Alcohol")) {
                food_image.setImageResource(R.drawable.alchol);
            } else if (Diet_log_key.equals("Drink")) {
                food_image.setImageResource(R.drawable.drink);
            } else if (Diet_log_key.equals("Snack")) {
                food_image.setImageResource(R.drawable.snack);
            } else if (Diet_log_key.equals("Dinner")) {
                food_image.setImageResource(R.drawable.dinner);
            } else if (Diet_log_key.equals("Breakfast")) {
                food_image.setImageResource(R.drawable.breakfast);
            }

        } catch (Exception e) {

        }

        try {
            mDbHelper = new TestAdapter(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mDbHelper.createDatabase();
        mDbHelper.open();


        list2 = mDbHelper.getlogFood();
        Log.e("size", list2.size() + "");

        mDbHelper.close();


        search_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(DietLog_Details.this, ",;nl,;jmd", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(DietLog_Details.this, Search_Activity.class);
                in.putExtra("code", code);
                //  in.putExtra("data", list2);
                startActivityForResult(in, code);
            }
        });

        adapter2 = new LAdapter3(Diet_log_key,getApplicationContext());
        lists.setAdapter(adapter2);

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        checkss.setOnClickListener(this);

        check_past_log();
        //getAll();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            try {

                if (requestCode == 104) {
                    DietLog_Models name = (DietLog_Models) data.getSerializableExtra("name");
                    search_food.setText(name.getFoodname());
                    list_get.add(name);
                    boolean aa = true;
                    Log.e("Addddddddddd", aa + "");
                    if (aa == true) {
                       updateListView();
                    } else {
                        Toast.makeText(DietLog_Details.this, "You Already Selected", Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (Exception e) {
            }

        } catch (Exception e) {
        }
    }

   void  updateListView()
    {
        adapter2.notifyDataSetChanged();
        search_food.setText("");
        lists.post(new Runnable() {
            @Override
            public void run() {
                dietLogScroll.fullScroll(ScrollView.FOCUS_DOWN);

            }
        });
    }
    @Override
    public void onBackPressed() {
        if (list_get.size() == 0) {
            controller.trancate();
            new MyIntent(DietLog_Details.this, DietLog_activity.class, R.anim.enter2, R.anim.exit2);
        } else {
            AlertDialog();
        }

//
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           /* controller.trancate();
            Intent in = new Intent(getApplicationContext(), DietLog_activity.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();*/
            if (list_get.size() == 0) {
                controller.trancate();
                Intent in = new Intent(getApplicationContext(), DietLog_activity.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            } else {
                AlertDialog();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    float _calories = 0.0f;
    float _carb = 0.0f;
    float _prot = 0.0f;
    float _fat = 0.0f;
    float _SFA = 0.0f;
    float _chole = 0.0f;
    float _sugar = 0.0f;
    float _sodium = 0.0f;
    float _fibre = 0.0f;

    float calories_mul;
    float Carbs_mul;
    float Sugar_mul;
    float Protein_mul;
    float Fat_mul;
    float fibre_mul;
    float sat_fat_mul;
    float chole_mul;
    float sodium_mul;

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            controller.trancate();
            new MyIntent(DietLog_Details.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            controller.trancate();
            new MyIntent(DietLog_Details.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            controller.trancate();
            new MyIntent(DietLog_Details.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            controller.trancate();
            new MyIntent(DietLog_Details.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            controller.trancate();
            new MyIntent(DietLog_Details.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == checkss) {
            // new MyIntent(DietLog_Details.this, MoreActivity.class, R.anim.enter, R.anim.exit);
            if (list_get.size() == 0) {
                Toast.makeText(this, "Please Add Data", Toast.LENGTH_SHORT).show();
            } else {

                try {
                    boolean b  = false;

                    for (int i = 0; i < list_get.size(); i++) {
                        Totalinsertedelement = Totalinsertedelement + 1;
                        DietLog_Models am = list_get.get(i);
                        controller.addProductLog(am);
                        float energe = Float.parseFloat(am.getEnergy());
                        String food_name = am.getFoodname();
                        String getnumber = am.getNumbers();
                        float Protein = Float.parseFloat(am.getProtein());
                        float Carbs = Float.parseFloat(am.getCarbs());
                        float Fat = Float.parseFloat(am.getFat());
                        float fibre = Float.parseFloat(am.getFibre());
                        float Sugar = Float.parseFloat(am.getSugar());
                        float sat_fat = Float.parseFloat(am.getSat_fat());
                        float souium = Float.parseFloat(am.getSodium());
                        float Cholestrol = Float.parseFloat(am.getCholestrol());

                        if (food_data.equals("")) {
                            food_data = food_name;
                        } else {
//                            food_data = food_data + ", " + food_name;
                            food_data = food_name;
                        }

                        Log.e("food_data", food_data + "");
                        Log.e("resulttttt", energe + ".." + Carbs + ".." + Sugar + ".." + Protein + ".." + Fat + ".." + fibre + ".." + sat_fat + ".." + getnumber);

                        float get_num = Float.valueOf(getnumber);
                        total_count = total_count + get_num;
                        float ene = energe * get_num;
                        float car = Carbs * get_num;
                        float sug = Sugar * get_num;
                        float pro = Protein * get_num;
                        float fatt = Fat * get_num;
                        float fibb = fibre * get_num;
                        float satfat = sat_fat * get_num;
                        float souiumss = souium * get_num;
                        float Cholestrolss = Cholestrol * get_num;

                        //   controller.addFoodData_Preview(food_name, ene+"", pro+"", fatt+"", car+"", sug+"", fibb+"", total_sat_fat,total_souium,total_cholestrol, current_date, user_id, Diet_log_key);


                        totalEnergy =  ene;
                        totalCarbs =  car;
                        totalSugar =  sug;
                        totalProtein = pro;
                        totalFat = fatt;
                        totalfibre = fibb;
                        totalsat_fat = satfat;
                        totalsouium = souiumss;
                        totalcholestrol =  Cholestrolss;



                    if (Diet_log_key.equals("Breakfast")) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Breakfast", "Total", "fibre", getApplicationContext());
                    } else if (Diet_log_key.equals("Lunch")) {
                        _calories = _calories = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Lunch", "Total", "fibre", getApplicationContext());

                    } else if (Diet_log_key.equals("Dinner")) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Dinner", "Total", "fibre", getApplicationContext());
                    } else if (Diet_log_key.equals("Snacks")) {
                        _calories = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "Calories", getApplicationContext());
                        _carb = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "carb", getApplicationContext());
                        _prot = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "prot", getApplicationContext());
                        _fat = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fat", getApplicationContext());
                        _SFA = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "SFA", getApplicationContext());
                        _chole = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "chole", getApplicationContext());
                        _sugar = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sugar", getApplicationContext());
                        _sodium = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "sodium", getApplicationContext());
                        _fibre = CalorieCalculator.getMealNutrientCalorieValue("Snacks", "Total", "fibre", getApplicationContext());
                    }

                    Carbs_mul = totalCarbs / _carb;
                    Sugar_mul = totalSugar / _sugar;
                    Protein_mul = totalProtein / _prot;
                    Fat_mul = totalFat / _fat;
                    fibre_mul = totalsat_fat / _fibre;
                    sat_fat_mul = totalsat_fat / _SFA;
                    chole_mul = totalcholestrol / _chole;
                    sodium_mul = totalsouium / _sodium;


                    String calories = String.valueOf(totalEnergy / _calories);
                    total_Energy = String.valueOf(totalEnergy);
                    total_Carbs = String.valueOf(Carbs_mul);
                    total_Sugar = String.valueOf(Sugar_mul);
                    total_Protein = String.valueOf(Protein_mul);
                    total_Fat = String.valueOf(Fat_mul);
                    total_fibre = String.valueOf(fibre_mul);
                    total_sat_fat = String.valueOf(sat_fat_mul);
                    total_souium = String.valueOf(sodium_mul);
                    total_cholestrol = String.valueOf(chole_mul);


                     b = controller.addFoodData(calories + "", food_data, total_Energy, total_Protein, total_Fat, total_Carbs, total_Sugar, total_fibre, total_sat_fat, total_souium, total_cholestrol, current_date, user_id, Diet_log_key);
                }
                    if (b == true) {

                        SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("select", "5");
                        editor.putInt("insertedelement",Totalinsertedelement);
                        editor.commit();

                        new MyIntent(DietLog_Details.this, See_Past_Diet_Log.class, R.anim.enter, R.anim.exit);

//                         new MyIntent(DietLog_Details.this, DietLog_Result.class, R.anim.enter, R.anim.exit);
                    } else {
                        Toast.makeText(this, "Somthing wrong", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e("Exceptionnn", e + "");
                }
            }
        }
    }

    class LAdapter3 extends BaseAdapter {

        public LAdapter3(String mealType, Context ctx) {
            this.mealType = mealType;
            this.ctx = ctx;
        }

        String mealType;
        Context ctx;
        boolean m = true;


        @Override
        public int getCount() {
            return list_get.size();
        }

        @Override
        public Object getItem(int position) {
            return list_get.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View vv = getLayoutInflater().inflate(R.layout.food_category_tracker_diet, null);
            TextView name = (TextView) vv.findViewById(R.id.name);
            TextView quentity = (TextView) vv.findViewById(R.id.qnt1);
            TextView results = (TextView) vv.findViewById(R.id.Result);
            final TextView count = (TextView) vv.findViewById(R.id.count);
            Spinner spv = (Spinner) vv.findViewById(R.id.listUnits);
            final EditText sizings = (EditText) vv.findViewById(R.id.servingSizings);
            ImageView cross = (ImageView) vv.findViewById(R.id.cross);
            Button buttock = (Button) vv.findViewById(R.id.servingComparisons);
            buttock.setVisibility(View.GONE);
            results.setVisibility(View.GONE);
            m = true;


            final DietLog_Models am = list_get.get(position);
            name.setText(am.getFoodname());
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(DietLog_Details.this, android.R.layout.simple_spinner_dropdown_item, am.getHm());

            if(am.getHm().size() >0) {
                spv.setAdapter(adapter);
                spv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {
                        if (pos == 0)
                            return;


                        ArrayList<String> list1 = am.getHm();
                        String selected = parentView.getItemAtPosition(pos).toString();
                        DietLog_Models temp = helper.getDietLogServing(selected, am);
                        list1.remove(pos);
                        temp.getHm().addAll(list1);
                        if (temp == null)
                            return;

                        list_get.set(position, temp);

                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }
                });
            }
            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_get.remove(position);
                    notifyDataSetChanged();
                }
            });


          /*  ArrayList<String> dataList = new ArrayList<String>();

            dataList.add("0.00");
            dataList.add("0.25");
            dataList.add("0.50");
            dataList.add("0.75");
            dataList.add("1.00");
            dataList.add("1.25");
            dataList.add("1.50");
            dataList.add("1.75");
            dataList.add("2.00");
            dataList.add("2.25");
            dataList.add("2.50");
            dataList.add("2.75");
            dataList.add("3.00");
            dataList.add("3.25");
            dataList.add("3.50");
            dataList.add("3.75");
            dataList.add("4.00");
            dataList.add("4.25");
            dataList.add("4.50");

            if (dataList.contains(list_get.get(position).getNumbers()) == false) {
                dataList.add(list_get.get(position).getNumbers());
            }
            ArrayAdapter<String> arrayAdapter;
            arrayAdapter = new ArrayAdapter<String>(DietLog_Details.this, android.R.layout.simple_spinner_dropdown_item, dataList);
            sizings.setAdapter(arrayAdapter);

            sizings.setSelection(getIndex(sizings, list_get.get(position).getNumbers()), false);


            sizings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {


                    ArrayList<String> list1 = am.getHm();
                    String selected = parentView.getItemAtPosition(pos).toString();
                    list_get.get(position).setNumbers(selected);


                }


                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });*/


            if(sizings.getText().toString().equals(list_get.get(position).getNumbers())==false)
            {
                sizings.setText(list_get.get(position).getNumbers());
            }
          /*  ArrayAdapter<String> arrayAdapter;
            arrayAdapter=  new ArrayAdapter<String>(Eat_out_Plan_Details.this, android.R.layout.simple_spinner_dropdown_item,dataList);
            sizings.setAdapter(arrayAdapter);*/

            sizings.setText(list_get.get(position).getNumbers());
            sizings.setSelection(sizings.getText().length());

            sizings.setOnEditorActionListener(
                    new EditText.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                    actionId == EditorInfo.IME_ACTION_DONE ||
                                    event != null &&
                                            event.getAction() == KeyEvent.ACTION_DOWN &&
                                            event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                if (event == null || !event.isShiftPressed()) {
                                    // the user is done typing.
                                    ArrayList<String> list1 = am.getHm();
                                    String selected = sizings.getText().toString();
                                    list_get.get(position).setNumbers(selected);
                                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                                    // consume.
                                }
                            }
                            return false; // pass on to other listeners.
                        }
                    });


            return vv;
        }

        //private method of your class


    }




    void AlertDialog() {

        dialog = new Dialog(DietLog_Details.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.cancerplan_dialog);
        dialog.setCancelable(true);
        Button send = (Button) dialog.findViewById(R.id.send);
        Button close = (Button) dialog.findViewById(R.id.close);
        TextView text_nmae = (TextView) dialog.findViewById(R.id.text_nmae);
        text_nmae.setText("Are you sure for exit");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.trancate();
                new MyIntent(DietLog_Details.this, DietLog_activity.class, R.anim.enter2, R.anim.exit2);
                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

}
