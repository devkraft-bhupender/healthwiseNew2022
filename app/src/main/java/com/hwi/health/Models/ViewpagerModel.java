package com.hwi.health.Models;

/**
 * Created by PAWAN on 29-05-2017.
 */

public class ViewpagerModel {

    String name,id;

    public ViewpagerModel(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public ViewpagerModel() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
