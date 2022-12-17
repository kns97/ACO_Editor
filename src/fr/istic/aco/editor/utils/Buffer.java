package fr.istic.aco.editor.utils;

/**
 * Class to handle the buffer
 */
public class Buffer {
    private String text;

    /**
     * Constructor
     */
    public Buffer() {
        this.text = "";
    }

    /**
     * Constructor
     * @param s String to initialize the buffer
     */
    public Buffer(String s) {
        this.text = s;
    }

    /**
     * Get text of the buffer
     * @return text inside this buffer
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text of this buffer
     * @param text text to be set
     */
    public void setText(String text) {
        this.text = text;
    }

}
