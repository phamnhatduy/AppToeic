package com.example.phamn.learningtoeic.Model;

public class Idiom {
    private String phrase;
    private String mean;

    public Idiom(String phrase, String mean) {
        this.phrase = phrase;
        this.mean = mean;
    }

    public Idiom() {
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
