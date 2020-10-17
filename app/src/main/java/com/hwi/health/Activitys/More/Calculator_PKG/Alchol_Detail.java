package com.hwi.health.Activitys.More.Calculator_PKG;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.Activitys.Logs.See_Past_Alcohol_Log;
import com.hwi.health.Activitys.Plans.Excercize_plans.NonScrollListView;
import com.hwi.health.Activitys.Plans.Search_Activity;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.Alchohal_tracker_model;
import com.hwi.health.Models.Eat_out_Plan_models;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.sqlite_database.DBHelper;
import com.hwi.health.sqlite_database.ProductController;
import com.hwi.health.sqlite_database.ProductController_For_All;
import com.hwi.health.sqlite_database.TestAdapter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Alchol_Detail extends AppCompatActivity implements View.OnClickListener, BaseUrl {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L, lin_search;
    TextView heading;
    Button checkss;
    AutoCompleteTextView search_alchohol;
    NonScrollListView listAlchoholCategorySelected;
    ListView lists;
    ArrayList<Alchohal_tracker_model> alist = new ArrayList<>();
    ArrayList<Alchohal_tracker_model> alist3 = new ArrayList<>();
    List<Alchohal_tracker_model> alist2 = new ArrayList<>();
    Dialog dialog,dialogAddItem,dialogAddItemDietLog;

    ListView list;
    int mSelectedItem = 100000000;
    int number = 1;
    String get_Std_size_drinks, get_Carbs, get_Sugar, get_Sodium, get_Calories, get_number, user_id;
    float total_Calories, total_Std_size_drinks, total_Carbs, total_Sugar, total_Sodium;
    public  ArrayList<Eat_out_Plan_models> list_alchohol_selected = new ArrayList<>();
    float total_count = 0;
    TestAdapter mDbHelper = null;
    String current_date, get_date, json_alchol, week_datee;

    ProductController controller = new ProductController(Alchol_Detail.this);
    ProductController_For_All controller2 = new ProductController_For_All(Alchol_Detail.this);
    int num = 22;
    String nummm = "22";

    SearchView search_view;
    String food_data = "food_data";
    LinearLayout see_past_lay;
    TextView see_past;
    public AlchoholUnits fu = null;
    DBHelper helper = null;
    //
    private int mYear, mMonth, mDay, mHour, mMinute;
    int YEAR, MONTH, DAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Alcohol Tracker");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alchol__detail);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        helper = new DBHelper(getApplicationContext(), DBHelper.DataBaseName, null,
                DBHelper.Version);


        see_past_lay = (LinearLayout) findViewById(R.id.see_past_lay);
        see_past = (TextView) findViewById(R.id.see_past);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        checkss = (Button) findViewById(R.id.checkss);

        lists = (ListView) findViewById(R.id.lists);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        search_alchohol = (AutoCompleteTextView) findViewById(R.id.search_alchohol);
        listAlchoholCategorySelected = (NonScrollListView) findViewById(R.id.listsAlchoholCategory);

        search_alchohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Alchol_Detail.this, Search_Activity.class);
                in.putExtra("code", 106);
                startActivityForResult(in, 106);

            }
        });


        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        user_id = sp.getString("Userid", "");

        try {
            List<Alchohal_tracker_model> diet_list = new ProductController_For_All(Alchol_Detail.this).getMyAlcho(user_id, "5", "", "");
            Log.e("diet_list", diet_list.get(0).getCarbs());
            if (!diet_list.isEmpty()) {

                see_past_lay.setVisibility(View.VISIBLE);
                see_past.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check_past_log();
                    }
                });
            }
        } catch (Exception e) {
            Log.e("diet_list error", e + "");
        }

        ///////////Current date/////////////
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        current_date = df.format(date);

/////////////////// one week Date///////////////
        try {
            Date myDate = df.parse(df.format(date));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(myDate);
            calendar.add(Calendar.DAY_OF_YEAR, +7);
            Date newDate = calendar.getTime();
            DateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
            week_datee = dff.format(newDate);
            Log.e("dateeeee", week_datee + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SharedPreferences sp2 = getSharedPreferences("week_date", MODE_PRIVATE);
        get_date = sp2.getString("date", "2017-12-31");
        json_alchol = sp2.getString("json_alchol", "{}");
//        Log.e("json_alchol ", get_date + ".." + json_alchol);
        Log.e("get_date ", get_date + ".." + current_date);

     //   dataJson(get_date, current_date);

        try {
            mDbHelper = new TestAdapter(this);
        } catch (IOException e) {
            Log.e("errororo", e + "");
            e.printStackTrace();
        }
        mDbHelper.createDatabase();
        mDbHelper.open();

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        checkss.setOnClickListener(this);




    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            try {


                if (requestCode == 106) {


                    Eat_out_Plan_models name = (Eat_out_Plan_models) data.getSerializableExtra("name");
                    this.list_alchohol_selected.add(name);
                    trackAlchoholCalories();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
    }


    public void updateDietLogs()
    {

        String food_data="";
        float total_count=0;
        float totalEnergy=0;
        float totalCarbs=0;
        float totalSugar=0;
        float totalProtein=0;
        float totalfibre=0;
        float totalFat=0;
        float totalsat_fat=0;
        float totalsouium=0;
        float totalcholestrol=0;

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String current_date = df.format(date);

        for (int i = 0; i < list_alchohol_selected.size(); i++) {
            Eat_out_Plan_models am = list_alchohol_selected.get(i);

            float energe=0;
            if(am.getEnergy().isEmpty()==false)
                energe = Float.parseFloat(am.getEnergy());

            String food_name="";
            if(am.getEnergy().isEmpty()==false) {
                food_name = am.getFoodname();
            }
            String getnumber="";
            if(am.getNumbers().isEmpty()==false)
                getnumber = am.getNumbers();

            float Protein=0;
            if(am.getProtein().isEmpty()==false)
                Protein = Float.parseFloat(am.getProtein());

            float Carbs=0;
            if(am.getCarbs().isEmpty()==false)
                Carbs = Float.parseFloat(am.getCarbs());

            float Fat=0;
            if(am.getFat().isEmpty()==false)
                Fat = Float.parseFloat(am.getFat());

            float fibre=0;
            if(am.getFibre().isEmpty()==false)
                fibre = Float.parseFloat(am.getFibre());

            float Sugar=0;
            if(am.getSugar().isEmpty()==false)
                Sugar = Float.parseFloat(am.getSugar());

            float sat_fat=0;
            if(am.getSat_fat().isEmpty()==false)
                sat_fat = Float.parseFloat(am.getSat_fat());

            float souium=0;
            if(am.getSodium_bev().isEmpty()==false)
                souium = Float.parseFloat(am.getSodium_bev());

            float Cholestrol=0;
            if(am.getCholestrol_bev().isEmpty()==false)
                Cholestrol = Float.parseFloat(am.getCholestrol_bev());

            if (food_data.equals("")) {
                food_data = food_name;
            } else {
                food_data = food_data + ", " + food_name;
            }

            Log.e("food_data", food_data + "");
            Log.e("resulttttt", energe + ".." + Carbs + ".." + Sugar + ".." + Protein + ".." + Fat + ".." + fibre + ".." + sat_fat + ".." + getnumber);


            if(getnumber.isEmpty()==false) {
                float get_num = Float.parseFloat(getnumber);
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
                totalEnergy = totalEnergy + ene;
                totalCarbs = totalCarbs + car;
                totalSugar = totalSugar + sug;
                totalProtein = totalProtein + pro;
                totalFat = totalFat + fatt;
                totalfibre = totalfibre + fibb;
                totalsat_fat = totalsat_fat + satfat;
                totalsouium = totalsouium + souiumss;
                totalcholestrol = totalcholestrol + Cholestrolss;
            }





        }
        float _calories = 1;
        float _carb = 1;
        float _prot = 1;
        float _fat = 1;
        float _SFA = 1;
        float _chole= 1;
        float _sugar = 1;
        float _sodium = 1;
        float _fibre = 1;
        String total_Energy, total_Carbs, total_Sugar, total_Protein, total_Fat, total_fibre, total_sat_fat, get_foodname, serNumber,total_cholestrol;

        total_Energy = String.valueOf(totalEnergy/_calories);
        total_Carbs = String.valueOf(totalCarbs/_carb);

        total_Sugar = String.valueOf(totalSugar/_sugar);
        total_Protein = String.valueOf(totalProtein/_prot);
        total_Fat = String.valueOf(totalFat/_fat);
        total_fibre = String.valueOf(totalfibre/_fibre);
        total_sat_fat = String.valueOf(totalsat_fat);
        String total_souium = String.valueOf(totalsouium/_sodium);
        total_cholestrol = String.valueOf(totalcholestrol/_chole);
        String get_food="Alcohol";
        ProductController_For_All controller = new ProductController_For_All(Alchol_Detail.this);
        boolean b = controller.addFoodData(String.valueOf(total_Energy) + "", food_data, String.valueOf(totalEnergy), total_Protein, total_Fat, total_Carbs, total_Sugar, total_fibre, total_sat_fat, total_souium, total_cholestrol, current_date, user_id, get_food);



    }

    void check_past_log() {
        final Dialog dialog = new Dialog(Alchol_Detail.this);
        dialog.setContentView(R.layout.exercise_log_popup);
        TextView xx = (TextView) dialog.findViewById(R.id.text_popup);
        final EditText select_date = (EditText) dialog.findViewById(R.id.select_date);
        final EditText select_To_date = (EditText) dialog.findViewById(R.id.select_To_date);
        final Button go = (Button) dialog.findViewById(R.id.go);
        final LinearLayout botm = (LinearLayout) dialog.findViewById(R.id.botm);

        select_date.setVisibility(View.VISIBLE);
        select_To_date.setVisibility(View.VISIBLE);
        go.setVisibility(View.VISIBLE);
        botm.setVisibility(View.GONE);



        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getDate = select_date.getText().toString();
                String getDateend = select_To_date.getText().toString();
                if (getDate.equals("") || getDateend.equals("")) {
                    Toast.makeText(Alchol_Detail.this, "Please select date", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.dismiss();

                    SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("select", "4");
                    editor.commit();
                    new MyIntent(Alchol_Detail.this, See_Past_Alcohol_Log.class, R.anim.enter, R.anim.exit);

                    //  new MyIntent(Alcohol_Tracker.this, See_Past_Alcohol_Log.class, R.anim.enter, R.anim.exit);
                }
            }
        });

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog dpd = new DatePickerDialog(Alchol_Detail.this,
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

                                select_date.setText(strDate2 + "");
                            }
                        }, mYear, mMonth, mDay);

                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();
            }
        });

        select_To_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog dpd = new DatePickerDialog(Alchol_Detail.this,
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
                                editor.putString("endDate", strDate);
                                editor.putString("endDate2", strDate2);
                                editor.commit();

                                select_To_date.setText(strDate2 + "");
                            }
                        }, mYear, mMonth, mDay);

                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();


            }
        });


        dialog.show();

    }


    @Override
    public void onBackPressed() {

        if (alist2.size() == 0) {
            controller.trancate();
            new MyIntent(Alchol_Detail.this, Alcohol_Tracker.class, R.anim.enter2, R.anim.exit2);
        } else {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (alist2.size() == 0) {
                controller.trancate();
                Intent in = new Intent(getApplicationContext(), Alcohol_Tracker.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            } else {
             //   AlertDialog2();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    void AlertDialogCheckResults() {
        dialogAddItemDietLog = new Dialog(Alchol_Detail.this, R.style.CustomDialog);
        dialogAddItemDietLog.setContentView(R.layout.cancerplan_dialog);
        dialogAddItemDietLog.setCancelable(true);
        Button send = (Button) dialogAddItemDietLog.findViewById(R.id.send);
        Button close = (Button) dialogAddItemDietLog.findViewById(R.id.close);
        TextView text_nmae = (TextView) dialogAddItemDietLog.findViewById(R.id.text_nmae);
        text_nmae.setText("Add food items to diet log?");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDietLogs();
                dialogAddItemDietLog.dismiss();
                try {
                    for (int i = 0; i < list_alchohol_selected.size(); i++) {
                        Eat_out_Plan_models am = list_alchohol_selected.get(i);
                        Log.e("am.getStd_size_drinks()", am.getStd_size());
                        Log.e("am.getCarbs()", am.getCarbs());
                        Log.e("am.getSugar()", am.getSugar());
                        Log.e("am.getSodium()", am.getSodium_bev());
                        Log.e("am.getCalories()", am.getEnergy());

                        float Std_size_drinks = Float.parseFloat(am.getStd_size());
                        float Carbs = Float.parseFloat(am.getCarbs());
                        float Sugar = Float.parseFloat(am.getSugar());
                        float Sodium = Float.parseFloat(am.getSodium_bev());
                        float Calories = Float.parseFloat(am.getEnergy());

                        String getnumber = am.getNumbers();
                        Log.e("am.getNumbers()", am.getNumbers());
                        String food_name = am.getRaw_cooked() + "(" + am.getFoodname()+ ")";

                        Log.e("food_name", food_name);


                        if (food_data.equals("food_data")) {
                            food_data = food_name;
                        } else {
                            food_data = food_data + ", " + food_name;
                        }

                        Log.e("food_data", food_data + "");

                        float get_num = Float.parseFloat(getnumber);
                        total_count = total_count + get_num;
                        float std = Std_size_drinks * get_num;
                        float car = Carbs * get_num;
                        float sug = Sugar * get_num;
                        float sod = Sodium * get_num;
                        float cal = Calories * get_num;
                        total_Calories = total_Calories + cal;
                        total_Std_size_drinks = total_Std_size_drinks + std;
                        total_Carbs = total_Carbs + car;
                        total_Sugar = total_Sugar + sug;
                        total_Sodium = total_Sodium + sod;


                        //  Log.e("get_data..", Std_size_drinks + ".." + Carbs + ".." + Sugar + ".." + Sodium + ".." + Calories + ".." + getnumber);

                    }
                    get_Std_size_drinks = String.valueOf(total_Std_size_drinks);
                    get_Carbs = String.valueOf(total_Carbs);
                    get_Sugar = String.valueOf(total_Sugar);
                    get_Sodium = String.valueOf(total_Sodium);
                    get_Calories = String.valueOf(total_Calories);
                    get_number = String.valueOf(total_count);
                    new AllSharedPrefrences(Alchol_Detail.this).Alchol_result(get_Calories, get_Std_size_drinks, get_Carbs, get_Sugar, get_Sodium, get_number, food_data, current_date);

                    boolean b = controller2.SaveAlchol(food_data, current_date, total_Calories + "", total_Carbs + "", total_Sugar + "", total_Sodium + "", total_Std_size_drinks + "", total_count + "", user_id);
                    updateDietLogs();
                    if (b == true) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("select", "5");
                        editor.commit();

                        new MyIntent(Alchol_Detail.this, See_Past_Alcohol_Log.class, R.anim.enter, R.anim.exit);
                    } else {
                       // Toast.makeText(this, "Somthing Worng", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e("Eeeee", e + "");
                }


            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddItemDietLog.dismiss();
                try {
                    for (int i = 0; i < list_alchohol_selected.size(); i++) {
                        Eat_out_Plan_models am = list_alchohol_selected.get(i);
                        Log.e("am.getStd_size_drinks()", am.getStd_size());
                        Log.e("am.getCarbs()", am.getCarbs());
                        Log.e("am.getSugar()", am.getSugar());
                        Log.e("am.getSodium()", am.getSodium_bev());
                        Log.e("am.getCalories()", am.getEnergy());

                        float Std_size_drinks = Float.parseFloat(am.getStd_size());
                        float Carbs = Float.parseFloat(am.getCarbs());
                        float Sugar = Float.parseFloat(am.getSugar());
                        float Sodium = Float.parseFloat(am.getSodium_bev());
                        float Calories = Float.parseFloat(am.getEnergy());

                        String getnumber = am.getNumbers();
                        Log.e("am.getNumbers()", am.getNumbers());
                        String food_name = am.getRaw_cooked() + "(" + am.getFoodname()+ ")";

                        Log.e("food_name", food_name);


                        if (food_data.equals("food_data")) {
                            food_data = food_name;
                        } else {
                            food_data = food_data + ", " + food_name;
                        }

                        Log.e("food_data", food_data + "");

                        float get_num = Float.parseFloat(getnumber);
                        total_count = total_count + get_num;
                        float std = Std_size_drinks * get_num;
                        float car = Carbs * get_num;
                        float sug = Sugar * get_num;
                        float sod = Sodium * get_num;
                        float cal = Calories * get_num;
                        total_Calories = total_Calories + cal;
                        total_Std_size_drinks = total_Std_size_drinks + std;
                        total_Carbs = total_Carbs + car;
                        total_Sugar = total_Sugar + sug;
                        total_Sodium = total_Sodium + sod;


                        //  Log.e("get_data..", Std_size_drinks + ".." + Carbs + ".." + Sugar + ".." + Sodium + ".." + Calories + ".." + getnumber);

                    }
                    get_Std_size_drinks = String.valueOf(total_Std_size_drinks);
                    get_Carbs = String.valueOf(total_Carbs);
                    get_Sugar = String.valueOf(total_Sugar);
                    get_Sodium = String.valueOf(total_Sodium);
                    get_Calories = String.valueOf(total_Calories);
                    get_number = String.valueOf(total_count);
                    new AllSharedPrefrences(Alchol_Detail.this).Alchol_result(get_Calories, get_Std_size_drinks, get_Carbs, get_Sugar, get_Sodium, get_number, food_data, current_date);

                    boolean b = controller2.SaveAlchol(food_data, current_date, total_Calories + "", total_Carbs + "", total_Sugar + "", total_Sodium + "", total_Std_size_drinks + "", total_count + "", user_id);
                    updateDietLogs();
                    if (b == true) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Date", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("select", "5");
                        editor.commit();

                        new MyIntent(Alchol_Detail.this, See_Past_Alcohol_Log.class, R.anim.enter, R.anim.exit);
                    } else {
                        //Toast.makeText(this, "Something Worng", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e("Eeeee", e + "");
                }


            }
        });

        dialogAddItemDietLog.show();
    }


    @Override
    public void onClick(View view) {
        if (view == home_L) {
            controller.trancate();
            new MyIntent(Alchol_Detail.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            controller.trancate();
            new MyIntent(Alchol_Detail.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            controller.trancate();
            new MyIntent(Alchol_Detail.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            controller.trancate();
            new MyIntent(Alchol_Detail.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            controller.trancate();
            new MyIntent(Alchol_Detail.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        ;

        if (view == checkss) {
            if (list_alchohol_selected.size() == 0) {
                Toast.makeText(this, "Please Add Data", Toast.LENGTH_SHORT).show();
            } else {

                AlertDialogCheckResults();

            }

        }

    }









    public float totalCaloriesConsumed = 0;
    public HashMap<Integer, String> hmServings = new HashMap<Integer, String>();
    public HashMap<Integer, String> servingString = new HashMap<Integer, String>();

    void trackAlchoholCalories() {

        totalCaloriesConsumed = 0;
        float userServings = 0;

        servingString.clear();

        for (int i = 0; i < list_alchohol_selected.size(); i++) {

            try {
                String val = list_alchohol_selected.get(i).getStd_size();
                String list_d = list_alchohol_selected.get(i).getNumbers();
                userServings += Float.valueOf(val) * Float.valueOf(list_d);
            }catch(Exception e){
                e.printStackTrace();
            }
            if (list_alchohol_selected.size() - 1 != i) {

               if(servingString.get(i)!=null)
                   servingString.remove(i);

            } else {

                String toShow = String.valueOf(userServings) + " Std Size Drinks";
                servingString.put(i,toShow);
            }
        }

        if (fu == null || list_alchohol_selected.size() == 1) {
            fu = new AlchoholUnits(getApplicationContext());
            listAlchoholCategorySelected.setAdapter(fu);


        } else {
            listAlchoholCategorySelected.setAdapter(fu);
            fu.notifyDataSetChanged();


        }
    }

    class AlchoholUnits1 extends BaseAdapter {

        public AlchoholUnits1(Context ctx) {

            this.ctx = ctx;
        }

        String mealType;
        Context ctx;

        @Override
        public int getCount() {
            return list_alchohol_selected.size();
        }

        @Override
        public Object getItem(int position) {
            return list_alchohol_selected.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (position > 3)
                return null;


            View vv = getLayoutInflater().inflate(R.layout.food_category_tracker, null);
            TextView name = (TextView) vv.findViewById(R.id.name);
            TextView quentity = (TextView) vv.findViewById(R.id.qnt1);
            final TextView count = (TextView) vv.findViewById(R.id.count);
            Spinner spv = (Spinner) vv.findViewById(R.id.listUnits);

            ImageView cross = (ImageView) vv.findViewById(R.id.cross);
            LinearLayout minus_count = (LinearLayout) vv.findViewById(R.id.minus_count);
            LinearLayout add_count = (LinearLayout) vv.findViewById(R.id.add_count);
            final Eat_out_Plan_models am = list_alchohol_selected.get(position);
            name.setText(am.getFoodname());
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(Alchol_Detail.this, android.R.layout.simple_spinner_dropdown_item, am.getHm());
            //adap  = new Base();
            spv.setAdapter(adapter);
            count.setText(am.getNumbers());

            spv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {
                    // your code here

                    if (pos == 0)
                        return;

                    ArrayList<String> list1 = am.getHm();
                    String selected = parentView.getItemAtPosition(pos).toString();
                    Eat_out_Plan_models temp = helper.getAlcoholicServing(selected, am);
                    list1.remove(pos);
                    temp.getHm().addAll(list1);
                    if (temp == null)
                        return;

                    list_alchohol_selected.set(position, temp);
                    trackAlchoholCalories();
                }


                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    if (position == list_alchohol_selected.size() - 1) {
                        list_alchohol_selected.remove(position);
                        if (servingString.get(position) != null) {


                            servingString.remove(position);
                        }

                        trackAlchoholCalories();
//                    } else {
//                        Toast.makeText(ctx, "You can only delete the last item", Toast.LENGTH_SHORT).show();
//                    }

                }
            });

            add_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        if (position != list_alchohol_selected.size() - 1) {


                            Toast.makeText(ctx, "Serving sizes can only be changed for the last item", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Eat_out_Plan_models am = list_alchohol_selected.get(position);


                        String numbers_add = am.getNumbers();
                        String energy = am.getEnergy();

                        int number = Integer.parseInt(numbers_add);

                        String num = Integer.toString(number + 1);
                        count.setText(num);
                        list_alchohol_selected.get(position).setNumbers(num);


                        // alist2.set(position, new Alchohal_tracker_model(first_type_add, name_add, quantity_add, Calories_add, Std_size_drinks_add, Carbs_add, Sugar_add, Sodium_add, num));
                        // controller.updateProduct2(new DietLog_Models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, num, cholestrol, sodium));
                        // getAll();
                        // Log.e("nummmm", alist2.get(position).getNumbers());

                        trackAlchoholCalories();
                    } catch (Exception e) {
                        Log.e("Exception", e + "");
                    }

                }
            });
            minus_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        if (position != list_alchohol_selected.size() - 1) {
                            Toast.makeText(ctx, "Serving sizes can only be changed for the last item", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Eat_out_Plan_models am = list_alchohol_selected.get(position);

                        String foodname = am.getFoodname();
                        String Indian_name = am.getIndian_name();
                        String Major_food_category = am.getMajor_food_category();
                        String Raw_cooked = am.getRaw_cooked();
                        String unit_notes = am.getUnit_notes();
                        String Weight_in_ms = am.getWeight_in_ms();
                        String energy = am.getEnergy();
                        String protein = am.getProtein();
                        String fat = am.getFat();
                        String Carbs = am.getCarbs();
                        String Sugar = am.getSugar();
                        String fibre = am.getFibre();
                        String sat_fat = am.getSat_fat();
                        String numbers_minus = am.getNumbers();
                        int number = Integer.parseInt(numbers_minus);

                        if (number > 1) {
                            String num = Integer.toString(number - 1);
                            count.setText(num);
                            list_alchohol_selected.get(position).setNumbers(num);
                        }


                        trackAlchoholCalories();


                    } catch (Exception e) {
                        Log.e("Exception", e + "");
                    }


                }
            });


            return vv;
        }
    }

    class AlchoholUnits extends BaseAdapter {

        public AlchoholUnits(Context ctx) {

                this.ctx = ctx;
        }

        String mealType;
        Context ctx;
        boolean m=true;


        @Override
        public int getCount() {
            return list_alchohol_selected.size();
        }

        @Override
        public Object getItem(int position) {
            return list_alchohol_selected.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(position > 3)
                return null;


            View vv = getLayoutInflater().inflate(R.layout.alcohol_detail, null);
            TextView name = (TextView) vv.findViewById(R.id.name);
            TextView quentity = (TextView) vv.findViewById(R.id.qnt1);
            TextView results = (TextView) vv.findViewById(R.id.Result);
            final TextView count = (TextView) vv.findViewById(R.id.count);
            Spinner spv=(Spinner)vv.findViewById(R.id.listUnits);
            final Spinner sizings=(Spinner) vv.findViewById(R.id.servingSizings);
            ImageView cross = (ImageView) vv.findViewById(R.id.cross);
            Button buttock=(Button)vv.findViewById(R.id.servingComparisons);
            m=true;




            final Eat_out_Plan_models am = list_alchohol_selected.get(position);
            name.setText(am.getFoodname());
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(Alchol_Detail.this, android.R.layout.simple_spinner_dropdown_item,am.getHm());

            spv.setAdapter(adapter);
            spv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {
                    if(pos==0)
                        return;

                    ArrayList<String> list1=am.getHm();
                    String selected = parentView.getItemAtPosition(pos).toString();
                    Eat_out_Plan_models temp=helper.getEatOutServing(selected,am);
                    list1.remove(pos);
                    temp.getHm().addAll(list1);
                    if(temp==null)
                        return;

                    list_alchohol_selected.set(position,temp);
                    trackAlchoholCalories();
                }



                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    if(position == list_alchohol_selected.size()-1) {
                        list_alchohol_selected.remove(position);
                        if(servingString.get(position)!=null) {


                            servingString.remove(position);
                        }

                        trackAlchoholCalories();
//                    }
//                    else
//                    {
//                        Toast.makeText(ctx, "You can only delete the last item", Toast.LENGTH_SHORT).show();
//                    }



                }
            });



                buttock.setVisibility(View.GONE);
                results.setVisibility(View.VISIBLE);
                //buttock.setText(servingString.get(position));

            results.setText(servingString.get(position));



            ArrayList<String> dataList =new ArrayList<String>();

            dataList.add("0.00");
            dataList.add("0.25");
            dataList.add("0.50");
            dataList.add("0.75");
            dataList.add("1.00");
            dataList.add("1.25");
            dataList.add("1.50");
            dataList.add("1.75");
            dataList.add("2.00");
            if(dataList.contains(list_alchohol_selected.get(position).getNumbers())==false)
            {
                dataList.add(list_alchohol_selected.get(position).getNumbers());
            }
            ArrayAdapter<String> arrayAdapter;
            arrayAdapter=  new ArrayAdapter<String>(Alchol_Detail.this, android.R.layout.simple_spinner_dropdown_item,dataList);
            sizings.setAdapter(arrayAdapter);

            sizings.setSelection(getIndex(sizings, list_alchohol_selected.get(position).getNumbers()),false);



            sizings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {



                    ArrayList<String> list1 = am.getHm();
                    String selected = parentView.getItemAtPosition(pos).toString();
                    list_alchohol_selected.get(position).setNumbers(selected);
                    trackAlchoholCalories();


                }



                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });






            return vv;
        }
    }

    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
