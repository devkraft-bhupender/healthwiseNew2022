package com.hwi.health.SharedPrefrecess;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.hwi.health.Activitys.Logs.LogActivity;

/**
 * Created by Win on 6/14/2017.
 */

public class AllSharedPrefrences {
    Context context;

    public AllSharedPrefrences(Context context) {
        this.context = context;
    }

    public SharedPreferences UserAdvanceData1(String Hemoglobin,String Hematocrit,String blood_sugar,String Total_cholesterol,String LDL_cholesterol,String HDL_cholesterol ,String Triglycerides,String Vitamin_levels,String Vitamin_B12_levels,String Senter_inc,String Senter_cmc,String SSystolic,String SDiastolic,String heart_disease,String Bp){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Hemoglobin", Hemoglobin);
        editor.putString("Hematocrit", Hematocrit);
        editor.putString("blood_sugar", blood_sugar);
        editor.putString("Total_cholesterol", Total_cholesterol);
        editor.putString("LDL_cholesterol", LDL_cholesterol);
        editor.putString("HDL_cholesterol", HDL_cholesterol);
        editor.putString("Triglycerides", Triglycerides);
        editor.putString("Vitamin_levels", Vitamin_levels);
        editor.putString("Senter_inc", Senter_inc);
        editor.putString("Senter_cmc", Senter_cmc);
        editor.putString("SSystolic", SSystolic);
        editor.putString("SDiastolic", SDiastolic);
        editor.putString("Vitamin_B12_levels", Vitamin_B12_levels);
        editor.putString("heart_disease", heart_disease);
        editor.putString("BP", Bp);
        editor.commit();


        return sp;
    }
    public SharedPreferences UserDob(String dob){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Dob", dob);
        editor.commit();
        return sp;
    }

    public SharedPreferences Food_item_and(String json,String key){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("json", json);
        editor.putString("key", key);

        editor.commit();
        return sp;
    }

    public SharedPreferences Cancer_key(String cancer_key){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("cancer_key", cancer_key);
        editor.commit();
        return sp;
    }
    public SharedPreferences Alchol_result(String total_Calories,String total_Std_size_drinks,String total_Carbs,String total_Sugar,String total_Sodium,String total_count,String food_data,String current_date){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("total_Calories", total_Calories);
        editor.putString("total_Std_size_drinks", total_Std_size_drinks);
        editor.putString("total_Carbs", total_Carbs);
        editor.putString("total_Sugar", total_Sugar);
        editor.putString("total_Sodium", total_Sodium);
        editor.putString("total_count", total_count);
        editor.putString("food_data", food_data);
        editor.putString("current_date", current_date);
        editor.commit();
        return sp;
    }
    public SharedPreferences Diet_log_result(String total_Calories,String Energy,String Carbs,String Sugar,String Protein,String Fat,String fibre,String sat_fat,String total_souium, String total_cholestrol,String key_food){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("total_Calories", total_Calories);
        editor.putString("total_Energy", Energy);
        editor.putString("total_carbs", Carbs);
        editor.putString("total_Protein", Protein);
        editor.putString("total_Fat", Fat);
        editor.putString("total_sugar", Sugar);
        editor.putString("total_fibre", fibre);
        editor.putString("total_sat_fat", sat_fat);
        editor.putString("total_souium", total_souium);
        editor.putString("total_cholestrol", total_cholestrol);
        editor.putString("key_food", key_food);
        editor.commit();
        return sp;
    }


    public SharedPreferences Eat_out_result(String Carbs_div,String Sugar_div,String Protein_div,String Fat_div,String fibre_div,String sat_fat_div,String with_water_cal,String with_begrage_cal,String chole_div, String sodium_div, String calories_cal){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Carbs_div", Carbs_div);
        editor.putString("Sugar_div", Sugar_div);
        editor.putString("Protein_div", Protein_div);
        editor.putString("Fat_div", Fat_div);
        editor.putString("fibre_div", fibre_div);
        editor.putString("sat_fat_div", sat_fat_div);
        editor.putString("chole_div", chole_div);
        editor.putString("sodium_div", sodium_div);
        editor.putString("calories_cal", calories_cal);
        editor.putString("with_water_cal", with_water_cal);
        editor.putString("with_begrage_cal", with_begrage_cal);

        editor.commit();
        return sp;
    }


    public SharedPreferences Plan_Ex_key(String plan_ex_key){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("plan_ex_key", plan_ex_key);
        editor.commit();
        return sp;
    }
    public SharedPreferences Customised_key(String cust_key){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("cust_key", cust_key);
        editor.commit();
        return sp;
    }
    public SharedPreferences Weight_loss(String weg_loss){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("weg_loss", weg_loss);
        editor.commit();


        return sp;
    }

    public SharedPreferences linkplan(String link,String key){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("link", link);
        editor.putString("key", key);
        editor.commit();


        return sp;
    }
    public SharedPreferences Profile_complete(int count){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("profile_count", count+"");
        editor.commit();


        return sp;
    }
    public SharedPreferences Profile_complete_persentage(String count){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("persentage", count);
        editor.commit();


        return sp;
    }
    public SharedPreferences Major_Meals(String meals,String break_fast){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("MajorMeals", meals);
        editor.putString("Breakfast", break_fast);
        editor.commit();
        return sp;
    }

    public SharedPreferences Lunch_Dinner(String dinner){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("LunchDinner", dinner);
        editor.commit();
        return sp;
    }

    public SharedPreferences Rice(String rice){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Rice", rice);
        editor.commit();
        return sp;
    }


    public SharedPreferences Non_Veg(String non_veg,String chicken, String fish,String beef, String goat_meat,String lamb,String pork){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Nonveg", non_veg);
        editor.putString("chicken", chicken);
        editor.putString("fish", fish);
        editor.putString("beef", beef);
        editor.putString("goat_meat", goat_meat);
        editor.putString("lamb", lamb);
        editor.putString("pork", pork);
        editor.commit();
        return sp;
    }

    public SharedPreferences Milk(String milk){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("milk", milk);
        editor.commit();
        return sp;
    }
    public SharedPreferences Oil(String oil,String categ1,String categ2,String categ3,String categ4,String categ5){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("oil", oil);
        editor.putString("categ1", categ1);
        editor.putString("categ2", categ2);
        editor.putString("categ3", categ3);
        editor.putString("categ4", categ4);
        editor.putString("categ5", categ5);
        editor.commit();
        return sp;
    }

    public SharedPreferences Cereals(String cereals){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("cereals", cereals);
        editor.commit();
        return sp;
    }
    public SharedPreferences Break_milk(String break_milk){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("break_milk", break_milk);
        editor.commit();
        return sp;
    } public SharedPreferences Vegetables(String vegetables){
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("vegetables", vegetables);
        editor.commit();
        return sp;
    }
    public SharedPreferences UserDataget() {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sp;
    }

    public SharedPreferences UserData(String Username, String userEmail, String userProfilePhoto, String key, String user_id,
                                      String gender, String age, String height, String weight, String is_pregnent, String first_day_of_last_menstrual_cycle,
                                      String pre_pregnancy_weight, String breast_feeding, String child_age, String is_diabetic, String taking_insulin, String diabetic_diet,
                                      String last_HBA1C, String blood_sugar_no_of_times, String blood_sugar_in, String last_fasting, String pp_value, String activity_level_one,
                                      String activity_level_two, String food_habits, String any_heart_disease, String is_alcholic, String alcohol_how_often, String is_smoke,
                                      String smoke_how_often, String is_login, String HBA1C_value) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", Username);
        editor.putString("userEmail", userEmail);
        editor.putString("userProfilePhoto", userProfilePhoto);
        editor.putString("key", key);
        editor.putString("Userid", user_id);
        editor.putString("gender", gender);
        editor.putString("age", age);
        editor.putString("height", height);
        editor.putString("weight", weight);
        editor.putString("is_pregnent", is_pregnent);
        editor.putString("first_day_of_last_menstrual_cycle", first_day_of_last_menstrual_cycle);
        editor.putString("pre_pregnancy_weight", pre_pregnancy_weight);
        editor.putString("breast_feeding", breast_feeding);
        editor.putString("child_age", child_age);
        editor.putString("is_diabetic", is_diabetic);
        editor.putString("taking_insulin", taking_insulin);
        editor.putString("taking_diabetic_diet", diabetic_diet);
        editor.putString("last_HBA1C", last_HBA1C);
        editor.putString("HBA1C_value", HBA1C_value);
        editor.putString("blood_sugar_no_of_times", blood_sugar_no_of_times);
        editor.putString("blood_sugar_in_D_W_M", blood_sugar_in);
        editor.putString("Last_fasting", last_fasting);
        editor.putString("pp_value", pp_value);
        editor.putString("activity_level_one", activity_level_one);
        editor.putString("activity_level_two", activity_level_two);
        editor.putString("food_habits", food_habits);
        editor.putString("any_heart_disease", any_heart_disease);
        editor.putString("is_alcholic", is_alcholic);
        editor.putString("alcohol_how_often", alcohol_how_often);
        editor.putString("is_smoke", is_smoke);
        editor.putString("smoke_how_often", smoke_how_often);
        editor.putString("is_login", is_login);
        editor.commit();
        return sp;
    }


    public SharedPreferences UserData2(String user_id,
                                       String gender, String age, String height, String weight, String is_pregnent, String first_day_of_last_menstrual_cycle,
                                       String pre_pregnancy_weight, String breast_feeding, String child_age, String is_diabetic, String taking_insulin, String diabetic_diet,
                                       String last_HBA1C, String blood_sugar_no_of_times, String blood_sugar_in, String last_fasting,String S_HBA1C_value, String pp_value, String activity_level_one,
                                       String activity_level_two, String food_habits, String any_heart_disease, String is_alcholic, String alcohol_how_often, String is_smoke,
                                       String smoke_how_often) {
//        Toast.makeText(context," ruyntnhgfhz ", Toast.LENGTH_SHORT).show();
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Userid", user_id);
        editor.putString("gender", gender);
        editor.putString("age", age);
        editor.putString("height", height);
        editor.putString("weight", weight);
        editor.putString("is_pregnent", is_pregnent);
        editor.putString("first_day_of_last_menstrual_cycle", first_day_of_last_menstrual_cycle);
        editor.putString("pre_pregnancy_weight", pre_pregnancy_weight);
        editor.putString("breast_feeding", breast_feeding);
        editor.putString("child_age", child_age);
        editor.putString("is_diabetic", is_diabetic);
        editor.putString("taking_insulin", taking_insulin);
        editor.putString("taking_diabetic_diet", diabetic_diet);
        editor.putString("last_HBA1C", last_HBA1C);
        editor.putString("HBA1C_value", S_HBA1C_value);
        editor.putString("blood_sugar_no_of_times", blood_sugar_no_of_times);
        editor.putString("blood_sugar_in_D_W_M", blood_sugar_in);
        editor.putString("Last_fasting", last_fasting);
        editor.putString("pp_value", pp_value);
        editor.putString("activity_level_one", activity_level_one);
        editor.putString("activity_level_two", activity_level_two);
        editor.putString("food_habits", food_habits);
        editor.putString("any_heart_disease", any_heart_disease);
        editor.putString("is_alcholic", is_alcholic);
        editor.putString("alcohol_how_often", alcohol_how_often);
        editor.putString("is_smoke", is_smoke);
        editor.putString("smoke_how_often", smoke_how_often);
        editor.commit();
        return sp;
    }

    public SharedPreferences Loginsp(String id) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Userid", id);
        return sp;
    }


    public SharedPreferences Signupsp1(String id) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Userid", id);
        editor.commit();
        return sp;
    }
    public SharedPreferences EmileId(String id) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userEmail", id);
        editor.commit();
        return sp;
    }

    public SharedPreferences Signupsp7(String diabetiv) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("is_diabetic", diabetiv);

        editor.commit();
        return sp;
    }

    public SharedPreferences keyBmi(String key_bmi) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key_bmi", key_bmi);
        editor.commit();
        return sp;
    }

    public SharedPreferences Reports_key(String key_rep) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key_rep", key_rep);
        editor.commit();
        return sp;
    }
    public SharedPreferences Prescription_key(String key_pres) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key_pres", key_pres);
        editor.commit();
        return sp;
    }
    public SharedPreferences keydiet(String key_diet) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key_diet", key_diet);

        editor.commit();
        return sp;
    }
    public SharedPreferences keyAdavance(String key_Adavance) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key_Adavance", key_Adavance);
        editor.commit();
        return sp;
    }
    public SharedPreferences keyHeartRisk(String Heart_Risk) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Heart_Risk", Heart_Risk);
        editor.commit();
        return sp;
    }

    public void ClearSignupsp() {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        sp.edit().clear().commit();

        SharedPreferences sp1 = context.getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
        sp1.edit().clear().commit();
    }


    public SharedPreferences BMI_Result(String BMI, String weight_range, String weight_Category, String weight_target, String weight) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("BMI", BMI);
        editor.putString("weight_range", weight_range);
        editor.putString("weight_Category", weight_Category);
        editor.putString("weight_target", weight_target);
        editor.putString("Weight", weight);
        editor.commit();
        Log.e("Bmiiiiii",BMI+"");
        return sp;
    }

    public SharedPreferences Advance_Result(String BMI, String central_obesity, String syndrome, String Diabetes, String Prediabetes, String High_BP, String Weight_Category, String Anaemia, String Vit_B, String Vit_D) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("BMI", BMI);
        editor.putString("central_obesity", central_obesity);
        editor.putString("syndrome", syndrome);
        editor.putString("Diabetes", Diabetes);
        editor.putString("Prediabetes", Prediabetes);
        editor.putString("High_BP", High_BP);
        editor.putString("weight_Category", Weight_Category);
        editor.putString("Anaemia", Anaemia);
        editor.putString("Vit_B", Vit_B);
        editor.putString("Vit_D", Vit_D);
        editor.commit();
        return sp;
    }
    public SharedPreferences Dob(String dob) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("DOB", dob);
        editor.commit();
        return sp;
    }


  /* TODO  RDCA : Recommended Daily Calorie Allowance*/

    public SharedPreferences Activity_Level_Diet_Plan(String Activity_Level) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Activity_Level", Activity_Level);
        editor.commit();
        return sp;
    }

    public SharedPreferences IDEAL_Diet_Plan(String BMI, String weight_range, String weight_Category, String weight_target, String Activity_Level, String RDCA, String Daily_Calorie
            , String Category, String Gender, String Age, String Height, String Pregnancy, String Trimester, String Pre_Pregnancy_Weight, String Breast_Feeding, String Child_Age, String Weight) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("BMI", BMI);
        editor.putString("weight_range", weight_range);
        editor.putString("weight_Category", weight_Category);
        editor.putString("weight_target", weight_target);
        editor.putString("Activity_Level", Activity_Level);
        editor.putString("RDCA", RDCA);
        editor.putString("Daily_Calorie", Daily_Calorie);
        editor.putString("Category", Category);
        editor.putString("Gender", Gender);
        editor.putString("Age", Age);
        editor.putString("Height", Height);
        editor.putString("Pregnancy", Pregnancy);
        editor.putString("Trimester", Trimester);
        editor.putString("Pre_Pregnancy_Weight", Pre_Pregnancy_Weight);
        editor.putString("Breast_Feeding", Breast_Feeding);
        editor.putString("Child_Age", Child_Age);
        editor.putString("Weight", Weight);
        editor.commit();
        return sp;
    }


    public SharedPreferences Risk_Cal(String bmi, String Weight_Category, String yearrisk10, String This_risk_is, String A44, String A45, String A46, String A47, String A48, String To_know_how, String year_risk_persent20, String year_risk20, String B51, String smoke_risk_amount, String smoke_risk_amount_title
            , String risk_again, String systolic_or_diastolic_risk_persentage, String systolic_or_diastolic_risk_category, String tot_cholesterol_risk_title, String tot_cholesterol_risk_persentage, String tot_cholesterol_risk_category, String A41, String A50) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("BMI", bmi);
        editor.putString("yearrisk10", yearrisk10);
        editor.putString("weight_Category", Weight_Category);
        editor.putString("This_risk_is", This_risk_is);
        editor.putString("A44", A44);
        editor.putString("A45", A45);
        editor.putString("A46", A46);
        editor.putString("A47", A47);
        editor.putString("A48", A48);
        editor.putString("To_know_how", To_know_how);
        editor.putString("year_risk_persent20", year_risk_persent20);
        editor.putString("year_risk20", year_risk20);
        editor.putString("B51", B51);
        editor.putString("smoke_risk_amount", smoke_risk_amount);
        editor.putString("smoke_risk_amount_title", smoke_risk_amount_title);
        editor.putString("risk_again", risk_again);
        editor.putString("systolic_or_diastolic_risk_persentage", systolic_or_diastolic_risk_persentage);
        editor.putString("systolic_or_diastolic_risk_category", systolic_or_diastolic_risk_category);
        editor.putString("tot_cholesterol_risk_title", tot_cholesterol_risk_title);
        editor.putString("tot_cholesterol_risk_persentage", tot_cholesterol_risk_persentage);
        editor.putString("tot_cholesterol_risk_category", tot_cholesterol_risk_category);
        editor.putString("A41", A41);
        editor.putString("A41", A41);
        editor.commit();

        return sp;
    }


    public SharedPreferences Analysis(String bmi, String Weight_Category, String your_wc, String metabolic, String Diabetes, String Prediabetes, String HighBP, String heart_risk, String Anaemia, String vb12, String vd, String Weight, String Age) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("BMI", bmi);
        editor.putString("your_wc", your_wc);
        editor.putString("weight_Category", Weight_Category);
        editor.putString("metabolic", metabolic);
        editor.putString("Diabetes", Diabetes);
        editor.putString("Prediabetes", Prediabetes);
        editor.putString("HighBP", HighBP);
        editor.putString("heart_risk", heart_risk);
        editor.putString("Anaemia", Anaemia);
        editor.putString("vb12", vb12);
        editor.putString("vd", vd);
        editor.putString("Weight", Weight);
        editor.putString("Age", Age);
        editor.commit();
        return sp;
    }


    public SharedPreferences IDEAL_Diet_Plan_Data(String GrainsCereals_Servings_equals, String GrainsCereals_BasicIndianDiet, String GrainsCereals_OptimizedIndiandiet, String GrainsCereals_IdealDietForPeopleWithHealthRisks, String GrainsCereals_ToknowmoregotoFWItipsl
            , String DalCereals_Servings_equals, String DalCereals_BasicIndianDiet, String DalCereals_OptimizedIndiandiet, String DalCereals_IdealDietForPeopleWithHealthRisks, String DalCereals_ToknowmoregotoFWItips,
                                                  String MaxmCereals_Servings_equals, String MaxmCereals_BasicIndianDiet, String MaxmCereals_OptimizedIndiandiet, String MaxmCereals_IdealDietForPeopleWithHealthRisks, String MaxmCereals_ToknowmoregotoFWItips,
                                                  String MinmCereals_Servings_equals, String MinmCereals_BasicIndianDiet, String MinmCereals_OptimizedIndiandiet, String MinmCereals_IdealDietForPeopleWithHealthRisks, String MinmCereals_ToknowmoregotoFWItips
            , String nvCereals_Servings_equals, String nvCereals_BasicIndianDiet, String nvCereals_OptimizedIndiandiet, String nvCereals_IdealDietForPeopleWithHealthRisks, String nvCereals_ToknowmoregotoFWItips,
                                                  String SoyaCereals_Servings_equals, String SoyaCereals_BasicIndianDiet, String SoyaCereals_OptimizedIndiandiet, String SoyaCereals_IdealDietForPeopleWithHealthRisks, String SoyaCereals_ToknowmoregotoFWItips,
                                                  String NutsCereals_Servings_equals, String NutsaCereals_BasicIndianDiet, String NutsCereals_OptimizedIndiandiet, String NutsCereals_IdealDietForPeopleWithHealthRisks, String NutsCereals_ToknowmoregotoFWItips,
                                                  String MilkCereals_Servings_equals, String MilkCereals_BasicIndianDiet, String MilkCereals_OptimizedIndiandiet, String MilkCereals_IdealDietForPeopleWithHealthRisks, String MilksCereals_ToknowmoregotoFWItips,
                                                  String vegCereals_Servings_equals, String vegCereals_BasicIndianDiet, String vegCereals_OptimizedIndiandiet, String vegCereals_IdealDietForPeopleWithHealthRisks, String vegCereals_ToknowmoregotoFWItips,
                                                  String FruitsCereals_Servings_equals, String FruitsCereals_BasicIndianDiet, String FruitsCereals_OptimizedIndiandiet, String FruitsCereals_IdealDietForPeopleWithHealthRisks, String FruitsCereals_ToknowmoregotoFWItips,
                                                  String OilsCereals_Servings_equals, String OilsCereals_BasicIndianDiet, String OilsCereals_OptimizedIndiandiet, String OilsCereals_IdealDietForPeopleWithHealthRisks, String OilsCereals_ToknowmoregotoFWItips) {
        SharedPreferences sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("GrainsCereals_Servings_equals", GrainsCereals_Servings_equals);
        editor.putString("GrainsCereals_BasicIndianDiet", GrainsCereals_BasicIndianDiet);
        editor.putString("GrainsCereals_OptimizedIndiandiet", GrainsCereals_OptimizedIndiandiet);
        editor.putString("GrainsCereals_IdealDietForPeopleWithHealthRisks", GrainsCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("GrainsCereals_ToknowmoregotoFWItipsl", GrainsCereals_ToknowmoregotoFWItipsl);
        editor.putString("DalCereals_Servings_equals", DalCereals_Servings_equals);
        editor.putString("DalCereals_BasicIndianDiet", DalCereals_BasicIndianDiet);
        editor.putString("DalCereals_OptimizedIndiandiet", DalCereals_OptimizedIndiandiet);
        editor.putString("DalCereals_IdealDietForPeopleWithHealthRisks", DalCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("DalCereals_ToknowmoregotoFWItips", DalCereals_ToknowmoregotoFWItips);
        editor.putString("MaxmCereals_Servings_equals", MaxmCereals_Servings_equals);
        editor.putString("MaxmCereals_BasicIndianDiet", MaxmCereals_BasicIndianDiet);
        editor.putString("MaxmCereals_OptimizedIndiandiet", MaxmCereals_OptimizedIndiandiet);
        editor.putString("MaxmCereals_IdealDietForPeopleWithHealthRisks", MaxmCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("MaxmCereals_ToknowmoregotoFWItips", MaxmCereals_ToknowmoregotoFWItips);
        editor.putString("MinmCereals_Servings_equals", MinmCereals_Servings_equals);
        editor.putString("MinmCereals_BasicIndianDiet", MinmCereals_BasicIndianDiet);
        editor.putString("MinmCereals_OptimizedIndiandiet", MinmCereals_OptimizedIndiandiet);
        editor.putString("MinmCereals_IdealDietForPeopleWithHealthRisks", MinmCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("MinmCereals_ToknowmoregotoFWItips", MinmCereals_ToknowmoregotoFWItips);
        editor.putString("nvCereals_Servings_equals", nvCereals_Servings_equals);
        editor.putString("nvCereals_BasicIndianDiet", nvCereals_BasicIndianDiet);
        editor.putString("nvCereals_OptimizedIndiandiet", nvCereals_OptimizedIndiandiet);
        editor.putString("nvCereals_IdealDietForPeopleWithHealthRisks", nvCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("nvCereals_ToknowmoregotoFWItips", nvCereals_ToknowmoregotoFWItips);
        editor.putString("SoyaCereals_Servings_equals", SoyaCereals_Servings_equals);
        editor.putString("SoyaCereals_BasicIndianDiet", SoyaCereals_BasicIndianDiet);
        editor.putString("SoyaCereals_OptimizedIndiandiet", SoyaCereals_OptimizedIndiandiet);
        editor.putString("SoyaCereals_IdealDietForPeopleWithHealthRisks", SoyaCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("SoyaCereals_ToknowmoregotoFWItips", SoyaCereals_ToknowmoregotoFWItips);
        editor.putString("NutsCereals_Servings_equals", NutsCereals_Servings_equals);
        editor.putString("NutsaCereals_BasicIndianDiet", NutsaCereals_BasicIndianDiet);
        editor.putString("NutsCereals_OptimizedIndiandiet", NutsCereals_OptimizedIndiandiet);
        editor.putString("NutsCereals_IdealDietForPeopleWithHealthRisks", NutsCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("NutsCereals_ToknowmoregotoFWItips", NutsCereals_ToknowmoregotoFWItips);
        editor.putString("MilkCereals_Servings_equals", MilkCereals_Servings_equals);
        editor.putString("MilkCereals_BasicIndianDiet", MilkCereals_BasicIndianDiet);
        editor.putString("MilkCereals_OptimizedIndiandiet", MilkCereals_OptimizedIndiandiet);
        editor.putString("MilkCereals_IdealDietForPeopleWithHealthRisks", MilkCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("MilksCereals_ToknowmoregotoFWItips", MilksCereals_ToknowmoregotoFWItips);
        editor.putString("vegCereals_Servings_equals", vegCereals_Servings_equals);
        editor.putString("vegCereals_BasicIndianDiet", vegCereals_BasicIndianDiet);
        editor.putString("vegCereals_OptimizedIndiandiet", vegCereals_OptimizedIndiandiet);
        editor.putString("vegCereals_IdealDietForPeopleWithHealthRisks", vegCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("vegCereals_ToknowmoregotoFWItips", vegCereals_ToknowmoregotoFWItips);
        editor.putString("FruitsCereals_Servings_equals", FruitsCereals_Servings_equals);
        editor.putString("FruitsCereals_BasicIndianDiet", FruitsCereals_BasicIndianDiet);
        editor.putString("FruitsCereals_OptimizedIndiandiet", FruitsCereals_OptimizedIndiandiet);
        editor.putString("FruitsCereals_IdealDietForPeopleWithHealthRisks", FruitsCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("FruitsCereals_ToknowmoregotoFWItips", FruitsCereals_ToknowmoregotoFWItips);
        editor.putString("OilsCereals_Servings_equals", OilsCereals_Servings_equals);
        editor.putString("OilsCereals_BasicIndianDiet", OilsCereals_BasicIndianDiet);
        editor.putString("OilsCereals_OptimizedIndiandiet", OilsCereals_OptimizedIndiandiet);
        editor.putString("OilsCereals_IdealDietForPeopleWithHealthRisks", OilsCereals_IdealDietForPeopleWithHealthRisks);
        editor.putString("OilsCereals_ToknowmoregotoFWItips", OilsCereals_ToknowmoregotoFWItips);
        editor.commit();
        return sp;
    }



/*============================================== Autoloin =========================================*/

    public SharedPreferences AutoLogin(String key) {
        SharedPreferences sp = context.getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key", key);
        editor.commit();
        return sp;
    }

    public SharedPreferences AutoLoginGet() {
        SharedPreferences sp = context.getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
        return sp;
    }
}
