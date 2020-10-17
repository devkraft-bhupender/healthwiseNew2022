package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by PAWAN on 13-07-2017.
 */

public class BeanQuestion implements Serializable {
    String qus,date,time,qus_id;

    public BeanQuestion(String qus, String date, String time, String qus_id) {
        this.qus = qus;
        this.date = date;
        this.time = time;
        this.qus_id = qus_id;
    }

    public String getQus_id() {
        return qus_id;
    }

    public void setQus_id(String qus_id) {
        this.qus_id = qus_id;
    }

    public String getQus() {
        return qus;
    }

    public void setQus(String qus) {
        this.qus = qus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BeanQuestion() {
    }
}
