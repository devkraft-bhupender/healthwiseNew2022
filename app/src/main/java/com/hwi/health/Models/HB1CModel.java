package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by Win on 6/15/2017.
 */

public class HB1CModel implements Serializable{

    String value,name;

    public HB1CModel(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
