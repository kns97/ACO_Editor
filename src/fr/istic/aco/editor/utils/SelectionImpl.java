package fr.istic.aco.editor.utils;

import fr.istic.aco.editor.Selection;

/**
 * Class to handle the Selection
 */
public class SelectionImpl implements Selection {
    private int beginIndex;
    private int endIndex;
    private Buffer buffer;

    /**
     * Constructor
     * @param buffer to have the selection on
     */
    public SelectionImpl(Buffer buffer) {
        this.buffer = buffer;
        this.beginIndex = 0;
        this.endIndex = 0;
    }

    /**
     * Getter for the beginning index
     * @return selection starting index
     */
    @Override
    public int getBeginIndex() {
        return this.beginIndex;
    }

    /**
     * Getter for the ending index
     * @return selection stopping index
     */
    @Override
    public int getEndIndex() {
        return this.endIndex;
    }

    /**
     * Getter for the beginning of the buffer (0)
     * @return 0
     */
    @Override
    public int getBufferBeginIndex() {
        return 0;
    }

    /**
     * Getter for the last index of the buffer
     * @return lenght of the buffer
     */
    @Override
    public int getBufferEndIndex() {
        return buffer.getText().length();
    }

    /**
     * Setter for the beginning of the selection
     * @param beginIndex index to be set
     * @throws IndexOutOfBoundsException in case the begin index cannot be set
     */
    @Override
    public void setBeginIndex(int beginIndex) throws IndexOutOfBoundsException{
    	if(beginIndex < 0 || beginIndex > this.getBufferEndIndex()) throw new IndexOutOfBoundsException("One or more arguments are invalid");
        this.beginIndex = beginIndex;
    }

    /**
     * Setter for the ending of the selection
     * @param endIndex index to be set
     */
    @Override
    public void setEndIndex(int endIndex){
    	if(endIndex < 0 || endIndex > this.getBufferEndIndex()) throw new IndexOutOfBoundsException("One or more arguments are invalid");
        this.endIndex = endIndex;
    }

    /**
     * Getter for the buffer
     * @return the buffer
     */
    @Override
    public Buffer getBuffer() {
        return buffer;
    }
}