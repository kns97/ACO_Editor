package fr.istic.aco.editor.utils;

public class Clipboard {
    private String text;
    
    public Clipboard() {
    	this.text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
