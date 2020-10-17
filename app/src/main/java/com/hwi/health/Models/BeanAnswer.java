package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by PAWAN on 14-07-2017.
 */

public class BeanAnswer implements Serializable {
    String ans,date,time,qus_id,name;

    public BeanAnswer(String ans, String date, String time, String name) {
        this.ans = ans;
        this.date = date;
        this.time = time;
        this.name = name;
    }

    public BeanAnswer() {
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
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

    public String getQus_id() {
        return qus_id;
    }

    public void setQus_id(String qus_id) {
        this.qus_id = qus_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
