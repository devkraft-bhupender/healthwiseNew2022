package com.hwi.health.InterFaces;

import com.hwi.health.Usages.RandomNumber;

import java.util.Random;

import static android.R.attr.x;

/**
 * Created by Win on 6/14/2017.
 */

public interface BaseUrl {

    //String URLS ="https://www.forhealthyindia.com/HWI/API/";
   // String URLS="http://3.230.219.247/HWI/API/";
   // String URLS="http://ec2-3-88-174-183.compute-1.amazonaws.com/HWI/";
    String URLS="http://ec2-18-208-161-123.compute-1.amazonaws.com/HWI/";
    String IMAGEURL = "http://ec2-18-208-161-123.compute-1.amazonaws.com/HWI/uploads/files/" ;
   // String URLS ="http://192.168.0.7/HWI/API/";
    String AccessToken = "LTg7oeITZo";
    String registration = "registration/";
    String manage_profile = "manage_profile/";
    String get_all_food_item_and_beverage_list = "get_all_food_item_and_beverage_list/";
    String login = "login/";
    String logout = "logout/";
    String forgot_password = "forgot_password/";
    String weight_loss_calculator = "weight_loss_calculator/";
    String ideal_diet_plan_for_adult = "ideal_diet_plan_for_adult/";
    String ideal_diet_plan_for_child = "ideal_diet_plan_for_child/";
    String hwi_check_up_for_adults = "hwi_check_up_for_adults/";
    String hwi_heart_risk_calculator = "hwi_heart_risk_calculator/";
    String hwi_heart_risk_calculator1 = "hwi_heart_risk1_calculator/";
    String social_registration_login = "social_registration_login/";
    String update_profile = "update_basic_profile/";
    String getProfile = "get_user_profile/";
    String add_consult = "add_consult/";
    String insert_weight_log = "insert_weight_log/";
    String insert_BP_log = "insert_BP_log/";
    String insert_sugar_log = "insert_sugar_log/";
    String subscribe_email = "subscribe_email/";
    String get_all_question  = "get_all_question/";
    String post_question  = "post_question/";
    String get_ans_by_queid  = "get_ans_by_queid/";
    String post_answer  = "post_answer/";
    String get_uploadsfile  = "get_uploadsfile/";
    String upload_files  = "upload_file/";
    String get_profile_completion_persentage  = URLS+"API/get_profile_completion_persentage/";
    String customize_diet_plan_and_nutrition  = "API/customize_diet_plan_and_nutrition/";
    String Insert_Exercise_Info  = "Insert_Exercise_Info/";
    String Insert_Exercise_Intensity_Info  = "Insert_Exercise_Intensity_Info/";
    String insert_heart_rate  = "insert_heart_rate/";
    String health_checkup_and_report_analysis_and_advice  = "health_checkup_and_report_analysis_and_advice/";
    String Get_Exercise_Intensity_Info  = "Get_Exercise_Intensity_Info/";
    String Get_Exercise_Info  = "Get_Exercise_Info/";
    String get_alcohol_master_details  = "get_alcohol_master_details/";
    String get_eat_out_calculation_formula  = "get_eat_out_calculation_formula/";
    String get_weight_log  = URLS+"API/get_weight_log/";
    String login_check  = URLS+"login_check/";
    String getV = "https://healthwiseindian.com/obesity-prediabetes-metabolic-syndrome-and-diabetes-the-full-spectrum/target=_blank";



}
