package com.example.phamn.learningtoeic.Model;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class TitleOnPhone {
    private String titleName;
    private int part1ID;
    private int part2ID;
    private int part3ID;
    private int part4ID;
    private String time1;
    private String time2;
    private String time3;
    private String time4;
    private int numberOfQuestions1;
    private int numberOfQuestions2;
    private int numberOfQuestions3;
    private int numberOfQuestions4;
    private History history1;
    private History history2;
    private History history3;
    private History history4;

    public TitleOnPhone(String titleName, int part1ID, int part2ID, int part3ID, int part4ID,
                        String time1, String time2, String time3, String time4,
                        int numberOfQuestions1, int numberOfQuestions2, int numberOfQuestions3, int numberOfQuestions4,
                        History history1, History history2, History history3, History history4) {
        this.titleName = titleName;
        this.part1ID = part1ID;
        this.part2ID = part2ID;
        this.part3ID = part3ID;
        this.part4ID = part4ID;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
        this.numberOfQuestions1 = numberOfQuestions1;
        this.numberOfQuestions2 = numberOfQuestions2;
        this.numberOfQuestions3 = numberOfQuestions3;
        this.numberOfQuestions4 = numberOfQuestions4;
        this.history1 = history1;
        this.history2 = history2;
        this.history3 = history3;
        this.history4 = history4;
    }

    public TitleOnPhone() {

    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getPart1ID() {
        return part1ID;
    }

    public void setPart1ID(int part1ID) {
        this.part1ID = part1ID;
    }

    public int getPart2ID() {
        return part2ID;
    }

    public void setPart2ID(int part2ID) {
        this.part2ID = part2ID;
    }

    public int getPart3ID() {
        return part3ID;
    }

    public void setPart3ID(int part3ID) {
        this.part3ID = part3ID;
    }

    public int getPart4ID() {
        return part4ID;
    }

    public void setPart4ID(int part4ID) {
        this.part4ID = part4ID;
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

    public History getHistory1() {
        return history1;
    }

    public void setHistory1(History history1) {
        this.history1 = history1;
    }

    public History getHistory2() {
        return history2;
    }

    public void setHistory2(History history2) {
        this.history2 = history2;
    }

    public History getHistory3() {
        return history3;
    }

    public void setHistory3(History history3) {
        this.history3 = history3;
    }

    public History getHistory4() {
        return history4;
    }

    public void setHistory4(History history4) {
        this.history4 = history4;
    }
}
