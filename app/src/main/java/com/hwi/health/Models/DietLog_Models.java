package com.hwi.health.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by julee on 15-11-2017.
 */

public class DietLog_Models implements Serializable {

    String date,food_type,foodname ,colories, Indian_name , Major_food_category , Raw_cooked , unit_notes , Weight_in_ms , energy , protein , fat , Carbs , Sugar , fibre , sat_fat,numbers ,cholestrol,sodium;
    String calories_cal;

    public ArrayList<String> getHm() {
        return hm;
    }

    public void setHm(ArrayList<String> hm) {
        this.hm = hm;
    }

    ArrayList<String> hm=new ArrayList<String>();
    public String getColories() {
        return colories;
    }

    public void setColories(String colories) {
        this.colories = colories;
    }

    public DietLog_Models(String foodname) {
        this.foodname = foodname;
    }

    public DietLog_Models(String foodname,
                          String indian_name,
                          String major_food_category, String raw_cooked,
                          String unit_notes, String weight_in_ms, String energy,
                          String protein, String fat, String carbs, String sugar,
                          String fibre, String sat_fat, String numbers, String cholestrol,
                          String sodium) {
        this.foodname = foodname;
        Indian_name = indian_name;
        Major_food_category = major_food_category;
        Raw_cooked = raw_cooked;
        this.unit_notes = unit_notes;
        Weight_in_ms = weight_in_ms;
        this.energy = energy;
        this.protein = protein;
        this.fat = fat;
        Carbs = carbs;
        Sugar = sugar;
        this.fibre = fibre;
        this.sat_fat = sat_fat;
        this.numbers = numbers;
        this.cholestrol = cholestrol;
        this.sodium = sodium;
    }

    public DietLog_Models(String foodname,String colories,String protein,
                          String fat, String carbs, String sugar, String fibre,
                          String sat_fat, String cholestrol, String sodium, String calories_cal
                          ,String date,String food_type) {
        this.foodname = foodname;
        this.protein = protein;
        this.fat = fat;
        Carbs = carbs;
        Sugar = sugar;
        this.fibre = fibre;
        this.sat_fat = sat_fat;
        this.cholestrol = cholestrol;
        this.sodium = sodium;
        this.calories_cal = calories_cal;
        this.date = date;
        this.food_type = food_type;
        this.colories = colories;
    }

    public DietLog_Models(String protein, String fat, String carbs, String sugar, String fibre, String sat_fat, String cholestrol, String sodium, String calories_cal) {
        this.protein = protein;
        this.fat = fat;
        Carbs = carbs;
        Sugar = sugar;
        this.fibre = fibre;
        this.sat_fat = sat_fat;
        this.cholestrol = cholestrol;
        this.sodium = sodium;
        this.calories_cal = calories_cal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getCalories_cal() {
        return calories_cal;
    }

    public void setCalories_cal(String calories_cal) {
        this.calories_cal = calories_cal;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getIndian_name() {
        return Indian_name;
    }

    public void setIndian_name(String indian_name) {
        Indian_name = indian_name;
    }

    public String getMajor_food_category() {
        return Major_food_category;
    }

    public void setMajor_food_category(String major_food_category) {
        Major_food_category = major_food_category;
    }

    public String getRaw_cooked() {
        return Raw_cooked;
    }

    public void setRaw_cooked(String raw_cooked) {
        Raw_cooked = raw_cooked;
    }

    public String getUnit_notes() {
        return unit_notes;
    }

    public void setUnit_notes(String unit_notes) {
        this.unit_notes = unit_notes;
    }

    public String getWeight_in_ms() {
        return Weight_in_ms;
    }

    public void setWeight_in_ms(String weight_in_ms) {
        Weight_in_ms = weight_in_ms;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbs() {
        return Carbs;
    }

    public void setCarbs(String carbs) {
        Carbs = carbs;
    }

    public String getSugar() {
        return Sugar;
    }

    public void setSugar(String sugar) {
        Sugar = sugar;
    }

    public String getFibre() {
        return fibre;
    }

    public void setFibre(String fibre) {
        this.fibre = fibre;
    }

    public String getSat_fat() {
        return sat_fat;
    }

    public void setSat_fat(String sat_fat) {
        this.sat_fat = sat_fat;
    }

    public String getCholestrol() {
        return cholestrol;
    }

    public void setCholestrol(String cholestrol) {
        this.cholestrol = cholestrol;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    @Override
    public String toString() {
        return foodname+"("+unit_notes+")";
    }

}
