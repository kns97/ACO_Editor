package fr.istic.aco.editor.utils;

/**
 * Class to handle the clipboard
 */
public class Clipboard {
    private String text;

    /**
     * Constructor
     */
    public Clipboard() {
    	this.text = "";
    }

    /**
     * Getter for the content of the clipboard
     * @return the text inside the clipboard
     */
    public String getText() {
        return text;
    }

    /**
     * Set content of the clipboard
     * @param text text to be copied in the clipboard
     */
    public void setText(String text) {
        this.text = text;
    }
}
