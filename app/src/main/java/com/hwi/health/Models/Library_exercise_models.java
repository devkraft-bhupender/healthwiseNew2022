package com.hwi.health.Models;

/**
 * Created by PAWAN on 31-08-2017.
 */

public class Library_exercise_models {
    String name,image,id,link;

    public Library_exercise_models(String name, String image, String id, String link) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
