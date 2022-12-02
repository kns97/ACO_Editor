package fr.istic.aco.editor.utils;

import java.util.ArrayList;

public class HistoryManager {
    private ArrayList<Buffer> history;
    private int current_index;
    private int size;

    public HistoryManager(){
        history = new ArrayList<>();
        current_index = -1;
        size = 0;
    }
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
    public Buffer getPreviousBuffer(){
        if(current_index-1 > -1){
            current_index = current_index-1;

            return history.get(current_index);
        }else{
            return null;
        }
    }
    public Buffer getNextBuffer(){
        if(current_index+1 < history.size()){
            current_index = current_index+1;
            return history.get(current_index);
        }else{
            return null;
        }

    }

    public int getCurrent_index() {
        return current_index;
    }

    public ArrayList<Buffer> getHistory() {
            return history;
    }
}
