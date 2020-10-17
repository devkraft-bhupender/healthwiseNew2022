package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by PAWAN on 23-05-2017.
 */

public class ExcerciseModel implements Serializable {

    String excerciseName,desc;
    int icon;
    String image;

    public ExcerciseModel(String excerciseName, String desc, int icon) {
        this.excerciseName = excerciseName;
        this.desc = desc;
        this.icon = icon;
    }

    public ExcerciseModel(String excerciseName, String image) {
        this.excerciseName = excerciseName;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExcerciseName() {
        return excerciseName;
    }

    public void setExcerciseName(String excerciseName) {
        this.excerciseName = excerciseName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
