package com.hwi.health.Activitys.Plans.Excercize_plans;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public class CalorieCalculator {

    public static boolean setPreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences("CalorieCalculator", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean writeCustomPlanAdult(Context context,String response)
    {
        SharedPreferences settings = context.getSharedPreferences("DietPlanAdult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("custom_diet_plan_adult", response);
         editor.commit();
         return true;
    }

    public static String getCustomPlanAdult(Context context)
    {
        SharedPreferences settings = context.getSharedPreferences("DietPlanAdult", Context.MODE_PRIVATE);
        return settings.getString("custom_diet_plan_adult", "defaultValue");

    }

    public static boolean writeIdealDietPlanAdult(Context context,String response)
    {
        SharedPreferences settings = context.getSharedPreferences("DietPlanAdult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("ideal_diet_plan_adult", response);
        editor.commit();
        return true;
    }

    public static String getIdealDietPlanAdult(Context context)
    {
        SharedPreferences settings = context.getSharedPreferences("DietPlanAdult", Context.MODE_PRIVATE);
        return settings.getString("ideal_diet_plan_adult", "defaultValue");

    }
    public static boolean writeIdealDietPlanChild(Context context,String response)
    {
        SharedPreferences settings = context.getSharedPreferences("DietPlanAdult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("ideal_diet_plan_adult", response);
        editor.commit();
        return true;
    }

    public static String getIdealDietPlanChild(Context context)
    {
        SharedPreferences settings = context.getSharedPreferences("DietPlanAdult", Context.MODE_PRIVATE);
        return settings.getString("ideal_diet_plan_adult", "defaultValue");

    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences("CalorieCalculator", Context.MODE_PRIVATE);
        return settings.getString(key, "defaultValue");
    }

    public static void storeMealNutrientCalorieValue(JSONObject calorieJson,String Meal,String MealType,String Nutrient,Context context)
    {
        String valueToStore= null;
        try {
            valueToStore = calorieJson.getJSONObject(Meal).getJSONObject(MealType).getString(Nutrient);
            setPreference(context,Meal+MealType+Nutrient,valueToStore);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static float getMealNutrientCalorieValue(String Meal,String MealType,String Nutrient,Context context)
    {

            return Float.valueOf(getPreference(context,Meal+MealType+Nutrient));


    }

    public static void storeValues(String Meal,String MealType,JSONObject calorieJson,Context context)
    {
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"Calories",context);
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"Servings",context);
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"carb",context);
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"prot",context);
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"fat",context);
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"SFA",context);
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"chole",context);
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"sugar",context);
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"sodium",context);
        storeMealNutrientCalorieValue(calorieJson,Meal,MealType,"fibre",context);

    }
    public static void StoreCalculatedCalories(JSONObject calorieJson,Context context)
    {
        storeValues("Breakfast","Cereals",calorieJson,context);
        storeValues("Breakfast","Milk/curd",calorieJson,context);
        storeValues("Breakfast","Fruits",calorieJson,context);
        storeValues("Breakfast","vegetables",calorieJson,context);
        storeValues("Breakfast","Total",calorieJson,context);
        storeValues("Breakfast","Cereals",calorieJson,context);
        storeValues("Breakfast","eggs",calorieJson,context);

        storeValues("Lunch","Rice",calorieJson,context);
        storeValues("Lunch","Roti",calorieJson,context);
        storeValues("Lunch","daal/meat/fish",calorieJson,context);
        storeValues("Lunch","vegetables",calorieJson,context);
        storeValues("Lunch","Milk/curd",calorieJson,context);
        storeValues("Lunch","Oils",calorieJson,context);
        storeValues("Lunch","Total",calorieJson,context);


        storeValues("Dinner","Rice",calorieJson,context);
        storeValues("Dinner","Roti",calorieJson,context);
        storeValues("Dinner","daal/meat/fish",calorieJson,context);
        storeValues("Dinner","vegetables",calorieJson,context);
        storeValues("Dinner","Milk/curd",calorieJson,context);
        storeValues("Dinner","Oils",calorieJson,context);
        storeValues("Dinner","Total",calorieJson,context);


        storeValues("Snacks","Nuts",calorieJson,context);
        storeValues("Snacks","Fruits",calorieJson,context);
        storeValues("Snacks","Milk/curd",calorieJson,context);
        storeValues("Snacks","Total",calorieJson,context);

    }


}
