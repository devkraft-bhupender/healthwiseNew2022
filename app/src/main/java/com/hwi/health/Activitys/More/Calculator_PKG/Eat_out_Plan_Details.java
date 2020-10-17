package com.hwi.health.Activitys.More.Calculator_PKG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.Activitys.Plans.Excercize_plans.CalorieCalculator;
import com.hwi.health.Activitys.Plans.Excercize_plans.NonScrollListView;
import com.hwi.health.Activitys.Plans.Search_Activity;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.Models.Baisc_diet_cla;
import com.hwi.health.Models.Eat_out_Beverage_models;
import com.hwi.health.Models.Eat_out_Plan_models;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.hwi.health.R;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.sqlite_database.DBHelper;
import com.hwi.health.sqlite_database.ProductController_For_All;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Eat_out_Plan_Details extends AppCompatActivity implements View.OnClickListener, BaseUrl {
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    Button check,compare;
    RadioButton breakfast, lunch, dinner, snack;
    //Spinner S_beverage_size;
    public String get_food = "breakfast";
    String result, randnoo, get_food_item, get_beverage, get_json, beverage_size, get_food_chain, get_size_bev;
    AutoCompleteTextView Food_Item;
    ArrayList<String> spin_list = new ArrayList<>();
    ArrayList<Eat_out_Plan_models> list_food = new ArrayList<>();
    public  ArrayList<Eat_out_Plan_models> list_foodcat_selected = new ArrayList<>();
    ArrayList<Eat_out_Beverage_models> list_bevg = new ArrayList<>();
    ArrayList<Eat_out_Beverage_models> list_bevg_selected = new ArrayList<>();
    ArrayList<String> list_make = new ArrayList<>();
    ArrayList<String> list_make_categories=new ArrayList<>();
    ProductController_For_All controller = new ProductController_For_All(Eat_out_Plan_Details.this);
    ArrayAdapter<Eat_out_Plan_models> adapter2;
    ArrayAdapter<String> adapter_make_auto;
    ArrayAdapter<String> adapter;
    NonScrollListView listBeverages;
    NonScrollListView listFoodCategory;
    ScrollView eatOutScrollView;
    int insertions=0;

    public FoodCategoryUnits fu=null;
    TextView foodSubCategory;

    String ser_number, get_food_name, get_food_cat, get_foodItem, result2, user_id, randnoo2;
    float en, car, sug, pro, fatt, fib, sat;
    Dialog dialog,dialogAddItem,dialogAddItemDietLog;
    ProgressDialog pd;

    String get_diet_json;
    ArrayAdapter<Eat_out_Beverage_models> adapter_bevg;
    String total_Energy, total_Carbs, total_Sugar, total_Protein, total_Fat, total_fibre, total_sat_fat, get_foodname, serNumber;
    String carb_result, prot_result, fat_result, SFA_result, chole_result, sugar_result, sodium_result, fibre_result;
    float D_roti, D_daal_meat_fish, D_vegetables, D_Oils = 0.0f, L_Roti, L_daal_meat_fish, L_vegetables, L_milk, L_Oils = 0.0f, B_Cereals = 0.0f, B_Milk_curd, B_Fruits, B_vegetables, B_Eggs, S_Nuts, S_Milk_curd, S_Fruits;
    String get_beverage_item, get_oil_value;
    ArrayList<Baisc_diet_cla> list_cal = new ArrayList<>();

    float energe_bevg, Carbs_bevg, Sugar_bevg, Protein_bevg, Fat_bevg, fibre_bevg, sat_fat_bevg, Cholestrol_bevg, Sodium_bevg;
    float energe__get_food_item, Carbs__get_food_item, Sugar__get_food_item, Protein__get_food_item, Fat__get_food_item, fibre__get_food_item, sat_fat__get_food_item, Cholestrol__get_food_item, Sodium__get_food_item;

    float with_water_cal = 0.0f;
    float with_begrage_cal = 0.0f;
    float calories_cal = 0.0f;

    String my_food_type;

    float final_breakfast_calculation_calories;
    float final_breakfast_calculation_carbs;
    float final_breakfast_calculation_protine;
    float final_breakfast_calculation_fat;
    float final_breakfast_calculation_satfat;
    float final_breakfast_calculation_chole;
    float final_breakfast_calculation_sugar;
    float final_breakfast_calculation_sodium;
    float final_breakfast_calculation_fibre;

    private static int cate = 101;
    private static int subcate = 102;
    private static int beverage = 103;
    DBHelper helper=null;
    private String total_cholestrol;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(" Snacks");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_out__plan__details);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        check = (Button) findViewById(R.id.check);


        breakfast = (RadioButton) findViewById(R.id.breakfast);
        lunch = (RadioButton) findViewById(R.id.lunch);
        dinner = (RadioButton) findViewById(R.id.dinner);
        snack = (RadioButton) findViewById(R.id.snack);
        Food_Item = (AutoCompleteTextView) findViewById(R.id.Food_Item);
        listFoodCategory = (NonScrollListView) findViewById(R.id.listsFoodCategory);
        eatOutScrollView=(ScrollView)findViewById(R.id.eatoutScrollView);

      if(get_food.isEmpty()==true) {
          if (breakfast.isChecked()) {
              get_food = "Breakfast";
          } else if (lunch.isChecked()) {
              get_food = "Lunch";
          } else if (dinner.isChecked()) {
              get_food = "Dinner";
          } else if (snack.isChecked()) {
              get_food = "Snacks";
          }
      }
        pd=new ProgressDialog(this);
        pd.setMessage("load database");
        pd.show();

         helper = new DBHelper(getApplicationContext(), DBHelper.DataBaseName, null,
                DBHelper.Version);

        pd.cancel();
        //  S_beverage_size = (Spinner) findViewById(R.id.beverage_size);

        try {
            SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
            user_id = sp.getString("Userid", "");
            my_food_type = sp.getString("food_habits", "");

        } catch (Exception e) {

        }

        if(list_foodcat_selected.size() > 0 && get_food.isEmpty()==false)
        {
            if (get_food.equals("breakfast")) {
                get_food="Breakfast";
               breakfast.setChecked(true);
                lunch.setChecked(false);
                dinner.setChecked(false);
                snack.setChecked(false);
            } else if (get_food.equals("lunch")) {
                get_food="Lunch";
                breakfast.setChecked(false);
                lunch.setChecked(true);
                dinner.setChecked(false);
                snack.setChecked(false);

            } else if (get_food.equals("dinner")) {
                get_food="Dinner";
                breakfast.setChecked(false);
                lunch.setChecked(false);
                snack.setChecked(false);
                dinner.setChecked(true);
            } else if (get_food.equals("snacks")) {
                get_food="Snacks";
                breakfast.setChecked(false);
                lunch.setChecked(false);
                dinner.setChecked(false);

                snack.setChecked(true);
            }
            trackCalories(get_food);

        }



        if(get_oil_value!=null && get_oil_value.isEmpty()==false) {
            get_oil_value = controller.getBaiscDietPlan_oil(user_id);
            float oil = Float.parseFloat(get_oil_value) / 2;
            D_Oils = oil;
            L_Oils = oil;
        }

        try {
            SharedPreferences sp = getSharedPreferences("Get_Food_data", Context.MODE_PRIVATE);
            get_foodname = sp.getString("eat_name", "");
            serNumber = sp.getString("eat_cat", "");
            Food_Item.setText(get_foodname);
        } catch (Exception e) {
            Log.e("Error  1", e + "");
        }




        Food_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     Intent in = new Intent(Eat_out_Plan_Details.this, Search_Activity.class);
                    in.putExtra("code", subcate);
                    startActivityForResult(in, subcate);

            }
        });

        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);
        check.setOnClickListener(this);
        breakfast.setOnClickListener(this);
        lunch.setOnClickListener(this);
        dinner.setOnClickListener(this);
        snack.setOnClickListener(this);
        RandomNumber rn = new RandomNumber();
        randnoo = rn.randno();

        // getDietJson(user_id);
    }

    void makeServingButtonVisible(String message)
    {
        compare.setVisibility(View.VISIBLE);
        compare.setText(message);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            try {


                if (requestCode == 102) {

                    if (breakfast.isChecked()) {
                        get_food = "Breakfast";
                    } else if (lunch.isChecked()) {
                        get_food = "Lunch";
                    } else if (dinner.isChecked()) {
                        get_food = "Dinner";
                    } else if (snack.isChecked()) {
                        get_food = "Snacks";
                    }

                    Eat_out_Plan_models name = (Eat_out_Plan_models) data.getSerializableExtra("name");
                    Food_Item.setText("Add Food Items");

                    this.list_foodcat_selected.add(name);
                    insertions=1;
                    trackCalories(get_food);
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
    }


    @Override
    public void onBackPressed() {



            AlertDialog();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            get_food_item = Food_Item.getText().toString();
            if (get_food_item.equals("")) {
                SharedPreferences sp1 = getSharedPreferences("Get_Food_data", Context.MODE_PRIVATE);
                sp1.edit().clear().commit();
                Intent in = new Intent(getApplicationContext(), Eat_Out_Snacker.class);
                startActivity(in);
                overridePendingTransition(R.anim.enter2, R.anim.exit2);
                finish();
            } else {
                AlertDialog();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    float one;


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

        for (int i = 0; i < list_foodcat_selected.size(); i++) {
            Eat_out_Plan_models am = list_foodcat_selected.get(i);

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
        float _calories = 0.0f;
        float _carb = 0.0f;
        float _prot = 0.0f;
        float _fat = 0.0f;
        float _SFA = 0.0f;
        float _chole= 0.0f;
        float _sugar = 0.0f;
        float _sodium = 0.0f;
        float _fibre = 0.0f;

        if (breakfast.isChecked()) {
            get_food="Breakfast";
            _calories = CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","Calories",getApplicationContext());
            _carb =     CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","carb",getApplicationContext());
            _prot =     CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","prot",getApplicationContext());
            _fat =      CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","fat",getApplicationContext());
            _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","SFA",getApplicationContext());
            _chole =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","chole",getApplicationContext());
            _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","sugar",getApplicationContext());
            _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","sodium",getApplicationContext());
            _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","fibre",getApplicationContext());
        } else if (lunch.isChecked()) {
            get_food="Lunch";
            _calories = _calories = CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","Calories",getApplicationContext());
            _carb =     CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","carb",getApplicationContext());
            _prot =     CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","prot",getApplicationContext());
            _fat =      CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","fat",getApplicationContext());
            _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","SFA",getApplicationContext());
            _chole =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","chole",getApplicationContext());
            _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","sugar",getApplicationContext());
            _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","sodium",getApplicationContext());
            _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","fibre",getApplicationContext());

        } else if (dinner.isChecked()) {
            get_food="Dinner";
            _calories = CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","Calories",getApplicationContext());
            _carb =     CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","carb",getApplicationContext());
            _prot =     CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","prot",getApplicationContext());
            _fat =      CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","fat",getApplicationContext());
            _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","SFA",getApplicationContext());
            _chole =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","chole",getApplicationContext());
            _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","sugar",getApplicationContext());
            _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","sodium",getApplicationContext());
            _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","fibre",getApplicationContext());
        } else if (snack.isChecked()) {
            get_food="Snacks";
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

        total_Energy = String.valueOf(totalEnergy/_calories);
        total_Carbs = String.valueOf(totalCarbs/_carb);

        total_Sugar = String.valueOf(totalSugar/_sugar);
        total_Protein = String.valueOf(totalProtein/_prot);
        total_Fat = String.valueOf(totalFat/_fat);
        total_fibre = String.valueOf(totalfibre/_fibre);
        total_sat_fat = String.valueOf(totalsat_fat);
        String total_souium = String.valueOf(totalsouium/_sodium);
        total_cholestrol = String.valueOf(totalcholestrol/_chole);
        boolean b = controller.addFoodData(String.valueOf(total_Energy) + "", food_data, String.valueOf(totalEnergy), total_Protein, total_Fat, total_Carbs, total_Sugar, total_fibre, total_sat_fat, total_souium, total_cholestrol, current_date, user_id, get_food);



    }
    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View view) {
        if (view == home_L) {
            SharedPreferences sp1 = getSharedPreferences("Get_Food_data", Context.MODE_PRIVATE);
            sp1.edit().clear().commit();
            new MyIntent(Eat_out_Plan_Details.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == profile_L) {
            SharedPreferences sp1 = getSharedPreferences("Get_Food_data", Context.MODE_PRIVATE);
            sp1.edit().clear().commit();
            new MyIntent(Eat_out_Plan_Details.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == log_L) {
            SharedPreferences sp1 = getSharedPreferences("Get_Food_data", Context.MODE_PRIVATE);
            sp1.edit().clear().commit();
            new MyIntent(Eat_out_Plan_Details.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == plans_L) {
            SharedPreferences sp1 = getSharedPreferences("Get_Food_data", Context.MODE_PRIVATE);
            sp1.edit().clear().commit();
            new MyIntent(Eat_out_Plan_Details.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == more_L) {
            SharedPreferences sp1 = getSharedPreferences("Get_Food_data", Context.MODE_PRIVATE);
            sp1.edit().clear().commit();
            new MyIntent(Eat_out_Plan_Details.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == breakfast) {
            lunch.setChecked(false);
            dinner.setChecked(false);
            snack.setChecked(false);
            get_food = "Breakfast";
            list_cal.clear();


        }
        if (view == lunch) {
            breakfast.setChecked(false);
            dinner.setChecked(false);
            snack.setChecked(false);
            get_food = "Lunch";
            Log.e("log_food..", get_food + "");
            list_cal.clear();

        }
        if (view == dinner) {
            breakfast.setChecked(false);
            lunch.setChecked(false);
            snack.setChecked(false);
            get_food = "Dinner";
            list_cal.clear();


        }
        if (view == snack) {
            breakfast.setChecked(false);
            lunch.setChecked(false);
            dinner.setChecked(false);
            get_food = "Snacks";
            list_cal.clear();

        }
        if (view == check) {
            if (breakfast.isChecked()) {
                get_food = "Breakfast";
            } else if (lunch.isChecked()) {
                get_food = "Lunch";
            } else if (dinner.isChecked()) {
                get_food = "Dinner";
            } else if (snack.isChecked()) {
                get_food = "Snacks";
            }

            AlertDialogCheckResults();


        }
        if (view == compare) {
            if (breakfast.isChecked()) {
                get_food = "breakfast";
            } else if (lunch.isChecked()) {
                get_food = "lunch";
            } else if (dinner.isChecked()) {
                get_food = "dinner";
            } else if (snack.isChecked()) {
                get_food = "snack";
            }


        }
    }

    void AlertDialogAdd() {
        dialogAddItemDietLog = new Dialog(Eat_out_Plan_Details.this, R.style.CustomDialog);
        dialogAddItemDietLog.setContentView(R.layout.cancerplan_dialog);
        dialogAddItemDietLog.setCancelable(true);
        Button send = (Button) dialogAddItemDietLog.findViewById(R.id.send);
        Button close = (Button) dialogAddItemDietLog.findViewById(R.id.close);
        TextView text_nmae = (TextView) dialogAddItemDietLog.findViewById(R.id.text_nmae);
        text_nmae.setText("Add food items to diet log ?");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  updateDietLogs();
                  dialogAddItemDietLog.dismiss();

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddItemDietLog.dismiss();
            }
        });

        dialogAddItemDietLog.show();
    }

    void AlertDialogCheckResults() {
        dialogAddItemDietLog = new Dialog(Eat_out_Plan_Details.this, R.style.CustomDialog);
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
                float values1 = 0.0f, values2 = 0.0f, values3 = 0.0f, values4 = 0.0f, values5 = 0.0f, values6 = 0.0f, values7 = 0.0f, values8 = 0.0f, values9 = 0.0f;
                for (int i = 0; i < list_foodcat_selected.size(); i++) {
                    if(list_foodcat_selected.get(i).getEnergy().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false) {
                        values1 = values1 + Float.valueOf(list_foodcat_selected.get(i).getEnergy()) * Float.valueOf(list_foodcat_selected.get(i).getNumbers());
                    }

                    if(list_foodcat_selected.get(i).getCarbs().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values2 = values2 + Float.valueOf(list_foodcat_selected.get(i).getCarbs())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getProtein().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values3 = values3 + Float.valueOf(list_foodcat_selected.get(i).getProtein())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getFat().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values4 = values4 + Float.valueOf(list_foodcat_selected.get(i).getFat())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getSat_fat().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values5 = values5 + Float.valueOf(list_foodcat_selected.get(i).getSat_fat())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getCholestrol_bev().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values6 = values6 + Float.valueOf(list_foodcat_selected.get(i).getCholestrol_bev())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getSugar().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values7 = values7 + Float.valueOf(list_foodcat_selected.get(i).getSugar())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getSodium_bev().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values8 = values8 + Float.valueOf(list_foodcat_selected.get(i).getSodium_bev())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getFibre().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values9 = values9 + Float.valueOf(list_foodcat_selected.get(i).getFibre())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());
                }

                calories_cal = values2 + values3 + values4;

                final_breakfast_calculation_calories = values1;
                final_breakfast_calculation_carbs = values2;
                final_breakfast_calculation_protine = values3;
                final_breakfast_calculation_fat = values4;
                final_breakfast_calculation_satfat = values5;
                final_breakfast_calculation_chole = values6;
                final_breakfast_calculation_sugar = values7;
                final_breakfast_calculation_sodium = values8;
                final_breakfast_calculation_fibre = values9;


                float _calories = 0.0f;
                float _carb = 0.0f;
                float _prot = 0.0f;
                float _fat = 0.0f;
                float _SFA = 0.0f;
                float _chole= 0.0f;
                float _sugar = 0.0f;
                float _sodium = 0.0f;
                float _fibre = 0.0f;

                if (breakfast.isChecked()) {
                    _calories = CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","Calories",getApplicationContext());
                    _carb =     CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","carb",getApplicationContext());
                    _prot =     CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","prot",getApplicationContext());
                    _fat =      CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","fat",getApplicationContext());
                    _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","SFA",getApplicationContext());
                    _chole =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","chole",getApplicationContext());
                    _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","sugar",getApplicationContext());
                    _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","sodium",getApplicationContext());
                    _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","fibre",getApplicationContext());
                } else if (lunch.isChecked()) {
                    _calories = _calories = CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","Calories",getApplicationContext());
                    _carb =     CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","carb",getApplicationContext());
                    _prot =     CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","prot",getApplicationContext());
                    _fat =      CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","fat",getApplicationContext());
                    _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","SFA",getApplicationContext());
                    _chole =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","chole",getApplicationContext());
                    _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","sugar",getApplicationContext());
                    _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","sodium",getApplicationContext());
                    _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","fibre",getApplicationContext());

                } else if (dinner.isChecked()) {
                    _calories = CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","Calories",getApplicationContext());
                    _carb =     CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","carb",getApplicationContext());
                    _prot =     CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","prot",getApplicationContext());
                    _fat =      CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","fat",getApplicationContext());
                    _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","SFA",getApplicationContext());
                    _chole =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","chole",getApplicationContext());
                    _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","sugar",getApplicationContext());
                    _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","sodium",getApplicationContext());
                    _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","fibre",getApplicationContext());
                } else if (snack.isChecked()) {
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



                float calories_mul =  (final_breakfast_calculation_calories / _calories)*100;
                float Carbs_mul = (final_breakfast_calculation_carbs / _carb)*100;
                float Sugar_mul =  (final_breakfast_calculation_sugar / _sugar)*100;
                float Protein_mul = (final_breakfast_calculation_protine / _prot)*100;
                float Fat_mul =  (final_breakfast_calculation_fat / _fat)*100;
                float fibre_mul =  (final_breakfast_calculation_fibre / _fibre)*100;
                float sat_fat_mul = (final_breakfast_calculation_satfat / _SFA)*100;
                float chole_mul =  (final_breakfast_calculation_chole / _chole)*100;
                float sodium_mul =  (final_breakfast_calculation_sodium / _sodium)*100;



                calories_cal = final_breakfast_calculation_carbs + final_breakfast_calculation_protine + final_breakfast_calculation_fat;
                new AllSharedPrefrences(Eat_out_Plan_Details.this).Eat_out_result(Carbs_mul + "", Sugar_mul + "", Protein_mul + "", Fat_mul + "", fibre_mul + "", sat_fat_mul + "", with_water_cal + "", with_begrage_cal + "", chole_mul + "", sodium_mul + "", calories_mul + "");
                new MyIntent(Eat_out_Plan_Details.this, Eat_out_Plan_Result.class, R.anim.enter, R.anim.exit,0);


            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddItemDietLog.dismiss();

                float values1 = 0.0f, values2 = 0.0f, values3 = 0.0f, values4 = 0.0f, values5 = 0.0f, values6 = 0.0f, values7 = 0.0f, values8 = 0.0f, values9 = 0.0f;
                for (int i = 0; i < list_foodcat_selected.size(); i++) {
                    if(list_foodcat_selected.get(i).getEnergy().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false) {
                        values1 = values1 + Float.valueOf(list_foodcat_selected.get(i).getEnergy()) * Float.valueOf(list_foodcat_selected.get(i).getNumbers());
                    }

                    if(list_foodcat_selected.get(i).getCarbs().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values2 = values2 + Float.valueOf(list_foodcat_selected.get(i).getCarbs())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getProtein().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values3 = values3 + Float.valueOf(list_foodcat_selected.get(i).getProtein())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getFat().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values4 = values4 + Float.valueOf(list_foodcat_selected.get(i).getFat())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getSat_fat().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values5 = values5 + Float.valueOf(list_foodcat_selected.get(i).getSat_fat())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getCholestrol_bev().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values6 = values6 + Float.valueOf(list_foodcat_selected.get(i).getCholestrol_bev())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getSugar().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values7 = values7 + Float.valueOf(list_foodcat_selected.get(i).getSugar())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getSodium_bev().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values8 = values8 + Float.valueOf(list_foodcat_selected.get(i).getSodium_bev())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());

                    if(list_foodcat_selected.get(i).getFibre().isEmpty()==false && list_foodcat_selected.get(i).getNumbers().isEmpty()==false)
                        values9 = values9 + Float.valueOf(list_foodcat_selected.get(i).getFibre())* Float.valueOf(list_foodcat_selected.get(i).getNumbers());
                }

                calories_cal = values2 + values3 + values4;

                final_breakfast_calculation_calories = values1;
                final_breakfast_calculation_carbs = values2;
                final_breakfast_calculation_protine = values3;
                final_breakfast_calculation_fat = values4;
                final_breakfast_calculation_satfat = values5;
                final_breakfast_calculation_chole = values6;
                final_breakfast_calculation_sugar = values7;
                final_breakfast_calculation_sodium = values8;
                final_breakfast_calculation_fibre = values9;


                float _calories = 0.0f;
                float _carb = 0.0f;
                float _prot = 0.0f;
                float _fat = 0.0f;
                float _SFA = 0.0f;
                float _chole= 0.0f;
                float _sugar = 0.0f;
                float _sodium = 0.0f;
                float _fibre = 0.0f;

                if (breakfast.isChecked()) {
                    _calories = CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","Calories",getApplicationContext());
                    _carb =     CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","carb",getApplicationContext());
                    _prot =     CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","prot",getApplicationContext());
                    _fat =      CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","fat",getApplicationContext());
                    _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","SFA",getApplicationContext());
                    _chole =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","chole",getApplicationContext());
                    _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","sugar",getApplicationContext());
                    _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","sodium",getApplicationContext());
                    _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Breakfast","Total","fibre",getApplicationContext());
                } else if (lunch.isChecked()) {
                    _calories = _calories = CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","Calories",getApplicationContext());
                    _carb =     CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","carb",getApplicationContext());
                    _prot =     CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","prot",getApplicationContext());
                    _fat =      CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","fat",getApplicationContext());
                    _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","SFA",getApplicationContext());
                    _chole =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","chole",getApplicationContext());
                    _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","sugar",getApplicationContext());
                    _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","sodium",getApplicationContext());
                    _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Lunch","Total","fibre",getApplicationContext());

                } else if (dinner.isChecked()) {
                    _calories = CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","Calories",getApplicationContext());
                    _carb =     CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","carb",getApplicationContext());
                    _prot =     CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","prot",getApplicationContext());
                    _fat =      CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","fat",getApplicationContext());
                    _SFA =      CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","SFA",getApplicationContext());
                    _chole =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","chole",getApplicationContext());
                    _sugar =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","sugar",getApplicationContext());
                    _sodium =   CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","sodium",getApplicationContext());
                    _fibre =    CalorieCalculator.getMealNutrientCalorieValue("Dinner","Total","fibre",getApplicationContext());
                } else if (snack.isChecked()) {
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



                float calories_mul =  (final_breakfast_calculation_calories / _calories)*100;
                float Carbs_mul = (final_breakfast_calculation_carbs / _carb)*100;
                float Sugar_mul =  (final_breakfast_calculation_sugar / _sugar)*100;
                float Protein_mul = (final_breakfast_calculation_protine / _prot)*100;
                float Fat_mul =  (final_breakfast_calculation_fat / _fat)*100;
                float fibre_mul =  (final_breakfast_calculation_fibre / _fibre)*100;
                float sat_fat_mul = (final_breakfast_calculation_satfat / _SFA)*100;
                float chole_mul =  (final_breakfast_calculation_chole / _chole)*100;
                float sodium_mul =  (final_breakfast_calculation_sodium / _sodium)*100;



                calories_cal = final_breakfast_calculation_carbs + final_breakfast_calculation_protine + final_breakfast_calculation_fat;
                new AllSharedPrefrences(Eat_out_Plan_Details.this).Eat_out_result(Carbs_mul + "", Sugar_mul + "", Protein_mul + "", Fat_mul + "", fibre_mul + "", sat_fat_mul + "", with_water_cal + "", with_begrage_cal + "", chole_mul + "", sodium_mul + "", calories_mul + "");
                new MyIntent(Eat_out_Plan_Details.this, Eat_out_Plan_Result.class, R.anim.enter, R.anim.exit,0);
            }
        });

        dialogAddItemDietLog.show();
    }

    void AlertDialog() {
        dialog = new Dialog(Eat_out_Plan_Details.this, R.style.CustomDialog);
        dialog.setContentView(R.layout.cancerplan_dialog);
        dialog.setCancelable(true);
        Button send = (Button) dialog.findViewById(R.id.send);
        Button close = (Button) dialog.findViewById(R.id.close);
        TextView text_nmae = (TextView) dialog.findViewById(R.id.text_nmae);
        text_nmae.setText("Do you want to exit the tracker?");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp1 = getSharedPreferences("Get_Food_data", Context.MODE_PRIVATE);
                sp1.edit().clear().commit();
                new MyIntent(Eat_out_Plan_Details.this, Eat_Out_Snacker.class, R.anim.enter2, R.anim.exit2);
                dialog.dismiss();
                finish();
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

    void AlertDialogItemAdd() {

    }

    public float totalCaloriesConsumed=0;
    public HashMap<Integer,String> hmServings=new HashMap<Integer,String>();
    public HashMap<Integer,String> servingString=new  HashMap<Integer,String>();

    void trackCalories(String mealType)
    {

        totalCaloriesConsumed=0;
        float userServings=1;

        servingString.clear();

        for(int i=0;i<list_foodcat_selected.size();i++) {

            userServings=Float.valueOf(list_foodcat_selected.get(i).getNumbers());

            if (list_foodcat_selected.size() - 1 != i) {

                String totalCaloriesNeeded = CalorieCalculator.getPreference(getApplicationContext(), mealType + "Total" + "Calories");
                Float totalCaloriesNeedInDecimal = Float.valueOf(totalCaloriesNeeded);

                Float portionsEqualToHealthyMeal = totalCaloriesNeedInDecimal / Float.valueOf(list_foodcat_selected.get(i).getEnergy());
                //String toShow = "Current Serving in your meal" + portionsEqualToHealthyMeal + " of (" + list_foodcat_selected.get(i).getUnit_notes() + ")";
                servingString.put(i,"");
                totalCaloriesConsumed += (userServings * Float.valueOf(list_foodcat_selected.get(i).getEnergy()));
            } else {

                String totalCaloriesNeeded = CalorieCalculator.getPreference(getApplicationContext(), mealType + "Total" + "Calories");
                Float totalCaloriesNeedInDecimal = Float.valueOf(totalCaloriesNeeded) - totalCaloriesConsumed;
                Float portionsCanBeTaken = totalCaloriesNeedInDecimal / Float.valueOf(list_foodcat_selected.get(i).getEnergy());

                String toShow =  String.format("%.1f", portionsCanBeTaken) + " * Choosen Serving = " + " Calories in your "+ get_food;
                servingString.put(i,toShow);

            }
        }

        if(fu==null || list_foodcat_selected.size() ==1) {
            fu = new FoodCategoryUnits(get_food,getApplicationContext());
            listFoodCategory.setAdapter(fu);
            }
        else {
            listFoodCategory.setAdapter(fu);
            fu.notifyDataSetChanged();


            eatOutScrollView.post(new Runnable() {
                @Override
                public void run() {

                    if(insertions==1) {
                        eatOutScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        insertions=0;
                    }
                }
            });
        }
    }

    class FoodCategoryUnits extends BaseAdapter {

        public FoodCategoryUnits(String mealType, Context ctx) {
            this.mealType = mealType;
            this.ctx = ctx;
        }

        String mealType;
        Context ctx;
        boolean m=true;


        @Override
        public int getCount() {
            return list_foodcat_selected.size();
        }

        @Override
        public Object getItem(int position) {
            return list_foodcat_selected.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(position > 3)
                return null;


            View vv = getLayoutInflater().inflate(R.layout.food_category_tracker, null);
            TextView name = (TextView) vv.findViewById(R.id.name);
            TextView quentity = (TextView) vv.findViewById(R.id.qnt1);
            TextView results = (TextView) vv.findViewById(R.id.Result);
            final TextView count = (TextView) vv.findViewById(R.id.count);
            Spinner spv=(Spinner)vv.findViewById(R.id.listUnits);
            final EditText sizings=(EditText) vv.findViewById(R.id.servingSizings);
            ImageView cross = (ImageView) vv.findViewById(R.id.cross);
            Button buttock=(Button)vv.findViewById(R.id.servingComparisons);
            m=true;




            final Eat_out_Plan_models am = list_foodcat_selected.get(position);
            name.setText(am.getFoodname());
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(Eat_out_Plan_Details.this, android.R.layout.simple_spinner_dropdown_item,am.getHm());

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

                    list_foodcat_selected.set(position,temp);
                    trackCalories(mealType);
                }



                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    if(position == list_foodcat_selected.size()-1) {
                        list_foodcat_selected.remove(position);
                        if(servingString.get(position)!=null) {


                            servingString.remove(position);
                        }

                        trackCalories(mealType);
                    }
//                    else
//                    {
//                        Toast.makeText(ctx, "You can only delete the last item", Toast.LENGTH_SHORT).show();
//                    }



//                }
            });


            if(position==list_foodcat_selected.size()-1)
            {
                buttock.setVisibility(View.VISIBLE);
                results.setVisibility(View.VISIBLE);
                //buttock.setText(servingString.get(position));
                if(list_foodcat_selected.size()==1)
                    results.setText(servingString.get(position));
                else if(list_foodcat_selected.size()<= 2)
                results.setText("Previous Item + " +servingString.get(position));
                else
                results.setText("Previous Items + " + servingString.get(position));


                buttock.setText("Set " + servingString.get(position).split("\\*")[0] +" as serving size");

                buttock.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                        dialogAddItem = new Dialog(Eat_out_Plan_Details.this, R.style.CustomDialog);
                        dialogAddItem.setContentView(R.layout.cancerplan_dialog);
                        dialogAddItem.setCancelable(true);
                        Button send = (Button) dialogAddItem.findViewById(R.id.send);
                        Button close = (Button) dialogAddItem.findViewById(R.id.close);
                        TextView text_nmae = (TextView) dialogAddItem.findViewById(R.id.text_nmae);
                        text_nmae.setText("Do you want to set this as serving size?");
                        send.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String split[]=servingString.get(position).split("\\*");
                                list_foodcat_selected.get(position).setNumbers(split[0]);
                                trackCalories(mealType);
                                dialogAddItem.dismiss();

                            }
                        });

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialogAdd();
                                dialogAddItem.dismiss();
                            }
                        });

                        dialogAddItem.show();


                    }
                });
            }
            else
            {
                buttock.setVisibility(View.GONE);
                results.setVisibility(View.GONE);
            }



            if(sizings.getText().toString().equals(list_foodcat_selected.get(position).getNumbers())==false)
            {
               sizings.setText(list_foodcat_selected.get(position).getNumbers());
            }
          /*  ArrayAdapter<String> arrayAdapter;
            arrayAdapter=  new ArrayAdapter<String>(Eat_out_Plan_Details.this, android.R.layout.simple_spinner_dropdown_item,dataList);
            sizings.setAdapter(arrayAdapter);*/

            sizings.setText(list_foodcat_selected.get(position).getNumbers());
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
                                    list_foodcat_selected.get(position).setNumbers(selected);
                                    trackCalories(mealType);
                                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                                    // consume.
                                }
                            }
                            return false; // pass on to other listeners.
                        }
                    });

         /*
            sizings.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // code to execute when EditText loses focus
                        ArrayList<String> list1 = am.getHm();
                        String selected = sizings.getText().toString();
                        list_foodcat_selected.get(position).setNumbers(selected);
                        trackCalories(mealType);
                    }
                }
            });*/

         /*   sizings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {



                       ArrayList<String> list1 = am.getHm();
                        String selected = parentView.getItemAtPosition(pos).toString();
                        list_foodcat_selected.get(position).setNumbers(selected);
                        trackCalories(mealType);


                }



                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });*/






            return vv;
        }
    }

    //private method of your class
    private int getIndex(AutoCompleteTextView spinner, String myString){
        for (int i=0;i<spinner.getAdapter().getCount();i++){
            if (spinner.getAdapter().getItem(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
