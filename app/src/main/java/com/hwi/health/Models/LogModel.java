package com.hwi.health.Models;

/**
 * Created by PAWAN on 22-05-2017.
 */

public class LogModel {

    String name;
    int image;

    public LogModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public LogModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
