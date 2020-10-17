package com.hwi.health.Activitys.Profile.Second;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;

/**
 * Created by PAWAN on 05-08-2017.
 */

public class User_Profile_Complete {

    AppCompatActivity act;
    String gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_diseas, Bp;

    int age_gender = 0, height_weight = 0, is_pregnent_value = 0, value_menstrual = 0, breast_feeding_value = 0, child_age_value = 0, is_diabetic_value = 0, last_HBA1C_value = 0, activity_level = 0, habits_value = 0, alcholic_value = 0, alcholic_often_value = 0, smoke_value = 0, smoke_often_value = 0, taking_insulin_value = 0;

    int count = 0;

    public User_Profile_Complete(AppCompatActivity act) {
        this.act = act;
    }

    public void sps() {

        SharedPreferences sp = new AllSharedPrefrences(act).UserDataget();
        gender = sp.getString("gender", "");
        age = sp.getString("age", "");
        user_id = sp.getString("Userid", "");
        sp.getString("key", "");
        height = sp.getString("height", "");
        weight = sp.getString("weight", "");
        is_pregnent = sp.getString("is_pregnent", "");
        first_day_of_last_menstrual_cycle = sp.getString("first_day_of_last_menstrual_cycle", "");
        pre_pregnancy_weight = sp.getString("pre_pregnancy_weight", "");
        breast_feeding = sp.getString("breast_feeding", "");
        child_age = sp.getString("child_age", "");
        is_diabetic = sp.getString("is_diabetic", "");
        taking_insulin = sp.getString("taking_insulin", "");
        diabetic_diet = sp.getString("taking_diabetic_diet", "");
        last_HBA1C = sp.getString("last_HBA1C", "");
        blood_sugar_no_of_times = sp.getString("blood_sugar_no_of_times", "");
        blood_sugar_in = sp.getString("blood_sugar_in_D_W_M", "");
        last_fasting = sp.getString("Last_fasting", "");
        pp_value = sp.getString("pp_value", "");
        activity_level_one = sp.getString("activity_level_one", "");
        activity_level_two = sp.getString("activity_level_two", "");
        food_habits = sp.getString("food_habits", "");
        any_heart_disease = sp.getString("any_heart_disease", "");
        is_alcholic = sp.getString("is_alcholic", "");
        alcohol_how_often = sp.getString("alcohol_how_often", "");
        is_smoke = sp.getString("is_smoke", "");
        smoke_how_often = sp.getString("smoke_how_often", "");
        sp.getString("is_login", "");
        Hemoglobin = sp.getString("Hemoglobin", "");
        Hematocrit = sp.getString("Hematocrit", "");
        blood_sugar = sp.getString("blood_sugar", "");
        Total_cholesterol = sp.getString("Total_cholesterol", "");
        LDL_cholesterol = sp.getString("LDL_cholesterol", "");
        HDL_cholesterol = sp.getString("HDL_cholesterol", "");
        Triglycerides = sp.getString("Triglycerides", "");
        Vitamin_levels = sp.getString("Vitamin_levels", "");
        Senter_inc = sp.getString("Senter_inc", "");
        Senter_cmc = sp.getString("Senter_cmc", "");
        SSystolic = sp.getString("SSystolic", "");
        SDiastolic = sp.getString("SDiastolic", "");
        Vitamin_B12_levels = sp.getString("Vitamin_B12_levels", "");
        heart_diseas = sp.getString("heart_disease", "");
        Bp = sp.getString("BP", "");
        Log.e("activity_levellll", activity_level_one + "..." + activity_level_two);

    }

    public void giveValue() {
        sps();

        if (!gender.equals("") || !age.equals("")) {
            age_gender = 1;
            count = age_gender;
            new AllSharedPrefrences(act).Profile_complete(count);

            if (gender.equals("male")) {
                if (!height.equals("") || !weight.equals("")) {
                    height_weight = 1;
                    count = age_gender + height_weight;
                    new AllSharedPrefrences(act).Profile_complete(count);

                    if (!is_diabetic.equals("")) {
                        is_diabetic_value = 1;
                        count = age_gender + height_weight + is_diabetic_value;
                        new AllSharedPrefrences(act).Profile_complete(count);


                        if (is_diabetic.equals("yes")) {
                            if (!taking_insulin.equals("") || !diabetic_diet.equals("")) {

                                taking_insulin_value = 1;
                                count = age_gender + height_weight + is_diabetic_value + taking_insulin_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                                if (!last_HBA1C.equals("") || !(last_HBA1C == null) || !blood_sugar_no_of_times.equals("") || !(blood_sugar_no_of_times == null) || !blood_sugar_in.equals("") || !(blood_sugar_in == null) || !last_fasting.equals("") || !(last_fasting == null) || !pp_value.equals("") || !(pp_value == null)) {
                                    last_HBA1C_value = 1;
                                    count = age_gender + height_weight + is_diabetic_value + taking_insulin_value + last_HBA1C_value;
                                    new AllSharedPrefrences(act).Profile_complete(count);

                                    if (!activity_level_one.equals("") || !(activity_level_one == null) || !activity_level_two.equals("") || !(activity_level_two == null)) {
                                        activity_level = 1;
                                        count = age_gender + height_weight + is_diabetic_value + taking_insulin_value + last_HBA1C_value + activity_level;
                                        new AllSharedPrefrences(act).Profile_complete(count);
                                        if (!food_habits.equals("") || !(food_habits == null) || !any_heart_disease.equals("") || !(any_heart_disease == null)) {
                                            habits_value = 1;
                                            count = age_gender + height_weight + is_diabetic_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value;
                                            new AllSharedPrefrences(act).Profile_complete(count);
                                            if (!is_smoke.equals("") || !(is_smoke == null)) {
                                                smoke_value = 1;
                                                count = age_gender + height_weight + is_diabetic_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value + smoke_value;
                                                new AllSharedPrefrences(act).Profile_complete(count);
                                                if (!is_alcholic.equals("") || !(is_alcholic == null)) {
                                                    alcholic_value = 1;
                                                    count = age_gender + height_weight + is_diabetic_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value + smoke_value + alcholic_value;
                                                    new AllSharedPrefrences(act).Profile_complete(count);
                                                }

                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
              /*         else  if (!activity_level_one.equals("") || !(activity_level_one == null) || !activity_level_two.equals("") || !(activity_level_two == null)) {
                                activity_level = 1;
                                count = age_gender + height_weight + is_diabetic_value + taking_insulin_value + last_HBA1C_value + activity_level;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else  if (!food_habits.equals("") || !(food_habits == null) || !any_heart_disease.equals("") || !(any_heart_disease == null)) {
                                habits_value = 1;
                                count = age_gender + height_weight + is_diabetic_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else if (!is_smoke.equals("") || !(is_smoke == null)) {
                                smoke_value = 1;
                                count = age_gender + height_weight + is_diabetic_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value + smoke_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }else if(!is_alcholic.equals("") || !(is_alcholic == null)) {
                                alcholic_value = 1;
                                count = age_gender + height_weight + is_diabetic_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value + smoke_value + alcholic_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }


                        else {
                            if (!activity_level_one.equals("") || !(activity_level_one == null) || !activity_level_two.equals("") || !(activity_level_two == null)) {
                                activity_level = 1;
                                count = age_gender + height_weight + is_diabetic_value + activity_level;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else  if (!food_habits.equals("") || !(food_habits == null) || !any_heart_disease.equals("") || !(any_heart_disease == null)) {
                                habits_value = 1;
                                count = age_gender + height_weight + is_diabetic_value + activity_level + habits_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else if (!is_smoke.equals("") || !(is_smoke == null)) {
                                smoke_value = 1;
                                count = age_gender + height_weight + is_diabetic_value + activity_level + habits_value + smoke_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }else if(!is_alcholic.equals("") || !(is_alcholic == null)) {
                                alcholic_value = 1;
                                count = age_gender + height_weight + is_diabetic_value + activity_level + habits_value + smoke_value + alcholic_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                        }



                    }
                    }



            else {
                if (!is_pregnent.equals("") || !(is_pregnent == null)) {
                    is_pregnent_value = 1;
                    count = age_gender + height_weight + is_pregnent_value;
                    new AllSharedPrefrences(act).Profile_complete(count);
                }
                if (is_pregnent.equals("yes")) {
                    if (!first_day_of_last_menstrual_cycle.equals("") || !(first_day_of_last_menstrual_cycle == null) || !pre_pregnancy_weight.equals("") || !(pre_pregnancy_weight == null)) {
                        value_menstrual = 1;
                        count = age_gender + height_weight + is_pregnent_value + value_menstrual;
                        new AllSharedPrefrences(act).Profile_complete(count);
                    } else if (!child_age.equals("") || !(child_age == null)) {
                        child_age_value = 1;
                        count = age_gender + height_weight + is_pregnent_value + value_menstrual + child_age_value ;
                        new AllSharedPrefrences(act).Profile_complete(count);
                    }
                    else if (!is_diabetic.equals("") || !(is_diabetic == null)) {
                        is_diabetic_value = 1;
                        count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value ;
                        new AllSharedPrefrences(act).Profile_complete(count);

                        if (is_diabetic.equals("yes")) {
                            if (!taking_insulin.equals("") || !(taking_insulin == null) || !diabetic_diet.equals("") || !(diabetic_diet == null)) {
                                taking_insulin_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + taking_insulin_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            } else if (!last_HBA1C.equals("") || !(last_HBA1C == null) || !blood_sugar_no_of_times.equals("") || !(blood_sugar_no_of_times == null) || !blood_sugar_in.equals("") || !(blood_sugar_in == null) || !last_fasting.equals("") || !(last_fasting == null) || !pp_value.equals("") || !(pp_value == null)) {
                                last_HBA1C_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + taking_insulin_value + last_HBA1C_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else  if (!activity_level_one.equals("") || !(activity_level_one == null) || !activity_level_two.equals("") || !(activity_level_two == null)) {
                                activity_level = 1;
                                count =age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + taking_insulin_value + last_HBA1C_value + activity_level;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else  if (!food_habits.equals("") || !(food_habits == null) || !any_heart_disease.equals("") || !(any_heart_disease == null)) {
                                habits_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else if (!is_smoke.equals("") || !(is_smoke == null)) {
                                smoke_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value + smoke_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else if(!is_alcholic.equals("") || !(is_alcholic == null)) {
                                alcholic_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value + smoke_value + alcholic_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }

                        }
                        else {
                            if (!activity_level_one.equals("") || !(activity_level_one == null) || !activity_level_two.equals("") || !(activity_level_two == null)) {
                                activity_level = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + activity_level ;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else  if (!food_habits.equals("") || !(food_habits == null) || !any_heart_disease.equals("") || !(any_heart_disease == null)) {
                                habits_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + activity_level + habits_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                           }
                            else if (!is_smoke.equals("") || !(is_smoke == null)) {
                                smoke_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + activity_level + habits_value + smoke_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }else if(!is_alcholic.equals("") || !(is_alcholic == null)) {
                                alcholic_value = 1;
                                count =age_gender + height_weight + is_diabetic_value +is_pregnent_value + value_menstrual + child_age_value + activity_level + habits_value + smoke_value + alcholic_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                        }

                    }

                }
                else {
                    if (!breast_feeding.equals("") || !(breast_feeding == null)) {
                        breast_feeding_value = 1;
                        count = age_gender + height_weight + is_pregnent_value + breast_feeding_value;
                        new AllSharedPrefrences(act).Profile_complete(count);
                    }
                    else if (!is_diabetic.equals("") || !(is_diabetic == null)) {
                        is_diabetic_value = 1;
                        count = age_gender + height_weight + is_diabetic_value +is_pregnent_value +breast_feeding_value;
                        new AllSharedPrefrences(act).Profile_complete(count);

                        if (is_diabetic.equals("yes")) {
                            if (!taking_insulin.equals("") || !(taking_insulin == null) || !diabetic_diet.equals("") || !(diabetic_diet == null)) {
                                taking_insulin_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value +breast_feeding_value + taking_insulin_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else if (!last_HBA1C.equals("") || !(last_HBA1C == null) || !blood_sugar_no_of_times.equals("") || !(blood_sugar_no_of_times == null) || !blood_sugar_in.equals("") || !(blood_sugar_in == null) || !last_fasting.equals("") || !(last_fasting == null) || !pp_value.equals("") || !(pp_value == null)) {
                                last_HBA1C_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value +breast_feeding_value + taking_insulin_value + last_HBA1C_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else  if (!activity_level_one.equals("") || !(activity_level_one == null) || !activity_level_two.equals("") || !(activity_level_two == null)) {
                                activity_level = 1;
                                count =age_gender + height_weight + is_diabetic_value +is_pregnent_value + breast_feeding_value + taking_insulin_value + last_HBA1C_value + activity_level;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else  if (!food_habits.equals("") || !(food_habits == null) || !any_heart_disease.equals("") || !(any_heart_disease == null)) {
                                habits_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value +breast_feeding_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else if (!is_smoke.equals("") || !(is_smoke == null)) {
                                smoke_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + breast_feeding_value + taking_insulin_value + last_HBA1C_value + activity_level + habits_value + smoke_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }else if(!is_alcholic.equals("") || !(is_alcholic == null)) {
                                alcholic_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value +breast_feeding_value+ taking_insulin_value + last_HBA1C_value + activity_level + habits_value + smoke_value + alcholic_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }

                        }
                        else {
                            if (!activity_level_one.equals("") || !(activity_level_one == null) || !activity_level_two.equals("") || !(activity_level_two == null)) {
                                activity_level = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value + breast_feeding_value + activity_level ;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else  if (!food_habits.equals("") || !(food_habits == null) || !any_heart_disease.equals("") || !(any_heart_disease == null)) {
                                habits_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value +breast_feeding_value + activity_level + habits_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                            else if (!is_smoke.equals("") || !(is_smoke == null)) {
                                smoke_value = 1;
                                count = age_gender + height_weight + is_diabetic_value +is_pregnent_value +breast_feeding_value + activity_level + habits_value + smoke_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }else if(!is_alcholic.equals("") || !(is_alcholic == null)) {
                                alcholic_value = 1;
                                count =age_gender + height_weight + is_diabetic_value +is_pregnent_value + breast_feeding_value + activity_level + habits_value + smoke_value + alcholic_value;
                                new AllSharedPrefrences(act).Profile_complete(count);
                            }
                        }

                    }


                }

            }
*/

    }
}