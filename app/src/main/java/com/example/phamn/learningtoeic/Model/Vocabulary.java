package com.example.phamn.learningtoeic.Model;

public class Vocabulary {
    private String word;
    private String spelling;
    private String mean;
    private String example;

    public Vocabulary() {
    }

    public Vocabulary(String word, String spelling, String mean, String example) {
        this.word = word;
        this.spelling = spelling;
        this.mean = mean;
        this.example = example;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
