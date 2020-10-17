package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by julee on 29-12-2017.
 */

public class Baisc_diet_cla implements Serializable {
    float calories,carb,prot,fat,sfa,chole,sugar,sodium,fibre;

    String id,name;
    public Baisc_diet_cla(float calories, float carb, float prot, float fat, float sfa, float chole, float sugar, float sodium, float fibre, String  id, String name) {
        this.calories = calories;
        this.carb = carb;
        this.prot = prot;
        this.fat = fat;
        this.sfa = sfa;
        this.chole = chole;
        this.sugar = sugar;
        this.sodium = sodium;
        this.fibre = fibre;
        this.id = id;
        this.name = name;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getCarb() {
        return carb;
    }

    public void setCarb(float carb) {
        this.carb = carb;
    }

    public float getProt() {
        return prot;
    }

    public void setProt(float prot) {
        this.prot = prot;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getSfa() {
        return sfa;
    }

    public void setSfa(float sfa) {
        this.sfa = sfa;
    }

    public float getChole() {
        return chole;
    }

    public void setChole(float chole) {
        this.chole = chole;
    }

    public float getSugar() {
        return sugar;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getFibre() {
        return fibre;
    }

    public void setFibre(float fibre) {
        this.fibre = fibre;
    }

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String  getName() {
        return name;
    }

    public void setName(String  name) {
        this.name = name;
    }
}
