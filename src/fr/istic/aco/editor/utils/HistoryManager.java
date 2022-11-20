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
        System.out.println("Current index:" + current_index + " Size:"+history.size());

        if(current_index < history.size()-1){
            for(int i = history.size()-1;i>current_index;i--){
                System.out.println("Remove index:" + i+ "containing:"+history.get(i).getText());
                history.remove(i);
            }

        }
        history.add(b);
        current_index = history.size()-1;
        for(Buffer bf:history){
            System.out.print(bf.getText() + " ");
        }
    }
    public Buffer getPreviousBuffer(){
        if(current_index-1 > -1){
            current_index = current_index-1;
            System.out.println("Current index:" + current_index + " Size:"+history.size());

            return history.get(current_index);
        }else{
            System.out.println("Current index:" + current_index + " Size:"+history.size());

            return null;
        }
    }
    public Buffer getNextBuffer(){
        if(current_index+1 < history.size()){
            System.out.println("Current index:" + current_index + " Size:"+history.size());

            current_index = current_index+1;
            return history.get(current_index);
        }else{
            System.out.println("Current index:" + current_index + " Size:"+history.size());

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
