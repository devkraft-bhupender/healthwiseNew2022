package com.hwi.health.sqlite_database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hwi.health.Models.Alchohal_tracker_model;
import com.hwi.health.Models.DietLog_Models;
import com.hwi.health.Models.Eat_out_Beverage_models;
import com.hwi.health.Models.Eat_out_Plan_models;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by julee on 15-11-2017.
 */

public class TestAdapter {

    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataHelper mDbHelper;

    public TestAdapter(Context context) throws IOException {
        this.mContext = context;
        mDbHelper = new DataHelper(mContext);
    }

    public TestAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public TestAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

   /* public ArrayList<DietLog_Models> getlogFood() {

        ArrayList<DietLog_Models> list2 = new ArrayList<>();

        try {
            String sql = "SELECT * FROM logFood";
            Cursor testdata = mDb.rawQuery(sql, null);

            while (testdata.moveToNext() == true) {

                String id = testdata.getString(0);
                String foodname = testdata.getString(1);
                String Indian_name = testdata.getString(2);
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

                DietLog_Models models = new DietLog_Models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, "1", cholestrol, sodium);
                list2.add(models);

            }

            return list2;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }*/

    public ArrayList<DietLog_Models> getlogFood() {

        ArrayList<DietLog_Models> list2 = new ArrayList<>();

        try {

            String sql1 = "SELECT * FROM logFood  WHERE  rawcooked = 'Everyday meal'";
            Cursor testdata = mDb.rawQuery(sql1, null);


            while (testdata.moveToNext() == true) {

                String id = testdata.getString(0);
                String foodname = testdata.getString(1);
                String Indian_name = testdata.getString(2);
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

            }

            String sql2 = "SELECT * FROM logFood  WHERE  rawcooked = 'Meal'";

            Cursor testdata2 = mDb.rawQuery(sql2, null);


            while (testdata2.moveToNext() == true) {

                String id = testdata2.getString(0);
                String foodname = testdata2.getString(1);
                String Indian_name = testdata2.getString(2);
                String Major_food_category = testdata2.getString(3);
                String Raw_cooked = testdata2.getString(4);
                String unit_notes = testdata2.getString(5);
                String Weight_in_ms = testdata2.getString(6);
                String energy = testdata2.getString(7);
                String protein = testdata2.getString(8);
                String fat = testdata2.getString(9);
                String Carbs = testdata2.getString(10);
                String Sugar = testdata2.getString(11);
                String fibre = testdata2.getString(12);
                String sat_fat = testdata2.getString(13);
                String cholestrol = testdata2.getString(14);
                String sodium = testdata2.getString(15);

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

            }

            String sql3 = "SELECT * FROM logFood  WHERE  rawcooked = 'Ingredient'";

            Cursor testdata3 = mDb.rawQuery(sql3, null);


            while (testdata3.moveToNext() == true) {

                String id = testdata3.getString(0);
                String foodname = testdata3.getString(1);
                String Indian_name = testdata3.getString(2);
                String Major_food_category = testdata3.getString(3);
                String Raw_cooked = testdata3.getString(4);
                String unit_notes = testdata3.getString(5);
                String Weight_in_ms = testdata3.getString(6);
                String energy = testdata3.getString(7);
                String protein = testdata3.getString(8);
                String fat = testdata3.getString(9);
                String Carbs = testdata3.getString(10);
                String Sugar = testdata3.getString(11);
                String fibre = testdata3.getString(12);
                String sat_fat = testdata3.getString(13);
                String cholestrol = testdata3.getString(14);
                String sodium = testdata3.getString(15);

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

            }


            return list2;

        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<Alchohal_tracker_model> Cocktails() {
        ArrayList<Alchohal_tracker_model> alist = new ArrayList<>();
        try {
            String sql = "SELECT * FROM logFood WHERE indianname = 'Cocktails' AND foodcategory = 'Alcoholic'";

            Cursor testdata = mDb.rawQuery(sql, null);

            while (testdata.moveToNext() == true) {

                String id = testdata.getString(0);
                String foodname = testdata.getString(1);
                String Indian_name = testdata.getString(2);
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
                alist.add(new Alchohal_tracker_model(Major_food_category, foodname, unit_notes, "0", sat_fat, Carbs, Sugar, "0", "1", id));
            }
            return alist;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<String> getMake() {
        ArrayList<String> flist = new ArrayList<>();
        try {
           String sql = "SELECT distinct(rawcooked) FROM logFood";
            // String sql = "SELECT distinct(foodcategory) FROM logFood WHERE rawcooked = 'Everyday meal' OR rawcooked = 'Meal'";
            Cursor mCur = mDb.rawQuery(sql, null);
            while (mCur.moveToNext() == true) {
                String make = mCur.getString(0).trim();
                flist.add(make);
            }
            return flist;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<String> getMakeCategories() {
        ArrayList<String> flist = new ArrayList<>();
        try {
            String sql = "SELECT distinct(foodcategory) FROM logFood";
            // String sql = "SELECT distinct(foodcategory) FROM logFood WHERE rawcooked = 'Everyday meal' OR rawcooked = 'Meal'";
            Cursor mCur = mDb.rawQuery(sql, null);
            while (mCur.moveToNext() == true) {
                String make = mCur.getString(0).trim();
                flist.add(make);
            }
            return flist;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<Eat_out_Plan_models> getlogFood2(String selection) {
        ArrayList<Eat_out_Plan_models> list_food = new ArrayList<>();

        String sql = "SELECT * FROM logFood where rawcooked = ?";
        Cursor testdata = mDb.rawQuery(sql, new String[]{selection});
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
            Eat_out_Plan_models models = new Eat_out_Plan_models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, cholestrol_bev, sodium_bev,"1.00");
            list_food.add(models);

        }
        return list_food;
    }

    public ArrayList<Eat_out_Beverage_models> getBaverage() {
        ArrayList<Eat_out_Beverage_models> list_bevg = new ArrayList<>();
        try {
            String sql = "SELECT * FROM logFood WHERE foodcategory = '7 UP' OR foodcategory = 'Alcoholic' OR foodcategory = 'Beverages' OR foodcategory = 'Energy bar' OR foodcategory = 'Fanta' OR foodcategory = 'Frooti' OR foodcategory = 'Juice' OR foodcategory = 'Limca' OR foodcategory = 'Lipton' OR foodcategory = 'Mazza' OR foodcategory = 'mineral water' OR foodcategory = 'Minute maid' OR foodcategory = 'Mirinda' OR foodcategory = 'Pepsi' OR foodcategory = 'Sprite' OR foodcategory = 'Starbucks' OR foodcategory = 'Tang' OR foodcategory = 'Tea' OR foodcategory = 'Thums Up' OR foodcategory = 'Tropicana'";

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

}

   /* public ArrayList<DietLog_Models> */


/*  public Cursor getMake(){
        try
        {
            String sql ="SELECT distinct(foodcategory) FROM logFood";

            Cursor mCur = mDb.rawQuery(sql, null);

            return mCur;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }*/









/*public ArrayList<DietLog_Models> getlogFood() {

        ArrayList<DietLog_Models> list2 = new ArrayList<>();

        try {

            String sql = "SELECT * FROM logFood where ";
            Cursor testdata = mDb.rawQuery(sql, null);

            while (testdata.moveToNext() == true) {

                String id = testdata.getString(0);
                String foodname = testdata.getString(1);
                String Indian_name = testdata.getString(2);
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

                DietLog_Models models = new DietLog_Models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, "1", cholestrol, sodium);
                list2.add(models);

            }

            return list2;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }*/