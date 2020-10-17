package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by PAWAN on 07-09-2017.
 */

public class Resistance_result_Models implements Serializable {

    String upper,lower,full,Library,date;

    public Resistance_result_Models(String upper, String lower, String full, String library, String date) {
        this.upper = upper;
        this.lower = lower;
        this.full = full;
        Library = library;
        this.date = date;
    }

    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public String getLower() {
        return lower;
    }

    public void setLower(String lower) {
        this.lower = lower;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getLibrary() {
        return Library;
    }

    public void setLibrary(String library) {
        Library = library;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
