package com.hwi.health.sqlite_database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.hwi.health.Models.DietLog_Models;
import com.hwi.health.Models.Eat_out_Beverage_models;
import com.hwi.health.Models.Eat_out_Plan_models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {

	public final static String DataBaseName="QB1111";
	public final static String TableName="Products1";
	public final static String TableFood="logFoodSave";
	public final static String TableFoodData="foodLogData";
	public final static String TableDietPlan="dietPlan";
	public final static String TableBasicDietPlan="basicdietPlan";
	public final static String TableAlchol="alcholResult";
	public final static String TableFood_Ideal_diet_plan ="Ideal_diet_plan";
	public final static String TablelogFood="logFood";
	public final static int Version=8;
	public static String DB_PATH;
	public static String DB_NAME;

	
	Context context;
	
	public DBHelper(Context context, String name, CursorFactory factory,
                    int version) {
		super(context, name, factory, version);
		this.context=context;
	}


    public ArrayList<DietLog_Models> getlogFoodDiet() {

        ArrayList<DietLog_Models> list2 = new ArrayList<>();
        HashMap<String,Integer> hm=new HashMap<String, Integer>();
        int i=0;

        try {

            SQLiteDatabase mDb = this.getReadableDatabase();
            String sql1 = "SELECT * FROM logFood  WHERE  rawcooked = 'Everyday meal'";
            Cursor testdata = mDb.rawQuery(sql1, null);


            while (testdata.moveToNext() == true) {
                String id = testdata.getString(0);
                String foodname = testdata.getString(1).trim();
                String Indian_name = testdata.getString(2).trim();
                String Major_food_category = testdata.getString(3);
                String Raw_cooked = testdata.getString(4);
                String unit_notes = testdata.getString(5);
                String Weight_in_ms = testdata.getString(6);
                String energy = testdata.getString(7);
                String protein = testdata.getString(8);
                String fat = testdata.getString(9);
                String Carbs = testdata.getString(10);
                String Sugar = testdata.getString(11);
                String fibre = testdata.getString(12);
                String sat_fat = testdata.getString(13);
                String cholestrol = testdata.getString(14);
                String sodium = testdata.getString(15);
                String percentage=testdata.getString(16);
                String standardDrink =testdata.getString(17);
                String volume=testdata.getString(18);

                if(hm.get(foodname)==null) {
                    DietLog_Models models = new DietLog_Models(
                            foodname,
                            Indian_name,
                            Major_food_category,
                            Raw_cooked,
                            unit_notes,
                            Weight_in_ms,
                            energy,
                            protein,
                            fat,
                            Carbs,
                            Sugar,
                            fibre,
                            sat_fat,
                            "1.00",
                            cholestrol,
                            sodium);
                    list2.add(models);
                    hm.put(foodname,i);
                    i++;
                }
                else
                {
                    list2.get(hm.get(foodname)).getHm().add(unit_notes);
                }




            }

            String sql2 = "SELECT * FROM logFood  WHERE  rawcooked = 'Meal'";

            testdata = mDb.rawQuery(sql2, null);


            while (testdata.moveToNext() == true) {
                String id = testdata.getString(0);
                String foodname = testdata.getString(1).trim();
                String Indian_name = testdata.getString(2).trim();
                String Major_food_category = testdata.getString(3);
                String Raw_cooked = testdata.getString(4);
                String unit_notes = testdata.getString(5);
                String Weight_in_ms = testdata.getString(6);
                String energy = testdata.getString(7);
                String protein = testdata.getString(8);
                String fat = testdata.getString(9);
                String Carbs = testdata.getString(10);
                String Sugar = testdata.getString(11);
                String fibre = testdata.getString(12);
                String sat_fat = testdata.getString(13);
                String cholestrol = testdata.getString(14);
                String sodium = testdata.getString(15);
                String percentage=testdata.getString(16);
                String standardDrink =testdata.getString(17);
                String volume=testdata.getString(18);



                if(hm.get(foodname)==null) {
                    DietLog_Models models = new DietLog_Models(
                            foodname,
                            Indian_name,
                            Major_food_category,
                            Raw_cooked,
                            unit_notes,
                            Weight_in_ms,
                            energy,
                            protein,
                            fat,
                            Carbs,
                            Sugar,
                            fibre,
                            sat_fat,
                            "1",
                            cholestrol,
                            sodium);

                    list2.add(models);
                    hm.put(foodname,i);
                    i++;
                }
                else
                {
                    list2.get(hm.get(foodname)).getHm().add(unit_notes);
                }

            }

           sql2 = "SELECT * FROM logFood  WHERE  rawcooked!= 'Meal' and rawcooked!= 'Everyday Meal' and rawcooked!= 'Ingredient'";

          testdata= mDb.rawQuery(sql2, null);


            while (testdata.moveToNext() == true) {
                String id = testdata.getString(0);
                String foodname = testdata.getString(1).trim();
                String Indian_name = testdata.getString(2).trim();
                String Major_food_category = testdata.getString(3);
                String Raw_cooked = testdata.getString(4);
                String unit_notes = testdata.getString(5);
                String Weight_in_ms = testdata.getString(6);
                String energy = testdata.getString(7);
                String protein = testdata.getString(8);
                String fat = testdata.getString(9);
                String Carbs = testdata.getString(10);
                String Sugar = testdata.getString(11);
                String fibre = testdata.getString(12);
                String sat_fat = testdata.getString(13);
                String cholestrol = testdata.getString(14);
                String sodium = testdata.getString(15);
                String percentage=testdata.getString(16);
                String standardDrink =testdata.getString(17);
                String volume=testdata.getString(18);


                if(hm.get(foodname)==null) {
                    DietLog_Models models = new DietLog_Models(
                            foodname,
                            Indian_name,
                            Major_food_category,
                            Raw_cooked,
                            unit_notes,
                            Weight_in_ms,
                            energy,
                            protein,
                            fat,
                            Carbs,
                            Sugar,
                            fibre,
                            sat_fat,
                            "1.00",
                            cholestrol,
                            sodium);
                    list2.add(models);

                    hm.put(foodname,i);
                    i++;
                }
                else
                {
                    list2.get(hm.get(foodname)).getHm().add(unit_notes);
                }


            }


            String sql3 = "SELECT * FROM logFood  WHERE  rawcooked = 'Ingredient'";

          testdata = mDb.rawQuery(sql3, null);


            while (testdata.moveToNext() == true) {

                String id = testdata.getString(0);
                String foodname = testdata.getString(1).trim();
                String Indian_name = testdata.getString(2).trim();
                String Major_food_category = testdata.getString(3);
                String Raw_cooked = testdata.getString(4);
                String unit_notes = testdata.getString(5);
                String Weight_in_ms = testdata.getString(6);
                String energy = testdata.getString(7);
                String protein = testdata.getString(8);
                String fat = testdata.getString(9);
                String Carbs = testdata.getString(10);
                String Sugar = testdata.getString(11);
                String fibre = testdata.getString(12);
                String sat_fat = testdata.getString(13);
                String cholestrol = testdata.getString(14);
                String sodium = testdata.getString(15);
                String percentage=testdata.getString(16);
                String standardDrink =testdata.getString(17);
                String volume=testdata.getString(18);


                if(hm.get(foodname)==null) {
                    DietLog_Models models = new DietLog_Models(
                            foodname,
                            Indian_name,
                            Major_food_category,
                            Raw_cooked,
                            unit_notes,
                            Weight_in_ms,
                            energy,
                            protein,
                            fat,
                            Carbs,
                            Sugar,
                            fibre,
                            sat_fat,
                            "1.00",
                            cholestrol,
                            sodium);
                    list2.add(models);

                    hm.put(foodname,i);
                    i++;
                }
                else
                {
                    list2.get(hm.get(foodname)).getHm().add(unit_notes);
                }

            }


            return list2;

        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }
    public boolean isTableExists(String tableName, boolean openDb) {

        SQLiteDatabase mDatabase = this.getReadableDatabase();


        Cursor cursor = mDatabase.rawQuery("select DISTINCT from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

	public void fillFromCsv() throws IOException {




		BufferedReader buffer = new BufferedReader(new InputStreamReader(context.getAssets().open("modified_eatout.csv")));
		String line = "";
		String tableName ="logFood";
		String columns = "foodname,indianname,foodcategory,rawcooked,unit,weightms,energy,protein,fat,carbs,sugar,fibre,satfat,cholestrol,sodium,percentage,std_drink,volume";
		String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";


		SQLiteDatabase db = this.getWritableDatabase();
	    db.execSQL("DROP TABLE IF EXISTS " + "logFood" );
        db.execSQL("create table logFood "+"(ids INTEGER PRIMARY KEY AUTOINCREMENT, foodname varchar, indianname varchar, foodcategory varchar, rawcooked varchar, unit varchar, weightms varchar, energy varchar, protein varchar, fat varchar, carbs varchar, sugar varchar, fibre varchar, satfat varchar, cholestrol varchar,sodium varchar,percentage varchar,std_drink varchar,volume varchar)");



        db.beginTransaction();
		try {
			while ((line = buffer.readLine()) != null) {
			    try {
			        line=  line.replaceAll("'", "\''");
			        StringBuilder sb = new StringBuilder(str1);
                    String[] str = line.split(",", Integer.MAX_VALUE);
                    if(str[0].equals(""))
                        str[0]="0";
                    sb.append("'" + str[0] + "',");
                    if(str[1].equals(""))
                        str[1]="0";
                    sb.append("'" + str[1] + "',");
                    if(str[2].equals(""))
                        str[2]="0";
                    sb.append("'" + str[2] + "',");
                    if(str[3].equals(""))
                        str[3]="0";
                    sb.append("'" + str[3] + "',");
                    if(str[4].equals(""))
                        str[4]="0";
                    sb.append("'" + str[4] + "',");
                    if(str[5].equals(""))
                        str[5]="0";
                    sb.append("'" + str[5] + "',");
                    if(str[6].equals(""))
                        str[6]="0";
                    sb.append("'" + str[6] + "',");
                    if(str[7].equals(""))
                        str[7]="0";
                    sb.append("'" + str[7] + "',");
                    if(str[8].equals(""))
                        str[8]="0";
                    sb.append("'" + str[8] + "',");
                    if(str[9].equals(""))
                        str[9]="0";
                    sb.append("'" + str[9] + "',");
                    if(str[10].equals(""))
                        str[10]="0";
                    sb.append("'" + str[10] + "',");
                    if(str[11].equals(""))
                        str[11]="0";
                    sb.append("'" + str[11] + "',");
                    if(str[12].equals(""))
                        str[12]="0";
                    sb.append("'" + str[12] + "',");
                    if(str[13].equals(""))
                        str[13]="0";
                    sb.append("'" + str[13] + "',");
                    if(str[14].equals(""))
                        str[14]="0";
                    sb.append("'" + str[14] + "',");
                    if(str[15].equals(""))
                        str[15]="0";
                    sb.append("'" + str[15] + "',");
                    if(str[16].equals(""))
                        str[16]="0";
                    sb.append("'" + str[16] + "',");
                    if(str[17].equals(""))
                        str[17]="0";
                    sb.append("'" + str[17] + "');");
                    db.execSQL(sb.toString());
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    continue;
                }
            }
		} catch (IOException e) {
            e.printStackTrace();
		}
		db.setTransactionSuccessful();
		db.endTransaction();
	}
    public ArrayList<Eat_out_Beverage_models> getBeverage() {

        SQLiteDatabase mDb = this.getReadableDatabase();
        ArrayList<Eat_out_Beverage_models> list_bevg = new ArrayList<>();
        try {
            String sql = "SELECT * FROM logFood WHERE rawcooked = 'Beverages'";

            Cursor mCur = mDb.rawQuery(sql, null);
            while (mCur.moveToNext() == true) {
                String foodname_bev = mCur.getString(1).trim();

                String Indian_name_bev = mCur.getString(2).trim();
                String Major_food_category_bev = mCur.getString(3);
                String Raw_cooked_bev = mCur.getString(4);
                String unit_notes_bev = mCur.getString(5);
                String Weight_in_ms_bev = mCur.getString(6);
                String energy_bev = mCur.getString(7);
                String protein_bev = mCur.getString(8);
                String fat_bev = mCur.getString(9);
                String Carbs_bev = mCur.getString(10);
                String Sugar_bev = mCur.getString(11);
                String fibre_bev = mCur.getString(12);
                String sat_fat_bev = mCur.getString(13);
                String cholestrol_bev = mCur.getString(14);
                String sodium_bev = mCur.getString(15);
                Eat_out_Beverage_models models = new Eat_out_Beverage_models(foodname_bev, Indian_name_bev, Major_food_category_bev, Raw_cooked_bev, unit_notes_bev, Weight_in_ms_bev, energy_bev, protein_bev, fat_bev, Carbs_bev, Sugar_bev, fibre_bev, sat_fat_bev, cholestrol_bev, sodium_bev,"1");
                list_bevg.add(models);
            }
            return list_bevg;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<String> getMake() {

        SQLiteDatabase mDb = this.getReadableDatabase();
        ArrayList<String> flist = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT rawcooked FROM logFood";
            // String sql = "SELECT distinct(foodcategory) FROM logFood WHERE rawcooked = 'Everyday meal' OR rawcooked = 'Meal'";
            Cursor mCur = mDb.rawQuery(sql, null);
            while (mCur.moveToNext() == true) {
                String make = mCur.getString(0).trim();
                if(make.isEmpty())
                    continue;
                flist.add(make);
            }
            Set<String> hs = new HashSet<>();
            hs.addAll(flist);
            flist.clear();
            flist.addAll(hs);
            return flist;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<String> getMakeCategories(String selection) {

	    if(selection.isEmpty()==true)
	        return null;

        SQLiteDatabase mDb = this.getReadableDatabase();
        ArrayList<String> flist = new ArrayList<>();
        try {
            String sql = "SELECT distinct(foodcategory) FROM logFood where rawcooked=? ";
            // String sql = "SELECT distinct(foodcategory) FROM logFood WHERE rawcooked = 'Everyday meal' OR rawcooked = 'Meal'";
            Cursor mCur = mDb.rawQuery(sql, new String[]{selection});
            while (mCur.moveToNext() == true) {

                String make = mCur.getString(0).trim();
                if(make.isEmpty())
                    continue;
                flist.add(make);
            }
            Set<String> hs = new HashSet<>();
            hs.addAll(flist);
            flist.clear();
            flist.addAll(hs);
            return flist;

        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Eat_out_Plan_models getAlcoholicServing(String selection,Eat_out_Plan_models am) {

        SQLiteDatabase mDb = null;
        ArrayList<Eat_out_Plan_models> list_food = null;
        Cursor testdata = null;
        String sql = null;
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        int i = 0;


        mDb = this.getReadableDatabase();
        list_food = new ArrayList<>();

        sql = "SELECT * FROM logFood where rawcooked='Alcoholic'";
        testdata = mDb.rawQuery(sql, null);


        while (testdata.moveToNext() == true) {
            String id = testdata.getString(0);
            String foodname = testdata.getString(1).trim();
            String Indian_name = testdata.getString(2).trim();
            String Major_food_category = testdata.getString(3);
            String Raw_cooked = testdata.getString(4);
            String unit_notes = testdata.getString(5);
            String Weight_in_ms = testdata.getString(6);
            String energy = testdata.getString(7);
            String protein = testdata.getString(8);
            String fat = testdata.getString(9);
            String Carbs = testdata.getString(10);
            String Sugar = testdata.getString(11);
            String fibre = testdata.getString(12);
            String sat_fat = testdata.getString(13);
            String cholestrol_bev = testdata.getString(14);
            String sodium_bev = testdata.getString(15);
            String percentage=testdata.getString(16);
            String standardDrink =testdata.getString(17);
            String volume=testdata.getString(18);


            if (am.getFoodname().equals(am.getFoodname())) {
                if (unit_notes.equals(selection)) {
                    Eat_out_Plan_models models = new Eat_out_Plan_models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, cholestrol_bev, sodium_bev, am.getNumbers(),percentage,standardDrink,volume);
                    return models;
                } else {
                    continue;
                }

            }


        }
        return null;
    }

    public DietLog_Models getDietLogServing(String selection,DietLog_Models am) {

        SQLiteDatabase mDb = null;
        ArrayList<DietLog_Models> list_food = null;
        Cursor testdata = null;
        String sql = null;
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        int i = 0;


        mDb = this.getReadableDatabase();
        list_food = new ArrayList<>();

        sql = "SELECT * FROM logFood";
        testdata = mDb.rawQuery(sql, null);


        while (testdata.moveToNext() == true) {
            String id = testdata.getString(0);
            String foodname = testdata.getString(1).trim();
            String Indian_name = testdata.getString(2).trim();
            String Major_food_category = testdata.getString(3);
            String Raw_cooked = testdata.getString(4);
            String unit_notes = testdata.getString(5);
            String Weight_in_ms = testdata.getString(6);
            String energy = testdata.getString(7);
            String protein = testdata.getString(8);
            String fat = testdata.getString(9);
            String Carbs = testdata.getString(10);
            String Sugar = testdata.getString(11);
            String fibre = testdata.getString(12);
            String sat_fat = testdata.getString(13);
            String cholestrol_bev = testdata.getString(14);
            String sodium_bev = testdata.getString(15);


            if (am.getFoodname().equals(am.getFoodname())) {
                if (unit_notes.equals(selection)) {
                    DietLog_Models models = new DietLog_Models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, cholestrol_bev, sodium_bev, am.getNumbers());
                    return models;
                } else {
                    continue;
                }

            }


        }
        return null;
    }
    public Eat_out_Plan_models getEatOutServing(String selection,Eat_out_Plan_models am) {

        SQLiteDatabase mDb = null;
        ArrayList<Eat_out_Plan_models> list_food = null;
        Cursor testdata = null;
        String sql = null;
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        int i = 0;


        mDb = this.getReadableDatabase();
        list_food = new ArrayList<>();

        sql = "SELECT * FROM logFood";
        testdata = mDb.rawQuery(sql, null);


        while (testdata.moveToNext() == true) {
            String id = testdata.getString(0);
            String foodname = testdata.getString(1).trim();
            String Indian_name = testdata.getString(2).trim();
            String Major_food_category = testdata.getString(3);
            String Raw_cooked = testdata.getString(4);
            String unit_notes = testdata.getString(5);
            String Weight_in_ms = testdata.getString(6);
            String energy = testdata.getString(7);
            String protein = testdata.getString(8);
            String fat = testdata.getString(9);
            String Carbs = testdata.getString(10);
            String Sugar = testdata.getString(11);
            String fibre = testdata.getString(12);
            String sat_fat = testdata.getString(13);
            String cholestrol_bev = testdata.getString(14);
            String sodium_bev = testdata.getString(15);


            if (am.getFoodname().equals(am.getFoodname())) {
                if (unit_notes.equals(selection)) {
                    Eat_out_Plan_models models = new Eat_out_Plan_models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, cholestrol_bev, sodium_bev, am.getNumbers());
                    return models;
                } else {
                    continue;
                }

            }


        }
        return null;
    }


    public ArrayList<Eat_out_Plan_models> getlogFood2(String selection) {

        SQLiteDatabase mDb=null;
        ArrayList<Eat_out_Plan_models> list_food=null;
        Cursor testdata=null;
        String sql=null;
        HashMap<String,Integer> hm=new HashMap<String, Integer>();
        int i=0;


        if(selection.isEmpty()!=true) {
             mDb = this.getReadableDatabase();
             list_food = new ArrayList<>();
             sql = "SELECT * FROM logFood where rawcooked = ? limit 300";
             testdata = mDb.rawQuery(sql, new String[]{selection});
        }
        else
        {
             mDb = this.getReadableDatabase();
            list_food = new ArrayList<>();

            sql = "SELECT * FROM logFood limit 700";
            testdata = mDb.rawQuery(sql, null);
        }




        while (testdata.moveToNext() == true) {
            String id = testdata.getString(0);
            String foodname = testdata.getString(1).trim();
            String Indian_name = testdata.getString(2).trim();
            String Major_food_category = testdata.getString(3);
            String Raw_cooked = testdata.getString(4);
            String unit_notes = testdata.getString(5);
            String Weight_in_ms = testdata.getString(6);
            String energy = testdata.getString(7);
            String protein = testdata.getString(8);
            String fat = testdata.getString(9);
            String Carbs = testdata.getString(10);
            String Sugar = testdata.getString(11);
            String fibre = testdata.getString(12);
            String sat_fat = testdata.getString(13);
            String cholestrol_bev = testdata.getString(14);
            String sodium_bev = testdata.getString(15);


            if(hm.get(foodname)==null) {
                Eat_out_Plan_models models = new Eat_out_Plan_models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, cholestrol_bev, sodium_bev, "1.00");
                list_food.add(models);
                hm.put(foodname,i);
                i++;
            }
            else
            {
               list_food.get(hm.get(foodname)).getHm().add(unit_notes);
            }


        }
        return list_food;
    }

    public ArrayList<Eat_out_Plan_models> getlogFood3(String selection) {

        SQLiteDatabase mDb=null;
        ArrayList<Eat_out_Plan_models> list_food=null;
        Cursor testdata=null;
        String sql=null;
        HashMap<String,Integer> hm=new HashMap<String, Integer>();
        int i=0;


        if(selection.isEmpty()!=true) {
            mDb = this.getReadableDatabase();
            list_food = new ArrayList<>();
            sql = "SELECT * FROM logFood where foodcategory= ?";
            testdata = mDb.rawQuery(sql, new String[]{selection});
        }
        else
        {
            mDb = this.getReadableDatabase();
            list_food = new ArrayList<>();

            sql = "SELECT * FROM logFood";
            testdata = mDb.rawQuery(sql, null);
        }




        while (testdata.moveToNext() == true) {
            String id = testdata.getString(0);
            String foodname = testdata.getString(1).trim();
            String Indian_name = testdata.getString(2).trim();
            String Major_food_category = testdata.getString(3);
            String Raw_cooked = testdata.getString(4);
            String unit_notes = testdata.getString(5);
            String Weight_in_ms = testdata.getString(6);
            String energy = testdata.getString(7);
            String protein = testdata.getString(8);
            String fat = testdata.getString(9);
            String Carbs = testdata.getString(10);
            String Sugar = testdata.getString(11);
            String fibre = testdata.getString(12);
            String sat_fat = testdata.getString(13);
            String cholestrol_bev = testdata.getString(14);
            String sodium_bev = testdata.getString(15);


            if(hm.get(foodname)==null) {
                Eat_out_Plan_models models = new Eat_out_Plan_models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, cholestrol_bev, sodium_bev, "1.00");
                list_food.add(models);
                hm.put(foodname,i);
                i++;
            }
            else
            {
                list_food.get(hm.get(foodname)).getHm().add(unit_notes);
            }


        }
        return list_food;
    }


    public ArrayList<Eat_out_Plan_models> getAlchoholicBeverages(String selection) {

        SQLiteDatabase mDb=null;
        ArrayList<Eat_out_Plan_models> list_food=null;
        Cursor testdata=null;
        String sql=null;
        HashMap<String,Integer> hm=new HashMap<String, Integer>();
        int i=0;


        if(selection.isEmpty()!=true) {
            mDb = this.getReadableDatabase();
            list_food = new ArrayList<>();
            sql = "SELECT * FROM logFood where rawcooked ='Alcoholic'";
            testdata = mDb.rawQuery(sql, new String[]{selection});
        }
        else
        {
            mDb = this.getReadableDatabase();
            list_food = new ArrayList<>();

            sql = "SELECT * FROM logFood where rawcooked ='Alcoholic'";
            testdata = mDb.rawQuery(sql, null);
        }




        while (testdata.moveToNext() == true) {
            String id = testdata.getString(0);
            String foodname = testdata.getString(1).trim();
            String Indian_name = testdata.getString(2).trim();
            String Major_food_category = testdata.getString(3);
            String Raw_cooked = testdata.getString(4);
            String unit_notes = testdata.getString(5);
            String Weight_in_ms = testdata.getString(6);
            String energy = testdata.getString(7);
            String protein = testdata.getString(8);
            String fat = testdata.getString(9);
            String Carbs = testdata.getString(10);
            String Sugar = testdata.getString(11);
            String fibre = testdata.getString(12);
            String sat_fat = testdata.getString(13);
            String cholestrol_bev = testdata.getString(14);
            String sodium_bev = testdata.getString(15);
            String percentage=testdata.getString(16);
            String standardDrink =testdata.getString(17);
            String volume=testdata.getString(18);


            if(hm.get(foodname)==null) {
                Eat_out_Plan_models models = new Eat_out_Plan_models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, cholestrol_bev, sodium_bev, "1.00",percentage,standardDrink,volume);
                list_food.add(models);
                hm.put(foodname,i);
                i++;
            }
            else
            {
                list_food.get(hm.get(foodname)).getHm().add(unit_notes);
            }


        }
        return list_food;
    }


    @Override
	public void onCreate(SQLiteDatabase db)
	{
		    db.execSQL("create table "+TableName+"(ids INTEGER PRIMARY KEY AUTOINCREMENT, first_type varchar, name varchar, quantity varchar, Calories varchar, Std_size_drinks varchar, Carbs varchar, Sugar varchar, Sodium varchar, numbers varchar, id varchar)");
			db.execSQL("create table "+TableFood+"(ids INTEGER PRIMARY KEY AUTOINCREMENT, foodname varchar, Indian_name varchar, Major_food_category varchar, Raw_cooked varchar, unit_notes varchar, Weight_in_ms varchar, energy varchar, protein varchar, fat varchar, Carbs varchar, Sugar varchar, fibre varchar, sat_fat varchar, numbers varchar, chole varchar, sodium varchar)");
			//db.execSQL("create table "+TableFood_Show+"(foodname varchar, Indian_name varchar, Major_food_category varchar, Raw_cooked varchar, unit_notes varchar, Weight_in_ms varchar, energy varchar, protein varchar, fat varchar, Carbs varchar, Sugar varchar, fibre varchar, sat_fat varchar, numbers varchar, chole varchar, sodium varchar)");
			db.execSQL("create table "+TableFoodData+"(ids INTEGER PRIMARY KEY AUTOINCREMENT, namefood varchar, Energy varchar, calories varchar, Protein varchar, Fat varchar, carbs varchar, sugar varchar, Fibre varchar, Sat_fat varchar, chole varchar, sodium varchar, date_ varchar, user_id varchar, food_key varchar)");
		    db.execSQL("create table "+TableDietPlan+"(ids INTEGER PRIMARY KEY AUTOINCREMENT, user_id varchar, diet_result varchar)");
		    db.execSQL("create table "+TableBasicDietPlan+"(ids INTEGER PRIMARY KEY AUTOINCREMENT, user_id varchar, basic_diet_oil varchar)");
		    db.execSQL("create table "+TableAlchol+"(ids INTEGER PRIMARY KEY AUTOINCREMENT, calories varchar, carbs varchar, sugar varchar, sodium varchar, Sat_size varchar, units varchar, date_ varchar, user_id varchar, food_data varchar)");

		    db.execSQL("create table "+TableFood_Ideal_diet_plan+"(ids INTEGER PRIMARY KEY AUTOINCREMENT,user_id varchar, GrainsCereals_Servings_equals varchar, GrainsCereals_BasicIndianDiet varchar, GrainsCereals_OptimizedIndiandiet varchar, GrainsCereals_IdealDietForPeopleWithHealthRisks varchar, GrainsCereals_ToknowmoregotoFWItips varchar, DalCereals_Servings_equals varchar, DalCereals_BasicIndianDiet varchar, DalCereals_OptimizedIndiandiet varchar, DalCereals_IdealDietForPeopleWithHealthRisks varchar, DalCereals_ToknowmoregotoFWItips varchar, MaxmCereals_Servings_equals varchar, MaxmCereals_BasicIndianDiet varchar, MaxmCereals_OptimizedIndiandiet varchar, MaxmCereals_IdealDietForPeopleWithHealthRisks varchar, MaxmCereals_ToknowmoregotoFWItips varchar" +
					", MinmCereals_Servings_equals varchar, MinmCereals_BasicIndianDiet varchar, MinmCereals_OptimizedIndiandiet varchar, MinmCereals_IdealDietForPeopleWithHealthRisks varchar, MinmCereals_ToknowmoregotoFWItips varchar, nvCereals_Servings_equals varchar, nvCereals_BasicIndianDiet varchar, nvCereals_OptimizedIndiandiet varchar, nvCereals_IdealDietForPeopleWithHealthRisks varchar, nvCereals_ToknowmoregotoFWItips varchar, SoyaCereals_Servings_equals varchar, SoyaCereals_BasicIndianDiet varchar, SoyaCereals_OptimizedIndiandiet varchar, SoyaCereals_IdealDietForPeopleWithHealthRisks varchar, SoyaCereals_ToknowmoregotoFWItips varchar, NutsCereals_Servings_equals varchar, NutsaCereals_BasicIndianDiet varchar, NutsCereals_OptimizedIndiandiet varchar, NutsCereals_IdealDietForPeopleWithHealthRisks varchar, NutsCereals_ToknowmoregotoFWItips varchar," +
					"MilkCereals_Servings_equals varchar, MilkCereals_BasicIndianDiet varchar, MilkCereals_OptimizedIndiandiet varchar, MilkCereals_IdealDietForPeopleWithHealthRisks varchar, MilksCereals_ToknowmoregotoFWItips varchar, vegCereals_Servings_equals varchar, vegCereals_BasicIndianDiet varchar, vegCereals_OptimizedIndiandiet varchar, vegCereals_IdealDietForPeopleWithHealthRisks varchar, vegCereals_ToknowmoregotoFWItips varchar, FruitsCereals_Servings_equals varchar, FruitsCereals_BasicIndianDiet varchar, FruitsCereals_OptimizedIndiandiet varchar, FruitsCereals_IdealDietForPeopleWithHealthRisks varchar, FruitsCereals_ToknowmoregotoFWItips varchar, OilsCereals_Servings_equals varchar, OilsCereals_BasicIndianDiet varchar, OilsCereals_OptimizedIndiandiet varchar, OilsCereals_IdealDietForPeopleWithHealthRisks varchar, OilsCereals_ToknowmoregotoFWItips varchar)");

        db.execSQL("create table logFood "+"(ids INTEGER PRIMARY KEY AUTOINCREMENT, foodname varchar, indianname varchar, foodcategory varchar, rawcooked varchar, unit varchar, weightms varchar, energy varchar, protein varchar, fat varchar, carbs varchar, sugar varchar, fibre varchar, satfat varchar, cholestrol varchar,sodium varchar,percentage varchar,std_drink varchar,volume varchar)");


		//Toast.makeText(context, "Create table"+db+"", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



		db.execSQL("DROP TABLE IF EXISTS " + TableName);
		db.execSQL("DROP TABLE IF EXISTS " + TableFood);
		db.execSQL("DROP TABLE IF EXISTS " + TableFoodData);
		db.execSQL("DROP TABLE IF EXISTS " + TableDietPlan);
		db.execSQL("DROP TABLE IF EXISTS " + TableAlchol);
		db.execSQL("DROP TABLE IF EXISTS " + TableBasicDietPlan);
		db.execSQL("DROP TABLE IF EXISTS " + "logFood" );

		onCreate(db);



    }

}
