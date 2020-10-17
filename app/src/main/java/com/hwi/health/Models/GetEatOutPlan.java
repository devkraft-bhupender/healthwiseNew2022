package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by Windows on 3/22/2018.
 */

public class GetEatOutPlan implements Serializable{
    String foodcategory, protien, fat, carbs, sugar, fiber, satfat, cholestrol, sodium;

    public GetEatOutPlan(String foodcategory, String protien, String fat, String carbs, String sugar, String fiber, String satfat, String cholestrol, String sodium) {
        this.foodcategory = foodcategory;
        this.protien = protien;
        this.fat = fat;
        this.carbs = carbs;
        this.sugar = sugar;
        this.fiber = fiber;
        this.satfat = satfat;
        this.cholestrol = cholestrol;
        this.sodium = sodium;
    }

    public GetEatOutPlan() {
    }

    public String getFoodcategory() {
        return foodcategory;
    }

    public void setFoodcategory(String foodcategory) {
        this.foodcategory = foodcategory;
    }

    public String getProtien() {
        return protien;
    }

    public void setProtien(String protien) {
        this.protien = protien;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getSatfat() {
        return satfat;
    }

    public void setSatfat(String satfat) {
        this.satfat = satfat;
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
        return foodcategory;
    }
}
