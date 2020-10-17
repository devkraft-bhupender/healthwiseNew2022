package com.hwi.health.Activitys.Profile.First;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Activitys.Profile.advanced_profile.Body_PARAMETERS;
import com.hwi.health.Activitys.Profile.advanced_profile.LABORATORY;
import com.hwi.health.Activitys.Profile.advanced_profile.RISK_RELATED_QUESTIONS;

import static android.content.Context.MODE_PRIVATE;

public class Profile_intent {

    AppCompatActivity act;
    String gender, age, height, weight, is_pregnent, first_day_of_last_menstrual_cycle, pre_pregnancy_weight, breast_feeding, child_age, is_diabetic, taking_insulin, diabetic_diet, last_HBA1C, blood_sugar_no_of_times, blood_sugar_in, last_fasting, pp_value, activity_level_one, activity_level_two, food_habits, any_heart_disease, is_alcholic, alcohol_how_often, is_smoke, smoke_how_often, user_id;
    String Hemoglobin, Hematocrit, blood_sugar, Total_cholesterol, LDL_cholesterol, HDL_cholesterol, Triglycerides, Vitamin_levels, Vitamin_B12_levels, Senter_inc, Senter_cmc, SSystolic, SDiastolic, heart_diseas, Bp;
    String adv_key,diet_weight_loss,get_dob;

    TextView Complete;
    public Profile_intent(AppCompatActivity act,TextView Complete) {
        this.act = act;
        this.Complete = Complete;
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
       diet_weight_loss = sp.getString("weg_loss", "");
       get_dob = sp.getString("Dob", "");

       Log.e("activity_levellll", activity_level_one + "..." + activity_level_two);

        SharedPreferences sp2 = act.getSharedPreferences("adv", MODE_PRIVATE);

        adv_key = sp2.getString("adv_key", "");
        Log.e("keyyyy",""+adv_key);

        if (!Senter_inc.equals("") && !Hemoglobin.equals("") && !Senter_inc.equals("") && !heart_diseas.equals("")) {
            Complete.setVisibility(View.GONE);
        }

    }


    public void getCondetions1() {
        sps();
    /*if (!gender.equals("") || !(gender == null) || !age.equals("") || !(age == null) ||height.equals(".") || !height.equals("") || !(height == null) || !weight.equals("") || !(weight == null) ||
             !is_diabetic.equals("") || !(is_diabetic == null)|| !taking_insulin.equals("") || !(taking_insulin == null) || !diabetic_diet.equals("") || !(diabetic_diet == null) ||
             !last_HBA1C.equals("") || !(last_HBA1C == null) || !blood_sugar_no_of_times.equals("") || !(blood_sugar_no_of_times == null) || !blood_sugar_in.equals("") || !(blood_sugar_in == null) || !last_fasting.equals("") || !(last_fasting == null) || !(pp_value.equals("")) || !(pp_value == null) ||
             !activity_level_one.equals("") || !(activity_level_one == null) || !activity_level_two.equals("") || !(activity_level_two == null) || !food_habits.equals("") || !(food_habits == null) || !any_heart_disease.equals("") || !(any_heart_disease == null) ||
             !is_smoke.equals("") || !is_alcholic.equals("") ){
        new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
    }
*/
        if (gender.equals("") || gender == null || age.equals("") || age == null) {
            new MyIntent(act, Profile_Step1.class, R.anim.enter, R.anim.exit);

        } else if (height.equals(".") || height.equals("") || height == null) {
            new MyIntent(act, Profile_Step2.class, R.anim.enter, R.anim.exit);
        }else if (weight.equals("") || weight == null) {
            new MyIntent(act, Profile_Step3.class, R.anim.enter, R.anim.exit);
        } else {
            if (gender.equals("male")) {
                if (is_diabetic.equals("") || is_diabetic == null) {
                    new MyIntent(act, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                } else if (is_diabetic.equals("yes")) {
                    if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                        new MyIntent(act, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                    } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                        new MyIntent(act, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                    } else if (activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null) {
                        new MyIntent(act, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                    } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                        new MyIntent(act, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                    }

                    /*---------------*/
                    else if (is_smoke.equals("") || is_smoke == null) {
                        new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                    } /*else if (smoke_how_often.equals("") || smoke_how_often == null) {
                        new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                    }*/ else if (is_alcholic.equals("") || is_alcholic == null) {
                       // Toast.makeText(act, "0", Toast.LENGTH_SHORT).show();
                        new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                    }
                   /* else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
                        Toast.makeText(act, "3", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                    }*/

                    else if (adv_key.equals("")) {
                        new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
                    } else if (Senter_inc.equals("") || Senter_inc == null || SSystolic.equals("") || SSystolic == null || Senter_cmc.equals("") || Senter_cmc == null || SDiastolic.equals("") || SDiastolic == null) {
                        new MyIntent(act, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);
                    } else if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || blood_sugar.equals("") || blood_sugar == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null) {
                        new MyIntent(act, LABORATORY.class, R.anim.enter, R.anim.exit);
                    } else if (heart_diseas.equals("") || heart_diseas == null || Bp.equals("") || Bp == null) {
                        new MyIntent(act, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    }else {

                    }


                } else if (is_diabetic.equals("no")) {
                    if (activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null) {
                        new MyIntent(act, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                    } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                        new MyIntent(act, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                    }
                    /*---------------*/
                    else if (is_smoke.equals("") || is_smoke == null) {
                        new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                    }/* else if (smoke_how_often.equals("") || smoke_how_often == null) {
                        new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                    }*/ else if (is_alcholic.equals("") || is_alcholic == null) {
                       // Toast.makeText(act, "0", Toast.LENGTH_SHORT).show();
                        new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                    }
                   /* else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
                        Toast.makeText(act, "3", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                    }*/

                    else if (adv_key.equals("")) {
                        new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
                    } else if (Senter_inc.equals("") || Senter_inc == null || SSystolic.equals("") || SSystolic == null || Senter_cmc.equals("") || Senter_cmc == null || SDiastolic.equals("") || SDiastolic == null) {
                        new MyIntent(act, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);
                    } else if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || blood_sugar.equals("") || blood_sugar == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null) {
                        new MyIntent(act, LABORATORY.class, R.anim.enter, R.anim.exit);
                    } else if (heart_diseas.equals("") || heart_diseas == null || Bp.equals("") || Bp == null) {
                        new MyIntent(act, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                    }else {

                    }
                }

            } else {

                //   Toast.makeText(act, is_pregnent, Toast.LENGTH_SHORT).show();


                if (is_pregnent.equals("") || is_pregnent == null) {
                    new MyIntent(act, Profile_Pregnant_3.class, R.anim.enter, R.anim.exit);
                } else if (is_pregnent.equals("yes")) {
                    if (first_day_of_last_menstrual_cycle.equals("") || first_day_of_last_menstrual_cycle == null || pre_pregnancy_weight.equals("") || pre_pregnancy_weight == null) {

                        new MyIntent(act, Profile_Menstrul_Cycle_4.class, R.anim.enter, R.anim.exit);
                    } else if (child_age.equals("") || child_age == null) {
                        new MyIntent(act, Profile_Child_Age_6.class, R.anim.enter, R.anim.exit);
                    } else if (is_diabetic.equals("") || is_diabetic == null) {
                        new MyIntent(act, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                    } else if (is_diabetic.equals("yes")) {
                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                            new MyIntent(act, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                        } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                            new MyIntent(act, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                        } else if (activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null) {
                            new MyIntent(act, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                            new MyIntent(act, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        }
                        /*---------------*/
                        else if (is_smoke.equals("") || is_smoke == null) {
                            new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                        } /*else if (smoke_how_often.equals("") || smoke_how_often == null) {
                            new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                        }*/ else if (is_alcholic.equals("") || is_alcholic == null) {
                          //  Toast.makeText(act, "0", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                        }
                   /* else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
                        Toast.makeText(act, "3", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                    }*/

                        else if (adv_key.equals("")) {
                            new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
                        } else if (Senter_inc.equals("") || Senter_inc == null || SSystolic.equals("") || SSystolic == null || Senter_cmc.equals("") || Senter_cmc == null || SDiastolic.equals("") || SDiastolic == null) {
                            new MyIntent(act, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);
                        } else if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || blood_sugar.equals("") || blood_sugar == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null) {
                            new MyIntent(act, LABORATORY.class, R.anim.enter, R.anim.exit);
                        } else if (heart_diseas.equals("") || heart_diseas == null || Bp.equals("") || Bp == null) {
                            new MyIntent(act, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                        }else {

                        }


//                         else if (is_alcholic.equals("") || is_alcholic == null) {
//                             new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
//                         } else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
//                             if (is_alcholic.equals("yes")) {
//                                 new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
//                             }else  {
//                                 new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
//                             }
//                         }
                    } else if (is_diabetic.equals("no")) {
                        if (activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null) {
                            new MyIntent(act, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                            new MyIntent(act, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        }
                        /*---------------*/
                        else if (is_smoke.equals("") || is_smoke == null) {
                            new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                        } /*else if (smoke_how_often.equals("") || smoke_how_often == null) {
                            new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                        }*/ else if (is_alcholic.equals("") || is_alcholic == null) {
                          //  Toast.makeText(act, "0", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                        }
                   /* else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
                        Toast.makeText(act, "3", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                    }*/

                        else if (adv_key.equals("")) {
                            new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
                        } else if (Senter_inc.equals("") || Senter_inc == null || SSystolic.equals("") || SSystolic == null || Senter_cmc.equals("") || Senter_cmc == null || SDiastolic.equals("") || SDiastolic == null) {
                            new MyIntent(act, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);
                        } else if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || blood_sugar.equals("") || blood_sugar == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null) {
                            new MyIntent(act, LABORATORY.class, R.anim.enter, R.anim.exit);
                        } else if (heart_diseas.equals("") || heart_diseas == null || Bp.equals("") || Bp == null) {
                            new MyIntent(act, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                        }else {

                        }

//                        else if (is_alcholic.equals("") || is_alcholic == null) {
//                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
//                        } else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
//                            if (is_alcholic.equals("yes")) {
//                                new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
//                            }else{
//                                new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
//                            }
//                        }
                    }

                } else if (is_pregnent.equals("no")) {
                    if (breast_feeding.equals("") || breast_feeding == null) {
                        new MyIntent(act, Profile_Breast_Feeding_5.class, R.anim.enter, R.anim.exit);
                    } else if (is_diabetic.equals("") || is_diabetic == null) {
                        new MyIntent(act, Profile_Diabetic_7.class, R.anim.enter, R.anim.exit);
                    } else if (is_diabetic.equals("yes")) {
                        if (taking_insulin.equals("") || taking_insulin == null || diabetic_diet.equals("") || diabetic_diet == null) {
                            new MyIntent(act, Profile_Insulin_8.class, R.anim.enter, R.anim.exit);
                        } else if (last_HBA1C.equals("") || last_HBA1C == null || blood_sugar_no_of_times.equals("") || blood_sugar_no_of_times == null || blood_sugar_in.equals("") || blood_sugar_in == null || last_fasting.equals("") || last_fasting == null || pp_value.equals("") || pp_value == null) {
                            new MyIntent(act, Profile_Blood_Suger_9.class, R.anim.enter, R.anim.exit);
                        } else if (activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null) {
                            new MyIntent(act, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                            new MyIntent(act, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        }
                        /*---------------*/
                        else if (is_smoke.equals("") || is_smoke == null) {
                            new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                        } /*else if (smoke_how_often.equals("") || smoke_how_often == null) {
                            new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                        }*/ else if (is_alcholic.equals("") || is_alcholic == null) {
                         //   Toast.makeText(act, "0", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                        }
                   /* else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
                        Toast.makeText(act, "3", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                    }*/

                        else if (adv_key.equals("")) {
                            new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
                        } else if (Senter_inc.equals("") || Senter_inc == null || SSystolic.equals("") || SSystolic == null || Senter_cmc.equals("") || Senter_cmc == null || SDiastolic.equals("") || SDiastolic == null) {
                            new MyIntent(act, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);
                        } else if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || blood_sugar.equals("") || blood_sugar == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null) {
                            new MyIntent(act, LABORATORY.class, R.anim.enter, R.anim.exit);
                        } else if (heart_diseas.equals("") || heart_diseas == null || Bp.equals("") || Bp == null) {
                            new MyIntent(act, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                        }else {

                        }
//                        else if (is_alcholic.equals("") || is_alcholic == null) {
//                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
//                        } else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
//                            if (is_alcholic.equals("yes")) {
//                                new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
//                            }else{
//                                new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
//                            }
//                        }
                    } else if (is_diabetic.equals("no")) {
                        if (activity_level_one.equals("") || activity_level_one == null || activity_level_two.equals("") || activity_level_two == null) {
                            new MyIntent(act, Profile_Active_10.class, R.anim.enter, R.anim.exit);
                        } else if (food_habits.equals("") || food_habits == null || any_heart_disease.equals("") || any_heart_disease == null) {
                            new MyIntent(act, Profile_Food_habits_11.class, R.anim.enter, R.anim.exit);
                        }
                         /*---------------*/
                        else if (is_smoke.equals("") || is_smoke == null) {
                            new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                        }/* else if (smoke_how_often.equals("") || smoke_how_often == null) {
                            new MyIntent(act, Smoking.class, R.anim.enter, R.anim.exit);
                        }*/ else if (is_alcholic.equals("") || is_alcholic == null) {
                           // Toast.makeText(act, "0", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                        }
                   /* else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
                        Toast.makeText(act, "3", Toast.LENGTH_SHORT).show();
                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
                    }*/

                        else if (adv_key.equals("")) {
                            new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
                        } else if (Senter_inc.equals("") || Senter_inc == null || SSystolic.equals("") || SSystolic == null || Senter_cmc.equals("") || Senter_cmc == null || SDiastolic.equals("") || SDiastolic == null) {
                            new MyIntent(act, Body_PARAMETERS.class, R.anim.enter, R.anim.exit);
                        } else if (Hemoglobin.equals("") || Hemoglobin == null || Hematocrit.equals("") || Hematocrit == null || blood_sugar.equals("") || blood_sugar == null || Total_cholesterol.equals("") || Total_cholesterol == null || LDL_cholesterol.equals("") || LDL_cholesterol == null || HDL_cholesterol.equals("") || HDL_cholesterol == null || Triglycerides.equals("") || Triglycerides == null || Vitamin_levels.equals("") || Vitamin_levels == null || Vitamin_B12_levels.equals("") || Vitamin_B12_levels == null) {
                            new MyIntent(act, LABORATORY.class, R.anim.enter, R.anim.exit);
                        } else if (heart_diseas.equals("") || heart_diseas == null || Bp.equals("") || Bp == null) {
                            new MyIntent(act, RISK_RELATED_QUESTIONS.class, R.anim.enter, R.anim.exit);
                        }
                        else {

                        }

//                        else if (is_alcholic.equals("") || is_alcholic == null) {
//                            new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
//                        } else if (alcohol_how_often.equals("") || alcohol_how_often == null) {
//                            if (is_alcholic.equals("yes")) {
//                                new MyIntent(act, Alcohol.class, R.anim.enter, R.anim.exit);
//                            }else {
//                                new MyIntent(act, Profile_complete.class, R.anim.enter, R.anim.exit);
//                            }
//
//                        }
                    }
                }



            }



        }
    }
}