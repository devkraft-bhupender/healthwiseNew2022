package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by PAWAN on 19-05-2017.
 */

public class Active_Spinner implements Serializable{

    String name,id;

    public Active_Spinner(String name, String id) {
        this.name = name;
        this.id = id;
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
}
