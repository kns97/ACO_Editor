package fr.istic.aco.editor.utils;

import java.util.ArrayList;

/**
 * Class to handle the history
 */
public class HistoryManager {
    private ArrayList<Buffer> history;
    private int current_index;
    private int size;

    /**
     * Constructor
     */
    public HistoryManager(){
        history = new ArrayList<>();
        current_index = -1;
        size = 0;
    }

    /**
     * Add a new buffer to the history (delete further branches of history)
     * @param b buffer to be added to history
     */
    public void addBuffer(Buffer b){
        if(current_index < history.size()-1){
            for(int i = history.size()-1;i>current_index;i--){
                history.remove(i);
            }

        }
        history.add(b);
        current_index = history.size()-1;
        for(Buffer bf:history){
        }
    }

    /**
     * Retrieve the buffer before the actual one in history, return null if at the start of history
     * @return previous buffer or null
     */
    public Buffer getPreviousBuffer(){
        if(current_index-1 > -1){
            current_index = current_index-1;

            return history.get(current_index);
        }else{
            return null;
        }
    }

    /**
     * Retrieve the buffer next to the actual one in history, return null if at the end of history
     * @return next buffer or null
     */
    public Buffer getNextBuffer(){
        if(current_index+1 < history.size()){
            current_index = current_index+1;
            return history.get(current_index);
        }else{
            return null;
        }

    }


    /**
     * Return the whole history
     * @return arraylist of buffers
     */
    public ArrayList<Buffer> getHistory() {
            return history;
    }
}
