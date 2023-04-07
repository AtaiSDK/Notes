package com.example.notes;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable  {
    private String title;
    private String description;
    private String Image;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Note(String title, String description, String image, String date) {
        this.title = title;
        this.description = description;
        Image = image;
        this.date = date;
    }
}
