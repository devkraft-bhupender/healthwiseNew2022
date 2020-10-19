package com.hwi.health.sqlite_database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.hwi.health.Models.Alchohal_tracker_model;
import com.hwi.health.Models.DietLog_Models;
import com.hwi.health.Models.IdealDietPlanDBModel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductController_For_All {
    Context context;

    public ProductController_For_All(Context context) {

        this.context = context;
    }

    public boolean addProductLog(DietLog_Models p) {
        Cursor cursor = null;
        Boolean ret = false;
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            cursor = db.rawQuery("SELECT * FROM " + DBHelper.TableFood + " WHERE foodname = ?", new String[]{p.getFoodname() + ""});

            if (cursor.getCount() == 0) {
                cursor.close();

                Log.e("dataytsdklhsdfhxzdfjk", p.getFoodname() + "");
                ContentValues values = new ContentValues();
                values.put("foodname", p.getFoodname());
                values.put("Indian_name", p.getIndian_name());
                values.put("Major_food_category", p.getMajor_food_category());
                values.put("Raw_cooked", p.getRaw_cooked());
                values.put("unit_notes", p.getUnit_notes());
                values.put("Weight_in_ms", p.getWeight_in_ms());
                values.put("energy", p.getEnergy());
                values.put("protein", p.getProtein());
                values.put("fat", p.getFat());
                values.put("Carbs", p.getCarbs());
                values.put("Sugar", p.getSugar());
                values.put("fibre", p.getFibre());
                values.put("sat_fat", p.getSat_fat());
                values.put("chole", p.getCholestrol());
                values.put("sodium", p.getSodium());
                values.put("numbers", p.getNumbers());

                db.insert(DBHelper.TableFood, null, values);

//				db.execSQL("insert into " + DBHelper.TableFood + " values('"
//						+ p.getFoodname() + "','" + p.getIndian_name() + "','" + p.getMajor_food_category() + "','"
//						+ p.getRaw_cooked()+ "','" + p.getUnit_notes() + "','" +p.getWeight_in_ms()+ "','"
//						+p.getEnergy()+ "','" +p.getProtein()+ "','" + p.getFat()+"','" + p.getCarbs()+"','" + p.getSugar()+"','" + p.getFibre()+"','" + p.getSat_fat()+"')");

                ret = true;
            } else {

                ret = false;
            }
        } catch (Exception e) {
            Log.e("Error", e + "");
        }
        return ret;
    }


    public boolean addIdealDietPlan(IdealDietPlanDBModel p) {
        Cursor cursor = null;
        Boolean ret = false;
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            cursor = db.rawQuery("SELECT * FROM " + DBHelper.TableFood + " WHERE user_id = ?", new String[]{p.getUserid() + ""});

            if (cursor.getCount() == 0) {
                cursor.close();




                ContentValues values = new ContentValues();
                values.put("user_id", p.getUserid());
                values.put("GrainsCereals_Servings_equals", p.getGrainsCereals_Servings_equals());
                values.put("GrainsCereals_BasicIndianDiet", p.getGrainsCereals_BasicIndianDiet());
                values.put("GrainsCereals_OptimizedIndiandiet", p.getGrainsCereals_OptimizedIndiandiet());
                values.put("GrainsCereals_IdealDietForPeopleWithHealthRisks", p.getGrainsCereals_IdealDietForPeopleWithHealthRisks());
                values.put("GrainsCereals_ToknowmoregotoFWItips", p.getGrainsCereals_ToknowmoregotoFWItips());
                values.put("DalCereals_Servings_equals", p.getDalCereals_Servings_equals());
                values.put("DalCereals_BasicIndianDiet", p.getDalCereals_BasicIndianDiet());
                values.put("DalCereals_OptimizedIndiandiet", p.getDalCereals_OptimizedIndiandiet());
                values.put("DalCereals_IdealDietForPeopleWithHealthRisks", p.getDalCereals_IdealDietForPeopleWithHealthRisks());
                values.put("DalCereals_ToknowmoregotoFWItips", p.getDalCereals_ToknowmoregotoFWItips());
                values.put("MaxmCereals_Servings_equals", p.getMaxmCereals_Servings_equals());
                values.put("MaxmCereals_BasicIndianDiet", p.getMaxmCereals_BasicIndianDiet());
                values.put("MaxmCereals_OptimizedIndiandiet", p.getMaxmCereals_OptimizedIndiandiet());
                values.put("MaxmCereals_IdealDietForPeopleWithHealthRisks", p.getMaxmCereals_IdealDietForPeopleWithHealthRisks());
                values.put("MaxmCereals_ToknowmoregotoFWItips", p.getMaxmCereals_ToknowmoregotoFWItips());
                values.put("MinmCereals_Servings_equals", p.getMinmCereals_Servings_equals());
                values.put("MinmCereals_BasicIndianDiet", p.getMinmCereals_BasicIndianDiet());
                values.put("MinmCereals_OptimizedIndiandiet", p.getMinmCereals_OptimizedIndiandiet());
                values.put("MinmCereals_IdealDietForPeopleWithHealthRisks", p.getMinmCereals_IdealDietForPeopleWithHealthRisks());
                values.put("MinmCereals_ToknowmoregotoFWItips", p.getMinmCereals_ToknowmoregotoFWItips());
                values.put("nvCereals_Servings_equals", p.getNvCereals_Servings_equals());
                values.put("nvCereals_BasicIndianDiet", p.getNvCereals_BasicIndianDiet());
                values.put("nvCereals_OptimizedIndiandiet", p.getNvCereals_OptimizedIndiandiet());
                values.put("nvCereals_IdealDietForPeopleWithHealthRisks", p.getNvCereals_IdealDietForPeopleWithHealthRisks());
                values.put("nvCereals_ToknowmoregotoFWItips", p.getNvCereals_ToknowmoregotoFWItips());
                values.put("SoyaCereals_Servings_equals", p.getSoyaCereals_Servings_equals());
                values.put("SoyaCereals_BasicIndianDiet", p.getSoyaCereals_BasicIndianDiet());
                values.put("SoyaCereals_OptimizedIndiandiet", p.getSoyaCereals_OptimizedIndiandiet());

                values.put("SoyaCereals_IdealDietForPeopleWithHealthRisks", p.getSoyaCereals_IdealDietForPeopleWithHealthRisks());
                values.put("SoyaCereals_ToknowmoregotoFWItips", p.getSoyaCereals_ToknowmoregotoFWItips());
                values.put("NutsCereals_Servings_equals", p.getNutsCereals_Servings_equals());
                values.put("NutsaCereals_BasicIndianDiet", p.getNutsaCereals_BasicIndianDiet());
                values.put("NutsCereals_OptimizedIndiandiet", p.getNutsCereals_OptimizedIndiandiet());
                values.put("NutsCereals_IdealDietForPeopleWithHealthRisks", p.getNutsCereals_IdealDietForPeopleWithHealthRisks());

                values.put("MilkCereals_Servings_equals", p.getMilkCereals_Servings_equals());
                values.put("MilkCereals_BasicIndianDiet", p.getMilkCereals_BasicIndianDiet());
                values.put("MilkCereals_OptimizedIndiandiet", p.getMilkCereals_OptimizedIndiandiet());
                values.put("MilkCereals_IdealDietForPeopleWithHealthRisks", p.getMilkCereals_IdealDietForPeopleWithHealthRisks());
                values.put("MilksCereals_ToknowmoregotoFWItips", p.getMilksCereals_ToknowmoregotoFWItips());
                values.put("vegCereals_Servings_equals", p.getVegCereals_Servings_equals());
                values.put("vegCereals_BasicIndianDiet", p.getVegCereals_BasicIndianDiet());
                values.put("vegCereals_OptimizedIndiandiet", p.getVegCereals_OptimizedIndiandiet());
                values.put("vegCereals_IdealDietForPeopleWithHealthRisks", p.getVegCereals_IdealDietForPeopleWithHealthRisks());
                values.put("vegCereals_ToknowmoregotoFWItips", p.getVegCereals_ToknowmoregotoFWItips());
                values.put("FruitsCereals_Servings_equals", p.getFruitsCereals_Servings_equals());
                values.put("FruitsCereals_BasicIndianDiet", p.getFruitsCereals_BasicIndianDiet());
                values.put("FruitsCereals_OptimizedIndiandiet", p.getFruitsCereals_OptimizedIndiandiet());
                values.put("FruitsCereals_IdealDietForPeopleWithHealthRisks", p.getFruitsCereals_IdealDietForPeopleWithHealthRisks());
                values.put("FruitsCereals_ToknowmoregotoFWItips", p.getFruitsCereals_ToknowmoregotoFWItips());
                values.put("OilsCereals_Servings_equals", p.getOilsCereals_Servings_equals());
                values.put("OilsCereals_BasicIndianDiet", p.getOilsCereals_BasicIndianDiet());
                values.put("OilsCereals_OptimizedIndiandiet", p.getOilsCereals_OptimizedIndiandiet());
                values.put("OilsCereals_IdealDietForPeopleWithHealthRisks", p.getOilsCereals_IdealDietForPeopleWithHealthRisks());
                values.put("OilsCereals_ToknowmoregotoFWItips", p.getOilsCereals_ToknowmoregotoFWItips());

                db.insert(DBHelper.TableFood_Ideal_diet_plan, null, values);

//				db.execSQL("insert into " + DBHelper.TableFood + " values('"
//						+ p.getFoodname() + "','" + p.getIndian_name() + "','" + p.getMajor_food_category() + "','"
//						+ p.getRaw_cooked()+ "','" + p.getUnit_notes() + "','" +p.getWeight_in_ms()+ "','"
//						+p.getEnergy()+ "','" +p.getProtein()+ "','" + p.getFat()+"','" + p.getCarbs()+"','" + p.getSugar()+"','" + p.getFibre()+"','" + p.getSat_fat()+"')");

                ret = true;
            } else {

                ret = false;
            }
        } catch (Exception e) {
            Log.e("Error", e + "");
        }
        return ret;
    }

    public boolean addFoodData_Preview(String foodname, String eng, String prot, String fatt, String car, String sug, String fib, String sat, String total_souium, String total_cholestrol, String datee, String user_idd, String foodkey) {
        Cursor cursor = null;
        Boolean ret = false;
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put("namefood", foodname);
            values.put("Energy", eng);
            values.put("Protein", prot);
            values.put("Fat", fatt);
            values.put("carbs", car);
            values.put("sugar", sug);
            values.put("Fibre", fib);
            values.put("Sat_fat", sat);
            values.put("sodium", total_souium);
            values.put("chole", total_cholestrol);
            values.put("date_", datee);
            values.put("user_id", user_idd);
            values.put("food_key", foodkey);
            db.insert(DBHelper.TableFoodData, null, values);

            ret = true;

        } catch (Exception e) {
            Log.e("Error", e + "");
        }
        return ret;
    }

    public boolean addFoodData(String calories,String foodname, String eng, String prot, String fatt, String car, String sug, String fib, String sat, String total_souium, String total_cholestrol, String datee, String user_idd, String foodkey) {
        Cursor cursor = null;
        Boolean ret = false;
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put("calories", calories);
            values.put("namefood", foodname);
            values.put("Energy", eng);
            values.put("Protein", prot);
            values.put("Fat", fatt);
            values.put("carbs", car);
            values.put("sugar", sug);
            values.put("Fibre", fib);
            values.put("Sat_fat", sat);
            values.put("sodium", total_souium);
            values.put("chole", total_cholestrol);
            values.put("date_", datee);
            values.put("user_id", user_idd);
            values.put("food_key", foodkey);
            db.insert(DBHelper.TableFoodData, null, values);

            ret = true;

        } catch (Exception e) {
            Log.e("Error", e + "");
        }
        return ret;
    }

    public ArrayList<DietLog_Models> getFoodData(String user_id, String _select_dite_type, String strDate, String endDate) {
        Cursor cr = null;
        ArrayList<DietLog_Models> alist = new ArrayList<>();
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);


        // cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE user_id = ?", new String[]{user_id + ""});

        SQLiteDatabase db = helper.getWritableDatabase();


        //     cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ BETWEEN ? AND ? AND user_id = ?", new String[]{cdate,todate,user_id});


        if (_select_dite_type.equals("0")) {

            Log.e("query",": "+"SELECT * FROM " + DBHelper.TableFoodData + " WHERE user_id = "+user_id+" ORDER BY ids DESC LIMIT 1");
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE user_id = ? ORDER BY ids DESC LIMIT 1", new String[]{user_id + ""});

        }else if (_select_dite_type.equals("1")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ >= DATETIME('now', '-1 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        } else if (_select_dite_type.equals("2")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ >= DATETIME('now', '-7 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        } else if (_select_dite_type.equals("3")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ >= DATETIME('now', '-31 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        } else if (_select_dite_type.equals("4")) {
//            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ = ? AND user_id = ?  ORDER BY ids DESC", new String[]{strDate, user_id + ""});
            try {
                Date one = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
                Date two = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

                if (one.before(two)) {
                    cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_  BETWEEN ? AND ? AND user_id = ?  ORDER BY ids DESC", new String[]{strDate, endDate, user_id + ""});
                } else if (one.before(two)) {
                    cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_  BETWEEN ? AND ? AND user_id = ?  ORDER BY ids DESC", new String[]{strDate, endDate, user_id + ""});
                } else {
                    cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_  BETWEEN ? AND ? AND user_id = ?  ORDER BY ids DESC", new String[]{endDate, strDate, user_id + ""});
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (_select_dite_type.equals("5")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE  user_id = ? ORDER BY ids DESC LIMIT 1", new String[]{user_id + ""});
        } else {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ >= DATETIME('now', '-1000 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        }

        while (cr.moveToNext() == true) {
            String name = cr.getString(1);
            String colories = cr.getString(3);
            String protine = cr.getString(4);
            String fat = cr.getString(5);
            String carbs = cr.getString(6);
            String sugar = cr.getString(7);
            String fiber = cr.getString(8);
            String sat_fat = cr.getString(9);
            String chole = cr.getString(10);
            String sodium = cr.getString(11);
            String date = cr.getString(12);
            String food_type = cr.getString(14);

//            float pro = Float.parseFloat(protine);
//            float ft = Float.parseFloat(fat);
//            float car = Float.parseFloat(carbs);
//
//            float Carbs_mul = car * 3.2f;
//            float Protein_mul = pro * 4.0f;
//            float Fat_mul = ft * 9.0f;
//
//            float calories_cal = Carbs_mul + Protein_mul + Fat_mul;
            alist.add(new DietLog_Models(name, colories,protine, fat, carbs, sugar, fiber, sat_fat, chole, sodium,  "", date, food_type));
        }

        return alist;

    }

    public ArrayList<DietLog_Models> getFoodDatadietlog(String user_id, String _select_dite_type, String strDate, String endDate,int insertedelement) {
        Cursor cr = null;
        ArrayList<DietLog_Models> alist = new ArrayList<>();
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);


        // cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE user_id = ?", new String[]{user_id + ""});

        SQLiteDatabase db = helper.getWritableDatabase();


        //     cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ BETWEEN ? AND ? AND user_id = ?", new String[]{cdate,todate,user_id});


        if (_select_dite_type.equals("0")) {

            Log.e("query",": "+"SELECT * FROM " + DBHelper.TableFoodData + " WHERE user_id = "+user_id+" ORDER BY ids DESC LIMIT 1");
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE user_id = ? ORDER BY ids DESC LIMIT 1", new String[]{user_id + ""});

        }else if (_select_dite_type.equals("1")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ >= DATETIME('now', '-1 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        } else if (_select_dite_type.equals("2")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ >= DATETIME('now', '-7 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        } else if (_select_dite_type.equals("3")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ >= DATETIME('now', '-31 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        } else if (_select_dite_type.equals("4")) {
//            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ = ? AND user_id = ?  ORDER BY ids DESC", new String[]{strDate, user_id + ""});
            try {
                Date one = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
                Date two = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

                if (one.before(two)) {
                    cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_  BETWEEN ? AND ? AND user_id = ?  ORDER BY ids DESC", new String[]{strDate, endDate, user_id + ""});
                } else if (one.before(two)) {
                    cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_  BETWEEN ? AND ? AND user_id = ?  ORDER BY ids DESC", new String[]{strDate, endDate, user_id + ""});
                } else {
                    cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_  BETWEEN ? AND ? AND user_id = ?  ORDER BY ids DESC", new String[]{endDate, strDate, user_id + ""});
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (_select_dite_type.equals("5")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE  user_id = ? ORDER BY ids DESC LIMIT ?", new String[]{user_id , insertedelement  + ""});
        } else {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableFoodData + " WHERE date_ >= DATETIME('now', '-1000 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        }

        while (cr.moveToNext() == true) {
            String name = cr.getString(1);
            String colories = cr.getString(3);
            String protine = cr.getString(4);
            String fat = cr.getString(5);
            String carbs = cr.getString(6);
            String sugar = cr.getString(7);
            String fiber = cr.getString(8);
            String sat_fat = cr.getString(9);
            String chole = cr.getString(10);
            String sodium = cr.getString(11);
            String date = cr.getString(12);
            String food_type = cr.getString(14);

//            float pro = Float.parseFloat(protine);
//            float ft = Float.parseFloat(fat);
//            float car = Float.parseFloat(carbs);
//
//            float Carbs_mul = car * 3.2f;
//            float Protein_mul = pro * 4.0f;
//            float Fat_mul = ft * 9.0f;
//
//            float calories_cal = Carbs_mul + Protein_mul + Fat_mul;
            alist.add(new DietLog_Models(name, colories,protine, fat, carbs, sugar, fiber, sat_fat, chole, sodium,  "", date, food_type));
        }

        return alist;

    }

    /////////////////Alchol result save////////////////

    public boolean SaveAlchol(String food_data, String current_date, String calories, String carbs, String sugar, String sodium, String Sat_size, String units, String user_id) {
        Cursor cursor = null;
        Boolean ret = false;
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();

//        Date date = new Date();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//       String  current_date = df.format(date);

        try {
            db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + "", null);

            ContentValues values = new ContentValues();
            values.put("calories", calories);
            values.put("carbs", carbs);
            values.put("sugar", sugar);
            values.put("sodium", sodium);
            values.put("Sat_size", Sat_size);
            values.put("units", units);
            values.put("date_", current_date);
            values.put("food_data", food_data);
            values.put("user_id", user_id);

            db.insert(DBHelper.TableAlchol, null, values);

            ret = true;

        } catch (Exception e) {
            Log.e("Error", e + "");
        }
        return ret;
    }

    public List<Alchohal_tracker_model> getMyAlcho(String user_id, String _select_dite_type, String strDate, String endDate) {
        List<Alchohal_tracker_model> plists = new ArrayList<Alchohal_tracker_model>();
        Cursor cr = null;

            DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();

        //   Cursor cr = db.rawQuery("select * from " + DBHelper.TableAlchol, null);

        if (_select_dite_type.equals("1")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + " WHERE date_ >= DATETIME('now', '-1 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        } else if (_select_dite_type.equals("2")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + " WHERE date_ >= DATETIME('now', '-7 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        } else if (_select_dite_type.equals("3")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + " WHERE date_ >= DATETIME('now', '-31 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        } else if (_select_dite_type.equals("4")) {
//            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + " WHERE date_ = ? AND user_id = ?  ORDER BY ids DESC", new String[]{strDate, user_id + ""});

            try {
                Date one = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
                Date two = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

                if (one.before(two)) {
                    cr = db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + " WHERE date_  BETWEEN ? AND ? AND user_id = ?  ORDER BY ids DESC", new String[]{strDate, endDate, user_id + ""});
                } else if (one.before(two)) {
                    cr = db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + " WHERE date_  BETWEEN ? AND ? AND user_id = ?  ORDER BY ids DESC", new String[]{strDate, endDate, user_id + ""});
                } else {
                    cr = db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + " WHERE date_  BETWEEN ? AND ? AND user_id = ?  ORDER BY ids DESC", new String[]{endDate, strDate, user_id + ""});
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (_select_dite_type.equals("5")) {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + " WHERE  user_id = ? ORDER BY ids DESC LIMIT 1", new String[]{user_id + ""});
        } else {
            cr = db.rawQuery("SELECT * FROM " + DBHelper.TableAlchol + " WHERE date_ >= DATETIME('now', '-1000 day') AND user_id = ?  ORDER BY ids DESC", new String[]{user_id + ""});
        }


        while (cr.moveToNext() == true) {
            String calories = cr.getString(1);
            String carbs = cr.getString(2);
            String sugar = cr.getString(3);
            String sodium = cr.getString(4);
            String standerd_size = cr.getString(5);
            String unit = cr.getString(6);
            String date = cr.getString(7);
            String name = cr.getString(9);

            plists.add(new Alchohal_tracker_model(unit, calories, standerd_size, carbs, sugar, sodium, date, name));

        }
        return plists;


    }

////////////////// DietPlan Result save //////////////////////

    public boolean addDietPlan(String userid, String result_json) {
        Cursor cursor = null;
        Boolean ret = false;
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();

        try {

            cursor = db.rawQuery("SELECT * FROM " + DBHelper.TableDietPlan + " WHERE user_id = ?", new String[]{userid + ""});

            if (cursor.getCount() == 0) {
                cursor.close();

                ContentValues values = new ContentValues();
                values.put("user_id", userid);
                values.put("diet_result", result_json);

                db.insert(DBHelper.TableDietPlan, null, values);
                ret = true;

            } else {

                ret = false;
            }

        } catch (Exception e) {
            Log.e("Error", e + "");
        }
        return ret;

    }

    public String getDietPlan(String user_id) {
        Cursor cr = null;
        String get_json = "";
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();
        cr = db.rawQuery("SELECT * FROM " + DBHelper.TableDietPlan + " WHERE user_id = ?", new String[]{user_id + ""});

        while (cr.moveToNext() == true) {
            String id = cr.getString(1);
            get_json = cr.getString(2);

        }

        return get_json;

    }

    ///////////////Basic_Diet_paln_oil////////////

    public boolean add_basic_oil(String user_id, String basic_oil) {
        Cursor cursor = null;
        Boolean ret = false;
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();
        try {

            cursor = db.rawQuery("SELECT * FROM " + DBHelper.TableBasicDietPlan + " WHERE user_id = ?", new String[]{user_id + ""});

            if (cursor.getCount() == 0) {
                cursor.close();

                ContentValues values = new ContentValues();
                values.put("user_id", user_id);
                values.put("basic_diet_oil", basic_oil);

                db.insert(DBHelper.TableBasicDietPlan, null, values);
                ret = true;

            } else {

                ret = false;
            }

        } catch (Exception e) {
            Log.e("Error", e + "");
        }
        return ret;

    }

    public String getBaiscDietPlan_oil(String user_id) {
        Cursor cr = null;
        String get_oil = "";
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();
        cr = db.rawQuery("SELECT * FROM " + DBHelper.TableBasicDietPlan + " WHERE user_id = ?", new String[]{user_id + ""});

        while (cr.moveToNext() == true) {
            String id = cr.getString(1);
            get_oil = cr.getString(2);

        }

        return get_oil;

    }

    ////////////////////
    public boolean removeProductLOg(String foodname) {
        Cursor cursor = null;
        Boolean ret = false;
        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            cursor = db.rawQuery("SELECT * FROM " + DBHelper.TableFood + " WHERE foodname = ?", new String[]{foodname + ""});

            if (cursor.getCount() == 0) {
                cursor.close();
                ret = true;
            } else {
                db.delete(DBHelper.TableFood, " foodname = ?", new String[]{String.valueOf(foodname)});
                ret = false;
            }
        } catch (Exception e) {
            Log.e("Error", e + "");
        }

        return ret;
    }

    public void trancate() {

        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from " + DBHelper.TableFood);
    }

    public boolean updateProduct2(DietLog_Models p) {

        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("foodname", p.getFoodname());
        values.put("Indian_name", p.getIndian_name());
        values.put("Major_food_category", p.getMajor_food_category());
        values.put("Raw_cooked", p.getRaw_cooked());
        values.put("unit_notes", p.getUnit_notes());
        values.put("Weight_in_ms", p.getWeight_in_ms());
        values.put("energy", p.getEnergy());
        values.put("protein", p.getProtein());
        values.put("fat", p.getFat());
        values.put("Carbs", p.getCarbs());
        values.put("Sugar", p.getSugar());
        values.put("fibre", p.getFibre());
        values.put("sat_fat", p.getSat_fat());
        values.put("chole", p.getCholestrol());
        values.put("sodium", p.getSodium());
        values.put("numbers", p.getNumbers());


        db.update(DBHelper.TableFood, values, " foodname = ?", new String[]{String.valueOf(p.getFoodname())});
        return true;
    }

    public Alchohal_tracker_model searchProduct(int id) {

        return null;
    }

//	public boolean removeProduct(String product_id) {
//
//		DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
//				DBHelper.Version);
//		SQLiteDatabase db = helper.getWritableDatabase();
//
//		db.execSQL("delete from " + DBHelper.TableName2 + " where pid="
//				+ p.getProduct_id());
//
//		return true;
//	}


    public List<DietLog_Models> getAllProduct() {
        List<DietLog_Models> plists = new ArrayList<DietLog_Models>();

        DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
                DBHelper.Version);

        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cr = db.rawQuery("select * from " + DBHelper.TableFood, null);
        while (cr.moveToNext() == true) {
            String foodname = cr.getString(1);
            String Indian_name = cr.getString(2);
            String Major_food_category = cr.getString(3);
            String Raw_cooked = cr.getString(4);
            String unit_notes = cr.getString(5);
            String Weight_in_ms = cr.getString(6);
            String energy = cr.getString(7);
            String protein = cr.getString(8);
            String fat = cr.getString(9);
            String Carbs = cr.getString(10);
            String Sugar = cr.getString(11);
            String fibre = cr.getString(12);
            String sat_fat = cr.getString(13);
            String number = cr.getString(14);
            String chole = cr.getString(15);
            String soudi = cr.getString(16);


            float Carbs__get_food_item = Float.parseFloat(Carbs);
            float Protein__get_food_item = Float.parseFloat(protein);
            float Fat__get_food_item = Float.parseFloat(fat);

            float Carbs_mul = Carbs__get_food_item * 3.2f;
            float Protein_mul = Protein__get_food_item * 4.0f;
            float Fat_mul = Fat__get_food_item * 9.0f;

            float calories_cal = Carbs_mul + Protein_mul + Fat_mul;

            //	category,description,sub_category,shipping_cost,featured,tag,status,brand,color

            plists.add(new DietLog_Models(foodname, Indian_name, Major_food_category, Raw_cooked, unit_notes, Weight_in_ms, energy, protein, fat, Carbs, Sugar, fibre, sat_fat, number, calories_cal + "", soudi));

        }
        return plists;
    }


}
