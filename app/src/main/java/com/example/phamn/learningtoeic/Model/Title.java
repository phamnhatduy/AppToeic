package com.example.phamn.learningtoeic.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Title implements Serializable{
    @SerializedName("serialID") private int serialID;
    @SerializedName("serialName") private String serialName;
    @SerializedName("titleName") private String titleName;
    @SerializedName("partID") private int partID;
    @SerializedName("partName") private String partName;
    @SerializedName("audio") private String audio;
    @SerializedName("time") private String time;
    @SerializedName("numberOfQuestions") private int numberOfQuestions;

    public Title() {
    }

    public Title(int serialID, String serialName, String titleName, int partID, String partName, String audio, String time, int numberOfQuestions) {
        this.serialID = serialID;
        this.serialName = serialName;
        this.titleName = titleName;
        this.partID = partID;
        this.partName = partName;
        this.audio = audio;
        this.time = time;
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getSerialID() {
        return serialID;
    }

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }

    public void setSerialID(int serialID) {
        this.serialID = serialID;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
