package com.hwi.health.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hwi.health.Models.Alchohal_tracker_model;

import java.util.ArrayList;
import java.util.List;

public class ProductController {

	Context context;

	public ProductController(Context context) {
		this.context = context;
	}

	public boolean addProduct(Alchohal_tracker_model p) {
		Cursor cursor = null;
		Boolean ret = false;
		DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
				DBHelper.Version);

		SQLiteDatabase db = helper.getWritableDatabase();

		try{
			cursor = db.rawQuery("SELECT * FROM " + DBHelper.TableName + " WHERE id = ?", new String[]{p.getId() + ""});

			if(cursor.getCount() == 0){
				cursor.close();

				ContentValues values = new ContentValues();
				values.put("first_type", p.getFirst_type());
				values.put("name",  p.getName());
				values.put("quantity", p.getQuantity());
				values.put("Calories",p.getCalories());
				values.put("Std_size_drinks", p.getStd_size_drinks());
				values.put("Carbs", p.getCarbs());
				values.put("Sugar", p.getSugar());
				values.put("Sodium", p.getSodium());
				values.put("numbers", p.getNumbers());
				values.put("id", p.getId());

				db.insert(DBHelper.TableName, null, values);

				ret = true;
			}else {

				ret = false;
			}
		}catch (Exception e){
			Log.e("Error",e+"");
		}
		return ret;
	}

	public boolean removeProduct(String id) {
		Cursor cursor = null;
		Boolean ret = false;
		DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
				DBHelper.Version);
		SQLiteDatabase db = helper.getWritableDatabase();

		try{
			cursor = db.rawQuery("SELECT * FROM " + DBHelper.TableName + " WHERE id = ?", new String[]{id + ""});

			if(cursor.getCount() == 0){
				cursor.close();
				ret = true;
			}else {
				db.delete(DBHelper.TableName ," id = ?",new String[]{ String.valueOf(id) });
				ret = false;
			}
		}catch (Exception e){
			Log.e("Error",e+"");
		}

		return ret;
	}

	public void trancate(){

		try{
			DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
					DBHelper.Version);
			SQLiteDatabase db = helper.getWritableDatabase();
			db.execSQL("delete from "+ DBHelper.TableName);
		}catch (Exception e){

		}

	}

	public boolean updateProduct(Alchohal_tracker_model p) {

		DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
				DBHelper.Version);
		SQLiteDatabase db = helper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("first_type", p.getFirst_type());
		values.put("name", p.getName());
		values.put("quantity", p.getQuantity());
		values.put("Calories", p.getCalories());
		values.put("Std_size_drinks", p.getStd_size_drinks());
		values.put("Carbs", p.getCarbs());
		values.put("Sugar", p.getSugar());
		values.put("Sodium", p.getSodium());
		values.put("numbers", p.getNumbers());
		values.put("id", p.getId());


		db.update(DBHelper.TableName , values, " id = ?",new String[]{ String.valueOf(p.getId()) });
		return true;
	}

	public Alchohal_tracker_model searchProduct(int id) {

		return null;
	}

	public List<Alchohal_tracker_model> getAllProduct() {
		List<Alchohal_tracker_model> plists = new ArrayList<Alchohal_tracker_model>();

		DBHelper helper = new DBHelper(context, DBHelper.DataBaseName, null,
				DBHelper.Version);

		SQLiteDatabase db = helper.getWritableDatabase();

		Cursor cr = db.rawQuery("select * from " + DBHelper.TableName, null);

		while (cr.moveToNext() == true) {
			String first_type = cr.getString(1);
			String name= cr.getString(2);
			String quantity= cr.getString(3);
			String Calories= cr.getString(4);
			String Std_size_drinks= cr.getString(5);
			String Carbs= cr.getString(6);
			String Sugar= cr.getString(7);
			String Sodium= cr.getString(8);
			String numbers= cr.getString(9);
			String id= cr.getString(10);

		//	category,description,sub_category,shipping_cost,featured,tag,status,brand,color

			plists.add(new Alchohal_tracker_model(first_type,  name,  quantity, Calories, Std_size_drinks, Carbs, Sugar, Sodium,  numbers,id));

		}
		return plists;
	}

}
