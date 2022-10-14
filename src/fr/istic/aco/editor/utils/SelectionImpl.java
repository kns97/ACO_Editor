package fr.istic.aco.editor.utils;

import fr.istic.aco.editor.Selection;

public class SelectionImpl implements Selection {
    private int beginIndex;
    private int endIndex;
    private int bufferId;

    public SelectionImpl(int bufferId) {
        this.bufferId = beginIndex;
        this.beginIndex = 0;
        this.endIndex = 0;
    }

    @Override
    public int getBeginIndex() {
        return this.beginIndex;
    }

    @Override
    public int getEndIndex() {
        return this.endIndex;
    }

    @Override
    public int getBufferBeginIndex() {
        return 0;
    }

    @Override
    public int getBufferEndIndex() {
        return 0;
    }

    @Override
    public void setBeginIndex(int beginIndex) throws IndexOutOfBoundsException{
    	if(beginIndex < 0) throw new IndexOutOfBoundsException("One or more arguments are invalid");
        this.beginIndex = beginIndex;
    }

    @Override
    public void setEndIndex(int endIndex){
    	if(endIndex < 0) throw new IndexOutOfBoundsException("One or more arguments are invalid");
        this.endIndex = endIndex;
    }

    @Override
    public void setBufferId(int bufferId) {
        this.bufferId = bufferId;
    }

    @Override
    public int getBufferId() {
        return bufferId;
    }
}
