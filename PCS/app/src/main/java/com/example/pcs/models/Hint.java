package com.example.pcs.models;

public class Hint {

    private String hintTittle;
    private String hintText;

    public Hint(String hintTittle, String hintText) {
        this.hintTittle = hintTittle;
        this.hintText = hintText;
    }

    public String getHintTittle() {
        return hintTittle;
    }

    public void setHintTittle(String hintTittle) {
        this.hintTittle = hintTittle;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }
}
