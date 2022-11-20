package fr.istic.aco.editor.utils;


public class Buffer {
    private String text;

    public Buffer() {
        this.text = "";
    }
    public Buffer(String s) {
        this.text = s;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
