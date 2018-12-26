package com.example.phamn.learningtoeic.Model;

public class Tips {
    private int imageID;
    private String title;

    public Tips() {
    }

    public Tips(int imageID, String title) {
        this.imageID = imageID;
        this.title = title;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
