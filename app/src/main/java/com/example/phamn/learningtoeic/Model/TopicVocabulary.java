package com.example.phamn.learningtoeic.Model;

import android.widget.ImageView;

public class TopicVocabulary {
    int imageID;
    String topic;

    public TopicVocabulary(int imageID, String topic) {
        this.imageID = imageID;
        this.topic = topic;
    }

    public TopicVocabulary() {
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
