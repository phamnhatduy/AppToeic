package com.example.phamn.learningtoeic.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Serial implements Serializable{
    @SerializedName("serialID") private int serialID;
    @SerializedName("serialName") private String serialName;

    public Serial() {
    }

    public Serial(int serialID, String serialName) {
        this.serialID = serialID;
        this.serialName = serialName;
    }

    public int getSerialID() {
        return serialID;
    }

    public void setSerialID(int serialID) {
        this.serialID = serialID;
    }

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }
}
