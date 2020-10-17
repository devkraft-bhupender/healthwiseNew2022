package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by pawan on 10/11/17.
 */

public class Alchohal_tracker_model{

    String first_type,name,quantity,Calories,Std_size_drinks,Carbs,Sugar,Sodium,numbers,id,date;




    public Alchohal_tracker_model() {
    }

    public Alchohal_tracker_model(String quantity, String calories, String std_size_drinks, String carbs, String sugar, String sodium, String date, String name) {
        this.quantity = quantity;
        Calories = calories;
        Std_size_drinks = std_size_drinks;
        Carbs = carbs;
        Sugar = sugar;
        Sodium = sodium;
       this.date = date;
        this.name = name;

    }

    public Alchohal_tracker_model(String first_type, String name, String quantity, String calories, String std_size_drinks, String carbs, String sugar, String sodium, String numbers, String id) {
        this.first_type = first_type;
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.numbers = numbers;
        Calories = calories;
        Std_size_drinks = std_size_drinks;
        Carbs = carbs;
        Sugar = sugar;
        Sodium = sodium;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getFirst_type() {
        return first_type;
    }

    public void setFirst_type(String first_type) {
        this.first_type = first_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public String getStd_size_drinks() {
        return Std_size_drinks;
    }

    public void setStd_size_drinks(String std_size_drinks) {
        Std_size_drinks = std_size_drinks;
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

    public String getSodium() {
        return Sodium;
    }

    public void setSodium(String sodium) {
        Sodium = sodium;
    }

    @Override
    public String toString() {
        return name;
    }



}
