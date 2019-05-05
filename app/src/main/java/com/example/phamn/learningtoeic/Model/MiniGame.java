package com.example.phamn.learningtoeic.Model;

public class MiniGame {
    private String content;
    private String mean;
    private String answer;
    private int audioID;

    public MiniGame(String content, String mean, String answer, int audioID) {
        this.content = content;
        this.mean = mean;
        this.answer = answer;
        this.audioID = audioID;
    }

    public MiniGame() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getAudioID() {
        return audioID;
    }

    public void setAudioID(int audioID) {
        this.audioID = audioID;
    }
}
