package com.example.phamn.learningtoeic.Model;

import java.util.ArrayList;
import java.util.List;

public class TitleOnPhone {
    private String titleName;
    private int partID;
    private String time1;
    private String time2;
    private String time3;
    private String time4;
    private int numberOfQuestions1;
    private int numberOfQuestions2;
    private int numberOfQuestions3;
    private int numberOfQuestions4;
    private List<History> listHistory = new ArrayList<>();

    public TitleOnPhone(String titleName, int partID, String time1, String time2, String time3, String time4,
                        int numberOfQuestions1, int numberOfQuestions2, int numberOfQuestions3, int numberOfQuestions4, List<History> listHistory) {
        this.titleName = titleName;
        this.partID = partID;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
        this.numberOfQuestions1 = numberOfQuestions1;
        this.numberOfQuestions2 = numberOfQuestions2;
        this.numberOfQuestions3 = numberOfQuestions3;
        this.numberOfQuestions4 = numberOfQuestions4;
        this.listHistory = listHistory;
    }

    public TitleOnPhone() {

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

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4;
    }

    public int getNumberOfQuestions1() {
        return numberOfQuestions1;
    }

    public void setNumberOfQuestions1(int numberOfQuestions1) {
        this.numberOfQuestions1 = numberOfQuestions1;
    }

    public int getNumberOfQuestions2() {
        return numberOfQuestions2;
    }

    public void setNumberOfQuestions2(int numberOfQuestions2) {
        this.numberOfQuestions2 = numberOfQuestions2;
    }

    public int getNumberOfQuestions3() {
        return numberOfQuestions3;
    }

    public void setNumberOfQuestions3(int numberOfQuestions3) {
        this.numberOfQuestions3 = numberOfQuestions3;
    }

    public int getNumberOfQuestions4() {
        return numberOfQuestions4;
    }

    public void setNumberOfQuestions4(int numberOfQuestions4) {
        this.numberOfQuestions4 = numberOfQuestions4;
    }

    public List<History> getListHistory() {
        return listHistory;
    }

    public void setListHistory(List<History> listHistory) {
        this.listHistory = listHistory;
    }
}
