package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by PAWAN on 07-09-2017.
 */

public class Aerobic_result_Models implements Serializable {

    String duration,heart_rate,excercise_intensity,actually_exercise,date,id;

    public Aerobic_result_Models(String duration, String heart_rate, String excercise_intensity, String actually_exercise, String date, String id) {
        this.duration = duration;
        this.heart_rate = heart_rate;
        this.excercise_intensity = excercise_intensity;
        this.actually_exercise = actually_exercise;
        this.date = date;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public String getExcercise_intensity() {
        return excercise_intensity;
    }

    public void setExcercise_intensity(String excercise_intensity) {
        this.excercise_intensity = excercise_intensity;
    }

    public String getActually_exercise() {
        return actually_exercise;
    }

    public void setActually_exercise(String actually_exercise) {
        this.actually_exercise = actually_exercise;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
