package com.example.sarah.paramedicsguide;

public class VoiceToText {

    String text;
    String key;
    int num;

    public VoiceToText() {
    }

    public VoiceToText(String text, String key, int num) {
        this.text = text;
        this.key = key;
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public int getNum() {
        return num;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
