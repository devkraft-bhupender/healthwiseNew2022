package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by Win on 6/20/2017.
 */

public class Ideal_Diat_Model implements Serializable{

    String name,id;

    public Ideal_Diat_Model(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Ideal_Diat_Model() {
    }

    public Ideal_Diat_Model(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
