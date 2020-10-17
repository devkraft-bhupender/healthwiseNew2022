package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by julee on 08-11-2017.
 */

public class Eat_out_Beverage_models implements Serializable {
    String foodname , Indian_name , Major_food_category , Raw_cooked , unit_notes , Weight_in_ms , energy , protein , fat , Carbs , Sugar , fibre , sat_fat ;
    String cholestrol_bev ;
    String sodium_bev;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    int index;

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    String numbers;

    public Eat_out_Beverage_models(String foodname, String indian_name, String major_food_category, String raw_cooked, String unit_notes, String weight_in_ms, String energy, String protein, String fat, String carbs, String sugar, String fibre, String sat_fat, String cholestrol_bev,String sodium_bev,String numbers) {
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
        this.cholestrol_bev = cholestrol_bev;
        this.sodium_bev = sodium_bev;
        this.numbers=numbers;
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

    public String getCholestrol_bev() {
        return cholestrol_bev;
    }

    public void setCholestrol_bev(String cholestrol_bev) {
        this.cholestrol_bev = cholestrol_bev;
    }

    public String getSodium_bev() {
        return sodium_bev;
    }

    public void setSodium_bev(String sodium_bev) {
        this.sodium_bev = sodium_bev;
    }

    @Override
    public String toString() {
        return foodname+"("+unit_notes+")";
    }
}
